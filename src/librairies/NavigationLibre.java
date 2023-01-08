package librairies;

import ressources.Config;

public class NavigationLibre extends Etat {

    public NavigationLibre(int curseurX, int curseurY) {
        super(curseurX, curseurY);
        System.out.println("Navigation libre");
    }

    @Override
    public Etat actionHaut() {
        super.actionHaut();
        return this;
    }

    @Override
    public Etat actionBas() {
        super.actionBas();
        return this;
    }

    @Override
    public Etat actionGauche() {
        super.actionGauche();
        return this;
    }

    @Override
    public Etat actionDroite() {
        super.actionDroite();
        return this;
    }

    @Override
    public Etat actionEntree() {
        return new ChoisitTrajet(getCurseurX(), getCurseurY());
    }

}
