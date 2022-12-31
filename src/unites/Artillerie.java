package unites;

import main.Joueur;
import ressources.Chemins;

public class Artillerie extends Unite {

    public Artillerie(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_ARTILLERIE, 2, 6000, x, y);
    }
}
