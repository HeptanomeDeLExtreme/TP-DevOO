package modele;

import java.util.*;



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
//    	this.duree = (float) -1;
    	this.coutTotal = -1;
    	if(demandeDeLivraison != null){
    		this.demandeDeLivraison.nettoieDemandeDeLivraison();
    	}
    	this.entrepot = null;
    	this.itineraires = null;
    	this.livraisonsEnOrdre = null;
    }
    
    public void modifierTournee(Livraison livraison1, Livraison livraison2) {
    	
//    	System.out.println("DEBUT DE MODIFIER");
//    	System.out.println("Itineraires avant modif");
    	for (Itineraire it : itineraires)
    	{
//    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
    	}
    	
    	
    	
    	Livraison livraisonSuivante1 = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison1)+1);
    	Livraison livraisonSuivante2 = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison2)+1);
    	
    	supprimeLivraison(livraison1);
    	System.out.println("Suppr liv1 ok");
    	supprimeLivraison(livraison2);
    	System.out.println("Suppr liv2 ok");
    	
    	for (Livraison liv : livraisonsEnOrdre)
		{
		System.out.println(liv.getAdresse().getId());
		}
    	
    	// Gestion des cas où on veut intervertir deux intersections à la suite
    	if (livraisonSuivante1.equals(livraison2)){
    		ajouteLivraison(livraisonSuivante2, livraison1.getAdresse());
    		System.out.println("Réajout liv2 ok");
    		ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraisonSuivante2)-1), livraison2.getAdresse());
    		System.out.println("Réajout liv1 ok");}
    	
    	else if (livraisonSuivante2.equals(livraison1)){
    		ajouteLivraison(livraisonSuivante1, livraison2.getAdresse());
        	System.out.println("Réajout liv1 ok");
        	ajouteLivraison(livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraisonSuivante1)-1), livraison1.getAdresse());
        	System.out.println("Réajout liv2 ok");}
    
    	else{
 
    		ajouteLivraison(livraisonSuivante2, livraison1.getAdresse());
    		System.out.println("Réajout liv2 ok");
    		ajouteLivraison(livraisonSuivante1, livraison2.getAdresse());
    		System.out.println("Réajout liv1 ok");}
    	
    	
    	
    	
    	charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
    	
//    	System.out.println("Livraisons après modifs");
    	for (Livraison liv : livraisonsEnOrdre)
    		{
//    		System.out.println(liv.getAdresse().getId());
    		}
    	
//    	System.out.println("Itineraires après modif");
    	for (Itineraire it : itineraires)
    	{
//    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
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
    		if (livraison.equals(liv)){
    			livraison = liv;
    		}
    	}
    	
    	/*
    	 *  FIN TEST NICO
    	 */
    	
    	System.out.println("KV");
    	for(Livraison liv : livraisonsEnOrdre){
    		System.out.println(liv);
    	}
    	System.out.println("KF");
    	Livraison livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison) - 1 );
//    	System.out.println(" Livraison Précedente = " + livraisonPrecedente.getAdresse().getId());
    	Livraison livraisonSuivante = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison) +1 );
//    	System.out.println(" Livraison Suivante = " + livraisonSuivante.getAdresse().getId());
    	livraisonsEnOrdre.remove(livraison);
    	
    	int coutPrecedentToLivraison = 0;
    	int coutLivraisonToSuivant = 0;
    	int coutPrecedentToSuivant = 0;
    	
//    	System.out.println("Affichage des anciens itineraires");
//		for (Itineraire it : itineraires)
//		{
//		
//			System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
//		}
    	
    	Itineraire itiPrecedentToLivraison = new Itineraire();
    	Itineraire itiLivraisonToSuivant = new Itineraire();
    	//livraisonsEnOrdre.remove(livraison);
    	
//    	System.out.println("Mise à jour des livraisons");
//    	for (Livraison liv :livraisonsEnOrdre)
//    	{
//    		System.out.println("Id =" + liv.getAdresse().getId() );
//    	}
    	
    	for (Itineraire it : itineraires)
    	{
    		if (it.getArrivee().equals(livraison))
    		{
//    			System.out.println("HEUI on supprime precedentToLivraison");
    			coutPrecedentToLivraison = it.getCout();
    			itiPrecedentToLivraison = it;
    			break;
    		}	
    	}
    		//Suppression des itinéraires "Precedent à Livraison" et "Livraison à Suivant"
    		//Insertion de l'itineraire "Precedent à suivant"
    		itiLivraisonToSuivant=itineraires.get(itineraires.indexOf(itiPrecedentToLivraison)+1);
    		coutLivraisonToSuivant = itiLivraisonToSuivant.getCout();
    		
    		coutPrecedentToSuivant = livraisonPrecedente.rechercherCout(graphePondere.getMapCorrespondance(), livraisonSuivante);
			List <Troncon> tronconsPrecedentToSuivant = livraisonPrecedente.rechercherTroncons(graphePondere.getMapCorrespondance(), livraisonSuivante);
			Itineraire itiPrecedentToSuivant = new Itineraire(coutPrecedentToSuivant, tronconsPrecedentToSuivant, livraisonPrecedente, livraisonSuivante);
			
