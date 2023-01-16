package cases;

import main.Joueur;
import ressources.Chemins;

public class Propriete extends Terrain {

    private Joueur joueur;
    private boolean disponible;

    public Propriete(String type, Joueur joueur, int x, int y) {
        super(type, x, y);
        this.joueur = joueur;
        this.disponible = false;
        // Attribution d'image
        switch (type) {
            case "Ville":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_VILLE, joueur.getId()));
                break;
            case "Usine":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_USINE, joueur.getId()));
                break;
            case "QG":
                super.setImage(Chemins.getCheminPropriete(Chemins.FICHIER_QG, joueur.getId()));
                break;
        }
    }

    /**
     * Renvoi le joueur affecté à la propriété
     * 
     * @return Le joueur propriétaire
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Permet d'affecter un joueur à la propriété
     * 
     * @param joueur L'instance de joueur à affecter
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
