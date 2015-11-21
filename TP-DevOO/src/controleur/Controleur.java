package controleur;

import java.awt.Point;
import java.util.*;

import modele.DemandeDeLivraison;
import modele.Intersection;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

import vue.FenetreIHM;

/**
 * 
 */
public class Controleur {

    /**
     * 
     */
    protected Etat etatCourant;

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
    
    /**
     * Default constructor
     */
    public Controleur() {
    	Intersection i1 = new Intersection(1, 10, 10);
    	Intersection i2 = new Intersection(2,30,20);
    	Intersection i3 = new Intersection(3,50,80);
    	Troncon t1 = new Troncon();
    	Troncon t2 = new Troncon();
    	Troncon t3 = new Troncon();
    	t1.setOrigine(i1);
    	t1.setDestination(i2);
    	t2.setOrigine(i2);
    	t2.setDestination(i3);
    	t3.setOrigine(i1);
    	t3.setDestination(i3);
    	Set<Troncon> tronconE1 = new HashSet<Troncon>();
    	Set<Troncon> tronconS1 = new HashSet<Troncon>();
    	tronconS1.add(t1);
    	tronconS1.add(t3);
    	Set<Troncon> tronconE2 = new HashSet<Troncon>();
    	tronconE2.add(t1);
    	Set<Troncon> tronconS2 = new HashSet<Troncon>();
    	tronconS2.add(t2);
    	Set<Troncon> tronconE3 = new HashSet<Troncon>();
    	tronconE3.add(t2);
    	tronconE3.add(t3);
    	Set<Troncon> tronconS3 = new HashSet<Troncon>();
    	i1.setTronçonsEntrant(tronconE1);
    	i1.setTronçonsSortant(tronconS1);
    	i2.setTronçonsEntrant(tronconE2);
    	i2.setTronçonsSortant(tronconS2);
    	i3.setTronçonsEntrant(tronconE3);
    	i3.setTronçonsSortant(tronconS3);
    	
    	Set<Intersection> listeInter = new HashSet<Intersection>();
    	listeInter.add(i1);
    	listeInter.add(i2);
    	listeInter.add(i3);
    	this.plan = new Plan(listeInter);
    	
    	Tournee tournee = new Tournee();
    	fenetre = new FenetreIHM(tournee, this, plan);
    	this.etatCourant = new EtatDefaut();
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
    protected void ouvrirPlan() {
        // TODO implement here
    }

    /**
     * 
     */
    protected void importerLivraison() {
        // TODO implement here
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

}