package controleur;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
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
    public void ouvrirPlan(Plan plan) {
    	try {
    		plan.nettoiePlan();
    		DeserialiseurPlanXML.charger(plan);
    		Controleur.setEtatCourant(Controleur.etatPlanCharge);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur plan");			
		}
    }

    /**
     * @param fenetre
     */
    public void importerLivraison(FenetreIHM fenetre,DemandeDeLivraison demandeDeLivraison, Plan plan){
    	try {
    		demandeDeLivraison.nettoieDemandeDeLivraison();
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
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			System.out.println("Exception constructeur livraisons");			
		}
    }

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    public void calculerTournee(FenetreIHM fenetre, Plan plan, DemandeDeLivraison demandeDeLivraison) {
        demandeDeLivraison.calculerTournee(plan);
    }
    
    /**
     * @param demandeDeLivraison
     */
    public void genererFeuilleRoute(FenetreIHM fenetre, Tournee tournee) {
        try {
			GenerateurFeuilleDeRoute.genererFeuilleDeRoute(tournee);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void clicGauche(FenetreIHM fenetre, Plan plan, Point p, DemandeDeLivraison ddl){
    	Livraison liv = ddl.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	Intersection inter = plan.cherche(p,fenetre.getEchelleX(),fenetre.getEchelleY());
    	
    	if(liv != null){
    		Controleur.setEtatCourant(Controleur.etatLivraisonsSelectionnees);
    		Controleur.etatLivraisonsSelectionnees.setLivraison(liv);
    		fenetre.afficheMessage(Controleur.etatCourant.toString());
    	}
    	else if(inter != null){
    		Controleur.setEtatCourant(Controleur.etatIntersectionSelectionnee);
    		Controleur.etatIntersectionSelectionnee.setIntersection(inter);
    		fenetre.afficheMessage(Controleur.etatCourant.toString());
    	}
    }
    
    
  	
}