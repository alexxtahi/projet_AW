package librairies;

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
