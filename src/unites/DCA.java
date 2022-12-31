package unites;

import main.Joueur;
import ressources.Chemins;

public class DCA extends Unite {

    public DCA(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_ANTIAIR, 6, 6000, x, y);
    }
}
