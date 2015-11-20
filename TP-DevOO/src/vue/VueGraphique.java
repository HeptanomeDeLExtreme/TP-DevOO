package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JPanel;

import modele.Intersection;
import modele.Plan;
import modele.Tournee;

/**
 * 
 */
public class VueGraphique extends JPanel implements Observer {

	private int echelle;
	private int hauteurVue;
	private int largeurVue;
	private Graphics g;
	private Plan plan;
	private Tournee tournee;
	private FenetreIHM fenetre;
	
    /**
     * @param plan 
     * @param tournee 
     * @param fenetreIHM
     */
    public VueGraphique(Plan p, int echelle, FenetreIHM fenetreIHM) {
    	super();
		this.plan = p;
//		plan.addObserver(this); // this observe plan
		this.echelle = echelle;
		hauteurVue = plan.getHauteur()*echelle+100;
		largeurVue = plan.getLargeur()*echelle+100;
		setLayout(null);
		setBackground(Color.white);
		setSize(largeurVue, hauteurVue);
		fenetreIHM.getContentPane().add(this);
	
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		
		Set<Intersection> listInter = plan.getIntersections();
		for(Intersection inter : listInter){
			int x = inter.getX()*echelle;
			int y = inter.getY()*echelle;
			g.fillOval(x, y, 10, 10);
		}
		this.g = g;
	}
	
    /**
     * @param observable 
     * @param objet
     */
    public void update(Observable observable, Object obj) {
        // TODO implement here
    }

    public int getHauteur(){
    	return this.hauteurVue;
    }
    
    public int getLargeur(){
    	return this.largeurVue;
    }



}