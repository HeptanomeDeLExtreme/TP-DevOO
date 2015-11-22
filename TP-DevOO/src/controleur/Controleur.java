package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Itineraire;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

import vue.FenetreIHM;

/**
 * 
 */
public class Controleur {

	public static final EtatInit etatInit = new EtatInit();
	public static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	public static final EtatLivraisonChargee etatLivraisonChargee = new EtatLivraisonChargee();
	
	/**
	 * Change l'etat courant du controleur
	 * @param etat le nouvel etat courant
	 */
	protected static void setEtatCourant(Etat etat){
		etatCourant = etat;
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
//    	Intersection i1 = new Intersection(1, 10, 10);
//    	Intersection i2 = new Intersection(2,30,20);
//    	Intersection i3 = new Intersection(3,50,80);
//    	Troncon t1 = new Troncon("rue 1");
//    	Troncon t2 = new Troncon("rue 2");
//    	Troncon t3 = new Troncon("rue 3");
//    	t1.setOrigine(i1);
//    	t1.setDestination(i2);
//    	t2.setOrigine(i2);
//    	t2.setDestination(i3);
//    	t3.setOrigine(i1);
//    	t3.setDestination(i3);
//    	Set<Troncon> tronconE1 = new HashSet<Troncon>();
//    	Set<Troncon> tronconS1 = new HashSet<Troncon>();
//    	tronconS1.add(t1);
//    	tronconS1.add(t3);
//    	Set<Troncon> tronconE2 = new HashSet<Troncon>();
//    	tronconE2.add(t1);
//    	Set<Troncon> tronconS2 = new HashSet<Troncon>();
//    	tronconS2.add(t2);
//    	Set<Troncon> tronconE3 = new HashSet<Troncon>();
//    	tronconE3.add(t2);
//    	tronconE3.add(t3);
//    	Set<Troncon> tronconS3 = new HashSet<Troncon>();
//    	i1.setTronçonsEntrant(tronconE1);
//    	i1.setTronçonsSortant(tronconS1);
//    	i2.setTronçonsEntrant(tronconE2);
//    	i2.setTronçonsSortant(tronconS2);
//    	i3.setTronçonsEntrant(tronconE3);
//    	i3.setTronçonsSortant(tronconS3);
//    	Set<Intersection> listeInter = new HashSet<Intersection>();
//    	listeInter.add(i1);
//    	listeInter.add(i2);
//    	listeInter.add(i3);
    	this.plan = new Plan();
    	
    	this.tournee = new Tournee();
//    	List<Itineraire> lit = new ArrayList<Itineraire>();
//    	Itineraire it1 = new Itineraire();
//    	List<Troncon> lt1 = new ArrayList<Troncon>();
//    	lt1.add(t1);
//    	lt1.add(t2);
//    	it1.setTronçons(lt1);
//    	lit.add(it1);
//    	tournee.setItineraires(lit);
    	
    	this.demandeDeLivraison = new DemandeDeLivraison();
    	
    	etatCourant = etatInit;
    	
    	fenetre = new FenetreIHM(tournee, this, plan);
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
        this.etatCourant = this.etatLivraisonChargee;
    }

    /**
     * 
     */
    protected void calculerTournee() {
        // TODO implement here
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
    protected void genererFeuilleRoute() {
        // TODO implement here
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
		this.tournee.changementEffectue();
		this.etatCourant = this.etatPlanCharge;
	}

}