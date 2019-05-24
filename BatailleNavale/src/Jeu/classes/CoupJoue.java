/*
 * Coup.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

import java.util.List;
import java.util.ArrayList;
import Jeu.classes.Coordonnee;

/**
 * Objet CoupJou� qui stocke tous les coups valide jou� par l'utilisateur 
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
	 * Ajoute un coup jou� � la liste de l'objet
	 * @param coordonnees coordonnees du coup jou�
	 */
	public void addCoup(Coordonnee coordonnees) {
		this.coordSaisie.add(coordonnees);
	}
	
	/**
	 * R�cup�rer les coordonnee jou� de l'objet 
	 * @return coordSaisie Liste des couos jou�s du type 
	 */
	public List<Coordonnee> getCoordSaisie() {
		return coordSaisie;
	}
	
	/**
	 * D�termine si une coordonnee founie � �tait jou� 
	 * @param coord coordonnee � comparer 
	 * @return joue true si la coordonnee � �tait jou�
	 *              flase si elle n'a pas �tait jou�
	 */
	public boolean estJoue(Coordonnee coord) {    	
    	boolean joue = false; // Valide si la coordonn�e � �tait jou�
    	
    	for (Coordonnee element : this.coordSaisie) {
    		if (element.getPosX() == coord.getPosX()
    				&& element.getPosY() == coord.getPosY()) {
    			joue = true;
    		}
    	}
		return joue;
	}
	
	
	 
}