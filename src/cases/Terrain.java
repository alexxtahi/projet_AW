package cases;

import ressources.Affichage;
import ressources.Chemins;

public class Terrain {
    private String type;
    private String image;
    private int[] position = { 0, 0 };

    public Terrain(String type, int x, int y) {
        this.type = type;
        position[0] = x;
        position[1] = y;
        // Attribution d'image
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

    public int[] getPosition() {
        return new int[] { position[0], position[1] };
    }

    public String getPositionString() {
        return position[0] + "_" + position[1];
    }

    public void affiche() {
        Affichage.dessineImageDansCase(position[0], position[1], image);
    }
}