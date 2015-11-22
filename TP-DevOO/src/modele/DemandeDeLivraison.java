// TODO : Rajouter l'entrepot dans toutes les méthodes ! Pour l'instant,
// l'entrepot n'est pas pris en compte dans le calcul des plus courts chemins,
// la map de correspondance, la création d'itinéraires...etc...

package modele;

import java.util.*;
import java.util.Map.Entry;

import tsp.TSP1;

/**
 * 
 */
public class DemandeDeLivraison {
	
	/**
     * La tournee qui correspond a la demande de livraisons. Vide lors de la 
     * creation de l'objet DemandeDeLivraison, elle est calculee par la methode
     * calculTournee.
     * 
     * @see calculerTournee
     */
    protected Tournee tournee;

    /**
     * Les fenetres listees dans le fichier XML de demande de livraisons.
     */
    protected List<FenetreTemporelle> fenetres;

    /**
     * La livraison de depart et d'arrivee de la tournee.
     */
    protected Livraison entrepot;
    
    /**
     * Le nombre de livraisons totale du fichier XML de demande de livraisons.
     */
    protected int nbLivraisons;

	
    /**
     * Cree un objet DemandeDeLivraison a partir d'un fichier XML.
     * 
     * @param fenetres
     * 	Fenetres temporelles decrites dans le XML.
     * 
     * @param entrepot
     * 	Entrepot defini dans le XML.
     */
    public DemandeDeLivraison(List<FenetreTemporelle> fenetres, Livraison entrepot) {
		super();
		
		this.tournee = new Tournee();
		this.fenetres = fenetres;
		this.entrepot = entrepot;
		
		// Compte le nombre de livraisons du fichier XML
		int nbLivraisons = 0;
		
		for (FenetreTemporelle fenetre : fenetres) {
			Set<Livraison> livraisonsFActuelle = fenetre.getLivraisons();
			nbLivraisons += livraisonsFActuelle.size();
		}
		
		this.nbLivraisons = nbLivraisons;
	}

