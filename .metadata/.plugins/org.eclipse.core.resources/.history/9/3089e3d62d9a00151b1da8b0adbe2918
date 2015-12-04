/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * @author marion
 *
 */
public class DeserialiseurPlanXMLTest {

	private Plan plan;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		plan = new Plan();
		plan.setIntersections(new HashSet<Intersection>());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * A tester avec le fichier planTest
	 * Test method for {@link xml.DeserialiseurPlanXML#charger(modele.Plan)}.
	 * @throws ExceptionXML 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testCharger() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
		
		DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planTest.xml"));
		Set<Intersection> intersections = this.plan.getIntersections();
		
		for (Intersection intersection : intersections){
			
			if (intersection.getId()==0){
				
				assertEquals((int)intersection.getX(), 63);
				assertEquals((int)intersection.getY(), 100);
				
				Set<Troncon> troncons = intersection.getTronconsSortant();
				
				for(Troncon troncon : troncons){
					if(troncon.getDestination().getId()==1){
						assertEquals(troncon.getNomDeRue(), "v0");
						assertEquals((float)troncon.getLongueur(), 602.1, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 3.9, 0.1);
					}
					else if(troncon.getDestination().getId()==10){
						assertEquals(troncon.getNomDeRue(), "h0");
						assertEquals((float)troncon.getLongueur(), 729.0, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.2, 0.1);	
					}	
					else{
						assertTrue(false);
					}
				}
			}
			else if(intersection.getId()==2){
				
				assertEquals((int)intersection.getX(), 96);
				assertEquals((int)intersection.getY(), 52);
				
				Set<Troncon> troncons = intersection.getTronconsSortant();
				
				for(Troncon troncon : troncons){
					if(troncon.getDestination().getId()==10){
						assertEquals(troncon.getNomDeRue(), "v0");
						assertEquals((float)troncon.getLongueur(), 602.1, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.1, 0.1);
					}
					else if(troncon.getDestination().getId()==1){
						assertEquals(troncon.getNomDeRue(), "m0");
						assertEquals((float)troncon.getLongueur(), 627.5, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 3.8, 0.1);	
					}	
					else if(troncon.getDestination().getId()==11){
						assertEquals(troncon.getNomDeRue(), "h1");
						assertEquals((float)troncon.getLongueur(), 600.0, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.0, 0.1);	
					}	
					else{
						assertTrue(false);
					}
				}
				
			}
			else if(intersection.getId()==10){
				
				assertEquals((int)intersection.getX(), 99);
				assertEquals((int)intersection.getY(), 42);
				
				Set<Troncon> troncons = intersection.getTronconsSortant();
				
				for(Troncon troncon : troncons){
					if(troncon.getDestination().getId()==0){
						assertEquals(troncon.getNomDeRue(), "j0");
						assertEquals((float)troncon.getLongueur(), 602.1, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.1, 0.1);
					}	
					else if(troncon.getDestination().getId()==11){
						assertEquals(troncon.getNomDeRue(), "h1");
						assertEquals((float)troncon.getLongueur(), 600.0, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.0, 0.1);	
					}	
					else{
						assertTrue(false);
					}
				}
				
			}
			else if(intersection.getId()==11){
				
				assertEquals((int)intersection.getX(), 46);
				assertEquals((int)intersection.getY(), 120);
				
				Set<Troncon> troncons = intersection.getTronconsSortant();
				
				for(Troncon troncon : troncons){
					if(troncon.getDestination().getId()==10){
						assertEquals(troncon.getNomDeRue(), "l0");
						assertEquals((float)troncon.getLongueur(), 602.1, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.1, 0.1);
					}
					else if(troncon.getDestination().getId()==2){
						assertEquals(troncon.getNomDeRue(), "v0");
						assertEquals((float)troncon.getLongueur(), 627.5, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 3.8, 0.1);	
					}	
					else if(troncon.getDestination().getId()==1){
						assertEquals(troncon.getNomDeRue(), "h1");
						assertEquals((float)troncon.getLongueur(), 600.0, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.0, 0.1);	
					}	
					else{
						assertTrue(false);
					}
				}
				
			}
			else if(intersection.getId()==1){
				
				assertEquals((int)intersection.getX(), 88);
				assertEquals((int)intersection.getY(), 171);
				
				Set<Troncon> troncons = intersection.getTronconsSortant();
				
				for(Troncon troncon : troncons){
					if(troncon.getDestination().getId()==0){
						assertEquals(troncon.getNomDeRue(), "v0");
						assertEquals((float)troncon.getLongueur(), 602.1, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.1, 0.1);
					}
					else if(troncon.getDestination().getId()==2){
						assertEquals(troncon.getNomDeRue(), "c0");
						assertEquals((float)troncon.getLongueur(), 627.5, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 3.8, 0.1);	
					}	
					else if(troncon.getDestination().getId()==11){
						assertEquals(troncon.getNomDeRue(), "h1");
						assertEquals((float)troncon.getLongueur(), 600.0, 0.1);
						assertEquals((float)troncon.getVitesseMoyenne(), 4.0, 0.1);	
					}	
					else{
						assertTrue(false);
					}
				}
				
			}
			else{
				assertTrue(false);
			}
				
		}

	}
	@Test
	public void testErreurBalise() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planBaliseTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Déclaration de l'élément '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' introuvable."));
		}
		
	}
	
	@Test
	public void testErreurAttibuts() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planAttributTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("L'attribut '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' n'est pas autorisé dans l'élément '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("'."));
		}
	}
	
	@Test
	public void testErreurNoeudManquant() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planNoeudManquantTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'Reseau' n'est pas complet. L'un des éléments '{Noeud}' est attendu."));
		}
	}
	
	@Test
	public void testErreurTronconManquant() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planTronconManquantTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'Noeud' n'est pas complet. L'un des éléments '{LeTronconSortant}' est attendu."));
		}
	}
	
	@Test
	public void testErreurDeuxTroncons() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planDeuxTronconsTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Valeur unique en double ["));
			assertThat(e.getMessage(), CoreMatchers.containsString("] déclarée pour la contrainte d'identité de l'élément "));
			assertThat(e.getMessage(), CoreMatchers.containsString("Noeud"));
		}
	}
	
	@Test
	public void testUniqueId() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planUniqueIdTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Valeur unique en double ["));
			assertThat(e.getMessage(), CoreMatchers.containsString("] déclarée pour la contrainte d'identité de l'élément "));
			assertThat(e.getMessage(), CoreMatchers.containsString("Reseau"));
		}
	}
	
	@Test
	public void testDestinationAbsente() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planDestinationAbsenteTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("La clé 'referenceTroncon' ayant la valeur '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' est introuvable pour la contrainte d'identité de l'élément 'Reseau'."));
		}
	}
	
	@Test
	public void testNomRue() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planNomRueTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("La valeur '', ayant pour longueur '0', n'est pas un facet valide par rapport à minLength '1' pour le type 'nonEmptyString'."));
		}
	}
	
	@Test
	public void testVitessePositive() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planVitessePositiveTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("La valeur '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' n'est pas un facet valide par rapport au modèle '\\d+(,\\d+)?' pour le type 'positive-decimal'."));
		}
	}
	
	@Test
	public void testLongueurPositive() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planLongueurPositiveTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("La valeur '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' n'est pas un facet valide par rapport au modèle '\\d+(,\\d+)?' pour le type 'positive-decimal'."));
		}
	}
	
	@Test
	public void testBoucle() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planBoucleTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Un tronçon ne peut avoir la même intersection comme origine et destination."));
		}
	}
	
	@Test
	public void testCoordonneesUnique() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planCoordonneesUniqueTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("Valeur unique en double ["));
			assertThat(e.getMessage(), CoreMatchers.containsString("] déclarée pour la contrainte d'identité de l'élément "));
			assertThat(e.getMessage(), CoreMatchers.containsString("Reseau"));
		}
	}
	
	@Test
	public void testIntersectionPositive() {
		try {
			DeserialiseurPlanXML.charger(this.plan, new File("src/tests/files/planIntersectionPositiveTest.xml"));
			fail("Devrait avoir lancé XMLException");
		} catch (ParserConfigurationException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (SAXException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Devrait avoir lancé XMLException");
			e.printStackTrace();
		} catch (ExceptionXML e) {
			assertThat(e.getMessage(), CoreMatchers.containsString("La valeur '"));
			assertThat(e.getMessage(), CoreMatchers.containsString("' n'est pas un facet valide par rapport à minInclusive '0' pour le type 'nonNegativeInteger'."));
		}
	}
}