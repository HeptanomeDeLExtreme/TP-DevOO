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
 * 
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
    
    /**
     * 
     */
    public void nettoieDemandeDeLivraison(){
    	fenetres = new ArrayList<FenetreTemporelle>();
    	this.entrepot = null;
    	int nbLivraisons = 0;
    	this.tsp = new TSP1();
    }
    
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
    /**
     * Cree un objet DemandeDeLivraison a partir d'un fichier XML.
     * 
     * @param fenetres
     * 	Fenetres temporelles decrites dans le XML.
     * 
     * @param entrepot
     * 	Entrepot defini dans le XML.
     */
    /*public DemandeDeLivraison(List<FenetreTemporelle> fenetres, Livraison entrepot) {
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
	}*/
    
    
    public int getNbLivraisons() {
		return nbLivraisons;
	}

	public void setNbLivraisons(int nbLivraisons) {
		this.nbLivraisons = nbLivraisons;
	}

    public void changementEffectue(){
        setChanged(); 
        notifyObservers();
    }
    
	public void chargerLivraison(Plan plan){
    	try {
    		fenetres = new ArrayList<FenetreTemporelle>();
    		
    		// Compte le nombre de livraisons du fichier XML
    		nbLivraisons = 0;
    	
    		for (FenetreTemporelle fenetre : fenetres) {
    			//System.out.println("Fenetre : "+fenetre);
    			Set<Livraison> livraisonsFActuelle = fenetre.getLivraisons();
    			//System.out.println("Nombre de livraisons : "+livraisonsFActuelle.size());
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
     * @param livraisonAvant 
     * @param livraison
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
    
    /**
     * @param plan
     */
    public void calculerTournee(Plan plan) {
    	
    	GraphePondere graphePondere = new GraphePondere(plan);
    	
    	this.tournee.setGraphePondere(graphePondere);
    	
    	// Calcul des plus courts chemins a partir d'un livraison sur tout le plan
    	calculDesPlusCourtsChemins(plan, graphePondere);
    	
//    	System.out.println("Map de correspondance entre sommets graphe plan et intersection :");
//    	System.out.println(graphePondere.mapCorrespondance);
    	
    	// Creation des correspondances entre un sommet (Integer) et une livraison
    	Map<Integer,Livraison> mapLivraisons = correspondanceLivraisons();
//    	System.out.println("Map de correspondance entre sommets graphe livraison et livraisons : ");
//    	System.out.println(mapLivraisons);
		
    	// Generation des arcs du graphe de livraisons
    	int couts[][] = genererTableauArcs(graphePondere.getMapCorrespondance(), mapLivraisons);
    	    	
//    	System.out.println("Tableau de cout : " + couts);
    	// Generation du graphe de livraisons
    	GrapheLivraisons graphe = new GrapheLivraisons(nbLivraisons, couts);
    	
    	// Recherche de la solution avec TSP
    	tsp.chercheSolution(1000, graphe);

    	// Recuperer l'ordre des livraisons a effectuer grace a TSP
    	LinkedList<Livraison> livraisonsEnOrdre = new LinkedList<Livraison>();
    	livraisonsEnOrdre = recupererLivraisonsEnOrdre(graphe, mapLivraisons);
    	
//    	System.out.println("");
//    	for(Livraison uneLiv : livraisonsEnOrdre) {
//    		System.out.println("Livraison : "+uneLiv);
//    		System.out.println(uneLiv.getFenetre());
//    	}
//    	System.out.println("");
    	
    	// Récupérer l'ordre des itinéraires entre les livraisons.
    	List<Itineraire> itinerairesEnOrdre = new LinkedList<Itineraire>();
    	itinerairesEnOrdre = recupererItinerairesEnOrdre(graphePondere.getMapCorrespondance(),livraisonsEnOrdre, mapLivraisons,couts);
    	
//    	System.out.println("");
//    	for(Itineraire iti : itinerairesEnOrdre){
//    		System.out.println("Itineraire : "+iti);
//    		List<Troncon> listTronc = iti.getTroncons();
//    		for(Troncon tronc : listTronc){
//    			System.out.println(tronc);
//    		}
//    		System.out.println();
//    	}
//    	System.out.println("");
    	
    	// Créer la tournée
    	int coutTotalSolution = tsp.getCoutSolution();
    	this.tournee.charge(graphePondere.getMapCorrespondance(),this, entrepot, coutTotalSolution, livraisonsEnOrdre, itinerairesEnOrdre);

    	// Mise à jour des données concernant le moment où la livraison s'effectue
    	majHorairesDesLivraisons(itinerairesEnOrdre);

    	this.tournee.changementEffectue();
    	
    	// Tests manuels D'ajouter /Modifier/supprimer
    	Set<Intersection> mesinters = plan.getIntersections();
    	Intersection intercible = new Intersection();
    	for (Intersection inter : mesinters){
    		//Donner ici l'iD de l'intersection
    		if (inter.getId()== 33){
    			intercible = inter;
    			
    		}
    		
    	}
    	Livraison livraisonSuivante = new Livraison();
    	for (Livraison livSuivante : tournee.getLivraisonsEnOrdre()){
    		//Donner ici l'iD de la livraison
    		if(livSuivante.getAdresse().getId() == 34){
    			livraisonSuivante =livSuivante;
    		}
    	}
    	
    	Livraison livraisonSuivante2 = new Livraison();
    	for (Livraison livSuivante : tournee.getLivraisonsEnOrdre()){
    		//Donner ici l'iD de la livraison
    		if(livSuivante.getAdresse().getId() == 42){
    			livraisonSuivante2 =livSuivante;
    		}
    	}
    	
    	//tournee.modifierTournee(livraisonSuivante, livraisonSuivante2);
    	//tournee.supprimeLivraison(livraisonSuivante);
    	//tournee.ajouteLivraison(livraisonSuivante, intercible);

    }

    /**
     * @param itinerairesEnOrdre
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
    		//System.out.println("Compteur : " + compteur);
    		
    		Livraison livraisonDepart = unItineraire.getDepart();
    		//System.out.println("Livraison de départ : " + livraisonDepart);
    		Livraison livraisonArrivee = unItineraire.getArrivee();
    		//System.out.println("Livraison d'arrivée : " + livraisonArrivee);
    		int coutItineraire = unItineraire.getCout();
    		//System.out.println("Coût de l'itinéraire : " + coutItineraire);
    		
    		if(compteur == 0) {
    			// 1er itinéraire : contient comme livraison de départ l'entrepôt.
    			//System.out.println("1er itinéraire !");
    			fenetre = livraisonArrivee.getFenetre();
    			coutTotal = coutItineraire;
    			livraisonDepart.setHeureArrivee(fenetre.getHeureDebut());
    			//System.out.println("Cout total pour cet itinéraire : " + coutTotal);
    		} else if(compteur == (itinerairesEnOrdre.size() - 1)) {
    			//System.out.println("Dernier itinéraire !");
    			// Dernier itinéraire : contient comme livraison d'arrivée l'entrepôt
    			coutTotal = coutItineraire + TEN_MINUTES;
    			//System.out.println("Cout total pour cet itinéraire : " + coutTotal);
    		} else { 
    			//System.out.println("Itinéraire normal !");
    			// Le reste des itinéraires
    			fenetre = livraisonArrivee.getFenetre();
    			coutTotal = coutItineraire + TEN_MINUTES;
    			//System.out.println("Cout total pour cet itinéraire : " + coutTotal);
    		}
    		
    		Horaire horaireCoutTotal = new Horaire(coutTotal);
    		//System.out.println("Horaire du coup total : " + horaireCoutTotal);
    		
    		if(compteur == 0) {
    			heureArrivee = livraisonDepart.getHeureArrivee().additionnerHoraire(horaireCoutTotal);
    		} else {
    			heureArrivee = livraisonDepart.getHeureLivraison().additionnerHoraire(horaireCoutTotal);
    		}
    		
    		//System.out.println("Heure d'arrivée pour la prochaine livraison : ");
    		//System.out.println(heureArrivee);
    		
    		// Calcul de l'heure de livraison
    		if(fenetrePrecedente != fenetre) {
    			//System.out.println("Ma fenêtre est différente de la fenêtre précédente !");
    			// Le reste des itinéraires
    			if(heureArrivee.isInferieurA(fenetre.getHeureDebut())) {
    				heureLivraison = fenetre.getHeureDebut();
    			} else {
    				heureLivraison = heureArrivee;
    			}
    		} else {
    			//System.out.println("Ma fenetre est la même que la précédente !");
    			heureLivraison = heureArrivee;
    		}
    		
    		if(compteur == 0) {
    			//System.out.println("Attention, je modifie l'heure de livraison parce que j'en suis au premier itinéraire !");
    			heureLivraison = heureArrivee;
    		} else if(compteur == (itinerairesEnOrdre.size() - 1)) {
    			//System.out.println("Attention, je modifie l'heure de livraison parce que j'en suis au dernier itinéraire !");
    			heureLivraison = null;
    		}
    		
    		// Savoir si l'heure de livraison est dans la fenêtre
    		if(compteur == (itinerairesEnOrdre.size() - 1)) {
    			//System.out.println("On s'en fiche de l'heure de livraison de l'entrepot");
    		} else {
    			//System.out.println("L'heure de livraison de la prochaine livraison est-elle dans la fenêtre ? :");
    			estDansFenetre = heureLivraison.isInFenetreTemporelle(fenetre.getHeureDebut(), fenetre.getHeureFin());
    			livraisonArrivee.setEstDansFenetre(estDansFenetre);
    		}
    		
    		livraisonArrivee.setHeureArrivee(heureArrivee);
    		livraisonArrivee.setHeureLivraison(heureLivraison);
//    		System.out.println("");
//    		System.out.println("----------");
//    		System.out.println("Pour la livraison : " + livraisonArrivee);
//    		System.out.println("Fenêtre de la livraison : " + fenetre);
//    		System.out.println("Heure d'arrivée sur le point de livraison : " + heureArrivee);
//    		System.out.println("Heure de livraison sur le point de livraison : " + heureLivraison);
//    		System.out.println("----------");
    		
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
    	
    	// TEST
    	
    	for(int compteur = 0; compteur < mapLivraisons.size(); compteur++){
    		Livraison result = mapLivraisons.get(compteur);
//    		System.out.println("compteur : "+compteur+"livraison : "+result);
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
//    		System.out.println(numSommetArrive+" "+numSommetEntrepot);
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
    	
    	
    	//TEST
    	for(int i =0; i < nbLivraisons; i++){
    		for(int j = 0; j < nbLivraisons; j++){
//    			System.out.println("tableauArc "+i+" "+j+" :"+tableauArcs[i][j]);
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
//    		System.out.println("Sol : "+numeroSommet);
    		Livraison livraison = mapLivraisons.get(numeroSommet);
//    		System.out.println(livraison);
        	livraisonsEnOrdre.add(livraison);
    	}    	
    	livraisonsEnOrdre.addLast(entrepot);
    	
    	livraisonsEnOrdre.getLast().setFenetre(fenetres.get(fenetres.size()-1));
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
//    	System.out.println("Liste des livraisons en ordre donné par TSP :");
//    	System.out.println(listeLivraisons);
    	for(int i = 0; i < listeLivraisons.size()-1; i++) {
    		Livraison livraisonActuelle = listeLivraisons.get(i);
    		Livraison livraisonSuivante = listeLivraisons.get(i+1);
    		 
    		int depart = getKeyByValue(mapLivraisons, livraisonActuelle);
    		int arrivee = getKeyByValue(mapLivraisons, livraisonSuivante);
    		int coutItineraire = couts[depart][arrivee];
//        	System.out.println("Depart : "+livraisonActuelle);
    		List <Troncon> troncons = livraisonActuelle.rechercherTroncons(map,livraisonSuivante);
    		for(Troncon tronc : troncons)
    		{
//    			System.out.println(tronc);
    		}
//    		System.out.println("Arrivee : "+livraisonSuivante);
//        	System.out.println();
    		Itineraire itineraire = new Itineraire(coutItineraire, troncons, livraisonActuelle, livraisonSuivante);
    		       	
    		itinerairesEnOrdre.add(itineraire);
    	}
    	
    	/*// Traitement de la fin de la tournee, depuis la derniere livraison
    	// jusqu'a l'entrepot de depart
    	Livraison derniereLivraison = listeLivraisons.getLast();
    	Livraison premiereLivraison = listeLivraisons.getFirst();
    	
    	int depart = getKeyByValue(mapLivraisons, derniereLivraison);
    	int arrivee = getKeyByValue(mapLivraisons, premiereLivraison);
    	int coutItineraire = couts[depart][arrivee];
//    	System.out.println("Depart : "+premiereLivraison);
//    	System.out.println("Arrivee : "+derniereLivraison);
//    	System.out.println();
    	List<Troncon> troncons = derniereLivraison.rechercherTroncons(map,premiereLivraison);
    	Itineraire itineraire = new Itineraire(coutItineraire, troncons, derniereLivraison, premiereLivraison);
    	itinerairesEnOrdre.add(itineraire);*/
    	
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