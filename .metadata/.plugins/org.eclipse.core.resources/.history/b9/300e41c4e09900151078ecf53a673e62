package controleur;

import java.awt.Point;
import java.util.*;

import vue.FenetreIHM;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

/**
 * 
 */
public class EtatDeuxLivraisonSelectionnee extends EtatDefaut {

	protected Livraison liv1;
	protected Livraison liv2;
	
    /**
     * Default constructor
     */
    public EtatDeuxLivraisonSelectionnee() {
    }
    
    
    
    public void setLiv1(Livraison liv1) {
		this.liv1 = liv1;
	}



	public void setLiv2(Livraison liv2) {
		this.liv2 = liv2;
	}

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }

	public String toString(){
    	return "Etat deux livraisons selectionnés ";
    }

    /**
     * @param livraison1 
     * @param livraison2
     */
    public void modifierLivraison(Modele modele, ListeCommande listeCommandes) {
    	listeCommandes.ajoute(new CommandeModifie(modele,liv1,liv2));
        Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }

}