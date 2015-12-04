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
     * Charger le plan.
     * @param modele Le modele dans lequel ouvrir le plan.
     */ 
    public void ouvrirPlan(Modele modele);

    /**
     * Charger la demande de livraison.
     * @param fenetre La fenetre ou afficher les eventuels messages.
     * @param modele Le modele dans lequel ouvrir la demande de livraison. 
     */
    public void importerLivraison(FenetreIHM fenetre,Modele modele, Plan plan);

    /**
     * Calculer la tournee.
     * @param modele Le modele avec lequel calculer la tournee. 
     * @param fenetre La fenetre ou afficher les eventuels messages.
     */
    public void calculerTournee(Modele modele, FenetreIHM fenetre);
    
    /**
     * Ajouter une livraison.
     * @param modele Le modele dans lequel ajouter les livraisons.
     * @param ldc La liste dans laquelle ajouter la commande.
     */
    public void ajouterLivraison(Modele modele, ListeCommande ldc);

    /**
     * Generer la feuille de route.
     * @param tournee
     */
    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee);

    /**
     * Undo la derniere commande.
     * @param listeCommande La liste de commande a undo.
     */
    public void undo(ListeCommande listeCommande);

    /**
     * Redo la derniere commande.
     * @param listeCommande La liste de commande a redo.
     */
    public void redo(ListeCommande listeCommande);

    /**
     * Modifier la livraison
     * @param modele Le modele dans lequel intervertir les livraisons
     * @param listeCommandes La liste dans lequel ajouter la commande.
     */
    public void modifierLivraison(Modele modele, ListeCommande listeCommandes);

    /**
     * Supprimer la livraison.
     * @param modele Le modele dans lequel supprimer la livraison.
     * @param listeCommandes La liste dans lequel ajouter la suppression. 
     */
    public void supprimeLivraison(Modele modele, ListeCommande listeCommandes);

    /**
     * Permet de gerer le clic droit.
     * @param fenetre La fenetre dans lequel afficher le message.
     * @param p Le point ou l'on a cliqué.
     */
    public void clicDroit(FenetreIHM fenetre, Point p);

    /** Permet de gerer le clic gauche.
    * @param fenetre La fenetre dans lequel afficher le message.
    * @param plan Le plan sur lequel on a cliqué.
    * @param p Le point ou l'on a cliqué.
    * @param ddl La demande de livraison 
    */
	public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl);

}