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
    
    /**
     * 
     * @param nbSommets
     * @param couts
     */
    public GrapheLivraisons(int nbSommets, float[][] couts) {
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
    protected float couts[][];
    
    /**
     * 
     * @return
     */
    public int getNbSommets() {
		return nbSommets;
	}

    /**
     * 
     * @param i
     * @param j
     * @return
     */
	public float getCout(Integer i,Integer j) {
		return couts[i][j];
	}

	@Override
	public int getCout(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estArc(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	

}