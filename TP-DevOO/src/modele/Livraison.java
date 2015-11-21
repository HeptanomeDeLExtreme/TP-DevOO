package modele;

import java.util.*;

/**
 * 
 */
public class Livraison {

    /**
     * Default constructor
     */
    public Livraison() {
    }

    /**
     * 
     */
    protected Integer id;

    /**
     * 
     */
    protected Date heureArrivee;

    /**
     * 
     */
    protected Boolean estDansFenetre;

    /**
     * 
     */
    protected Intersection adresse;

    /**
     * 
     */
    public Client client;

    /**
     * 
     */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param livraisonDest
	 * @return
	 */
	public int rechercherCout(Livraison livraisonDest)
	{
		return 1;
	}

	public void calculerPlusCourtsChemins(Plan plan, Set<Livraison> livraisons) {
		// TODO Auto-generated method stub
		
	}
	
}