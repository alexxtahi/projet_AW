package cases;

import ressources.Chemins;

public class Propriete extends Terrain {

    private int idJoueur;

    public Propriete(String type, int idJoueur) {
        super(type);
        this.idJoueur = idJoueur;
        switch (type) {
            case "Ville":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_VILLE, idJoueur));
                break;
            case "Usine":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_USINE, idJoueur));
                break;
            case "QG":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_QG, idJoueur));
                break;
        }
    }

    public int getIdJoueur() {
        return idJoueur;
    }
}
