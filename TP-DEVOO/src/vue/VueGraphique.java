package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.util.*;

import javax.swing.JPanel;

import modele.DemandeDeLivraison;
import modele.FenetreTemporelle;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Modele;
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
	private Modele modele;
	private FenetreIHM fenetre;
	
	public static final float correcteurEchelle = (float) 0.95;
	
	public static final Color couleurEntrepot = Color.BLUE;
	public static final Color couleurIntersection = Color.LIGHT_GRAY;
	public static final Color couleurLivraison = Color.GREEN;
	public static final Color couleurLivraisonRetard = Color.RED;
	
    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueGraphique(Modele modele, FenetreIHM fenetreIHM) {
    	super();
    	this.fenetre = fenetreIHM;
    	this.modele = modele;
    	this.modele.addObserver(this);
;		this.echelleX = (float) 1.0;
		this.echelleY = (float) 1.0;
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(largeurVue, hauteurVue);
		fenetreIHM.getContentPane().add(this);
	
    }

	public void paintComponent(Graphics g) {
		
		Plan plan = modele.getPlan();
		Tournee tournee = modele.getTournee();
		DemandeDeLivraison demandeDeLivraison = modele.getDemandeDeLivraison();
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(couleurIntersection);
		
	
		Set<Intersection> listInter = plan.getIntersections();
		if(listInter != null && listInter.size()>0){
			
			// DESSINE LE PLAN 
			
			// Calcul de l'echelle en x
			Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			int largeurEcran = screen.width;
			int largeurPlan = plan.getLargeur();
			int largeurVG = largeurEcran - fenetre.getLargeurVueTextuelle();
			this.echelleX = (float)(largeurVG) / (float)largeurPlan;
			this.echelleX *= correcteurEchelle;
			this.fenetre.setEchelleX(echelleX);
			
			// Calcul de l'echelle en y
			int hauteurEcran = screen.height;
			int hauteurPlan = plan.getHauteur();
			int hauteurVG = hauteurEcran - fenetre.getHauteurCadreMessages();
			this.echelleY = (float)(hauteurVG) / (float)hauteurPlan ;
			this.echelleY *= correcteurEchelle;
			this.fenetre.setEchelleY(echelleY);
			
			for(Intersection inter : listInter){
				// Dessine les intersections		
				String id = inter.getId()+"";
				int x = (int) (inter.getX()*echelleX);
				int y = (int) (inter.getY()*echelleY);
				g.drawString(id, x-5, y-5);
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
			g.setColor(couleurEntrepot);
			Livraison entrepot = demandeDeLivraison.getEntrepot();
			if(entrepot != null){
				Intersection inter  = entrepot.getAdresse();
				int x = (int) (inter.getX()*echelleX);
				int y = (int) (inter.getY()*echelleY);
				g.fillOval(x, y, 10, 10);
			}
			List<FenetreTemporelle> listeFenetre = demandeDeLivraison.getFenetres();
			if(listeFenetre != null){
				g.setColor(couleurLivraison);
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
			List<Itineraire> listeItineraire = tournee.getItineraires();
			if(listeItineraire != null){
				
				
				for(Itineraire itineraire : listeItineraire){
					
					// Troncon
					List<Troncon> listeTroncon = itineraire.getTroncons();
					for(Troncon tronc : listeTroncon){
						Intersection origine = tronc.getOrigine();
						Intersection destination = tronc.getDestination();
						
						int x1 = (int) (origine.getX()*echelleX);
						int y1 = (int) (origine.getY()*echelleY);
						int x2 = (int) (destination.getX()*echelleX);
						int y2 = (int) (destination.getY()*echelleY);
						
			        	// Correction pour les fleche
			        	x1 +=5;
			        	y1 +=5;
			        	x2+=5;
			        	y2+=5;
			        	
			        	// Lien entre les deux
			        	g.setColor(couleurLivraison);
			        	g.drawLine(x1,y1,x2,y2);
			        	
			        	// Bout de la fleche
			        	double theta = Math.atan2(y2 - y1, x2 - x1);
			    		drawArrow(g2,theta,(double)x2,(double)y2);
					}
					
					// Depart
					Livraison arrivee = itineraire.getArrivee();
					if(arrivee != demandeDeLivraison.getEntrepot()){
						Intersection inter = arrivee.getAdresse();
						int x = (int) (inter.getX()*echelleX);
						int y = (int) (inter.getY()*echelleY);
						if(arrivee.getEstDansFenetre()){
							g.setColor(couleurLivraison);
						}
						else{
							g.setColor(couleurLivraisonRetard);
						}
						g.fillOval(x, y, 10, 10);
					}
					// Arrivee
//					Livraison depart = itineraire.getDepart();
//					inter = arrivee.getAdresse();
//					x = (int) (inter.getX()*echelleX);
//					y = (int) (inter.getY()*echelleY);
//					if(arrivee.getEstDansFenetre()){
//						g.setColor(couleurLivraison);
//					}
//					else{
//						g.setColor(couleurLivraisonRetard);
//					}
//					g.fillOval(x, y, 10, 10);

				}
			}
		}
		
		this.g = g;
	}
	
    private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
    {
    	int barb = 20;                   // barb length
        double phi = Math.PI/6;   
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
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