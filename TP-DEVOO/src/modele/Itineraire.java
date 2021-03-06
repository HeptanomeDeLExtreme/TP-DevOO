package modele;

import java.util.*;

/**
 * 
 */
public class Itineraire {

    /**
     * Default constructor
     */
    public Itineraire() {
    }

    /**
     * 
     * @param cout
     * @param tronçons
     * @param depart
     * @param arrivee
     */
    public Itineraire(int cout, List<Troncon> troncons, Livraison depart, Livraison arrivee) {
		super();
		this.cout = cout;
		this.troncons = troncons;
		this.depart = depart;
		this.arrivee = arrivee;
	}

    
    
    @Override
	public String toString() {
		return "Itineraire [cout=" + cout + ", troncons=" + troncons
				+ ", depart=" + depart + ", arrivee=" + arrivee + "]";
	}



	/**
     * 
     */
    protected Integer cout;

    public Integer getCout() {
		return cout;
	}

	public void setCout(Integer cout) {
		this.cout = cout;
	}



	/**
     * 
     */
    protected List<Troncon> troncons;

    /**
     * 
     */
    protected Livraison depart;

    
    
    public Livraison getDepart() {
		return depart;
	}

	public void setDepart(Livraison depart) {
		this.depart = depart;
	}

	public Livraison getArrivee() {
		return arrivee;
	}

	public void setArrivee(Livraison arrivee) {
		this.arrivee = arrivee;
	}

	/**
     * 
     */
    protected Livraison arrivee;

	public List<Troncon> getTroncons() {
		return troncons;
	}

	public void setTroncons(List<Troncon> troncons) {
		this.troncons = troncons;
	}
    
    

}