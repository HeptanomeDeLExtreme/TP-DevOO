package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import vue.FenetreIHM;

/**
 * 
 */
public class EtatDefaut implements Etat {

    /**
     * Default constructor
     */
    public EtatDefaut() {
    }

    /**
     * @param fenetre
     */
    public void ouvrirPlan(Plan plan) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan) {
        // TODO implement here
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void ajouterLivraison(FenetreIHM fenetre) {
        // TODO implement here
    }

    /**
     * @param demandeDeLivraison
     */
    public void genererFeuilleRoute(DemandeDeLivraison demandeDeLivraison) {
        // TODO implement here
    }

    /**
     * @param listeCommande
     */
    public void undo(ListeCommande listeCommande) {
        // TODO implement here
    }

    /**
     * @param listeCommande
     */
    public void redo(ListeCommande listeCommande) {
        // TODO implement here
    }

    /**
     * @param livraison1 
     * @param livraison2
     */
    public void modifierLivraison(Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    public void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p) {

    }


	@Override
	public void clicDroit(FenetreIHM fenetre, Point p) {
		fenetre.afficheMessage("Clic droit, c'est bien mais ça ne sert à rien :) ");
	}

}