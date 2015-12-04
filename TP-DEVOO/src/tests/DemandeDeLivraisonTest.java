package tests;

import static org.junit.Assert.*;

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
		
		// Test du constructeur sans paramètres
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
		
		// Test du constructeur avec parametres
	}
	
	@Test
	public void chargerLivraisonTest() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		Plan plan = new Plan();
		DeserialiseurPlanXML.charger(plan, new File("src/tests/files/planTestOC.xml"));
		Tournee tournee = new Tournee();
		DemandeDeLivraison ddl = new DemandeDeLivraison(tournee);
		DeserialiseurDemandeDeLivraisonXML.charger(ddl, plan, new File("src/tests/files/livraisonsTestOC1.xml"));
		
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
					
		// Toutes les fenetres sont presentes
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
		
		// Les livraisons dans les fenetres sont presentes
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
		
		// Solutions attendues
		List<FenetreTemporelle> listeFenetresAttenduesVide = new ArrayList<FenetreTemporelle>();
		
		// Aucune fenetres
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
		
		// Résultats attendue
		List<Livraison> listeLivraisonAttendues = ddl.getTournee().getLivraisonsEnOrdre();
		
		// Vérifier les livraisons en ordre  (voir tournée)
		List<Livraison> listeLivraisonsObtenu = ddl.getTournee().getLivraisonsEnOrdre();
		assertEquals(listeLivraisonAttendues, listeLivraisonsObtenu);
		
		// Véirifier les itinéraires en ordre (voir tournée)
		List<Itineraire> listeItineraireObtenu = ddl.getTournee().getItineraires();
		Intersection i0 = new Intersection (0,20,5);
		Intersection i1 = new Intersection (1,5,20);
		Intersection i2 = new Intersection (2,5,45);
		Intersection i3 = new Intersection (3,20,55);
		Intersection i4 = new Intersection (4,40,45);
		Intersection i5 = new Intersection (5,40,15);
		List<Intersection> listeItineraireAttendus = new ArrayList<Intersection>();
		listeItineraireAttendus.add(i0);
		listeItineraireAttendus.add(i1);
		listeItineraireAttendus.add(i2);
		listeItineraireAttendus.add(i3);
		listeItineraireAttendus.add(i4);
		listeItineraireAttendus.add(i5);
		
		assertEquals(listeItineraireObtenu, listeItineraireAttendus);
		
		// Vérifier le cout total de la solution
		
		// Vérifier la durée totale
		
		// Vérfiier l'heure d'arrivée et de livraison de chaque livraison
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void nettoieDemandeDeLivraisonTest() {
		// Créer une demande de livraison
		// Faire le calcul de la tournée (ie : avoir charger un
		// plan et une livraison)
		// Faire appel a nettoieDemandeDeLivraison
		// Vérifier qu'on obtient bien les parametres attendus
		// par le fait d'appeler la fonction
		
		// Creer une demande de livraison
		// Charger un plan
		// Charger une demande de livraison
		// Nettoyer
		// Verifier le nettoyage
		// Charger une autre demande de livraison
		// Nettoyer
		// Verifier le nettoyage
		fail("Not yet implemented"); // TODO
	}
	
	@Test 
	public void getKeyByValueTest() {
		// Charger un plan
		// Charger des livraisons
		// Créer une map<Integer, Livraison> en faisant appel à la fonction
		// compétente
		// Pour chaque livraison, faire getKeyByValue
		// Vérifier que c'est bien ce qu'on attend
		fail("Not yet implemented"); // TODO
	}
	
	
	@Test
	public void chercheTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void changementEffectueTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void ajouteLivraisonTest() {
		// Charger un plan
		// Charger des livraisons
		// Faire la tournée
		// Ajouter une livraison avec cette méthode
		// Vérifier que la livraison existe bien et ce
		// dans la même fenetre que la livraisonSuivante (je crois)
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void supprimeLivraisonTest() {
		// Charger un plan
		// Charger des livraisons
		// Faire la tournée
		// Supprimer une livraison avec cette méthode
		// Vérfifier que la livraison n'existe plus et ce
		// dans toutes les fenetres de livraisons qui existent
		// dans la ddl
		fail("Not yet implemented"); //TODO
	}
	
	@Test
	public void modifierTourneeTest() {
		fail("Not yet implemented"); //TODO
	}
}