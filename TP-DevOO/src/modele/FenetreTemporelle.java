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
    	this.livraisons = new HashSet<Livraison>();
    }

    /**
     * Constructeur de base de fenêtre temporelle
     */
    public FenetreTemporelle(Horaire dateDeb,
			Horaire dateFin) {
    	this.livraisons = new HashSet<Livraison>();
		this.heureDebut = dateDeb;
		this.heureFin = dateFin;
	}

	/**
     * 
     */
    protected Horaire heureDebut;

    /**
     * 
     */
    protected Horaire heureFin;

    /**
     * 
     */
    protected Set<Livraison> livraisons;

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    public void ajouteLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }
    
    @Override
    public String toString(){
    	String s = " heureDeb = "+this.heureDebut.toString()+" heureFin = "+this.heureFin.toString();
    	for (Livraison l : livraisons){
    		s+=l.toString();
    	}
    	return s;
    }

}