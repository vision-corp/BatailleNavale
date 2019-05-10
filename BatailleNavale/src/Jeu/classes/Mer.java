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
    /** Longueur par d�faut d'une mer instanci�e sans param�tres */
    final private int LONGUEUR_DEFAULT = 12;

    /** Largeur par d�faut d'une mer instanci�e sans param�tres */
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
     * Instanciation d'une mer sans arguments, avec valeurs par d�faut
     */
    public Mer() {
        this.longueur = LONGUEUR_DEFAULT;
        this.largeur = LARGEUR_DEFAULT;
        this.etatMer = new int[LONGUEUR_DEFAULT][LARGEUR_DEFAULT];
    }

    /**
     * Instanciation d'une mer sans arguments, avec valeurs par d�faut
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
     * @param x position en absisse du point cherch�
     * @param y position en ordonn�e du point cherch�
     * @return �tat de la case. 0 si rien, 1 si bateau, 2 si touch�
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
     * Ajout d'un bateau existant � la mer
     * @param bateau � ajouter
     */
    public void ajouterBateau(Bateau bateau) {
        bateaux.add(bateau);
    }

    /**
     * Place les bateaux dans la mer. Tous lesbateaux doivent �tre espac�s d'au minimum une case
     * pour que le placement soit valide, sinon on essai un autre placement jusqu'� en trouver
     * un correct.
     * @return indicateur de placement : true -> r�ussi, flase -> �chou�
     */
    public boolean placerBateaux(){
        int nbEssais = 0;
        for(Bateau bat : bateaux) { 
            boolean ok = true;
            while (ok && nbEssais < 30) {
                /* Choix al�atoire de points */
                int posX = (int)(Math.random() * 12);
                int posY = (int)(Math.random() * 12);

                /* V�rifie si le bateau rentre en longueur en tenant compte des bateaux d�j� plac�s */
                if (this.getLongueur() - (posX + bat.getTaille()) > 0) {
                    // teste si les cases sont occup�es
                    for (int i = 0; i < getLongueur(); i++) {
                        ok = ok && getEtatPosition(posX + i, posY) == 0;
                    }
                }

                /* Changement de sens si le bateau ne rentre pas dans la configuration pr�c�dente */
                if(!ok) {
                    bat.setSens(true);
                }

                /* V�rifie si le bateau rentre en hauteur en tenant compte des bateaux d�j� plac�s*/
                if (!ok && getLargeur() - (posY + bat.getTaille()) > 0) {
                    // teste si les cases sont occup�es
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
            
            //TODO v�rifications effectu�es pr�c�dament,reste juste � placer les bateaux dans la grille
        }
        return false;
        
        
    }
}
