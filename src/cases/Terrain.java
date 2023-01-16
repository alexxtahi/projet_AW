package cases;

import ressources.Affichage;
import ressources.Chemins;

public class Terrain {
    private String type;
    private String image;
    private int x;
    private int y;

    public Terrain(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
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

    /**
     * Renvoi le type du terrain
     * 
     * @return Le type du terrain
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoi le chemin de l'image du terrain
     * 
     * @return Le chemin de l'image du terrain
     */
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Renvoi la position du terrain sur les deux axes (x,y)
     * 
     * @return Un tableau d'entiers correspondant à la position sur les deux axes
     *         (x,y)
     */
    public int[] getPosition() {
        return new int[] { x, y };
    }

    /**
     * Affiche l'image du terrain à l'écran
     */
    public void affiche() {
        Affichage.dessineImageDansCase(x, y, image);
    }
}