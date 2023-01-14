package unites;

import main.Joueur;
import ressources.Chemins;

public class Artillerie extends Unite {

    public Artillerie(Joueur joueur, String moyenDeDep, int x, int y) {
        super(joueur, Chemins.FICHIER_ARTILLERIE, moyenDeDep, 2, 6000, x, y);
    }
}
