package modele;

import java.util.*;
import java.util.Map.Entry;


/**
 * 
 */
public class Livraison {
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
	protected Horaire heureLivraison;

	/**
	 * 
	 */
	protected Boolean estDansFenetre;

	/**
	 * 
	 */
	protected Intersection adresse;

	protected FenetreTemporelle fenetre;

	/**
	 * 
	 */
	protected Client client;

	/**
	 * 
	 */
	protected int[] tableauPi;



/**
	 * 
	 */
	protected int[] tableauD;



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
	public Livraison(int id, Client client, Intersection adresse, FenetreTemporelle fenetre) {
		this.id = id;
		this.client = client;
		this.adresse = adresse;
		this.fenetre = fenetre;
		this.heureArrivee = null;
		this.heureLivraison = null;
		this.estDansFenetre = true;
	}
	
	

	/**
	 * Verifie l'egalite entre deux objets Livraison
	 * @param obj Livraison a comparer a la livraison appelant la methode
	 * @return Resultat du test de l'egalite
	 */
		public boolean equals(Livraison obj) {
	    	boolean resultat = false;
	//    	boolean id = (this.id == obj.id);
	//    	boolean client = (this.client == obj.client);
	    	boolean adresse = (this.adresse == obj.adresse);
	//    	boolean fenetre = (this.fenetre == obj.fenetre);
			
	    	if( adresse  ) {
				resultat = true;
			}
			return resultat;
		}

	public FenetreTemporelle getFenetre() {
		return fenetre;
	}

	public void setFenetre(FenetreTemporelle fenetre) {
		this.fenetre = fenetre;
	}

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

	public Horaire getHeureLivraison() {
		return heureLivraison;
	}

	public void setHeureLivraison(Horaire heureLivraison) {
		this.heureLivraison = heureLivraison;
	}
    
    @Override
    public String toString(){
    	String s = "id = " + this.id.toString() + " adresse = " + this.adresse.toString()+" fenetre : "+this.heureLivraison+" "+this.fenetre;
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
	
	// TODO Description de la fonction
	/**
	 * 
	 * @param correspondancePlan Map de correspondance entre les livraisons et les int associés
	 * @param livraisonDest Livraison vers laquelle on souhaite obtenir le cout de l'itineraire
	 * @return Tableau 
	 */
	public int rechercherCout(Map<Integer, Intersection> correspondancePlan, Livraison livraisonDest)
	{
		Intersection adresseDestination = livraisonDest.getAdresse();
		int idDest = getKeyByValue(correspondancePlan, adresseDestination);
		return this.tableauD[idDest];
	}
	
    /**
     * Obtenir la cle d'un element d'un map en fonction de sa valeur.
     * @param map Map dans lequel on recherche
     * @param value Valeur dont on recherche la cle associee
     * @return Cle associee a la valeur
     */
    public Integer getKeyByValue(Map<Integer, Intersection> map, Intersection value) {
    	Integer resultat = null;
    	for(Integer compteur = 0; compteur < map.size(); compteur++) {
    		if (value == map.get(compteur)) {
    			resultat =  compteur;
    		}
    	}
    	return resultat;
    }
    

    /**
     * Recherche de la liste des troncons permettant d'acceder a une autre livraison.
     * @param map Map associant chaque Intersection a une valeur numerique
     * @param livraisonDest Livraison de destination
     * @return Liste des troncons a emprunter pour arriver a destination
     */
	public List<Troncon> rechercherTroncons(Map<Integer, Intersection> map, Livraison livraisonDest)
	{
		// Recuperer l'int de destination
		Intersection arrivee = livraisonDest.getAdresse();

		Integer intDestination = getKeyByValue(map, arrivee);

		
		// Recupere l'int de l'origine
		Integer intOrigine = getKeyByValue(map, adresse);

		
		// Recherche de la liste des int des intersections
				
		List<Integer> listeEntierIntersection = new LinkedList<Integer>();
		calculPlusCourtCheminRecursif(intOrigine, intDestination, this.tableauPi, listeEntierIntersection);

		
		// Passage des int en intersections
		List<Intersection> listeIntersection = new LinkedList<Intersection>();
		for(Integer intInter : listeEntierIntersection){

			Intersection inter = map.get(intInter);

			listeIntersection.add(inter);
		}
		
		// Recherche des troncons
		List<Troncon> tronconsOrdonnes = new LinkedList<>();
		for(int i = 0 ; i<listeIntersection.size()-1;i++){
			Intersection depart = listeIntersection.get(i);
			Intersection dest = listeIntersection.get(i+1);

			Troncon tronc = depart.rechercherTroncon(dest);

			tronconsOrdonnes.add(tronc);
		}		
		return tronconsOrdonnes;
	}
	//TODO Java Doc
	private void calculPlusCourtCheminRecursif(Integer intOrigine, Integer intDestination, int[] Pi, List<Integer> listeEntierIntersection) {
		if(intOrigine == intDestination){
			listeEntierIntersection.add(intOrigine);

		}
		else if(Pi[intDestination] == -1){

			listeEntierIntersection.add(intDestination);
		}
		else{
			calculPlusCourtCheminRecursif(intOrigine, Pi[intDestination], Pi, listeEntierIntersection);
			listeEntierIntersection.add(intDestination);
		}
	}

	//TODO Java Doc
	public void calculerPlusCourtsChemins(GraphePondere graphe) {
		Map<Integer, Intersection> mapCorrespondancePlan = graphe.getMapCorrespondance();

		Collection<Intersection> setIntersection = mapCorrespondancePlan.values();
		for(Intersection unInter : setIntersection) {
			Integer resultat = getKeyByValue(mapCorrespondancePlan, unInter);

		}
		Integer numeroSommet = getKeyByValue(mapCorrespondancePlan, adresse);

		int [][]piEtD = Dijkstra.dijkstra(graphe, numeroSommet);
		tableauD = piEtD[0];

		tableauPi = piEtD[1];

	}
}