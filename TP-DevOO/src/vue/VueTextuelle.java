package vue;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import modele.Intersection;
import modele.Itineraire;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * 
 */
public class VueTextuelle extends JLabel implements Observer{

	private String text;
	private Tournee tournee;
	private FenetreIHM fenetre;
	private Plan plan;

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
		this.setVerticalTextPosition(TOP);
		this.setVerticalAlignment(TOP);
		fenetre.getContentPane().add(this);
    }
    
    protected void changeText(String s){
    	this.setText(s);
    }

    /**
     * @param observable 
     * @param objet
     */
    public void update(Observable observable, Object objet) {
        List<Itineraire> listeItineraire = this.tournee.getItineraires();
        if(listeItineraire != null){
	        List<Troncon> listeTroncon = new ArrayList<Troncon>();
	        for(Itineraire itineraire : listeItineraire){
	        	System.out.println("1");
	        	listeTroncon.addAll(itineraire.getTroncons());
	        }
	        
	
	        this.setText("<html>");
	        if(listeTroncon != null){
		        for(Troncon troncon : listeTroncon){
		        	if(troncon != null){
		        		this.setText(this.getText()+"\n"+"Prenez :\n"+troncon.getNomDeRue()+"<br>");
		        	}
		        }
		        this.setText(this.getText()+"</html>");
	        }
    	}
        
    }




}