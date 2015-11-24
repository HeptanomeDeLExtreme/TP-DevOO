
package modele;
import java.util.*;

public class Dijkstra {
	  
	 // Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int [] dijkstra (GraphePondere G, int s) {
	final int [] dist = new int [G.getNbNoeuds()];  // shortest known distance from "s"
	final int [] pred = new int [G.getNbNoeuds()];  // preceeding node in path
	final boolean [] visited = new boolean [G.getNbNoeuds()]; // all false initially
	   

	for (int i=0; i<dist.length; i++) {
		dist[i] = Integer.MAX_VALUE;
	}

	dist[s] = 0; 
	for (int i=0; i<dist.length; i++) {
		 		
		final int next = minVertex (dist, visited);
		visited[next] = true;
	 
	 	// The shortest path to next is dist[next] and via pred[next].
	 
		final int [] n = G.neighbors (next);

		for (int j=0; j<n.length; j++) {
			final int v = n[j];
			final int d = dist[next] + G.getWeight(next,v);
			if (dist[v] > d) {
				dist[v] = d;
				pred[v] = next;
				}
			}
		}
	
	return pred;  // (ignore pred[s]==0!)

	}
	  

	private static int minVertex (int [] dist, boolean [] v) {

		int x = Integer.MAX_VALUE;

		int y = -1;   // graph not connected, or no unvisited vertices

		for (int i=0; i<dist.length; i++) {
			if (!v[i] && dist[i]<x) {y=i; x=dist[i];}
			}
		
		return y;
	 }

	
	}
