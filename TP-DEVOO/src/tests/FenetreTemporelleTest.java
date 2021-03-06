/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.FenetreTemporelle;
import modele.Horaire;

/**
 * @author marion
 *
 */
public class FenetreTemporelleTest {

	FenetreTemporelle fenetre;
	
	Horaire debut;
	
	Horaire fin;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		debut = new Horaire(8,30,0);
		fin = new Horaire(9,50,0);
		
		fenetre = new FenetreTemporelle(debut, fin);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link modele.FenetreTemporelle#FenetreTemporelle(modele.Horaire, modele.Horaire)}.
	 */
	@Test
	public void testFenetreTemporelleHoraireHoraire() {
		
		assertEquals(fenetre.getHeureDebut().getHeure(), debut.getHeure());
		assertEquals(fenetre.getHeureDebut().getMinute(), debut.getMinute());
		assertEquals(fenetre.getHeureDebut().getSeconde(), debut.getSeconde());
		
		assertEquals(fenetre.getHeureFin().getHeure(), fin.getHeure());
		assertEquals(fenetre.getHeureFin().getMinute(), fin.getMinute());
		assertEquals(fenetre.getHeureFin().getSeconde(), fin.getSeconde());

	}

	/**
	 * Test method for {@link modele.FenetreTemporelle#equals(modele.FenetreTemporelle)}.
	 */
	@Test
	public void testEqualsFenetreTemporelle() {

		FenetreTemporelle fenetre1 = new FenetreTemporelle(debut, fin);
		FenetreTemporelle fenetre2 = new FenetreTemporelle(new Horaire (7,30,0), new Horaire(9,50,0));
		FenetreTemporelle fenetre3 = new FenetreTemporelle(new Horaire (8,30,0), new Horaire(10,50,0));
		FenetreTemporelle fenetre4 = new FenetreTemporelle(new Horaire (8,35,0), new Horaire(9,55,0));
		
		FenetreTemporelle fenetre5 = new FenetreTemporelle(new Horaire (8,30,0), new Horaire(10,0,0));
		FenetreTemporelle fenetre6 = new FenetreTemporelle(new Horaire (8,30,0), new Horaire(10,0,0));
		
		assertTrue(fenetre.equals(fenetre1));
		assertTrue(!fenetre.equals(fenetre2));
		assertTrue(!fenetre.equals(fenetre3));
		assertTrue(!fenetre.equals(fenetre4));
	}

}
