package modele;

import java.util.Observable;

public class Modele extends Observable{

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
}
