package modele;
import java.util.*;

public class GraphePondere {
	
	/**
	 * Default Constructor
	 */
	 public GraphePondere(){
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
		 
		 Integer numeroIntersection = 0;
		 
		 // Attribution d'un numéro à toutes les intersections
		 Map<Intersection, Integer> mapIntersections = new HashMap<Intersection, Integer>();
		 for(Intersection intersection : setIntersections){
			 mapIntersections.put(intersection,numeroIntersection);
			 numeroIntersection++;
		 }
		 this.mapCorrespondance = mapIntersections;
		 
		 //Récupération des intersections voisines de chaque intersection et du cout associé
		 for (Intersection intersection : setIntersections){
			 
			 Integer origin_Integer = mapIntersections.get(intersection);
			 int origin = origin_Integer.intValue();
			 
			 Map<Intersection,Integer> intersectionsVoisines = intersection.getIntersectionsVoisines();
			
			 //Récupération du numéro de chaque voisin et du cout associé au trajet
			 for (Map.Entry<Intersection,Integer> entry : intersectionsVoisines.entrySet()) {
				    Intersection voisin = entry.getKey();
				    Integer cout_Integer = entry.getValue();
				    Integer dest_Integer= mapIntersections.get(voisin);
				    
				    int dest = dest_Integer.intValue();
				    int cout = cout_Integer.intValue();
				    
				    couts[origin][dest]=cout;
				}
		
		 }
		 
		 this.couts = couts;
	 } 
	 
	 public Map<Intersection, Integer> getMapCorrespondance() {
		return mapCorrespondance;
	}

	public void setMapCorrespondance(Map<Intersection, Integer> mapCorrespondance) {
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
	  */
	 protected int [][]  couts;
	 
	 /*
	  * 
	  */
	 protected int nbNoeuds;
	 
	 /**
	 * 
	 */
	protected Map<Intersection, Integer> mapCorrespondance;
	 
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
