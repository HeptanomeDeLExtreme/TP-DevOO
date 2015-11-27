/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.ExceptionXML;

/**
 * @author marion
 *
 */
public class DeserialiseurDemandeDeLivraisonXMLTest {

	private Plan plan;
	
	private DemandeDeLivraison demandeDeLivraison;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    	Intersection i1 = new Intersection(97, 10, 10);
    	Intersection i2 = new Intersection(37,30,20);
    	Intersection i3 = new Intersection(23,50,80);
    	Intersection i4 = new Intersection(88, 11, 15);
    	Intersection i5 = new Intersection(34,34,26);
    	Intersection i6 = new Intersection(87,55,80);
    	Intersection i7 = new Intersection(42, 19, 10);
    	Intersection i8 = new Intersection(72,33,20);
    	Intersection i9 = new Intersection(14,50,87);
    	
    	Set<Intersection> listeInter = new HashSet<Intersection>();
    	listeInter.add(i1);
    	listeInter.add(i2);
    	listeInter.add(i3);
    	listeInter.add(i4);
    	listeInter.add(i5);
    	listeInter.add(i6);
    	listeInter.add(i7);
    	listeInter.add(i8);
    	listeInter.add(i9);
    	
    	this.plan=new Plan(listeInter);
    	this.demandeDeLivraison = new DemandeDeLivraison();
    	demandeDeLivraison.setFenetres(new ArrayList<FenetreTemporelle>());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link xml.DeserialiseurDemandeDeLivraisonXML#charger(modele.DemandeDeLivraison, modele.Plan)}.
	 * @throws ExceptionXML 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testChargerBase() throws ParserConfigurationException, SAXException, IOException, ExceptionXML {
    	
		DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonTest.xml"));
		
		Livraison entrepot = this.demandeDeLivraison.getEntrepot();
		assertEquals((int)entrepot.getAdresse().getId(), 14);
		
		testPremierElement();
		testDernierElement();

	}

	/**
	 * Teste le premier élément de la demande de livraison
	 */
	private void testPremierElement() {
		
		List<FenetreTemporelle> fenetres = this.demandeDeLivraison.getFenetres();
		Iterator<FenetreTemporelle> iterateurFenetre = fenetres.iterator();
		FenetreTemporelle fenetre = iterateurFenetre.next();
				
		assertEquals(fenetre.getHeureDebut().getHeure(), 8);
		assertEquals(fenetre.getHeureDebut().getMinute(), 0);
		assertEquals(fenetre.getHeureDebut().getSeconde(), 0);
		
		assertEquals(fenetre.getHeureFin().getHeure(), 9);
		assertEquals(fenetre.getHeureFin().getMinute(), 30);
		assertEquals(fenetre.getHeureFin().getSeconde(), 0);
		
		Set<Livraison> livraisons = fenetre.getLivraisons();
		
		for(Livraison livraison : livraisons){
			if(livraison.getAdresse().getId()==97){
				assertEquals((int)livraison.getClient().getId(), 329);
				assertEquals((int)livraison.getId(), 1);
			}
			else if(livraison.getAdresse().getId()==37){
				assertEquals((int)livraison.getClient().getId(), 41);
				assertEquals((int)livraison.getId(), 2);	
			}
			
			else if(livraison.getAdresse().getId()==23){
				assertEquals((int)livraison.getClient().getId(), 804);
				assertEquals((int)livraison.getId(), 3);
			}
			
			else{
				assertTrue(false);
			}
		}
	}
	
