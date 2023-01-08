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
        String dernierDeplacement = (dernierDeplacement().getFin() != null) ? dernierDeplacement().getFin()
                : Chemins.DIRECTION_BAS;
        ajouteDeplacement(dernierDeplacement, Chemins.DIRECTION_HAUT);
        return this;
    }

    @Override
    public Etat actionBas() {
        super.actionBas();
        String dernierDeplacement = (dernierDeplacement().getFin() != null) ? dernierDeplacement().getFin()
                : Chemins.DIRECTION_HAUT;
        ajouteDeplacement(dernierDeplacement, Chemins.DIRECTION_BAS);
        return this;
    }

    @Override
    public Etat actionGauche() {
        super.actionGauche();
        String dernierDeplacement = (dernierDeplacement().getFin() != null) ? dernierDeplacement().getFin()
                : Chemins.DIRECTION_DROITE;
        ajouteDeplacement(dernierDeplacement, Chemins.DIRECTION_GAUCHE);
        return this;
    }

    @Override
    public Etat actionDroite() {
        super.actionDroite();
        Deplacement dernierDeplacement = dernierDeplacement();
        ajouteDeplacement((dernierDeplacement != null) ? dernierDeplacement.getFin() : Chemins.DIRECTION_GAUCHE,
                Chemins.DIRECTION_DROITE);
        return this;
    }

    @Override
    public Etat actionEntree() {
        return new NavigationLibre(getCurseurX(), getCurseurY());
    }

}
