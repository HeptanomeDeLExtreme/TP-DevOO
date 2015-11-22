package controleur;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.Plan;

import vue.FenetreIHM;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * 
 */
public class EtatInit extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatInit() {
    }

    /**
     * @param fenetre
     */
    public void ouvrirPlan(Plan plan) {
        plan.chargerPlan();
//    	try {
//			DeserialiseurPlanXML.charger(plan);
//		} catch (ParserConfigurationException 
//				| SAXException | IOException 
//				| ExceptionXML | NumberFormatException e) {
//		}
    }
    
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        fenetre.afficheMessage("Veuillez charger un plan !");
    }

}