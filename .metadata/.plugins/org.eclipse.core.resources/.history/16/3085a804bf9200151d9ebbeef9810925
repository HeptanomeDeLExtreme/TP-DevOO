package modele;

import java.util.*;



/**
 * 
 */
public class Tournee extends Observable {

    /**
     * Default constructor
     */
    public Tournee() {
    }

    /**
     * @param coutTotal
     * @param livraisonsEnOrdre
     */
    public Tournee(DemandeDeLivraison ddl, Livraison entrepot, int coutTotal, List<Itineraire> itinerairesEnOrdre) {
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

    
}