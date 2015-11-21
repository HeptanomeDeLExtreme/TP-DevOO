package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.Plan;
import modele.Intersection;
import modele.Troncon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author H4203
 * 
 */
public class DeserialiseurPlanXML {
	/**
	 * Ouvre un fichier xml et cree plan a partir du contenu du fichier
	 * 
	 * @param plan
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document document = docBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element racine = document.getDocumentElement();
		
		if (racine.getNodeName().equals("Reseau")) {
			construireAPartirDeDOMXML(racine, plan);
		} else
			throw new ExceptionXML("Document non conforme");
	}

	/**
	 * Construit les intersections et les tronçons a partir du XML
	 * 
	 * @param noeudDOMRacine
	 * @param plan
	 * @throws ExceptionXML
	 * @throws NumberFormatException
	 */
	private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan)
			throws ExceptionXML, NumberFormatException {
		System.out.println("Je suis dans Deserialiseur.construireAvecDOM");
		NodeList listeIntersections = noeudDOMRacine.getElementsByTagName("Noeud");
		// 1er passage d'initialisation des intersections
		for (int i = 0; i < listeIntersections.getLength(); i++) {
			plan.ajoute(creerIntersection((Element) listeIntersections.item(i)));
			
		}
		System.out.println(listeIntersections.item(2));
		// 2e passage : creation des tronçons - les intersections doivent déjà avoir été créées
		for (int i = 0; i < listeIntersections.getLength(); i++) {
			creerTroncons((Element) listeIntersections.item(i), plan);
		}
	}

	/**
	 * Cree une Intersection à partir d'un Element, correspondant a un noeud DOM
	 * 
	 * @param elt
	 * @return
	 * @throws ExceptionXML
	 */
	private static Intersection creerIntersection(Element elt) throws ExceptionXML {
	    Integer id = Integer.parseInt(elt.getAttribute("id"));
		Integer x = Integer.parseInt(elt.getAttribute("x"));
		Integer y = Integer.parseInt(elt.getAttribute("y"));
		
		return new Intersection(id, x, y);
	}
	
	/**
	 * Cree les Troncons à partir des Elements noeuds et du Plan
	 * 
	 * @param noeud
	 * @param plan
	 * @throws ExceptionXML
	 */
	private static void creerTroncons(Element noeud, Plan plan) throws ExceptionXML {
		//TODO test a enlever
		System.out.println("" + ((Element)noeud.getParentNode()).getAttribute("id"));
		Integer idTronconEntrant = Integer.parseInt(((Element)noeud.getParentNode()).getAttribute("id"));
		Intersection origine = plan.getIntersection(idTronconEntrant);
		NodeList listeTronconsXML = noeud.getElementsByTagName("LeTronconSortant");
		for (int i = 0; i < listeTronconsXML.getLength(); i++) {
			creerTroncon((Element)listeTronconsXML.item(i), plan, origine);
		}
	}

	/**
	 * Cree un troncon
	 * 
	 * @param tronconXML
	 * @param plan
	 * @param origine
	 * @return troncon
	 * @throws ExceptionXML
	 */
	private static Troncon creerTroncon(Element tronconXML, Plan plan, Intersection origine) throws ExceptionXML {
		String nomRue = tronconXML.getAttribute("nomRue"); 
		Float vitesse = Float.parseFloat(tronconXML.getAttribute("vitesse"));
		Float longueur = Float.parseFloat(tronconXML.getAttribute("longueur"));
		Integer idDestination = Integer.parseInt(tronconXML.getAttribute("id"));
		Intersection destination = plan.getIntersection(idDestination);
		Troncon troncon = new Troncon(nomRue, vitesse, longueur, origine, destination);
		origine.ajouteTronconSortant(troncon);
		destination.ajouteTronconEntrant(troncon);
		return troncon;
	}

}
