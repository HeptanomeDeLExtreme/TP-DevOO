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
    
    public Horaire soustraireHoraire(Horaire hor) {

        float duree1 = this.horaireToDuree();

        float duree2 = hor.horaireToDuree();

        float duree = duree1 - duree2;

        Horaire finalHor = new Horaire(duree);

        return finalHor;
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
        
        float duree1 = this.horaireToDuree();

        float duree2 = hor.horaireToDuree();
        
        float duree = duree1 + duree2;
        
        Horaire finalHor = new Horaire(duree);
        
        return finalHor;
    }
    
    /**
     * @param heureDebut
     * @param heureFin
     * @return
     */
    public boolean isInFenetreTemporelle(Horaire heureDebut, Horaire heureFin) {
    	int duree = (int) this.horaireToDuree();
    	int dureeDepart = (int) heureDebut.horaireToDuree();
    	int dureeArrivee = (int) heureFin.horaireToDuree();

    	boolean resultat = false;
    	
    	boolean dureeInferieureADureeArrivee = (duree <= dureeArrivee);
    	boolean dureeSuperieureADureeDepart = (dureeDepart <= duree);
    	boolean dureeDansLafenetre = ((dureeDepart <= duree) && (duree <= dureeArrivee));
    	
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

		float duree2 = heureDebut.horaireToDuree();

		boolean resultat = false;
		if(duree1 <= duree2) {
			resultat = true;
		}
		return resultat;
	}

}
