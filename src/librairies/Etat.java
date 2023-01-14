package librairies;

import java.util.List;

import cases.Case;

import java.util.LinkedList;

import ressources.Chemins;
import ressources.Config;
import unites.Unite;

public abstract class Etat {

    private Unite uniteAdeplacer;
    private int curseurX;
    private int curseurY;

    private List<Deplacement> listeDeplacements;

    public Etat(Unite uniteAdeplacer, int curseurX, int curseurY) {
        this.uniteAdeplacer = uniteAdeplacer;
        this.curseurX = curseurX;
        this.curseurY = curseurY;
        listeDeplacements = new LinkedList<Deplacement>();
    }

    public List<Deplacement> getDeplacements() {
        return new LinkedList<Deplacement>(listeDeplacements);
    }

    public int getNombreDep() {
        return listeDeplacements.size();
    }

    public void ajouteDeplacement(String debut, String fin) {
        if (dernierDeplacement() == null) {
            listeDeplacements.add(new Deplacement(Chemins.DIRECTION_DEBUT, fin, getCurseurX(), getCurseurY()));
        } else {
            dernierDeplacement().setFin(fin);
            listeDeplacements.add(new Deplacement(debut, Chemins.DIRECTION_FIN, getCurseurX(), getCurseurY()));
        }
        uniteAdeplacer.diminuePointsDep(1);
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

    public abstract Etat actionEntree(Case[][] carte, int indexJoueurActif);

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
