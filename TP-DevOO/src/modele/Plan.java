package modele;

import java.util.*;

/**
 * 
 */
public class Plan {

    /**
     * Default constructor
     */
    public Plan() {
    }

    /**
     * 
     */
    protected Set<Intersection> intersections;

    /**
     * 
     */
    protected Plan singleton;

    /**
     * @param fichier
     */
    public void Plan(String fichier) {
        // TODO implement here
    }
    
    /**
     * Ajoute une intersection au plan
     * @param intersection Intersection à ajouter
     */
    public void ajoute(Intersection intersection) {
    	intersections.add(intersection);
    }

	public Intersection getIntersection(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
    

}