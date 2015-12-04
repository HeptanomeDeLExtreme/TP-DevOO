package vue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.SwingUtilities;

import modele.Plan;

import controleur.Controleur;

/**
 * Gere les clics de souris.
 */
public class EcouteurSouris extends MouseAdapter {

    /**
     * Le controleur avec lequel interagir.
     */
    protected Controleur controleur;

    /**
     * La vue graphique sur laquelle les evenements ont lieu.
     */
    protected VueGraphique vueGraphique;

    /**
     * La fenetre utilisateur qui contient la vue graphique.
     */
    protected FenetreIHM fenetreIHM;

    /**
     * Constructeur par defaut.
     * 
     * @param controleur
     *            Le controleur avec lequel interagir.
     * @param vueGraphique
     *            La vue graphique sur laquelle les evenements surviennent.
     * @param fenetreIHM
     *            La fenetre utilisateur qui contient la vue graphique.
     */
    public EcouteurSouris(Controleur controleur, VueGraphique vueGraphique,
	    FenetreIHM fenetreIHM) {
	this.controleur = controleur;
	this.vueGraphique = vueGraphique;
	this.fenetreIHM = fenetreIHM;
    }

    @Override
    /**
     * Delegue selon le clic au controleur.
     */
    public void mouseClicked(MouseEvent evt) {
	Point p = coordonnees(evt);
	switch (evt.getButton()) {
	case MouseEvent.BUTTON1:
	    if (p != null) {
		controleur.clicGauche(p);
	    }
	    break;
	case MouseEvent.BUTTON3:
	    if (p != null) {
		controleur.clicDroit(p);
	    }
	    break;
	default:
	}
    }

    private Point coordonnees(MouseEvent evt) {
	MouseEvent e = SwingUtilities.convertMouseEvent(fenetreIHM, evt,
		vueGraphique);
	int x = Math.round((float) e.getY()
		/ (float) vueGraphique.getEchelleY());
	int y = Math.round((float) e.getX()
		/ (float) vueGraphique.getEchelleX());
	return new Point(y, x);
    }
}