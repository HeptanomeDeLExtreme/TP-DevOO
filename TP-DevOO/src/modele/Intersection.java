package modele;

import java.util.*;

/**
 * 
 */
public class Intersection {

    /**
     * Default constructor
     */
    public Intersection() {
    }

    /**
     * 
     */
    protected Integer id;

    /**
     * 
     */
    protected Integer x;

    /**
     * 
     */
    protected Integer y;

    /**
     * 
     */
    protected Set<Troncon> tronconsSortant;

    /**
     * 
     */
    protected Set<Troncon> tronconsEntrant;


    /**
     * @param id 
     * @param x 
     * @param y 
     * @param tronconSortant
     */
    public Intersection(Integer id, Integer x, Integer y, List<Troncon> tronconSortant) {
        // TODO implement here
    }
    
    public Integer getId() {
    	return id;
    }

}