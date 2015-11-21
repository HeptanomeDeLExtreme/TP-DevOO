package modele;

import java.util.*;

/**
 * 
 */
public class FenetreTemporelle {

    /**
     * Default constructor
     */
    public FenetreTemporelle() {
    }

    /**
     * 
     */
    protected Date heureDebut;

    /**
     * 
     */
    protected Date heureFin;

    /**
     * 
     */
    protected Set<Livraison> livraisons;

    /**
     * 
     */
    public Date getHeureDebut() {
		return heureDebut;
	}
    
    /**
     * 
     */
	public Date getHeureFin() {
		return heureFin;
	}
	
	/**
     * 
     */
	public Set<Livraison> getLivraisons() {
		return livraisons;
	}

	/**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraison) {
        // TODO implement here
    }

}