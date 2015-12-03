package controleur;

import java.util.*;

import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * Cette classe permet de gerer le undo/redo de l'ajout de livraison.
 */
public class CommandeAjout implements Commande {

	/**
	 * Represente le modele.
	 */
	protected Modele modele;
	/**
	 * Represente la livraison avant laquelle ajouter.
	 */
	protected Livraison liv;
	/**
	 * Represente l'intersection surlaquelle ajouter.
	 */
	protected Intersection inter;
	/**
	 * Represente la livraison qui a ete cree;
	 */
	protected Livraison livraisonCree;
    /**
     * Default constructor
     * @param inter L'intersection sur laquelle ajouter.
     * @param liv La livraison avant laquelle ajouter.
     * @param modele Le modele a modifier. 
     */
    public CommandeAjout(Modele modele, Livraison liv, Intersection inter) {
    	this.modele = modele;
    	this.liv = liv;
    	this.inter = inter;
    }

 
    /**
     * Fait la commande.
     */
    public void doCommande() {
        this.livraisonCree = this.modele.getTournee().ajouteLivraison(this.liv, this.inter);
        this.modele.changementEffectue();
    }

    /**
     * Annule la commande.
     */
    public void undoCommande() {
        this.modele.supprimeLivraison(this.livraisonCree);
    }

}