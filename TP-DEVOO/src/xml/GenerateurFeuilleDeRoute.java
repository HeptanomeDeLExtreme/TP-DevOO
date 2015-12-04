/**
 * 
 */
package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import modele.Horaire;
import modele.Itineraire;
import modele.Livraison;
import modele.Tournee;
import modele.Troncon;


/**
 * @author nicolas
 *
 */
public class GenerateurFeuilleDeRoute {


	private static final int ANNULATION_UTILISATEUR = -1;
	private static final int SUCCES = 0;
	
	/**
	 * @param tournee : tournee pour laquelle on veut generer la feuille de route
	 * @return execCode : code d'execution, 0 si succes, -1 si echec 
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 * @throws ExceptionXML 
	 */
	public static int genererFeuilleDeRoute(Tournee tournee) throws FileNotFoundException, UnsupportedEncodingException, ExceptionXML{
		String nomFichier = "";
		
		 String sb = "TEST CONTENT";
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	nomFichier = chooser.getSelectedFile().toString();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    else{
	    	throw new ExceptionXML("Pas de fichier selectionné !");
	    }
	    
	    if(nomFichier == ""){
	    	return ANNULATION_UTILISATEUR;
	    }
		
		//TODO amelioration : décider du fichier à écrire avec JFileChooser
		PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
		
		writer.println("FEUILLE DE ROUTE : Durée totale = " + tournee.getDuree());
		Horaire horaireCoutTotal = new Horaire(tournee.getCoutTotal());
		writer.println("Durée totale sans les pauses = " + horaireCoutTotal);
		
		writer.println("");
		
		List<Itineraire> itineraires = tournee.getItineraires();
		Livraison entrepot = tournee.getLivraisonsEnOrdre().get(0);
		
		for(Itineraire iti : itineraires){
			writer.println("");
			writer.println("Livraison au " + iti.getArrivee().getAdresse() + " prévue à " + iti.getArrivee().getHeureArrivee());
			writer.println("Itinéraire : ");
			
//			List<Troncon> troncons = iti.getTroncons();
//			for(Troncon tr : troncons){
//				writer.println("Prenez la rue " + tr.getNomDeRue() + " sur " + tr.getLongueur() + " mètres.");
//			}
			
			
			List<Troncon> listeTroncon = iti.getTroncons();
    		float longueur = listeTroncon.get(0).getLongueur();
    		Troncon courant = null;
    		for(int i = 1;i<listeTroncon.size();i++){
    			courant = listeTroncon.get(i);
    			Troncon precedent = listeTroncon.get(i-1);
    			if(courant.getNomDeRue().equals(precedent.getNomDeRue())){
    				longueur += courant.getLongueur(); 
    			}
    			else{
    				writer.println("Prendre "+precedent.getNomDeRue()+ " sur "+longueur+"m."); 
    				longueur = courant.getLongueur();
    			}
    		}
    		if(courant != null ){
    			writer.println("Prendre "+courant.getNomDeRue()+ " sur "+longueur+"m."); 
    		}
    		if(listeTroncon.size() == 1 ){
    			writer.println("Prendre "+listeTroncon.get(0).getNomDeRue()+ " sur "+longueur+"m."); 
    		}
			
			
			Livraison arrivee = iti.getArrivee();
			writer.println("Heure d'arivée estimée : "+arrivee.getHeureArrivee());
			writer.println("Heure de livraison estimée : "+arrivee.getHeureLivraison());
			writer.println("Fenetre : "+arrivee.getFenetre());
			
			if(arrivee != entrepot){
	    		if(! arrivee.getEstDansFenetre()){
	    			writer.println("/!\\ ATTENTION RETARD /!\\<");
	    		}
    		}
			
			writer.println("");
		}
		
		
		
		writer.close();
		
		
		return SUCCES;
	}
	
	// TODO transformer en constructeur de Horaire ?
	public static Horaire dureeToHoraire(float duree){
		
		int dureeEntiere = (int) duree;
		
	    int heure = (int) dureeEntiere / 3600;
	    int retenue = (int) dureeEntiere - heure * 3600;
	    int min = retenue / 60;
	    retenue = retenue - min * 60;
	    int sec = retenue;

	    Horaire horaire = new Horaire(heure,min,sec);
	    
		return horaire;
	}
	
	
	
	
}
