package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Plan;

import vue.FenetreIHM;

/**
 * 
 */
public class EtatLivraisonChargee extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatLivraisonChargee() {
    }

    /**
     * @param fenetre
     */
    public void ouvrirPlan(Plan plan) {
        plan.chargerPlan();
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan){
    	demandeDeLivraison.chargerLivraison(plan);
    	Controleur.setEtatCourant(Controleur.etatLivraisonChargee);
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Plan plan, DemandeDeLivraison demandeDeLivraison) {
        // TODO implement here
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