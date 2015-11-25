package modele;

import java.util.*;



/**
 * 
 */
public class Tournee extends Observable {

    private int coutTotal;


	/**
     * Default constructor
     */
    public Tournee() {
    }

    /**
     * @param correspondancePlan 
     * @param coutTotal
     * @param livraisonsEnOrdre
     */
    public void charge(Map<Intersection, Integer> correspondancePlan, DemandeDeLivraison ddl, Livraison entrepot, int coutTotal, List<Itineraire> itinerairesEnOrdre) {
    	this.demandeDeLivraison = ddl;
    	this.entrepot = entrepot;
    	this.coutTotal = coutTotal;
    	this.itineraires = itinerairesEnOrdre;

    	for(Itineraire itineraire : itinerairesEnOrdre){
    		Livraison depart = itineraire.getArrivee();
    		Livraison arrivee = itineraire.getDepart();
    		List<Troncon> listeTroncon = depart.rechercherTroncons(correspondancePlan,arrivee);
    	}
    	
    	this.changementEffectue();
    }
    
    /**
     * 
     */
    protected Float duree;

    /**
     * 
     */
    protected DemandeDeLivraison demandeDeLivraison;

    /**
     * 
     */
    protected Livraison entrepot;

    /**
     * 
     */
    protected List<Itineraire> itineraires;


    /**
     * @param livraison1 
     * @param livraison2
     */
    protected void modifierTournee(Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }

    
    
    public List<Itineraire> getItineraires() {
		return itineraires;
	}

	/**
     * @param livraisonAvant 
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param preTournee
     */
    public Tournee(Map<FenetreTemporelle,List<Itineraire>> preTournee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genererFeuilleDeRoute() {
        // TODO implement here
    }
    
    public void changementEffectue(){
        setChanged(); 
        notifyObservers();
    }

	public void setItineraires(List<Itineraire> itineraires) {
		this.itineraires = itineraires;
	}

    public String toString(){
    	String toRet="";
    	for(Itineraire iti : itineraires){
    		List<Troncon> listeTroncon = iti.getTroncons();
    		for(Troncon tronc : listeTroncon ){
    			toRet += "\n";
    			toRet +=tronc;
    		}
    	}
    	return toRet;
    }
}