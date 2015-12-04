package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

import vue.FenetreIHM;

/**
 * Represente le comportement dans l'etat livraison selectionnee.
 */
public class EtatLivraisonsSelectionnees extends EtatDefaut {

    /*
     * La livraison que l'on a selectionnée.
     */
    protected Livraison liv;

    public EtatLivraisonsSelectionnees() {
	this.liv = null;
    }

    public String toString() {
	return "Etat livraison selectionnée";
    }

    public void setLivraison(Livraison liv) {
	this.liv = liv;
    }

    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande) {
	// TODO implement here
    }

    public void supprimeLivraison(Modele modele, ListeCommande list) {
	list.ajoute(new CommandeSuprime(modele, liv));
	Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p,
	    DemandeDeLivraison ddl) {
	Livraison liv2 = ddl.cherche(p, fenetre.getEchelleX(),
		fenetre.getEchelleY());

	if (liv2 != null) {
	    Controleur.etatDeuxLivraisonSelectionnee.setLiv1(liv);
	    Controleur.etatDeuxLivraisonSelectionnee.setLiv2(liv2);
	    Controleur.setEtatCourant(Controleur.etatDeuxLivraisonSelectionnee);
	    fenetre.afficheMessage("Livraisons sélectionnées : "
		    + liv.getAdresse().getId() + " et "
		    + liv2.getAdresse().getId());
	} else {
	    Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	    fenetre.afficheMessage("Sélection vide");
	}
    }

}