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

	public CoupJoue() {
		
	}
	
	/**
	 * Ajoute un coup joué à la liste de l'objet
	 * @param coordonnees coordonnees du coup joué
	 */
	public void addCoup(Coordonnee coordonnees) {
		this.coordSaisie.add(coordonnees);
	}
	
	public List<Coordonnee> getCoordSaisie() {
		return coordSaisie;
	}
	
	
	 
}