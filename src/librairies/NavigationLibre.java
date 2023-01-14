package librairies;

import cases.Case;
import ressources.Config;

public class NavigationLibre extends Etat {

    public NavigationLibre(int curseurX, int curseurY) {
        super(null, curseurX, curseurY);
        System.out.println("Navigation libre");
    }

    @Override
    public Etat actionHaut(Case destination) {
        super.actionHaut(destination);
        return this;
    }

    @Override
    public Etat actionBas(Case destination) {
        super.actionBas(destination);
        return this;
    }

    @Override
    public Etat actionGauche(Case destination) {
        super.actionGauche(destination);
        return this;
    }

    @Override
    public Etat actionDroite(Case destination) {
        super.actionDroite(destination);
        return this;
    }

    @Override
    public Etat actionEntree(Case caseActuelle, int indexJoueurActif) {
        if (caseActuelle.getUnite() != null && caseActuelle.getUnite().getJoueur().getId() == indexJoueurActif) {
            return new ChoisitTrajet(caseActuelle.getUnite(), getCurseurX(), getCurseurY());
        }
        System.out.println("Pas d'unité appartenant au joueur sur cette case");
        return this;
    }

}
