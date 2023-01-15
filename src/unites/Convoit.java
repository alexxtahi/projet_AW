package unites;

import main.Joueur;
import ressources.Chemins;

public class Convoit extends Unite {

    public Convoit(Joueur joueur, String moyenDeDep, String arme, int x, int y) {
        super(joueur, Chemins.FICHIER_GENIE, moyenDeDep, arme, 6, 5000, x, y);
    }
}
