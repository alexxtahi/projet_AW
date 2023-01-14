package unites;

import main.Joueur;
import ressources.Chemins;

public class Bazooka extends Unite {

    public Bazooka(Joueur joueur, String moyenDeDep, int x, int y) {
        super(joueur, Chemins.FICHIER_BAZOOKA, moyenDeDep, 2, 3500, x, y);
    }
}
