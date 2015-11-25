package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JPanel;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * 
 */
public class VueGraphique extends JPanel implements Observer {

	private float echelleX;
	private float echelleY;
	private int hauteurVue;
	private int largeurVue;
	private Graphics g;
	private Tournee tournee;
	private FenetreIHM fenetre;
	private DemandeDeLivraison demandeDeLivraison;
	private Plan plan;
	
	public static final float correcteurEchelle = (float) 0.07;
	
    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueGraphique(DemandeDeLivraison demandeDeLivraison, Tournee tournee, Plan p, FenetreIHM fenetreIHM) {
    	super();
    	this.plan = p;
    	this.plan.addObserver(this);
    	this.fenetre = fenetreIHM;
    	this.demandeDeLivraison = demandeDeLivraison;
    	this.demandeDeLivraison.addObserver(this);
    	this.tournee = tournee;
    	this.tournee.addObserver(this);
		this.echelleX = (float) 1.0;
		this.echelleY = (float) 1.0;
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(largeurVue, hauteurVue);
		fenetreIHM.getContentPane().add(this);
	
    }

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		
	
		Set<Intersection> listInter = plan.getIntersections();
		if(listInter != null && listInter.size()>0){
			
			// DESSINE LE PLAN 
			
			// Calcul de l'echelle en x
			Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			int largeurEcran = screen.width;
			int largeurPlan = this.plan.getLargeur();
			int largeurVG = largeurEcran - fenetre.getLargeurVueTextuelle();
			this.echelleX = (float)(largeurVG) / (float)largeurPlan;
			this.echelleX -= correcteurEchelle;
			this.fenetre.setEchelleX(echelleX);
			
			// Calcul de l'echelle en y
			int hauteurEcran = screen.height;
			int hauteurPlan = this.plan.getHauteur();
			int hauteurVG = hauteurEcran - fenetre.getHauteurCadreMessages();
			this.echelleY = (float)(hauteurVG) / (float)hauteurPlan ;
			this.echelleY -= correcteurEchelle;
			this.fenetre.setEchelleY(echelleY);
			
			for(Intersection inter : listInter){
				// Dessine les intersections		
				String id = inter.getId()+"";
				int x = (int) (inter.getX()*echelleX);
				int y = (int) (inter.getY()*echelleY);
//				g.drawString(id, x-5, y-5);
				g.fillOval(x, y, 10, 10);
				
				// Dessine les troncons
				Set<Troncon> tronconSortant = inter.getTronconsSortant();
				for(Troncon tronc : tronconSortant){
					Intersection dest = tronc.getDestination();
					int x1 = (int) (inter.getX()*echelleX+5);
					int y1 = (int) (inter.getY()*echelleY+5);
					int x2 = (int) (dest.getX()*echelleX+5);
					int y2 = (int) (dest.getY()*echelleY+5);
					g.drawLine(x1,y1,x2,y2);
				}
			}
		
		
			// DESSINE LA DEMANDE DE LIVRAISON
			g.setColor(Color.BLUE);
			Livraison entrepot = this.demandeDeLivraison.getEntrepot();
			if(entrepot != null){
				Intersection inter  = entrepot.getAdresse();
				int x = (int) (inter.getX()*echelleX);
				int y = (int) (inter.getY()*echelleY);
				g.fillOval(x, y, 10, 10);
			}
			List<FenetreTemporelle> listeFenetre = this.demandeDeLivraison.getFenetres();
			if(listeFenetre != null){
				g.setColor(Color.GREEN);
				for(FenetreTemporelle fenetre : listeFenetre){
					Set<Livraison> livraisons = fenetre.getLivraisons();
					if(livraisons != null){
						for(Livraison livraison : livraisons){
							Intersection inter = livraison.getAdresse();
							int x = (int) (inter.getX()*echelleX);
							int y = (int) (inter.getY()*echelleY);
							g.fillOval(x, y, 10, 10);
						}
					}
				}
			}
			
			// DESSINE LA TOURNEE
			List<Itineraire> listeItineraire = this.tournee.getItineraires();
			if(listeItineraire != null){
		        List<Troncon> listeTroncon = new ArrayList<Troncon>();
		        for(int i = 0; i<listeItineraire.size();i++){
		        	Itineraire itineraire = listeItineraire.get(i);
		        	listeTroncon.addAll(itineraire.getTroncons());
		        }
		        
		        for(int i = 0; i<listeTroncon.size();i++){
		        	Troncon troncon = listeTroncon.get(i);
		        	if(troncon == null){continue;}
		        	Intersection origine = troncon.getOrigine();
		        	Intersection destination = troncon.getDestination();
		        	
		        	g.setColor(Color.GREEN);
		        	
		        	// Origine
		        	int x1 = (int) (origine.getX()*echelleX);
		        	int y1 = (int) (origine.getY()*echelleY);
//		        	g.fillOval(x1, y1, 10, 10);
		        	
		        	// Destination
		        	int x2 = (int) (destination.getX()*echelleX);
		        	int y2 = (int) (destination.getY()*echelleY);
//		        	g.fillOval(x2, y2, 10, 10);
//		        	
		        	// Lien entre les deux
		        	g.drawLine(x1+5,y1+5,x2+5,y2+5);
		        }
			}
		}
        
		this.g = g;
	}
	
	public float getEchelleX() {
		return echelleX;
	}

	public float getEchelleY() {
		return echelleY;
	}

	/**
     * @param observable 
     * @param objet
     */
    public void update(Observable observable, Object obj) {
        repaint();
    }

    public int getHauteur(){
    	return this.hauteurVue;
    }
    
    public int getLargeur(){
    	return this.largeurVue;
    }



}