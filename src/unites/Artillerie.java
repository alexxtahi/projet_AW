package unites;

import main.Joueur;
import ressources.Chemins;

public class Artillerie extends Unite {

    public Artillerie(Joueur joueur, String moyenDeDep, String arme, int x, int y) {
        super(joueur, Chemins.FICHIER_ARTILLERIE, moyenDeDep, arme, 2, 6000, x, y);
    }
}
