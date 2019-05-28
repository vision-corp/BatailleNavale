/*
 * Bateau.java                        7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.classes;

/**
 * Bateau qui sera placé sur une mer
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
	private int sens;

	/**
	 * Position de la prou du bateau qui permettra ensuite
	 * à partir de la taille et du sens de déterminé
	 * sa position sur la mer et la place occupée
	 */
	private Coordonnee prou;

	/**
	 * Nombre de case du bateau touché pour déterminer quand il doit couler
	 * - nb points max : longueur du bateau
	 * - 0 si bateau coulé
	 */
	private int nbCaseTouche;

	/** position x du bateau */
	private int posX;

	/** position y du bateau*/
	private char posY;

	/**
	 * Création d'un nouveau bateau en utilisant des valeurs définis lors de l'instanciation
	 * @param nom appellation du bateai
	 * @param taille nombre de cases occupée par ce bateau
	 */
	public Bateau(String nom, int taille) {
		this.nom = nom;
		this.taille = taille;
	}

	/**
	 * Création d'un nouveau bateau en utilisant des valeurs définis lors de l'instanciation
	 * @param nom
	 * @param sens
	 * @param taille
	 * @param nbCaseTouche
	 * @param prou
	 */
	public Bateau(String nom, int sens, int taille, int nbCaseTouche, Coordonnee prou) {
		this.nom = nom;
		setSens(sens);
		this.taille = taille;
		this.nbCaseTouche = nbCaseTouche;
		setProu(prou);
	}

	/**
	 * @return the sens
	 */
	public int getSens() {
		return sens;
	}

	/**
	 * @param sens the sens to set
	 */
	public void setSens(int sens) {
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
		this.setPosX(prou.getPosX());
		this.setPosY(prou.getPosY());
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
	public void incNbCasesTouche() {
		this.nbCaseTouche = this.nbCaseTouche++;
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
	 * @return true si le bateau à bien était placé 
	 *         false si le bateau n'a pas été placé
	 */
	public boolean placer(int sens, Coordonnee prou, Mer merOrdinateur) {

		final int LARGEUR_ANTI_ABORDAGE = 3;
		// TODO : Coder méthode placer
		int parcoursX,  // Parcours sur l'axe X de l'espace occupé par le bateau
		parcoursY; 

		int X = prou.getPosX();
		int Y = prou.getPosY()-65;

		Bateau bat = null;

		if (sens == 0) {
			parcoursY = this.taille+2;
			parcoursX = LARGEUR_ANTI_ABORDAGE;
		} else if (sens == 1) {
			parcoursY = LARGEUR_ANTI_ABORDAGE;
			parcoursX = this.taille+2;
		} else if (sens == 2) {
			parcoursY = -this.taille-2;
			parcoursX = -LARGEUR_ANTI_ABORDAGE;
		} else {
			parcoursY = -LARGEUR_ANTI_ABORDAGE;
			parcoursX = -this.taille-2;
		}

		if (sens == 0 || sens == 1) {
			for (int indice = 0; indice < parcoursY; indice++) {
				for (int parcours = 0; parcours < parcoursX; parcours++) {
					bat = merOrdinateur.trouverBateau(new Coordonnee(X+parcours, (char) (Y+indice)));
				}
			}
		} else {
			for (int indice = parcoursY; indice != 0; indice--) {
				for (int parcours = parcoursX; parcours != 0; parcours--) {
					bat = merOrdinateur.trouverBateau(new Coordonnee(X-parcours, (char) (Y-indice)));
				}
			}
		}

		return bat == null?true:false;
	}

	/**
	 * Détermine si un bateau a été coulé ou pas
	 * @return true si coulé, false si non coulé complètement
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

	/**
	 * @return la valeur de  posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX la nouvelle valeur de posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return la valeur de  posY
	 */
	public char getPosY() {
		return posY;
	}

	/**
	 * @param posY la nouvelle valeur de posY
	 */
	public void setPosY(char posY) {
		this.posY = posY;
	}

}
