package modele;
import java.util.*;

public class GraphePondere {
	
	/**
	 * Default Constructor
	 */
	 public GraphePondere(){
	 }
	 
	 /**
	  * 
	  * @param n
	  */
	 public GraphePondere(Plan plan) {
	
		 Set<Intersection> setIntersections =plan.getIntersections();
		 this.nbNoeuds = setIntersections.size();
		 
		 int[][]couts= new int[nbNoeuds][nbNoeuds];
		 
		 Integer numeroIntersection = 0;
		 
		 Map<Intersection, Integer> mapIntersections = new HashMap<Intersection, Integer>();
		 for(Intersection intersectionActuelle : setIntersections){
			 mapIntersections.put(intersectionActuelle,numeroIntersection);
			 numeroIntersection++;
		 }
		 
		 
		 
		 
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
