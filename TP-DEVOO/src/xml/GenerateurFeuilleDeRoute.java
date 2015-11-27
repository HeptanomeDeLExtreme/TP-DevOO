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
	 */
	public static int genererFeuilleDeRoute(Tournee tournee) throws FileNotFoundException, UnsupportedEncodingException{
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
	    
	    if(nomFichier == ""){
	    	return ANNULATION_UTILISATEUR;
	    }
		
		//TODO amelioration : décider du fichier à écrire avec JFileChooser
		PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
		
		writer.println("FEUILLE DE ROUTE : Durée totale = " + dureeToHoraire(tournee.getDuree()));
		writer.println("");
		
		List<Itineraire> itineraires = tournee.getItineraires();
		
		
		for(Itineraire iti : itineraires){
			writer.println("");
			writer.println("Livraison au " + iti.getArrivee().getAdresse() + " prévue à " + iti.getArrivee().getHeureArrivee());
			writer.println("Itinéraire : ");
			List<Troncon> troncons = iti.getTroncons();
			for(Troncon tr : troncons){
				writer.print(tr.getNomDeRue() + " - ");
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
