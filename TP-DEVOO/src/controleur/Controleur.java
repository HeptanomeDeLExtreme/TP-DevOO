package controleur;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Itineraire;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

import vue.FenetreIHM;
import xml.GenerateurFeuilleDeRoute;

/**
 * 
 */
public class Controleur {

	public static final EtatInit etatInit = new EtatInit();
	public static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	public static final EtatLivraisonChargee etatLivraisonChargee = new EtatLivraisonChargee();
	public static final EtatTourneeCalculee etatTourneeCalculee = new EtatTourneeCalculee();
	
	/**
	 * Change l'etat courant du controleur
	 * @param etat le nouvel etat courant
	 */
	protected static void setEtatCourant(Etat etat){
		etatCourant = etat;
		System.out.println("Etat changé pour : "+etat);
	}
	
    /**
     * 
     */
    protected static Etat etatCourant;

    /**
     * 
     */
    protected ListeCommande listeCommandes;

    /**
     * 
     */
    protected FenetreIHM fenetre;

    /**
     * 
     */
    protected Plan plan;

    /**
     * 
     */
    protected DemandeDeLivraison demandeDeLivraison;

    /**
     * 
     */
    
    // A ENLEVER
    protected Tournee tournee;
    // FIN A ENLEVER
    /**
     * Default constructor
     */
    public Controleur() {
    	this.plan = new Plan();
    	
    	this.tournee = new Tournee();
    	
    	this.demandeDeLivraison = new DemandeDeLivraison(this.tournee);
    	
    	etatCourant = etatInit;
    	
    	fenetre = new FenetreIHM(demandeDeLivraison, tournee, this, plan);
    }
    
    public void afficheMessageIHM(String s){
    	this.fenetre.afficheMessage(s);
    }
    
    protected void undo() {
        // TODO implement here
    }

    /**
     * 
     */
    protected void redo() {
        // TODO implement here
    }


    /**
     * 
     */
    public void importerLivraison() {
        this.etatCourant.importerLivraison(fenetre,demandeDeLivraison,plan);
        this.demandeDeLivraison.changementEffectue();
    }

    /**
     * 
     */
    public void calculerTournee() {
        this.etatCourant.calculerTournee(fenetre, plan, demandeDeLivraison);
    }

    /**
     * 
     */
    protected void ajouterLivraison() {
        // TODO implement here
    }

    /**
     * 
     */
    public void genererFeuilleRoute() {
        try {
			GenerateurFeuilleDeRoute.genererFeuilleDeRoute(this.tournee);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     */
    protected void modifierLivraison() {
        // TODO implement here
    }

    /**
     * 
     */
    protected void supprimeLivraison() {
        // TODO implement here
    }

    /**
     * 
     */
    public void clicGauche(Point p) {
        this.etatCourant.clicGauche(fenetre,plan,p);
    }

	public int getPlanLargeur() {
		return this.plan.getLargeur();
	}

	public int getPlanHauteur() {
		return this.plan.getHauteur();
	}



	public void newFakeTournee() {
	    this.tournee.changementEffectue();
	}



	public void ouvrirPlan() {
		this.etatCourant.ouvrirPlan(this.plan);	
		this.plan.changementEffectue();
	}



	public void clicDroit(Point p) {
		this.etatCourant.clicDroit(fenetre,p);		
	}
	
	int kil = 0;
	public void caractereSaisi(int keyCode) {
		switch(keyCode){
			case KeyEvent.VK_P:
				this.ouvrirPlan();
				break;
			case KeyEvent.VK_L:
				this.importerLivraison();
				break;
			case KeyEvent.VK_T:
				this.calculerTournee();
				System.out.println("Calcul tournée!!");
				this.fenetre.afficheMessage("Essai"+kil++);
				break;
			case KeyEvent.VK_G:
				this.genererFeuilleRoute();
				break;
		}
	}

}