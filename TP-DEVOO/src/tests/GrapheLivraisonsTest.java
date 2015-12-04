package tests;

import static org.junit.Assert.*;

import modele.GrapheLivraisons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrapheLivraisonsTest {

    GrapheLivraisons graphe;

    @Before
    public void setUp() throws Exception {
	int[][] couts = new int[3][3];
	couts[0][0] = 0;
	couts[1][1] = 0;
	couts[2][2] = 0;
	couts[0][1] = 4;
	couts[0][2] = 2;
	couts[1][0] = 0;
	couts[1][2] = 1;
	couts[2][0] = 4;
	couts[2][1] = 3;
	graphe = new GrapheLivraisons(3, couts);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGrapheLivraisonsIntIntArrayArray() {

	assertEquals(graphe.getNbSommets(), 3);
	assertEquals(graphe.getCout(0, 0), 0);
	assertEquals(graphe.getCout(1, 1), 0);
	assertEquals(graphe.getCout(2, 2), 0);
	assertEquals(graphe.getCout(0, 1), 4);
	assertEquals(graphe.getCout(0, 2), 2);
	assertEquals(graphe.getCout(1, 0), 0);
	assertEquals(graphe.getCout(1, 2), 1);
	assertEquals(graphe.getCout(2, 0), 4);
	assertEquals(graphe.getCout(2, 1), 3);

    }

    @Test
    public void testEstArc() {
	assertTrue(!graphe.estArc(0, 0));
	assertTrue(graphe.estArc(2, 0));
	assertTrue(!graphe.estArc(1, 0));
	assertTrue(graphe.estArc(0, 2));
	assertTrue(graphe.estArc(1, 2));
    }

}
