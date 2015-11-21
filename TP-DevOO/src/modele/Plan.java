package modele;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * 
 */
public class Plan {

    @Override
	public String toString() {
		return "Plan [intersections=" + intersections + "]";
	}

	/**
     * Default constructor
     */
    public Plan() {
    }

    /**
     * 
     */
    protected Set<Intersection> intersections;

    /**
     * 
     */
    protected Plan singleton;

    /**
     * @param fichier
     */
    public Plan(final String fichier) {
    	try {
    		intersections = new HashSet<Intersection>();
    		DeserialiseurPlanXML.charger(this);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			System.out.println("Exception constructeur plan");			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
    
    /**
     * Ajoute une intersection au plan
     * @param intersection Intersection à ajouter
     */
    public void ajoute(Intersection intersection) {
    	intersections.add(intersection);
    }

	public Intersection getIntersection(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
    

}