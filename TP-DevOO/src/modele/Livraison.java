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
     * Constructeur de base sans client
     * @param int id
     * @param Intersection adresse
     */
    public Livraison(int id, Intersection adresse) {
    	this.id = id;
    	this.adresse = adresse;
	}

    /**
     * Constructeur de base
     * @param int id
     * @param Intersection adresse
     * @param int client
     */
	public Livraison(int id, Client client, Intersection adresse) {
		this.id = id;
		this.client = client;
		this.adresse = adresse;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public Boolean getEstDansFenetre() {
		return estDansFenetre;
	}

	public void setEstDansFenetre(Boolean estDansFenetre) {
		this.estDansFenetre = estDansFenetre;
	}

	public Intersection getAdresse() {
		return adresse;
	}

	public void setAdresse(Intersection adresse) {
		this.adresse = adresse;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}