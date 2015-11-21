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

    public Plan(Set<Intersection> listeInter) {
		this.intersections = listeInter;
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
     * Permet de récuperer une intersection avec son id
     * @param int id
     * @return Intersection noeud
     */
    public Intersection recupererIntersectionParId(int id){
    	Iterator<Intersection> iterateur = intersections.iterator();
    	Intersection noeud = iterateur.next();
    	
    	while (iterateur.hasNext() && noeud.id != id) {
    		noeud = iterateur.next();
    	}
    	
    	if (noeud.id == id){
    		return noeud;
    	}
    	
    	else
    	{
    		return null;
    	}
    		
    }

}