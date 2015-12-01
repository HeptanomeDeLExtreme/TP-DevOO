package controleur;

import java.util.*;

import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * 
 */
public class CommandeAjout implements Commande {

	protected Modele modele;
	protected Livraison liv;
	protected Intersection inter;
    /**
     * Default constructor
     * @param inter 
     * @param liv 
     * @param tournee2 
     */
    public CommandeAjout(Modele modele, Livraison liv, Intersection inter) {
    	this.modele = modele;
    	this.liv = liv;
    	this.inter = inter;
    }

 
    /**
     * 
     */
    public void doCommande() {
        this.modele.ajouteLivraison(this.liv, this.inter);
    }

    /**
     * 
     */
    public void undoCommande() {
    	List<Livraison> listeLivraison = modele.getTournee().getLivraisonsEnOrdre();
    	int index = listeLivraison.indexOf(this.liv);
    	Livraison aSupprime = listeLivraison.get(index-1);
        this.modele.supprimeLivraison(aSupprime);
    }

}