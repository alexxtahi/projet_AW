package unites;

import ressources.Chemins;

public class Tank extends Unite {

    public Tank(int idJoueur) {
        super(idJoueur, Chemins.FICHIER_TANK, 6, 7000);
    }
}
