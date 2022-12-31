package unites;

import main.Joueur;
import ressources.Chemins;

public class Bazooka extends Unite {

    public Bazooka(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_BAZOOKA, 2, 3500, x, y);
    }
}
