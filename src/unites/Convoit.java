package unites;

import main.Joueur;
import ressources.Chemins;

public class Convoit extends Unite {

    public Convoit(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_GENIE, 6, 5000, x, y);
    }
}
