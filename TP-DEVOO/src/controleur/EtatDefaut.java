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
    public void ouvrirPlan(Modele modele) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,Modele modele, Plan plan) {
        // TODO implement here
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Modele modele, FenetreIHM fenetre) {
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
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl) {

    }


	@Override
	public void clicDroit(FenetreIHM fenetre, Point p) {
		fenetre.afficheMessage("Clic droit, c'est bien mais ça ne sert à rien :) ");
	}

	@Override
	public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimeLivraison(Modele modele, ListeCommande listeCommandes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterLivraison(Modele modele, ListeCommande ldc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierLivraison(Modele modele, ListeCommande listeCommandes) {
		// TODO Auto-generated method stub
		
	}

	

}