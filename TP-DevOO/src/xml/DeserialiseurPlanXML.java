package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.Plan;
import modele.Intersection;
import modele.Troncon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
		Element racine = document.getDocumentElement();
		if (racine.getNodeName().equals("Reseau")) {
			construireAPartirDeDOMXML(racine, plan);
		} else
			throw new ExceptionXML("Document non conforme");
	}

	private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan)
			throws ExceptionXML, NumberFormatException {
		NodeList listeIntersections = noeudDOMRacine.getElementsByTagName("Noeud");
		// 1er passage d'initialisation des intersections
		for (int i = 0; i < listeIntersections.getLength(); i++) {
			plan.ajoute(creeIntersection((Element) listeIntersections.item(i)));
		}
		// 2e passage : creation des tronçons - les intersections doivent déjà avoir été créées
		for (int i = 0; i < listeIntersections.getLength(); i++) {
			creeTroncons((Element) listeIntersections.item(i), plan);
		}
	}

	private static Intersection creeIntersection(Element elt) throws ExceptionXML {
	    Integer id = Integer.parseInt(elt.getAttribute("id"));
		Integer x = Integer.parseInt(elt.getAttribute("x"));
		Integer y = Integer.parseInt(elt.getAttribute("y"));
		
		return new Intersection(id, x, y);
	}
	
	private static void creeTroncons(Element noeud, Plan plan) throws ExceptionXML {
		Integer idTronconEntrant = Integer.parseInt(((Element)noeud.getParentNode()).getAttribute("id"));
		Intersection origine = plan.getIntersection(idTronconEntrant);
		NodeList listeTronconsXML = noeud.getElementsByTagName("LeTronconSortant");
		for (int i = 0; i < listeTronconsXML.getLength(); i++) {
			creeTroncon((Element)listeTronconsXML.item(i), plan, origine);
		}
	}

	private static Troncon creeTroncon(Element tronconXML, Plan plan, Intersection origine) throws ExceptionXML {
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
