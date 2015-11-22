package modele;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * 
 */
public class DemandeDeLivraison {

    /**
     * 
     */
    protected Tournee tournee;

    /**
     * 
     */
    protected List<FenetreTemporelle> fenetres;

    /**
     * 
     */
    protected Livraison entrepot;

    /**
     * 
     */
    protected DemandeDeLivraison singleton;
    
    /**
     * Default constructor
     */
    public DemandeDeLivraison() {
    }
    
    public void chargerLivraison(Plan plan){
    	try {
    		fenetres = new ArrayList<FenetreTemporelle>();
    		DeserialiseurDemandeDeLivraisonXML.charger(this,plan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			System.out.println("Exception constructeur livraisons");			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }

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

    /**
     * @param livraisonAvant 
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param plan
     */
    protected void calculerTournee(Plan plan) {
        // TODO implement here
    }

    /**
     * @param plan 
     * @param depart 
     * @param arrivee
     */
    protected void calculPlusCourtChemin(Plan plan, Livraison depart, Livraison arrivee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genereFeuilleDeRoute() {
        // TODO implement here
    }
    
    /**
     * Permet d'ajouter une livraison. Ne doit servir que lors de la création de la demande de livraison lors de l'ajout de l'entrepot
     * @param Livraison entrepot
     */
	public void ajouteEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}
	
    /**
     * Permet d'ajouter une fenetre temporelle. Ne doit servir que lors de la création de la demande de livraison
     * @param FenetreTemporelle fenetreTemporelle
     */
	public void ajouteFenetreTemporelle(FenetreTemporelle fenetreTemporelle) {
		this.fenetres.add(fenetreTemporelle);
	}
	
	@Override
	public String toString() {
		String s = this.entrepot.toString() + "/n";
		for (FenetreTemporelle f : this.fenetres) {
			s += f.toString() +"/n";	 // TODO FenetreTemporelle.toString()
		}
		return s;
	}

	public Tournee getTournee() {
		return tournee;
	}

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

	public List<FenetreTemporelle> getFenetres() {
		return fenetres;
	}

	public void setFenetres(List<FenetreTemporelle> fenetres) {
		this.fenetres = fenetres;
	}

	public Livraison getEntrepot() {
		return entrepot;
	}

	public void setEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}

	public DemandeDeLivraison getSingleton() {
		return singleton;
	}

	public void setSingleton(DemandeDeLivraison singleton) {
		this.singleton = singleton;
	}

}