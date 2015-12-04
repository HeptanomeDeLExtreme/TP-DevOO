package controleur;

import java.util.*;

import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * Cette classe permet de gerer le undo/redo de la modification de livraison.
 */
public class CommandeModifie implements Commande {

	/**
	 * La premiere livraison a intervertir.
	 */
	protected Livraison liv1;
	/**
	 * La deuxieme livraison a intervertir.
	 */
	protected Livraison liv2;
	/**
	 * Le modele a modifier.
	 */
	protected Modele modele;

	
    /**
     * Constructeur par defaut.
     */
    public CommandeModifie() {
    }

    /**
     * @param modele Le modele a modifier. 
     * @param livraison1 La premiere livraison a intervertir.
     * @param livraison2 La deuxieme livraison a intervertir.
     */
    public CommandeModifie(Modele modele, Livraison l1, Livraison l2) {
        this.modele = modele;
        this.liv1 = l1;
        this.liv2 = l2;
    }

    /**
     * Fait la commande.
     */
    public void doCommande() {
        this.modele.modifier(liv1,liv2);
    }

    /**
     * Annule la commande.
     */
    public void undoCommande() {
    	this.modele.modifier(liv2,liv1);
    }

}