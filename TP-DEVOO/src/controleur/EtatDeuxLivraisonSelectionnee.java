package controleur;

import java.util.*;

import modele.Livraison;

/**
 * 
 */
public class EtatDeuxLivraisonSelectionnee extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatDeuxLivraisonSelectionnee() {
    }
    
    public String toString(){
    	return "Etat deux livraisons selectionnés ";
    }

    /**
     * @param livraison1 
     * @param livraison2
     */
    public void modifierLivraison(Livraison livraison1, Livraison livraison2) {
        Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }

}