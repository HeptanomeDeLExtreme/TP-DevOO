package modele;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * Represente le plan de circulation sur lequel on travaille.
 */
public class Plan {

    /**
     * Intersections du plan
     */
    protected Set<Intersection> intersections;

    public Plan(Set<Intersection> listeInter) {
	this.intersections = listeInter;
    }

    public Plan() {
	intersections = new HashSet<Intersection>();
    }

    public Set<Intersection> getIntersections() {
	return this.intersections;
    }

    public int getLargeur() {
	int max = 0;
	for (Intersection inter : this.intersections) {
	    if (inter.getX() > max) {
		max = inter.getX();
	    }
	}
	return max;
    }

    public int getHauteur() {
	int max = 0;
	for (Intersection inter : this.intersections) {
	    if (inter.getY() > max) {
		max = inter.getY();
	    }
	}
	return max;
    }

    public void setIntersections(Set<Intersection> intersections) {
	this.intersections = intersections;
    }

    public void nettoiePlan() {
	intersections = new HashSet<Intersection>();
    }

    /**
     * Charge le plan a partir d'un fichier XML
     */
    public void chargerPlan() {
	try {
	    intersections = new HashSet<Intersection>();
	    DeserialiseurPlanXML.charger(this);
	}

	catch (ParserConfigurationException | SAXException | IOException
		| ExceptionXML e) {

	}
    }

    @Override
    public String toString() {
	return "Plan [intersections=" + intersections + "]";
    }

    /**
     * Permet de récuperer une intersection avec son id
     * 
     * @param int id
     * @return Intersection noeud
     */
    public Intersection recupererIntersectionParId(int id) {
	Iterator<Intersection> iterateur = intersections.iterator();
	Intersection noeud = iterateur.next();

	while (iterateur.hasNext() && noeud.getId() != id) {
	    noeud = iterateur.next();
	}

	if (noeud.id == id) {
	    return noeud;
	}

	else {
	    return null;
	}

    }

    /**
     * Ajoute une intersection au plan
     * 
     * @param intersection
     *            Intersection à ajouter
     */
    public void ajoute(Intersection intersection) {
	if (intersection != null)
	    intersections.add(intersection);
	else
	    System.out.println("Erreur : intersection inexistante");
    }

    public Intersection cherche(Point p, float echelleX, float echelleY) {
	if (intersections != null) {
	    Iterator<Intersection> it = intersections.iterator();
	    while (it.hasNext()) {
		Intersection inter = it.next();
		if (inter.contient(p, echelleX, echelleY)) {
		    return inter;
		}
	    }
	}
	return null;
    }

}