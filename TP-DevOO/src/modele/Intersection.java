package modele;

import java.util.*;

/**
 * 
 */
public class Intersection {

    @Override
	public String toString() {
		return "Intersection [id=" + id +"]\n";
	}


	/**
     * Default constructor
     */
    public Intersection() {
    }
    
	public Intersection(Integer id, Integer x, Integer y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.tronconsEntrant = new HashSet<Troncon>();
		this.tronconsSortant = new HashSet<Troncon>();
		
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

	public void setTronçonsEntrant(Set<Troncon> troncons) {
		this.tronconsEntrant = troncons;
	}

	public void setTronçonsSortant(Set<Troncon> troncons) {
		this.tronconsSortant = troncons;
	}


	public void ajouteTronconEntrant(Troncon troncon) {
		tronconsEntrant.add(troncon);
	}


	public Integer getId() {
		return id;
	}


	public void ajouteTronconSortant(Troncon troncon) {
		tronconsSortant.add(troncon);
	}

}