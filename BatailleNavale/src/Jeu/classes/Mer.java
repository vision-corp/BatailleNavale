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
    /** Longueur par d�faut d'une mer instanci�e sans param�tres */
    final private int LONGUEUR_DEFAULT = 12;

    /** Largeur par d�faut d'une mer instanci�e sans param�tres */
    final private int LARGEUR_DEFAULT = 12;

    /** Longueur de la mer en cases */
    private int longueur;

    /** Largeur de la mer en cases */
    private int largeur;

    /** Ensemble des bateaux contenus dans la mer class� par taille decroissante 
     * Exemple : Taille 4 puis Taille 3 puis Taille 2...*/
    List<Bateau> bateaux = new ArrayList<>();

    /**
     * Objet contenant taille de la mer et sa r�f�rence
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

    /** Ajout d'un bateau dans la mer */
    public void ajouterBateau(Bateau bateau) {
        this.bateaux.add(bateau);
    }

    /** Suppression d'un bateau de la mer */
    public void supprimerBatteau(Bateau bateau) {
        this.bateaux.remove(bateau);
    }
    
    /**
     * m�thode d'affichage de la mer � l'�cran 
     * @return aAfficher chaine de carcact�re mer � afficher � l'�cran 
     */
    public String toString(CoupJoue coup) {

    	StringBuilder aAfficher = new StringBuilder("");
    	
    	int indice,          // Indice de la colonne ou ligne � parcourir
    	    noColonne;  // Taille de la colonne ou ligne � parcourir
    	
    	int longueur = this.getLongueur(), // Longueur de la mer 
            largeur = this.getLargeur();   // Largeur de la mer
    	
    	char noLigne;        // Num�ro de la ligne � afficher
    	

    	try {
    		if (longueur > 26 || largeur > 26 || longueur < 0 || largeur < 0) {
    			throw new IllegalArgumentException();
    		}

    		// Initialisation affichage (Alignement num�rotation)
    		aAfficher.append("    "); 
    		// Affichage des num�rotation num�rique pour les colonnes 
    		for (indice = 1; indice <= longueur; indice++) {
    			if (indice >= 10) {
    				aAfficher.append(indice + "  ");
    			} else {
    				aAfficher.append(indice + "   ");
    			}
    		}
    		aAfficher.append("\n");

    		/* Affichage de la mer avec en chaque d�but de ligne 
    		 * la num�rotation alphab�tique pour distinguer les lignes 
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
    		// Taille d�passant le nombre de lettre de l'alphabet 
    		return aAfficher.toString();
    	}

    }   	

    /**
     * Permet de d�finir l'�tat de la case � afficher 
     * @param coup objets contenant les coups jou�s par le joueur
     * @param noLigne caract�re saisie par l'utilisateur
     * @param noColonne num�ro saisie par l'utilisateur
     * @return " " : Si coordonn�es pas encore jou�e
     *         aAfficher : "~" si c'est de l'eau
     *                   : "*" si bateau touch�
     *                   : "o" si bateau coul�  
     */
    public String etatCase(CoupJoue coup, char noLigne, int noColonne) {
    	
    	Bateau tempBat; // Bateau temporaire
    	
    	Coordonnee coord = new Coordonnee(noColonne, noLigne);
    	String aAfficher = " "; 
    	
    	/* V�rification si les coordonn�es ont �t� jou� */
    	if (coup.estJoue(coord)) {
    		tempBat = this.trouverBateau(coord);
    		/* R�action en fonction de si un bateau � �tait trouv� */
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
     * G�re les erreurs de placements et lors de la sortie de 
     * cette m�thode tous les bateaux sont plac� si il existe bien un possibilit�
     * de les placer 
     */
    public void placerBateaux() {
    	/*// TODO Coder m�thode 
    	boolean ok;  // D�termine si le placement unique � r�ussi 
    	List<Bateau> listeBateaux = getBateaux();
    	
    	// Envoyer Bateau � placer */
    	Bateau bat;
    	
    		bat = bateaux.get(1);
    		bat.setSens(1);
    		bat.setProu(new Coordonnee(1, 'A'));
    		
    		bat = bateaux.get(2);
    		bat.setSens(1);
    
    }
    
    /**
	 * Regarde si un bateau est plac� sur une coordonn�e donn�e
	 * @param coordonnee � comparer
	 * @return bateau plac� � cette coordonn�e. Renvoi null si aucun bateau trouv�.
	 */
	public Bateau trouverBateau(Coordonnee coordonnee) {
		for (Bateau b : bateaux) {
			if (b.getProu() == coordonnee) {
				return b;
			}
		}
		return null;
	}


}
