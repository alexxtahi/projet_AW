package librairies;

import cases.Case;
import main.Jeu;
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
        int coutDuDep = Deplacement.getCoutDuDep(super.uniteAdeplacer.getMoyenDeDep(),
                super.destinationDuCurseur.getTerrain().getType());

        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());

        return (super.depEnArriere() || (super.uniteAdeplacer.estDispo() && coutDuDep != -1
                && super.uniteAdeplacer.getPointsDep() - coutDuDep >= 0));
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
        return this;
    }

    @Override
    public Etat actionEntree(Case[][] carte, int indexJoueurActif) {
        Case caseDarrivee = carte[getCurseurY()][getCurseurX()];
        if (Jeu.memesPositions(getPosition(), dernierDeplacement().getPosition()) && caseDarrivee.getUnite() == null) {
            uniteAdeplacer.move(getCurseurX(), getCurseurY());
            caseDeDepart.setUnite(null);
            caseDarrivee.setUnite(uniteAdeplacer);
        }
        uniteAdeplacer.resetDep();
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
