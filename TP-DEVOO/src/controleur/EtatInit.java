package controleur;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

import vue.FenetreIHM;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * Represente le comportement dans l'etat initial.
 */
public class EtatInit extends EtatDefaut {

    public EtatInit() {
    }

    public String toString() {
	return "Etat Init";
    }

    public void ouvrirPlan(Modele modele) {
	Plan plan = modele.getPlan();

	try {
	    plan.nettoiePlan();
	    if (modele.getDemandeDeLivraison() != null) {
		modele.getDemandeDeLivraison().nettoieDemandeDeLivraison();
	    }
	    if (modele.getTournee() != null) {
		modele.getTournee().nettoyer();
	    }
	    DeserialiseurPlanXML.charger(plan);
	    Controleur.setEtatCourant(Controleur.etatPlanCharge);
	    JOptionPane.showMessageDialog(null, "Plan chargé correctement !",
		    "Info", JOptionPane.INFORMATION_MESSAGE);
	} catch (ParserConfigurationException | SAXException | IOException
		| ExceptionXML e) {
	    System.out.println("Exception constructeur plan");
	    System.out.println(e.getMessage());
	    JOptionPane.showMessageDialog(null, e.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    public void importerLivraison(FenetreIHM fenetre, Modele modele, Plan plan) {
	fenetre.afficheMessage("Veuillez charger un plan !");
    }

    public void calculerTournee(Modele modele, FenetreIHM fenetre) {
	fenetre.afficheMessage("Veuillez charger un plan puis une livraison !");
    }

    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
	fenetre.afficheMessage("Veuillez effectuer le calcul de la tournée !");
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p,
	    DemandeDeLivraison ddl) {
	fenetre.afficheMessage("Aucun plan chargé !");
    }

}