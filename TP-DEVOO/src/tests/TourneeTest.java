/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import tsp.TSP1;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

import modele.Client;
import modele.Dijkstra;
import modele.FenetreTemporelle;
import modele.GraphePondere;
import modele.Horaire;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import modele.DemandeDeLivraison;
import modele.Troncon;

/**
 * @author marion
 *
 */
public class TourneeTest {

	Tournee tournee;
	
	Intersection destination1;
	
	Intersection destination2;
	
	Intersection adresse; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tournee = new Tournee();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.Tournee#nettoyer()}.
	 */
	@Test
	public void testNettoyer() {
		Horaire unHoraire = new Horaire(8, 30, 0);
		Tournee tournee = new Tournee();
		tournee.setCoutTotal(42);
		tournee.setDuree(unHoraire);
		tournee.setGraphePondere(null);
		tournee.setItineraires(null);
		tournee.setLivraisonsEnOrdre(null);
		tournee.setRetard(false);
		
		tournee.nettoyer();
		
		assertEquals(-1, tournee.getCoutTotal());
		assertEquals(null, tournee.getItineraires());
		assertEquals(unHoraire, tournee.getDuree());
		assertEquals(null, tournee.getGraphePondere());
		assertEquals(null, tournee.getItineraires());
		assertEquals(null, tournee.getLivraisonsEnOrdre());
		assertEquals(false, tournee.isRetard());
	}

	/**
	 * Test method for {@link modele.Tournee#modifierTournee(modele.Livraison, modele.Livraison)}.
	 * @throws ExceptionXML 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testModifierTournee() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(6);
		
		// Solutions attendues
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(8, 31, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(8, 31, 0), new Horaire(8, 32, 0));
		FenetreTemporelle fenetre3 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(10, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		listeFenetresAttendues.add(fenetre3);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(5, new Client(309), plan.recupererIntersectionParId(5), fenetre2);
		Livraison livraison3 = new Livraison(3, new Client(396), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison4 = new Livraison(15, new Client(309), plan.recupererIntersectionParId(0), fenetre3);
		Livraison livraison5 = new Livraison(4, new Client(396), plan.recupererIntersectionParId(4), fenetre3);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		Set<Livraison> setLiv3 = new HashSet<Livraison>();
		setLiv3.add(livraison4);
		setLiv3.add(livraison5);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		fenetre3.setLivraisons(setLiv3);
		
		ddl.calculerTournee(plan);
		
		List<Livraison> livraisons = tournee.getLivraisonsEnOrdre();
		
		Livraison l1 = livraisons.get(1);
		Livraison l2 = livraisons.get(2);
		tournee.modifierTournee(l1, l2);
		assertTrue(l1.getFenetre().equals(fenetre1));
		assertTrue(l2.getFenetre().equals(fenetre2));
	}

	/**
	 * Test method for {@link modele.Tournee#supprimeLivraison(modele.Livraison)}.
	 * @throws ExceptionXML 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testSupprimeLivraison() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(6);
		
		// Solutions attendues
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(8, 31, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(8, 31, 0), new Horaire(8, 32, 0));
		FenetreTemporelle fenetre3 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(10, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		listeFenetresAttendues.add(fenetre3);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(5, new Client(309), plan.recupererIntersectionParId(5), fenetre2);
		Livraison livraison3 = new Livraison(3, new Client(396), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison4 = new Livraison(15, new Client(309), plan.recupererIntersectionParId(0), fenetre3);
		Livraison livraison5 = new Livraison(4, new Client(396), plan.recupererIntersectionParId(4), fenetre3);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		Set<Livraison> setLiv3 = new HashSet<Livraison>();
		setLiv3.add(livraison4);
		setLiv3.add(livraison5);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		fenetre3.setLivraisons(setLiv3);
		
		ddl.calculerTournee(plan);
		
		tournee.supprimeLivraison(livraison1);
		assertEquals(tournee.getLivraisonsEnOrdre().size(), 6);
		for (Livraison livraison : tournee.getLivraisonsEnOrdre()){
			if(livraison.equals(livraison1)){
				fail();
			}
		}

	}

	/**
	 * Test method for {@link modele.Tournee#ajouteLivraison(modele.Livraison, modele.Intersection)}.
	 * @throws ExceptionXML 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testAjouteLivraison() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(6);
		
		// Solutions attendues
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(8, 31, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(8, 31, 0), new Horaire(8, 32, 0));
		FenetreTemporelle fenetre3 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(10, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		listeFenetresAttendues.add(fenetre3);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(5, new Client(309), plan.recupererIntersectionParId(5), fenetre2);
		Livraison livraison3 = new Livraison(3, new Client(396), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison4 = new Livraison(15, new Client(309), plan.recupererIntersectionParId(0), fenetre3);
		Livraison livraison5 = new Livraison(4, new Client(396), plan.recupererIntersectionParId(4), fenetre3);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		Set<Livraison> setLiv3 = new HashSet<Livraison>();
		setLiv3.add(livraison4);
		setLiv3.add(livraison5);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		fenetre3.setLivraisons(setLiv3);
		
		ddl.calculerTournee(plan);
		
		tournee.supprimeLivraison(livraison2);
		tournee.ajouteLivraison(livraison3, plan.recupererIntersectionParId(5));
		
		assertTrue(tournee.getLivraisonsEnOrdre().get(2).equals(livraison2));
		
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
