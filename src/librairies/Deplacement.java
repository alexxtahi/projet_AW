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
	 * Calcule et renvoie le coût d'un déplacement en fonction du moyen de
	 * déplacement et du type de l'unité sélectionnée
	 * 
	 * @param moyenDeDep    Le moyen de déplacement utilisé par l'unité sélectionnée
	 * @param typeDeTerrain Le type de terrain à traverser
	 * @return Le cout du déplacement de l'unité vers ce terrain, -1 si le terrain
	 *         est inaccessible pour l'unité
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

	/**
	 * Renvoi la position du déplacement sur les deux axes (x,y)
	 * 
	 * @return Un tableau d'entiers correspondant à la position sur les deux axes
	 *         (x,y)
	 */
	public int[] getPosition() {
		return new int[] { x, y };
	}

	/**
	 * Renvoi la direction de début du déplacement
	 * 
	 * @return La direction de début du déplacement
	 */
	public String getDebut() {
		return debut;
	}

	/**
	 * Renvoi le cout qu'il a fallu pour effectuer le déplacement
	 * 
	 * @return Le cout du déplacement
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * Renvoi la direction de fin du déplacement
	 * 
	 * @return La direction de fin du déplacement
	 */
	public String getFin() {
		return fin;
	}

	/**
	 * Affecte une nouvelle direction de fin au déplacement
	 * 
	 * @param nouvelleFin la nouvelle direction de la fin
	 */
	public void setFin(String nouvelleFin) {
		fin = nouvelleFin;
		image = Chemins.getCheminFleche(debut, nouvelleFin);
	}

	/**
	 * Affiche l'image correspondant au déplacément
	 */
	public void affiche() {
		Affichage.dessineImageDansCase(x, y, image);
	}
}
