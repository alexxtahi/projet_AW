package librairies;

import java.util.LinkedList;
import java.util.List;

import cases.Case;
import main.Jeu;
import ressources.Chemins;
import ressources.Config;
import unites.Unite;

public abstract class Etat {

    protected Unite uniteAdeplacer;
    protected Case destinationDuCurseur;
    private boolean pasEnArriere;
    private int curseurX;
    private int curseurY;

    protected List<Deplacement> listeDeplacements;

    public Etat(Unite uniteAdeplacer, int curseurX, int curseurY) {
        this.uniteAdeplacer = uniteAdeplacer;
        this.destinationDuCurseur = null;
        this.pasEnArriere = false;
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

    /**
     * Ajoute un nouveau déplacement à la liste des déplacements que l'unité fait
     *
     * @param destination la destination de l'unité
     * @param debut       la position initiale de l'unité
     * @param fin         la position finale de l'unité
     */
    public void ajouteDeplacement(String debut, String fin) {
        if (pasEnArriere) {
            pasEnArriere = false;
            System.out.println("Pas en arriere");
            return;
        }
        System.out.println("Pas en avant");

        int coutDuDep = Deplacement.getCoutDuDep(uniteAdeplacer.getMoyenDeDep(),
                this.destinationDuCurseur.getTerrain().getType());

        if (dernierDeplacement() == null) {
            System.out.println("1er dep");
            listeDeplacements
                    .add(new Deplacement(this.destinationDuCurseur, Chemins.DIRECTION_DEBUT, Chemins.DIRECTION_FIN,
                            coutDuDep, getCurseurX(), getCurseurY()));
        } else {
            System.out.println("Nouveau dep");
            dernierDeplacement().setFin(fin);
            listeDeplacements
                    .add(new Deplacement(this.destinationDuCurseur, debut, Chemins.DIRECTION_FIN, coutDuDep,
                            getCurseurX(),
                            getCurseurY()));
        }
        uniteAdeplacer.diminuePointsDep(coutDuDep);
        System.out.println(uniteAdeplacer.getMoyenDeDep() + "/" + this.destinationDuCurseur.getTerrain().getType()
                + " -> cout du déplacement: " + coutDuDep);
    }

    // à revoir

    public boolean depEnArriere() {
        // On vérifie si le joueur a fait un pas en arrière
        if (Jeu.memesPositions(destinationDuCurseur.getPosition(), uniteAdeplacer.getPosition())) {
            pasEnArriere = true;
            listeDeplacements.clear();
            uniteAdeplacer.resetDep();
        } else {
            int depDepenses = 0;
            for (Deplacement dep : listeDeplacements) {
                if (!pasEnArriere && Jeu.memesPositions(destinationDuCurseur.getPosition(), dep.getPosition())) {
                    pasEnArriere = true;
                    dep.setFin(Chemins.DIRECTION_FIN);
                } else if (pasEnArriere) {
                    depDepenses += dep.getCout();
                    listeDeplacements.remove(dep);
                }
            }
            uniteAdeplacer.restorePointsDep(depDepenses);
        }
        return pasEnArriere;
    }

    /**
     * Récupère le dernier déplacement de l'unité
     * 
     * @return le dernier déplacement
     */
    public Deplacement dernierDeplacement() {
        if (listeDeplacements.size() != 0)
            return listeDeplacements.get(listeDeplacements.size() - 1);
        return null;
    }

    public boolean isOutOfLimit(int x, int y) {
        boolean outOfLimitVert = getCurseurX() + x < 0 || getCurseurX() + x > Config.longueurCarteXCases - 1;
        boolean outOfLimitHoriz = getCurseurY() + y < 0 || getCurseurY() + y > Config.longueurCarteYCases - 1;
        return (outOfLimitVert || outOfLimitHoriz);
    }

    /**
     * Déplace le curseur vers le haut sans sortir de la grille
     *
     * @return la même instance de la classe Etat
     */
    public Etat actionHaut(Case[][] carte) {
        if (!isOutOfLimit(0, 1)) {
            deplaceCurseur(0, 1);
            destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
        }
        return this;
    }

    /**
     * Déplace le curseur vers le bas sans sortir de la grille
     * 
     * @return la même instance de la classe Etat
     * 
     */
    public Etat actionBas(Case[][] carte) {
        if (!isOutOfLimit(0, -1)) {
            deplaceCurseur(0, -1);
            destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
        }
        return this;
    }

    /**
     * Déplace le curseur vers la gauche sans sortir de la grille
     * 
     * @return la même instance de la classe Etat
     * 
     */
    public Etat actionGauche(Case[][] carte) {
        if (!isOutOfLimit(-1, 0)) {
            deplaceCurseur(-1, 0);
            destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
        }
        return this;
    }

    /**
     * Déplace le curseur vers la droite sans sortir de la grille
     * 
     * @return la même instance de la classe Etat
     * 
     */

    public Etat actionDroite(Case[][] carte) {
        if (!isOutOfLimit(1, 0)) {
            deplaceCurseur(1, 0);
            destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
        }
        return this;
    }

    public abstract Etat actionEntree(Case[][] carte, int indexJoueurActif);

    /**
     * Déplace le curseur en fonction des coordonnées placées en paramètre
     * 
     * @param x la nouvelle position du curseur sur l'axe x
     * @param y la nouvelle position du curseur sur l'axe y
     * 
     */
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

    public int[] getPosition() {
        return new int[] { curseurX, curseurY };
    }

}
