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
    protected Boolean commandeValide = true;
    protected FenetreTemporelle fenetreTemp;
    

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
//				this.commandeValide = false;
//			}
     		this.commandeValide = false;
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
    		this.modele.supprimeLivraison(this.livraisonAjoutee);
    	}
    	else{
    		this.modele.supprimeLivraison(this.livraisonAjoutee);
    	}
    	fenetreTemp = this.livraisonAjoutee.getFenetre();
    }

    /**
     * 
     */
    public void undoCommande() {
    	if(this.commandeValide){
    		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
   	    	this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(this.livraisonSuivante, this.livraisonAjoutee.getAdresse());
   	    	this.livraisonAjoutee.setFenetre(this.fenetreTemp);
	    	this.modele.changementEffectue();
    	}
    	else{
     		
    		List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
    		Livraison derniere = livEnOrdre.get(livEnOrdre.size()-2);
    		
    		this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(derniere,this.livraisonAjoutee.getAdresse());
    		FenetreTemporelle fenetreDerniere=derniere.getFenetre();
    		
    		this.modele.getTournee().modifierTournee(derniere, this.livraisonAjoutee);
    		
    		this.modele.getTournee().getLivraisonsEnOrdre().get(this.modele.getTournee().getLivraisonsEnOrdre().size()-2).setFenetre(fenetreTemp);
    		System.out.println("On force la fenetre " + this.fenetreTemp.getHeureDebut() + " à " + this.fenetreTemp.getHeureFin());
    		
    		
    		this.modele.changementEffectue();
    		
    	}
    	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
    	this.modele.getDemandeDeLivraison().majCoutTournee();
    }

}