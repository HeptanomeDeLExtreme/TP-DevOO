package modele;

import java.awt.Point;
import java.util.*;

/**
 * Represente une intersection a laquelle peut etre affectee une livraison
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
     * Ensemble de troncons permettant de quitter l'intersection.
     */
	protected Set<Troncon> tronconsEntrant;


	/**
     * Ensemble des troncons permettant d'acceder a l'intersection
     */
	protected Set<Troncon> tronconsSortant;


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

	public Integer getId() {
		return id;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setTronconsEntrant(Set<Troncon> tronconsEntrant) {
		this.tronconsEntrant = tronconsEntrant;
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

	@Override
	public String toString() {
		return "Intersection [id=" + id + ", x=" + x + ", y=" + y + "]";
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
   
    public void ajouteTronconEntrant(Troncon troncon) {
		tronconsEntrant.add(troncon);
	}
	
	public void ajouteTronconSortant(Troncon troncon) {
		tronconsSortant.add(troncon);
	}

	/**
	 * Verifie par rapport a un clic utilisateur si il y a une intersection sur ce point
	 * @param p Point clique par l'utilisateur
	 * @param echelleX Echelle de la fenetre sur l'axe X
	 * @param echelleY Echelle de la fenetre sur l'axe Y
	 * @return
	 */
	public boolean contient(Point p, float echelleX, float echelleY) {
		int ecartX = (int) Math.abs(p.getX()*echelleX-this.x*echelleX);
		int ecartY = (int) Math.abs(p.getY()*echelleY-this.y*echelleY);
		return (ecartX < 10 && ecartY < 10);
	}



}