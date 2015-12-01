package controleur;

import java.util.*;

import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * 
 */
public class CommandeSuprime implements Commande {


    /**
     * 
     */
    protected Modele modele;
    protected Livraison liv;
    protected Livraison livraisonPrecedente;

    /**
     * @param tournee 
     * @param livraison
     */
    public CommandeSuprime(Modele modele, Livraison livraison) {
        this.modele = modele;
        this.liv = livraison;
    	List<Livraison> listeLivraison = modele.getTournee().getLivraisonsEnOrdre();
    	int index = listeLivraison.indexOf(this.liv);
    	this.livraisonPrecedente = listeLivraison.get(index-1);
    	
//    	System.out.println("ASUP : "+this.liv);
//    	System.out.println("PREC : "+this.livraisonPrecedente);
//    	System.out.println("INTER : "+this.liv.getAdresse());
    }

    /**
     * 
     */
    public void doCommande() {
    	System.out.println("TO SUP : "+this.liv);
        this.modele.supprimeLivraison(this.liv);
    }

    /**
     * 
     */
    public void undoCommande() {
    	Intersection inter = liv.getAdresse();
        this.modele.ajouteLivraison(livraisonPrecedente, inter);
    }

}