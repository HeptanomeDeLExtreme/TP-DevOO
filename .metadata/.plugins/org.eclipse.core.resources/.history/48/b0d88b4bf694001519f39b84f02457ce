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
    protected Livraison liv;

    /**
     * @param tournee 
     * @param livraison
     */
    public void CommandeSuprime(Tournee tournee, Livraison livraison) {
        this.tournee = tournee;
        this.liv = livraison;
    }

    /**
     * 
     */
    public void doCommande() {
        this.tournee.supprimeLivraison(this.liv);
        System.out.println("je lé fai");
    }

    /**
     * 
     */
    public void undoCommande() {
        // TODO implement here
    }

}