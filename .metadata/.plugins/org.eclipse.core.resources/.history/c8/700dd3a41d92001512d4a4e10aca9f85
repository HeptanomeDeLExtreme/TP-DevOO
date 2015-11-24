package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Livraison;
import modele.Plan;

import vue.FenetreIHM;

/**
 * 
 */
public interface Etat {

    /**
     * @param plan
     */ 
    public void ouvrirPlan(Plan plan);

    /**
     * @param fenetre
     * @param plan 
     * @param demandeDeLivraison 
     */
    public void importerLivraison(FenetreIHM fenetre, DemandeDeLivraison demandeDeLivraison, Plan plan);

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Plan plan, DemandeDeLivraison demandeDeLivraison);

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
     * @param p
     */
    public void clicDroit(FenetreIHM fenetre, Point p);

	public void clicGauche(FenetreIHM fenetre, Plan plan, Point p);

}