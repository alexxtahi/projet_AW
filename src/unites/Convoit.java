package unites;

import main.Joueur;
import ressources.Chemins;

public class Convoit extends Unite {

    public Convoit(Joueur joueur, String moyenDeDep, int x, int y) {
        super(joueur, Chemins.FICHIER_GENIE, moyenDeDep, 6, 5000, x, y);
    }
}
