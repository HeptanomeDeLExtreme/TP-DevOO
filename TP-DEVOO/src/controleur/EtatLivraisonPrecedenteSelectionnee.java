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
public class EtatLivraisonPrecedenteSelectionnee extends EtatDefaut {

	
	protected Intersection inter;
	protected Livraison liv;
	
    /**
     * Default constructor
     */
    public EtatLivraisonPrecedenteSelectionnee() {
    }
    
    public String toString(){
    	return "Etat livraison precedente selectionne";
    }

	@Override
	public void ajouterLivraison(Modele modele, ListeCommande ldc) {
		ldc.ajoute(new CommandeAjout(modele,this.liv,this.inter));	
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}

	public void init(Intersection inter, Livraison liv) {
		this.inter = inter;
		this.liv = liv;
	}

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
        Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }
    
}