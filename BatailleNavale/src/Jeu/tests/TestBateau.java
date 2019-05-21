/**
 * TestBateau.java						7 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.tests;

import Jeu.classes.Bateau;

/**
 * Tests de la classe Bateau.java
 * @author Tom margalejo
 *
 */
public class TestBateau {

    /**
     * Tests de la classe bateau
     * @param args 
     */
    public static void main(String[] args) {
        Bateau b1 = new Bateau();
        System.out.println(b1.toString());
        System.out.println(b1.toString());
        
        Bateau b2 = new Bateau("Charles de Gaule", 4);
        System.out.println(b2.toString());
    }

}
