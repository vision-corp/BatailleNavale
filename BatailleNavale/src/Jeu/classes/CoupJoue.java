/*
 * Coup.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

import java.util.List;
import java.util.ArrayList;
import Jeu.classes.Coordonnee;

/**
 * Objet CoupJoué qui stocke tous les coups valide joué par l'utilisateur 
 * @author onsenfou
 */
public class CoupJoue {
	
	/** Liste des coordonnees saisies lors de la partie 
	 * du plus ancien au plus recent 
	 */
	List<Coordonnee> coordSaisie = new ArrayList<>();

	/**
	 * commenter l'etat initial atteint
	 */
	public CoupJoue() {
		
	}
	
	/**
	 * Ajoute un coup joué à la liste de l'objet
	 * @param coordonnees coordonnees du coup joué
	 */
	public void addCoup(Coordonnee coordonnees) {
		this.coordSaisie.add(coordonnees);
	}
	
	/**
	 * Récupérer les coordonnee joué de l'objet 
	 * @return coordSaisie Liste des couos joués du type 
	 */
	public List<Coordonnee> getCoordSaisie() {
		return coordSaisie;
	}
	
	/**
	 * Détermine si une coordonnee founie à était joué 
	 * @param coord coordonnee à comparer 
	 * @return joue true si la coordonnee à était joué
	 *              flase si elle n'a pas était joué
	 */
	public boolean estJoue(Coordonnee coord) {    	
    	boolean joue = false; // Valide si la coordonnée à était joué
    	
    	for (Coordonnee element : this.coordSaisie) {
    		if (element.getPosX() == coord.getPosX()
    				&& element.getPosY() == coord.getPosY()) {
    			joue = true;
    		}
    	}
		return joue;
	}
	
	
	 
}