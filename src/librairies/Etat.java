package librairies;

import java.util.LinkedList;
import java.util.List;

import cases.Case;
import main.Jeu;
import ressources.Chemins;
import ressources.Config;
import unites.Unite;

public abstract class Etat {

	protected Unite uniteAdeplacer;
	protected Case destinationDuCurseur;
	private boolean pasEnArriere;
	private int curseurX;
	private int curseurY;

	protected List<Deplacement> listeDeplacements;
	protected List<Unite> listeUnitesDispo;
	protected int indexUniteDispo;

	public Etat(Unite uniteAdeplacer, int curseurX, int curseurY) {
		this.uniteAdeplacer = uniteAdeplacer;
		this.destinationDuCurseur = null;
		this.pasEnArriere = false;
		this.curseurX = curseurX;
		this.curseurY = curseurY;
		this.listeDeplacements = new LinkedList<Deplacement>();
		this.listeUnitesDispo = new LinkedList<Unite>();
		this.indexUniteDispo = 0;
	}

	/**
	 * Renvoi la liste des déplacements enregistré
	 *
	 * @return La liste des déplacements éffectués par l'unité séléectionnée par le
	 *         joueur
	 */
	public List<Deplacement> getDeplacements() {
		return new LinkedList<Deplacement>(listeDeplacements);
	}

	/**
	 * Réinitialise la liste des unités dispo d'un joueur
	 */
	public void clearListeUnitesDispos() {
		listeUnitesDispo.clear();
	}

	/**
	 * Renvoi le nombre de déplacement de l'unité sélectionnée par le joueur
	 * 
	 * @return Le nombre de déplacements
	 */
	public int getNombreDep() {
		return listeDeplacements.size();
	}

	/**
	 * Ajoute un nouveau déplacement à la liste des déplacements de l'unité
	 * sélectionnée par le joueur
	 *
	 * @param debut La direction de départ de l'unité
	 * @param fin   La direction d'arrivée de l'unité
	 */
	public void ajouteDeplacement(String debut, String fin) {
		if (pasEnArriere) {
			pasEnArriere = false;
			System.out.println("Pas en arriere");
			return;
		}
		System.out.println("Pas en avant");

		int coutDuDep = Deplacement.getCoutDuDep(uniteAdeplacer.getMoyenDeDep(),
				this.destinationDuCurseur.getTerrain().getType());

		if (dernierDeplacement() == null) {
			System.out.println("1er dep");
			listeDeplacements.add(new Deplacement(this.destinationDuCurseur, Chemins.DIRECTION_DEBUT,
					Chemins.DIRECTION_FIN, coutDuDep, getCurseurX(), getCurseurY()));
		} else {
			System.out.println("Nouveau dep");
			dernierDeplacement().setFin(fin);
			listeDeplacements.add(new Deplacement(this.destinationDuCurseur, debut, Chemins.DIRECTION_FIN, coutDuDep,
					getCurseurX(), getCurseurY()));
		}
		uniteAdeplacer.diminuePointsDep(coutDuDep);
		System.out.println(uniteAdeplacer.getMoyenDeDep() + "/" + this.destinationDuCurseur.getTerrain().getType()
				+ " -> cout du déplacement: " + coutDuDep);
	}

	/**
	 * Vérifie si le joueur est revenu en arrière dans le dessin du trajet d'une
	 * unité
	 *
	 * @return true s'il y'a eu retour en arrière, false sinon
	 */
	public boolean depEnArriere() {
		// On vérifie si le joueur a fait un pas en arrière
		if (Jeu.memesPositions(destinationDuCurseur.getPosition(), uniteAdeplacer.getPosition())) {
			pasEnArriere = true;
			listeDeplacements.clear();
			uniteAdeplacer.resetDep();
		} else {
			int depDepenses = 0;
			LinkedList<Deplacement> trajetAeffacer = new LinkedList<Deplacement>();
			for (Deplacement dep : listeDeplacements) {
				if (!pasEnArriere && Jeu.memesPositions(destinationDuCurseur.getPosition(), dep.getPosition())) {
					pasEnArriere = true;
					dep.setFin(Chemins.DIRECTION_FIN);
				} else if (pasEnArriere && dep != null) {
					depDepenses += dep.getCout();
					trajetAeffacer.add(dep);
				}
			}
			listeDeplacements.removeAll(trajetAeffacer);
			uniteAdeplacer.restorePointsDep(depDepenses);
		}
		return pasEnArriere;
	}

