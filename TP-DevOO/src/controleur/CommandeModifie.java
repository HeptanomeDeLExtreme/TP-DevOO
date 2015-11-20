package controleur;

import java.util.*;

import modele.Livraison;
import modele.Tournee;

/**
 * 
 */
public class CommandeModifie implements Commande {

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
    public void CommandeModifie(Tournee tournee, Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * 
     */
    public void doCommande() {
        // TODO implement here
    }

    /**
     * 
     */
    public void undoCommande() {
        // TODO implement here
    }

}