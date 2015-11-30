package vue;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * 
 */
public class VueTextuelle extends JScrollPane implements Observer{

	private String text;
	private Modele modele;
	private FenetreIHM fenetre;
	private JLabel label;

    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueTextuelle(Modele modele, FenetreIHM fenetreIHM) {
    	super();
    	this.fenetre = fenetreIHM;
    	this.modele.addObserver(this);
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
    	
    	Tournee tournee = modele.getTournee();
    	
    	if(tournee != null){
	    	List<Itineraire> listeItineraire = tournee.getItineraires();
	    	if(listeItineraire != null){
		    	for(Itineraire itineraire : listeItineraire){
		    		// Depart 
		    		Livraison depart = itineraire.getDepart();
		    		Intersection interDepart = depart.getAdresse();
		    		html +="Partir de x = "+interDepart.getX()+" y = "+interDepart.getY()+" id = "+interDepart.getId()+"<br>";
		    		
		    		// Troncons
		    		List<Troncon> listeTroncon = itineraire.getTroncons();
		    		for(Troncon tronc : listeTroncon){
		    			html += "Passer par "+tronc.getNomDeRue()+"<br>";
		    			html += "debug : "+tronc.getOrigine() + "<br>"+tronc.getDestination()+"<br>";
		    		}
		    		
		    		// Arrivee
		    		Livraison arrivee = itineraire.getArrivee();
		    		Intersection interArrivee = arrivee.getAdresse();
		    		html +="Partir de x = "+interArrivee.getX()+" y = "+interArrivee.getY()+"<br>";
		    		if(arrivee.getHeureArrivee() != null){
		    			html +="Heure d'arivée estimée : "+arrivee.getHeureArrivee()+"<br>";
		    		}
		    		if(arrivee.getHeureLivraison() != null){
		    			html +="Heure de livraison estimée : "+arrivee.getHeureLivraison()+"<br>";
		    		}
		    		FenetreTemporelle fenetreArrivee = arrivee.getFenetre();
		    		if(fenetreArrivee != null){
		    			html +="Fenetre : "+arrivee.getFenetre().getHeureDebut()+" "+arrivee.getFenetre().getHeureFin()+"<br><br>";
		    		}
		    	}
		        
		    	this.setText(html);
	    	}
    	}
    }




}