//			System.out.println("Génération de l'itinéraire " + itiPrecedentToSuivant.getDepart().getAdresse().getId() + " à " + itiPrecedentToSuivant.getArrivee().getAdresse().getId());
			
			itineraires.add(itineraires.indexOf(itiPrecedentToLivraison), itiPrecedentToSuivant);
			
//			System.out.println("Suppression de l'itinéraire " + itiLivraisonToSuivant.getDepart().getAdresse().getId() + " à " + itiLivraisonToSuivant.getArrivee().getAdresse().getId());
			itineraires.remove(itineraires.get(itineraires.indexOf(itiPrecedentToLivraison)+1));
			itineraires.remove(itiPrecedentToLivraison);
//			System.out.println("Suppression de l'itinéraire " + itiPrecedentToLivraison.getDepart().getAdresse().getId() + " à " + itiPrecedentToLivraison.getArrivee().getAdresse().getId());
//			System.out.println("Changement effectué");
			
    		coutTotal = coutTotal - coutLivraisonToSuivant - coutPrecedentToLivraison + coutPrecedentToSuivant ;
    		
    		charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
    		this.demandeDeLivraison.supprimeLivraison(livraison);
    		this.demandeDeLivraison.majHorairesDesLivraisons(itineraires);
//    		System.out.println("Affichage des nouveaux itineraires");
			for (Itineraire it : itineraires)
//			{			
//				System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
//			}
			this.changementEffectue();
    	}
    	
   
    
    public void setDuree(Horaire duree) {
		this.duree = duree;
	}



	/**
     * @param livraisonAvant 
     * @param livraison
     */
    public void ajouteLivraison(Livraison livraisonSuivante, Intersection intersectionCible) {
        // TODO implement here
    	//Récupération d'un ID non attribue
    	
    	List<Integer> listeIDLivraisons = new ArrayList<Integer>();
    	
    	ListIterator<Livraison> itr = livraisonsEnOrdre.listIterator();
    	
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
    	
    	livraisonsEnOrdre.add(livraisonsEnOrdre.indexOf (livraisonSuivante),livraison);
    	
    	Livraison livraisonPrecedente = new Livraison();
    	//Cas où on veut ajouter à la fin, soit avant le retour à l'entrepot.
    	if (livraisonSuivante.equals(entrepot)){
    		livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.size()-2);
    	}
    	else
    	{
    		livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison)-1);
    	}
//    	System.out.println("Affichage des nouvelles livraisons");
    	for(Livraison liv: livraisonsEnOrdre)
    	{
//    		System.out.println(liv.getAdresse().getId());
    	}
    	
//    	System.out.println("Anciens Itineraires");
    	for (Itineraire it : itineraires)
    	{
//    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
    	}
    	
    	int coutLivraisonToSuivant = livraison.rechercherCout(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	int coutPrecedentToLivraison = livraisonPrecedente.rechercherCout(graphePondere.getMapCorrespondance(), livraison);
    	
    	List <Troncon> tronconsLivraisonToSuivant = livraison.rechercherTroncons(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	List <Troncon> tronconsPrecedentToLivraison = livraisonPrecedente.rechercherTroncons(graphePondere.getMapCorrespondance(), livraison);
    	
    	Itineraire itLivraisonToSuivant = new Itineraire (coutLivraisonToSuivant, tronconsLivraisonToSuivant, livraison, livraisonSuivante);
    	
//    	System.out.println("ItLivraisonToSuivant");
//    	System.out.println("Itineraire de " + itLivraisonToSuivant.getDepart().getAdresse().getId() + " à " + itLivraisonToSuivant.getArrivee().getAdresse().getId());
    	
    	Itineraire itPrecedentToLivraison = new Itineraire (coutPrecedentToLivraison, tronconsPrecedentToLivraison, livraisonPrecedente, livraison);
//    	
//    	System.out.println("ItPrecedentToLivraison");
//    	System.out.println("Itineraire de " + itPrecedentToLivraison.getDepart().getAdresse().getId() + " à " + itPrecedentToLivraison.getArrivee().getAdresse().getId());
    	
    	int coutASoustraire = 0;
    	for(Itineraire it : this.itineraires){
    		if (it.getArrivee() == livraisonSuivante){
    			
//    			System.out.println("Hééééuiiiiiii j'ai trouvé");
//    			System.out.println("On remplace l'Itineraire de " + livraisonPrecedente.getAdresse().getId() + " à " + livraisonSuivante.getAdresse().getId());
//    			System.out.println("Par");
//    			System.out.println("L'itinéraire de " + itPrecedentToLivraison.getDepart().getAdresse().getId() + " à " + itPrecedentToLivraison.getArrivee().getAdresse().getId());
    			
    			coutASoustraire=it.getCout();
    			//it = itPrecedentToLivraison;
    			itineraires.add(itineraires.indexOf(it), itPrecedentToLivraison);
    			itineraires.add(itineraires.indexOf(it)+1, itLivraisonToSuivant);
    			itineraires.remove(it);
    			break;
    		}
    	}
//    	System.out.println("Nouveaux Itineraires");
    	for (Itineraire it : itineraires)
    	{
//    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
    	}
    	
    	coutTotal = coutTotal - coutASoustraire + coutLivraisonToSuivant + coutPrecedentToLivraison;
    	this.demandeDeLivraison.ajouteLivraison(livraisonSuivante, livraison);
    	this.demandeDeLivraison.majHorairesDesLivraisons(itineraires);
    	charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
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
	
	
}