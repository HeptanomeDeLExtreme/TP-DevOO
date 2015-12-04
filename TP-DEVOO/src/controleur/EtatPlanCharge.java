package controleur;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;

import vue.FenetreIHM;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * Represente le comportement dans l'etat plan chargé.
 */
public class EtatPlanCharge extends EtatDefaut {

    public EtatPlanCharge() {
    }

    public String toString() {
	return "Etat Plan Chargé";
    }

    public void ouvrirPlan(Modele modele) {
	Plan plan = new Plan();
	try {
	    DeserialiseurPlanXML.charger(plan);
	    JOptionPane.showMessageDialog(null, "Plan chargé correctement !",
		    "Info", JOptionPane.INFORMATION_MESSAGE);
	    modele.setPlan(plan);
	    if (modele.getDemandeDeLivraison() != null) {
		modele.getDemandeDeLivraison().nettoieDemandeDeLivraison();
	    }
	    if (modele.getTournee() != null) {
		modele.getTournee().nettoyer();
	    }
	} catch (ParserConfigurationException | SAXException | IOException
		| ExceptionXML e) {
	    System.out.println("Exception constructeur plan");
	    System.out.println(e.getMessage());
	    JOptionPane.showMessageDialog(null, e.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    public void importerLivraison(FenetreIHM fenetre, Modele modele, Plan plan) {
	Tournee tournee = new Tournee();
	DemandeDeLivraison demandeDeLivraison = new DemandeDeLivraison(tournee);

	try {
	    DeserialiseurDemandeDeLivraisonXML
		    .charger(demandeDeLivraison, plan);

	    int nbLivraisons = 0;
	    List<FenetreTemporelle> fenetres = demandeDeLivraison.getFenetres();

	    for (FenetreTemporelle petitFenetre : fenetres) {
		Set<Livraison> livraisonsFActuelle = petitFenetre
			.getLivraisons();
		nbLivraisons += livraisonsFActuelle.size();
	    }
	    // Ajout de l'entrepot
	    nbLivraisons++;
	    demandeDeLivraison.setNbLivraisons(nbLivraisons);
	    Controleur.setEtatCourant(Controleur.etatLivraisonChargee);
	    modele.setTournee(tournee);
	    modele.setDemandeDeLivraison(demandeDeLivraison);
	    JOptionPane.showMessageDialog(null,
		    "Demande de livraison chargée correctement !", "Info",
		    JOptionPane.INFORMATION_MESSAGE);
	} catch (ParserConfigurationException | SAXException | IOException
		| ExceptionXML e) {
	    System.out.println("Exception constructeur livraisons");
	    System.out.println(e.getMessage());
	    JOptionPane.showMessageDialog(null, e.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    public void calculerTournee(Modele modele, FenetreIHM fenetre) {
	fenetre.afficheMessage("Veuillez charger une livraison !");
    }

    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
	fenetre.afficheMessage("Veuillez effectuer le calcul de la tournée !");
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p,
	    DemandeDeLivraison ddl) {
	Intersection inter = plan.cherche(p, fenetre.getEchelleX(),
		fenetre.getEchelleY());
	if (inter != null) {
	    fenetre.afficheMessage("Intersection sélectionnée : "
		    + inter.getId());
	} else {
	    fenetre.afficheMessage("Pas d'intersection ici !");
	}
    }

}