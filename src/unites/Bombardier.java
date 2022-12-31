package unites;

import main.Joueur;
import ressources.Chemins;

public class Bombardier extends Unite {

    public Bombardier(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_BOMBARDIER, 7, 20000, x, y);
    }
}
