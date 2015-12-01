package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

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
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison);

    /**
     * @param fenetre
     */
    public void ajouterLivraison(Modele modele, ListeCommande ldc);

    /**
     * @param tournee
     */
    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee);

    /**
     * @param listeCommande
     */
    public void undo(ListeCommande listeCommande);

    /**
     * @param listeCommande
     */
    public void redo(ListeCommande listeCommande);

    /**
     * @param tournee 
     * @param listeCommandes
     */
    public void modifierLivraison(Modele modele, ListeCommande listeCommandes);

    /**
     * @param listeCommandes 
     * @param livraison
     */
    public void supprimeLivraison(Modele modele, ListeCommande listeCommandes);

    /**
     * @param fenetre 
     * @param p
     */
    public void clicDroit(FenetreIHM fenetre, Point p);

	public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl);

}