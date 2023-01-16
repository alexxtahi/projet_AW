package librairies;

import java.util.ArrayList;

import cases.Case;
import unites.Unite;

public class NavigationLibre extends Etat {

	public NavigationLibre(int curseurX, int curseurY) {
		super(null, curseurX, curseurY);
		System.out.println("Navigation libre");
	}

	@Override
	public Etat actionHaut(Case[][] carte) {
		super.actionHaut(carte);
		return this;
	}

	@Override
	public Etat actionBas(Case[][] carte) {
		super.actionBas(carte);
		return this;
	}

	@Override
	public Etat actionGauche(Case[][] carte) {
		super.actionGauche(carte);
		return this;
	}

	@Override
	public Etat actionDroite(Case[][] carte) {
		super.actionDroite(carte);
		return this;
	}

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
			this.setPositionCurseur(listeUnitesDispo.get(0).getPosition());
		} else {
			if (indexUniteDispo < listeUnitesDispo.size()) {
				indexUniteDispo++;
			}
			if (indexUniteDispo >= listeUnitesDispo.size()) {
				indexUniteDispo = 0;
			}
			this.setPositionCurseur(listeUnitesDispo.get(indexUniteDispo).getPosition());
		}
		return this;

	}

}
