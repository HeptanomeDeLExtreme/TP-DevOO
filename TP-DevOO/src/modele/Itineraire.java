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
    public Itineraire(int cout, List<Troncon> tronçons, Livraison depart, Livraison arrivee) {
		super();
		this.cout = cout;
		this.tronçons = tronçons;
		this.depart = depart;
		this.arrivee = arrivee;
	}


	/**
     * 
     */
    protected int cout;

    /**
     * 
     */
    protected List<Troncon> tronçons;

    /**
     * 
     */
    protected Livraison depart;

    /**
     * 
     */
    protected Livraison arrivee;

}