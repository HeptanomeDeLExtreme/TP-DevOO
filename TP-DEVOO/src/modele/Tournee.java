package modele;

import java.util.*;

// DEBUG

/**
 * 
 */
public class Tournee extends Observable {

    private int coutTotal;

	private List<Livraison> livraisonsEnOrdre;

	private GraphePondere graphePondere;

	/**
     * Default constructor
     */
    public Tournee() {
    	this.coutTotal = -1;
    	this.retard = false;
    }
    
    

    public int getCoutTotal() {
		return coutTotal;
	}

    

	public boolean isRetard() {
		return retard;
	}



	/**
     * @param correspondancePlan 
     * @param coutTotal
     * @param livraisonsEnOrdre 
     * @param livraisonsEnOrdre
     */
    public void charge(Map<Integer, Intersection> correspondancePlan, DemandeDeLivraison ddl, Livraison entrepot, int coutTotal, List<Livraison> livraisonsEnOrdre, List<Itineraire> itinerairesEnOrdre) {
    	this.demandeDeLivraison = ddl;
    	this.entrepot = entrepot;
    	this.coutTotal = coutTotal;
    	this.livraisonsEnOrdre = livraisonsEnOrdre;
    	this.itineraires = itinerairesEnOrdre;

    	for(Itineraire itineraire : itinerairesEnOrdre){
    		Livraison depart = itineraire.getArrivee();
    		Livraison arrivee = itineraire.getDepart();
    		List<Troncon> listeTroncon = depart.rechercherTroncons(correspondancePlan,arrivee);
    	}
    	
    }
    
    /**
     * 
     */
    protected Horaire duree;

    /**
     * 
     */
    protected DemandeDeLivraison demandeDeLivraison;

    /**
     * 
     */
    protected Livraison entrepot;

    /**
     * 
     */
    protected List<Itineraire> itineraires;

	private boolean retard;

