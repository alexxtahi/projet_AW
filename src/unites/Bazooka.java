package unites;

import main.Joueur;
import ressources.Chemins;

public class Bazooka extends Unite {

    public Bazooka(Joueur joueur, String moyenDeDep, String arme, int x, int y) {
        super(joueur, Chemins.FICHIER_BAZOOKA, moyenDeDep, arme, 2, 3500, x, y);
    }
}
