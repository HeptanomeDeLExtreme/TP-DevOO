package modele;

import java.util.Observable;

public class Modele extends Observable{
	
	protected Tournee tournee;
	protected DemandeDeLivraison demandeDeLivraison;
	protected Plan plan;
	
	public Modele(){
		this.tournee = new Tournee();
		this.demandeDeLivraison = new DemandeDeLivraison(this.tournee);
		this.plan = new Plan();
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
