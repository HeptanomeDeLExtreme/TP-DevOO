package modele;
import java.util.*;

public class GraphePondere {
	
	/**
	  * Tableau contenant les couts des arcs entre chaque noeud.
	  */
	 protected int [][]  couts;

	/**
	  * Nombre de noeuds du graphe
	  */
	 protected int nbNoeuds;

	/**
	 * Association des noeuds a une valeur numerique.
	 */
	protected Map<Integer, Intersection> mapCorrespondance;

	/**
	 * Default Constructor
	 */
	 public GraphePondere(){
	 }
	 
	  
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
	  * Genere un Graphe a partir d'un plan passe en parametre.
	  * 
	  * @param plan
	  */
	 public GraphePondere(Plan plan) {
	
		 Set<Intersection> setIntersections =plan.getIntersections();
		 this.nbNoeuds = setIntersections.size();
		 
		 int[][]couts= new int[nbNoeuds][nbNoeuds];
		 for(int i = 0;i<nbNoeuds;i++){
			 for(int j = 0; j<nbNoeuds;j++){
				 couts[i][j] = 0;
			 }
		 }
		 
		 Integer numeroIntersection = 0;
		 // Attribution d'un numéro à toutes les intersections
		 Map<Integer, Intersection> mapIntersections = new HashMap<Integer, Intersection>();
		 for(Intersection intersection : setIntersections){

			 mapIntersections.put(numeroIntersection, intersection);

			 numeroIntersection++;
		 }
		 
		Integer compteur = 0;
		for(Intersection intersection : setIntersections) {

			Integer resultat = getKeyByValue(mapIntersections, intersection);

			compteur++;
		}

		 this.mapCorrespondance = mapIntersections;
		 
		 //Récupération des intersections voisines de chaque intersection et du cout associé
		 for (Intersection intersection : setIntersections){
			 
			 Integer origin_Integer = getKeyByValue(mapIntersections, intersection);
			 int origin = origin_Integer.intValue();
			 
			 Map<Intersection,Integer> intersectionsVoisines = intersection.getIntersectionsVoisines();
			
			 //Récupération du numéro de chaque voisin et du cout associé au trajet
			 for (Map.Entry<Intersection,Integer> entry : intersectionsVoisines.entrySet()) {
				    Intersection voisin = entry.getKey();
				    Integer cout_Integer = entry.getValue();
				    Integer dest_Integer= getKeyByValue(mapIntersections, voisin);
				    
				    int dest = dest_Integer.intValue();
				    int cout = cout_Integer.intValue();
				    
				    couts[origin][dest]=cout;
				}
		
		 }
		 
		 this.couts = couts;
	 } 
	 
		public String toString(){
		 String toRet = "";
		 for(int i = 0; i< this.nbNoeuds;i++){
			 for(int j = 0;j<this.nbNoeuds;j++){
				 toRet += i+" est relié à "+j+" avec un cout de "+getWeight(i,j) +"\n";
			 }
		 }
		 return toRet;
	 }
	 
	 public Map<Integer, Intersection> getMapCorrespondance() {
		return mapCorrespondance;
	}

	public void setMapCorrespondance(Map<Integer, Intersection> mapCorrespondance) {
		this.mapCorrespondance = mapCorrespondance;
	}

	public int[][] getCouts() {
		return couts;
	}

	public void setCouts(int[][] couts) {
		this.couts = couts;
	}

	public int getNbNoeuds() {
		return nbNoeuds;
	}

	public void setNbNoeuds(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
	}


	 
	 /**
	  * 
	  * @param source
	  * @param dest
	  * @param cout
	  */
    public void addEdge(int source, int dest, int cout){ 
    	couts[source][dest] = cout; 
    }
    
    /**
     * 
     * @param source
     * @param dest
     * @return
     */
	public boolean isEdge(int source, int dest){ 
		return couts[source][dest]>0; 
	}
    
	/**
	 * 
	 * @param source
	 * @param dest
	 */
	public void removeEdge(int source, int dest){
		couts[source][dest] = 0; 
	}
	
    /**
     * 
     * @param source
     * @param dest
     * @return
     */
	public int getWeight (int source, int dest)  
	{ 
		return couts[source][dest]; 
	}
	
	/**
	 * 
	 * @param noeud
	 * @return
	 */
	public int [] neighbors (int noeud) {
		
		 int count = 0;
		 for (int i=0; i<couts[noeud].length; i++) {
			 if (couts[noeud][i]>0) count++;
		 }
		 
		 final int[]answer= new int[count];
		 count = 0;
		 for (int i=0; i<couts[noeud].length; i++) {
			 if (couts[noeud][i]>0) answer[count++]=i;
		 }
		 
		 return answer;
	}

}
