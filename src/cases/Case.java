package cases;

import unites.Unite;

public class Case {
    private Terrain terrain;
    private Unite unite;
    private int[] position = { 0, 0 };

    public Case(Terrain terrain, Unite unite, int x, int y) {
        this.terrain = terrain;
        this.unite = unite;
        position[0] = x;
        position[1] = y;
    }

    public Unite getUnite() {
        return unite;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void affiche() {
        terrain.affiche();
        if (unite != null)
            unite.affiche();
    }
}
