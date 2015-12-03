package controleur;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import xml.GenerateurFeuilleDeRoute;

/**
 * 
 */
public class EtatTourneeCalculee extends EtatDefaut {

    /**
     * Default constructor
     */
    public EtatTourneeCalculee() {
    }

    
    public String toString(){
    	return "Etat Tournée Calculée";
    }
    
    /**
     * @param listeDeCommande
     */
    public void undo(ListeCommande listeDeCommande) {
        listeDeCommande.undo();
    }

    /**
     * @param listeDeCommande
     */
    public void redo(ListeCommande listeDeCommande) {
        listeDeCommande.redo();
    }

    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    public void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande) {
        // TODO implement here
    }

    /**
     * @param fenetre
     */
    public void ouvrirPlan(Modele modele) {
    	Plan plan = new Plan();
    	try {
    		DeserialiseurPlanXML.charger(plan);
    		JOptionPane.showMessageDialog(null, "Plan chargé correctement !", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
    		modele.setPlan(plan);
    		Controleur.setEtatCourant(Controleur.etatPlanCharge);
    		if(modele.getDemandeDeLivraison() != null){
    			modele.getDemandeDeLivraison().nettoieDemandeDeLivraison();
    		}
    		if(modele.getTournee() != null){
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

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,Modele modele, Plan plan){
    	Tournee tournee = new Tournee();
    	DemandeDeLivraison demandeDeLivraison = new DemandeDeLivraison(tournee);
    	
    	try {
    		DeserialiseurDemandeDeLivraisonXML.charger(demandeDeLivraison,plan);
    		
    		int nbLivraisons = 0;
        	List<FenetreTemporelle> fenetres = demandeDeLivraison.getFenetres();
        	
    		for (FenetreTemporelle petitFenetre : fenetres) {
    			Set<Livraison> livraisonsFActuelle = petitFenetre.getLivraisons();
    			nbLivraisons += livraisonsFActuelle.size();
    		}
    		// Ajout de l'entrepot
    		nbLivraisons++;
    		demandeDeLivraison.setNbLivraisons(nbLivraisons);
            Controleur.setEtatCourant(Controleur.etatLivraisonChargee);
            modele.setTournee(tournee);
            modele.setDemandeDeLivraison(demandeDeLivraison);
            JOptionPane.showMessageDialog(null, "Demande de livraison chargée correctement !", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur livraisons");
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(Modele modele, FenetreIHM fenetre) {
    	JOptionPane.showMessageDialog(null, "Tournee déja calculée  !", "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * @param demandeDeLivraison
     */
    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
        try {
			GenerateurFeuilleDeRoute.genererFeuilleDeRoute(tournee);
			JOptionPane.showMessageDialog(null, "Feuille de route générée !", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erreur lors de la génération !", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	Livraison liv = ddl.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	Intersection inter = plan.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	
    	if(liv != null){
    		Controleur.setEtatCourant(Controleur.etatLivraisonsSelectionnees);
    		Controleur.etatLivraisonsSelectionnees.setLivraison(liv);
    	}
    	else if(inter != null){
    		Controleur.setEtatCourant(Controleur.etatIntersectionSelectionnee);
    		Controleur.etatIntersectionSelectionnee.setIntersection(inter);
    	}
    	fenetre.afficheMessage("coucou");
    }
    
    
  	
}