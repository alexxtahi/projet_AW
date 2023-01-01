package main;

public class TestJeu {

    public static void afficheElementDansCase(int[] positionCurseur, String[][] carteString) {
        System.out.println("(x: " + positionCurseur[0] + ", y: " + positionCurseur[1] + ") -> "
                + carteString[positionCurseur[1]][positionCurseur[0]]);
    }

    public static void afficheCarteString(String[][] carte) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                System.out.print(carte[i][j] + " | ");
            }
        }
    }
}
