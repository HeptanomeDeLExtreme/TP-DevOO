package vue;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * 
 */
public class VueTextuelle extends JScrollPane implements Observer{

	private String text;
	private Tournee tournee;
	private FenetreIHM fenetre;
	private Plan plan;
	private JLabel label;

    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueTextuelle(Plan plan, Tournee tournee, FenetreIHM fenetreIHM) {
    	super();
    	this.fenetre = fenetreIHM;
    	this.tournee = tournee;
    	this.tournee.addObserver(this);
    	this.plan = plan;
    	this.plan.addObserver(this);
		setBorder(BorderFactory.createTitledBorder("Tournée :"));
//		this.setVerticalTextPosition(TOP);
//		this.setVerticalAlignment(TOP);
		
		label = new JLabel();
		this.setViewportView(label);
		
		fenetre.getContentPane().add(this);
		
    }
    
    protected void setText(String s){
    	this.label.setText(s);
    }
    
    protected void changeText(String s){
    	this.setText(s);
    }

    /**
     * @param observable 
     * @param objet
     */
    public void update(Observable observable, Object objet) {
    	String html = "<html>";
    	
    	if(tournee != null){
	    	List<Itineraire> listeItineraire = this.tournee.getItineraires();
	    	if(listeItineraire != null){
		    	for(Itineraire itineraire : listeItineraire){
		    		// Depart 
		    		Livraison depart = itineraire.getDepart();
		    		Intersection interDepart = depart.getAdresse();
		    		html +="Partir de x = "+interDepart.getX()+" y = "+interDepart.getY()+"<br>";
		    		
		    		// Troncons
		    		List<Troncon> listeTroncon = itineraire.getTroncons();
		    		for(Troncon tronc : listeTroncon){
		    			html += "Passer par "+tronc.getNomDeRue()+"<br>";
		    		}
		    		
		    		// Arrivee
		    		Livraison arrivee = itineraire.getArrivee();
		    		Intersection interArrivee = arrivee.getAdresse();
		    		html +="Partir de x = "+interArrivee.getX()+" y = "+interArrivee.getY()+"<br>";
		    		html +="Heure d'arivée estimée : "+arrivee.getHeureArrivee()+"<br>";
		    		html +="Fenetre : "+arrivee.getFenetre().getHeureDebut()+" "+arrivee.getFenetre().getHeureFin()+"<br><br>";
		    	}
		        
		    	this.setText(html);
	    	}
    	}
    }




}