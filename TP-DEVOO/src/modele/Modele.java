package modele;

import java.util.Observable;

public class Modele extends Observable{
// TODO Java doc des methodes
	protected Tournee tournee;
	protected DemandeDeLivraison demandeDeLivraison;
	protected Plan plan;
	
	public Modele(){
		this.plan = new Plan();
		this.tournee = new Tournee();
		this.demandeDeLivraison = new DemandeDeLivraison(this.tournee);
	}

	public Tournee getTournee() {
		return tournee;
	}

	public DemandeDeLivraison getDemandeDeLivraison() {
		return demandeDeLivraison;
	}

	public Plan getPlan() {
		return plan;
	}
	
	public void changementEffectue(){
		setChanged();
		notifyObservers();
	}
	//TODO
	public void ajouteLivraison(Livraison liv, Intersection inter) {
		this.tournee.ajouteLivraison(liv, inter);	
		this.changementEffectue();
	}

	//TODO
	public void supprimeLivraison(Livraison liv) {
		this.tournee.supprimeLivraison(liv);
		this.changementEffectue();
	}
	
	//TODO
	public void modifier(Livraison liv1, Livraison liv2) {
		this.tournee.modifierTournee(liv1, liv2);
		this.changementEffectue();
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
}