    /**
     * 
     * @param map
     * @param value
     * @return
     */
    public static Integer getKeyByValue(Map<Integer, Livraison> map, Livraison value) {
    	
        Integer key=0;
        for (Entry<Integer, Livraison> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                key=entry.getKey();
            }
        }
        return key;
    }
    
    /**
     * @param livraison1 
     * @param livraison2
     */
    protected void modifierTournee(Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

   /**
    * 
    * @param livraisonAvant
    * @param livraison
    */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }
    
    /**
     * 
     * @return
     */
    private Map<Integer,Livraison> correspondanceLivraisons (){
    	
    	Map<Integer,Livraison> mapLivraisons = new HashMap<Integer,Livraison>();
    	Integer livraisonInteger = 0;
    	
    	for(int i = 0; i<fenetres.size(); i++){
    		
    		FenetreTemporelle fenetreActuelle = fenetres.get(i);
    		Set<Livraison> livraisonsFenetre = fenetreActuelle.getLivraisons();
    		
    		for(Livraison livraisonActuelle : livraisonsFenetre){
    			mapLivraisons.put(livraisonInteger, livraisonActuelle);
    			livraisonInteger++;
    		}	
    	}
    	
    	return mapLivraisons;
    	
    }

    /**
     * 
     * @param map
     * @return tableauArcs
     */

    protected int[][] genererTableauArcs(Map<Integer,Livraison> map){
    	
    	int tableauArcs[][]= new int[nbLivraisons][nbLivraisons];
    	
    	for(int i = 0; i<fenetres.size()-1; i++){
    		FenetreTemporelle fenetreActuelle = fenetres.get(i);
    		FenetreTemporelle fenetreSuivante = fenetres.get(i+1);
    		
    		Set<Livraison> livraisonsFActuelle = fenetreActuelle.getLivraisons();
    		Set<Livraison> livraisonsFSuivante = fenetreSuivante.getLivraisons();
    		
    		for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
    			
    			for(Livraison livraisonDestActuelle : livraisonsFActuelle){
    				if(livraisonDestActuelle.getId() != livraisonSourceActuelle.getId()){
    					Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    					Integer integerDest = getKeyByValue(map, livraisonDestActuelle);
    					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestActuelle);					
    				}
    			}
    			
    			for(Livraison livraisonDestSuivante : livraisonsFSuivante){
    				Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    				Integer integerDest = getKeyByValue(map, livraisonDestSuivante);
    				tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestSuivante);
    			}
    		}
    	}
    	
    	/*Traitement de la dernière Fenetre */ 
    	
    	FenetreTemporelle fenetreActuelle = fenetres.get(fenetres.size()-1);
    	Set<Livraison> livraisonsFActuelle = fenetreActuelle.getLivraisons();
    	
    	for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
			
			for(Livraison livraisonDestActuelle : livraisonsFActuelle){
			
				if(livraisonDestActuelle.getId() != livraisonSourceActuelle.getId()){
					Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
					Integer integerDest = getKeyByValue(map, livraisonDestActuelle);
					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestActuelle);					
				}
			}
    	}
    	
    	return tableauArcs;
    }
    
    /**
     * @param plan
     */
    protected void calculerTournee(Plan plan, TSP1 tsp) {
        // TODO implement here
    	
    	// Calcul des plus courts chemins a partir d'un livraison sur tout le plan
    	calculDesPlusCourtsChemins(plan);

    	// Creation des correspondances entre un sommet (Integer) et une livraison
		Map<Integer,Livraison> mapLivraisons = correspondanceLivraisons();
		
    	// Generation des arcs du graphe de livraisons
    	int couts[][] = genererTableauArcs(mapLivraisons);
    	
    	// Generation du graphe de livraisons
    	GrapheLivraisons graphe = new GrapheLivraisons(nbLivraisons, couts);
    	
    	// Recherche de la solution avec TSP
    	tsp.chercheSolution(0, graphe);
    	
    	// Recuperer l'ordre des livraisons a effectuer grace a TSP
    	List<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	livraisonsEnOrdre = recupererLivraisonsEnOrdre(graphe, tsp, mapLivraisons);
    	
    	// Récupérer l'ordre des itinéraires entre les livraisons.
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	itinerairesEnOrdre = recupererItinerairesEnOrdre(livraisonsEnOrdre, mapLivraisons,couts);
    	
    }

    private void calculDesPlusCourtsChemins(Plan plan) {
		// TODO Auto-generated method stub
    	
    	// Recherche des plus courts chemins pour l'entrepôt mentionné dans le
    	// fichier XML
    	entrepot.calculerPlusCourtsChemins(plan);  	
    	
    	// Recherche des plus courts chemins pour toutes les livraisons du fichier
    	// XML
    	Set<Livraison> livraisons;
    	
    	for (FenetreTemporelle fenetre : fenetres) {
    		
    		livraisons = fenetre.getLivraisons();
    		
    		for (Livraison livraisonDepart : livraisons) {
    			livraisonDepart.calculerPlusCourtsChemins(plan);
    		}
    	}
	}


	private List<Livraison> recupererLivraisonsEnOrdre(GrapheLivraisons graphe, TSP1 tsp, Map<Integer, Livraison> mapLivraisons) {
		// TODO Auto-generated method stub
    	
    	List<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	Integer nombreSommet = graphe.getNbSommets();
    	
    	for (int i = 0; i < nombreSommet; i++) {
    		Integer numeroSommet = tsp.getSolution(i);
    		Livraison livraison = mapLivraisons.get(numeroSommet);
        	livraisonsEnOrdre.add(livraison);
    	}
    	
    	
    	
		return livraisonsEnOrdre;
	}
    
    /**
     * @param listeLivraisons
     * @param mapLivraisons
     * @return
     */
    private List<Itineraire> recupererItinerairesEnOrdre(List<Livraison> listeLivraisons, Map<Integer, Livraison> mapLivraisons, int[][] couts){
 
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	for(int i = 0; i < listeLivraisons.size() -1; i++)
    	{
    		Livraison livraisonActuelle = listeLivraisons.get(i);
    		Livraison livraisonSuivante = listeLivraisons.get(i+1);
    		 
    		int depart = getKeyByValue(mapLivraisons, livraisonActuelle);
    		int arrivee = getKeyByValue(mapLivraisons, livraisonSuivante);
    		int coutItineraire = couts[depart][arrivee];
    		List <Troncon> troncons = livraisonSuivante.rechercherTroncons();
    		
    		Itineraire itineraire = new Itineraire(coutItineraire, troncons, livraisonActuelle, livraisonSuivante);
    		
    		itinerairesEnOrdre.add(itineraire);
    		
    	}
    	return itinerairesEnOrdre;
    }

	/**
     * @param plan 
     * @param depart 
     * @param arrivee
     */
    protected void calculPlusCourtChemin(Plan plan, Livraison depart, Livraison arrivee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genereFeuilleDeRoute() {
        // TODO implement here
    }

}