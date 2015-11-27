package modele;

import java.util.*;

/**
 * 
 */
public class Troncon {

    /**
     * Default constructor
     */
    public Troncon() {
    }

    public Troncon(String nomRue, Float vitesse, Float longueur, Intersection origine, Intersection destination) {
		this.nomDeRue = nomRue;
		this.vitesseMoyenne = vitesse;
		this.longueur = longueur;
		this.origine = origine;
		this.destination = destination;
	}

	/**
     * 
     */
    protected Float longueur;

    /**
     * 
     */
    protected Float vitesseMoyenne;

    /**
     * 
     */
    protected String nomDeRue;

    /**
     * 
     */
    protected Intersection origine;

    /**
     * 
     */
    protected Intersection destination;
    
	@Override
	public String toString() {
		return "Troncon [longueur=" + longueur + ", vitesseMoyenne="
				+ vitesseMoyenne + ", nomDeRue=" + nomDeRue + ", origine="
				+ origine + ", destination=" + destination + "]";
	}

	public Float getLongueur() {
		return longueur;
	}

	public void setLongueur(Float longueur) {
		this.longueur = longueur;
	}

	public Float getVitesseMoyenne() {
		return vitesseMoyenne;
	}

	public void setVitesseMoyenne(Float vitesseMoyenne) {
		this.vitesseMoyenne = vitesseMoyenne;
	}

	public void setNomDeRue(String nomDeRue) {
		this.nomDeRue = nomDeRue;
	}
	 
    public Intersection getOrigine() {
		return origine;
	}

	public Intersection getDestination() {
		return destination;
	}

	public void setOrigine(Intersection origine) {
		this.origine = origine;
	}

	public void setDestination(Intersection destination) {
		this.destination = destination;
	}

	public String getNomDeRue() {
		return nomDeRue;
	}


}