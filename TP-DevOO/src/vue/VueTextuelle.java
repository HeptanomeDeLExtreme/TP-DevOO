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

    /**
     * @param tournee 
     * @param fenetreIHM
     */
    public VueTextuelle(Tournee tournee, FenetreIHM fenetreIHM) {
    	super();
    	this.fenetre = fenetreIHM;
    	this.tournee = tournee;
    	this.tournee.addObserver(this);
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
        List<Troncon> listeTroncon = new ArrayList<Troncon>();
        for(Itineraire itineraire : listeItineraire){
        	System.out.println("1");
        	listeTroncon.addAll(itineraire.getTronçons());
        }
        

        this.setText("<html>");
        for(Troncon troncon : listeTroncon){
        	this.setText(this.getText()+"\n"+"Prenez :\n"+troncon.getNomDeRue()+"<br>");
        }
        this.setText(this.getText()+"</html>");
        
    }




}