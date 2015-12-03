package controleur;

import java.util.*;

import javax.swing.JOptionPane;

import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Modele;
import modele.Tournee;
import modele.Troncon;

/**
 * Cette classe permet de gerer le undo/redo de la suppression de livraison.
 */
public class CommandeSuprime implements Commande {


    /**
     * Le modele a modifier.
     */
    protected Modele modele;
    /**
     * La livraison que l'on a ajouter au undo.
     */
    protected Livraison livraisonAjoutee;
    /**
     * La livraison après laquelle faire le undo.
     */
    protected Livraison livraisonSuivante;
    /**
     * True si l'entrepot est la livraison suivante.
     */
    protected Boolean entrepotSelectionne = false; 
    /**
     * La fenetre temporelle a laquelle appartient la livraison supprimee.
     */
    protected FenetreTemporelle fenetreTemp;
    /**
     * True si la livraison a supprimer n'est pas la derniere presente.
     */
    protected Boolean commandeValide = true; 

    /**
     * Constructeur par defaut.
     * @param modele Le modele a modifier. 
     * @param livraison La livraison a supprimer.
     */
    public CommandeSuprime(Modele modele, Livraison livraison) {
        this.modele = modele;
     	this.livraisonAjoutee = livraison;
     	
     	List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
     	this.livraisonSuivante = livEnOrdre.get(livEnOrdre.indexOf(this.livraisonAjoutee)+1);
     	
     	Livraison entrepot = livEnOrdre.get(0);
     	if(this.livraisonSuivante.equals(entrepot)){
     		this.entrepotSelectionne = true;
     	}
     	
//     	if(this.modele.getTournee().getLivraisonsEnOrdre().size()==3){
     	if(livraison.equals(livEnOrdre.get(livEnOrdre.size()-2))){
			int response = JOptionPane.showConfirmDialog(null, "Si vous supprimez la derniere livraison, des erreurs peuvent subvenir.", "Continuer",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {
				this.commandeValide = false;
			}
     	}
    }

    /**
     * Fait la commande.
     */
    public void doCommande() {
    	if(this.commandeValide){
	    	if(this.entrepotSelectionne){
	    		this.modele.supprimeLivraison(this.livraisonAjoutee);
	    	}
	    	else{
	    		this.modele.supprimeLivraison(this.livraisonAjoutee);
	    	}
	    	fenetreTemp = this.livraisonAjoutee.getFenetre();

	    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
	    	this.modele.getDemandeDeLivraison().majCoutTournee();
    	}
    	
    }

    /**
     * Annule la commande.
     */
    public void undoCommande() {
    	if(this.commandeValide){
    		System.out.println("OPHELIE : "+this.livraisonAjoutee);
	    	if(!this.entrepotSelectionne){
	    		
	    		System.out.println("OPHELIE1 : "+this.livraisonAjoutee);
	    		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
	    		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		    	this.modele.getDemandeDeLivraison().majCoutTournee();
	    		
	    		System.out.println("OPHELIE2 : "+this.livraisonAjoutee);
	   	    	this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(this.livraisonSuivante, this.livraisonAjoutee.getAdresse());
	   	    	
	   	    	System.out.println("OPHELIE3 : "+this.livraisonAjoutee);
	   	    	this.livraisonAjoutee.setFenetre(this.fenetreTemp);
	   	    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		    	this.modele.getDemandeDeLivraison().majCoutTournee();
		    	
	   	    	System.out.println("OPHELIE4 : "+this.livraisonAjoutee);
		    	this.modele.changementEffectue();
		    	
		    	System.out.println("OPHELIE5 : "+this.livraisonAjoutee);
	    	}
	    	else{
	    		System.out.println("OPHELIE1bis : "+this.livraisonAjoutee);
	    		List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
	    		
	    		System.out.println("OPHELIE2bis : "+this.livraisonAjoutee);
	    		Livraison derniere = livEnOrdre.get(livEnOrdre.size()-2);
	    		
	    		System.out.println("OPHELIE3bis : "+this.livraisonAjoutee);
	    		this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(derniere,this.livraisonAjoutee.getAdresse());
	    		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
	    		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		    	this.modele.getDemandeDeLivraison().majCoutTournee();
		    	
	    		System.out.println("OPHELIE4bis : "+this.livraisonAjoutee);
	    		FenetreTemporelle fenetreDerniere=derniere.getFenetre();
	    		
	    		System.out.println("OPHELIE5bis : "+this.livraisonAjoutee);
	    		this.modele.getDemandeDeLivraison().majCoutTournee();
	    		
	    		System.out.println("OPHELIE6bis : "+this.livraisonAjoutee);
		    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		    	
	    		System.out.println("OPHELIE7bis : "+this.livraisonAjoutee);
	    		this.modele.getTournee().modifierTournee(derniere, this.livraisonAjoutee);
	    		
	    		System.out.println("OPHELIE8bis : "+this.livraisonAjoutee);
	    		this.modele.getTournee().getLivraisonsEnOrdre().get(this.modele.getTournee().getLivraisonsEnOrdre().size()-2).setFenetre(fenetreTemp);
	    		
	    		System.out.println("OPHELIE9bis : "+this.livraisonAjoutee);
	    		this.modele.getDemandeDeLivraison().majCoutTournee();
	    		
	    		System.out.println("OPHELIE10bis : "+this.livraisonAjoutee);
		    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
	    		
		    	System.out.println("OPHELIE11bis : "+this.livraisonAjoutee);
	    		this.modele.changementEffectue();
	    		
	    		System.out.println("OPHELIE** : "+this.livraisonAjoutee);

	    	}
	    	
    	}
    	
    	
    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
    	this.modele.getDemandeDeLivraison().majCoutTournee();
    	
    	System.out.println("");
    	System.out.println("POUIT");
    	for(Itineraire iti : this.modele.getTournee().getItineraires()){
    		System.out.println("Itineraire : "+iti);
    		List<Troncon> listTronc = iti.getTroncons();
    		for(Troncon tronc : listTronc){
    			System.out.println(tronc);
    		}
    		System.out.println();
    	}
    	System.out.println("");
    }

}