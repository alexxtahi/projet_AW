package unites;

import main.Joueur;
import ressources.Chemins;

public class DCA extends Unite {

    public DCA(Joueur joueur, String moyenDeDep, String arme, int x, int y) {
        super(joueur, Chemins.FICHIER_ANTIAIR, moyenDeDep, arme, 6, 6000, x, y);
    }
}
