package modele;

import java.util.*;

/**
 * 
 */
public class FenetreTemporelle {

	/**
	 * Heure de debut de la Fenetre
	 */
    protected Horaire heureDebut;

    /**
     * Heure de fin de la fenetre.
     */
    protected Horaire heureFin;

    /**
     * Livraisons a effectuer dans la fenetre temporelle.
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
    
    
    /**Supprime une livraison de la fenetre
     * @param livraison Livraison a supprimer
     */
    public void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    	this.livraisons.remove(livraison);
    }

    /**Ajoute une livraison dans la fenetre
     * @param livraison Livraison a ajouter
     */
    public void ajouteLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }
    
    @Override
    public String toString(){
    	String s = " heureDeb = "+this.heureDebut.toString()+" heureFin = "+this.heureFin.toString();
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