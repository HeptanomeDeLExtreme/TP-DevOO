package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import modele.Client;
import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.GraphePondere;
import modele.Horaire;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;

import org.junit.Test;
import org.xml.sax.SAXException;

import tsp.TSP1;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

public class DemandeDeLivraisonTest {

	@Test
	public void ConstructeursTest() {
		
		Tournee tourneeAttendue = null;
		TSP1 tspAttendu = null;
		List<FenetreTemporelle> fenetresAttendues = null;
		Livraison entrepotAttendu = null;
		int nbLivraisonsAttendues = 0;
		
		DemandeDeLivraison ddl = new DemandeDeLivraison();
		Tournee tourneeObtenue = ddl.getTournee();
		TSP1 tspObtenu = ddl.getTsp();
		List<FenetreTemporelle> fenetresObtenues = ddl.getFenetres();
		Livraison entrepotObtenu = ddl.getEntrepot();
		int nbLivraisonsObtenues = ddl.getNbLivraisons();
		
		assertEquals(tourneeAttendue, tourneeObtenue);
		assertEquals(tspAttendu, tspObtenu);
		assertEquals(fenetresAttendues, fenetresObtenues);
		assertEquals(entrepotAttendu, entrepotObtenu);
		assertEquals(nbLivraisonsAttendues, nbLivraisonsObtenues);
	}
	
