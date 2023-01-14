package unites;

import main.Joueur;
import ressources.Chemins;

public class Helico extends Unite {

    public Helico(Joueur joueur, String moyenDeDep, int x, int y) {
        super(joueur, Chemins.FICHIER_HELICOPTERE, moyenDeDep, 6, 12000, x, y);
    }
}
