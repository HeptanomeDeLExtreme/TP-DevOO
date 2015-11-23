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
        plan.chargerPlan();
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        demandeDeLivraison.chargerLivraison(plan);
        Controleur.setEtatCourant(Controleur.etatLivraisonChargee);
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