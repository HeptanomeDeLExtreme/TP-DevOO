package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import modele.DemandeDeLivraison;
import modele.Livraison;
import modele.Tournee;
import modele.FenetreTemporelle;

import org.junit.Test;

import tsp.TSP1;

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
	public void chargerLivraisonTest() {
		fail("Not yet implemented"); // TODO
	}

}
