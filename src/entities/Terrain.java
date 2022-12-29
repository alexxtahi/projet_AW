package entities;

import ressources.Chemins;

public class Terrain {
    private String type;
    private String image;

    public Terrain(String t) {
        type = t;
        switch (t) {
            case "Foret":
                image = Chemins.FICHIER_FORET;
                break;
            case "Eau":
                image = Chemins.FICHIER_EAU;
                break;
            case "Montagne":
                image = Chemins.FICHIER_MONTAGNE;
                break;
            default:
                image = Chemins.FICHIER_PLAINE;
                break;
        }
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}