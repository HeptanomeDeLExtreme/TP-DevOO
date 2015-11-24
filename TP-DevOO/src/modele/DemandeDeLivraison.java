package modele;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tsp.TSP1;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 * 
 */
public class DemandeDeLivraison {

	/**
     * La tournee qui correspond a la demande de livraisons. Vide lors de la 
     * creation de l'objet DemandeDeLivraison, elle est calculee par la methode
     * calculTournee.
     * 
     * @see calculerTournee
     */
    protected Tournee tournee;

    
    protected TSP1 tsp;
    /**
     * Les fenetres listees dans le fichier XML de demande de livraisons.
     */
    protected List<FenetreTemporelle> fenetres;

    /**
     * La livraison de depart et d'arrivee de la tournee.
     */
    protected Livraison entrepot;

    /**
     * Le nombre de livraisons totale du fichier XML de demande de livraisons +
     * la livraison representant l'entrepot
     */
    protected int nbLivraisons;
    
    /**
     * Default constructor
     */
    public DemandeDeLivraison() {
    	
    }
    
    /**
     * @param tournee
     */
    public DemandeDeLivraison(Tournee tournee) {
    	this.tournee = tournee;
    	fenetres = new ArrayList<FenetreTemporelle>();
    	int nbLivraisons = 0;
    	this.tsp = new TSP1();
    }
    
    public void nettoieDemandeDeLivraison(){
    	fenetres = new ArrayList<FenetreTemporelle>();
    	int nbLivraisons = 0;
    	this.tsp = new TSP1();
    }
    
    /**
     * Cree un objet DemandeDeLivraison a partir d'un fichier XML.
     * 
     * @param fenetres
     * 	Fenetres temporelles decrites dans le XML.
     * 
     * @param entrepot
     * 	Entrepot defini dans le XML.
     */
    public DemandeDeLivraison(List<FenetreTemporelle> fenetres, Livraison entrepot) {
		super();
		this.tsp = new TSP1();
		this.tournee = new Tournee();
		this.fenetres = fenetres;
		this.entrepot = entrepot;
		
		// Compte le nombre de livraisons du fichier XML
		int nbLivraisons = 0;
		
		for (FenetreTemporelle fenetre : fenetres) {
			Set<Livraison> livraisonsFActuelle = fenetre.getLivraisons();
			nbLivraisons += livraisonsFActuelle.size();
		}
		
		// Ajout de l'entrepot
		nbLivraisons++;
		
		this.nbLivraisons = nbLivraisons;
	}
    
    
    public int getNbLivraisons() {
		return nbLivraisons;
	}

	public void setNbLivraisons(int nbLivraisons) {
		this.nbLivraisons = nbLivraisons;
	}

	public void chargerLivraison(Plan plan){
    	try {
    		fenetres = new ArrayList<FenetreTemporelle>();
    		
    		// Compte le nombre de livraisons du fichier XML
    		nbLivraisons = 0;
    	
    		for (FenetreTemporelle fenetre : fenetres) {
    			System.out.println("Fenetre : "+fenetre);
    			Set<Livraison> livraisonsFActuelle = fenetre.getLivraisons();
    			System.out.println("Nombre de livraisons : "+livraisonsFActuelle.size());
    			nbLivraisons += livraisonsFActuelle.size();
    		}
    		
    		// Ajout de l'entrepot
    		nbLivraisons++;
    		System.out.println("Nombre de livraisons totales : "+nbLivraisons);
    		
    		DeserialiseurDemandeDeLivraisonXML.charger(this,plan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			System.out.println("Exception constructeur livraisons");			
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
    }

    /**
     * 
     * @param map
     * @param value
     * @return
     */
    public Integer getKeyByValue(Map<Integer, Livraison> map, Livraison value) {
    	
        Integer key=0;
        for (Entry<Integer, Livraison> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                key=entry.getKey();
            }
        }
        return key;
    }
    
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

    /**
     * @param livraisonAvant 
     * @param livraison
     */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }

