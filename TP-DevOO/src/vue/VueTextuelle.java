package vue;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import modele.Plan;
import modele.Tournee;

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
        // TODO implement here
    }




}