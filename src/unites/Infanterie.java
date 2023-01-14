package unites;

import main.Joueur;
import ressources.Chemins;

public class Infanterie extends Unite {

    public Infanterie(Joueur joueur, String moyenDeDep, int x, int y) {
        super(joueur, Chemins.FICHIER_INFANTERIE, moyenDeDep, 3, 1500, x, y);
    }
}
