package vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import modele.Intersection;
import modele.Plan;
import modele.Tournee;

import controleur.Controleur;
/**
 * 
 */
public class FenetreIHM extends JFrame{

    protected EcouteurBoutons ecouteurBoutons;
    protected EcouteurSouris ecouteurSouris;
    protected VueGraphique vueGraphique;
    protected VueTextuelle vueTextuelle;
    protected Controleur controleur;
    
	private JMenuBar barreMenu;
	private JMenu menuFichier;
	private JMenu menuModifier;
	private JMenu menuHistorique;
	private JMenuItem chargerPlan;
	private JMenuItem chargerLivraison;
	private JMenuItem genererFeuilleRoute;
	private JMenuItem ajouterLivraison;
	private JMenuItem supprimerLivraison;
	private JMenuItem modifierLivraison;
	private JMenuItem undo;
	private JMenuItem redo;
	
	private JLabel cadreMessages;
	
	private final int hauteurCadreMessages = 100;
	private final int largeurVueTextuelle = 400;
	
	private float echelleX;
	private float echelleY;
	
    /**
     * Default constructor
     */
    public FenetreIHM(Tournee tournee, Controleur controleur, Plan plan) {
    	
		this.controleur = controleur;
		
		// Calcul de l'echelle en x
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largeurEcran = (int)screenSize.getWidth();
		int largeurPlan = controleur.getPlanLargeur();
		this.echelleX = (float)(largeurEcran - largeurVueTextuelle) / (float)largeurPlan;
		
		// Calcul de l'echelle en y
		int hauteurEcran = (int)screenSize.getHeight()-100;
		int hauteurPlan = controleur.getPlanHauteur();
		this.echelleY = (float)(hauteurEcran - hauteurCadreMessages) / (float)hauteurPlan ;
		
		System.out.println("ECHELLE : "+echelleX+" "+echelleY);
		
    	setLayout(null);
		creerMenu();
		cadreMessages = new JLabel();
		cadreMessages.setBorder(BorderFactory.createTitledBorder("Messages..."));
		getContentPane().add(cadreMessages);
		vueGraphique = new VueGraphique(tournee,plan,echelleX,echelleY,this);
		vueTextuelle = new VueTextuelle(tournee,this);
		ecouteurSouris = new EcouteurSouris(controleur,vueGraphique,this);
		addMouseListener(ecouteurSouris);
		setTailleFenetre();
		setVisible(true);
		
		
		System.out.println("Largeur Ecran : "+largeurEcran+" Hauteur Ecran : "+hauteurEcran);
		System.out.println("Largeur :"+(largeurVueTextuelle+largeurPlan*echelleX+100)+" Hauteur : "+(hauteurCadreMessages+hauteurPlan*echelleY+100));
		
    }

    
    
    public float getEchelleX() {
		return echelleX;
	}



	public float getEchelleY() {
		return echelleY;
	}



	private void creerMenu(){
    	// Crée l'écouteur de bouton
    	ecouteurBoutons =  new EcouteurBoutons(this.controleur);
    	
    	// Construit la barre de menus
    	barreMenu = new JMenuBar();
    	
    	// Construit le menu fichier
    	menuFichier = new JMenu("Fichier");
    	chargerPlan = new JMenuItem("ChargerPlan");
    	chargerLivraison = new JMenuItem("ChargerLivraison");
    	genererFeuilleRoute = new JMenuItem("Generer Feuille de Route");
    	menuFichier.add(chargerPlan);
    	menuFichier.add(chargerLivraison);
    	menuFichier.add(genererFeuilleRoute);
    	
    	// Construit le menu modifier
    	menuModifier = new JMenu("Modifier");
    	ajouterLivraison = new JMenuItem("Ajouter Livraison");
    	modifierLivraison = new JMenuItem("Modifier Livraison");
    	supprimerLivraison = new JMenuItem("Supprimer Livraison");
    	menuModifier.add(ajouterLivraison);
    	menuModifier.add(modifierLivraison);
    	menuModifier.add(supprimerLivraison);
    	
    	// Construit le menu historique
    	menuHistorique = new JMenu("Historique");
    	undo = new JMenuItem("Undo");
    	redo = new JMenuItem("Redo");
    	menuHistorique.add(undo);
    	menuHistorique.add(redo);
    	
    	// Ajoute les ecouteurs de boutons
    	chargerPlan.addActionListener(ecouteurBoutons);
    	chargerLivraison.addActionListener(ecouteurBoutons);
    	genererFeuilleRoute.addActionListener(ecouteurBoutons);
    	ajouterLivraison.addActionListener(ecouteurBoutons);
    	modifierLivraison.addActionListener(ecouteurBoutons);
    	supprimerLivraison.addActionListener(ecouteurBoutons);
    	undo.addActionListener(ecouteurBoutons);
    	redo.addActionListener(ecouteurBoutons);

    	// Ajoute les menus à la barre de menu
    	barreMenu.add(menuFichier);
    	barreMenu.add(menuModifier);
    	barreMenu.add(menuHistorique);
    	
    	// Ajoute la barre de menu a la fenetre
    	this.setJMenuBar(barreMenu);
    }
    
    private void setTailleFenetre() {
		int hauteurBoutons = 0;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int hauteurFenetre = Math.max(vueGraphique.getHauteur(),hauteurBoutons)+hauteurCadreMessages;
		int hauteurFenetre = (int) screenSize.getHeight()-20;
		int largeurFenetre = vueGraphique.getLargeur()+0+largeurVueTextuelle+10;
		setSize(largeurFenetre, hauteurFenetre);
		cadreMessages.setSize(largeurFenetre,60);
		cadreMessages.setLocation(0,hauteurFenetre-hauteurCadreMessages);
		vueGraphique.setLocation(0, 0);
		vueTextuelle.setSize(largeurVueTextuelle,hauteurFenetre-hauteurCadreMessages);
		vueTextuelle.setLocation(10+vueGraphique.getLargeur()+0,0);
	}

    public void afficheVueTextuelle(String s){
    	this.vueTextuelle.changeText(s);
    }
    
    public void afficheMessage(String s){
    	this.cadreMessages.setText(s);
    }

}