	/**
	 * Teste le dernier élément de la demande de livraison
	 */
	private void testDernierElement() {
		
		List<FenetreTemporelle> fenetres = this.demandeDeLivraison.getFenetres();
		Iterator<FenetreTemporelle> iterateurFenetre = fenetres.iterator();
		FenetreTemporelle fenetre = iterateurFenetre.next();
	    while(iterateurFenetre.hasNext()) {
	    	fenetre=iterateurFenetre.next();
	    }
				
		assertEquals(fenetre.getHeureDebut().getHeure(), 11);
		assertEquals(fenetre.getHeureDebut().getMinute(), 0);
		assertEquals(fenetre.getHeureDebut().getSeconde(), 0);
		
		assertEquals(fenetre.getHeureFin().getHeure(), 12);
		assertEquals(fenetre.getHeureFin().getMinute(), 30);
		assertEquals(fenetre.getHeureFin().getSeconde(), 0);
		
		Set<Livraison> livraisons = fenetre.getLivraisons();
		
		for(Livraison livraison : livraisons){
			if(livraison.getAdresse().getId()==42){
				assertEquals((int)livraison.getClient().getId(), 678);
				assertEquals((int)livraison.getId(), 1);
			}
			else if(livraison.getAdresse().getId()==72){
				assertEquals((int)livraison.getClient().getId(), 578);
				assertEquals((int)livraison.getId(), 2);	
			}
			
			else{
				assertTrue(false);
			}
		}		
	}
	
	@Test
	public void testErreurBalise() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonBaliseTest.xml"));
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
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonAttributTest.xml"));
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
	public void testErreurEntrepotManquant() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonEntrepotTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Contenu non valide trouvé à partir de l'élément 'PlagesHoraires'. L'une des valeurs '{Entrepot}' est attendue."));
		}
	}
	
	@Test
	public void testErreurPlagesTrop() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonPlagesTropTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Contenu non valide trouvé à partir de l'élément 'PlagesHoraires'. Aucun élément enfant n'est attendu à cet endroit."));
		}
	}
	
	@Test
	public void testErreurPlagesManquante() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonPlagesManquanteTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'JourneeType' n'est pas complet. L'un des éléments '{PlagesHoraires}' est attendu."));
		}
	}
	
	@Test
	public void testErreurPlageManquante() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonPlageManquanteTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'PlagesHoraires' n'est pas complet. L'un des éléments '{Plage}' est attendu."));

		}
	}
	
	@Test
	public void testErreurLivraisonsTrop() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonLivraisonsTropTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Contenu non valide trouvé à partir de l'élément 'Livraisons'. Aucun élément enfant n'est attendu à cet endroit."));
		}
	}
	
	@Test
	public void testErreurLivraisonsManquante() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonLivraisonsManquanteTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'Plage' n'est pas complet. L'un des éléments '{Livraisons}' est attendu."));
		}
	}
	
	@Test
	public void testErreurLivraisonManquante() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonLivraisonManquanteTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Le contenu de l'élément 'Livraisons' n'est pas complet. L'un des éléments '{Livraison}' est attendu."));

		}
	}
	
	@Test
	public void testUniciteId() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonUniciteIdTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Livraisons"));
		}
	}
	
	@Test
	public void testIntersectionAbsente() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonIntersectionTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("Toutes les livraisons de la demande de livraison doivent correspondre à une adresse du plan."));
		}
	}
	
	@Test
	public void testIntersectionEntrepotAbsente() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonIntersectionEntrepotTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("L'entrepot de la demande de livraison n'est pas dans le plan."));
		}
	}
	
	@Test
	public void testIntersectionEgales() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonAdresseTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("PlagesHoraires"));
		}
	}
	
	@Test
	public void testClientPositif() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonClientTest.xml"));
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
	
	@Test
	public void testIdPositif() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonIdTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("' n'est pas un facet valide par rapport à minInclusive '1' pour le type 'positiveInteger'."));

		}
	}
	
	@Test
	public void testCoherenceHeure() {
		try {
			DeserialiseurDemandeDeLivraisonXML.charger(this.demandeDeLivraison, this.plan, new File("src/tests/files/livraisonHeureTest.xml"));
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
			assertThat(e.getMessage(), CoreMatchers.containsString("La date de début de la fenêtre doit être strictement supérieure à la date de fin."));
		}
	}
}
