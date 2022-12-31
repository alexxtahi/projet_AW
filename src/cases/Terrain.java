package cases;

import ressources.Affichage;
import ressources.Chemins;

public class Terrain {
    private String type;
    private String image;

    public Terrain(String type) {
        this.type = type;
        switch (type) {
            case "Foret":
                image = Chemins.getCheminTerrain(Chemins.FICHIER_FORET);
                break;
            case "Eau":
                image = Chemins.getCheminTerrain(Chemins.FICHIER_EAU);
                break;
            case "Montagne":
                image = Chemins.getCheminTerrain(Chemins.FICHIER_MONTAGNE);
                break;
            default:
                image = Chemins.getCheminTerrain(Chemins.FICHIER_PLAINE);
                break;
        }
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void affiche(int x, int y) {
        Affichage.dessineImageDansCase(x, y, image);
    }
}