package main;

public class TestJeu {

    public static void afficheElementDansCase(int[] positionCurseur, String[][] carteString) {
        System.out.println("(x: " + positionCurseur[0] + ", y: " + positionCurseur[1] + ") -> "
                + carteString[positionCurseur[1]][positionCurseur[0]]);
    }
}
