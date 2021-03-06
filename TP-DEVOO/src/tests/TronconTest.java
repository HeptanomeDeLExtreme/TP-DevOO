/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import modele.Intersection;
import modele.Troncon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class TronconTest {

	Troncon troncon;
	
	Intersection origine;
	
	Intersection destination;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String nomRue = "h2";
		float longueur = (float) 100.3;
		float vitesse = (float) 20.7;
		origine = new Intersection(97,10,10);
		destination = new Intersection(94,13,20);
		troncon = new Troncon(nomRue, vitesse, longueur, origine, destination);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.Troncon#Troncon(java.lang.String, java.lang.Float, java.lang.Float, modele.Intersection, modele.Intersection)}.
	 */
	@Test
	public void testTronconStringFloatFloatIntersectionIntersection() {
		assertEquals(troncon.getNomDeRue(), "h2");
		assertEquals(troncon.getVitesseMoyenne(), 20.7, 0.1);
		assertEquals(troncon.getLongueur(), 100.3, 0.1);
		assertTrue(troncon.getDestination().equals(destination));
		assertTrue(troncon.getOrigine().equals(origine));
	}

	/**
	 * Test method for {@link modele.Troncon#getCout()}.
	 */
	@Test
	public void testGetCout() {
		assertEquals(troncon.getCout(), 4);
	}

}
