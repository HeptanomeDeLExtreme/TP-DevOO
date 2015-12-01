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
    protected Livraison livraisonSuivante;
//    protected Livraison livraisonSauvee;
    protected Intersection intersectionSauvee;
    

    /**
     * @param tournee 
     * @param livraison
     */
    public CommandeSuprime(Modele modele, Livraison livraison) {
        this.modele = modele;
        this.liv = livraison;
    	List<Livraison> listeLivraison = modele.getTournee().getLivraisonsEnOrdre();
    	int index = listeLivraison.indexOf(this.liv);
    	this.livraisonSuivante = listeLivraison.get(index+1);
    	
    	this.intersectionSauvee = livraison.getAdresse();
    	
//    	System.out.println("ASUP : "+this.liv);
//    	System.out.println("PREC : "+this.livraisonSuivante);
//    	System.out.println("INTER : "+this.liv.getAdresse());
    }

    /**
     * 
     */
    public void doCommande() {

//    	this.livraisonSauvee = this.liv.nouvelleCopie();
        this.modele.supprimeLivraison(this.liv);
                
        System.out.println("Je supprime : " + this.liv);
    }

    /**
     * 
     */
    public void undoCommande() {
//    	Intersection inter = liv.getAdresse();
    	this.modele.ajouteLivraison(this.livraisonSuivante, this.intersectionSauvee);
//      this.modele.ajouteLivraisonSpecifique(livraisonSauvee,livraisonSuivante);
    }

}