package tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.ExceptionXML;

import modele.Client;
import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

public class testChargerDemandeDeLivraisonXML {
	
	public static void main (String args[]) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
    	Intersection i1 = new Intersection(97, 10, 10);
    	Intersection i2 = new Intersection(37,30,20);
    	Intersection i3 = new Intersection(23,50,80);
    	Intersection i4 = new Intersection(88, 11, 15);
    	Intersection i5 = new Intersection(34,34,26);
    	Intersection i6 = new Intersection(87,55,80);
    	Intersection i7 = new Intersection(42, 19, 10);
    	Intersection i8 = new Intersection(72,33,20);
    	Intersection i9 = new Intersection(14,50,87);
    	Troncon t1 = new Troncon();
    	Troncon t2 = new Troncon();
    	Troncon t3 = new Troncon();
    	t1.setOrigine(i1);
    	t1.setDestination(i2);
    	t2.setOrigine(i2);
    	t2.setDestination(i3);
    	t3.setOrigine(i1);
    	t3.setDestination(i3);
    	Set<Troncon> tronconE1 = new HashSet<Troncon>();
    	Set<Troncon> tronconS1 = new HashSet<Troncon>();
    	tronconS1.add(t1);
    	tronconS1.add(t3);
    	Set<Troncon> tronconE2 = new HashSet<Troncon>();
    	tronconE2.add(t1);
    	Set<Troncon> tronconS2 = new HashSet<Troncon>();
    	tronconS2.add(t2);
    	Set<Troncon> tronconE3 = new HashSet<Troncon>();
    	tronconE3.add(t2);
    	tronconE3.add(t3);
    	Set<Troncon> tronconS3 = new HashSet<Troncon>();
    	i1.setTronconsEntrant(tronconE1);
    	i1.setTronconsSortant(tronconS1);
    	i2.setTronconsEntrant(tronconE2);
    	i2.setTronconsSortant(tronconS2);
    	i3.setTronconsEntrant(tronconE3);
    	i3.setTronconsSortant(tronconS3);
    	
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
    	Plan plan = new Plan(listeInter);
    	//Plan plan = new Plan();
    	
    	DemandeDeLivraison demandeDeLivraison = new DemandeDeLivraison();
    	demandeDeLivraison.setFenetres(new ArrayList<FenetreTemporelle>());
    	DeserialiseurDemandeDeLivraisonXML.charger(demandeDeLivraison, plan);
    	
    	System.out.println(demandeDeLivraison.toString());
	}
	
	public void testConstructeurLivraisonSansClient(){
		Intersection i1 = new Intersection(1, 10, 10);
		Livraison testLivraison = new Livraison (0, i1);
		//TODO
	}
	
	public void testConstructeurLivraison(){
		Client c = new Client (0);
		Intersection i1 = new Intersection(1, 10, 10);
		Livraison testLivraison = new Livraison (0, i1);
		//TODO
	}
	
	public void testAjoutEntrepot(){
		//TODO
	}
	
	public void testAjoutFenetreTemporelle(){
		//TODO
	}
	
	public void testRecupererIntersectionParId(){
		//TODO
	}
	
	public void testConstucteurClient(){
		//TODO
	}
	
	public void testConstucteurFenetreTemporelle(){
		//TODO
	}

	public void testCharger(){
		//TODO
	}
	
	public void testConstruireAPartirDeDOMXML(){
		//TODO
	}
	
	public void testCreeFenetreTemporelle(){
		//TODO
	}
	
	public void testCreeLivraison(){
		//TODO
	}
	
	public void testCreeEntrepot(){
		//TODO
	}

}
