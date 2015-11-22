package controleur;

import java.util.*;

import modele.DemandeDeLivraison;
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

    /**
     * @param fenetre
     */
    public void ouvrirPlan(FenetreIHM fenetre) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        demandeDeLivraison.chargerLivraison(plan);
    }

}