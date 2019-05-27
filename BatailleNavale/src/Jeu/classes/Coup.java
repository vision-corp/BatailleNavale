/*
 * Coup.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

import java.util.Scanner;
import java.util.List;
import Jeu.classes.Coordonnee;
import Jeu.classes.Mer;

/**
 * Coup joué par l'utilisateur ainsi que toutes les méthodes
 * lui soyant lié
 * @author onsenfou
 */
public class Coup {

    /** Analyseur lexical de l'entrée standard */
    Scanner entree = new Scanner(System.in);

    /** Valeur non convetie entrée par l'utilisateur */
    private String valeurEntree;

    /**
     * commenter l'etat initial atteint
     * @param valeurEntree
     */
    public Coup(String valeurEntree) {
        this.valeurEntree = valeurEntree;
    }

    /**
     * Détermine si le coup joué par l'utilisateur est valide 
     * par rapport à la mer jouée
     * @param aTester chaine correspondant au coup joué
     * @param largeurMer largeur de la mer jouée
     * @param longueurMer 
     * @return valide : true si valide
     *                  false si faux
     */
    public boolean estValide(String aTester, int largeurMer, int longueurMer) {
        boolean valide;
        int[] coordConvertie;

        valide = false;
        if (!(aTester.length() <= 1 || aTester.length() > 3 ) && aTester.charAt(0) >= 65 && aTester.charAt(0) <= 90) {
            coordConvertie = convertion(aTester);

            /* test si l'axe des ordonnées saisie est correcte */
            if ((65 <= coordConvertie[1] && coordConvertie[1] < 65+largeurMer)
                    && (coordConvertie[0] > 0 && coordConvertie[0] <= longueurMer)) {
                valide = true;
            }	
        }
        return valide;
    }

    /**
     * Convertie une chaine contenant des coordoonées, en coordonnes exploitable
     * @param aConvertir 
     * @return convertie tableau de 2 éléments (ordonnée, abscisse) exploitable
     */
    public int[] convertion(String aConvertir) {
        int[] convertie = new int[2];

        /* On récupère la valeur de l'ordonnée */
        convertie[0] = Integer.parseInt(aConvertir.substring(1));
        convertie[1] = aConvertir.charAt(0);

        return convertie;
    }

    /**
     * @return the valeurEntree
     */
    public String getValeurEntree() {
        return valeurEntree;
    }

    /**
     * Conseille le joueur sur la pertinance des coups qu'il vient de jouer
     * @param coupJoueJoueur le coup que le joueur vient de jouer
     * @param coordCoupJoue les coordonnées du coup 
     * @return coupDejaJoue quand le coup que le joueur a jouer a déja ete� jou�
     */
    public String conseils(CoupJoue coupJoueJoueur, Coordonnee coordCoupJoue) {

        /* Liste des coups jou�s */
        List<Coordonnee> coupJoues = coupJoueJoueur.getCoordSaisie();

        /* String retourn�e quand le coup jou� � d�j� jou� */
        String coupDejaJoue;

        /* String retourn�e quand le coup est jou� trop loin d'un touch� */
        String coupTropLoin;

        /* String retourn�e quand le coup jou� est en diagonale du coup touch� */
        String coupDiagonale;

        int coordX,  // coordonn�es du coup jou�
        coordY; 

        int coordXTouche,       // coordonn�es du dernier coup touch�
        coordYTouche;

        coupDejaJoue = "Ne jouez pas le même coup !";

        coupTropLoin = "Essayez de jouer autour d'un coup touché !";

        coupDiagonale = "Il n'y a pas de situation d'abordage, et un bateau ne peut pas �tre plac� en diagonale";

        /** Test d'un coup d�j� jou� */
        if (coupJoueJoueur.estJoue(coordCoupJoue)) {
            return coupDejaJoue;
        }

        /* On recupère les coordonnées du coup */
        coordX = coordCoupJoue.getPosX();
        coordY = coordCoupJoue.getPosY();


        if (Mer.trouverBateau()) {


            /* On r�cup�re le dernier coup //TODO le dernier coup touch� */
            coordXTouche = coupJoues.get(coupJoues.size()-1).getPosX();
            coordYTouche = coupJoues.get(coupJoues.size()-1).getPosY();

            /** Test d'un coup �loign� d'un touch� */
            if ((Math.abs(coordXTouche - coordX)) > 3 || (Math.abs(coordYTouche - coordY)) > 3 ) {
                return coupTropLoin;
            }

            /** Test d'un coup en diagonale d'un coup d�j� jou� */
            if ((coordXTouche == coordX - 1 || coordXTouche == coordX + 1) 
                    && (coordYTouche == coordY - 1 || coordYTouche == coordY + 1)) {
                return coupDiagonale;
            }

        }


        return "";
    }
}