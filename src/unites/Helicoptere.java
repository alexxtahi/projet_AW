package unites;

import main.Joueur;
import ressources.Chemins;

public class Helicoptere extends Unite {

    public Helicoptere(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_HELICOPTERE, 6, 12000, x, y);
    }
}
