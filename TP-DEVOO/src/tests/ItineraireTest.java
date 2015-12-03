/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Troncon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class ItineraireTest {

	Itineraire itineraire;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		Intersection i1 = new Intersection (63, 96, 20);
		Intersection i2= new Intersection (97,10,10);
		Intersection i3= new Intersection (45,46,85);
		
		String nomRue1 = "h2";
		float longueur1 = (float) 100.3;
		float vitesse1 = (float) 20.7;
		Troncon troncon1 = new Troncon(nomRue1, vitesse1, longueur1, i1, i3);
		
		String nomRue2 = "d3";
		float longueur2 = (float) 45.3;
		float vitesse2 = (float) 53.7;
		Troncon troncon2 = new Troncon(nomRue2, vitesse2, longueur2, i3, i2);
		
		Livraison depart = new Livraison(4, i1);
		Livraison arrivee = new Livraison(3, i2);
		
		List <Troncon> troncons = new ArrayList<Troncon>();
		troncons.add(troncon1);
		troncons.add(troncon2);
		
		itineraire = new Itineraire(4, troncons, depart, arrivee);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.Itineraire#Itineraire(int, java.util.List, modele.Livraison, modele.Livraison)}.
	 */
	@Test
	public void testItineraireIntListOfTronconLivraisonLivraison() {

		Intersection i1 = new Intersection (63, 96, 20);
		Intersection i2= new Intersection (97,10,10);
		Intersection i3= new Intersection (45,46,85);
		
		String nomRue1 = "h2";
		float longueur1 = (float) 100.3;
		float vitesse1 = (float) 20.7;
		Troncon troncon1 = new Troncon(nomRue1, vitesse1, longueur1, i1, i3);
		
		String nomRue2 = "d3";
		float longueur2 = (float) 45.3;
		float vitesse2 = (float) 53.7;
		Troncon troncon2 = new Troncon(nomRue2, vitesse2, longueur2, i3, i2);
		
		Livraison depart = new Livraison(4, i1);
		Livraison arrivee = new Livraison(3, i2);
		
		List <Troncon> troncons = new ArrayList<Troncon>();
		troncons.add(troncon1);
		troncons.add(troncon2);
		
		assertEquals((int)itineraire.getCout(), 4);
		for (Troncon troncon : itineraire.getTroncons()){
			if(troncon.getNomDeRue() == "h2"){
				assertTrue(troncon.getDestination().equals(i3));
				assertTrue(troncon.getOrigine().equals(i1));
				assertEquals(troncon.getLongueur(), 100.3, 0.1);
				assertEquals(troncon.getVitesseMoyenne(), 20.7, 0.1);
			}
			else if(troncon.getNomDeRue() == "d3"){
				assertTrue(troncon.getDestination().equals(i2));
				assertTrue(troncon.getOrigine().equals(i3));
				assertEquals(troncon.getLongueur(), 45.3, 0.1);
				assertEquals(troncon.getVitesseMoyenne(), 53.7, 0.1);
			}
			else {
				fail("Le troncon trouvé n'est pas censé exister");
			}
		}
		assertEquals(itineraire.getArrivee().getId(), arrivee.getId());
		assertTrue(itineraire.getArrivee().getAdresse().equals(arrivee.getAdresse()));
		
		assertEquals(itineraire.getDepart().getId(), depart.getId());
		assertTrue(itineraire.getDepart().getAdresse().equals(depart.getAdresse()));
	
	}

}
