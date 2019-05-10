/*
 * BatailleNavale.java
 * Groupe 9 - Projet tutor� - Projet Barrios
 * copyright Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
 */
package Jeu;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.*;

// Import de classe cr�e 
import Jeu.classes.Mer;
import Jeu.classes.Bateau;
 
 /**
  * Jeu de la Bataille Navale qui se d�roule sur un mer rectangulaire de n lignes
  * num�rot�es alphab�tiquement, de m colonnes num�rot�es num�riquement. 
  * (Pr�d�finis dans cette version)
  * Dans cette Bataille l'ordinateur place les navires selon des dispositions verticales
  * et horizontale. Le joueur doit couler tout les bateaux adversaires pour gagner.
  * Le nombre de cases occup� par un navire d�pend de sa taille
  * Deux navires diff�rents ne peuvent pas occuper deux cases cons�cutives horizontalement
  * ou verticalement ou diagonalement (Pas d'abordage)
  * @author Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
  * @version 0.1
  */
  public class BatailleNavale {
	  
	  /**
	   * m�thode d'affichage de la mer adverse � l'�cran 
	   * @return aAfficher mer � afficher � l'�cran 
	   */
	  public static StringBuilder afficherMer(int[][] aConvertir) 
	  throws IllegalArgumentException {
		  
		  // Alignement de la num�rotation d�s la cr�ation 
		  StringBuilder aAfficher = new StringBuilder("");
		  
		  try {
			  if (aConvertir[0].length > 26 || aConvertir[0].length <= 0) {
				  throw new IllegalArgumentException();
			  }
			 
			  int indice;

			  aAfficher.append("    "); // Initialisation affichage 
			  // Affichage des num�rotation num�rique pour les colonnes 
			  for (indice = 1; indice < aConvertir.length+1; indice++) {
				  if (indice >= 10) {
					  aAfficher.append(indice + "  ");
				  } else {
					  aAfficher.append(indice + "   ");
				  }
			  }
			  aAfficher.append("\n");

			  /* Affichage de la mer avec � chaque d�but de ligne 
			   * la num�rotation alphab�tique pour distinguer les lignes 
			   */
			  indice = 0;
			  for (int[] ligne : aConvertir) {
				  aAfficher.append("   ");
				  for (int element : ligne) {
					  aAfficher.append("----");
				  }
				  aAfficher.append("\n");

				  /* Num�rotation alphab�tique en d�but de ligne */
				  aAfficher.append(Character.toChars(indice+65));
				  aAfficher.append("   ");
				  indice++;

				  for (int element : ligne) {
					  aAfficher.append(element + " | ");
				  }
				  aAfficher.append("\n");

			  }
			  return aAfficher;
		  } catch (IllegalArgumentException erreur) {
			  return aAfficher;
		  }
		  
		  
	  }
	  
	  /**
	   * m�thode estValide qui v�rifie si la saisie des coordoon�es est correcte
	   * et que les coordonn�es existent 
	   */
	  public static boolean estValide(String aTester) {
		  
		    // Patterne des coordoonn�es � saisir 
		    Pattern patterneCoord = Pattern.compile("^[A-Z]\\p{Blank}(([0-9][0-9])|([0-9]))$"); // Patterne cchcc type regex pour verifier validit�

	        boolean resultat = false;           // vrai si la chaine est correcte
	        Matcher matcherVal = patterneCoord.matcher(aTester);
	        resultat = matcherVal.matches();  
	       
	        
	        return resultat;
	  }
	  
	  /**
	   * m�thode qui convertie les coordonn�es en donn�es exploitable
	   */
	  public static int[] convertion(String aConvertir) {
		  
		  // tableau qui contiendra les valeurs exploitable
		  int[] coordChiffre = new int[2];
		  
		  char lettre; 
		  
		  // R�cup�re les 2 parties de la chaine 
		  StringTokenizer aTraduire = new StringTokenizer(aConvertir);
		  
		  lettre = aTraduire.nextToken().charAt(0);
		  
		  coordChiffre[0] = Character.getNumericValue(lettre);;
		  coordChiffre[1] = (Integer.parseInt(aTraduire.nextToken()));
		  return coordChiffre;
	  }
	  
	  /**
	   * m�thode estOccupe qui d�termine si les coordonn�es saisie contiennent un bateau 
	   */
	  public static boolean estOccupe(int[] aTester) {
		  
		 // TODO : Remplacer le bouchon par la vrai mer quand la fonction getEtatMer sera cod�e 
		 //int[][] etatMerOrdinateur = Mer.getEtatMer();
		  int[][] etatMerOrdinateur = new int[12][12]; // STUB
		  
		 if (!(etatMerOrdinateur[aTester[0]][aTester[1]] == 1)) {
			  return false;
		 }
		  
		  miseAJourDonnee(aTester);
		  return true;
	  }   
	  
	  /**
	   * m�thode qui met � jour toutes les donn�es d�coulant de l'action du joueur
	   * si ce dernier � toucher ou couler un bateau 
	   */
	   public static void miseAJourDonnee(int[] coordonnees) {
		   // TODO : Coder fonction
	   }
	  
	  /**
	   * Disposition des Bateaux al�atoirement par le jeu puis d�roulement d'une partie
	   * L'odinateur demande une position � attaquer, le joueur la saisie 
	   * (V�rification si correcte)
	   * Si touch� ou couler : le joueur est inform� et peut rejouer 
	   * (Case touch�e desormais consid�r� comme  de l'eau)
	   * Si "coup dans l'eau" : Informe le joueur et rend la main au joueur (Dans cette version)
	   * @param args inutilis� 
	   */
	  public static void main (String[] args) {
		  
		  Scanner entree = new Scanner(System.in);
		  
		  int[][] etatMerOrdinateur; // etat de la mer de l'ordinateur
		  int numCoup;               // num�ro du coup jou�
		  
		  String coordSaisie;        // Coordonn�es saisie par le joueur 
		  
		  /* R�gle du jeu de la bataille navale */
		  final String regle = 
				  "Le jeu de la bataille navale se joue sur une \" mer \" rectangulaire "
				  + "de 12 lignes sur 12 colonnes. "
				  + "\nDans cette mer sera dispos� de mani�re al�atoire une flotte de bateaux "
				  + "par le jeu. "
				  + "\nLe joueur saisira une position sur la mer (r�pr�sent� alphab�tiquement pour"
				  + "les lignes et num�riquement pour les colonnes) \nde la mani�re suivante : "
				  + "\"A1\", \"B11\", ..."
				  + "\n"
				  + "\nSuivant les coordon�es saisie, le joueur peut : "
				  + "\n- Toucher un bateau qui aura pour effet d'endommager le bateau"
				  + "\n- Couler un bateau lorsque toutes les cases d'un bateau sont touch�es"
				  + "\n- \"Coup � l'eau\" lorsque aucun bateau n'est touch� ou coul�"
				  + "\nDans cette version le joueur rejouera peut importe le coup (touch�, coul�, \"Coup � l'eau\") ";
		  
		  /* Affichage des r�gles du jeu */
		  System.out.println("==================================="
		  		             + "\nREGLE DU JEU BATAILLE NAVALE"
		  		             + "\n===================================");
		  System.out.println(regle + "\n");
		  
		   /* Initialisation de la Mer et des bateaux */
		   /** Mer contenant les bateaux */
	        Mer mer = new Mer(12, 12);

	        /** Cr�ation des 4 bateaux constituant la flotte plac�e sur la mer */
	        Bateau b1 = new Bateau("Porte Avions", 4);
	        Bateau b2 = new Bateau("Croiseur", 3);
	        Bateau b3 = new Bateau("Croiseur", 3);
	        Bateau b4 = new Bateau("Sous-Marin", 2);
	        Bateau b5 = new Bateau("Sous-Marin", 2);
	        Bateau b6 = new Bateau("Sous-Marin", 2);
	        Bateau b7 = new Bateau("Vedette", 1);
	        Bateau b8 = new Bateau("Vedette", 1);
	        Bateau b9 = new Bateau("Vedette", 1);
	        Bateau b10 = new Bateau("Vedette", 1);

	        /** Ajout des bateaux � la mer*/
	        mer.ajouterBateau(b1);
	        mer.ajouterBateau(b2);
	        mer.ajouterBateau(b3);
	        mer.ajouterBateau(b4);
	        mer.ajouterBateau(b5);
	        mer.ajouterBateau(b6);
	        mer.ajouterBateau(b7);
	        mer.ajouterBateau(b8);
	        mer.ajouterBateau(b9);
	        mer.ajouterBateau(b10);
//		  
//		  /* Ajout des bateaux cr�es � la mer de l'ordinateur */
//		  
//		  /* Disposition al�atoire des bateaux */
//		  Mer.genererFlotte(merOrdinateur);
		  //TODO : Coder m�thode
		  
		  System.out.println("Appuyez sur entree continuer...");
		  entree.nextLine();
		  
		  /* Affichage des informations sur la mer et sur flotte pr�sente */
		  System.out.println("Dimensions de la \"mer\" :\n"
		  		             + "- nombre de ligne = 12\n"
		  		             + "- nombre de colonne = 12\n");
		  
		  System.out.println("Quatre types de batiments diff�rents :\n"
		  		             + "1 batiment de type porte-avions de taille 4\n"
		  		             + "2 batiments de type croiseur de taille 3\n"
		  		             + "3 batiments de type sous-marin de taille 2\n"
		  		             + "4 batiments de type vedette de taille 1\n"); 
		  
		  System.out.println("Appuyez sur entree pour commencer une partie...");
		  entree.nextLine();
		  
		  /* Affichage des informations de la partie */
		  //etatMerOrdinateur = Mer.getEtatMer(); // On r�cup�re l'�tat de la mer
		  etatMerOrdinateur = new int[12][12]; // stub
		  
		  /* Affichage de la mer :
	       * Les cases inconnu sont symbolis� par �.� � � �~�
	       * Les cases �touch�es� par �x� �!�
	       * Les cases plouf sont symbolis�e par �O�
	       */ 
		  System.out.println(afficherMer(etatMerOrdinateur));
		  
		  
		  /* Attends d'une action du joueur */
		  numCoup = 0;
		  System.out.println("D�but du jeu");
		  do {
			  System.out.print("Coup " + numCoup + " >");
			  coordSaisie = entree.next() + entree.nextLine();
			  
			  // V�rifie si saisie correcte
			  if (!estValide(coordSaisie)) {
				  System.out.println("Coordonn�es saisies incorrectes r�essayer"
						             + " Ex : A 3, C 12, D 1..."); 
			  }
		  } while (!estValide(coordSaisie));
		  
		  /* V�rification si bateau aux coordoon�es saisie */
		  estOccupe(convertion(coordSaisie));
		  
		  
		  /* Boucle tant que des bateaux sont encores sur la mer */
		  

		      /* Saisie coordoonn�es par joueur */
		      /* V�rification si touch�, coul� ou "Coup � l'eau" */
		  
	  }
  
  }