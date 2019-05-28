/*
 * BatailleNavale.java
 * Groupe 9 - Projet tutoré - Projet Barrios
 * copyright Tom Margalejo, Jean-Charles Luans, Florian Hyver, Tanguy Fenouillot, Dorian Gayraud
 */
package Jeu;

import java.util.Scanner;

// Import de classe crée 
import Jeu.classes.Mer;
import Jeu.outils.GestionSauvegarde;
import Jeu.classes.Bateau;
import Jeu.classes.Coup;
import Jeu.classes.Coordonnee;
import Jeu.classes.CoupJoue;


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
	 * Renvoie les règles de la batailleNavale
	 * @return regle regle de la bataille navale
	 */
	public static String aide() {
		/* Règle du jeu de la bataille navale */
		final String regle = 
				"===================================\n"
				+ "REGLE DU JEU BATAILLE NAVALE\n"
				+ "===================================\n"
				+ "Le jeu de la bataille navale se joue sur une \" mer \" rectangulaire "
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
			     + "\nDans cette version le joueur rejouera peut importe le coup"
			     + " (touché, coulé, \"Coup à l'eau\")\n\n"
			     + "Appuyez sur entree continuer...";

		return regle;
	}
	
	/**
	 * 
	 */
	public static String information() {
		/* Informations sur la partie jouée */
		final String infos = 
				"Dimensions de la \"mer\" :\n"
				+ "- nombre de ligne = 12\n"
				+ "- nombre de colonne = 12\n\n"
				
				+ "Quatre types de batiments différents :\n"
				+ "1 batiment de type porte-avions de taille 4\n"
				+ "2 batiments de type croiseur de taille 3\n"
				+ "3 batiments de type sous-marin de taille 2\n"
				+ "4 batiments de type vedette de taille 1\n\n"
				+ "Appuyez sur entree pour commencer une partie...\n";
		
		return infos;
	}
	
	/**
	 * Initialise la partie en :
	 * - Créeant les bateaux 
	 * - Liant les bateaux à la mer 
	 * - Placer les bateaux sur la mer  
	 */
	public static void initialiserPartie(Mer merOrdinateur) {
		
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
       
        
        /* Ajout des bateaux crées à la mer de l'ordinateur */
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

        int numCoup;               // numéro du coup joué

        String coordSaisie;        // Coordonnées saisie par le joueur 

        boolean fin,               // Permet de savoir quand il y a plus de bateau sur la mer
        valide;            // Evite de refaire appel deux fois à la méthode estValide

        fin = true;  //Initalisation dès le début pour gain de performance
        
        /* Mer de l'ordinateur */
        Mer merOrdinateur = new Mer(12, 12);
        
        /* Affichage des règles du jeu */
        System.out.println(aide());
        entree.nextLine();

        /* Initialisation de la partie */
        initialiserPartie(merOrdinateur);

        /* objet qui stockera les coups joué pour sauvegarde */
        CoupJoue coupJoueJoueur = new CoupJoue();

        /* objet qui stocke de manière temporaire les coordonnees saisie */
        Coordonnee saisieJoueur = new Coordonnee();
        
        /* Afficher les informations sur la partie */
        System.out.println(information());
        entree.nextLine();

        /* Affichage de la mer :
         * Les cases inconnu sont symbolisé par ‘.’ ‘ ’ ‘~’
         * Les cases “touchées” par ‘x’ ‘!’
         * Les cases plouf sont symbolisée par ‘O’
         */ 
        System.out.println(merOrdinateur.toString(coupJoueJoueur));

        numCoup = 1;
        System.out.println("Début du jeu");

        /* Boucle tant que des bateaux sont encores sur la mer 
         * While utilisé pour ne rien faire si les bateaux
         * n'ont pas pu être placé  
         */
        while (fin) {
            do {
                System.out.print("Coup " + numCoup + " > ");
                coordSaisie = entree.next() + entree.nextLine();
                Coup coupJoueur = new Coup(coordSaisie);
                valide = (coupJoueur.estValide(coordSaisie, merOrdinateur.getLargeur(),
                        merOrdinateur.getLongueur()));

                // Vérifie si saisie correcte
                // TODO : déplacer estValide dans Coup 
                if (!valide) {
                    System.out.println("Coordonnées saisies incorrectes réessayer"
                            + " Ex : A3, C12, D1..."); 
                } else {
                    /* Conseils pour le joueur */
//                    Coup.conseils(,new Coordonnee(coupJoueur.convertion(coordSaisie)));
                    /* Attribution temporaire des coordonnees à l'objet */
                    saisieJoueur = new Coordonnee(coupJoueur.convertion(coordSaisie));
                    /* Sauvegarde du coup joué pour la fonction sauvegarde */
                    coupJoueJoueur.addCoup(new Coordonnee(coupJoueur.convertion(coordSaisie)));
                    numCoup++;
                }
            } while (!valide);

            /* Vérification si bateau aux coordoonées saisie */
            Bateau tempo = merOrdinateur.trouverBateau(saisieJoueur);
            if(tempo == null) {
                System.out.println("plouf !");
            } else {
            	/* Incrémente le nombre de case touché avant vérification coulé */
            	tempo.incNbCasesTouche();
                if(tempo.estCoule()) {
                    System.out.println("Touché coulé !");
                } else {
                    System.out.println("Touché !");
                }

            }
            GestionSauvegarde.Restauration("nom", merOrdinateur, coupJoueJoueur);

            System.out.println(merOrdinateur.toString(coupJoueJoueur));
            /* Si liste de bateau de l'objet mer vide
             * Tous les bateaux sont coulés donc partie fini 
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



 