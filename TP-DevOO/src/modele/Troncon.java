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
    
    // TODO vrai constructeur
    // problème : dans le XML, on donne l'id de la destination, qui peut ne pas encore exister lors de la lecture du XML.
    
    public void setOrigine(Intersection intersection) {
    	origine = intersection;
    }
    
    public void setDestination(Intersection intersection) {
    	destination = intersection;
    }

}