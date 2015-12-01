package controleur;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

import vue.FenetreIHM;
import xml.GenerateurFeuilleDeRoute;

/**
 * 
 */
public class Controleur {

	// INIT
	public static final EtatInit etatInit = new EtatInit();
	
	// NOYAU MINIMAL
	public static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	public static final EtatLivraisonChargee etatLivraisonChargee = new EtatLivraisonChargee();
	public static final EtatTourneeCalculee etatTourneeCalculee = new EtatTourneeCalculee();
	
	// MODIFICATION TOURNEE
	public static final EtatDeuxLivraisonSelectionnee etatDeuxLivraisonSelectionnee = new EtatDeuxLivraisonSelectionnee();
	public static final EtatIntersectionSelectionnee etatIntersectionSelectionnee = new EtatIntersectionSelectionnee();
	public static final EtatLivraisonPrecedenteSelectionnee etatLivraisonPrecedenteSelectionnee = new EtatLivraisonPrecedenteSelectionnee();
	public static final EtatLivraisonsSelectionnees etatLivraisonsSelectionnees = new EtatLivraisonsSelectionnees();
	
	
	
	
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
    protected Modele modele;
    
     /** Default constructor
     */
    public Controleur() {
    	this.modele = new Modele();
    	
    	this.listeCommandes = new ListeCommande();
    	
    	etatCourant = etatInit;
    	
    	fenetre = new FenetreIHM(modele, this);
    }
    
    public void afficheMessageIHM(String s){
    	this.fenetre.afficheMessage(s);
    }
    
    
    // UNDO REDO 
    
    public void undo() {
        this.etatCourant.undo(listeCommandes);
    }

    /**
     * 
     */
    public void redo() {
        this.etatCourant.redo(listeCommandes);
    }

    
    
    // NOYAU MINIMAL
    
	public void ouvrirPlan() {
		this.etatCourant.ouvrirPlan(this.modele.getPlan());	
		this.modele.changementEffectue();
	}
	
    /**
     * 
     */
    public void importerLivraison() {
        this.etatCourant.importerLivraison(fenetre,this.modele.getDemandeDeLivraison(),this.modele.getPlan());
        this.modele.changementEffectue();
    }

    /**
     * 
     */
    public void calculerTournee() {
        this.etatCourant.calculerTournee(fenetre, this.modele.getPlan(), this.modele.getDemandeDeLivraison());
        this.modele.changementEffectue();
    }

    
    
    // MODIFICATION TOURNEE
    
    /**
     * 
     */
    public void ajouterLivraison() {
        this.etatCourant.ajouterLivraison(this.modele, listeCommandes);
        this.modele.changementEffectue();
        this.setEtatCourant(etatTourneeCalculee);
    }

    /**
     * 
     */
    public void modifierLivraison() {
        this.etatCourant.modifierLivraison(this.modele, listeCommandes);
        this.modele.changementEffectue();
        this.setEtatCourant(etatTourneeCalculee);
    }
    

    /**
     * 
     */
    public void supprimeLivraison() {
        this.etatCourant.supprimeLivraison(this.modele, listeCommandes);
        this.modele.changementEffectue();
        this.setEtatCourant(etatTourneeCalculee);
    }
    
    
    
    // GENERATION FEUILLE DE ROUTE
    
    /**
     * 
     */
    public void genererFeuilleRoute() {
    	this.etatCourant.genererFeuilleRoute(this.fenetre, this.modele.getTournee());
    }

    /**
     * 
     */
    
    
    
    // GESTION SOURIS
    
    public void clicGauche(Point p) {
        this.etatCourant.clicGauche(fenetre,this.modele.getPlan(),p,this.modele.getDemandeDeLivraison());
    }

	public int getPlanLargeur() {
		return this.modele.getPlan().getLargeur();
	}

	public int getPlanHauteur() {
		return this.modele.getPlan().getHauteur();
	}

	public void clicDroit(Point p) {
		this.etatCourant.clicDroit(fenetre,p);		
	}
	
	
	// GESTION CLAVIER
	
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
				break;
			case KeyEvent.VK_G:
				this.genererFeuilleRoute();
				break;
			case KeyEvent.VK_U:
				this.undo();
				break;
			case KeyEvent.VK_R:
				this.redo();
				break;
			case KeyEvent.VK_A:
				this.ajouterLivraison();
				break;
			case KeyEvent.VK_S:
				this.supprimeLivraison();
				break;
			case KeyEvent.VK_M:
				this.modifierLivraison();
				break;
		}
	}

}