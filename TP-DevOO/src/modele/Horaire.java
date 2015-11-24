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
	
	/**
	 * Compare 2 horaires
	 * Utilisation : horaire1.compare(horaire2)
	 * retourne 1 si horaire1>horaire2, 0 si horaire1=horaire2, -1 si horaire1<horaire2
	 * @param Horaire horaire : horaire à comparer avec this
	 * @return int
	 */	
	public int compare(Horaire horaire){
		if(this.heure > horaire.getHeure()){
			return 1;
		}
		else if (this.heure < horaire.getHeure()){
			return -1;
		}
		else{
			if (this.minute > horaire.getMinute()){
				return 1;
			}
			else if (this.minute < horaire.getMinute()){
				return -1;
			}
			else{
				if (this.seconde > horaire.getSeconde()){
					return 1;
				}
				else if (this.seconde < horaire.getSeconde()){
					return -1;
				}
				else{
					return 0;
				}
			}
		}
	}
	
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
