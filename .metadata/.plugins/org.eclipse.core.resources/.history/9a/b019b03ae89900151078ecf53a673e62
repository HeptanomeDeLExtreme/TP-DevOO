package modele;

public class Horaire {
	
	
    /**
     * Default constructor
     */
    public Horaire() {
    }

    /**
     * Heure de l'horaire
     */
	protected int heure;
	
	/**
     * Minutes de l'horaire
     */
	protected int minute;
	
	/**
     * Secondes de l'horaire
     */
	protected int seconde;
	
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
    
	/**
	 * Obtenir la duree d'un horaire
	 * @return Duree Horaire
	 */
    public float horaireToDuree(){
        
        // Duree en secondes
        float duree = 0;
        
        duree += this.getHeure()*3600;
        duree += this.getMinute()*60;
        duree += this.getSeconde();
        
        return duree;
    }
    /**
     * Realiser l'addition de deux horaires
     * @param hor Horaire a additionner
     * @return Horaire correspondant a l'addition des deux horaires.
     */
    public Horaire additionnerHoraire(Horaire hor){
        
        float duree1 = this.horaireToDuree();

        float duree2 = hor.horaireToDuree();
        
        float duree = duree1 + duree2;
        
        Horaire finalHor = new Horaire(duree);
        
        return finalHor;
    }
    
    /**Verifie que l'horaire se trouve dans une fenetre temporelle definie.
     * @param heureDebut Heure de debut de la fenetre
     * @param heureFin Heure de debut de la fin
     * @return Resultat du test.
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

	/**Verifie que l'horaire est plus tot qu'un autre horaire
	 * @param heureDebut Horaire a comparer
	 * @return Resultat du test
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
