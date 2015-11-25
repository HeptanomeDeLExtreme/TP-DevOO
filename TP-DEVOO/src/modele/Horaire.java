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

}
