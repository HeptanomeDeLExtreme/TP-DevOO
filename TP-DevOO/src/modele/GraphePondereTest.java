package modele;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphePondereTest {
	
	@Test
	public void testGraphePondere1() {
		Intersection inter1 = new Intersection(1, 1, 1);
		Intersection inter2 = new Intersection(2, 2, 2);
		
		Troncon troncon1 = new Troncon("rue1", (float)1, (float)5, inter1, inter2);
		Troncon troncon2 = new Troncon("rue2", (float)2, (float)5, inter2, inter1);
		
		Set<Troncon> set1 = new HashSet<Troncon>();
		set1.add(troncon1);
		
		Set<Troncon> set2 = new HashSet<Troncon>();
		set2.add(troncon2);
		
		inter1.setTronconsSortant(set1);
		inter2.setTronconsSortant(set2);
		
		Set<Intersection> listeInter = new HashSet<Intersection>();
		listeInter.add(inter1);
		listeInter.add(inter2);
		
		Plan plan = new Plan(listeInter);
		
		// Graphe
		int [][]coutsObtenus;
		int [][]coutsAttendus = { {0, 50}, {100, 0} };
		
		int nbSommetsAttendus = 2;
		int nbSommetsObtenus;
		
		Map<Intersection, Integer> mapIntersectionsAttendue = new HashMap<Intersection, Integer>();
		Integer compteur = 0;
		for(Intersection uneInter : plan.getIntersections()) {
			mapIntersectionsAttendue.put(uneInter, compteur);
			compteur++;
		}
		Map<Intersection, Integer> mapIntersectionsObtenue = new HashMap<Intersection, Integer>();
		
		GraphePondere unGraphe = new GraphePondere(plan);
		coutsObtenus = unGraphe.getCouts();
		nbSommetsObtenus = unGraphe.getNbNoeuds();
		mapIntersectionsObtenue = unGraphe.getMapCorrespondance();
		System.out.println("Nb sommets : "+nbSommetsAttendus);
		//assertEquals(nbSommetsAttendus, nbSommetsObtenus);
		//assertEquals(mapIntersectionsAttendue, mapIntersectionsObtenue);
		
		for(int i = 0; i < nbSommetsAttendus; i++) {
			System.out.println("i : "+mapIntersectionsObtenue.get(i));
		}
		//assertArrayEquals(coutsAttendus, coutsObtenus);
	}
	
	@Test
	public void testGraphePondere2() {
		Intersection inter1 = new Intersection(1, 1, 1);
		Intersection inter2 = new Intersection(2, 2, 2);
		Intersection inter3 = new Intersection(3, 3, 3);
		
		Troncon troncon1 = new Troncon("rue1", (float)1, (float)5, inter1, inter2);
		Troncon troncon2 = new Troncon("rue2", (float)2, (float)5, inter2, inter3);
		Troncon troncon3 = new Troncon("rue3", (float)3, (float)5, inter3, inter1);
		Troncon troncon4 = new Troncon("rue4", (float)4, (float)5, inter3, inter2);
		
		Set<Troncon> tronconsInter1 = new HashSet<Troncon>();
		tronconsInter1.add(troncon1);
		
		Set<Troncon> tronconsInter2 = new HashSet<Troncon>();
		tronconsInter2.add(troncon2);
		
		Set<Troncon> tronconsInter3 = new HashSet<Troncon>();
		tronconsInter3.add(troncon3);
		tronconsInter3.add(troncon4);
		
		inter1.setTronconsSortant(tronconsInter1);
		inter2.setTronconsSortant(tronconsInter2);
		inter3.setTronconsSortant(tronconsInter3);
		
		Set<Intersection> listeInter = new HashSet<Intersection>();
		listeInter.add(inter1);
		listeInter.add(inter2);
		listeInter.add(inter3);
		
		Plan plan = new Plan(listeInter);
		
		// Graphe
		int [][]coutsObtenus;
		int [][]coutsAttendus = { {0, 50, 0}, {0, 0, 100}, {150, 200, 0} };
		
		int nbSommetsAttendus = 3;
		int nbSommetsObtenus;
		
		Map<Intersection, Integer> mapIntersectionsAttendue = new HashMap<Intersection, Integer>();
		Integer compteur = 0;
		for(Intersection uneInter : plan.getIntersections()) {
			mapIntersectionsAttendue.put(uneInter, compteur);
			compteur++;
		}
		Map<Intersection, Integer> mapIntersectionsObtenue = new HashMap<Intersection, Integer>();
		
		GraphePondere unGraphe = new GraphePondere(plan);
		coutsObtenus = unGraphe.getCouts();
		nbSommetsObtenus = unGraphe.getNbNoeuds();
		mapIntersectionsObtenue = unGraphe.getMapCorrespondance();
		
		/*assertEquals(nbSommetsAttendus, nbSommetsObtenus);
		assertEquals(mapIntersectionsAttendue, mapIntersectionsObtenue);
		assertArrayEquals(coutsAttendus, coutsObtenus);*/
	}

}
