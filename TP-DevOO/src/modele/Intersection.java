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
    public void Intersection(Integer id, Integer x, Integer y, List<Troncon> tronconSortant) {
        // TODO implement here
    }


/**
 * 
 * @return Map contenant toutes les intersections atteignables depuis cette intersection, et le cout associé du trajet.
 */
	public Map<Intersection,Integer> getIntersectionsVoisines(){
		
		Map<Intersection,Integer> intersectionsVoisines=new HashMap<Intersection,Integer>();
		
		for (Troncon tronçon : tronçonsSortant){
			Integer coutInteger = new Integer(tronçon.getCout());
			intersectionsVoisines.put(tronçon.getDestination(), coutInteger);
		}
		
		return intersectionsVoisines;
	}

}