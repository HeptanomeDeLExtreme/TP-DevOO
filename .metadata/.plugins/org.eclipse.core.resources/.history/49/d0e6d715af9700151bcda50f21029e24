package controleur;

import java.util.*;

import modele.Intersection;
import modele.Livraison;
import modele.Tournee;

/**
 * 
 */
public class CommandeAjout implements Commande {

	protected Tournee tournee;
	protected Livraison liv;
	protected Intersection inter;
    /**
     * Default constructor
     * @param inter 
     * @param liv 
     * @param tournee2 
     */
    public CommandeAjout(Tournee tournee, Livraison liv, Intersection inter) {
    	this.tournee = tournee;
    	this.liv = liv;
    	this.inter = inter;
    }

 
    /**
     * 
     */
    public void doCommande() {
        this.tournee.ajouteLivraison(this.liv, this.inter);
    }

    /**
     * 
     */
    public void undoCommande() {
        // TODO implement here
    }

}