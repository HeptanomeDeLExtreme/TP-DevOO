/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import modele.Horaire;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marion
 *
 */
public class HoraireTest {
	
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
	 * Test method for {@link modele.Horaire#Horaire(int, int, int)}.
	 */
	@Test
	public void testHoraireIntIntInt() {
		Horaire horaire = new Horaire (8,30,0);
		assertEquals(horaire.getHeure(),8);
		assertEquals(horaire.getMinute(),30);
		assertEquals(horaire.getSeconde(),0);
	}

	/**
	 * Test method for {@link modele.Horaire#Horaire(float)}.
	 */
	@Test
	public void testHoraireFloat() {
		Horaire horaire = new Horaire (30306);
		assertEquals(horaire.getHeure(),8);
		assertEquals(horaire.getMinute(),25);
		assertEquals(horaire.getSeconde(),6);
	}

	/**
	 * Test method for {@link modele.Horaire#soustraireHoraire(Horaire)}.
	 */
	@Test
	public void testSoustraireHoraire() {
		Horaire horaire = new Horaire (8,30,0);
		Horaire resultat1 = horaire.soustraireHoraire(new Horaire(1, 20, 6));
		Horaire resultat2 = horaire.soustraireHoraire(new Horaire(1, 42, 0));
		Horaire resultat3 = horaire.soustraireHoraire(new Horaire(1, 32, 23));
		
		assertEquals(resultat1.getHeure(), 7);
		assertEquals(resultat1.getMinute(), 9);
		assertEquals(resultat1.getSeconde(), 54);
		
		assertEquals(resultat2.getHeure(), 6);
		assertEquals(resultat2.getMinute(), 48);
		assertEquals(resultat2.getSeconde(), 0);
		
		assertEquals(resultat3.getHeure(), 6);
		assertEquals(resultat3.getMinute(), 57);
		assertEquals(resultat3.getSeconde(), 37);
		
		
		
		
	}
	
	/**
	 * Test method for {@link modele.Horaire#horaireToDuree()}.
	 */
	@Test
	public void testHoraireToDuree() {
		Horaire horaire = new Horaire (8,30,0);
		assertEquals(horaire.horaireToDuree(), 30600, 0.1);
		}

	/**
	 * Test method for {@link modele.Horaire#additionnerHoraire(modele.Horaire)}.
	 */
	@Test
	public void testAdditionnerHoraire() {
		Horaire horaire = new Horaire (8,30,0);
		horaire = horaire.additionnerHoraire(new Horaire (1,40,6));
		assertEquals(horaire.getHeure(), 10);
		assertEquals(horaire.getMinute(), 10);
		assertEquals(horaire.getSeconde(), 6);
	}

	/**
	 * Test method for {@link modele.Horaire#isInFenetreTemporelle(modele.Horaire, modele.Horaire)}.
	 */
	@Test
	public void testIsInFenetreTemporelle() {
		Horaire horaire = new Horaire (8,30,0);
		assertTrue(horaire.isInFenetreTemporelle(new Horaire(7,50,30), new Horaire(8,40,0)));
		assertTrue(!horaire.isInFenetreTemporelle(new Horaire(7,50,30), new Horaire(8,20,0)));
		assertTrue(!horaire.isInFenetreTemporelle(new Horaire(9,50,30), new Horaire(10,20,0)));
		assertTrue(horaire.isInFenetreTemporelle(new Horaire(8,30,0), new Horaire(10,20,0)));
		assertTrue(horaire.isInFenetreTemporelle(new Horaire(7,30,0), new Horaire(8,30,0)));
		assertTrue(!horaire.isInFenetreTemporelle(new Horaire(8,30,0), new Horaire(6,30,0)));
	}

	/**
	 * Test method for {@link modele.Horaire#isInferieurA(modele.Horaire)}.
	 */
	@Test
	public void testIsInferieurA() {
		Horaire horaire = new Horaire (8,30,0);
		assertTrue(horaire.isInferieurA(new Horaire (9,0,0)));
		assertTrue(horaire.isInferieurA(new Horaire (8,30,0)));
		assertTrue(!horaire.isInferieurA(new Horaire (7,30,0)));
	}

}
