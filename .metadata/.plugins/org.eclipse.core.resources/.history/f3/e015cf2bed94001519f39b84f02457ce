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
		String s = " h " + this.heure + " m " + this.minute+ " s " + this.seconde;
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
    	System.out.println("Horaire à additionner : " + hor);
        
        float duree1 = this.horaireToDuree();
        System.out.println("durée de du this : " + duree1);
        float duree2 = hor.horaireToDuree();
        System.out.println("durée de du hor : " + duree2);
        
        float duree = duree1 + duree2;
        System.out.println("Durée totale + " + duree);
        
        Horaire finalHor = new Horaire(duree);
        System.out.println("Horaire finale : " + finalHor);
        
        return finalHor;
    }
    
    public boolean isInFenetreTemporelle(Horaire heureDebut, Horaire heureFin) {
    	int duree = (int) this.horaireToDuree();
    	int dureeDepart = (int) heureDebut.horaireToDuree();
    	int dureeArrivee = (int) heureFin.horaireToDuree();
    	boolean resultat = false;
    	
    	if( (dureeDepart <= duree) && (duree <= dureeArrivee) ) {
    		resultat = true;
    	}
    	return resultat;
    }

}
