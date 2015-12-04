package modele;

import java.util.*;

/**
 * Represente un troncon reliant deux intersections (unidirectionnel).
 */
public class Troncon {

	/**
	 * Longueur du troncon
	 */
	protected Float longueur;

	/**
	 * Vitesse moyenne de parcours du troncon
	 */
	protected Float vitesseMoyenne;

	/**
	 * Nom de la rue associee au troncon
	 */
	protected String nomDeRue;

	/**
	 * Intersection d'origine du troncon.
	 */
	protected Intersection origine;

	/**
	 * Intersection de destination du troncon
	 */
	protected Intersection destination;

	/**
     * Default constructor
     */
    public Troncon() {
    }

    public Troncon(String nomRue, Float vitesse, Float longueur, Intersection origine, Intersection destination) {
		this.nomDeRue = nomRue;
		this.vitesseMoyenne = vitesse;
		this.longueur = longueur;
		this.origine = origine;
		this.destination = destination;
	}

    
    
    public Intersection getOrigine() {
		return origine;
	}

	public Intersection getDestination() {
		return destination;
	}

	public String getNomDeRue() {
		return nomDeRue;
	}

	/**
	 * 
	 * @return Cout en temps du parcours d'un troncon
	 */
	public int getCout(){
		int l = (int) (longueur*10);
		int v = (int) (vitesseMoyenne*10);
		return (l/v);
	}

	/**
     * Renvoit le cout d'un troncon en temps.
     * @return Cout du troncon en temps.
     */
	public Float getLongueur() {
		return longueur;
	}

	public void setOrigine(Intersection origine) {
		this.origine = origine;
	}



	public void setDestination(Intersection destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Troncon [longueur=" + longueur + ", vitesseMoyenne="
				+ vitesseMoyenne + ", nomDeRue=" + nomDeRue + ", origine="
				+ origine + ", destination=" + destination + "]";
	}
    
}
