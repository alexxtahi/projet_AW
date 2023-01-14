package librairies;

import java.util.LinkedList;
import java.util.List;

import cases.Case;
import ressources.Chemins;
import ressources.Config;
import unites.Unite;

public abstract class Etat {

	protected Unite uniteAdeplacer;
	protected Case destinationDuCurseur;
	private int curseurX;
	private int curseurY;

	private List<Deplacement> listeDeplacements;

	public Etat(Unite uniteAdeplacer, int curseurX, int curseurY) {
		this.uniteAdeplacer = uniteAdeplacer;
		this.destinationDuCurseur = null;
		this.curseurX = curseurX;
		this.curseurY = curseurY;
		listeDeplacements = new LinkedList<Deplacement>();
	}

	public List<Deplacement> getDeplacements() {
		return new LinkedList<Deplacement>(listeDeplacements);
	}

	public int getNombreDep() {
		return listeDeplacements.size();
	}

	/**
	 * Ajoute un nouveau déplacement à la liste des déplacements que l'unité fait
	 * 
	 * @param destination la destination de l'unité
	 * @param debut       la position initiale de l'unité
	 * @param fin         la position finale de l'unité
	 */
	public void ajouteDeplacement(Case destination, String debut, String fin) {
		if (dernierDeplacement() == null) {
			listeDeplacements
					.add(new Deplacement(destination, Chemins.DIRECTION_DEBUT, fin, getCurseurX(), getCurseurY()));
		} else {
			dernierDeplacement().setFin(fin);
			listeDeplacements
					.add(new Deplacement(destination, debut, Chemins.DIRECTION_FIN, getCurseurX(), getCurseurY()));
		}
		int coutDuDep = Deplacement.getCoutDuDep(uniteAdeplacer.getMoyenDeDep(), destination.getTerrain().getType());
		System.out.println(uniteAdeplacer.getMoyenDeDep() + "/" + destination.getTerrain().getType()
				+ " -> cout du déplacement: " + coutDuDep);
		uniteAdeplacer.diminuePointsDep(coutDuDep);
	}

	// à revoir

	public void retireDeplacement(Deplacement deplacement) {
		listeDeplacements.remove(deplacement);
	}

	/**
	 * Récupère le dernier déplacement de l'unité
	 * 
	 * @return le dernier déplacement
	 * 
	 */
	public Deplacement dernierDeplacement() {
		if (listeDeplacements.size() != 0)
			return listeDeplacements.get(listeDeplacements.size() - 1);
		return null;
	}

	/**
	 * Déplace le curseur vers le haut sans sortir de la grille
	 * 
	 * @return la même instance de la classe Etat
	 * 
	 */

	public Etat actionHaut(Case[][] carte) {
		if (getCurseurY() < Config.longueurCarteYCases - 1) {
			deplaceCurseur(0, 1);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers le bas sans sortir de la grille
	 * 
	 * @return la même instance de la classe Etat
	 * 
	 */
	public Etat actionBas(Case[][] carte) {
		if (getCurseurY() > 0) {
			deplaceCurseur(0, -1);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers la gauche sans sortir de la grille
	 * 
	 * @return la même instance de la classe Etat
	 * 
	 */
	public Etat actionGauche(Case[][] carte) {
		if (getCurseurX() > 0) {
			deplaceCurseur(-1, 0);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	/**
	 * Déplace le curseur vers la droite sans sortir de la grille
	 * 
	 * @return la même instance de la classe Etat
	 * 
	 */

	public Etat actionDroite(Case[][] carte) {
		if (getCurseurX() < Config.longueurCarteXCases - 1) {
			deplaceCurseur(1, 0);
			destinationDuCurseur = carte[getCurseurY()][getCurseurX()];
		}
		return this;
	}

	public abstract Etat actionEntree(Case[][] carte, int indexJoueurActif);

	/**
	 * Déplace le curseur en fonction des coordonnées placées en paramètre
	 * 
	 * @param x la nouvelle position du curseur sur l'axe x
	 * @param y la nouvelle position du curseur sur l'axe y
	 * 
	 */
	public void deplaceCurseur(int x, int y) {
		curseurX += x;
		curseurY += y;
	}

	public int getCurseurX() {
		return curseurX;
	}

	public int getCurseurY() {
		return curseurY;
	}

}
