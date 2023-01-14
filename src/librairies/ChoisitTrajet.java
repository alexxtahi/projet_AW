package librairies;

import cases.Case;
import ressources.Chemins;
import unites.Unite;

public class ChoisitTrajet extends Etat {

    public ChoisitTrajet(Unite uniteAdeplacer, int curseurX, int curseurY) {
        super(uniteAdeplacer, curseurX, curseurY);
        System.out.println("Déplacement d'une unité");
    }

    private boolean canUniteMove(Case destination) {
        int coutDuDep = Deplacement.getCoutDuDep(super.uniteAdeplacer.getMoyenDeDep(),
                destination.getTerrain().getType());
        return (super.uniteAdeplacer.getPointsDep() - coutDuDep > 0);
    }

    @Override
    public Etat actionHaut(Case destination) {
        super.actionHaut(destination);
        if (canUniteMove(destination))
            ajouteDeplacement(destination, Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT);
        return this;
    }

    @Override
    public Etat actionBas(Case destination) {
        super.actionBas(destination);
        if (canUniteMove(destination))
            ajouteDeplacement(destination, Chemins.DIRECTION_HAUT, Chemins.DIRECTION_BAS);
        return this;
    }

    @Override
    public Etat actionGauche(Case destination) {
        super.actionGauche(destination);
        if (canUniteMove(destination))
            ajouteDeplacement(destination, Chemins.DIRECTION_DROITE, Chemins.DIRECTION_GAUCHE);
        return this;
    }

    @Override
    public Etat actionDroite(Case destination) {
        super.actionDroite(destination);
        if (canUniteMove(destination))
            ajouteDeplacement(destination, Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_DROITE);
        return this;
    }

    @Override
    public Etat actionEntree(Case caseActuelle, int indexJoueurActif) {
        // if ()
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