	@Test
	public void chargerLivraisonTest() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(10, 0, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(11, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(3, new Client(309), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison3 = new Livraison(5, new Client(396), plan.recupererIntersectionParId(5), fenetre2);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
					
		List<FenetreTemporelle> fenetres = ddl.getFenetres();
		int tailleListeAttendue = listeFenetresAttendues.size();
		int tailleListeObtenue = fenetres.size();
		assertEquals(tailleListeAttendue, tailleListeObtenue);
		
		boolean resultatFenetre1 = false;
		boolean resultatFenetre2 = false;
		for(FenetreTemporelle fenetre : fenetres) {
			
			if(fenetre.equals(fenetre1)) {
				resultatFenetre1 = true;
			}
			if(fenetre.equals(fenetre2)) {
				resultatFenetre2 = true;
			}
		}
		assertEquals(true, resultatFenetre1);
		assertEquals(true, resultatFenetre2);
		
		FenetreTemporelle fenetre1Obtenue = fenetres.get(0);
		FenetreTemporelle fenetre2Obtenue = fenetres.get(1);
		Set<Livraison> setLiv1Obtenu = fenetre1Obtenue.getLivraisons();
		Set<Livraison> setLiv2Obtenu = fenetre2Obtenue.getLivraisons();
		
		assertEquals(setLiv1.size(), setLiv1Obtenu.size());
		assertEquals(setLiv2.size(), setLiv2Obtenu.size());
		
		boolean resultatLiv1 = false;
		boolean resultatLiv2 = false;
		boolean resultatLiv3 = false;
		for(Livraison liv : setLiv1Obtenu) {
			if(liv.toString().equals(livraison1.toString())) {
				resultatLiv1 = true;
			}
		}
		for(Livraison liv : setLiv2Obtenu) {
			if(liv.toString().equals(livraison2.toString())) {
				resultatLiv2 = true;
			}
			if(liv.toString().equals(livraison3.toString())) {
				resultatLiv3 = true;
			}
		}
		
		assertEquals(true, resultatLiv1);
		assertEquals(true, resultatLiv2);
		assertEquals(true, resultatLiv3);
		
		Plan plan1 = new Plan();
		DeserialiseurPlanXML.charger(plan1, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee1 = new Tournee();
		DemandeDeLivraison ddl1 = new DemandeDeLivraison(tournee1);
		
		boolean resultat = false;
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(ddl1, plan1, new File("src/tests/files/livraisonHeureTest.xml"));
		} catch(ExceptionXML e) {
			resultat = true;
		}
		assertEquals(true, resultat);
		
		List<FenetreTemporelle> listeFenetresAttenduesVide = new ArrayList<FenetreTemporelle>();
		
		List<FenetreTemporelle> listeFenetreObtenue = ddl1.getFenetres();
		assertEquals(listeFenetresAttenduesVide.size(), listeFenetreObtenue.size());
	}
	
	@Test
	public void calculerTourneeTest() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(4);
		
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(10, 0, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(11, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(3, new Client(309), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison3 = new Livraison(5, new Client(396), plan.recupererIntersectionParId(5), fenetre2);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		
		ddl.calculerTournee(plan);

		List<Livraison> listeLivraisonAttendues = ddl.getTournee().getLivraisonsEnOrdre();
		
		List<Livraison> listeLivraisonsObtenu = ddl.getTournee().getLivraisonsEnOrdre();
		assertEquals(listeLivraisonAttendues, listeLivraisonsObtenu);
		
		List<Itineraire> listeItineraireObtenu = ddl.getTournee().getItineraires();
		Integer tableauIdInter[] = {2, 0, 1, 2, 3, 4, 5, 4, 5, 0, 2};
		boolean resultatBool = true;
		int compteur = 0;
		for(Itineraire iti : listeItineraireObtenu) {
			Integer resultat = tableauIdInter[compteur];
			if( (iti.getArrivee().getAdresse().getId()).equals(resultat) ) {} else {
				resultatBool = false;
			}
			compteur++;
		}
		
		assertEquals(1920, tournee.getCoutTotal());
		
		Horaire duree = new Horaire(1, 50, 50);
		float dureeFloat = duree.horaireToDuree();
		System.out.println("Duree : " + tournee.getDuree().horaireToDuree());
		boolean resultat = false;
		if(duree.equals(new Horaire(1, 50, 50))) {
			resultat = true;
		}
		assertEquals(true, resultat);
		
		Livraison liv1 = new Livraison();
		liv1.setHeureArrivee(new Horaire(8, 30, 40));
		liv1.setHeureLivraison(new Horaire(8, 30, 40));
		
		Livraison liv1Obtenu = listeLivraisonsObtenu.get(1);
		assertTrue(liv1Obtenu.getHeureArrivee().equals(liv1.getHeureArrivee()));
		assertTrue(liv1Obtenu.getHeureLivraison().equals(liv1.getHeureLivraison()));
	}
	
	@Test
	public void nettoieDemandeDeLivraisonTest() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(4);
		
		// Solutions attendues
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(10, 0, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(11, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(3, new Client(309), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison3 = new Livraison(5, new Client(396), plan.recupererIntersectionParId(5), fenetre2);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		
		ddl.calculerTournee(plan);
		
		ddl.nettoieDemandeDeLivraison();
		
		assertEquals(0, ddl.getFenetres().size());
		assertEquals(null, ddl.getEntrepot());
	}	
	
	@Test
	public void chercheTest() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		ddl.setNbLivraisons(4);
		
		// Solutions attendues
		FenetreTemporelle fenetre1 = new FenetreTemporelle(new Horaire(8, 30, 0), new Horaire(10, 0, 0));
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire(10, 0, 0), new Horaire(11, 30, 0));
		List<FenetreTemporelle> listeFenetresAttendues = new ArrayList<FenetreTemporelle>();
		listeFenetresAttendues.add(fenetre1);
		listeFenetresAttendues.add(fenetre2);
		Livraison livraison1 = new Livraison(1, new Client(631), plan.recupererIntersectionParId(1), fenetre1);
		Livraison livraison2 = new Livraison(3, new Client(309), plan.recupererIntersectionParId(3), fenetre2);
		Livraison livraison3 = new Livraison(5, new Client(396), plan.recupererIntersectionParId(5), fenetre2);
		Set<Livraison> setLiv1 = new HashSet<Livraison>();
		setLiv1.add(livraison1);
		Set<Livraison> setLiv2 = new HashSet<Livraison>();
		setLiv2.add(livraison2);
		setLiv2.add(livraison3);
		fenetre1.setLivraisons(setLiv1);
		fenetre2.setLivraisons(setLiv2);
		
		Livraison resultat = ddl.cherche(new Point(plan.recupererIntersectionParId(1).getX(), plan.recupererIntersectionParId(1).getY()), 1, 1);
		assertTrue(resultat.equals(livraison1));
	}
}