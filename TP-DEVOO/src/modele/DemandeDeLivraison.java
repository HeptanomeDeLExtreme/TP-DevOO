package modele;

import java.awt.Point;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tsp.Graphe;
import tsp.TSP1;
import xml.DeserialiseurDemandeDeLivraisonXML;
import xml.DeserialiseurPlanXML;
import xml.ExceptionXML;

/**
 */
public class DemandeDeLivraison extends Observable{

	/**
     * La tournee qui correspond a la demande de livraisons. Vide lors de la 
     * creation de l'objet DemandeDeLivraison, elle est calculee par la methode
     * calculTournee.
     * 
     * @see calculerTournee
     */
    protected Tournee tournee;

    //TODO Java Doc
    protected TSP1 tsp;
    
    /**
     * Les fenetres listees dans le fichier XML de demande de livraisons.
     */
    protected List<FenetreTemporelle> fenetres;

    public TSP1 getTsp() {
		return tsp;
	}

	public void setTsp(TSP1 tsp) {
		this.tsp = tsp;
	}

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
    public DemandeDeLivraison() {}
    
    /**
     * @param tournee
     */
    public DemandeDeLivraison(Tournee tournee) {
    	this.tournee = tournee;
    	fenetres = new ArrayList<FenetreTemporelle>();
    	int nbLivraisons = 0;
    	this.tsp = new TSP1();
    }
     // TODO
    /**
     *
     */
    public void nettoieDemandeDeLivraison(){
    	fenetres = new ArrayList<FenetreTemporelle>();
    	this.entrepot = null;
    	int nbLivraisons = 0;
    	this.tsp = new TSP1();
    }
    
    //TODO
    public Livraison cherche(Point p, float echelleX, float echelleY){
   	
    	Set<Livraison> touteLivraison = new HashSet<Livraison>();
    	for(FenetreTemporelle fenetre : fenetres){
    		for(Livraison liv : fenetre.getLivraisons()){
    			touteLivraison.add(liv);
    		}
    	}
    	
    	Iterator<Livraison> it = touteLivraison.iterator();
			while (it.hasNext()){
				Livraison livrai = it.next();
				Intersection inters = livrai.getAdresse();
				if (inters.contient(p,echelleX,echelleY)){
					return livrai;
				}
			}
    	
    	return null;
    }
    
    public int getNbLivraisons() {
		return nbLivraisons;
	}

	public void setNbLivraisons(int nbLivraisons) {
		this.nbLivraisons = nbLivraisons;
	}
	
	//TODO JavaDoc
    public void changementEffectue(){
        setChanged(); 
        notifyObservers();
    }
    
