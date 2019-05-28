/*
 * BatailleNavale.java
 * Groupe 9 - Projet tutor� - Projet Barrios
 * copyright Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
 */
package Jeu;

import java.util.Scanner;

// Import de classe cr�e 
import Jeu.classes.Mer;
import Jeu.outils.GestionSauvegarde;
import Jeu.classes.Bateau;
import Jeu.classes.Coup;
import Jeu.classes.Coordonnee;
import Jeu.classes.CoupJoue;


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
	 * Renvoie les r�gles de la batailleNavale
	 * @return regle regle de la bataille navale
	 */
	public static String aide() {
		/* R�gle du jeu de la bataille navale */
		final String regle = 
				"===================================\n"
				+ "REGLE DU JEU BATAILLE NAVALE\n"
				+ "===================================\n"
				+ "Le jeu de la bataille navale se joue sur une \" mer \" rectangulaire "
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
			     + "\nDans cette version le joueur rejouera peut importe le coup"
			     + " (touch�, coul�, \"Coup � l'eau\")\n\n"
			     + "Appuyez sur entree continuer...";

		return regle;
	}
	
	/**
	 * 
	 */
	public static String information() {
		/* Informations sur la partie jou�e */
		final String infos = 
				"Dimensions de la \"mer\" :\n"
				+ "- nombre de ligne = 12\n"
				+ "- nombre de colonne = 12\n\n"
				
				+ "Quatre types de batiments diff�rents :\n"
				+ "1 batiment de type porte-avions de taille 4\n"
				+ "2 batiments de type croiseur de taille 3\n"
				+ "3 batiments de type sous-marin de taille 2\n"
				+ "4 batiments de type vedette de taille 1\n\n"
				+ "Appuyez sur entree pour commencer une partie...\n";
		
		return infos;
	}
	
	/**
	 * Initialise la partie en :
	 * - Cr�eant les bateaux 
	 * - Liant les bateaux � la mer 
	 * - Placer les bateaux sur la mer  
	 */
	public static void initialiserPartie(Mer merOrdinateur) {
		
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
       
        
        /* Ajout des bateaux cr�es � la mer de l'ordinateur */
        merOrdinateur.ajouterBateau(b1);
        merOrdinateur.ajouterBateau(b2);
        merOrdinateur.ajouterBateau(b3);
        merOrdinateur.ajouterBateau(b4);
        merOrdinateur.ajouterBateau(b5);
        merOrdinateur.ajouterBateau(b6);
        merOrdinateur.ajouterBateau(b7);
        merOrdinateur.ajouterBateau(b8);
        merOrdinateur.ajouterBateau(b9);
        merOrdinateur.ajouterBateau(b10);
        
        /* Placement des bateau sur la mer */
        merOrdinateur.placerBateaux();
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

        int numCoup;               // num�ro du coup jou�

        String coordSaisie;        // Coordonn�es saisie par le joueur 

        boolean fin,               // Permet de savoir quand il y a plus de bateau sur la mer
        valide;            // Evite de refaire appel deux fois � la m�thode estValide

        fin = true;  //Initalisation d�s le d�but pour gain de performance
        
        /* Mer de l'ordinateur */
        Mer merOrdinateur = new Mer(12, 12);
        
        /* Affichage des r�gles du jeu */
        System.out.println(aide());
        entree.nextLine();

        /* Initialisation de la partie */
        initialiserPartie(merOrdinateur);

        /* objet qui stockera les coups jou� pour sauvegarde */
        CoupJoue coupJoueJoueur = new CoupJoue();

        /* objet qui stocke de mani�re temporaire les coordonnees saisie */
        Coordonnee saisieJoueur = new Coordonnee();
        
        /* Afficher les informations sur la partie */
        System.out.println(information());
        entree.nextLine();

        /* Affichage de la mer :
         * Les cases inconnu sont symbolis� par �.� � � �~�
         * Les cases �touch�es� par �x� �!�
         * Les cases plouf sont symbolis�e par �O�
         */ 
        System.out.println(merOrdinateur.toString(coupJoueJoueur));

        numCoup = 1;
        System.out.println("D�but du jeu");

        /* Boucle tant que des bateaux sont encores sur la mer 
         * While utilis� pour ne rien faire si les bateaux
         * n'ont pas pu �tre plac�  
         */
        while (fin) {
            do {
                System.out.print("Coup " + numCoup + " > ");
                coordSaisie = entree.next() + entree.nextLine();
                Coup coupJoueur = new Coup(coordSaisie);
                valide = (coupJoueur.estValide(coordSaisie, merOrdinateur.getLargeur(),
                        merOrdinateur.getLongueur()));

                // V�rifie si saisie correcte
                // TODO : d�placer estValide dans Coup 
                if (!valide) {
                    System.out.println("Coordonn�es saisies incorrectes r�essayer"
                            + " Ex : A3, C12, D1..."); 
                } else {
                    /* Conseils pour le joueur */
//                    Coup.conseils(,new Coordonnee(coupJoueur.convertion(coordSaisie)));
                    /* Attribution temporaire des coordonnees � l'objet */
                    saisieJoueur = new Coordonnee(coupJoueur.convertion(coordSaisie));
                    /* Sauvegarde du coup jou� pour la fonction sauvegarde */
                    coupJoueJoueur.addCoup(new Coordonnee(coupJoueur.convertion(coordSaisie)));
                    numCoup++;
                }
            } while (!valide);

            /* V�rification si bateau aux coordoon�es saisie */
            Bateau tempo = merOrdinateur.trouverBateau(saisieJoueur);
            if(tempo == null) {
                System.out.println("plouf !");
            } else {
            	/* Incr�mente le nombre de case touch� avant v�rification coul� */
            	tempo.incNbCasesTouche();
                if(tempo.estCoule()) {
                    System.out.println("Touch� coul� !");
                } else {
                    System.out.println("Touch� !");
                }

            }
            GestionSauvegarde.Restauration("nom", merOrdinateur, coupJoueJoueur);

            System.out.println(merOrdinateur.toString(coupJoueJoueur));
            /* Si liste de bateau de l'objet mer vide
             * Tous les bateaux sont coul�s donc partie fini 
             */
            if(merOrdinateur.getBateaux().size() == 0) {
                fin = true;
            }
        };

        // Fermeture du scanner entree 
        entree.close();

        System.out.println("FELICITATION VOUS AVEZ DEFAIT BATAILLONAVALO");

    }
}



 