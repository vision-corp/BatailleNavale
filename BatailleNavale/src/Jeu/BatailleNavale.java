/*
 * BatailleNavale.java
 * Groupe 9 - Projet tutoré - Projet Barrios
 * copyright Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
 */
package Jeu;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.*;

// Import de classe crée 
import Jeu.classes.Mer;
import Jeu.classes.Bateau;
 
 /**
  * Jeu de la Bataille Navale qui se déroule sur un mer rectangulaire de n lignes
  * numérotées alphabétiquement, de m colonnes numérotées numériquement. 
  * (Prédéfinis dans cette version)
  * Dans cette Bataille l'ordinateur place les navires selon des dispositions verticales
  * et horizontale. Le joueur doit couler tout les bateaux adversaires pour gagner.
  * Le nombre de cases occupé par un navire dépend de sa taille
  * Deux navires différents ne peuvent pas occuper deux cases consécutives horizontalement
  * ou verticalement ou diagonalement (Pas d'abordage)
  * @author Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
  * @version 0.1
  */
  public class BatailleNavale {
	  
	  /**
	   * méthode d'affichage de la mer adverse à l'écran 
	   * @return aAfficher mer à afficher à l'écran 
	   */
	  public static StringBuilder afficherMer(int[][] aConvertir) 
	  throws IllegalArgumentException {
		  
		  // Alignement de la numérotation dès la création 
		  StringBuilder aAfficher = new StringBuilder("");
		  
		  try {
			  if (aConvertir[0].length > 26 || aConvertir[0].length <= 0) {
				  throw new IllegalArgumentException();
			  }
			 
			  int indice;

			  aAfficher.append("    "); // Initialisation affichage 
			  // Affichage des numérotation numérique pour les colonnes 
			  for (indice = 1; indice < aConvertir.length+1; indice++) {
				  if (indice >= 10) {
					  aAfficher.append(indice + "  ");
				  } else {
					  aAfficher.append(indice + "   ");
				  }
			  }
			  aAfficher.append("\n");

			  /* Affichage de la mer avec à chaque début de ligne 
			   * la numérotation alphabétique pour distinguer les lignes 
			   */
			  indice = 0;
			  for (int[] ligne : aConvertir) {
				  aAfficher.append("   ");
				  for (int element : ligne) {
					  aAfficher.append("----");
				  }
				  aAfficher.append("\n");

				  /* Numérotation alphabétique en début de ligne */
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
	   * méthode estValide qui vérifie si la saisie des coordoonées est correcte
	   * et que les coordonnées existent 
	   */
	  public static boolean estValide(String aTester) {
		  
		    // Patterne des coordoonnées à saisir 
		    Pattern patterneCoord = Pattern.compile("^[A-Z]\\p{Blank}(([0-9][0-9])|([0-9]))$"); // Patterne cchcc type regex pour verifier validité

	        boolean resultat = false;           // vrai si la chaine est correcte
	        Matcher matcherVal = patterneCoord.matcher(aTester);
	        resultat = matcherVal.matches();  
	       
	        
	        return resultat;
	  }
	  
	  /**
	   * méthode qui convertie les coordonnées en données exploitable
	   */
	  public static int[] convertion(String aConvertir) {
		  
		  // tableau qui contiendra les valeurs exploitable
		  int[] coordChiffre = new int[2];
		  
		  char lettre; 
		  
		  // Récupère les 2 parties de la chaine 
		  StringTokenizer aTraduire = new StringTokenizer(aConvertir);
		  
		  lettre = aTraduire.nextToken().charAt(0);
		  
		  coordChiffre[0] = Character.getNumericValue(lettre);;
		  coordChiffre[1] = (Integer.parseInt(aTraduire.nextToken()));
		  return coordChiffre;
	  }
	  
	  /**
	   * méthode estOccupe qui détermine si les coordonnées saisie contiennent un bateau 
	   */
	  public static boolean estOccupe(int[] aTester) {
		  
		 // TODO : Remplacer le bouchon par la vrai mer quand la fonction getEtatMer sera codée 
		 //int[][] etatMerOrdinateur = Mer.getEtatMer();
		  int[][] etatMerOrdinateur = new int[12][12]; // STUB
		  
		 if (!(etatMerOrdinateur[aTester[0]][aTester[1]] == 1)) {
			  return false;
		 }
		  
		  miseAJourDonnee(aTester);
		  return true;
	  }   
	  
	  /**
	   * méthode qui met à jour toutes les données découlant de l'action du joueur
	   * si ce dernier à toucher ou couler un bateau 
	   */
	   public static void miseAJourDonnee(int[] coordonnees) {
		   // TODO : Coder fonction
	   }
	  
	  /**
	   * Disposition des Bateaux aléatoirement par le jeu puis déroulement d'une partie
	   * L'odinateur demande une position à attaquer, le joueur la saisie 
	   * (Vérification si correcte)
	   * Si touché ou couler : le joueur est informé et peut rejouer 
	   * (Case touchée desormais considéré comme  de l'eau)
	   * Si "coup dans l'eau" : Informe le joueur et rend la main au joueur (Dans cette version)
	   * @param args inutilisé 
	   */
	  public static void main (String[] args) {
		  
		  Scanner entree = new Scanner(System.in);
		  
		  int[][] etatMerOrdinateur; // etat de la mer de l'ordinateur
		  int numCoup;               // numéro du coup joué
		  
		  String coordSaisie;        // Coordonnées saisie par le joueur 
		  
		  /* Règle du jeu de la bataille navale */
		  final String regle = 
				  "Le jeu de la bataille navale se joue sur une \" mer \" rectangulaire "
				  + "de 12 lignes sur 12 colonnes. "
				  + "\nDans cette mer sera disposé de manière aléatoire une flotte de bateaux "
				  + "par le jeu. "
				  + "\nLe joueur saisira une position sur la mer (réprésenté alphabétiquement pour"
				  + "les lignes et numériquement pour les colonnes) \nde la manière suivante : "
				  + "\"A1\", \"B11\", ..."
				  + "\n"
				  + "\nSuivant les coordonées saisie, le joueur peut : "
				  + "\n- Toucher un bateau qui aura pour effet d'endommager le bateau"
				  + "\n- Couler un bateau lorsque toutes les cases d'un bateau sont touchées"
				  + "\n- \"Coup à l'eau\" lorsque aucun bateau n'est touché ou coulé"
				  + "\nDans cette version le joueur rejouera peut importe le coup (touché, coulé, \"Coup à l'eau\") ";
		  
		  /* Affichage des règles du jeu */
		  System.out.println("==================================="
		  		             + "\nREGLE DU JEU BATAILLE NAVALE"
		  		             + "\n===================================");
		  System.out.println(regle + "\n");
		  
		   /* Initialisation de la Mer et des bateaux */
		   /** Mer contenant les bateaux */
	        Mer mer = new Mer(12, 12);

	        /** Création des 4 bateaux constituant la flotte placée sur la mer */
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

	        /** Ajout des bateaux à la mer*/
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
//		  /* Ajout des bateaux crées à la mer de l'ordinateur */
//		  
//		  /* Disposition aléatoire des bateaux */
//		  Mer.genererFlotte(merOrdinateur);
		  //TODO : Coder méthode
		  
		  System.out.println("Appuyez sur entree continuer...");
		  entree.nextLine();
		  
		  /* Affichage des informations sur la mer et sur flotte présente */
		  System.out.println("Dimensions de la \"mer\" :\n"
		  		             + "- nombre de ligne = 12\n"
		  		             + "- nombre de colonne = 12\n");
		  
		  System.out.println("Quatre types de batiments différents :\n"
		  		             + "1 batiment de type porte-avions de taille 4\n"
		  		             + "2 batiments de type croiseur de taille 3\n"
		  		             + "3 batiments de type sous-marin de taille 2\n"
		  		             + "4 batiments de type vedette de taille 1\n"); 
		  
		  System.out.println("Appuyez sur entree pour commencer une partie...");
		  entree.nextLine();
		  
		  /* Affichage des informations de la partie */
		  //etatMerOrdinateur = Mer.getEtatMer(); // On récupère l'état de la mer
		  etatMerOrdinateur = new int[12][12]; // stub
		  
		  /* Affichage de la mer :
	       * Les cases inconnu sont symbolisé par ‘.’ ‘ ’ ‘~’
	       * Les cases “touchées” par ‘x’ ‘!’
	       * Les cases plouf sont symbolisée par ‘O’
	       */ 
		  System.out.println(afficherMer(etatMerOrdinateur));
		  
		  
		  /* Attends d'une action du joueur */
		  numCoup = 0;
		  System.out.println("Début du jeu");
		  do {
			  System.out.print("Coup " + numCoup + " >");
			  coordSaisie = entree.next() + entree.nextLine();
			  
			  // Vérifie si saisie correcte
			  if (!estValide(coordSaisie)) {
				  System.out.println("Coordonnées saisies incorrectes réessayer"
						             + " Ex : A 3, C 12, D 1..."); 
			  }
		  } while (!estValide(coordSaisie));
		  
		  /* Vérification si bateau aux coordoonées saisie */
		  estOccupe(convertion(coordSaisie));
		  
		  
		  /* Boucle tant que des bateaux sont encores sur la mer */
		  

		      /* Saisie coordoonnées par joueur */
		      /* Vérification si touché, coulé ou "Coup à l'eau" */
		  
	  }
  
  }