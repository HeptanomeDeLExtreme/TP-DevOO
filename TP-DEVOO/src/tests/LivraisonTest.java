/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modele.Client;
import modele.Dijkstra;
import modele.FenetreTemporelle;
import modele.GraphePondere;
import modele.Horaire;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class LivraisonTest {

	Horaire debut;
	
	Horaire fin;
	
	Client client;
	
	Intersection adresse; 
	
	FenetreTemporelle fenetre;
	
	Livraison livraison;
	
	Intersection destination1;
	
	Intersection destination2;
	
	Troncon troncon1;
	
	Troncon troncon2;
	
	int[] tableauPi, tableauD;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		adresse = new Intersection (5,15,96);
		destination1 = new Intersection(16,85,96);
		destination2 = new Intersection(6,74,12);
		
		String nomRue1 = "h2";
		float longueur1 = (float) 100.3;
		float vitesse1 = (float) 20.7;
		troncon1 = new Troncon(nomRue1, vitesse1, longueur1, adresse, destination1);
		adresse.ajouteTronconSortant(troncon1);
		
		String nomRue2 = "j2";
		float longueur2 = (float) 178.3;
		float vitesse2 = (float) 36.7;
		troncon2 = new Troncon(nomRue2, vitesse2, longueur2, adresse, destination2);
		adresse.ajouteTronconSortant(troncon2);
		
		debut = new Horaire (8,30,0);
		fin = new Horaire (9,50,0);
		client = new Client(52);
		fenetre = new FenetreTemporelle(debut, fin);
		
		livraison = new Livraison (1, client, adresse, fenetre);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.Livraison#equals(modele.Livraison)}.
	 */
	@Test
	public void testEqualsLivraison() {
		Horaire debut1 = new Horaire (8,30,0);
		Horaire fin1 = new Horaire (9,50,0);
		Client client1 = new Client(52);
		Intersection adresse1 = new Intersection (5,15,96);
		FenetreTemporelle fenetre1 = new FenetreTemporelle(debut1, fin1);
		
		Livraison livraison1 = new Livraison (1, client1, adresse1, fenetre1);
		
		Horaire debut2 = new Horaire (8,30,0);
		Horaire fin2 = new Horaire (9,50,0);
		Client client2 = new Client(52);
		FenetreTemporelle fenetre2 = new FenetreTemporelle(debut2, fin2);
		
		Livraison livraison2 = new Livraison (2, client2, adresse1, fenetre2);

		Intersection adresse2 = new Intersection (8,69,12);
		
		Livraison livraison3 = new Livraison (1, client1, adresse2, fenetre1);
		
		assertTrue(livraison.equals(livraison1));
		assertTrue(livraison.equals(livraison2));
		assertTrue(!livraison.equals(livraison3));
		
	}

	/**
	 * Test method for {@link modele.Livraison#Livraison(int, modele.Intersection)}.
	 */
	@Test
	public void testLivraisonIntIntersection() {
		Livraison livraison1 = new Livraison (2,adresse);
		assertEquals((int)livraison1.getId(), 2);
		assertTrue(livraison1.getAdresse().equals(adresse));
	}

	/**
	 * Test method for {@link modele.Livraison#Livraison(int, modele.Client, modele.Intersection, modele.FenetreTemporelle)}.
	 */
	@Test
	public void testLivraisonIntClientIntersectionFenetreTemporelle() {

		assertEquals((int)livraison.getId(), 1);
		assertEquals(livraison.getClient().getId(), client.getId());
		assertTrue(livraison.getFenetre().equals(fenetre));
		assertTrue(livraison.getAdresse().equals(adresse));	
	}

	/**
	 * Test method for {@link modele.Livraison#rechercherCout(java.util.Map, modele.Livraison)}.
	 */
	@Test
	public void testRechercherCout() {

		int[] tableauD = new int [4];
		tableauD[0] = 1;
		tableauD[1] = 3;
		tableauD[2] = 2;
		tableauD[3] = 7;
		livraison.setTableauD(tableauD);
		Map<Integer, Intersection> mapIntersectionsGraphe = new HashMap<Integer, Intersection>();
		mapIntersectionsGraphe.put(0, adresse);
		mapIntersectionsGraphe.put(1, destination1);
		assertEquals(livraison.rechercherCout(mapIntersectionsGraphe, livraison), 1);
	}

	/**
	 * Test method for {@link modele.Livraison#rechercherTroncons(java.util.Map, modele.Livraison)}.
	 */
	@Test
	public void testRechercherTroncons() {
		Map<Integer, Intersection> mapIntersectionsGraphe = new HashMap<Integer, Intersection>();
		mapIntersectionsGraphe.put(0, adresse);
		mapIntersectionsGraphe.put(1, destination1);
		mapIntersectionsGraphe.put(2, destination2);
		
		List<Troncon> troncons = livraison.rechercherTroncons(mapIntersectionsGraphe, livraison);
		
		for(Troncon troncon : troncons){
			if (troncon.getNomDeRue()=="h2"){
				assertEquals(troncon.getLongueur(), 100.3, 0.1);
				assertEquals(troncon.getVitesseMoyenne(), 20.7, 0.1);
				assertTrue(troncon.getDestination().equals(destination1));
				assertTrue(troncon.getOrigine().equals(adresse));			
			}
			else if (troncon.getNomDeRue()=="j2"){
				assertEquals(troncon.getLongueur(), 178.3, 0.1);
				assertEquals(troncon.getVitesseMoyenne(), 38.7, 0.1);
				assertTrue(troncon.getDestination().equals(destination2));
				assertTrue(troncon.getOrigine().equals(adresse));			
			}
			else{
				fail("Le troncon trouvé ne devrai pas exister");
			}
			
		}
		
	}

	/**
	 * Test method for {@link modele.Livraison#calculerPlusCourtsChemins(modele.GraphePondere)}.
	 */
	@Test
	public void testCalculerPlusCourtsChemins() {
		Set<Intersection> intersections = new HashSet<Intersection>();
		intersections.add(adresse);
		intersections.add(destination1);
		intersections.add(destination2);
		Plan plan = new Plan(intersections);
		GraphePondere graphePondere = new GraphePondere(plan);
		Map<Integer, Intersection> mapCorrespondancePlan = graphePondere.getMapCorrespondance();
		
		int[] clefs = new int[6];
		clefs[0] = getKeyByValue(mapCorrespondancePlan, adresse);
		clefs[1] = getKeyByValue(mapCorrespondancePlan, destination1);
		clefs[2] = getKeyByValue(mapCorrespondancePlan, destination2);
		
		int[][] resultat = Dijkstra.dijkstra(graphePondere, clefs[0]);
		int[] dist = resultat[0];
		int[] pred = resultat[1];
		
		
		
		Assert.assertEquals(dist[clefs[0]], 0);
		Assert.assertEquals(pred[clefs[0]], -1);
		Assert.assertEquals(pred[clefs[1]], clefs[0]);
		Assert.assertEquals(dist[clefs[1]], 4);
		Assert.assertEquals(pred[clefs[2]], clefs[0]);
		Assert.assertEquals(dist[clefs[2]], 4);
		
	}
	
	public Integer getKeyByValue(Map<Integer, Intersection> map, Intersection value) {
		Integer resultat = null;
		for (Integer compteur = 0; compteur < map.size(); compteur++) {
			if (value == map.get(compteur)) {
				resultat = compteur;
			}
		}
		return resultat;
	}

}
