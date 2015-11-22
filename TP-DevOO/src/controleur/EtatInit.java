package controleur;

import java.util.*;

import modele.DemandeDeLivraison;
import modele.Plan;

import vue.FenetreIHM;

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
    }
    
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        fenetre.afficheMessage("Veuillez charger un plan !");
    }

}