package modele;

import java.util.*;

/**
 * Rpresente une serie de troncons a emprunter pour aller d'une livraison a une
 * autre
 */
public class Itineraire {

    /**
     * Cout total de l'itineraire
     */
    protected Integer cout;

    /**
     * Liste des troncons constituant l'itineraire
     */
    protected List<Troncon> troncons;

    /**
     * Livraison de depart de l'itineraire
     */
    protected Livraison depart;

    /**
     * Livraison d'arrivee de l'itineraire
     */
    protected Livraison arrivee;

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
    public Itineraire(int cout, List<Troncon> troncons, Livraison depart,
	    Livraison arrivee) {
	super();
	this.cout = cout;
	this.troncons = troncons;
	this.depart = depart;
	this.arrivee = arrivee;
    }

    public Integer getCout() {
	return cout;
    }

    public void setCout(Integer cout) {
	this.cout = cout;
    }

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

    public List<Troncon> getTroncons() {
	return troncons;
    }

    public void setTroncons(List<Troncon> troncons) {
	this.troncons = troncons;
    }

    @Override
    public String toString() {
	return "Itineraire [cout=" + cout + ", troncons=" + troncons
		+ ", depart=" + depart + ", arrivee=" + arrivee + "]";
    }

}