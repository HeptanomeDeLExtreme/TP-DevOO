package controleur;

import java.util.*;

import modele.Livraison;
import modele.Tournee;

/**
 * 
 */
public class CommandeSuprime implements Commande {


    /**
     * 
     */
    protected Tournee tournee;
    protected Livraison liv;

    /**
     * @param tournee 
     * @param livraison
     */
    public CommandeSuprime(Tournee tournee, Livraison livraison) {
        this.tournee = tournee;
        this.liv = livraison;
    }

    /**
     * 
     */
    public void doCommande() {
        this.tournee.supprimeLivraison(this.liv);
    }

    /**
     * 
     */
    public void undoCommande() {
        // TODO implement here
    }

}