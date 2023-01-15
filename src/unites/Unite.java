package unites;

import java.util.ArrayList;

import cases.Case;
import librairies.Arme;
import main.Joueur;
import ressources.Affichage;
import ressources.Chemins;
import ressources.Config;

public class Unite {

	private Joueur joueur;
	private String image;
	private String moyenDeDep;
	private String arme;
	private float pointsVie;
	private int pointsDepMax;
	private int pointsDep;
	private int prix;
	private int x;
	private int y;
	private boolean disponible;

	public Unite(Joueur joueur, String image, String moyenDeDep, String arme, int pointsDep, int prix, int x, int y) {
		this.joueur = joueur;
		this.image = image;
		this.moyenDeDep = moyenDeDep;
		this.arme = arme;
		this.pointsVie = 10f;
		this.pointsDepMax = this.pointsDep = pointsDep;
		this.prix = prix;
		this.x = x;
		this.y = y;
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
				return new Artillerie(joueur, "Pieds", "Mortier", x, y);
			case "Bazooka":
				return new Bazooka(joueur, "Pieds", "Canon", x, y);
			case "Bombardier":
				return new Bombardier(joueur, "Aerien", "Bombes", x, y);
			case "Convoit":
				return new Convoit(joueur, "Chenilles", null, x, y);
			case "DCA":
				return new DCA(joueur, "Chenilles", "Mlourde", x, y);
			case "Helico":
				return new Helico(joueur, "Aerien", "Missiles", x, y);
			case "Infanterie":
				return new Infanterie(joueur, "Pieds", "Mlegere", x, y);
			case "Tank":
				return new Tank(joueur, "Chenilles", "Canon", x, y);
			default:
				return null;
		}
	}

	public ArrayList<Unite> detectEnnemisProches(Case[][] carte, Case destination) {
		ArrayList<Unite> unitesEnnemies = new ArrayList<Unite>(4);
		for (int i = destination.getX() - 1; i <= destination.getX() + 1; i++) {
			for (int j = destination.getY() - 1; j <= destination.getY() + 1; j++) {
				if ((0 <= i && i < Config.longueurCarteXCases) && (0 <= j && j < Config.longueurCarteYCases)) {
					Unite uniteAdjacente = carte[j][i].getUnite();
					if (uniteAdjacente != null
							&& (uniteAdjacente.getX() == destination.getX()
									|| uniteAdjacente.getY() == destination.getY())
							&& uniteAdjacente.getJoueur().getId() != this.joueur.getId()) {
						unitesEnnemies.add(uniteAdjacente);
						System.out.println(j + "," + i + " -> " + uniteAdjacente.getClass().getName());
					}
				}
			}
		}
		return unitesEnnemies;
	}

	public float getPointsVie() {
		return pointsVie;
	}

	public int getPointsVieArrondis() {
		return (int) Math.ceil(pointsVie);
	}

	/**
	 * Diminue les points de vie de l'unité en fonction des dégats subit
	 *
	 * @param degats les dégats subit par l'unité
	 */
	public void diminuePV(float degats) {
		pointsVie -= degats;
	}

	public void restorePointsVie(float pv) {
		pointsVie += pv;
	}

	public void attaque(Unite uniteAattaquer) {
		float efficaciteArme = Arme.efficacites.get(this.arme).get(uniteAattaquer.getClass().getSimpleName());
		float degats = getPointsVieArrondis() * efficaciteArme;
		uniteAattaquer.diminuePV(degats);
		System.out.println("Attaque");
	}

	public int getPointsDep() {
		return pointsDep;
	}

	public void resetDep() {
		pointsDep = pointsDepMax;
	}

	public void restorePointsDep(int coutDuDep) {
		pointsDep += coutDuDep;
	}

	/**
	 * Diminue les points de deplacement de l'unité en fonction du cout du
	 * déplacement
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[] getPosition() {
		return new int[] { x, y };
	}

	/**
	 * Déplacer l'unité vers une nouvelle position
	 *
	 * @param x nouvelle position de l'unité sur l'axe x
	 * @param y nouvelle position de l'unité sur l'axe y
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		setDispo(false);
	}

	/**
	 * Vérifie si l'unité est disponible
	 *
	 * @return true si l'unité est disponible, false sinon
	 */
	public boolean estDispo() {
		return disponible;
	}

	/**
	 * Change la disponibilité de l'unité
	 *
	 * @param dispo true pour rendre l'unité disponible, false pour la rendre
	 *              indisponible
	 */
	public void setDispo(boolean dispo) {
		disponible = dispo;
	}

	/**
	 * Affiche l'image de l'unité à l'écran
	 */
	public void affiche() {
		if (pointsVie > 0.0f) {
			Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur.getId(), disponible, image));
			if (pointsVie != 10) {
				Affichage.afficheTexteDansCase(x, y, getPointsVieArrondis() + "", Config.POINTS_DE_VIE_UNITES, 0.7, 0.2,
						Config.POLICE_PV_UNITES);
			}
		}
	}
}