    //TODO JavaDoc
	public void chargerLivraison(Plan plan){
    	try {
    		fenetres = new ArrayList<FenetreTemporelle>();
    		
    		// Compte le nombre de livraisons du fichier XML
    		nbLivraisons = 0;
    	
    		for (FenetreTemporelle fenetre : fenetres) {
    			
    			Set<Livraison> livraisonsFActuelle = fenetre.getLivraisons();
    			nbLivraisons += livraisonsFActuelle.size();
    		}
    		
    		// Ajout de l'entrepot
    		nbLivraisons++;    		
    		DeserialiseurDemandeDeLivraisonXML.charger(this,plan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			//System.out.println("Exception constructeur livraisons");			
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
    }

	 /**
     * Obtenir la cle d'un element d'un map en fonction de sa valeur.
     * @param map Map dans lequel on recherche
     * @param value Valeur dont on recherche la cle associee
     * @return Cle associee a la valeur
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
     * Supprime une livraison de sa fenetre.
     * @param livraison Livraison a supprimer des fenetres.
     */
    public void supprimeLivraison(Livraison livraison) {
        
    	for (FenetreTemporelle fenetre  : this.fenetres)
		{
    		Set<Livraison> livraisonsFenetres = fenetre.getLivraisons();
    		List<Livraison> listLivraisonsFenetre = new ArrayList<Livraison>(livraisonsFenetres);
    			
    			if (listLivraisonsFenetre.contains(livraison)){
    				fenetre.supprimeLivraison(livraison);
    				break;
    			}
			}
	}
    

    /**
     * Ajoute une livraison dans la meme fenetre que livraison Suivante
     * @param livraison a inserer
     * @param livraisonSuivante Livraison devant laquelle on insere la livraison
     */
    public void ajouteLivraison(Livraison livraisonSuivante, Livraison livraison) {
        // TODO implement here
    	{
            
        	for (FenetreTemporelle fenetre  : this.fenetres)
    		{
        		Set<Livraison> livraisonsFenetres = fenetre.getLivraisons();
        		List<Livraison> listLivraisonsFenetre = new ArrayList<Livraison>(livraisonsFenetres);
        			
        			if (listLivraisonsFenetre.contains(livraisonSuivante)){
        				fenetre.ajouteLivraison(livraison);
        				break;
        			}
        			
    			
    			}
    	}
    }

    //TODO
    // A EFFACER
    public Intersection getKeyByValue(Map<Intersection, Integer> map, Integer value) {
    	Intersection resultat = null;
    	Set<Intersection> setIntersection = map.keySet();
    	for(Intersection uneInter : setIntersection) {
    		if (value == map.get(uneInter)) {
    			resultat =  uneInter;
    		}
    	}
    	return resultat;
    }
    // A EFFACER
    
    /** Realise le calcul de la tournee a partir du plan et des livraisons importees.
     * @param plan Plan sur lequel on effectue le calcul.
     */
    public void calculerTournee(Plan plan) {
    	
    	GraphePondere graphePondere = new GraphePondere(plan);
    	
    	this.tournee.setGraphePondere(graphePondere);
    	
    	// Calcul des plus courts chemins a partir d'un livraison sur tout le plan
    	calculDesPlusCourtsChemins(plan, graphePondere);
    	
    	// Creation des correspondances entre un sommet (Integer) et une livraison
    	Map<Integer,Livraison> mapLivraisons = correspondanceLivraisons();
		
    	// Generation des arcs du graphe de livraisons
    	int couts[][] = genererTableauArcs(graphePondere.getMapCorrespondance(), mapLivraisons);
    	    	
    	// Generation du graphe de livraisons
    	GrapheLivraisons graphe = new GrapheLivraisons(nbLivraisons, couts);
    	
    	// Recherche de la solution avec TSP
    	tsp.chercheSolution(1000, graphe);

    	// Recuperer l'ordre des livraisons a effectuer grace a TSP
    	LinkedList<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	livraisonsEnOrdre = recupererLivraisonsEnOrdre(graphe, mapLivraisons);
    	
    	
    	// Récupérer l'ordre des itinéraires entre les livraisons.
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	itinerairesEnOrdre = recupererItinerairesEnOrdre(graphePondere.getMapCorrespondance(),livraisonsEnOrdre, mapLivraisons,couts);
    	
    	// Mise à jour des données concernant le moment où la livraison s'effectue
        majHorairesDesLivraisons(itinerairesEnOrdre);
        
        // Calcul du cout de la tournée + durée de la tournée
        int coutTotalSolution = tsp.getCoutSolution();
        int nbLivSansEntrepot = getNbLivraisons() - 1;
        coutTotalSolution += (10 * 60) * nbLivSansEntrepot;
        
        
        Horaire heureDepartEntrepotDebutTournee = livraisonsEnOrdre.get(1).getFenetre().getHeureDebut();
        Horaire heureArriveeEntrepotFinTournee = livraisonsEnOrdre.getLast().getHeureArrivee();
        Horaire tempsTournee = heureArriveeEntrepotFinTournee.soustraireHoraire(heureDepartEntrepotDebutTournee);

        
        // Créer la tournée
        this.tournee.charge(graphePondere.getMapCorrespondance(),this, entrepot, coutTotalSolution, livraisonsEnOrdre, itinerairesEnOrdre);
        this.tournee.setDuree(tempsTournee);
    }
    
    //TODO Java Doc
    /**
     * 
     */
    public void majCoutTournee(){

    	// Recalcul du cout de la tournée + durée de la tournée
    	int coutTotalSolution = 0;
    	List<Itineraire> itinerairesMAJ = tournee.getItineraires();
    	int nbLivraisonsSansEntrepots = tournee.getLivraisonsEnOrdre().size() - 2;
    	List<Livraison> livraisonsEnOrdre = tournee.getLivraisonsEnOrdre();
    	
    	for(Itineraire itineraire : itinerairesMAJ) {
    		coutTotalSolution += itineraire.getCout();
    	}
    	
    	coutTotalSolution += (10 * 60) * nbLivraisonsSansEntrepots;

    	
    	Horaire heureDepartEntrepotDebutTournee = livraisonsEnOrdre.get(1).getFenetre().getHeureDebut();
    	Horaire heureArriveeEntrepotFinTournee = livraisonsEnOrdre.get(livraisonsEnOrdre.size() - 1).getHeureArrivee();

		Horaire tempsTournee = heureArriveeEntrepotFinTournee.soustraireHoraire(heureDepartEntrepotDebutTournee);

		
		tournee.setCoutTotal(coutTotalSolution);
		tournee.setDuree(tempsTournee);
    }

    /** Mets a jour les horaires d'arrivee a chaquez livraison.
     * @param itinerairesEnOrdre Liste des itineraires a realiser en ordre.
     */
    public void majHorairesDesLivraisons(List<Itineraire> itinerairesEnOrdre) {
    	int compteur = 0;
    	int coutTotal = 0;
    	int TEN_MINUTES = 10 * 60;
    	Horaire TEN_MINUTES_HORAIRE = new Horaire(TEN_MINUTES);
    	FenetreTemporelle fenetre = null;
    	FenetreTemporelle fenetrePrecedente = null;
    	Horaire heureArrivee = null;
    	Horaire heureLivraison = null;
    	boolean estDansFenetre = false;
    	for(Itineraire unItineraire : itinerairesEnOrdre) {
    		
    		Livraison livraisonDepart = unItineraire.getDepart();

    		Livraison livraisonArrivee = unItineraire.getArrivee();

    		int coutItineraire = unItineraire.getCout();
    		
    		if(compteur == 0) {
    			fenetre = livraisonArrivee.getFenetre();
    			coutTotal = coutItineraire;
    			livraisonDepart.setHeureArrivee(fenetre.getHeureDebut());

    		} else if(compteur == (itinerairesEnOrdre.size() - 1)) {

    			// Dernier itinéraire : contient comme livraison d'arrivée l'entrepôt
    			coutTotal = coutItineraire + TEN_MINUTES;

    		} else { 

    			// Le reste des itinéraires
    			fenetre = livraisonArrivee.getFenetre();
    			coutTotal = coutItineraire + TEN_MINUTES;

    		}
    		
    		Horaire horaireCoutTotal = new Horaire(coutTotal);

    		if(compteur == 0) {
    			heureArrivee = livraisonDepart.getHeureArrivee().additionnerHoraire(horaireCoutTotal);
    		} else {
    			heureArrivee = livraisonDepart.getHeureLivraison().additionnerHoraire(horaireCoutTotal);
    		}
    		
    		
    		// Calcul de l'heure de livraison
    		if(fenetrePrecedente != fenetre) {
    			// Le reste des itinéraires
    			if(heureArrivee.isInferieurA(fenetre.getHeureDebut())) {
    				heureLivraison = fenetre.getHeureDebut();
    			} else {
    				heureLivraison = heureArrivee;
    			}
    		} else {

    			heureLivraison = heureArrivee;
    		}
    		
    		if(compteur == 0) {

    			heureLivraison = heureArrivee;
    		} else if(compteur == (itinerairesEnOrdre.size() - 1)) {

    			heureLivraison = null;
    		}
    		
    		// Savoir si l'heure de livraison est dans la fenêtre
    		if(compteur == (itinerairesEnOrdre.size() - 1)) {

    		} else {

    			estDansFenetre = heureLivraison.isInFenetreTemporelle(fenetre.getHeureDebut(), fenetre.getHeureFin());
    			livraisonArrivee.setEstDansFenetre(estDansFenetre);
    			if(estDansFenetre == false){
    				this.tournee.setRetard(true);
    			}
    		}
    		
    		livraisonArrivee.setHeureArrivee(heureArrivee);
    		livraisonArrivee.setHeureLivraison(heureLivraison);
    		
    		fenetrePrecedente = fenetre;
    		compteur++;
    	}
    }

	/**
     * @param heureDebut
     * @param coutItineraire
     * 	En seconde.
     * @return
     */
    private Horaire calculerHeureArrivee(Horaire heureDebut, long coutItineraire) {
		Horaire horaire = new Horaire(coutItineraire);
		Horaire resultatAdd = heureDebut.additionnerHoraire(horaire);
		Horaire resultat = new Horaire(resultatAdd.getHeure(), resultatAdd.getMinute(), resultatAdd.getSeconde());
		return resultat;
	}
    
    /**
     * @param heure
     * @param horaireDebut
     * @param horaireArrivee
     * @return
     */
    private boolean isHeureDansFenetreTemporelle(Horaire heure, Horaire horaireDebut, Horaire horaireArrivee) {
    	boolean resultat = heure.isInFenetreTemporelle(horaireDebut, horaireArrivee);
    	return resultat;
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
    protected int[][] genererTableauArcs(Map<Integer, Intersection> map2, Map<Integer,Livraison> map){
//    	System.out.println("NbLivraisons : "+nbLivraisons);
    	int tableauArcs[][]= new int[nbLivraisons][nbLivraisons];
    	
    	for(int i = 0;i<nbLivraisons;i++){
    		for(int j = 0;j<nbLivraisons;j++){
    			tableauArcs[i][j] = 0;
    		}
    	}
    	
    	// Creation d'un arc entre l'entrepot et les livraisons de 
    	// la premiere fenetre de livraisons
    	FenetreTemporelle fenetre = fenetres.get(0);
    	Set<Livraison> livraisonsFenetre = fenetre.getLivraisons();
    	Integer numSommetEntrepot = getKeyByValue(map, entrepot);
    	
    	for(Livraison livraisonArrivee : livraisonsFenetre) {
    		Integer numSommetArrive = getKeyByValue(map, livraisonArrivee);

    		tableauArcs[numSommetEntrepot][numSommetArrive] = 
    				entrepot.rechercherCout(map2, livraisonArrivee);
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
    					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(map2,livraisonDestActuelle);					
    				}
    			}
    			
    			// Creation des arcs entre la livraison courante et le reste
    			// des livraisons de la fenetre suivante
    			for(Livraison livraisonDestSuivante : livraisonsFSuivante){
    				Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    				Integer integerDest = getKeyByValue(map, livraisonDestSuivante);
    				tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(map2,livraisonDestSuivante);
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
					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(map2,
							livraisonDestActuelle);					
				}
			}
    	}
    	
    	fenetreActuelle = fenetres.get(fenetres.size()-1);
    	livraisonsFActuelle = fenetreActuelle.getLivraisons();
    	for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
    		if(livraisonSourceActuelle.getId() != entrepot.getId()){
    			Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    			Integer integerEntrepot = getKeyByValue(map,entrepot);
    			tableauArcs[integerSource][integerEntrepot] = livraisonSourceActuelle.rechercherCout(map2,
    					entrepot);
    		}
    	}
    	
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
    	livraisonsEnOrdre.addLast(entrepot);
    	
