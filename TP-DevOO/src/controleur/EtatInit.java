package controleur;

import java.awt.Point;
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

    public String toString(){
    	return "Etat Init";
    }
    
    /**
     * @param fenetre
     */
    public void ouvrirPlan(Plan plan) {
        plan.chargerPlan();
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
    }
    
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        fenetre.afficheMessage("Veuillez charger un plan !");
    }
    
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison) {
        fenetre.afficheMessage("Veuillez charger un plan puis une livraison !");
    }
    
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p){
    	fenetre.afficheMessage("Aucun plan chargé !");
    }

}