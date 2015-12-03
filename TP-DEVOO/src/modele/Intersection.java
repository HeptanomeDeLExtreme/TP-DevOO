package modele;

import java.awt.Point;
import java.util.*;

/**
 * 
 */
public class Intersection {
	
	/**
     * ID de l'intersection
     */
    protected Integer id;

    /**
     * Coordonnee X de l'intersection.
     */
    protected Integer x;

    /**
     * Coordonnee Y de l'intersection
     */
    protected Integer y;

    /**
     * Ensemble des troncons permettant d'acceder a l'intersection
     */
    protected Set<Troncon> tronconsSortant;

    /**
     * Ensemble de troncons permettant de quitter l'intersection.
     */
    protected Set<Troncon> tronconsEntrant;

	/**
     * Default constructor
     */
    public Intersection() {
    }
    
    /**
     * 
     * @param id
     * @param x
     * @param y
     */
	public Intersection(Integer id, Integer x, Integer y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.tronconsEntrant = new HashSet<Troncon>();
		this.tronconsSortant = new HashSet<Troncon>();
		
	}
	
	
	/**
	 * Verifie que l'intersection est identique a une autre intersection
	 * @param i2 Intersection a comparer
	 * @return Resultat du test
	 */
	public boolean equals(Intersection i2) {
		return (this.id == i2.id);
	}

	/**
	 * Recherche un troncon entre l'intersection et une autre intersection
	 * @param arrivee Intersection d'arrivee
	 * @return Troncon reliant les deux intersection, si existant
	 */
	public Troncon rechercherTroncon(Intersection arrivee){
		Troncon toRet = null;
		for(Troncon tronc : tronconsSortant){
			if(tronc.getDestination().equals(arrivee)){
				toRet = tronc;
			}
		}
		return toRet;
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

	//TODO JavaDoc
	public boolean contient(Point p, float echelleX, float echelleY) {
		int ecartX = (int) Math.abs(p.getX()*echelleX-this.x*echelleX);
		int ecartY = (int) Math.abs(p.getY()*echelleY-this.y*echelleY);
		return (ecartX < 10 && ecartY < 10);
	}




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
    
	/**
	 * Recherche toutes les intersections accessibles depuis l'intersection, par l'emprunt d'un seul troncon.
	 * @return Map contenant toutes les intersections atteignables depuis cette intersection, et le cout associé du trajet.
	 */
		public Map<Intersection,Integer> getIntersectionsVoisines(){
			
			Map<Intersection,Integer> intersectionsVoisines=new HashMap<Intersection,Integer>();
			
			for (Troncon tronçon : tronconsSortant){
				Integer coutInteger = new Integer(tronçon.getCout());
				intersectionsVoisines.put(tronçon.getDestination(), coutInteger);
			}
			
			return intersectionsVoisines;
		}

}