package main;

import java.util.List;

import cases.Case;

/** Implémente des fonctions utiles pour tester le jeu. */
public class TestJeu {

    /**
     * Affiche dans la console le contenu de la case sur laquelle le curseur est
     *
     * @param x           La position sur l'axe x du curseur
     * @param y           La position sur l'axe y du curseur
     * @param carteString la carte du jeu
     */
    public static void afficheElementDansCase(int x, int y, Case[][] carteString) {
        System.out.println("(x: " + x + ", y: " + y + ") -> " + carteString[y][x].getUnite());
    }

    /**
     * Affiche la carte sous forme de string dans la console
     *
     * @param carte la carte du jeu
     */
    public static void afficheCarteString(String[][] carte) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                System.out.print(carte[i][j] + " | ");
            }
        }
    }

    public static void afficheArgentDesJoueurs(List<Joueur> joueurs) {
        for (Joueur j : joueurs)
            System.out.println("Argent J" + j.getId() + " = " + j.getArgent());
        System.out.println("");
    }
}
