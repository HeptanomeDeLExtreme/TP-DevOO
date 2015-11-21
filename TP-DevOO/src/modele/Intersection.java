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
    public Intersection(int id, int x, int y) {
    	this.id = id;
    	this.x = x;
    	this.y = y;
    }
   
    public Set<Troncon> getTronçonsSortant() {
		return tronçonsSortant;
	}



	public void setTronçonsSortant(Set<Troncon> tronçonsSortant) {
		this.tronçonsSortant = tronçonsSortant;
	}



	public Set<Troncon> getTronçonsEntrant() {
		return tronçonsEntrant;
	}



	public void setTronçonsEntrant(Set<Troncon> tronçonsEntrant) {
		this.tronçonsEntrant = tronçonsEntrant;
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

	public boolean contient(Point p, int echelleX, int echelleY) {
		int ecartX = (int) Math.abs(p.getX()*echelleX-this.x*echelleX);
		int ecartY = (int) Math.abs(p.getY()*echelleY-this.y*echelleY);
		System.out.println((int)p.getX()+" "+this.x+" "+ecartX);
		System.out.println((int)p.getY()+" "+this.y+" "+ecartY);
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
    protected Set<Troncon> tronçonsSortant;

    /**
     * 
     */
    protected Set<Troncon> tronçonsEntrant;


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