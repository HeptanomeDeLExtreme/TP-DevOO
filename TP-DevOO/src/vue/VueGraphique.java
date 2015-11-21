package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JPanel;

import modele.Intersection;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

/**
 * 
 */
public class VueGraphique extends JPanel implements Observer {

	private int echelleX;
	private int echelleY;
	private int hauteurVue;
	private int largeurVue;
	private Graphics g;
	private Tournee tournee;
	private FenetreIHM fenetre;
	private Plan plan;
	
    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueGraphique(Tournee tournee, Plan p, int echelleX, int echelleY, FenetreIHM fenetreIHM) {
    	super();
    	this.plan = p;
    	this.tournee = tournee;
    	this.tournee.addObserver(this);
		this.echelleX = echelleX;
		this.echelleY = echelleY;
		hauteurVue = p.getHauteur()*echelleY+100;
		largeurVue = p.getLargeur()*echelleX+100;
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(largeurVue, hauteurVue);
		fenetreIHM.getContentPane().add(this);
	
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		
		Set<Intersection> listInter = plan.getIntersections();
		for(Intersection inter : listInter){
			// Dessine les intersections		
			String id = inter.getId()+"";
			int x = inter.getX()*echelleX;
			int y = inter.getY()*echelleY;
			g.drawString(id, x-5, y-5);
			g.fillOval(x, y, 10, 10);
			
			// Dessine les tronçons
			Set<Troncon> tronconSortant = inter.getTronçonsSortant();
			for(Troncon tronc : tronconSortant){
				Intersection dest = tronc.getDestination();
				int x1 = inter.getX()*echelleX+5;
				int y1 = inter.getY()*echelleY+5;
				int x2 = dest.getX()*echelleX+5;
				int y2 = dest.getY()*echelleY+5;
				g.drawLine(x1,y1,x2,y2);
			}
		}
		this.g = g;
	}
	
	public int getEchelleX() {
		return echelleX;
	}

	public int getEchelleY() {
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