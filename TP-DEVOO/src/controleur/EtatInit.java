package controleur;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

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

    public String toString(){
    	return "Etat Init";
    }
    
    /**
     * @param fenetre
     */
    public void ouvrirPlan(Modele modele) {
    	Plan plan = modele.getPlan();
    	try {
    		plan.nettoiePlan();
        	if(modele.getDemandeDeLivraison() != null){
        		modele.getDemandeDeLivraison().nettoieDemandeDeLivraison();
        	}
    		DeserialiseurPlanXML.charger(plan);
    		Controleur.setEtatCourant(Controleur.etatPlanCharge);
    		JOptionPane.showMessageDialog(null, "Plan chargé correctement !", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur plan");	
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
			plan.nettoiePlan();
        	if(modele.getDemandeDeLivraison() != null){
        		modele.getDemandeDeLivraison().nettoieDemandeDeLivraison();
        	}
			Controleur.setEtatCourant(Controleur.etatInit);
		}
    }
    
    public void importerLivraison(FenetreIHM fenetre,Modele modele, Plan plan) {
        fenetre.afficheMessage("Veuillez charger un plan !");
    }
    
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison) {
        fenetre.afficheMessage("Veuillez charger un plan puis une livraison !");
    }
    
    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
    	fenetre.afficheMessage("Veuillez effectuer le calcul de la tournée !");
    }
    
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	fenetre.afficheMessage("Aucun plan chargé !");
    }

}