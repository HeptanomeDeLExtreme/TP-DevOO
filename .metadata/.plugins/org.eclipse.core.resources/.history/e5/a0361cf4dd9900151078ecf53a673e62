package controleur;

import java.util.*;

import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * 
 */
public class CommandeModifie implements Commande {

	protected Livraison liv1;
	protected Livraison liv2;
	protected Modele modele;
	
    /**
     * Default constructor
     */
    public CommandeModifie() {
    }

    /**
     * 
     */
    protected Tournee tournee;

    /**
     * @param tournee 
     * @param livraison1 
     * @param livraison2
     */
    public CommandeModifie(Modele modele, Livraison l1, Livraison l2) {
        this.modele = modele;
        this.liv1 = l1;
        this.liv2 = l2;
    }

    /**
     * 
     */
    public void doCommande() {
        this.modele.modifier(liv1,liv2);
    }

    /**
     * 
     */
    public void undoCommande() {
    	this.modele.modifier(liv2,liv1);
    }

}