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

}