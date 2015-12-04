package controleur;

import java.awt.Point;
import java.util.*;

import javax.swing.JSpinner.ListEditor;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

import vue.FenetreIHM;

/**
 * Represente le comportement par defaut des methodes.
 */
public class EtatDefaut implements Etat {

    /**
     * Constructeur par defaut.
     */
    public EtatDefaut() {
    }

    public void ouvrirPlan(Modele modele) {
	// TODO implement here
    }

    public void importerLivraison(FenetreIHM fenetre, Modele modele, Plan plan) {
	// TODO implement here
    }

    public void calculerTournee(Modele modele, FenetreIHM fenetre) {
	// TODO implement here
    }

    public void undo(ListeCommande listeCommande) {
	// TODO implement here
    }

    public void redo(ListeCommande listeCommande) {
	// TODO implement here
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p,
	    DemandeDeLivraison ddl) {

    }

    public void clicDroit(FenetreIHM fenetre, Point p) {
	fenetre.afficheMessage("Clic droit, c'est bien mais ça ne sert à rien :) ");
    }

    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
	// TODO Auto-generated method stub

    }

    public void supprimeLivraison(Modele modele, ListeCommande listeCommandes) {
	// TODO Auto-generated method stub

    }

    public void ajouterLivraison(Modele modele, ListeCommande ldc) {
	// TODO Auto-generated method stub

    }

    public void modifierLivraison(Modele modele, ListeCommande listeCommandes) {
	// TODO Auto-generated method stub

    }

}