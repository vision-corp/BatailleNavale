/**
 * GestionSauvegarde.java						23 mai 2019
 * IUT info1 2018-2019 TD2, no copyright, no copyleft
 */
package Jeu.outils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Jeu.classes.Bateau;
import Jeu.classes.CoupJoue;
import Jeu.classes.Mer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Cette classe sera chargée de sauvegarder les objets néccessaire de la partie
 * de bataille navale et de les restaurer pour continuer une partie plus tard
 * 
 * @author florian.hyver
 */
public class GestionSauvegarde {
    /**
     * sauvegarde les objets de la partie dans un fichier .jsonù*ij,nbs 
     * ù
     * @param filename nom du fichier sauvegarder
     * @param merOrdinateur 
     * @param coupJoueJoueur 
     */
    public static void Sauvegarde(String filename, Mer merOrdinateur, CoupJoue coupJoueJoueur) {

        String[] nom = new String[] { filename };

        // Vérification des arguments
        if (nom.length != 1) {
            System.err.println("Erreur : vous devez spécifier le nom du fichier JSON.");
            System.err.println();
            System.err.println("Usage : java GenerateurJSON fichier.json");
            System.err.println("\toù 'fichier.json' est le nom du fichier dans lequel sauvegarder");
            System.exit(-1);
        }

        // Génération du JSON depuis un tableau d'objets
        // sauvegarde des objets Bateau et taille de la mer et Coup Jouée
        Object tabObjets[] = new Bateau[merOrdinateur.getBateaux().size() + coupJoueJoueur.getCoordSaisie().size() + 2];
        int indice = 0; // indice de parcours du tableau précédemment initialisé
        // Objets Bateaux
        for (int i = 0; i < merOrdinateur.getBateaux().size(); i++) {
            tabObjets[i] = merOrdinateur.getBateaux().get(i);
            indice++;
        }
        // coup Jouée
        for (int i = indice; i < coupJoueJoueur.getCoordSaisie().size(); i++) {
            tabObjets[i] = coupJoueJoueur.getCoordSaisie().get(i);
            indice++;
        }
        // taille de la mer
        tabObjets[indice] = merOrdinateur.getLargeur();
        tabObjets[indice + 1] = merOrdinateur.getLongueur();
        JSONObject objet = new JSONObject();

        // Ajout du tableau
        try {
            objet.put("contacts", new JSONArray(tabObjets));
        } catch (JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(-1);
        }

        // Création du fichier de sortie
        FileWriter fs = null;
        try {
            fs = new FileWriter("src/Save/" + nom[0] + ".json");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + nom[0] + ".json '.");
            System.err.println(e);
            System.exit(-1);
        }

        // Sauvegarde dans le fichier
        try {
            objet.write(fs, 3, 0);
            fs.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier.");
            System.err.println(e);
            System.exit(-1);
        }

        // Fermeture du fichier
        try {
            fs.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier.");
            System.err.println(e);
            System.exit(-1);
        }

        System.out.println("La partie '" + nom[0] + "' a été sauvegardée.");
    }

    /**
     * restaure les objet de la partie enregistrer pour pouvoir la continuer
     * @param filename
     * @param merOrdinateur 
     */
    public static void Restauration(String filename, Mer merOrdinateur ) {
        String[] nom = new String[] { filename };
        // Vérification des arguments
        if (nom.length != 1) {
            System.err.println("Erreur : vous devez spécifier le nom du fichier JSON.");
            System.err.println();
            System.err.println("Usage : java LecteurJSON fichier.json");
            System.err.println("\tù 'fichier.json' est le nom du fichier Ã  ouvrir");
            System.exit(-1);
        }

        // Ouverture du fichier
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("src/Save/" + nom[0] + ".json");
        } catch (FileNotFoundException e) {
            System.err.println("Fichier '" + nom[0] + ".json' introuvable");
            System.exit(-1);
        }

        // Récupération de la chaîne JSON depuis le fichier
        String json = new String();
        Scanner entree = new Scanner(fs);
        while (entree.hasNext())
            json += entree.nextLine();
        entree.close();
        json = json.replaceAll("[\t ]", "");

        // Fermeture du fichier
        try {
            fs.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier.");
            System.err.println(e);
            System.exit(-1);
        }

        // Création d'un objet JSON
        JSONObject objet = new JSONObject(json);
        System.out.println("Contenu JSON : ");
        System.out.println(json);

        // Affichage à  l'écran
        JSONArray tableau = objet.getJSONArray("contacts");
        System.out.println("Liste des Bateau :");
        for (int i = 0; i < merOrdinateur.getBateaux().size(); i++) {
            JSONObject element = tableau.getJSONObject(i);
            System.out.print("sens=" + element.getInt("sens"));
            System.out.print(", taille=" + element.getInt("taille"));
            System.out.print(", CaseTouchée(s)=" + element.getInt("nbCaseTouche"));
            System.out.println(", nom=" + element.getString("nom"));
        }

    }
}
