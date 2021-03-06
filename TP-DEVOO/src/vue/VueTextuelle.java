package vue;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.FenetreTemporelle;
import modele.Horaire;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Modele;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * Represente la vue textuelle de la tournee.
 */
public class VueTextuelle extends JScrollPane implements Observer{

	/**
	 * Le contenu de la zone de texte.
	 */
	private String text;
	/**
	 * Le modele represente.
	 */
	private Modele modele;
	/*
	 * La fenetre de contenance.
	 */
	private FenetreIHM fenetre;
	/*
	 * Le label d'ecriture.
	 */
	private JLabel label;

    /**
     * Constructeur par defaut.
     * @param modele Le modele represente. 
     * @param fenetreIHM La fenetre de contenance.
     */
    public VueTextuelle(Modele modele, FenetreIHM fenetreIHM) {
    	super();
    	this.fenetre = fenetreIHM;
    	this.modele = modele;
    	this.modele.addObserver(this);
		setBorder(BorderFactory.createTitledBorder("Tournée :"));
		label = new JLabel();
		this.setViewportView(label);
		fenetre.getContentPane().add(this);
    }
    
    protected void setText(String s){
    	this.label.setText(s);
    }
    
    /*
     * Change le texte
     * @param s le texte a mettre
     */
    protected void changeText(String s){
    	this.setText(s);
    }

    /**
     * Mettre a jour la vision de la tournee.
     */
    public void update(Observable observable, Object objet) {
    	String html = "<html>";
    	
    	Tournee tournee = modele.getTournee();
    	
    	if(tournee != null && tournee.getCoutTotal() != -1){
        	Livraison entrepot = tournee.getLivraisonsEnOrdre().get(0);
	    	List<Itineraire> listeItineraire = tournee.getItineraires();
	    	if(listeItineraire != null){
		    	for(Itineraire itineraire : listeItineraire){
		    		// Depart 
		    		Livraison depart = itineraire.getDepart();
		    		Intersection interDepart = depart.getAdresse();
		    		html +="Partir de x = "+interDepart.getX()+" y = "+interDepart.getY()+" id = "+interDepart.getId()+"<br>";
		    		
		    		// Troncons
		    		List<Troncon> listeTroncon = itineraire.getTroncons();
		    		for(Troncon tronc : listeTroncon){
		    			html += "Prendre "+tronc.getNomDeRue()+ " sur "+tronc.getLongueur()+"m.<br>";
		    		}
		    		
		    		// Arrivee
		    		Livraison arrivee = itineraire.getArrivee();
		    		Intersection interArrivee = arrivee.getAdresse();
		    		html +="Arriver à  x = "+interArrivee.getX()+" y = "+interArrivee.getY()+" id = "+interArrivee.getId()+"<br>";
		    		if(arrivee.getHeureArrivee() != null){
		    			html +="Heure d'arivée estimée : "+arrivee.getHeureArrivee()+"<br>";
		    		}
		    		if(arrivee.getHeureLivraison() != null){
		    			html +="Heure de livraison estimée : "+arrivee.getHeureLivraison()+"<br>";
		    		}
		    		if(arrivee != entrepot){
			    		if(! arrivee.getEstDansFenetre()){
			    			html += "<FONT color=\"red\">"+"RETARD"+"</FONT><br>";
			    		}
		    		}
		    		FenetreTemporelle fenetreArrivee = arrivee.getFenetre();
		    		if(fenetreArrivee != null){
		    			html +="Fenetre : "+arrivee.getFenetre().getHeureDebut()+" "+arrivee.getFenetre().getHeureFin()+"<br><br>";
		    		}
		    	}
		    	html += "Temps de tournée : "+new Horaire(tournee.getCoutTotal()) + "<br>";
		    	html += "Temps total : "+tournee.getDuree()+"<br><br>";
		        
		    	this.setText(html);
	    	}
    	}
    }

}