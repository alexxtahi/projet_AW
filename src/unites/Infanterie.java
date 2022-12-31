package unites;

import ressources.Chemins;

public class Infanterie extends Unite {

    public Infanterie(int idJoueur) {
        super(idJoueur, Chemins.FICHIER_INFANTERIE, 3, 1500);
    }
}
