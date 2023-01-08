package main;

/** Implémente des fonctions utiles pour tester le jeu. */
public class TestJeu {

    /**
     * Affiche dans la console le contenu de la case sur laquelle le curseur est
     *
     * @param x           La position sur l'axe x du curseur
     * @param y           La position sur l'axe y du curseur
     * @param carteString la carte du jeu
     */
    public static void afficheElementDansCase(int x, int y, String[][] carteString) {
        System.out.println("(x: " + x + ", y: " + y + ") -> " + carteString[y][x]);
    }

    /**
     * Affiche la carte sous forme de string dans la console
     *
     * @param carteString la carte du jeu
     */
    public static void afficheCarteString(String[][] carte) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                System.out.print(carte[i][j] + " | ");
            }
        }
    }
}
