/*
 * Coordonnee.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

/**
 * Coordonnee cartésienne sur un plan 2D
 * @author 
 *
 */
public class Coordonnee {

    /** Coordonnées sur un plan 2D en partant du coin supérieur gauche */

    /** Position en absisse sur le plan 2D en commancent à 1 */
    private int posX;

    /** Position en ordonnée commancent à A sur un plan 2D*/
    private char posY;

    /**
     * commenter l'etat initial atteint
     */
    public Coordonnee() {
    }
    
    /**
     * @param noLigne numéro de la ligne joué par le joueur
     * @param noColonne numéro de la colonne jouée par le joueur (char)
     */
    public Coordonnee(int noLigne, char noColonne) {
    	this.posX = noLigne;
    	this.posY = noColonne;
    }
    
    /**
     * @param coordonnees tableau contenant les deux position (X puis Y)
     */
    public Coordonnee(int[] coordonnees) {
    	this.posX = coordonnees[0];
    	this.posY = (char)coordonnees[1];
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public char getPosY() {
        return posY;
    }

}