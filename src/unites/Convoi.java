package unites;

import main.Joueur;
import ressources.Chemins;

public class Convoi extends Unite {

    public Convoi(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_GENIE, 6, 5000, x, y);
    }
}
