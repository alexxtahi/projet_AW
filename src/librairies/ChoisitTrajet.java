package librairies;

import ressources.Chemins;

public class ChoisitTrajet extends Etat {

    public ChoisitTrajet(int curseurX, int curseurY) {
        super(curseurX, curseurY);
        System.out.println("Déplacement d'une unité");
    }

    @Override
    public Etat actionHaut() {
        super.actionHaut();
        ajouteDeplacement(Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT);
        return this;
    }

    @Override
    public Etat actionBas() {
        super.actionBas();
        ajouteDeplacement(Chemins.DIRECTION_HAUT, Chemins.DIRECTION_BAS);
        return this;
    }

    @Override
    public Etat actionGauche() {
        super.actionGauche();
        ajouteDeplacement(Chemins.DIRECTION_DROITE, Chemins.DIRECTION_GAUCHE);
        return this;
    }

    @Override
    public Etat actionDroite() {
        super.actionDroite();
        ajouteDeplacement(Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_DROITE);
        return this;
    }

    @Override
    public Etat actionEntree() {
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
