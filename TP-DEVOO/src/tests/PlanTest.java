/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import modele.Intersection;
import modele.Plan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlanTest {

    Plan plan;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	Intersection i1 = new Intersection(63, 96, 20);
	Intersection i2 = new Intersection(97, 10, 10);
	Intersection i3 = new Intersection(45, 46, 85);
	Set<Intersection> intersections = new HashSet<Intersection>();
	intersections.add(i1);
	intersections.add(i2);
	intersections.add(i3);

	plan = new Plan(intersections);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link modele.Plan#Plan(java.util.Set)}.
     */
    @Test
    public void testPlanSetOfIntersection() {

	Set<Intersection> intersectionsPlan = plan.getIntersections();

	for (Intersection intersection : intersectionsPlan) {
	    if (intersection.getId() == 63) {
		assertEquals((int) intersection.getX(), 96);
		assertEquals((int) intersection.getY(), 20);
	    } else if (intersection.getId() == 97) {
		assertEquals((int) intersection.getX(), 10);
		assertEquals((int) intersection.getY(), 10);
	    } else if (intersection.getId() == 45) {
		assertEquals((int) intersection.getX(), 46);
		assertEquals((int) intersection.getY(), 85);
	    } else {
		fail("L'intersection trouvée n'est pas censé exister.");
	    }
	}
    }

    /**
     * Test method for {@link modele.Plan#nettoiePlan()}.
     */
    @Test
    public void testNettoiePlan() {

	plan.nettoiePlan();

	Set<Intersection> intersectionsPlan = plan.getIntersections();
	assertTrue(intersectionsPlan.isEmpty());
    }

    /**
     * Test method for {@link modele.Plan#recupererIntersectionParId(int)}.
     */
    @Test
    public void testRecupererIntersectionParId() {

	Intersection intersection1 = plan.recupererIntersectionParId(97);
	assertEquals((int) intersection1.getId(), 97);
	assertEquals((int) intersection1.getX(), 10);
	assertEquals((int) intersection1.getY(), 10);

	Intersection intersection2 = plan.recupererIntersectionParId(102);
	assertEquals(intersection2, null);

    }

    /**
     * Test method for {@link modele.Plan#ajoute(modele.Intersection)}.
     */
    @Test
    public void testAjoute() {
	Intersection intersection = new Intersection(102, 49, 67);
	plan.ajoute(intersection);

	Intersection intersectionRecuperee = plan
		.recupererIntersectionParId(102);
	assertEquals((int) intersectionRecuperee.getId(), 102);
	assertEquals((int) intersectionRecuperee.getX(), 49);
	assertEquals((int) intersectionRecuperee.getY(), 67);
    }

    /**
     * Test method for {@link modele.Plan#getLargeur()}.
     */
    @Test
    public void testGetLargeur() {

	assertEquals(plan.getLargeur(), 96);
    }

    /**
     * Test method for {@link modele.Plan#getHauteur()}.
     */
    @Test
    public void testGetHauteur() {
	assertEquals(plan.getHauteur(), 85);
    }

    /**
     * Test method for {@link modele.Plan#cherche(java.awt.Point, float, float)}
     * .
     */
    @Test
    public void testCherche() {
	Intersection intersection1 = plan.cherche(new Point(96, 20), 1, 1);
	assertEquals((int) intersection1.getId(), 63);

	Intersection intersection2 = plan.cherche(new Point(10, 10), 1, 1);
	assertEquals((int) intersection2.getId(), 97);

	Intersection intersection3 = plan.cherche(new Point(46, 85), 1, 1);
	assertEquals((int) intersection3.getId(), 45);

	Intersection intersection4 = plan.cherche(new Point(90, 85), 1, 1);
	assertEquals(intersection4, null);

	Intersection intersection5 = plan.cherche(new Point(90, 25), 1, 1);
	assertEquals((int) intersection5.getId(), 63);

	Intersection intersection6 = plan.cherche(new Point(20, 20), 1, 1);
	assertEquals(intersection6, null);
    }
}
