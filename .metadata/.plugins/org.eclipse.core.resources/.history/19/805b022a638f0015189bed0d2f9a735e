
import java.util.*;

/**
 * 
 */
public interface Etat {

    /**
     * @param fenetre
     */
    protected void ouvrirPlan(FenetreIHM fenetre);

    /**
     * @param fenetre
     */
    protected void importerLivraison(FenetreIHM fenetre);

    /**
     * @param plan 
     * @param demandeDeLivraison
     */
    protected void calculerTournee(Plan plan, DemandeDeLivraison demandeDeLivraison);

    /**
     * @param fenetre
     */
    protected void ajouterLivraison(FenetreIHM fenetre);

    /**
     * @param demandeDeLivraison
     */
    protected void genererFeuilleRoute(DemandeDeLivraison demandeDeLivraison);

    /**
     * @param listeCommande
     */
    protected void undo(ListeCommande listeCommande);

    /**
     * @param listeCommande
     */
    protected void redo(ListeCommande listeCommande);

    /**
     * @param livraison1 
     * @param livraison2
     */
    protected void modifierLivraison(Livraison livraison1, Livraison livraison2);

    /**
     * @param livraison
     */
    protected void supprimeLivraison(Livraison livraison);

    /**
     * @param fenetre 
     * @param listeDeCommande
     */
    protected void clicDroit(FenetreIHM fenetre, ListeCommande listeDeCommande);

}