package vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controleur.Controleur;

/**
 * Gere le cas des saisies clavier.
 */
public class EcouteurClavier extends KeyAdapter {

    /**
     * Le controleur avec lequel interagir.
     */
    private Controleur controleur;

    /**
     * Constructeur par defaut.
     * 
     * @param controleur
     *            Le constructeur avec lequel interagir.
     */
    public EcouteurClavier(Controleur controleur) {
	this.controleur = controleur;
    }

    @Override
    /**
     * Delegue le traitement au controleur.
     */
    public void keyPressed(KeyEvent e) {
	// Methode appelee par l'ecouteur de clavier a chaque fois qu'une touche
	// est frappee
	controleur.caractereSaisi(e.getKeyCode());
    }

}