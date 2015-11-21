package modele;

import java.awt.Point;
import java.util.*;

/**
 * 
 */
public class Plan {

    /**
     * Default constructor
     */
    public Plan(Set<Intersection> listeInter) {
    	this.intersections = listeInter;
    }
    
    public Set<Intersection> getIntersections(){
    	return this.intersections;
    }

    public int getLargeur(){
    	int max = 0;
    	for(Intersection inter : this.intersections){
    		if(inter.getX() > max){
    			max = inter.getX();
    		}
    	}
    	return max;
    }
    
    public int getHauteur(){
    	int max = 0;
    	for(Intersection inter : this.intersections){
    		if(inter.getY() > max){
    			max = inter.getY();
    		}
    	}
    	return max;
    }
    
    public Intersection cherche(Point p){
    	Iterator<Intersection> it = intersections.iterator();
		while (it.hasNext()){
			Intersection inter = it.next();
			if (inter.contient(p)) return inter;
		}
		return null;
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

}