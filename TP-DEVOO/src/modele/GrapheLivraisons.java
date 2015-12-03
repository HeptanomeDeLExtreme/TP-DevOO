package modele;

import java.util.*;

/**
 * 
 */
public class GrapheLivraisons implements tsp.Graphe{

    /**
     * Default constructor
     */
    public GrapheLivraisons() {
    }

    
    /**
     * 
     * @param nbSommets
     * @param couts
     */
    public GrapheLivraisons(int nbSommets, int[][] couts) {
		super();
		this.nbSommets = nbSommets;
		this.couts = couts;
	}

	/**
     * 
     */
    protected int nbSommets;
    
    /**
     * 
     */
    protected int couts[][];
    
    /**
     * 
     * @return
     */
    public int getNbSommets() {
		return nbSommets;
	}

   
	@Override
	 /**
     * Obtenir le cout d'un arcentre un noeud i et un noeud j
     * @param i Noeud i
     * @param j Noeud j
     * @return Cout de l'arc
     */
	public int getCout(int i, int j) {
		
		 Integer int_i = new Integer(i);
		 Integer int_j = new Integer(j);
		 
		return couts[int_i][int_j] ;
	}

	@Override
	/**
	 * 
	 */
	public boolean estArc(int i, int j) {
		
		Integer int_i = new Integer(i);
		Integer int_j = new Integer(j);
		
		if (couts[int_i][int_j]!=0){
			return true;
		}
			
		else{
			return false;
		}
			
	}

	

}