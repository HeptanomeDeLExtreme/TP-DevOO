package tests;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modele.Dijkstra;
import modele.GraphePondere;
import modele.Intersection;
import modele.Plan;
import modele.Troncon;

public class DijkstraTest {

    Plan plan;

    GraphePondere graphePondere;

    Map<Integer, Intersection> mapCorrespondancePlan;

    int[] clefs;

    Intersection i0 = new Intersection(0, 96, 20);
    Intersection i1 = new Intersection(1, 10, 10);
    Intersection i2 = new Intersection(2, 46, 85);
    Intersection i3 = new Intersection(3, 35, 89);
    Intersection i4 = new Intersection(4, 56, 21);
    Intersection i5 = new Intersection(5, 45, 45);

    @Before
    public void setUp() throws Exception {
	float longueur, vitesse;
	Troncon troncon;
	String nomRue;

	nomRue = "cassis";
	longueur = (float) 20;
	vitesse = (float) 2;
	troncon = new Troncon(nomRue, vitesse, longueur, i0, i1);
	i0.ajouteTronconSortant(troncon);
	i1.ajouteTronconEntrant(troncon);

	nomRue = "grenadine";
	longueur = (float) 60;
	vitesse = (float) 3;
	troncon = new Troncon(nomRue, vitesse, longueur, i0, i2);
	i0.ajouteTronconSortant(troncon);
	i2.ajouteTronconEntrant(troncon);

	nomRue = "bonbon";
	longueur = (float) 100;
	vitesse = (float) 1;
	troncon = new Troncon(nomRue, vitesse, longueur, i0, i5);
	i0.ajouteTronconSortant(troncon);
	i5.ajouteTronconEntrant(troncon);

	nomRue = "caramel";
	longueur = (float) 30;
	vitesse = (float) 3;
	troncon = new Troncon(nomRue, vitesse, longueur, i1, i0);
	i1.ajouteTronconSortant(troncon);
	i0.ajouteTronconEntrant(troncon);

	nomRue = "meringue";
	longueur = (float) 60;
	vitesse = (float) 3;
	troncon = new Troncon(nomRue, vitesse, longueur, i1, i2);
	i1.ajouteTronconSortant(troncon);
	i2.ajouteTronconEntrant(troncon);

	nomRue = "mangue";
	longueur = (float) 60;
	vitesse = (float) 2;
	troncon = new Troncon(nomRue, vitesse, longueur, i2, i0);
	i2.ajouteTronconSortant(troncon);
	i0.ajouteTronconEntrant(troncon);

	nomRue = "vanille";
	longueur = (float) 100;
	vitesse = (float) 1;
	troncon = new Troncon(nomRue, vitesse, longueur, i2, i1);
	i2.ajouteTronconSortant(troncon);
	i1.ajouteTronconEntrant(troncon);

	nomRue = "meringue";
	longueur = (float) 60;
	vitesse = (float) 6;
	troncon = new Troncon(nomRue, vitesse, longueur, i2, i3);
	i2.ajouteTronconSortant(troncon);
	i3.ajouteTronconEntrant(troncon);

	nomRue = "groseille";
	longueur = (float) 80;
	vitesse = (float) 2;
	troncon = new Troncon(nomRue, vitesse, longueur, i2, i4);
	i2.ajouteTronconSortant(troncon);
	i4.ajouteTronconEntrant(troncon);

	nomRue = "chocolat";
	longueur = (float) 70;
	vitesse = (float) 2;
	troncon = new Troncon(nomRue, vitesse, longueur, i2, i5);
	i2.ajouteTronconSortant(troncon);
	i5.ajouteTronconEntrant(troncon);

	nomRue = "soda";
	longueur = (float) 30;
	vitesse = (float) 6;
	troncon = new Troncon(nomRue, vitesse, longueur, i3, i2);
	i3.ajouteTronconSortant(troncon);
	i2.ajouteTronconEntrant(troncon);

	nomRue = "framboise";
	longueur = (float) 20;
	vitesse = (float) 2;
	troncon = new Troncon(nomRue, vitesse, longueur, i3, i4);
	i3.ajouteTronconSortant(troncon);
	i4.ajouteTronconEntrant(troncon);

	nomRue = "fraise";
	longueur = (float) 100;
	vitesse = (float) 1;
	troncon = new Troncon(nomRue, vitesse, longueur, i4, i3);
	i4.ajouteTronconSortant(troncon);
	i3.ajouteTronconEntrant(troncon);

	nomRue = "banane";
	longueur = (float) 50;
	vitesse = (float) 5;
	troncon = new Troncon(nomRue, vitesse, longueur, i4, i5);
	i4.ajouteTronconSortant(troncon);
	i5.ajouteTronconEntrant(troncon);

	nomRue = "nougat";
	longueur = (float) 40;
	vitesse = (float) 4;
	troncon = new Troncon(nomRue, vitesse, longueur, i5, i0);
	i5.ajouteTronconSortant(troncon);
	i0.ajouteTronconEntrant(troncon);

	nomRue = "pomme";
	longueur = (float) 30;
	vitesse = (float) 3;
	troncon = new Troncon(nomRue, vitesse, longueur, i5, i4);
	i5.ajouteTronconSortant(troncon);
	i4.ajouteTronconEntrant(troncon);

	Set<Intersection> intersections = new HashSet<Intersection>();
	intersections.add(i0);
	intersections.add(i1);
	intersections.add(i2);
	intersections.add(i3);
	intersections.add(i4);
	intersections.add(i5);

	plan = new Plan(intersections);

	graphePondere = new GraphePondere(plan);
	mapCorrespondancePlan = graphePondere.getMapCorrespondance();

	clefs = new int[6];
	clefs[0] = getKeyByValue(mapCorrespondancePlan, i0);
	clefs[1] = getKeyByValue(mapCorrespondancePlan, i1);
	clefs[2] = getKeyByValue(mapCorrespondancePlan, i2);
	clefs[3] = getKeyByValue(mapCorrespondancePlan, i3);
	clefs[4] = getKeyByValue(mapCorrespondancePlan, i4);
	clefs[5] = getKeyByValue(mapCorrespondancePlan, i5);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDijkstra1() {
	int[][] resultat = Dijkstra.dijkstra(graphePondere, clefs[1]);
	int[] dist = resultat[0];
	int[] pred = resultat[1];

	Assert.assertEquals(dist[clefs[0]], 10);
	Assert.assertEquals(pred[clefs[0]], clefs[1]);
	Assert.assertEquals(dist[clefs[1]], 0);
	Assert.assertEquals(pred[clefs[1]], -1);
	Assert.assertEquals(dist[clefs[2]], 20);
	Assert.assertEquals(pred[clefs[2]], clefs[1]);
	Assert.assertEquals(dist[clefs[3]], 30);
	Assert.assertEquals(pred[clefs[3]], clefs[2]);
	Assert.assertEquals(dist[clefs[4]], 40);
	Assert.assertEquals(pred[clefs[4]], clefs[3]);
	Assert.assertEquals(dist[clefs[5]], 50);
	Assert.assertEquals(pred[clefs[5]], clefs[4]);

    }

    @Test
    public void testDijkstra2() {
	int[][] resultat = Dijkstra.dijkstra(graphePondere, clefs[2]);
	int[] dist = resultat[0];
	int[] pred = resultat[1];

	Assert.assertEquals(dist[clefs[0]], 30);
	Assert.assertEquals(pred[clefs[0]], clefs[2]);
	Assert.assertEquals(dist[clefs[1]], 40);
	Assert.assertEquals(pred[clefs[1]], clefs[0]);
	Assert.assertEquals(dist[clefs[2]], 0);
	Assert.assertEquals(pred[clefs[2]], -1);
	Assert.assertEquals(dist[clefs[3]], 10);
	Assert.assertEquals(pred[clefs[3]], clefs[2]);
	Assert.assertEquals(dist[clefs[4]], 20);
	Assert.assertEquals(pred[clefs[4]], clefs[3]);
	Assert.assertEquals(dist[clefs[5]], 30);
	Assert.assertEquals(pred[clefs[5]], clefs[4]);

    }

    @Test
    public void testDijkstra3() {
	int[][] resultat = Dijkstra.dijkstra(graphePondere, clefs[3]);
	int[] dist = resultat[0];
	int[] pred = resultat[1];

	Assert.assertEquals(dist[clefs[0]], 30);
	Assert.assertEquals(pred[clefs[0]], clefs[5]);
	Assert.assertEquals(dist[clefs[1]], 40);
	Assert.assertEquals(pred[clefs[1]], clefs[0]);
	Assert.assertEquals(dist[clefs[2]], 5);
	Assert.assertEquals(pred[clefs[2]], clefs[3]);
	Assert.assertEquals(dist[clefs[3]], 0);
	Assert.assertEquals(pred[clefs[3]], -1);
	Assert.assertEquals(dist[clefs[4]], 10);
	Assert.assertEquals(pred[clefs[4]], clefs[3]);
	Assert.assertEquals(dist[clefs[5]], 20);
	Assert.assertEquals(pred[clefs[5]], clefs[4]);

    }

    public Integer getKeyByValue(Map<Integer, Intersection> map,
	    Intersection value) {
	Integer resultat = null;
	for (Integer compteur = 0; compteur < map.size(); compteur++) {
	    if (value == map.get(compteur)) {
		resultat = compteur;
	    }
	}
	return resultat;
    }

}
