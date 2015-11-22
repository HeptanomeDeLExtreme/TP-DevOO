package modele;

import java.awt.Point;
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
    
	public Intersection(Integer id, Integer x, Integer y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.tronconsEntrant = new HashSet<Troncon>();
		this.tronconsSortant = new HashSet<Troncon>();
		
	}
   
    public Set<Troncon> getTronconsSortant() {
		return tronconsSortant;
	}



	public void setTronconsSortant(Set<Troncon> tronconsSortant) {
		this.tronconsSortant = tronconsSortant;
	}



	public Set<Troncon> getTronconsEntrant() {
		return tronconsEntrant;
	}

	public void ajouteTronconEntrant(Troncon troncon) {
		tronconsEntrant.add(troncon);
	}
	
	public void ajouteTronconSortant(Troncon troncon) {
		tronconsSortant.add(troncon);
	}

	public void setTronconsEntrant(Set<Troncon> tronconsEntrant) {
		this.tronconsEntrant = tronconsEntrant;
	}



	public Integer getId() {
		return id;
	}


	public Integer getX() {
		return x;
	}


	public Integer getY() {
		return y;
	}

	public boolean contient(Point p, float echelleX, float echelleY) {
		int ecartX = (int) Math.abs(p.getX()*echelleX-this.x*echelleX);
		int ecartY = (int) Math.abs(p.getY()*echelleY-this.y*echelleY);
		return (ecartX < 10 && ecartY < 10);
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

	@Override
	public String toString() {
		return "Intersection [id=" + id + ", x=" + x + ", y=" + y + "]";
	}
    
    

}