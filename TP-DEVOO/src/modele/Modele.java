package modele;

import java.util.Observable;

/**
 * 
 * Implemente l'ensemble du modele
 * 
 */
public class Modele extends Observable {

    protected Tournee tournee;
    protected DemandeDeLivraison demandeDeLivraison;
    protected Plan plan;

    public Tournee getTournee() {
	return tournee;
    }

    public DemandeDeLivraison getDemandeDeLivraison() {
	return demandeDeLivraison;
    }

    public Plan getPlan() {
	return plan;
    }

    public void setPlan(Plan copie) {
	this.plan = copie;
    }

    public void setTournee(Tournee tournee2) {
	this.tournee = tournee2;
    }

    public void setDemandeDeLivraison(DemandeDeLivraison demandeDeLivraison2) {
	this.demandeDeLivraison = demandeDeLivraison2;
    }

    /**
     * Ajoute une livraison dans le modele
     * 
     * @param liv
     *            Livraison avant laquelle on ajoute la livraison
     * @param inter
     *            Intersection sur laquelle on ajoute la livraison
     */
    public void ajouteLivraison(Livraison liv, Intersection inter) {
	this.tournee.ajouteLivraison(liv, inter);
	this.changementEffectue();
    }

    /**
     * Supprime une livraison dans le modele
     * 
     * @param liv
     *            Livraison a supprimer
     */
    public void supprimeLivraison(Livraison liv) {
	this.tournee.supprimeLivraison(liv);
	this.changementEffectue();
    }

    /**
     * Intervertit deux livraisons dans le modele
     * 
     * @param liv1
     * @param liv2
     */
    public void modifier(Livraison liv1, Livraison liv2) {
	this.tournee.modifierTournee(liv1, liv2);
	this.changementEffectue();
    }

    public Modele() {
	this.plan = new Plan();
	this.tournee = new Tournee();
	this.demandeDeLivraison = new DemandeDeLivraison(this.tournee);
    }

    public void changementEffectue() {
	setChanged();
	notifyObservers();
    }
}
