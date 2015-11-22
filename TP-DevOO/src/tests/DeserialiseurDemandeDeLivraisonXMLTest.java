/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

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
		this.demandeDeLivraison = new DemandeDeLivraison();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test à effectuer avec le fichier livraison10*10-2.xml
	 * Test method for {@link xml.DeserialiseurDemandeDeLivraisonXML#charger(modele.DemandeDeLivraison, modele.Plan)}.
	 */
	@Test
	public void testChargerBase() {
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
    	
    	this.demandeDeLivraison.chargerLivraison(plan);
		
		testFile();
		
		Livraison entrepot = this.demandeDeLivraison.getEntrepot();
		assertEquals((int)entrepot.getAdresse().getId(), 14);
		
		testPremierElement();
		testDernierElement();

	}

	private void testFile() {
		// TODO Auto-generated method stub
		
	}
	
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
}
