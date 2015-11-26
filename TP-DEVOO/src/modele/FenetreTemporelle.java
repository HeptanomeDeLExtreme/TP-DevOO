package modele;

import java.util.*;

/**
 * 
 */
public class FenetreTemporelle {

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
     * Default constructor
     */
    public FenetreTemporelle() {
    	this.heureDebut = new Horaire();
    	this.heureFin = new Horaire();
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

	public Horaire getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Horaire heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Horaire getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Horaire heureFin) {
		this.heureFin = heureFin;
	}

	public Set<Livraison> getLivraisons() {
		return livraisons;
	}

	public void setLivraisons(Set<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public boolean equals(FenetreTemporelle obj) {
		boolean resultat = false;
		if( (this.getHeureDebut() == obj.getHeureDebut()) && (this.getHeureFin() == obj.getHeureFin()) ) {
			resultat = true;
		}
		return resultat;
	}

}