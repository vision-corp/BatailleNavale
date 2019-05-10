/*
 * Bateau.java						7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.classes;

/**
 * Bateau qui sera placé sur une mer
 * @author tom margalejo
 *
 */
public class Bateau {
    
    /** Nom par défaut d'un bateau */
    final private String NOM_DEFAUT = "Pirogue";
    
    /** Taille par défaut d'un bateau */
    final private int TAILLE_DEFAUT = 1;
    
    /** Nom identifiant le bateau pour le joueur */
    private String nom;
    
    /** Taille d'un bateau en cases */
    private int taille;
    
    /** Sens d'un bateau : false -> vertical, true->horizontal */
    private boolean sens;
    
    /** Etat de vie du bateau : 
     *   - nb points max : longueur du bateau
     *   - 0 si bateau coulé
     */
    private int etat;
    
    /**
     * Création d'un nouveau bateau en utilisant des valeurs par défaut
     */
    public Bateau() {
        this.nom = NOM_DEFAUT;
        this.taille = TAILLE_DEFAUT;
        this.sens = false;
        this.etat = TAILLE_DEFAUT;
        this.positionX = 0;
        this.positionY = 0;
    }
    
    /**
     * Création d'un nouveau bateau en utilisant des valeurs définis lors de l'instanciation
     * @param nom appellation du bateai
     * @param taille nombre de cases occupée par ce bateau
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        this.sens = false;
        this.etat = taille;
        this.positionX = 0;
        this.positionY = 0;
    }
    
    /**
     * @return la valeur de  sens
     */
    public boolean isSens() {
        return sens;
    }
    
    /** Position X du point de départ du positionnement du bateau*/
    private int positionX;
    
    /** Position Y du point de départ du positionnement du bateau */
    private int positionY;

    /**
     * @param sens la nouvelle valeur de sens
     */
    public void setSens(boolean sens) {
        this.sens = sens;
    }

    /**
     * @return la valeur de  etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     * @param etat la nouvelle valeur de etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * @return la valeur de  nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return la valeur de  taille
     */
    public int getTaille() {
        return taille;
    }

    /**
     * @return la valeur de  positionX
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * @return la valeur de  positionY
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * @param positionX la nouvelle valeur de positionX
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * @param positionY la nouvelle valeur de positionY
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "Bateau [nom=" + nom + ", taille=" + taille + ", sens=" + sens + ", etat=" + etat + ", positionX="
                + positionX + ", positionY=" + positionY + "]";
    }
}
