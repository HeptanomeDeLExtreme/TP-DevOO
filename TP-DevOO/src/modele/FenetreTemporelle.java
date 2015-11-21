package modele;

import java.util.*;

/**
 * 
 */
public class FenetreTemporelle {

    /**
     * Default constructor
     */
    public FenetreTemporelle() {
    }

    /**
     * Constructeur de base de fentre temporelle
     */
    public FenetreTemporelle(GregorianCalendar dateDeb,
			GregorianCalendar dateFin) {
		this.heureDebut = dateDeb;
		this.heureFin = dateFin;
	}

	/**
     * 
     */
    protected GregorianCalendar heureDebut;

    /**
     * 
     */
    protected GregorianCalendar heureFin;

    /**
     * 
     */
    protected Set<Livraison> livraisons;

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    public void ajouteLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }

}