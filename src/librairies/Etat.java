package librairies;

import java.util.List;

import java.util.LinkedList;
import ressources.Config;

public abstract class Etat {

    private int curseurX;
    private int curseurY;

    private List<Deplacement> listeDeplacements;

    public Etat(int curseurX, int curseurY) {
        this.curseurX = curseurX;
        this.curseurY = curseurY;
        listeDeplacements = new LinkedList<Deplacement>();
    }

    public List<Deplacement> getDeplacements() {
        // return List.copyOf(listeDeplacements);
        return new LinkedList<Deplacement>(listeDeplacements);
    }

    public void ajouteDeplacement(String debut, String fin) {
        listeDeplacements.add(new Deplacement(debut, fin, getCurseurX(), getCurseurY()));
    }

    // à revoir
    public void retireDeplacement(Deplacement deplacement) {
        listeDeplacements.remove(deplacement);
    }

    public Deplacement dernierDeplacement() {
        if (listeDeplacements.size() != 0)
            return listeDeplacements.get(listeDeplacements.size() - 1);
        return null;
    }

    public Etat actionHaut() {
        if (getCurseurY() < Config.longueurCarteYCases - 1)
            deplaceCurseur(0, 1);
        return this;
    }

    public Etat actionBas() {
        if (getCurseurY() > 0)
            deplaceCurseur(0, -1);
        return this;
    }

    public Etat actionGauche() {
        if (getCurseurX() > 0)
            deplaceCurseur(-1, 0);
        return this;
    }

    public Etat actionDroite() {
        if (getCurseurX() < Config.longueurCarteXCases - 1)
            deplaceCurseur(1, 0);
        return this;
    }

    public abstract Etat actionEntree();

    public void deplaceCurseur(int x, int y) {
        curseurX += x;
        curseurY += y;
    }

    public int getCurseurX() {
        return curseurX;
    }

    public int getCurseurY() {
        return curseurY;
    }

}
