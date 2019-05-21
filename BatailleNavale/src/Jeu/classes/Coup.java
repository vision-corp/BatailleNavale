/*
 * Coup.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */

package Jeu.classes;

import java.util.Scanner;

/**
 * Coup joué par l'utilisateur ainsi que toutes les méthodes
 * lui soyant lié
 * @author onsenfou
 */
public class Coup {
	
	/** Commande pour quitter la partie */
	final private String cmdQuitter = "quitter";

	/** Commande pour afficher l'aide */
	final private String cmdAide = "aide";

	/** Commande pour sauvegarder la partie */
	final private String cmdSauv = "sauvegarder";

	/** Analyseur lexical de l'entrée standard */
	Scanner entree = new Scanner(System.in);

	/** Valeur non convetie entrée par l'utilisateur */
	private String valeurEntree;
	
	/*
	 * Permet à l'utilisateur d'entrer les coordonnées d'une case en gérent les erreurs.
	 
	// TODO ajouter la méthodes estValide()
	public void saisirCoordonnee() {
		String saisie;
		boolean ok;
		ok = false;
		do {
			System.out.println("Sur quelle case voulez-vous tirer ?");
			saisie = entree.nextLine();
			if (saisie.equals(cmdAide)) {
				ok = true;
				
			} else if (saisie.equals(cmdQuitter)) {
				ok = true;
				
			} else if (saisie.equals(cmdSauv)) {
				ok = true;
				
			} else if (estValide(saisie, )){
				ok = true;
				
			} else {
				System.out.println("Veuillez entrer une coordonnée valide (ex : D9).\n");
			}
		}while (!ok);
	} */
	
	/**
	 * Détermine si le coup joué par l'utilisateur est valide 
	 * par rapport à la mer jouée
	 * @param aTester chaine correspondant au coup joué
	 * @param largeurMer largeur de la mer jouée
	 * @param longueur longueur de la mer jouée
	 * @return valide : true si valide
	 *                  false si faux
	 */
	public static boolean estValide(String aTester, int largeurMer, int longueurMer) {
		boolean valide;
		int[] coordConvertie;
		
		valide = false;
		if (!(aTester.length() > 3)) {
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
	 * @return convertie tableau de 2 éléments (ordonnée, abscisse) exploitable
	 */
	public static int[] convertion(String aConvertir) {
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
}