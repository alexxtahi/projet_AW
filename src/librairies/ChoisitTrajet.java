package librairies;

import cases.Case;
import ressources.Chemins;
import unites.Unite;

public class ChoisitTrajet extends Etat {

    public ChoisitTrajet(Unite uniteAdeplacer, int curseurX, int curseurY) {
        super(uniteAdeplacer, curseurX, curseurY);
        System.out.println("Déplacement d'une unité");
    }

    private boolean canMove() {
        return (unite.getPointsDep() - getNombreDep()) > 0;
    }

    @Override
    public Etat actionHaut() {
        super.actionHaut();
        if (canMove())
            ajouteDeplacement(Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT);
        return this;
    }

    @Override
    public Etat actionBas() {
        super.actionBas();
        if (canMove())
            ajouteDeplacement(Chemins.DIRECTION_HAUT, Chemins.DIRECTION_BAS);
        return this;
    }

    @Override
    public Etat actionGauche() {
        super.actionGauche();
        if (canMove())
            ajouteDeplacement(Chemins.DIRECTION_DROITE, Chemins.DIRECTION_GAUCHE);
        return this;
    }

    @Override
    public Etat actionDroite() {
        super.actionDroite();
        if (canMove())
            ajouteDeplacement(Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_DROITE);
        return this;
    }

    @Override
    public Etat actionEntree(Case[][] carte, int indexJoueurActif) {
        // if ()
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
