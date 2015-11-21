package modele;

import java.util.*;

/**
 * 
 */
public class Intersection {

    @Override
	public String toString() {
		return "Intersection [id=" + id + ", x=" + x + ", y=" + y
				+ ", tronconsSortant=" + tronconsSortant + ", tronconsEntrant="
				+ tronconsEntrant + "]\n";
	}


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
     */
    public Intersection(Integer id, Integer x, Integer y) {
        // TODO implement here
    }


	public void ajouteTronconEntrant(Troncon troncon) {
		// TODO Auto-generated method stub
		
	}


	public void ajouteTronconSortant(Troncon troncon) {
		// TODO Auto-generated method stub
		
	}

}