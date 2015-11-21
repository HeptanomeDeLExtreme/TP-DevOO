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
    protected Set<Troncon> tronconsSortant;

    /**
     * 
     */
    protected Set<Troncon> tronconsEntrant;


    /**
     * @param id 
     * @param x 
     * @param y 
     * @param tronconsSortant
     */
    public void Intersection(Integer id, Integer x, Integer y, Set<Troncon> tronconsSortant) {
        this.id = id;
        this.x=x;
        this.y=y;
        this.tronconsSortant = tronconsSortant;
    }

	public void setTronçonsEntrant(Set<Troncon> tronconE1) {
		this.tronconsEntrant = tronconE1;
	}

	public void setTronçonsSortant(Set<Troncon> tronconS1) {
		this.tronconsSortant = tronconS1;
	}
	
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