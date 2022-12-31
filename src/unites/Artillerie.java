package unites;

import ressources.Chemins;

public class Artillerie extends Unite {

    public Artillerie(int idJoueur) {
        super(idJoueur, Chemins.FICHIER_ARTILLERIE, 2, 6000);
    }
}
