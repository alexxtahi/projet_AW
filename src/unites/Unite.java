package unites;

import ressources.Affichage;
import ressources.Chemins;

public class Unite {

    private int idJoueur;
    private String image;
    private double pointsVie;
    private int pointsDep;
    private int prix;
    private boolean disponible;

    public Unite(int idJoueur, String image, int pointsDep, int prix) {
        this.idJoueur = idJoueur;
        this.image = image;
        pointsVie = 10;
        this.pointsDep = pointsDep;
        this.prix = prix;
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

    public int getIdJoueur() {
        return idJoueur;
    }

    public int getPrix() {
        return prix;
    }

    public void affiche(int x, int y) {
        Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(idJoueur, disponible, image));
    }
}
