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
		// TODO Auto-generated constructor stub
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
    
    // TODO vrai constructeur
    // problème : dans le XML, on donne l'id de la destination, qui peut ne pas encore exister lors de la lecture du XML.

	public void setOrigine(Intersection i1) {
		this.origine = i1;
		
	}

	public void setDestination(Intersection i2) {
		this.destination = i2;
		
	}

}