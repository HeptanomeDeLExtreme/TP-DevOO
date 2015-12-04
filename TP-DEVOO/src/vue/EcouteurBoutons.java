package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controleur.Controleur;

/**
 * Represente l'ecouteur de boutons.
 */
public class EcouteurBoutons implements ActionListener {

    /**
     * Le controleur avec lequel interagir.
     */
    protected Controleur controleur;

    /**
     * Le constructeur par defaut.
     * 
     * @param controleur
     *            Le controleur avec lequel interagir.
     */
    public EcouteurBoutons(Controleur controleur) {
	this.controleur = controleur;
    }

    @Override
    /**
     * Gere le cas des differents boutons.
     */
    public void actionPerformed(ActionEvent e) {
	System.out.println(e.getActionCommand());
	switch (e.getActionCommand()) {
	case FenetreIHM.PLAN:
	    controleur.ouvrirPlan();
	    break;
	case FenetreIHM.LIVRAISON:
	    controleur.importerLivraison();
	    break;
	case FenetreIHM.TOURNEE:
	    controleur.calculerTournee();
	    break;
	case FenetreIHM.FEUILLE:
	    controleur.genererFeuilleRoute();
	    break;
	case FenetreIHM.AJOUTER:
	    controleur.ajouterLivraison();
	    break;
	case FenetreIHM.MODIFIER:
	    controleur.modifierLivraison();
	    break;
	case FenetreIHM.SUPPRIMER:
	    controleur.supprimeLivraison();
	    break;
	case FenetreIHM.UNDO:
	    controleur.undo();
	    break;
	case FenetreIHM.REDO:
	    controleur.redo();
	    break;
	}
    }

}