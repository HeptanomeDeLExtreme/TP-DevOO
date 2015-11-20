package vue;

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

	// A ENLEVER JUSTE POUR LE DEBUG
	protected Plan p;
	// FIN A ENLEVER
	
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
	
	private final int hauteurCadreMessages = 80;
	private final int largeurVueTextuelle = 400;
	
    /**
     * Default constructor
     */
    public FenetreIHM(Plan p, int echelle) {
    	
		this.p = p;
		
    	setLayout(null);
		creerMenu();
		cadreMessages = new JLabel();
		cadreMessages.setBorder(BorderFactory.createTitledBorder("Messages..."));
		getContentPane().add(cadreMessages);
		vueGraphique = new VueGraphique(p,echelle, this);
		vueTextuelle = new VueTextuelle(this);
		ecouteurSouris = new EcouteurSouris();
		addMouseListener(ecouteurSouris);
		setTailleFenetre();
		setVisible(true);
		
    }

    private void creerMenu(){
    	// Crée l'écouteur de bouton
    	ecouteurBoutons =  new EcouteurBoutons();
    	
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
		int hauteurFenetre = Math.max(vueGraphique.getHauteur(),hauteurBoutons)+hauteurCadreMessages;
		int largeurFenetre = vueGraphique.getLargeur()+0+largeurVueTextuelle+10;
		setSize(largeurFenetre, hauteurFenetre);
		cadreMessages.setSize(largeurFenetre,60);
		cadreMessages.setLocation(0,hauteurFenetre-hauteurCadreMessages);
		vueGraphique.setLocation(0, 0);
		vueTextuelle.setSize(largeurVueTextuelle,hauteurFenetre-hauteurCadreMessages);
		vueTextuelle.setLocation(10+vueGraphique.getLargeur()+0,0);
	}

    public static void main(String[] args){
    	Intersection i1 = new Intersection(1, 10, 10);
    	Intersection i2 = new Intersection(2,30,20);
    	Set<Intersection> listeInter = new HashSet<Intersection>();
    	listeInter.add(i1);
    	listeInter.add(i2);
    	Plan p = new Plan(listeInter);
    	Set<Intersection> retInter = p.getIntersections();
    	for(Intersection inter : retInter){
    		int id = inter.getId();
    		int x = inter.getX();
    		int y = inter.getY();
    		System.out.println("id : "+id+" x : "+x+" y : "+y);
    	}
    	System.out.println(p.getHauteur());
    	System.out.println(p.getLargeur());
    	new FenetreIHM(p,10);
    }
}


