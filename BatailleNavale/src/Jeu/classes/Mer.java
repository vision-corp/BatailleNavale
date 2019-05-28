/*
 * Mer.java                        8 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.classes;

import java.util.List;
import java.util.ArrayList;

/**
 * Ensemble de bateaux constituant tous les navires d'un joueur
 * @author tom margalejo
 *
 */
public class Mer {
    /** Longueur par défaut d'une mer instanciée sans paramètres */
    final private int LONGUEUR_DEFAULT = 12;

    /** Largeur par défaut d'une mer instanciée sans paramétres */
    final private int LARGEUR_DEFAULT = 12;

    /** Longueur de la mer en cases */
    private int longueur;

    /** Largeur de la mer en cases */
    private int largeur;

    /** Ensemble des bateaux contenus dans la mer classé par taille decroissante 
     * Exemple : Taille 4 puis Taille 3 puis Taille 2...*/
    public List<Bateau> bateaux = new ArrayList<>();

    /**
     * Objet contenant taille de la mer et sa référence
     */
    public Mer() {
        this.longueur = LONGUEUR_DEFAULT;
        this.largeur = LARGEUR_DEFAULT;
    }

    /**
     * @param longueur de la mer
     * @param largeur de la mer
     */
    public Mer(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    /**
     * @return la valeur de  longueur
     */
    public int[] getTaille() {
        int[] sumTaille = {this.longueur, this.largeur};
        return sumTaille;
    }

    /**
     * @return la valeur de  largeur
     */
    public int getLargeur() {
        return largeur;
    }
    
    /**
     * @return la valeur de longueur
     */
    public int getLongueur() {
    	return longueur;
    }

    /**
     * @return the bateaux
     */
    public List<Bateau> getBateaux() {
        return bateaux;
    }

    /** Ajout d'un bateau dans la mer 
     * @param bateau 
     */
    public void ajouterBateau(Bateau bateau) {
        this.bateaux.add(bateau);
    }

    /** Suppression d'un bateau de la mer 
     * @param bateau 
     */
    public void supprimerBatteau(Bateau bateau) {
        this.bateaux.remove(bateau);
    }
    
    /**
     * Suppression de l'arrayList contenant tous les bateaux
     */
    public void suppArrayBateau() {
        bateaux.clear();
    }
    
    /**
     * méthode d'affichage de la mer à l'écran 
     * @param coup 
     * @return aAfficher chaine de carcactère mer à afficher à l'écran 
     */
    public String toString(CoupJoue coup) {

    	StringBuilder aAfficher = new StringBuilder("");
    	
    	int indice,          // Indice de la colonne ou ligne à parcourir
    	    noColonne;  // Taille de la colonne ou ligne à parcourir
    	
    	int longueur = this.getLongueur(), // Longueur de la mer 
            largeur = this.getLargeur();   // Largeur de la mer
    	
    	char noLigne;        // Numéro de la ligne à afficher
    	

    	try {
    		if (longueur > 26 || largeur > 26 || longueur < 0 || largeur < 0) {
    			throw new IllegalArgumentException();
    		}

    		// Initialisation affichage (Alignement numérotation)
    		aAfficher.append("    "); 
    		// Affichage des numérotation numérique pour les colonnes 
    		for (indice = 1; indice <= longueur; indice++) {
    			if (indice >= 10) {
    				aAfficher.append(indice + "  ");
    			} else {
    				aAfficher.append(indice + "   ");
    			}
    		}
    		aAfficher.append("\n");

    		/* Affichage de la mer avec en chaque début de ligne 
    		 * la numérotation alphabétique pour distinguer les lignes 
    		 */
    		noLigne = 'A';
    		for (indice = 1; indice <= largeur; indice++, noLigne++) {
    			aAfficher.append("   ");
    			for (noColonne = 1; noColonne <= longueur;
    				 noColonne++) {
        			aAfficher.append("----");
    			}
    			aAfficher.append("\n");
    			aAfficher.append(noLigne);
    			aAfficher.append(" ");
    			for (noColonne = 1; noColonne <= longueur;
       				 noColonne++) { 
    				aAfficher.append(" | " + etatCase(coup, noLigne, noColonne));
    			}
    			aAfficher.append("\n");
    		}

    		return aAfficher.toString();
    	} catch (IllegalArgumentException erreur) {
    		// Taille dépassant le nombre de lettre de l'alphabet 
    		return aAfficher.toString();
    	}

    }   	

    /**
     * Permet de définir l'état de la case à afficher 
     * @param coup objets contenant les coups joués par le joueur
     * @param noLigne caractère saisie par l'utilisateur
     * @param noColonne numéro saisie par l'utilisateur
     * @return " " : Si coordonnées pas encore jouée
     *         aAfficher : "~" si c'est de l'eau
     *                   : "*" si bateau touché
     *                   : "o" si bateau coulé  
     */
    public String etatCase(CoupJoue coup, char noLigne, int noColonne) {
    	
    	Bateau tempBat; // Bateau temporaire
    	
    	Coordonnee coord = new Coordonnee(noColonne, noLigne);
    	String aAfficher = " "; 
    	
    	/* Vérification si les coordonnées ont été joué */
    	if (coup.estJoue(coord)) {
    		tempBat = this.trouverBateau(coord);
    		/* Réaction en fonction de si un bateau à était trouvé */
	    	if (tempBat == null) {
	    	    aAfficher = "~";
	    	} else {
	        	if(tempBat.estCoule()) {
				    aAfficher = "o";
			    } else {
				    aAfficher = "*";
			    }
	    	}
    	}
    	return aAfficher;
    }
    
    /**
     * Place les bateaux de la liste bateaux sur la mer cible
     * Gère les erreurs de placements et lors de la sortie de 
     * cette méthode tous les bateaux sont placé si il existe 
     * bien un possibilité de les placer 
     */
    public void placerBateaux() {
    	// TODO Coder méthode 
    	boolean ok;  // Détermine si le placement unique à réussi 
    	List<Bateau> listeBateaux = getBateaux();

    	int sens,
    	    nbEssaie;
    	Coordonnee prou;
    	
    	nbEssaie = 0;
    	ok = false;
    	for (Bateau bat : listeBateaux) {
    		
    		while (nbEssaie != 100000 && !ok) {
    			// Tirage au sort des différents valeurs 
    	    	sens = (int) (Math.random() * ( 3 - 0 ));
    	    	prou = new Coordonnee((int) (Math.random() * ( 12 - 1 )),
    	    			              (char) (Math.random() * ( 12 - 1 )+65));
    		    if (bat.placer(sens, prou, this)) {
    		    	ok = true;
    		    	bat.setProu(prou);
    		    	bat.setSens(sens);
    		    } else {
    		    	nbEssaie++;
    		    }
    		}
    	}
    
    }
    
    /**
     * Regarde si un bateau est placé sur une coordonnée donnée
     * Regarde la proue :
     *              - si sens = 1 : regarde vers le nord
     *              - si sens = 0 : regarde vers l'est
     *              - si sens = 3 : regarde vers le sud
     *              - si sens = 2 : regarde vers l'ouest
     * Chaque point d'un bateau est testé (nb points à tester = longeur du bateau)
     * @param coordonnee à comparer
     * @return bateau placé à cette coordonnée. Renvoi null si aucun bateau trouvé.
     */
    public Bateau trouverBateau(Coordonnee coordonnee) {
            // Indicateur de prédence d'un bateau à la coordonnée donnée
            boolean ok;
            
            // ok pas à true si bateau trouvé à la coordonnée à comparer
            ok = false;
            for (Bateau b : bateaux) {
                    ok = false;
                    
                    
                    if (b.getProu() == coordonnee) { 
                            ok = true;
                    } else {
                            
                            // Balayage de touts les points en fonction de la taille du bateau
                            for(int i = b.getTaille(); i > 1; i--) {
                                    
                                    // balaygae adapté en fonction de l'orientation du bateau
                                    switch (b.getSens()) {
                                    case 0:
                                            if (b.getPosX() - i == coordonnee.getPosX() 
                                                    && b.getPosY() == coordonnee.getPosY()) {
                                                    ok = true;
                                            }
                                            break;
                                    case 1 :
                                            if (b.getPosY() - i == coordonnee.getPosY()
                                                    && b.getPosX() == coordonnee.getPosX()) {
                                                    ok = true;
                                            }
                                            break;
                                    case 2 :
                                            if (b.getPosX() + i == coordonnee.getPosX()
                                                    && b.getPosY() == coordonnee.getPosY()) {
                                                    ok = true;
                                            }
                                            break;
                                    case 3 :
                                            if (b.getPosY() + i == coordonnee.getPosY()
                                                    && b.getPosX() == coordonnee.getPosX()) {
                                                    ok = true;
                                            }
                                    default:
                                            break;
                                    }
                            }
                    }
                    // bateau trouvé à la coordonnée placée en paramètre
                    if (ok) {
                            return b;
                    }
            }
            return null;
    }

}
