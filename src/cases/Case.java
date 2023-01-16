package cases;

import unites.Unite;

public class Case {
    private Terrain terrain;
    private Unite unite;
    private int x;
    private int y;

    public Case(Terrain terrain, Unite unite, int x, int y) {
        this.terrain = terrain;
        this.unite = unite;
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return Une instance de Unité, null s'il n'y a pas d'unité présente sur la
     *         case
     */
    public Unite getUnite() {
        return unite;
    }

    /**
     * 
     * @param unite la nouvelle unité à affecter à la case
     */
    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    /**
     * 
     * @return L'instance de terrain présente sur la case
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Renvoi la position de la case sur l'axe x
     * 
     * @return Un entier représentant la position sur x de la case
     */
    public int getX() {
        return x;
    }

    /**
     * Renvoi la position de la case sur l'axe y
     * 
     * @return Un entier représentant la position sur y de la case
     */
    public int getY() {
        return y;
    }

    /**
     * Renvoi la position de la case sur les deux axes (x,y)
     * 
     * @return Un tableau d'entiers correspondant à la position sur les deux axes
     *         (x,y)
     */
    public int[] getPosition() {
        return new int[] { x, y };
    }

    /**
     * Affiche les éléments contenus dans la case à l'écran
     */
    public void affiche() {
        terrain.affiche();
        if (unite != null) {
            unite.affiche();
        }
    }
}
