package unites;

import main.Joueur;
import ressources.Affichage;
import ressources.Chemins;

public class Unite {

    private Joueur joueur;
    private String image;
    private String moyenDeDep;
    private double pointsVie;
    private int pointsDepMax;
    private int pointsDep;
    private int prix;
    private int[] position = { 0, 0 };
    private boolean disponible;

    public Unite(Joueur joueur, String image, String moyenDeDep, int pointsDep, int prix, int x, int y) {
        this.joueur = joueur;
        this.image = image;
        this.moyenDeDep = moyenDeDep;
        this.pointsVie = 10;
        this.pointsDepMax = this.pointsDep = pointsDep;
        this.prix = prix;
        this.position = new int[] { x, y };
        this.disponible = false;
    }

    public static Unite genererUniteParType(String type, Joueur joueur, int x, int y) {
        switch (type) {
            case "Artillerie":
                return new Artillerie(joueur, "Pieds", x, y);
            case "Bazooka":
                return new Bazooka(joueur, "Pieds", x, y);
            case "Bombardier":
                return new Bombardier(joueur, "Aerien", x, y);
            case "Convoit":
                return new Convoit(joueur, "Chenilles", x, y);
            case "DCA":
                return new DCA(joueur, "Chenilles", x, y);
            case "Helico":
                return new Helico(joueur, "Aerien", x, y);
            case "Infanterie":
                return new Infanterie(joueur, "Pieds", x, y);
            case "Tank":
                return new Tank(joueur, "Chenilles", x, y);
            default:
                return null;
        }
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

    public int getPointsDep() {
        return pointsDep;
    }

    public void diminuePointsDep(int coutDuDep) {
        pointsDep -= coutDuDep;
    }

    public String getMoyenDeDep() {
        return moyenDeDep;
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

    public boolean estDispo() {
        return disponible;
    }

    public void changeDispo() {
        disponible = !disponible;
    }

    public void affiche() {
        Affichage.dessineImageDansCase(position[0],
                position[1], Chemins.getCheminUnite(joueur.getId(), disponible, image));
    }
}
