package controleur;

import java.util.*;

import modele.Livraison;
import modele.Tournee;

/**
 * 
 */
public class CommandeSuprime implements Commande {

    /**
     * Default constructor
     */
    public CommandeSuprime() {
    }

    /**
     * 
     */
    protected Tournee tournee;

    /**
     * @param tournee 
     * @param livraison
     */
    public void CommandeSuprime(Tournee tournee, Livraison livraison) {
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