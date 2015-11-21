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
public interface Etat {

    /**
     * @param fenetre
     */
    public void ouvrirPlan(FenetreIHM fenetre);

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre);

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Plan plan, DemandeDeLivraison demandeDeLivraison, TSP1 tsp);

    /**
     * @param fenetre
     */
    public void ajouterLivraison(FenetreIHM fenetre);

    /**
     * @param demandeDeLivraison
     */
    public void genererFeuilleRoute(DemandeDeLivraison demandeDeLivraison);

    /**
     * @param listeCommande
     */
    public void undo(ListeCommande listeCommande);

    /**
     * @param listeCommande
     */
    public void redo(ListeCommande listeCommande);

    /**
     * @param livraison1 
     * @param livraison2
     */
    public void modifierLivraison(Livraison livraison1, Livraison livraison2);

    /**
     * @param livraison
     */
    public void supprimeLivraison(Livraison livraison);

    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande);

}