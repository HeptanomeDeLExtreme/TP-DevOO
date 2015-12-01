package modele;

public class Horaire {
	
	
    /**
     * Default constructor
     */
    public Horaire() {
    }

    /**
     * Constructeur de base de Horaire
     * @param int heure
     * @param int minute
     * @param int seconde
     */
    public Horaire(int heure, int minute, int seconde) {
    	this.heure = heure;
    	this.minute = minute;
    	this.seconde = seconde;
	}
    
    public Horaire(float duree) {
        int dureeEntiere = (int) duree;
        
        int heure = (int) dureeEntiere / 3600;
        int retenue = (int) dureeEntiere - heure * 3600;
        int min = retenue / 60;
        retenue = retenue - min * 60;
        int sec = retenue;
        
        this.heure = heure;
        this.minute = min;
        this.seconde = sec;	
    }
    
	/**
     * 
     */
	protected int heure;
	
	/**
     * 
     */
	protected int minute;
	
	/**
     * 
     */
	protected int seconde;
	
	@Override
	public String toString(){
		String s = " "+this.heure + "h " + this.minute+ "m " + this.seconde+"s";
		return s;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSeconde() {
		return seconde;
	}

	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
    
    public float horaireToDuree(){
        
        // Duree en secondes
        float duree = 0;
        
        duree += this.getHeure()*3600;
        duree += this.getMinute()*60;
        duree += this.getSeconde();
        
        return duree;
    }
    
    public Horaire additionnerHoraire(Horaire hor){
//    	System.out.println("Horaire à additionner : " + hor);
        
        float duree1 = this.horaireToDuree();
//        System.out.println("durée de du this : " + duree1);
        float duree2 = hor.horaireToDuree();
//        System.out.println("durée de du hor : " + duree2);
        
        float duree = duree1 + duree2;
//        System.out.println("Durée totale + " + duree);
        
        Horaire finalHor = new Horaire(duree);
//        System.out.println("Horaire finale : " + finalHor);
        
        return finalHor;
    }
    
    /**
     * @param heureDebut
     * @param heureFin
     * @return
     */
    public boolean isInFenetreTemporelle(Horaire heureDebut, Horaire heureFin) {
    	int duree = (int) this.horaireToDuree();
    	//System.out.println("Durée en seconde de l'horaire de livraison :" + duree);
    	int dureeDepart = (int) heureDebut.horaireToDuree();
    	//System.out.println("Durée eb seconde de l'horaire de début de fenetre : " + dureeDepart);
    	int dureeArrivee = (int) heureFin.horaireToDuree();
    	//System.out.println("Durée en seconde de l'horaire de fin de fenêtre : " + dureeArrivee);
    	boolean resultat = false;
    	
    	boolean dureeInferieureADureeArrivee = (duree <= dureeArrivee);
    	boolean dureeSuperieureADureeDepart = (dureeDepart <= duree);
    	boolean dureeDansLafenetre = ((dureeDepart <= duree) && (duree <= dureeArrivee));
    	
    	//System.out.println("Durée inférieure à la durée d'arrivée ? " + dureeInferieureADureeArrivee);
    	//System.out.println("Durée supérieure à la durée de départ ? : " + dureeSuperieureADureeDepart);
    	//System.out.println("Durée comprise dans la fenêtre de temps ? " + dureeDansLafenetre);
    	
    	if( (dureeDepart <= duree) && (duree <= dureeArrivee) ) {
    		resultat = true;
    	}
    	return resultat;
    }

	/**
	 * @param heureDebut
	 * @return
	 */
	public boolean isInferieurA(Horaire heureDebut) {
		float duree1 = this.horaireToDuree();
		//System.out.println("nombre de secondes de la livraison : " + duree1);
		float duree2 = heureDebut.horaireToDuree();
		//System.out.println("nombre de secondes de l'horaire de début de la fenetre de livraison : " + duree2);
		boolean resultat = false;
		if(duree1 <= duree2) {
			//System.out.println("Héhé, on peut faire la livraison pile à l'heure du début de la fenêtre ;)");
			resultat = true;
		}
		return resultat;
	}

}
