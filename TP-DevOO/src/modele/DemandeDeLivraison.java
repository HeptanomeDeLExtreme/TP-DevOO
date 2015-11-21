package modele;

import java.util.*;

/**
 * 
 */
public class DemandeDeLivraison {

    /**
     * Default constructor
     */
    public DemandeDeLivraison() {
    }

    /**
     * 
     */
    protected Tournee tournee;

    /**
     * 
     */
    protected List<FenetreTemporelle> fenetres;

    /**
     * 
     */
    protected Livraison entrepot;

    /**
     * 
     */
    protected DemandeDeLivraison singleton;

    /**
     * @param livraison1 
     * @param livraison2
     */
    protected void modifierTournee(Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param livraisonAvant 
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param plan
     */
    protected void calculerTournee(Plan plan) {
        // TODO implement here
    }

    /**
     * @param plan 
     * @param depart 
     * @param arrivee
     */
    protected void calculPlusCourtChemin(Plan plan, Livraison depart, Livraison arrivee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genereFeuilleDeRoute() {
        // TODO implement here
    }

    /**
     * Permet d'ajouter une livraison. Ne doit servir que lors de la création de la demande de livraison lors de l'ajout de l'entrepot
     * @param Livraison entrepot
     */
	public void ajouteEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}
	
    /**
     * Permet d'ajouter une fenetre temporelle. Ne doit servir que lors de la création de la demande de livraison
     * @param FenetreTemporelle fenetreTemporelle
     */
	public void ajouteFenetreTemporelle(FenetreTemporelle fenetreTemporelle) {
		this.fenetres.add(fenetreTemporelle);
	}

}