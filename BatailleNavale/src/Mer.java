/*
 * Flotte.java						8 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble de bateaux constituant tous les navires d'un joueur
 * @author tom margalejo
 */
public class Mer {
    
    /** Longueur par défaut d'une mer instanciée sans paramètres */
    final private int LONGUEUR_DEFAULT = 12;
    
    /** Largeur par défaut d'une mer instanciée sans paramètres */
    final private int LARGEUR_DEFAULT = 12;
    
    /** Longueur de la mer en cases */
    private int longueur;
    
    /** Largeur de la mer en cases */
    private int largeur;
    
    /** ??? */
    private int etatMer;
    
    /** Ensemble des bateaux contenus dans la mer */
    List<Bateau> bateaux = new ArrayList<>();
    
    /**
     * Instanciation d'une mer sans arguments, avec valeurs par défaut
     */
    public Mer() {
        this.longueur = LONGUEUR_DEFAULT;
        this.largeur = LARGEUR_DEFAULT;
        this.etatMer = this.longueur * this.largeur;
    }
    
    /**
     * Instanciation d'une mer sans arguments, avec valeurs par défaut
     * @param longueur de la mer
     * @param largeur de la mer
     */
    public Mer(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.etatMer = this.longueur * this.largeur;
    }

    /**
     * @return la valeur de  etatMer
     */
    public int getEtatMer() {
        return etatMer;
    }

    /**
     * @param etatMer la nouvelle valeur de etatMer
     */
    public void setEtatMer(int etatMer) {
        this.etatMer = etatMer;
    }

    /**
     * @return la valeur de  longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @return la valeur de  largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @return la valeur de  bateaux
     */
    public List<Bateau> getBateaux() {
        return bateaux;
    }

    @Override
    public String toString() {
        return "Mer [longueur=" + longueur + ", largeur=" + largeur + ", etatMer=" + etatMer + ", bateaux=" + bateaux
                + "]";
    }
    
    /**
     * Ajout d'un bateau existant à la mer
     * @param bateau à ajouter
     */
    public void ajouterBateau(Bateau bateau) {
        bateaux.add(bateau);
    }
    
    /**
     * Place les bateaux dans la mer. Tous lesbateaux doivent être espacés d'au minimum une case
     * pour que le placement soit valide, sinon on essai un autre placement jusqu'à en trouver
     * un correct.
     */
    private void placerBateaux() {
        
    }
}
