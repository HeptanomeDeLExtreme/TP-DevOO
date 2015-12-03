package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
    public EcouteurBoutons(Controleur controleur) {
        this.controleur = controleur;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());	
		switch (e.getActionCommand()){
		case "ChargerPlan": controleur.ouvrirPlan(); break;
		case "ChargerLivraison" : controleur.importerLivraison(); break;
		case "CalculTournee" : controleur.calculerTournee(); break;
		case "Generer Feuille de Route" : controleur.genererFeuilleRoute(); break;
		case "Ajouter Livraison" : controleur.ajouterLivraison(); break;
		case "Modifier Livraison": controleur.modifierLivraison(); break;
		case "Supprimer Livraison": controleur.supprimeLivraison(); break;
		case "Undo": controleur.undo(); break;
		case "Redo": controleur.redo(); break;
		}
	}

}