package librairies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import main.Jeu;
import ressources.Affichage;
import ressources.Chemins;

public class Deplacement {
    private String image;
    private String debut;
    private String fin;
    private int x;
    private int y;

    public Deplacement(String debut, String fin, int x, int y) {
        this.debut = debut;
        this.fin = fin;
        this.image = Chemins.getCheminFleche(debut, fin);
        this.x = x;
        this.y = y;
    }

    protected static int getCoutDuDep(String moyenDeDep, String typeDeTerrain) {
        if (moyenDeDep.equals("Aerien")) {
            return 1;
        } else if ((moyenDeDep.equals("Pieds") || moyenDeDep.equals("Chenilles"))
                && (typeDeTerrain.equals("Plaine") || !Jeu.dicoTypesTerrain.contains(typeDeTerrain))) {
            return 1;
        } else if (moyenDeDep.equals("Pieds") && typeDeTerrain.equals("Foret")) {
            return 1;
        } else if (moyenDeDep.equals("Pieds") && typeDeTerrain.equals("Montagne")) {
            return 2;
        } else if (moyenDeDep.equals("Chenilles") && typeDeTerrain.equals("Foret")) {
            return 2;
        }
        return -1;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String nouvelleFin) {
        fin = nouvelleFin;
        image = Chemins.getCheminFleche(debut, nouvelleFin);
    }

    public void affiche() {
        Affichage.dessineImageDansCase(x, y, image);
    }
}
