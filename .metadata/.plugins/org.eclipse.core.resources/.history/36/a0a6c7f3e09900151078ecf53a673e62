package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

import vue.FenetreIHM;

/**
 * 
 */
public class EtatIntersectionSelectionnee extends EtatDefaut {

	
	protected Intersection inter;
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
        	fenetre.afficheMessage("Sélection vide");
    	}
    	else if(inter != null){
    		Controleur.setEtatCourant(Controleur.etatLivraisonPrecedenteSelectionnee);
    		Controleur.etatLivraisonPrecedenteSelectionnee.init(this.inter,liv);
    		fenetre.afficheMessage("Intersection sélectionnée : " + this.inter.getId() + " - Livraison suivante sélectionnée : " + liv.getAdresse().getId());
    	}
    	else{
    		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    		fenetre.afficheMessage("Sélection vide");
    	}
    }

	@Override
	public void ajouterLivraison(Modele modele, ListeCommande ldc) {
				
	}

	public void setIntersection(Intersection inter) {
		this.inter = inter;
	}
}