    public void nettoyer(){

    	this.coutTotal = -1;
    	if(demandeDeLivraison != null){
    		this.demandeDeLivraison.nettoieDemandeDeLivraison();
    	}
    	this.entrepot = null;
    	this.itineraires = null;
    	this.livraisonsEnOrdre = null;
    }
        
    
    public void modifierTournee(Livraison livraison1, Livraison livraison2) {
    	

    	
    	if(livraison2.equals(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2)))
    	{
    		Inversion(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3), livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2));
    		Inversion(livraison1,livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3));
    		Inversion(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3), livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2));
    	}
    	
    	else if(livraison1.equals(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2)))
    	{
    		Inversion(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3), livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2));
    		Inversion(livraison2,livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3));
    		Inversion(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-3), livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2));
    	}
    	
    	else{
    		Inversion(livraison1,livraison2);
    		}
   	
    }
    
    public void Inversion(Livraison livraison1, Livraison livraison2) {

    	for(Livraison liv : livraisonsEnOrdre){
    		if(liv.equals(livraison1)){
    			livraison1 = liv;
    		}
    		if(liv.equals(livraison2)){
    			livraison2 = liv;
    		}
    	}
      	
    	Livraison livraisonSuivante1 = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison1)+1);
    	Livraison livraisonSuivante2 = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison2)+1);
    	
    	FenetreTemporelle fenetre2 = livraison1.getFenetre();
    	FenetreTemporelle fenetre1 = livraison2.getFenetre();
    	
    	// Gestion des cas où on veut intervertir deux intersections à la suite
    	if (livraisonSuivante1.equals(livraison2))
    	{
    		
    		if (livraisonSuivante2.equals(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-1)))
    		{
    		// On veut intervertir les deux dernieres
    			supprimeLivraison(livraison1);
    			ajouteLivraison(livraison2,livraison1.getAdresse()).setFenetre(fenetre1);
    			supprimeLivraison(livraison2);    			
    			ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2), livraison2.getAdresse()).setFenetre(fenetre2);
    			
    		}
    		
    		else{
    			supprimeLivraison(livraison1);
    	    	supprimeLivraison(livraison2);
    			ajouteLivraison(livraisonSuivante2, livraison1.getAdresse()).setFenetre(fenetre1);
        		ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraisonSuivante2)-1), livraison2.getAdresse()).setFenetre(fenetre2);}
    		}
    	
    	else if (livraisonSuivante2.equals(livraison1))
    	{
    		// On veut intervertir les deux dernieres
    		if(livraisonSuivante1.equals(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-1)))
    		{
    			supprimeLivraison(livraison2);
    			ajouteLivraison(livraison1,livraison2.getAdresse()).setFenetre(fenetre2);
    			supprimeLivraison(livraison1);
    			ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2), livraison1.getAdresse()).setFenetre(fenetre1);	
    		}
    		else{
    			supprimeLivraison(livraison1);
    			supprimeLivraison(livraison2);
    			ajouteLivraison(livraisonSuivante1, livraison2.getAdresse()).setFenetre(fenetre2);
    			ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraisonSuivante1)-1), livraison1.getAdresse()).setFenetre(fenetre1);
    			}
    		}
    	
    	else{
    		supprimeLivraison(livraison1);
        	
    		ajouteLivraison(livraisonSuivante2, livraison1.getAdresse()).setFenetre(fenetre1);
    		supprimeLivraison(livraison2);
    		ajouteLivraison(livraisonSuivante1, livraison2.getAdresse()).setFenetre(fenetre2);
    	
    	
    	
    	
    	charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
    
    	
    	}
    	
    }

    /**
     * @param livraison
     */
    public void supprimeLivraison(Livraison livraison) {
        // 
    	//System.out.println("Vous essayez de supprimer" + livraison.getAdresse().getId());
    	//System.out.println("Livraisons dans l'ordre");
    	//for (Livraison livraisonOrdre : livraisonsEnOrdre){
    		//System.out.println(livraisonOrdre.getAdresse().getId());
    	//}
    	
    	/*
    	 *  TEST NICO
    	 */
    	
    	for(Livraison liv : livraisonsEnOrdre){
//    		System.out.println(liv+" "+livraison);
    		if (livraison.equals(liv)){
//    			System.out.println("eq");
    			livraison = liv;
    			break;
    		}
    	}
    	System.out.println();
    	
    	/*
    	 *  FIN TEST NICO
    	 */

    	Livraison livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison) - 1 );
    	Livraison livraisonSuivante = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison) +1 );
    	livraisonsEnOrdre.remove(livraison);
    	
    	int coutPrecedentToLivraison = 0;
    	int coutLivraisonToSuivant = 0;
    	int coutPrecedentToSuivant = 0;
    	

    	
    	Itineraire itiPrecedentToLivraison = new Itineraire();
    	Itineraire itiLivraisonToSuivant = new Itineraire();
    	

    	for (Itineraire it : itineraires)
    	{
    		if (it.getArrivee().equals(livraison))
    		{
    			coutPrecedentToLivraison = it.getCout();
    			itiPrecedentToLivraison = it;
    		}	
    	}
    		//Suppression des itinéraires "Precedent à Livraison" et "Livraison à Suivant"
    		//Insertion de l'itineraire "Precedent à suivant"
    		itiLivraisonToSuivant=itineraires.get(itineraires.indexOf(itiPrecedentToLivraison)+1);
    		coutLivraisonToSuivant = itiLivraisonToSuivant.getCout();
    		
    		coutPrecedentToSuivant = livraisonPrecedente.rechercherCout(graphePondere.getMapCorrespondance(), livraisonSuivante);
			List <Troncon> tronconsPrecedentToSuivant = livraisonPrecedente.rechercherTroncons(graphePondere.getMapCorrespondance(), livraisonSuivante);
			Itineraire itiPrecedentToSuivant = new Itineraire(coutPrecedentToSuivant, tronconsPrecedentToSuivant, livraisonPrecedente, livraisonSuivante);
			
			
			itineraires.add(itineraires.indexOf(itiPrecedentToLivraison), itiPrecedentToSuivant);
			
			itineraires.remove(itineraires.get(itineraires.indexOf(itiPrecedentToLivraison)+1));
			itineraires.remove(itiPrecedentToLivraison);
			
    		coutTotal = coutTotal - coutLivraisonToSuivant - coutPrecedentToLivraison + coutPrecedentToSuivant ;
    		this.demandeDeLivraison.supprimeLivraison(livraison);
    		charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
    		
    		this.demandeDeLivraison.majHorairesDesLivraisons(itineraires);
    		this.demandeDeLivraison.majCoutTournee();
    	
    	}
    	
   
    
    public void setDuree(Horaire duree) {
		this.duree = duree;
	}



	/**
     * @param livraisonAvant 
     * @param livraison
     */
    public Livraison ajouteLivraison(Livraison livraisonSuivante, Intersection intersectionCible) {
          

    	for(Livraison liv : livraisonsEnOrdre){

    		if(liv.equals(livraisonSuivante)){
    			livraisonSuivante = liv;
    		}
    	}	

    	
    	List<Integer> listeIDLivraisons = new ArrayList<Integer>();
    	
    	ListIterator<Livraison> itr = livraisonsEnOrdre.listIterator();
    	
    	;
    	
    	while (itr.hasNext()){
    		Livraison livraisontoID = itr.next();
    		listeIDLivraisons.add(livraisontoID.getId());
    	}
    	
    	Object max = Collections.max(listeIDLivraisons);
    	int id_max  = (Integer) max;
    	id_max++;
    	
    	// Affectation d'un client fixe, à changer en cas d'évolution
    	Client philippe= new Client(24);
    	
    	Livraison livraison = new Livraison(id_max,philippe, intersectionCible,livraisonSuivante.getFenetre() );
    	livraison.calculerPlusCourtsChemins(graphePondere);

    	
    	if (livraisonSuivante.equals(livraisonsEnOrdre.get(livraisonsEnOrdre.size()-1)))
    	{
    		
    		livraisonsEnOrdre.add(livraisonsEnOrdre.size()-1, livraison);
    	}
    
    	else{
    		
    		livraisonsEnOrdre.add(livraisonsEnOrdre.indexOf (livraisonSuivante),livraison);
    	}
    	
    	Livraison livraisonPrecedente = new Livraison();
    	//Cas où on veut ajouter à la fin, soit avant le retour à l'entrepot.
    	if (livraisonSuivante.equals(entrepot)){
    		livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2);
    	}
    	else
    	{
    		livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison)-1);
    	}

    	

    	
    	int coutLivraisonToSuivant = livraison.rechercherCout(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	int coutPrecedentToLivraison = livraisonPrecedente.rechercherCout(graphePondere.getMapCorrespondance(), livraison);
    	
    	List <Troncon> tronconsLivraisonToSuivant = livraison.rechercherTroncons(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	List <Troncon> tronconsPrecedentToLivraison = livraisonPrecedente.rechercherTroncons(graphePondere.getMapCorrespondance(), livraison);
    	
    	Itineraire itLivraisonToSuivant = new Itineraire (coutLivraisonToSuivant, tronconsLivraisonToSuivant, livraison, livraisonSuivante);
    	    	
    	Itineraire itPrecedentToLivraison = new Itineraire (coutPrecedentToLivraison, tronconsPrecedentToLivraison, livraisonPrecedente, livraison);
    	
    	int coutASoustraire = 0;
    	for(Itineraire it : this.itineraires){
    		if (it.getArrivee() == livraisonSuivante){
    			
    			
    			coutASoustraire=it.getCout();
    			
    			itineraires.add(itineraires.indexOf(it), itPrecedentToLivraison);
    			itineraires.add(itineraires.indexOf(it)+1, itLivraisonToSuivant);
    			itineraires.remove(it);
    			break;
    		}
    	}

    	
    	coutTotal = coutTotal - coutASoustraire + coutLivraisonToSuivant + coutPrecedentToLivraison;
    	this.demandeDeLivraison.ajouteLivraison(livraisonSuivante, livraison);
    	this.demandeDeLivraison.majHorairesDesLivraisons(itineraires);
    	this.demandeDeLivraison.majCoutTournee();
    	charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
    	return livraison;
    }
    
    
    
    public List<Itineraire> getItineraires() {
		return itineraires;
	}


    /**
     * @param preTournee
     */
    public Tournee(Map<FenetreTemporelle,List<Itineraire>> preTournee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genererFeuilleDeRoute() {
        // TODO implement here
    }
    
    public void changementEffectue(){
        setChanged(); 
        notifyObservers();
    }

	public void setItineraires(List<Itineraire> itineraires) {
		this.itineraires = itineraires;
	}

    public String toString(){
    	String toRet="";
    	for(Itineraire iti : itineraires){
    		List<Troncon> listeTroncon = iti.getTroncons();
    		for(Troncon tronc : listeTroncon ){
    			toRet += "\n";
    			toRet +=tronc;
    		}
    	}
    	return toRet;
    }

	public Horaire getDuree() {
		return duree;
	}

	public void setGraphePondere(GraphePondere graphePondere) {
		this.graphePondere = graphePondere;
	}

	public List<Livraison> getLivraisonsEnOrdre() {
		return livraisonsEnOrdre;
	}

	public void setLivraisonsEnOrdre(List<Livraison> livraisonsEnOrdre) {
		this.livraisonsEnOrdre = livraisonsEnOrdre;
	}



	public void setRetard(boolean b) {
		this.retard = b;
	}



	public void setCoutTotal(int coutTotalSolution) {
		this.coutTotal = coutTotalSolution;
	}
	
	
}