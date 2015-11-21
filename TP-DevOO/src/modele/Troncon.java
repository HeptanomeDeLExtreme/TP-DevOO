package modele;

import java.util.*;

/**
 * 
 */
public class Troncon {
	
	@Override
	public String toString() {
		return "Troncon " + nomDeRue + " : " + vitesseMoyenne;
	}

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
    
	public void setOrigine(Intersection inter) {
		this.origine = inter;
		
	}

	public void setDestination(Intersection dest) {
		this.destination = dest;
		
	}

}