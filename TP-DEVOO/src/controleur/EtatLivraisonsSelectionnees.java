package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import vue.FenetreIHM;

/**
 * 
 */
public class EtatLivraisonsSelectionnees extends EtatDefaut {

	protected Livraison liv;
    /**
     * Default constructor
     */
    public EtatLivraisonsSelectionnees() {
    	this.liv = null;
    }

    public String toString(){
    	return "Etat livraison selectionnée";
    }
    
    public void setLivraison(Livraison liv){
    	this.liv = liv;
    }
    
    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    public void supprimerLivraison(ListeCommande list) {
    	System.out.println("je suis la");
    	list.ajoute(new CommandeSuprime());
        Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }
    
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	Livraison liv = ddl.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	Intersection inter = plan.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	
    	if(liv == null){
    		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    	}
    	else{
    		Controleur.setEtatCourant(Controleur.etatDeuxLivraisonSelectionnee);
    	}
    }

}