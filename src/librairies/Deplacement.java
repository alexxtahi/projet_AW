package librairies;

import cases.Case;
import main.Jeu;
import ressources.Affichage;
import ressources.Chemins;

public class Deplacement {
    // private Case destination;
    private String image;
    private String debut;
    private String fin;
    private int cout;
    private int x;
    private int y;

    public Deplacement(Case destination, String debut, String fin, int cout, int x, int y) {
        // this.destination = destination;
        this.image = Chemins.getCheminFleche(debut, fin);
        this.debut = debut;
        this.fin = fin;
        this.cout = cout;
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

    public int[] getPosition() {
        return new int[] { x, y };
    }

    public int getCout() {
        return cout;
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
