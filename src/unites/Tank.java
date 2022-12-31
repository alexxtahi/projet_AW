package unites;

import main.Joueur;
import ressources.Chemins;

public class Tank extends Unite {

    public Tank(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_TANK, 6, 7000, x, y);
    }
}