	/**
	 * Renvoi le dernier déplacement de l'unité sélectionnée par le joueur
	 *
	 * @return Le dernier déplacement
	 */
	public Deplacement dernierDeplacement() {
		if (listeDeplacements.size() != 0)
			return listeDeplacements.get(listeDeplacements.size() - 1);
		return null;
	}

	/**
	 * Vérifie si la case de destination du curseur est en dehors des limites
	 * 
	 * @param x la valeur à ajouter à l'axe x pour déplacer le curseur
	 * @param y la valeur à ajouter à l'axe y pour déplacer le curseur
	 * @return true si le cursuer sera en dehors des limites, false sinon
	 */
	public boolean isOutOfLimit(int x, int y) {
		boolean outOfLimitVert = getCurseurX() + x < 0 || getCurseurX() + x > Config.longueurCarteXCases - 1;
		boolean outOfLimitHoriz = getCurseurY() + y < 0 || getCurseurY() + y > Config.longueurCarteYCases - 1;
		return (outOfLimitVert || outOfLimitHoriz);
	}

	/**
	 * Déplace le curseur vers le haut sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	public Etat actionHaut(Case[][] carte) {
		if (!isOutOfLimit(0, 1)) {
			deplaceCurseur(0, 1);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers le bas sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	public Etat actionBas(Case[][] carte) {
		if (!isOutOfLimit(0, -1)) {
			deplaceCurseur(0, -1);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers la gauche sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	public Etat actionGauche(Case[][] carte) {
		if (!isOutOfLimit(-1, 0)) {
			deplaceCurseur(-1, 0);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers la droite sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	public Etat actionDroite(Case[][] carte) {
		if (!isOutOfLimit(1, 0)) {
			deplaceCurseur(1, 0);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	public abstract Etat actionEntree(Case[][] carte, int indexJoueurActif);

	public abstract Etat actionEchap();

	public abstract Etat actionY(Case[][] carte, int indexJoueurActif);

	/**
	 * Déplace le curseur en fonction des coordonnées passées en paramètre
	 * 
	 * @param x La nouvelle position du curseur sur l'axe x
	 * @param y La nouvelle position du curseur sur l'axe y
	 */
	public void deplaceCurseur(int x, int y) {
		curseurX += x;
		curseurY += y;
	}

	/**
	 * Renvoi la position du curseur sur l'axe x
	 * 
	 * @return Un entier correspondant à la position sur l'axe x
	 */
	public int getCurseurX() {
		return curseurX;
	}

	/**
	 * Renvoi la position du curseur sur l'axe y
	 * 
	 * @return Un entier correspondant à la position sur l'axe y
	 */
	public int getCurseurY() {
		return curseurY;
	}

	/**
	 * Renvoi la position du curseur sur les deux axes (x,y)
	 * 
	 * @return Un tableau d'entiers correspondant à la position sur les deux axes
	 *         (x,y)
	 */
	public int[] getPositionCurseur() {
		return new int[] { curseurX, curseurY };
	}

	/**
	 * Déplace le curseur à une nouvelle position
	 * 
	 * @param position Un tableau d'entiers correspondant à future position du
	 *                 curseur
	 */
	public void setPositionCurseur(int[] position) {
		curseurX = position[0];
		curseurY = position[1];
	}

}
