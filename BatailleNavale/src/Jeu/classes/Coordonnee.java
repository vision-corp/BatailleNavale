/*
 * Coordonnee.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

/**
 * Coordonnee cart�sienne sur un plan 2D
 * @author 
 *
 */
public class Coordonnee {

    /** Coordonn�es sur un plan 2D en partant du coin sup�rieur gauche */

    /** Position en absisse sur le plan 2D en commancent � 1 */
    private int posX;

    /** Position en ordonn�e commancent � A sur un plan 2D*/
    private char posY;

    /**
     * commenter l'etat initial atteint
     */
    public Coordonnee() {
    }
    
    /**
     * @param noLigne num�ro de la ligne jou� par le joueur
     * @param noColonne num�ro de la colonne jou�e par le joueur (char)
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