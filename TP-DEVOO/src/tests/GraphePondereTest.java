/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import modele.GraphePondere;
import modele.Intersection;
import modele.Plan;
import modele.Troncon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class GraphePondereTest {

	Plan plan;
	
	GraphePondere graphePondere;
	
	Intersection i1 = new Intersection (63, 96, 20);
	Intersection i2= new Intersection (97,10,10);
	Intersection i3= new Intersection (45,46,85);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		
		String nomRue1 = "h2";
		float longueur1 = (float) 100.3;
		float vitesse1 = (float) 20.7;
		Troncon troncon1 = new Troncon(nomRue1, vitesse1, longueur1, i1, i2);
		i1.ajouteTronconSortant(troncon1);
		i2.ajouteTronconEntrant(troncon1);
		
		String nomRue2 = "j2";
		float longueur2 = (float) 178.3;
		float vitesse2 = (float) 36.7;
		Troncon troncon2 = new Troncon(nomRue2, vitesse2, longueur2, i2, i1);
		i2.ajouteTronconSortant(troncon2);
		i1.ajouteTronconEntrant(troncon2);
		
		String nomRue3 = "d3";
		float longueur3 = (float) 45.3;
		float vitesse3 = (float) 53.7;
		Troncon troncon3 = new Troncon(nomRue3, vitesse3, longueur3, i2, i3);
		i2.ajouteTronconSortant(troncon3);
		i3.ajouteTronconEntrant(troncon3);

		String nomRue4 = "d1";
		float longueur4 = (float) 162.6;
		float vitesse4 = (float) 15.3;
		Troncon troncon4 = new Troncon(nomRue4, vitesse4, longueur4, i3, i1);
		i3.ajouteTronconSortant(troncon4);
		i1.ajouteTronconEntrant(troncon4);
		
		Set<Intersection> intersections = new HashSet<Intersection>();
		intersections.add(i1);
		intersections.add(i2);
		intersections.add(i3);
		
		plan = new Plan(intersections);
		
		graphePondere = new GraphePondere(plan);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link modele.GraphePondere#GraphePondere(modele.Plan)}.
	 */
	@Test
	public void testGetKeyByValue() {
		Map<Integer, Intersection> mapIntersectionsGraphe = graphePondere.getMapCorrespondance();
		Integer cle = graphePondere.getKeyByValue(mapIntersectionsGraphe, i1);
		
		assertTrue(mapIntersectionsGraphe.get(cle).equals(i1));
		
	}
	
	/**
	 * Test method for {@link modele.GraphePondere#GraphePondere(modele.Plan)}.
	 */
	@Test
	public void testGraphePonderePlan() {
		assertEquals(graphePondere.getNbNoeuds(), 3);
		
		Map<Integer, Intersection> mapIntersectionsGraphe = graphePondere.getMapCorrespondance();
		
		Set<Intersection> setIntersections = plan.getIntersections();
		
		Integer numeroIntersection = 0;
		Map<Integer, Intersection> mapIntersections = new HashMap<Integer, Intersection>();
		for(Intersection intersection : setIntersections){
			mapIntersections.put(numeroIntersection, intersection);
			numeroIntersection++;
		}
		assertEquals(mapIntersectionsGraphe, mapIntersections);
		
		 int[][]couts= new int[graphePondere.getNbNoeuds()][graphePondere.getNbNoeuds()];
		 for(int i = 0;i<graphePondere.getNbNoeuds();i++){
			 for(int j = 0; j<graphePondere.getNbNoeuds();j++){
				 couts[i][j] = 0;
			}
		}
		
		for (Intersection intersection : setIntersections){
			 
			 Integer origin_Integer = graphePondere.getKeyByValue(mapIntersections, intersection);
			 int origin = origin_Integer.intValue();
			 
			 Map<Intersection,Integer> intersectionsVoisines = intersection.getIntersectionsVoisines();
			
			 for (Map.Entry<Intersection,Integer> entry : intersectionsVoisines.entrySet()) {
				    Intersection voisin = entry.getKey();
				    Integer cout_Integer = entry.getValue();
				    Integer dest_Integer= graphePondere.getKeyByValue(mapIntersections, voisin);
				    
				    int dest = dest_Integer.intValue();
				    int cout = cout_Integer.intValue();
				    
				    couts[origin][dest]=cout;
			}
		 }
		couts = (int[][])couts;
		int[][] coutsGraphe = (int[][])graphePondere.getCouts();
		assertEquals(couts, coutsGraphe);
	}

	/**
	 * Test method for {@link modele.GraphePondere#addEdge(int, int, int)}.
	 */
	@Test
	public void testAddEdge() {
		int[][] couts = graphePondere.getCouts();
		int cout = couts[0][1];
		graphePondere.addEdge(0, 1, cout+2);
		
		int[][] coutsModifies = graphePondere.getCouts();
		assertEquals(coutsModifies[0][1], cout+2);
	}

	/**
	 * Test method for {@link modele.GraphePondere#isEdge(int, int)}.
	 */
	@Test
	public void testIsEdge() {
		int[][] couts = graphePondere.getCouts();
		for(int i = 0; i<couts.length ; i++){
			for(int j = 0 ; j<couts[i].length ; j++){
				if(couts[i][j]>0){
					assertTrue(graphePondere.isEdge(i, j));	
				}
				else{
					assertTrue(!graphePondere.isEdge(i, j));	
				}
			}
		}
	}

	/**
	 * Test method for {@link modele.GraphePondere#removeEdge(int, int)}.
	 */
	@Test
	public void testRemoveEdge() {
		int[][] couts = graphePondere.getCouts();
		for(int i = 0; i<couts.length ; i++){
			for(int j = 0 ; j<couts[i].length ; j++){
				if(couts[i][j]!=0){
					graphePondere.removeEdge(i, j);
					int coutsModifies = graphePondere.getWeight(i,j);
					assertEquals(coutsModifies, 0);	
				}
			}
		}
	}

	/**
	 * Test method for {@link modele.GraphePondere#getWeight(int, int)}.
	 */
	@Test
	public void testGetWeight() {
		int[][] couts = graphePondere.getCouts();
		for(int i = 0; i<couts.length ; i++){
			for(int j = 0 ; j<couts[i].length ; j++){
				if(couts[i][j]!=0){
					int cout = graphePondere.getWeight(i,j);
					assertEquals(couts[i][j], cout);	
				}
			}
		}	}

	/**
	 * Test method for {@link modele.GraphePondere#neighbors(int)}.
	 */
	@Test
	public void testNeighbors() {

		int nbEdge=0;
		int[][] couts = graphePondere.getCouts();		
		Map<Integer, Intersection> mapIntersectionsGraphe = graphePondere.getMapCorrespondance();
		
		for (Map.Entry<Integer,Intersection> entry : mapIntersectionsGraphe.entrySet()) {			
			if(entry.getValue().equals(i1)){
				Map<Intersection,Integer> intersectionsVoisines = i1.getIntersectionsVoisines();
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
					 
					 if(couts[entry.getKey()][intersection]>0){
						 nbEdge++;
					 }
				}
				int [] neighbors = new int[nbEdge];
				nbEdge=0;
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
					 
					 if(couts[entry.getKey()][intersection]>0){
						 neighbors[nbEdge++] = intersection;
					 }
				}
				nbEdge-=1;
				assertArrayEquals(graphePondere.neighbors(entry.getKey()), neighbors);
			}
			else if(entry.getValue().equals(i2)){
				Map<Intersection,Integer> intersectionsVoisines = i2.getIntersectionsVoisines();
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
					 
					 if(couts[entry.getKey()][intersection]>0){
						 nbEdge++;
					 }
				}
				int [] neighbors = new int[nbEdge];
				nbEdge=0;
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
				
					 if(couts[entry.getKey()][intersection]>0){
						 neighbors[nbEdge++] = intersection;
					 }
				}
				nbEdge-=1;
				assertArrayEquals(graphePondere.neighbors(entry.getKey()), neighbors);
			}
			else if(entry.getValue().equals(i3)){
				Map<Intersection,Integer> intersectionsVoisines = i3.getIntersectionsVoisines();
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
					 
					 if(couts[entry.getKey()][intersection]>0){
						 nbEdge++;
					 }
				}
				int [] neighbors = new int[nbEdge];
				nbEdge=0;
				
				for (Map.Entry<Intersection,Integer> entryIntersection : intersectionsVoisines.entrySet()) {
					 int intersection = graphePondere.getKeyByValue(mapIntersectionsGraphe, entryIntersection.getKey());
				
					 if(couts[entry.getKey()][intersection]>0){
						 neighbors[nbEdge++] = intersection;
					 }
				}
				nbEdge-=1;
				assertArrayEquals(graphePondere.neighbors(entry.getKey()), neighbors);
			}
			else{
				fail("Intersection inexistante.");
			}
		}
	}
}
