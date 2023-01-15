package librairies;

import cases.Case;
import main.Jeu;
import ressources.Affichage;
import ressources.Chemins;
import unites.Unite;

public class ChoisitTrajet extends Etat {
    private Case caseDeDepart;

    public ChoisitTrajet(Case caseDeDepart, Unite uniteAdeplacer, int curseurX, int curseurY) {
        super(uniteAdeplacer, curseurX, curseurY);
        this.caseDeDepart = caseDeDepart;
        System.out.println("Déplacement de l'unité " + uniteAdeplacer.getClass().getName() + " - Pts de déplacement: "
                + uniteAdeplacer.getPointsDep());
    }

    private boolean canUniteMove() {
        String moyenDeDep = super.uniteAdeplacer.getMoyenDeDep();
        String terrainDeDestination = super.destinationDuCurseur.getTerrain().getType();
        boolean uniteEnnemiPresente = false;
        if (super.destinationDuCurseur.getUnite() != null
                && super.destinationDuCurseur.getUnite().getJoueur().getId() != uniteAdeplacer.getJoueur().getId()) {
            uniteEnnemiPresente = true;
        }

        int coutDuDep = Deplacement.getCoutDuDep(moyenDeDep, terrainDeDestination);

        return (super.depEnArriere() || (super.uniteAdeplacer.estDispo() && coutDuDep != -1
                && super.uniteAdeplacer.getPointsDep() - coutDuDep >= 0 && !uniteEnnemiPresente));
    }

    @Override
    public Etat actionHaut(Case[][] carte) {
        if (!isOutOfLimit(0, 1)) {
            super.destinationDuCurseur = carte[getCurseurY() + 1][getCurseurX()];
            if (canUniteMove()) {
                super.actionHaut(carte);
                ajouteDeplacement(Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT);
            }
        }
        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());

        return this;
    }

    @Override
    public Etat actionBas(Case[][] carte) {
        if (!isOutOfLimit(0, -1)) {
            super.destinationDuCurseur = carte[getCurseurY() - 1][getCurseurX()];
            if (canUniteMove()) {
                super.actionBas(carte);
                ajouteDeplacement(Chemins.DIRECTION_HAUT, Chemins.DIRECTION_BAS);
            }
        }
        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());
        return this;
    }

    @Override
    public Etat actionGauche(Case[][] carte) {
        if (!isOutOfLimit(-1, 0)) {
            super.destinationDuCurseur = carte[getCurseurY()][getCurseurX() - 1];
            if (canUniteMove()) {
                super.actionGauche(carte);
                ajouteDeplacement(Chemins.DIRECTION_DROITE, Chemins.DIRECTION_GAUCHE);
            }
        }
        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());
        return this;
    }

    @Override
    public Etat actionDroite(Case[][] carte) {
        if (!isOutOfLimit(1, 0)) {
            super.destinationDuCurseur = carte[getCurseurY()][getCurseurX() + 1];
            if (canUniteMove()) {
                super.actionDroite(carte);
                ajouteDeplacement(Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_DROITE);
            }
        }
        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());
        return this;
    }

    @Override
    public Etat actionEntree(Case[][] carte, int indexJoueurActif) {
        Case caseDarrivee = carte[getCurseurY()][getCurseurX()];
        Deplacement dernierDep = dernierDeplacement();
        if (dernierDep != null && Jeu.memesPositions(getPosition(), dernierDep.getPosition())
                && caseDarrivee.getUnite() == null) {
            // Choix de l'action à effectuer par l'unité
            String[] actions = { "Attendre" };
            if (Affichage.popup("Choisissez l'action à effectuer :", actions, true, 0) == 0) {
                uniteAdeplacer.move(getCurseurX(), getCurseurY());
                caseDeDepart.setUnite(null);
                caseDarrivee.setUnite(uniteAdeplacer);
            } else {
                setPosition(uniteAdeplacer.getPosition());
            }
        }
        uniteAdeplacer.resetDep();
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

    @Override
    public Etat actionEchap() {
        uniteAdeplacer.resetDep();
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
