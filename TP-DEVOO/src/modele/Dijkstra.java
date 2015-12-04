package modele;

/**
 * 
 * Implemente l'Algorithme de Dijkstra afin de determiner les plus courts
 * chemins d'une intersection a une autre
 * 
 */
public class Dijkstra {

    /**
     * Algorithme de Dijkstra
     * 
     * @param G
     *            graphe pondéré traité
     * @param racine
     *            Noeud à partir duquel les coûts sont calculés
     * @return int[][] {tableau des distances, tableau des prédecesseurs}
     */
    public static int[][] dijkstra(GraphePondere G, int racine) {
	final int[] dist = new int[G.getNbNoeuds()]; // tableau des distances
	final int[] pred = new int[G.getNbNoeuds()]; // tableau des
						     // prédécesseurs
	for (int index = 0; index < G.getNbNoeuds(); index++) {
	    pred[index] = -1;
	}
	final boolean[] visite = new boolean[G.getNbNoeuds()]; // couleurs

	for (int i = 0; i < dist.length; i++) {
	    dist[i] = Integer.MAX_VALUE;
	}

	dist[racine] = 0;
	for (int i = 0; i < dist.length; i++) {

	    final int suivant = calculSuivant(dist, visite);
	    visite[suivant] = true;

	    final int[] n = G.neighbors(suivant);

	    for (int j = 0; j < n.length; j++) {
		final int v = n[j];
		final int d = dist[suivant] + G.getWeight(suivant, v);
		if (dist[v] > d) {
		    dist[v] = d;
		    pred[v] = suivant;
		}
	    }
	}

	return new int[][] { dist, pred };

    }

    /**
     * 
     * @param dist
     * @param v
     * @return
     */
    private static int calculSuivant(int[] dist, boolean[] v) {

	int x = Integer.MAX_VALUE;

	int y = -1;

	for (int i = 0; i < dist.length; i++) {
	    if (!v[i] && dist[i] < x) {
		y = i;
		x = dist[i];
	    }
	}

	return y;
    }

}
