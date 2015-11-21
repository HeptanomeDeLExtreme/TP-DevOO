package modele;

import java.util.*;
import java.util.Map.Entry;

/**
 * 
 */
public class DemandeDeLivraison {

    /**
     * Default constructor
     */
    public DemandeDeLivraison() {
    	
    	//TODO Implémenter le calcul du nombre de livraisons.
    }
    

    /**
     * 
     */
    protected Tournee tournee;

    /**
     * 
     */
    protected List<FenetreTemporelle> fenetres;

    /**
     * 
     */
    protected Livraison entrepot;

    /**
     * 
     */
    protected DemandeDeLivraison singleton;
    
    /**
     * 
     */
    protected int nbLivraisons;

    /**
     * 
     * @param map
     * @param value
     * @return
     */
    public static Integer getKeyByValue(Map<Integer, Livraison> map, Livraison value) {
    	
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
    * 
    * @param livraisonAvant
    * @param livraison
    */
    protected void ajouteLivraison(Livraison livraisonAvant, Livraison livraison) {
        // TODO implement here
    }
    
    /**
     * 
     * @return
     */
    protected Map<Integer,Livraison> correspondanceLivraisons (){
    	
    	Map<Integer,Livraison> mapLivraisons = new HashMap<Integer,Livraison>();
    	Integer livraisonInteger = 0;
    	
    	for(int i = 0; i<fenetres.size(); i++){
    		
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
     * 
     * @param map
     * @return tableauArcs
     */
    protected float[][] genererTableauArcs(Map<Integer,Livraison> map){
    	
    	float tableauArcs[][]= new float[nbLivraisons][nbLivraisons];
    	
    	for(int i = 0; i<fenetres.size()-1; i++){
    		FenetreTemporelle fenetreActuelle = fenetres.get(i);
    		FenetreTemporelle fenetreSuivante = fenetres.get(i+1);
    		
    		Set<Livraison> livraisonsFActuelle = fenetreActuelle.getLivraisons();
    		Set<Livraison> livraisonsFSuivante = fenetreSuivante.getLivraisons();
    		
    		for(Livraison livraisonSourceActuelle : livraisonsFActuelle){
    			
    			for(Livraison livraisonDestActuelle : livraisonsFActuelle){
    				if(livraisonDestActuelle.getId() != livraisonSourceActuelle.getId()){
    					Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    					Integer integerDest = getKeyByValue(map, livraisonDestActuelle);
    					tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestActuelle);					
    				}
    			}
    			
    			for(Livraison livraisonDestSuivante : livraisonsFSuivante){
    				Integer integerSource = getKeyByValue(map,livraisonSourceActuelle);
    				Integer integerDest = getKeyByValue(map, livraisonDestSuivante);
    				tableauArcs[integerSource][integerDest] = livraisonSourceActuelle.rechercherCout(livraisonDestSuivante);
    			}
    		}
    	}
    	
    	/*Traitement de la dernière Fenetre */ 
    	
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
    	
    	return tableauArcs;
    }
    
    /**
     * @param plan
     */
    protected void calculerTournee(Plan plan) {
        // TODO implement here
    	
    	Map<Integer,Livraison> mapLivraisons= correspondanceLivraisons();
    	float tableauArcs[][] = genererTableauArcs(mapLivraisons);
    	GrapheLivraisons graphe = new GrapheLivraisons(nbLivraisons, tableauArcs);
    }

    /**
     * @param plan 
     * @param depart 
     * @param arrivee
     */
    protected void calculPlusCourtChemin(Plan plan, Livraison depart, Livraison arrivee) {
        // TODO implement here
    }

    /**
     * 
     */
    protected void genereFeuilleDeRoute() {
        // TODO implement here
    }

}