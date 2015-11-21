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

    public Intersection(int i, int j, int k) {
		this.id=i;
		this.x=j;
		this.y=k;
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
    protected Set<Troncon> tronçonsSortant;

    /**
     * 
     */
    protected Set<Troncon> tronçonsEntrant;


    /**
     * @param id 
     * @param x 
     * @param y 
     * @param tronconsSortant
     */
    public void Intersection(Integer id, Integer x, Integer y, List<Troncon> tronconsSortant) {
        this.id = id;
        this.x=x;
        this.y=y;
        this.tronçonsSortant = tronçonsSortant;
    }

	public void setTronçonsEntrant(Set<Troncon> tronconE1) {
		this.tronçonsEntrant = tronconE1;
	}

	public void setTronçonsSortant(Set<Troncon> tronconS1) {
		this.tronçonsSortant = tronconS1;
		
	}

}