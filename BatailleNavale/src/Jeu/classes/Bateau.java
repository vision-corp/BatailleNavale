/*
 * Bateau.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.classes;

/**
 * Bateau qui sera plac� sur une mer
 * @author tom margalejo
 *
 */
public class Bateau {

    /** Nom identifiant le bateau pour le joueur */
    private String nom;

    /** Taille d'un bateau en cases */
    private int taille;

    /** Sens d'un bateau : 
     * 0 = Est 
     * 1 = Nord 
     * 2 = Ouest 
     * 3 = Sud
     */
    private boolean sens;

    /**
     * Position de la prou du bateau qui permettra ensuite
     * � partir de la taille et du sens de d�termin�
     * sa position sur la mer et la place occup�e
     */
    private Coordonnee prou;

    /**
     * Nombre de case du bateau touch� pour d�terminer quand il doit couler
     * - nb points max : longueur du bateau
     * - 0 si bateau coul�
     */
    private int nbCaseTouche;

     /**
     * Cr�ation d'un nouveau bateau en utilisant des valeurs d�finis lors de l'instanciation
     * @param nom appellation du bateai
     * @param taille nombre de cases occup�e par ce bateau
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
    }

    /**
     * @return the sens
     */
    public boolean isSens() {
        return sens;
    }

    /**
     * @param sens the sens to set
     */
    public void setSens(boolean sens) {
        this.sens = sens;
    }

    /**
     * @return the prou
     */
    public Coordonnee getProu() {
        return prou;
    }

    /**
     * @param prou the prou to set
     */
    public void setProu(Coordonnee prou) {
        this.prou = prou;
    }

    /**
     * @return the nbCaseTouche
     */
    public int getNbCaseTouche() {
        return nbCaseTouche;
    }
    
    /**
    * @param nbCaseTouche the nbCaseTouche to set
    */
   public void setNbCaseTouche(int nbCaseTouche) {
       this.nbCaseTouche = nbCaseTouche;
   }

   /**
    * @return the nom
    */
   public String getNom() {
       return nom;
   }

   /**
    * @return the taille
    */
   public int getTaille() {
       return taille;
   }
   
   /**
    * Place le bateau sur la mer en lui attribuant : 
    * - Une coordonnee pour la proue du bateau
    * - Un sens 
    * @param bateau bateau � placer sur la mer 
    * @return true si le bateau � bien �tait plac� 
    *         false si le bateau n'a pas �t� plac�
    */
   public boolean placer() {
	   // TODO : Coder m�thode placer 
	   return true;
   }
   
   /**
	 * D�termine si un bateau a �t� coul� ou pas
	 * @param bateau � tester
	 * @return true si coul�, false si non coul� compl�tement
	 */
	public boolean estCoule() {
		if (this.getTaille() == this.getNbCaseTouche()) {
			return true;
		}
		return false;
	}

   @Override
   public String toString() {
       return "Bateau [nom=" + nom + ", taille=" + taille + ", sens=" + sens + ", prou=" + prou + ", nbCaseTouche="
               + nbCaseTouche + "]";
   }

}
