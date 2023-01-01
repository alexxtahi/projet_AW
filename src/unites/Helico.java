package unites;

import main.Joueur;
import ressources.Chemins;

public class Helico extends Unite {

    public Helico(Joueur joueur, int x, int y) {
        super(joueur, Chemins.FICHIER_HELICOPTERE, 6, 12000, x, y);
    }
}
