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
     * 
     * @param modele
     *            Le modele a modifier.
     * @param livraison
     *            La livraison a supprimer.
     */
    public CommandeSuprime(Modele modele, Livraison livraison) {
	this.modele = modele;
	this.livraisonAjoutee = livraison;

	List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();
	this.livraisonSuivante = livEnOrdre.get(livEnOrdre.indexOf(this.livraisonAjoutee) + 1);

	Livraison entrepot = livEnOrdre.get(0);
	if (this.livraisonSuivante.equals(entrepot)) {
	    this.entrepotSelectionne = true;
	}

	if (livraison.equals(livEnOrdre.get(livEnOrdre.size() - 2))) {
	    int response = JOptionPane.showConfirmDialog(null,
		    "Si vous supprimez la derniere livraison, des erreurs peuvent subvenir. Voulez vous continuer ?",
		    "Continuer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.NO_OPTION) {
		this.commandeValide = false;
	    }
	}
    }

    /**
     * Fait la commande.
     */
    public void doCommande() {
	if (this.commandeValide) {
	    if (this.entrepotSelectionne) {
		this.modele.supprimeLivraison(this.livraisonAjoutee);
	    } else {
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
	if (this.commandeValide) {
	    if (!this.entrepotSelectionne) {

		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		this.modele.getDemandeDeLivraison().majCoutTournee();

		this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(this.livraisonSuivante,
			this.livraisonAjoutee.getAdresse());

		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		this.modele.getDemandeDeLivraison().majCoutTournee();

		this.modele.changementEffectue();

	    } else {
		List<Livraison> livEnOrdre = this.modele.getTournee().getLivraisonsEnOrdre();

		Livraison derniere = livEnOrdre.get(livEnOrdre.size() - 2);

		this.livraisonAjoutee = this.modele.getTournee().ajouteLivraison(derniere,
			this.livraisonAjoutee.getAdresse());
		this.livraisonAjoutee.setFenetre(this.fenetreTemp);
		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
		this.modele.getDemandeDeLivraison().majCoutTournee();

		FenetreTemporelle fenetreDerniere = derniere.getFenetre();

		this.modele.getDemandeDeLivraison().majCoutTournee();

		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());

		this.modele.getTournee().modifierTournee(derniere, this.livraisonAjoutee);

		this.modele.getTournee().getLivraisonsEnOrdre()
			.get(this.modele.getTournee().getLivraisonsEnOrdre().size() - 2).setFenetre(fenetreTemp);

		this.modele.getDemandeDeLivraison().majCoutTournee();

		this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());

		this.modele.changementEffectue();

	    }

	}

	this.modele.getDemandeDeLivraison().majHorairesDesLivraisons(this.modele.getTournee().getItineraires());
	this.modele.getDemandeDeLivraison().majCoutTournee();


    }

}