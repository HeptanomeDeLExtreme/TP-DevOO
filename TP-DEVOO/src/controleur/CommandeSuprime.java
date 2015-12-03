package controleur;

import java.util.*;

import javax.swing.JOptionPane;

import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Livraison;
import modele.Modele;
import modele.Tournee;

/**
 * 
 */
public class CommandeSuprime implements Commande {


    /**
     * 
     */
    protected Modele modele;
    protected Livraison livraisonAjoutee;
    protected Livraison livraisonSuivante;
    protected Boolean entrepotSelectionne = false;
    protected FenetreTemporelle fenetreTemp;
    protected Boolean commandeValide = true;

    /**
     * @param tournee 
     * @param livraison
     */
    public CommandeSuprime(Modele modele, Livraison livraison) {
        this.modele = modele;
     	this.livraisonAjoutee = livraison;
     	
     	List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
     	this.livraisonSuivante = livEnOrdre.get(livEnOrdre.indexOf(this.livraisonAjoutee)+1);
     	
     	Livraison entrepot = livEnOrdre.get(0);
     	if(this.livraisonSuivante.equals(entrepot)){
//			int response = JOptionPane.showConfirmDialog(null, "Si vous supprimez la derniere livraison, des erreurs peuvent subvenir.", "Continuer",
//			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			if (response == JOptionPane.NO_OPTION) {
//				this.entrepotSelectionne = false;
//			}
     		this.entrepotSelectionne = true;
     	}
     	
     	if(this.modele.getTournee().getLivraisonsEnOrdre().size()==3){
			int response = JOptionPane.showConfirmDialog(null, "Si vous supprimez la derniere livraison, des erreurs peuvent subvenir.", "Continuer",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {
				this.commandeValide = false;
			}
     	}
     	
     	
     	
    	
//    	System.out.println("ASUP : "+this.liv);
//    	System.out.println("PREC : "+this.livraisonSuivante);
//    	System.out.println("INTER : "+this.liv.getAdresse());
    }

    /**
     * 
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
    	}
    }

    /**
     * 
     */
    public void undoCommande() {
    	if(this.commandeValide){
    		// si l'entrepot n'a pas été selectionné en tant que livraison suivante
	    	if(!this.entrepotSelectionne){
	   	    	this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(this.livraisonSuivante, this.livraisonAjoutee.getAdresse());
	    		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
		    	this.modele.changementEffectue();
	    	}
	    	// si l'entrepot a été selectionné en tant que livraison suivante
	    	else{
	    		List<FenetreTemporelle> listFenetre = this.modele.getDemandeDeLivraison().getFenetres();
	    		List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
	    		Livraison derniere = livEnOrdre.get(livEnOrdre.size()-2);
	    		
	    		this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(derniere,this.livraisonAjoutee.getAdresse());
	    		this.livraisonAjoutee.setFenetre(listFenetre.get(listFenetre.size()-1));
	    		this.modele.getTournee().modifierTournee(derniere, this.livraisonAjoutee);
//	    		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
	    		this.livraisonAjoutee.setFenetre(listFenetre.get(listFenetre.size()-1));
	    		this.modele.changementEffectue();
	    	}
	    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
	    	this.modele.getDemandeDeLivraison().majCoutTournee();
    	}
    }

}