package controleur;

import java.util.*;

import modele.DemandeDeLivraison;
import modele.Livraison;
import modele.Plan;

import tsp.TSP1;
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
    public void ouvrirPlan(FenetreIHM fenetre) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre) {
        // TODO implement here
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Plan plan, DemandeDeLivraison demandeDeLivraison, TSP1 tsp) {
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
    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande) {
        // TODO implement here
    }

}