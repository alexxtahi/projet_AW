package unites;

import main.Joueur;
import ressources.Affichage;
import ressources.Chemins;

public class Unite {

    private Joueur joueur;
    private String image;
    private double pointsVie;
    private int pointsDep;
    private int prix;
    private int[] position = { 0, 0 };
    private boolean disponible;

    public Unite(Joueur joueur, String image, int pointsDep, int prix, int x, int y) {

        this.joueur = joueur;
        this.image = image;
        pointsVie = 10;
        this.pointsDep = pointsDep;
        this.prix = prix;
        position[0] = x;
        position[1] = y;
        this.disponible = false;

    }

    public double getPointsVie() {
        return pointsVie;
    }

    public void diminuePV(float degats) {
        pointsVie -= degats;
    }

    public void restorePointsVie(float pv) {
        pointsVie += pv;
    }

    public int getDeplacement() {
        return pointsDep;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public int getPrix() {
        return prix;
    }

    public int[] getPosition() {
        return new int[] { position[0], position[1] };
    }

    public String getPositionString() {
        return position[0] + "_" + position[1];
    }

    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    public void affiche() {
        Affichage.dessineImageDansCase(position[0],
                position[1], Chemins.getCheminUnite(joueur.getId(), disponible, image));
    }
}