    	List<Integer> listeIDLivraisons = new ArrayList<Integer>();
    	
    	ListIterator<Livraison> itr = livraisonsEnOrdre.listIterator();
    	
    	while (itr.hasNext()){
    		Livraison livraisontoID = itr.next();
    		listeIDLivraisons.add(livraisontoID.getId());
    	}
    	
    	Object max = Collections.max(listeIDLivraisons);
    	int id_max  = (Integer) max;
    	id_max++;
    	livraisonsEnOrdre.getLast().setId(id_max);
    	livraisonsEnOrdre.getLast().setFenetre(fenetres.get(fenetres.size()-1));
    	livraisonsEnOrdre.getLast().setEstDansFenetre(true);
		return livraisonsEnOrdre;
	}
    
    /**
     * Permet de recuperer les itineraires entre chaque livraison.
     * @param map 
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
    private List<Itineraire> recupererItinerairesEnOrdre(Map<Integer, Intersection> map, LinkedList<Livraison> listeLivraisons, 
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

    		List <Troncon> troncons = livraisonActuelle.rechercherTroncons(map,livraisonSuivante);
    		
    		Itineraire itineraire = new Itineraire(coutItineraire, troncons, livraisonActuelle, livraisonSuivante);
    		       	
    		itinerairesEnOrdre.add(itineraire);
    	}
    	
    	
    	return itinerairesEnOrdre;
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