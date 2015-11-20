package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import controleur.Controleur;

/**
 * 
 */
public class EcouteurBoutons implements ActionListener {

    /**
     * 
     */
    protected Controleur controleur;
    
    /**
     * Default constructor
     */
    public EcouteurBoutons() {
    }

    /**
     * @param controleur
     */
    public void EcouteurBoutons(Controleur controleur) {
        this.controleur = controleur;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());		
	}

}