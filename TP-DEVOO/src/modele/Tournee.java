package modele;

import java.util.*;



/**
 * 
 */
public class Tournee extends Observable {

    private int coutTotal;

	private List<Livraison> livraisonsEnOrdre;


	/**
     * Default constructor
     */
    public Tournee() {
    }

    /**
     * @param correspondancePlan 
     * @param coutTotal
     * @param livraisonsEnOrdre 
     * @param livraisonsEnOrdre
     */
    public void charge(Map<Intersection, Integer> correspondancePlan, DemandeDeLivraison ddl, Livraison entrepot, int coutTotal, List<Livraison> livraisonsEnOrdre, List<Itineraire> itinerairesEnOrdre) {
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
    	
    	this.changementEffectue();
    }
    
    /**
     * 
     */
    protected Float duree;
    
    protected GraphePondere graphePondere;

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


    /**
     * @param livraison1 
     * @param livraison2
     */
    protected void modifierTournee(Livraison livraison1, Livraison livraison2) {
        // TODO implement here
    }

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison) {
        // TODO implement here
    }
    
	public List<Itineraire> getItineraires() {
		return itineraires;
	}
	
	public void setItineraires(List<Itineraire> itineraires) {
		this.itineraires = itineraires;
	}

	public List<Livraison> getLivraisonsEnOrdre() {
		return livraisonsEnOrdre;
	}

	public void setLivraisonsEnOrdre(List<Livraison> livraisonsEnOrdre) {
		this.livraisonsEnOrdre = livraisonsEnOrdre;
	}

	public GraphePondere getGraphePondere() {
		return graphePondere;
	}

	public void setGraphePondere(GraphePondere graphepondere) {
		this.graphePondere = graphepondere;
	}

	/**
     * @param livraisonAvant 
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraisonSuivante, Intersection intersectionCible) {
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
    	
    	Client philippe= new Client(24);
    	Livraison livraison = new Livraison(id_max,philippe, intersectionCible,livraisonSuivante.getFenetre() );
    	livraison.calculerPlusCourtsChemins(graphePondere);
    	
    	livraisonsEnOrdre.add(livraisonsEnOrdre.indexOf (livraisonSuivante),livraison);
    	Livraison livraisonPrecedente = livraisonsEnOrdre.get(livraisonsEnOrdre.indexOf(livraison)-1);
    	System.out.println("Affichage des nouvelles livraisons");
    	for(Livraison liv: livraisonsEnOrdre)
    	{
    		System.out.println(liv.getAdresse().getId());
    	}
    	
    	System.out.println("Anciens Itineraires");
    	for (Itineraire it : itineraires)
    	{
    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
    	}
    	
    	int coutLivraisonToSuivant = livraison.rechercherCout(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	int coutPrecedentToLivraison = livraisonPrecedente.rechercherCout(graphePondere.getMapCorrespondance(), livraison);
    	
    	List <Troncon> tronconsLivraisonToSuivant = livraison.rechercherTroncons(graphePondere.getMapCorrespondance(), livraisonSuivante);
    	List <Troncon> tronconsPrecedentToLivraison = livraisonPrecedente.rechercherTroncons(graphePondere.getMapCorrespondance(), livraison);
    	
    	Itineraire itLivraisonToSuivant = new Itineraire (coutLivraisonToSuivant, tronconsLivraisonToSuivant, livraison, livraisonSuivante);
    	
    	System.out.println("ItLivraisonToSuivant");
    	System.out.println("Itineraire de " + itLivraisonToSuivant.getDepart().getAdresse().getId() + " à " + itLivraisonToSuivant.getArrivee().getAdresse().getId());
    	
    	Itineraire itPrecedentToLivraison = new Itineraire (coutPrecedentToLivraison, tronconsPrecedentToLivraison, livraisonPrecedente, livraison);
    	
    	System.out.println("ItPrecedentToLivraison");
    	System.out.println("Itineraire de " + itPrecedentToLivraison.getDepart().getAdresse().getId() + " à " + itPrecedentToLivraison.getArrivee().getAdresse().getId());
    	
    	int coutASoustraire = 0;
    	for(Itineraire it : this.itineraires){
    		if (it.getArrivee() == livraisonSuivante){
    			
    			System.out.println("Hééééuiiiiiii j'ai trouvé");
    			System.out.println("On remplace l'Itineraire de " + livraisonPrecedente.getAdresse().getId() + " à " + livraisonSuivante.getAdresse().getId());
    			System.out.println("Par");
    			System.out.println("L'itinéraire de " + itPrecedentToLivraison.getDepart().getAdresse().getId() + " à " + itPrecedentToLivraison.getArrivee().getAdresse().getId());
    			
    			coutASoustraire=it.getCout();
    			//it = itPrecedentToLivraison;
    			itineraires.add(itineraires.indexOf(it), itPrecedentToLivraison);
    			itineraires.add(itineraires.indexOf(it)+1, itLivraisonToSuivant);
    			itineraires.remove(it);
    			break;
    		}
    	}
    	System.out.println("Nouveaux Itineraires");
    	for (Itineraire it : itineraires)
    	{
    		System.out.println("Itineraire de " + it.getDepart().getAdresse().getId() + " à " + it.getArrivee().getAdresse().getId());
    	}
    	
    	coutTotal = coutTotal - coutASoustraire + coutLivraisonToSuivant + coutPrecedentToLivraison;
    	
    	charge(graphePondere.getMapCorrespondance(), demandeDeLivraison,entrepot, coutTotal,livraisonsEnOrdre, itineraires );
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

	public float getDuree() {
		return 10; // TODO
	}
}