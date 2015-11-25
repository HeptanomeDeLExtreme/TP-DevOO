package modele;

import java.util.*;
import java.util.Map.Entry;


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
    protected Horaire heureArrivee;

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
    protected Client client;
    
    /**
     * 
     */
    protected int[] tableauPi;
    
    public int[] getTableauPi() {
		return tableauPi;
	}

	public void setTableauPi(int[] tableauPi) {
		this.tableauPi = tableauPi;
	}

	public int[] getTableauD() {
		return tableauD;
	}

	public void setTableauD(int[] tableauD) {
		this.tableauD = tableauD;
	}

	/**
     * 
     */
    protected int[] tableauD;
    
    @Override
    public String toString(){
    	String s = "id = " + this.id.toString() + " adresse = " + this.adresse.toString();
    	return s;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Horaire getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Horaire heureArrivee) {
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
	
	/**
	 * 
	 * @param correspondancePlan 
	 * @param livraisonDest
	 * @return
	 */
	public int rechercherCout(Map<Intersection, Integer> correspondancePlan, Livraison livraisonDest)
	{
		Intersection adresseDestination = livraisonDest.getAdresse();
		int idDest = correspondancePlan.get(adresseDestination);
		return this.tableauD[idDest];
	}
	
    /**
     * 
     * @param map
     * @param value
     * @return
     */
    public Intersection getKeyByValue(Map<Intersection, Integer> map, Integer value) {
    	Intersection resultat = null;
    	Set<Intersection> setIntersection = map.keySet();
    	for(Intersection uneInter : setIntersection) {
    		if (value == map.get(uneInter)) {
    			resultat =  uneInter;
    		}
    	}
    	return resultat;
    }
    
	public List<Troncon> rechercherTroncons(Map<Intersection, Integer> correspondancePlan, Livraison livraisonDest)
	{
		List<Troncon> tronconsOrdonnes = new LinkedList<>(); 
		
		Intersection arrivee = livraisonDest.getAdresse();
		Integer numeroSommet = correspondancePlan.get(arrivee);
		Integer numeroSommetLivDepart = correspondancePlan.get(adresse);
		
		Integer numeroSommetSuivant = tableauPi[numeroSommet];
		Intersection interNumeroSommetSuivant = 
				getKeyByValue(correspondancePlan, numeroSommetSuivant);

		// TODO OPHELIE
		while(numeroSommetSuivant != numeroSommetLivDepart){
			// Rechercher troncon avec numeroSommet et numeroSommetSuivant
			Troncon unTroncon = arrivee.rechercherTroncon(interNumeroSommetSuivant);
			System.out.println("KFS : "+adresse+" "+arrivee+" "+unTroncon);
			// Ajouter le troncon à la liste
			tronconsOrdonnes.add(unTroncon);
			// Dire que numeroSommet = numeroSommetSuivant
			numeroSommet = numeroSommetSuivant;
			// Changement de l'intersection suivante
			arrivee = interNumeroSommetSuivant;
			// Récupérer numeroSommetSuivant avec tableauPi[numeroSommet]
			numeroSommetSuivant = tableauPi[numeroSommet];
		}
		// Insertion du dernier troncon
		Troncon unTroncon = arrivee.rechercherTroncon(adresse);
		System.out.println("Dernier tronocn :"+unTroncon);
		tronconsOrdonnes.add(unTroncon);
		return tronconsOrdonnes;
	}

	public void calculerPlusCourtsChemins(GraphePondere graphe) {
		Map<Intersection, Integer> mapCorrespondancePlan = graphe.getMapCorrespondance();
		// TEST
		System.out.println("Livraison actuelle : " + this.getId());
		System.out.println("Intersection actuelle : " + this.getAdresse());
		Set<Intersection> setKey = mapCorrespondancePlan.keySet();
		for(Intersection unInter : setKey) {
			Integer resultat = mapCorrespondancePlan.get(unInter);
			System.out.println("Clé : " + unInter + ", Valeur : " + resultat);
		}
		Integer numeroSommet = mapCorrespondancePlan.get(this.adresse);
		System.out.println(numeroSommet);
		int [][]piEtD = Dijkstra.dijkstra(graphe, numeroSommet);
		tableauD = piEtD[0];
		for(int i = 0; i < tableauD.length; i++) {
			int resultat = tableauD[i];
			System.out.println("resultat D : "+ resultat);
		}
		tableauPi = piEtD[1];
		for(int i = 0; i < tableauPi.length; i++) {
			int resultat = tableauPi[i];
			System.out.println("resultat PI : "+ resultat);
		}
		// TEST
	}
}