package librairies;

import cases.Case;
import ressources.Chemins;
import unites.Unite;

public class ChoisitTrajet extends Etat {

    public ChoisitTrajet(Unite uniteAdeplacer, int curseurX, int curseurY) {
        super(uniteAdeplacer, curseurX, curseurY);
        System.out.println("Déplacement de l'unité " + uniteAdeplacer.getClass().getName() + " - Pts de déplacement: "
                + uniteAdeplacer.getPointsDep());
    }

    private boolean canUniteMove(Case destination) {
        int coutDuDep = Deplacement.getCoutDuDep(super.uniteAdeplacer.getMoyenDeDep(),
                destination.getTerrain().getType());
        System.out.println("Deps restants: " + super.uniteAdeplacer.getPointsDep());
        return (super.uniteAdeplacer.getPointsDep() - coutDuDep >= 0);
    }

    @Override
    public Etat actionHaut(Case[][] carte) {
        super.actionHaut(carte);
        if (canUniteMove(super.destinationDuCurseur))
            ajouteDeplacement(super.destinationDuCurseur, Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT);
        return this;
    }

    @Override
    public Etat actionBas(Case[][] carte) {
        super.actionBas(carte);
        if (canUniteMove(super.destinationDuCurseur))
            ajouteDeplacement(super.destinationDuCurseur, Chemins.DIRECTION_HAUT, Chemins.DIRECTION_BAS);
        return this;
    }

    @Override
    public Etat actionGauche(Case[][] carte) {
        super.actionGauche(carte);
        if (canUniteMove(super.destinationDuCurseur))
            ajouteDeplacement(super.destinationDuCurseur, Chemins.DIRECTION_DROITE, Chemins.DIRECTION_GAUCHE);
        return this;
    }

    @Override
    public Etat actionDroite(Case[][] carte) {
        super.actionDroite(carte);
        if (canUniteMove(super.destinationDuCurseur))
            ajouteDeplacement(super.destinationDuCurseur, Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_DROITE);
        return this;
    }

    @Override
    public Etat actionEntree(Case[][] carte, int indexJoueurActif) {
        // if ()
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
