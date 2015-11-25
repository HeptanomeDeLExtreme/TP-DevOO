package controleur;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import vue.FenetreIHM;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * 
 */
public class EtatPlanCharge extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatPlanCharge() {
    }

    public String toString(){
    	return "Etat Plan Chargé";
    }
    
    /**
     * @param fenetre
     */
    public void ouvrirPlan(Plan plan) {
    	try {
    		plan.nettoiePlan();
    		DeserialiseurPlanXML.charger(plan);
    		Controleur.setEtatCourant(Controleur.etatPlanCharge);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur plan");			
		}
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
    	try {
    		demandeDeLivraison.nettoieDemandeDeLivraison();
    		DeserialiseurDemandeDeLivraisonXML.charger(demandeDeLivraison,plan);
    		
    		int nbLivraisons = 0;
        	List<FenetreTemporelle> fenetres = demandeDeLivraison.getFenetres();
        	
    		for (FenetreTemporelle petitFenetre : fenetres) {
    			Set<Livraison> livraisonsFActuelle = petitFenetre.getLivraisons();
    			nbLivraisons += livraisonsFActuelle.size();
    		}
    		// Ajout de l'entrepot
    		nbLivraisons++;
    		demandeDeLivraison.setNbLivraisons(nbLivraisons);
            Controleur.setEtatCourant(Controleur.etatLivraisonChargee);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur livraisons");			
		}
    }
    
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison) {
        fenetre.afficheMessage("Veuillez charger une livraison !");
    }
    
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p){
        Intersection inter = plan.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
        if(inter != null){
	        fenetre.afficheMessage(inter.toString());
        }
        else{
        	fenetre.afficheMessage("Pas d'intersection ici !");
        }
    }

}