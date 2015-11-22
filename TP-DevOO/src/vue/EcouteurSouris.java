package vue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.SwingUtilities;

import modele.Plan;

import controleur.Controleur;

/**
 * 
 */
public class EcouteurSouris extends MouseAdapter{

    /**
     * Default constructor
     */
    public EcouteurSouris() {
    }

    /**
     * @param controleur 
     * @param vueGraphique 
     * @param fenetreIHM
     */
    public EcouteurSouris(Controleur controleur, VueGraphique vueGraphique, FenetreIHM fenetreIHM) {
        this.controleur = controleur;
        this.vueGraphique = vueGraphique;
        this.fenetreIHM = fenetreIHM;
    }
    
    /**
     * 
     */
    protected Controleur controleur;

    /**
     * 
     */
    protected VueGraphique vueGraphique;

    /**
     * 
     */
    protected FenetreIHM fenetreIHM;

    
    @Override
	public void mouseClicked(MouseEvent evt) {
		Point p = coordonnees(evt);
		switch (evt.getButton()){
		case MouseEvent.BUTTON1: 
			if (p != null){
				controleur.clicGauche(p);
			}
			break;
		case MouseEvent.BUTTON3: 
			break;
		default:
		}
	}

	private Point coordonnees(MouseEvent evt) {
		MouseEvent e = SwingUtilities.convertMouseEvent(fenetreIHM, evt, vueGraphique);
		int x = Math.round((float)e.getY()/(float)vueGraphique.getEchelleY());
		int y = Math.round((float)e.getX()/(float)vueGraphique.getEchelleX());
		return new Point(y,x);
	}

}