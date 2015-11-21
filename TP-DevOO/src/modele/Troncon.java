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

	public void setOrigine(Intersection i1) {
		this.origine = i1;
		
	}

	public void setDestination(Intersection i2) {
		this.destination = i2;
		
	}

}