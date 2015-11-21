package xml;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.Client;
import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DeserialiseurXmlDemandeDeLivraison {
	/**
	 * Ouvre un fichier xml et cree une demande de livraison a partir du contenu du fichier
	 * @param DemandeDeLivraison demandeDeLivraison
	 * @param Plan plan
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(DemandeDeLivraison demandeDeLivraison, Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("JourneeType")) {
           construireAPartirDeDOMXML(racine, demandeDeLivraison, plan);
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}

	/**
	 * Construit l'objet demande de livraison à partir du fichier xml chargé
	 * @param Element noeudDOMRacine
	 * @param DemandeDeLivraison demandeDeLivraison
	 * @param Plan plan
	 * @throws ExceptionXML
	 * @throws NumberFormatException
	 */
    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, DemandeDeLivraison demandeDeLivraison, Plan plan) throws ExceptionXML, NumberFormatException{
    	
    	//Création de l'entrepot
    	Element entrepot  = (Element) noeudDOMRacine.getElementsByTagName("Entrepot").item(0);
    	demandeDeLivraison.ajouteEntrepot(creeEntrepot(entrepot, plan)); 
    	
    	// Création des fenêtres temporelles
    	Element plagesHoraires  = (Element) noeudDOMRacine.getElementsByTagName("PlagesHoraires").item(0);
    	NodeList listePlagesHoraire = plagesHoraires.getElementsByTagName("Plage");
    	
    	for (int i = 0; i < listePlagesHoraire.getLength(); i++) {
    		
    		Element fenetreTemporelle = (Element) listePlagesHoraire.item(i);
    		FenetreTemporelle fenetre = creeFenetreTemporelle(fenetreTemporelle);
    		demandeDeLivraison.ajouteFenetreTemporelle(fenetre);
    		
    		//Création pour chaque fenêtre de sa liste de livraisons
    		Element Livraisons  = (Element) noeudDOMRacine.getElementsByTagName("Livraisons").item(0);
    		NodeList listeLivraisons = Livraisons.getElementsByTagName("Livraison");
    		for (int j = 0; j < listeLivraisons.getLength(); j++) {
    			fenetre.ajouteLivraison(creeLivraison((Element) listeLivraisons.item(j), plan));
    		}
       	}
    }

	/**
	 * Cree une fenêtre temporelle
	 * @param Element item
	 * @return FenetreTemporelle fenetre
	 */
	private static FenetreTemporelle creeFenetreTemporelle(Element fenetre) {
		String[] dateDebXml = fenetre.getAttribute("heureDebut").split(":");
		String[] dateFinXml = fenetre.getAttribute("heureFin").split(":");
		GregorianCalendar dateDeb = new GregorianCalendar(1970, 0, 1, Integer.parseInt(dateDebXml[0]), Integer.parseInt(dateDebXml[1]), Integer.parseInt(dateDebXml[2]));
		GregorianCalendar dateFin = new GregorianCalendar(1970, 0, 1, Integer.parseInt(dateFinXml[0]), Integer.parseInt(dateFinXml[1]), Integer.parseInt(dateFinXml[2]));
		return new FenetreTemporelle(dateDeb, dateFin);
	}
	
	/**
	 * Cree une livraison
	 * @param Element item
	 * @return Livraison livraison
	 */
	private static Livraison creeLivraison(Element livraisonXml, Plan plan) {
    	int idadresse = Integer.parseInt(livraisonXml.getAttribute("adresse"));
    	Intersection adresse = plan.recupererIntersectionParId(idadresse);
    	int idClient = Integer.parseInt(livraisonXml.getAttribute("client"));
    	Client client = new Client (idClient);
    	int id = Integer.parseInt(livraisonXml.getAttribute("id"));
    	return new Livraison(id, client, adresse);
	}

	/**
	 * Cree un entrepot
	 * @param Element item
	 * @return Livraison entrepot
	 */
	private static Livraison creeEntrepot(Element entrepot, Plan plan) {
    	int idadresse = Integer.parseInt(entrepot.getAttribute("adresse"));
    	Intersection adresse = plan.recupererIntersectionParId(idadresse);
    	return new Livraison(0, adresse);
	}
 
}