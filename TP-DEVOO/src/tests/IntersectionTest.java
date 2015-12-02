/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import modele.Intersection;
import modele.Troncon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class IntersectionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.Intersection#Intersection(java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testIntersectionIntegerIntegerInteger() {
		Intersection intersection = new Intersection(97, 10, 10);
		assertEquals((int)intersection.getId(),97);
		assertEquals((int)intersection.getX(),10);
		assertEquals((int)intersection.getY(),10);
	}

	/**
	 * Test method for {@link modele.Intersection#equals(modele.Intersection)}.
	 */
	@Test
	public void testEqualsIntersection() {
		Intersection intersection = new Intersection(97, 10, 10);
		assertTrue(intersection.equals(new Intersection (97,10,10)));
		assertTrue(!intersection.equals(new Intersection (94,10,10)));
	}

	/**
	 * Test method for {@link modele.Intersection#rechercherTroncon(modele.Intersection)}.
	 */
	@Test
	public void testRechercherTroncon() {
		Intersection depart = new Intersection (63, 15, 20);
		Intersection arrivee = new Intersection (97,10,10);
		String nomRue = "h2";
		float longueur = (float) 100.3;
		float vitesse = (float) 20.7;
		Intersection origine = depart;
		Intersection destination = arrivee;
		Troncon troncon = new Troncon(nomRue, vitesse, longueur, origine, destination);
		depart.ajouteTronconSortant(troncon);
		
		Troncon tronconTrouve = depart.rechercherTroncon(arrivee);
		assertEquals(tronconTrouve.getNomDeRue(), "h2");
		assertEquals(tronconTrouve.getVitesseMoyenne(), 20.7, 0.1);
		assertEquals(tronconTrouve.getLongueur(), 100.3, 0.1);
		assertTrue(tronconTrouve.getDestination().equals(arrivee));
		assertTrue(tronconTrouve.getOrigine().equals(depart));
	}

	
	/**
	 * Test method for {@link modele.Intersection#ajouteTronconEntrant(modele.Troncon)}.
	 */
	@Test
	public void testAjouteTronconEntrant() {
		Intersection depart = new Intersection (63, 15, 20);
		Intersection arrivee = new Intersection (97,10,10);
		String nomRue = "h2";
		float longueur = (float) 100.3;
		float vitesse = (float) 20.7;
		Troncon troncon = new Troncon(nomRue, vitesse, longueur, depart, arrivee);
		arrivee.ajouteTronconEntrant(troncon);
		
		Set<Troncon> tronconsTrouve = depart.getTronconsEntrant();
		
		for (Troncon tronconTrouve : tronconsTrouve){
			if(tronconTrouve.getNomDeRue() == "h2"){
				assertEquals(tronconTrouve.getVitesseMoyenne(), 20.7, 0.1);
				assertEquals(tronconTrouve.getLongueur(), 100.3, 0.1);
				assertTrue(tronconTrouve.getDestination().equals(arrivee));
				assertTrue(tronconTrouve.getOrigine().equals(depart));
			}
			else{
				fail("Le troncon trouvé n'est pas censé exister");
			}
		}			
	}

	/**
	 * Test method for {@link modele.Intersection#ajouteTronconSortant(modele.Troncon)}.
	 */
	@Test
	public void testAjouteTronconSortant() {
		Intersection depart = new Intersection (63, 15, 20);
		Intersection arrivee = new Intersection (97,10,10);
		String nomRue = "h2";
		float longueur = (float) 100.3;
		float vitesse = (float) 20.7;
		Troncon troncon = new Troncon(nomRue, vitesse, longueur, depart, arrivee);
		depart.ajouteTronconSortant(troncon);
		
		Set<Troncon> tronconsTrouve = depart.getTronconsSortant();
		
		for (Troncon tronconTrouve : tronconsTrouve){
			if(tronconTrouve.getNomDeRue() == "h2"){
				assertEquals(tronconTrouve.getVitesseMoyenne(), 20.7, 0.1);
				assertEquals(tronconTrouve.getLongueur(), 100.3, 0.1);
				assertTrue(tronconTrouve.getDestination().equals(arrivee));
				assertTrue(tronconTrouve.getOrigine().equals(depart));
			}
			else{
				fail("Le troncon trouvé n'est pas censé exister");
			}
		}		
	}

	/**
	 * Test method for {@link modele.Intersection#contient(java.awt.Point, float, float)}.
	 */
	@Test
	public void testContient() {
		Intersection intersection = new Intersection (63, 15, 20);
		assertTrue(intersection.contient(new Point(15,20), 1, 1));
		assertTrue(intersection.contient(new Point(17,28), 1, 1));
		assertTrue(!intersection.contient(new Point(25,20), 1, 1));
		assertTrue(!intersection.contient(new Point(15,30), 1, 1));
		assertTrue(!intersection.contient(new Point(46,20), 1, 1));
		assertTrue(!intersection.contient(new Point(15,80), 1, 1));
		assertTrue(!intersection.contient(new Point(5,20), 1, 1));
		assertTrue(!intersection.contient(new Point(15,10), 1, 1));
		assertTrue(intersection.contient(new Point(10,12), 1, 1));
	}

	/**
	 * Test method for {@link modele.Intersection#getIntersectionsVoisines()}.
	 */
	@Test
	public void testGetIntersectionsVoisines() {
		Intersection depart = new Intersection (63, 75, 20);
		Intersection arrivee1 = new Intersection (97,10,10);
		Intersection arrivee2 = new Intersection (94,19,83);
		String nomRue1 = "h2";
		float longueur1 = (float) 100.3;
		float vitesse1 = (float) 20.7;
		Troncon troncon1 = new Troncon(nomRue1, vitesse1, longueur1, depart, arrivee1);
		String nomRue2 = "k2";
		float longueur2 = (float) 189.3;
		float vitesse2 = (float) 34.7;
		Troncon troncon2 = new Troncon(nomRue2, vitesse2, longueur2, depart, arrivee2);
		depart.ajouteTronconSortant(troncon1);
		depart.ajouteTronconSortant(troncon2);
		int coutTroncon1 = troncon1.getCout();
		int coutTroncon2 = troncon2.getCout();
		
		Map<Intersection,Integer> intersectionsVoisines = depart.getIntersectionsVoisines();
		
		for(Map.Entry<Intersection, Integer> intersectionVoisine : intersectionsVoisines.entrySet() ){
			if (intersectionVoisine.getKey().getId() == 97){
				assertEquals((int)intersectionVoisine.getValue(), coutTroncon1);
				assertEquals((int)intersectionVoisine.getKey().getX(), 10);
				assertEquals((int)intersectionVoisine.getKey().getY(), 10);
			}
			else if (intersectionVoisine.getKey().getId() == 94){
				assertEquals((int)intersectionVoisine.getValue(), coutTroncon2);
				assertEquals((int)intersectionVoisine.getKey().getX(), 19);
				assertEquals((int)intersectionVoisine.getKey().getY(), 83);
			}
			else{
				fail("L'intersection trouvée n'est pas censé exister.");
			}
		}		
	}
}
