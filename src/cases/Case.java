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

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getPosition() {
        return new int[] { x, y };
    }

    public void affiche() {
        terrain.affiche();
        if (unite != null) {
            unite.affiche();
        }
    }
}
