/*
 * Flotte.java						8 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble de bateaux constituant tous les navires d'un joueur
 * @author tom margalejo
 *
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

    /** Position des bateaux sur la mer */
    private int[][] etatMer;

    /** Ensemble des bateaux contenus dans la mer */
    List<Bateau> bateaux = new ArrayList<>();

    /**
     * Instanciation d'une mer sans arguments, avec valeurs par défaut
     */
    public Mer() {
        this.longueur = LONGUEUR_DEFAULT;
        this.largeur = LARGEUR_DEFAULT;
        this.etatMer = new int[LONGUEUR_DEFAULT][LARGEUR_DEFAULT];
    }

    /**
     * Instanciation d'une mer sans arguments, avec valeurs par défaut
     * @param longueur de la mer
     * @param largeur de la mer
     */
    public Mer(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.etatMer = new int[LONGUEUR_DEFAULT][LARGEUR_DEFAULT];
    }

    /**
     * @return la valeur de  etatMer
     */
    public int[][] getEtatMer() {
        return etatMer;
    }

    /**
     * TODO commenter le role et les attributs
     * @param x position en absisse du point cherché
     * @param y position en ordonnée du point cherché
     * @return état de la case. 0 si rien, 1 si bateau, 2 si touché
     */
    public int getEtatPosition(int x, int y){
        return etatMer[x][y];
    }

    /**
     * @param etatMer la nouvelle valeur de etatMer
     */
    public void setEtatMer(int[][] etatMer) {
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
     * @return indicateur de placement : true -> réussi, flase -> échoué
     */
    public boolean placerBateaux(){
        int nbEssais = 0;
        for(Bateau bat : bateaux) { 
            boolean ok = true;
            while (ok && nbEssais < 30) {
                /* Choix aléatoire de points */
                int posX = (int)(Math.random() * 12);
                int posY = (int)(Math.random() * 12);

                /* Vérifie si le bateau rentre en longueur en tenant compte des bateaux déjà placés */
                if (this.getLongueur() - (posX + bat.getTaille()) > 0) {
                    // teste si les cases sont occupées
                    for (int i = 0; i < getLongueur(); i++) {
                        ok = ok && getEtatPosition(posX + i, posY) == 0;
                    }
                }

                /* Changement de sens si le bateau ne rentre pas dans la configuration précédente */
                if(!ok) {
                    bat.setSens(true);
                }

                /* Vérifie si le bateau rentre en hauteur en tenant compte des bateaux déjà placés*/
                if (!ok && getLargeur() - (posY + bat.getTaille()) > 0) {
                    // teste si les cases sont occupées
                    for (int i = 0; i < getLargeur(); i++) {
                        ok = ok && getEtatPosition(posX, posY + i) == 0;
                    }
                }
                nbEssais++;
                if (ok) {
                    nbEssais = 0;
                    return true;
                }
            }
            
            //TODO vérifications effectuées précédament,reste juste à placer les bateaux dans la grille
        }
        return false;
        
        
    }
}
