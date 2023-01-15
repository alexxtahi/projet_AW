package librairies;

import cases.Case;
import main.Jeu;
import ressources.Affichage;
import ressources.Chemins;

public class Deplacement {

	// private Case destination;
	private String image;
	private String debut;
	private String fin;
	private int x;
	private int y;
	private int cout;

	public Deplacement(Case destination, String debut, String fin, int x, int y) {
		// this.destination = destination;
		this.image = Chemins.getCheminFleche(debut, fin);
		this.debut = debut;
		this.fin = fin;
		this.x = x;
		this.y = y;
	}

	public Deplacement(Case destination, String debut, String fin, int cout, int x, int y) {
		// this.destination = destination;
		this.image = Chemins.getCheminFleche(debut, fin);
		this.debut = debut;
		this.fin = fin;
		this.cout = cout;
		this.x = x;
		this.y = y;
	}

	/**
	 * Calcule et renvoie le coût du déplacement en fonction du moyen de déplacement
	 * et du type de l'unité terrain
	 * 
	 * @param moyenDeDep
	 * @param typeDeTerrain
	 * @return moyen de locomotion
	 * 
	 */
	protected static int getCoutDuDep(String moyenDeDep, String typeDeTerrain) {
		if (moyenDeDep.equals("Aerien")) {
			return 1;
		} else if ((moyenDeDep.equals("Pieds") || moyenDeDep.equals("Chenilles"))
				&& (typeDeTerrain.equals("Plaine") || !Jeu.dicoTypesTerrain.contains(typeDeTerrain))) {
			return 1;
		} else if (moyenDeDep.equals("Pieds") && typeDeTerrain.equals("Foret")) {
			return 1;
		} else if (moyenDeDep.equals("Pieds") && typeDeTerrain.equals("Montagne")) {
			return 2;
		} else if (moyenDeDep.equals("Chenilles") && typeDeTerrain.equals("Foret")) {
			return 2;
		}
		return -1;
	}

	public int[] getPosition() {
		return new int[] { x, y };
	}

	public String getDebut() {
		return debut;
	}

	public int getCout() {
		return cout;
	}

	public String getFin() {
		return fin;
	}

	/**
	 * modifie l'ancienne fin pour afficher la nouvelle fin
	 * 
	 * @param nouvelleFin la nouvelle position de l'unité
	 * 
	 */

	public void setFin(String nouvelleFin) {
		fin = nouvelleFin;
		image = Chemins.getCheminFleche(debut, nouvelleFin);
	}

	/**
	 * affiche l'image correspondant au déplacéments
	 * 
	 */
	public void affiche() {
		Affichage.dessineImageDansCase(x, y, image);
	}
}