    /**
     * @param plan
     */
    public void calculerTournee(Plan plan) {
    	GraphePondere graphePondere = new GraphePondere(plan);
    	
    	// Calcul des plus courts chemins a partir d'un livraison sur tout le plan
    	calculDesPlusCourtsChemins(plan, graphePondere);

    	// Creation des correspondances entre un sommet (Integer) et une livraison
    	Map<Integer,Livraison> mapLivraisons = correspondanceLivraisons();
		
    	// Generation des arcs du graphe de livraisons
    	int couts[][] = genererTableauArcs(mapLivraisons);
    	
    	// Generation du graphe de livraisons
    	/*GrapheLivraisons graphe = new GrapheLivraisons(nbLivraisons, couts);
    	
    	// Recherche de la solution avec TSP
    	tsp.chercheSolution(0, graphe);
    	
    	// Recuperer l'ordre des livraisons a effectuer grace a TSP
    	LinkedList<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	livraisonsEnOrdre = recupererLivraisonsEnOrdre(graphe, mapLivraisons);
    	
    	// Récupérer l'ordre des itinéraires entre les livraisons.
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	itinerairesEnOrdre = recupererItinerairesEnOrdre(livraisonsEnOrdre, mapLivraisons,couts);
    	
    	// Créer la tournée
    	int coutTotalSolution = tsp.getCoutSolution();
    	Tournee tournee = new Tournee(this, entrepot, coutTotalSolution, itinerairesEnOrdre);
    */
    }



    /**
     * Permet de lancer le calcul des plus courts chemins entre des livraisons
     * (l'entrepot et le reste des livraisons dans le fichier de demande de
     * livraisons) et un plan.
     * Le resultat du calcul (tableau pi et d) est stocke directement dans 
     * l'objet livraison.
     * 
     * @param plan
     * 	Plan charge dans l'application.
     * 
     * @see Livraison
     */
    private void calculDesPlusCourtsChemins(Plan plan, GraphePondere graphePondere) {    	
    	// Recherche des plus courts chemins pour l'entrepôt mentionné dans le
    	// fichier XML
    	entrepot.calculerPlusCourtsChemins(graphePondere);  	
    	
    	// Recherche des plus courts chemins pour toutes les livraisons du fichier
    	// XML
    	Set<Livraison> livraisons;
    	
    	for (FenetreTemporelle fenetre : fenetres) {
    		
    		livraisons = fenetre.getLivraisons();
    		
    		for (Livraison livraisonDepart : livraisons) {
    			livraisonDepart.calculerPlusCourtsChemins(graphePondere);
    		}
    	}
	}
    
    /**
     * Cree un tableau de correspondance entre une livraison et un numero
     * de sommet (Integer).
     * Cette correspondance permet de connaitre la relation entre les sommets
     * du GrapheLivraisons et les livraisons.
     * 
     * @return map qui contient les correspondances entre un sommet et une
     * livraison.
     */
    private Map<Integer,Livraison> correspondanceLivraisons (){
    	
    	Map<Integer,Livraison> mapLivraisons = new HashMap<Integer,Livraison>();
    	
    	// Ajout de l'entrepot dans la mapLivraison
    	Integer livraisonInteger = 0;
    	mapLivraisons.put(livraisonInteger, entrepot);
    	livraisonInteger++;
    	
    	// Ajout du reste des livraisons dans la mapLivraison
    	for(int i = 0; i < fenetres.size(); i++){
    		
    		FenetreTemporelle fenetreActuelle = fenetres.get(i);
    		Set<Livraison> livraisonsFenetre = fenetreActuelle.getLivraisons();
    		
    		for(Livraison livraisonActuelle : livraisonsFenetre){
    			mapLivraisons.put(livraisonInteger, livraisonActuelle);
    			livraisonInteger++;
    		}	
    	}
    	
    	// TEST
    	
    	for(int compteur = 0; compteur < mapLivraisons.size(); compteur++){
    		Livraison result = mapLivraisons.get(compteur);
    		System.out.println("compteur : "+compteur+"livraison : "+result);
    	}
    	// TEST
    	
    	return mapLivraisons;
    	
    }
    
