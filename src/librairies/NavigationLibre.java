package librairies;

import cases.Case;
import unites.Unite;

public class NavigationLibre extends Etat {

	public NavigationLibre(int curseurX, int curseurY) {
		super(null, curseurX, curseurY);
		System.out.println("Navigation libre");
	}

	/**
	 * Déplace le curseur vers le haut sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	@Override
	public Etat actionHaut(Case[][] carte) {
		super.actionHaut(carte);
		return this;
	}

	/**
	 * Déplace le curseur vers le bas sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	@Override
	public Etat actionBas(Case[][] carte) {
		super.actionBas(carte);
		return this;
	}

	/**
	 * Déplace le curseur vers la gauche sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	@Override
	public Etat actionGauche(Case[][] carte) {
		super.actionGauche(carte);
		return this;
	}

	/**
	 * Déplace le curseur vers la droite sans sortir de la grille
	 *
	 * @param carte Un tableau à 2 dimensions représentant la carte du jeu
	 * @return l'instance modifiée de la classe
	 */
	@Override
	public Etat actionDroite(Case[][] carte) {
		super.actionDroite(carte);
		return this;
	}

	/**
	 * Permet de sélectionner une unité parmi les unités disponibles du joueur
	 * actif
	 *
	 * @param carte            Un tableau à 2 dimensions représentant la carte du
	 *                         jeu
	 * @param indexJoueurActif Le numéro du joueur actif
	 * @return l'instance modifiée de la classe pour un choix de trajet, ne fait
	 *         rien s'il n'y a pas d'unité appartenant au joueur sur la case ou si
	 *         son unité n'est plus disponible
	 */
	@Override
	public Etat actionEntree(Case[][] carte, int indexJoueurActif) {
		Case caseDeDepart = carte[getCurseurY()][getCurseurX()];
		Unite unitePresente = caseDeDepart.getUnite();
		if (unitePresente != null && unitePresente.estDispo()
				&& unitePresente.getJoueur().getId() == indexJoueurActif) {
			return new ChoisitTrajet(caseDeDepart, unitePresente, getCurseurX(), getCurseurY());
		}
		System.out.println("Pas d'unité appartenant au joueur sur cette case");
		return this;
	}

	@Override
	public Etat actionEchap() {
		return this;
	}

	/**
	 * Bonus 1: Déplace le curseur sur les unités disponibles du joueur actif
	 * 
	 * @param carte            Un tableau à 2 dimensions représentant la carte du
	 *                         jeu
	 * @param indexJoueurActif Le numéro du joueur actif
	 * @return l'instance modifiée de la classe
	 */
	@Override
	public Etat actionY(Case[][] carte, int indexJoueurActif) {
		if (listeUnitesDispo.size() == 0) {
			for (Case[] ligne : carte) {
				for (Case c : ligne) {
					Unite unite = c.getUnite();
					if (unite != null && unite.getJoueur().getId() == indexJoueurActif && unite.estDispo()) {
						listeUnitesDispo.add(unite);
					}
				}
			}
			indexUniteDispo = 0;
		} else {
			indexUniteDispo = (indexUniteDispo + 1 < listeUnitesDispo.size()) ? indexUniteDispo + 1 : 0;
		}
		this.setPositionCurseur(listeUnitesDispo.get(indexUniteDispo).getPosition());
		return this;

	}

}
