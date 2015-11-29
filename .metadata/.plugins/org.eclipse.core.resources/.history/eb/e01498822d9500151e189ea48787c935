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
public class EtatIntersectionSelectionnee extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatIntersectionSelectionnee() {
    }

    public String toString(){
    	return "Etat intersection selectionne";
    }
    
    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande) {
        // TODO implement here
    }
    
    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	Livraison liv = ddl.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	Intersection inter = plan.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	
    	if(liv == null){
    		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    	}
    	else{
    		Controleur.setEtatCourant(Controleur.etatLivraisonPrecedenteSelectionnee);
    	}
    }

}