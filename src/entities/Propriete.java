package entities;

import ressources.Chemins;

public class Propriete extends Terrain {

    private int indexJoueurProprietaire;

    public Propriete(String type, int indexJoueurActif) {
        super(type);
        indexJoueurProprietaire = indexJoueurActif;
        switch (type) {
            case "Ville":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_VILLE, indexJoueurActif));
                break;
            case "Usine":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_USINE, indexJoueurActif));
                break;
            case "QG":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_QG, indexJoueurActif));
                break;
        }
    }
}
