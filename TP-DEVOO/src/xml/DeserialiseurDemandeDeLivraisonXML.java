package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import modele.Client;
import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Horaire;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author H4203
 *
 */
public class DeserialiseurDemandeDeLivraisonXML {
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
        if (validateAgainstXSD(new FileInputStream(xml), new FileInputStream(new File("src/xml/XSDDemandeDeLivraison.xsd")))){
        	Document document = docBuilder.parse(xml);
        	Element racine = document.getDocumentElement();
            construireAPartirDeDOMXML(racine, demandeDeLivraison, plan);
        } 
        else{
        	throw new ExceptionXML("Document non conforme");
        }
        
	}
	
	/**
	 * Ouvre un fichier xml passé en parametre et cree une demande de livraison a partir du contenu du fichier
	 * @param DemandeDeLivraison demandeDeLivraison
	 * @param Plan plan
	 * @param File xml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(DemandeDeLivraison demandeDeLivraison, Plan plan, File xml) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        if (validateAgainstXSD(new FileInputStream(xml), new FileInputStream(new File("src/xml/XSDDemandeDeLivraison.xsd")))){
        	Document document = docBuilder.parse(xml);
        	Element racine = document.getDocumentElement();
            construireAPartirDeDOMXML(racine, demandeDeLivraison, plan);
        } 
        else{
        	throw new ExceptionXML("Document non conforme");
        }
        
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
    		Element Livraisons  = (Element) fenetreTemporelle.getElementsByTagName("Livraisons").item(0);
    		NodeList listeLivraisons = Livraisons.getElementsByTagName("Livraison");
    		for (int j = 0; j < listeLivraisons.getLength(); j++) {
    			fenetre.ajouteLivraison(creeLivraison((Element) listeLivraisons.item(j), plan, fenetre));
    		}
       	}
    }

	/**
	 * Cree une fenêtre temporelle
	 * @param Element item
	 * @return FenetreTemporelle fenetre
	 * @throws ExceptionXML 
	 */
	private static FenetreTemporelle creeFenetreTemporelle(Element fenetre) throws ExceptionXML {
		String[] dateDebXml = fenetre.getAttribute("heureDebut").split(":");
		String[] dateFinXml = fenetre.getAttribute("heureFin").split(":");
		Horaire dateDeb = new Horaire(Integer.parseInt(dateDebXml[0]), Integer.parseInt(dateDebXml[1]), Integer.parseInt(dateDebXml[2]));
		Horaire dateFin = new Horaire(Integer.parseInt(dateFinXml[0]), Integer.parseInt(dateFinXml[1]), Integer.parseInt(dateFinXml[2]));
		if (!dateDeb.isInferieurA(dateFin)){
			throw new ExceptionXML("Document non conforme : La date de début de la fenêtre doit être strictement supérieure à la date de fin.");
		}
		return new FenetreTemporelle(dateDeb, dateFin);
	}
	
	/**
	 * Cree une livraison
	 * @param Element item
	 * @param Plan plan
	 * @return Livraison livraison
	 * @throws ExceptionXML 
	 */
	private static Livraison creeLivraison(Element livraisonXml, Plan plan, FenetreTemporelle fenetre) throws ExceptionXML {
    	int idadresse = Integer.parseInt(livraisonXml.getAttribute("adresse"));
    	Intersection adresse = plan.recupererIntersectionParId(idadresse);
    	if(adresse == null){
    		throw new ExceptionXML("Document non conforme : Toutes les livraisons de la demande de livraison doivent correspondre à une adresse du plan.");
    	}
    	int idClient = Integer.parseInt(livraisonXml.getAttribute("client"));
    	Client client = new Client (idClient);
    	int id = Integer.parseInt(livraisonXml.getAttribute("id"));
    	return new Livraison(id, client, adresse, fenetre);
	}

	/**
	 * Cree un entrepot
	 * @param Element item
	 * @param Plan plan
	 * @return Livraison entrepot
	 * @throws ExceptionXML 
	 */
	private static Livraison creeEntrepot(Element entrepot, Plan plan) throws ExceptionXML {
    	int idadresse = Integer.parseInt(entrepot.getAttribute("adresse"));
    	Intersection adresse = plan.recupererIntersectionParId(idadresse);
    	if(adresse == null){
    		throw new ExceptionXML("Document non conforme : L'entrepot de la demande de livraison n'est pas dans le plan.");
    	}
    	return new Livraison(0, adresse);
	}
	
	private static boolean validateAgainstXSD(InputStream xml, InputStream xsd) throws ExceptionXML
	{
	    try
	    {
	        SchemaFactory factory = 
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    }
	    catch(Exception ex)
	    {
	    	throw new ExceptionXML("Document non conforme : " + ex.getMessage());
	    }
	}
 
}