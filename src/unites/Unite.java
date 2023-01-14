package unites;

import main.Joueur;
import ressources.Affichage;
import ressources.Chemins;

public class Unite {

	private Joueur joueur;
	private String image;
	private String moyenDeDep;
	private double pointsVie;
	private int pointsDepMax;
	private int pointsDep;
	private int prix;
	private int[] position = { 0, 0 };
	private boolean disponible;

	public Unite(Joueur joueur, String image, String moyenDeDep, int pointsDep, int prix, int x, int y) {
		this.joueur = joueur;
		this.image = image;
		this.moyenDeDep = moyenDeDep;
		this.pointsVie = 10;
		this.pointsDepMax = this.pointsDep = pointsDep;
		this.prix = prix;
		this.position = new int[] { x, y };
		this.disponible = false;
	}

	/**
	 * Générer une nouvelle unité associée à un joueur en fontion du type de l'unité
	 * et à une position bien précise sur la carte
	 * 
	 * @param type   le type de l'unité
	 * @param joueur le joueur associé a l'unité
	 * @param x      la position de l'unité sur l'axe x
	 * @param y      la position de l'unité sur l'axe y
	 *
	 * @return une nouvelle unité
	 */
	public static Unite genererUniteParType(String type, Joueur joueur, int x, int y) {
		switch (type) {
		case "Artillerie":
			return new Artillerie(joueur, "Pieds", x, y);
		case "Bazooka":
			return new Bazooka(joueur, "Pieds", x, y);
		case "Bombardier":
			return new Bombardier(joueur, "Aerien", x, y);
		case "Convoit":
			return new Convoit(joueur, "Chenilles", x, y);
		case "DCA":
			return new DCA(joueur, "Chenilles", x, y);
		case "Helico":
			return new Helico(joueur, "Aerien", x, y);
		case "Infanterie":
			return new Infanterie(joueur, "Pieds", x, y);
		case "Tank":
			return new Tank(joueur, "Chenilles", x, y);
		default:
			return null;
		}
	}

	public double getPointsVie() {
		return pointsVie;
	}

	/**
	 * diminue les points de vie de l'unité en fonction des dégats subit
	 *
	 * @param degats les dégats subit par l'unité
	 */
	public void diminuePV(float degats) {
		pointsVie -= degats;
	}

	public void restorePointsVie(float pv) {
		pointsVie += pv;
	}

	public int getPointsDep() {
		return pointsDep;
	}

	/**
	 * diminue les points de deplacement de l'unité en fonction du cout du
	 * deplacement
	 *
	 * @param coutDuDep le cout du deplacement de l'unité
	 */
	public void diminuePointsDep(int coutDuDep) {
		pointsDep -= coutDuDep;
	}

	public String getMoyenDeDep() {
		return moyenDeDep;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public int getPrix() {
		return prix;
	}

	public int[] getPosition() {
		return new int[] { position[0], position[1] };
	}

	public String getPositionString() {
		return position[0] + "_" + position[1];
	}

	/**
	 * modifie la position de l'unité
	 *
	 * @param x nouvelle position de l'unité sur l'axe x
	 * @param y nouvelle position de l'unité sur l'axe y
	 */

	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}

	/**
	 * verifie si l'unité est disponible
	 * 
	 * @return true si l'unité est disponible
	 */
	public boolean estDispo() {
		return disponible;
	}

	/**
	 * change la disponibilité de l'unité
	 */
	public void changeDispo() {
		disponible = !disponible;
	}

	/**
	 * affiche l'image de l'unité *
	 * 
	 */
	public void affiche() {
		Affichage.dessineImageDansCase(position[0], position[1],
				Chemins.getCheminUnite(joueur.getId(), disponible, image));
	}
}