    /**
     * Permet de generer un double tableau de couts representant les couts
     * pour chaque arc du GrapheLivraison.
     * Le double tableau est base sur des Integer representant des livraisons :
     * cout[livraisonDepart][livraisonArrivee].
     * 
     * @param map
     * 	map de correspondance entre un sommet et une livraison.
     * 
     * @return tableauArcs
     * 	Double tableau de couts (int) qui represente un cout entre une
     * livraison de depart et une livraison d'arrivee.
     * 
     * @see correspondanceLivraisons
     */
    protected int[][] genererTableauArcs(Map<Integer,Livraison> map){
    	System.out.println("NbLivraisons : "+nbLivraisons);
    	int tableauArcs[][]= new int[nbLivraisons][nbLivraisons];
    	
    	// Creation d'un arc entre l'entrepot et les livraisons de 
    	// la premiere fenetre de livraisons
    	FenetreTemporelle fenetre = fenetres.get(0);
    	Set<Livraison> livraisonsFenetre = fenetre.getLivraisons();
    	Integer numSommetEntrepot = getKeyByValue(map, entrepot);
    	
    	for(Livraison livraisonArrivee : livraisonsFenetre) {
    		Integer numSommetArrive = getKeyByValue(map, livraisonArrivee);
    		System.out.println(numSommetArrive+" "+numSommetEntrepot);
    		tableauArcs[numSommetEntrepot][numSommetArrive] = 
    				entrepot.rechercherCout(livraisonArrivee);
    	}
    	
    	// Pour chaque fenetre, recuperation des livraisons de la fenetre
    	// actuelle et de la fenetre suivante.
    	// Pour chaque fenetre, creation d'un arc entre la livraison courante
    	// et le reste des livraisons de la fenetre ainsi que les livraisons
    	// de la fenetre suivante.
    	for(int i = 0; i < fenetres.size()-1; i++){
    		FenetreTemporelle fenetreActuelle = fenetres.get(i);
    		FenetreTemporelle fenetreSuivante = fenetres.get(i+1);
    		
    		Set<Livraison> livraisonsFActuelle = fenetreActuelle.getLivraisons();
    		Set<Livraison> livraisonsFSuivante = fenetreSuivante.getLivraisons();
   		
    		for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
    			
    			// Creation des arcs entre la livraison courante et le reste
    			// des livraisons de la fenetre actuelle
    			for(Livraison livraisonDestActuelle : livraisonsFActuelle){
    				if(livraisonDestActuelle.getId() != livraisonSourceActuelle.getId()){
    					Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    					Integer integerDest = getKeyByValue(map, livraisonDestActuelle);
    					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestActuelle);					
    				}
    			}
    			
    			// Creation des arcs entre la livraison courante et le reste
    			// des livraisons de la fenetre suivante
    			for(Livraison livraisonDestSuivante : livraisonsFSuivante){
    				Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    				Integer integerDest = getKeyByValue(map, livraisonDestSuivante);
    				tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestSuivante);
    			}
    		}
    	}
    	
    	// Traitement de la derniere fenetre, pour chaque livraison de la fenetre
    	// creer un arc entre toutes les autres livraisons de la fenetre
    	FenetreTemporelle fenetreActuelle = fenetres.get(fenetres.size()-1);
    	Set<Livraison> livraisonsFActuelle = fenetreActuelle.getLivraisons();
    	
    	for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
			
			for(Livraison livraisonDestActuelle : livraisonsFActuelle){
			
				if(livraisonDestActuelle.getId() != livraisonSourceActuelle.getId()){
					Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
					Integer integerDest = getKeyByValue(map, livraisonDestActuelle);
					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestActuelle);					
				}
			}
    	}
    	//TEST
    	for(int i =0; i < nbLivraisons; i++){
    		for(int j = 0; j < nbLivraisons; j++){
    			System.out.println("tableauArc "+i+" "+j+" :"+tableauArcs[i][j]);
    		}
    	}
    	//TEST
    	return tableauArcs;
    }
    
    /**
	 * Permet de recuperer les livraisons dans l'ordre specifie par TSP.
	 * 
	 * @param graphe
	 * 	GrapheLivraison sur lequel a ete lance TSP.
	 * 
	 * @param tsp
	 * 	Instance de TSP1 sur laquelle a ete effectue le calcul.
	 * 
	 * @param mapLivraisons
	 * 	Map de correspondance permettant de retrouver les livraisons a partir
	 * du numero de sommet.
	 * 
	 * @return
	 * 	Liste de livraisons selon l'ordre trouve par TSP.
	 */
	private LinkedList<Livraison> recupererLivraisonsEnOrdre(GrapheLivraisons graphe, 
			 Map<Integer, Livraison> mapLivraisons) {
    	LinkedList<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	Integer nombreSommet = graphe.getNbSommets();
    	
    	for (int i = 0; i < nombreSommet; i++) {
    		Integer numeroSommet = tsp.getSolution(i);
    		Livraison livraison = mapLivraisons.get(numeroSommet);
        	livraisonsEnOrdre.add(livraison);
    	}
    	
		return livraisonsEnOrdre;
	}
    
    /**
     * Permet de recuperer les itineraires entre chaque livraison.
     * 
     * @param listeLivraisons
     * 	La liste des livraisons ordonnes apres l'appel a TSP.
     * 
     * @param mapLivraisons
     * 	La map de correspondance permettant de connaitre une livraison a
     * partir d'un sommet.
     * 
     * @return
     * 	Liste d'itinéraires en fonction de la liste de livraisons.
     */
    private List<Itineraire> recupererItinerairesEnOrdre(LinkedList<Livraison> listeLivraisons, 
    		Map<Integer, Livraison> mapLivraisons, int[][] couts){
 
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	
    	// Traitement de la tournee, depuis l'entrepot jusqu'a la derniere
    	// livraisons
    	for(int i = 0; i < listeLivraisons.size()-1; i++) {
    		Livraison livraisonActuelle = listeLivraisons.get(i);
    		Livraison livraisonSuivante = listeLivraisons.get(i+1);
    		 
    		int depart = getKeyByValue(mapLivraisons, livraisonActuelle);
    		int arrivee = getKeyByValue(mapLivraisons, livraisonSuivante);
    		int coutItineraire = couts[depart][arrivee];
    		List <Troncon> troncons = livraisonActuelle.rechercherTroncons(livraisonSuivante);
    		
    		Itineraire itineraire = new Itineraire(coutItineraire, troncons, livraisonActuelle, livraisonSuivante);
    		
    		itinerairesEnOrdre.add(itineraire);
    	}
    	
    	// Traitement de la fin de la tournee, depuis la derniere livraison
    	// jusqu'a l'entrepot de depart
    	Livraison derniereLivraison = listeLivraisons.getLast();
    	Livraison premiereLivraison = listeLivraisons.getFirst();
    	
    	int depart = getKeyByValue(mapLivraisons, derniereLivraison);
    	int arrivee = getKeyByValue(mapLivraisons, premiereLivraison);
    	int coutItineraire = couts[depart][arrivee];
    	List<Troncon> troncons = derniereLivraison.rechercherTroncons(premiereLivraison);
    	
    	return itinerairesEnOrdre;
    }


    /**
     * 
     */
    protected void genereFeuilleDeRoute() {
        // TODO implement here
    }
    
    /**
     * Permet d'ajouter une livraison. Ne doit servir que lors de la création de la demande de livraison lors de l'ajout de l'entrepot
     * @param Livraison entrepot
     */
	public void ajouteEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}
	
    /**
     * Permet d'ajouter une fenetre temporelle. Ne doit servir que lors de la création de la demande de livraison
     * @param FenetreTemporelle fenetreTemporelle
     */
	public void ajouteFenetreTemporelle(FenetreTemporelle fenetreTemporelle) {
		this.fenetres.add(fenetreTemporelle);
	}
	
	@Override
	public String toString() {
		String s = this.entrepot.toString() + "/n";
		for (FenetreTemporelle f : this.fenetres) {
			s += f.toString() +"/n";	 // TODO FenetreTemporelle.toString()
		}
		return s;
	}

	public Tournee getTournee() {
		return tournee;
	}

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

	public List<FenetreTemporelle> getFenetres() {
		return fenetres;
	}

	public void setFenetres(List<FenetreTemporelle> fenetres) {
		this.fenetres = fenetres;
	}

	public Livraison getEntrepot() {
		return entrepot;
	}

	public void setEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}


}