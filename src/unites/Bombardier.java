package unites;

import ressources.Chemins;

public class Bombardier extends Unite {

    public Bombardier(int idJoueur) {
        super(idJoueur, Chemins.FICHIER_BOMBARDIER, 7, 20000);
    }
}
