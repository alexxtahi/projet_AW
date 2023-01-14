/** package principal */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cases.Case;
import cases.Propriete;
import cases.Terrain;
import librairies.AssociationTouches;
import librairies.ChoisitTrajet;
import librairies.Deplacement;
import librairies.Etat;
import librairies.NavigationLibre;
import librairies.StdDraw;
import ressources.Affichage;
import ressources.Chemins;
import ressources.Config;
import ressources.ParseurCartes;
import unites.Unite;

public class Jeu {
	private int indexJoueurActif; // l'indice du joueur actif: 1 = rouge, 2 = bleu
	// l'indice 0 est reserve au neutre, qui ne joue pas mais peut posseder des
	// proprietes
	private Case[][] carte;
	private List<Joueur> joueurs;
	private int round;
	private Etat etat;
	private Joueur joueurActif;

	public static List<String> dicoTypesTerrain = new ArrayList<String>(
			Arrays.asList("Plaine", "Foret", "Montagne", "Eau"));

	public Jeu(String fileName) throws Exception {

		// Initialisation des joueurs
		joueurs = new ArrayList<Joueur>();
		joueurs.add(new Joueur(0)); // Joueur neutre 0
		joueurs.add(new Joueur(1)); // Joueur rouge 1
		joueurs.add(new Joueur(2)); // Joueur bleu 2
		indexJoueurActif = 1; // rouge commence
		joueurActif = joueurs.get(indexJoueurActif);

		// Création de la carte
		String[][] carteString = ParseurCartes.parseCarte(
				fileName);
		carte = new Case[carteString.length][];
		Config.setDimension(carteString[0].length, carteString.length);
		genererCases(carteString);

		// Configs de départ
		etat = new NavigationLibre(0, 0);
		round = 1; // 1er round
	}

	/**
	 * Indique l'état de la partie
	 *
	 * @return true si la partie est terminée, false sinon
	 */
	public boolean isOver() {
		return false;
	}

	/**
	 * Affiche les détails de la partie en cours
	 */
	public void afficheStatutJeu() {
		// Chargement du message
		String msg = "Round : " + round;
		msg += " - Joueur actuel : " + ((indexJoueurActif == 1) ? "Rouge" : "Bleu");
		msg += " - Argent : " + joueurActif.getArgent();
		// Affichage
		// Affichage.videZoneTexte(); // Je ne comprends pas l'intérêt de cette fonction
		Affichage.afficheTexteDescriptif(msg);
	}

	/**
	 * Converti une case représentée par une chaine de caractère en matrice
	 *
	 * @param caseString la chaine de caratère d'une case
	 * @return une matrice contenant le type de la case et l'unité potentiellement
	 *         présente sur cette case
	 */
	public String[][] dispacthCaseString(String caseString) {
		String[][] dispacthTab = new String[2][2];
		String[] caseEtUnite = caseString.split(";");
		for (int i = 0; i < caseEtUnite.length; i++) {
			String[] caseEtJoueur = caseEtUnite[i].split(":");
			for (int j = 0; j < caseEtJoueur.length; j++) {
				dispacthTab[i][j] = caseEtJoueur[j];
			}
		}
		return dispacthTab;
	}

	/**
	 * Utilise la matrice de string de la carte pour générer les cases
	 *
	 * @param caseString la matrice de String de la carte
	 */
	public void genererCases(String[][] carteString) {
		for (int i = 0; i < carteString.length; i++) {
			carte[i] = new Case[carteString[i].length];
			for (int j = 0; j < carteString[0].length; j++) {
				String[][] caseDispatchee = dispacthCaseString(carteString[i][j]);
				// Générer le terrain ou la propriété
				Terrain terrain = null;
				Unite unite = null;
				if (dicoTypesTerrain.contains(caseDispatchee[0][0])) { // Si on a un terrain
					terrain = new Terrain(caseDispatchee[0][0], j, i);
				} else { // Si on a une propriété
					Joueur joueurProprietaire = (caseDispatchee[0][1].equals("0")) ? joueurs.get(0) // à modifier
							: joueurs.get(Integer.parseInt(caseDispatchee[0][1]));
					terrain = new Propriete(caseDispatchee[0][0], joueurProprietaire, j, i);
					joueurProprietaire.addProp();
				}
				// Générer l'unité potentiellement présente
				if (caseDispatchee[1][0] != null) {
					Joueur joueurProprietaire = joueurs.get(Integer.parseInt(caseDispatchee[1][1]));
					unite = Unite.genererUniteParType(caseDispatchee[1][0], joueurProprietaire, j, i);
					unite.changeDispo();
				}
				// Générer la case
				carte[i][j] = new Case(terrain, unite, j, i);
			}
		}
		joueurActif.addMoney();
		TestJeu.afficheArgentDesJoueurs(joueurs);
	}

	/**
	 * Affiche dans la fenêtre l'image de chaque case
	 */
	public void afficheCases() {
		for (Case[] ligne : carte) {
			for (Case c : ligne) {
				c.affiche();
			}
		}

		if (etat instanceof ChoisitTrajet) {
			for (Deplacement d : etat.getDeplacements()) {
				d.affiche();
			}
		}
	}

	public void display() {
		StdDraw.clear();
		afficheStatutJeu();
		afficheCases();

		// ! TEST
		Affichage.dessineImageDansCase(1, 1,
				Chemins.getCheminFleche(Chemins.DIRECTION_DEBUT, Chemins.DIRECTION_HAUT));
		Affichage.dessineImageDansCase(2, 1, Chemins.getCheminFleche(Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_HAUT));
		Affichage.dessineImageDansCase(2, 2, Chemins.getCheminFleche(Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT));
		Affichage.dessineImageDansCase(2, 3, Chemins.getCheminFleche(Chemins.DIRECTION_BAS, Chemins.DIRECTION_FIN));

		Affichage.dessineImageDansCase(4, 4, Chemins.getCheminFleche(Chemins.DIRECTION_DEBUT, Chemins.DIRECTION_FIN));
		// ! FIN TEST

		Affichage.dessineGrille(); // Affiche une grille, mais n'affiche rien dans
		// les cases
		drawGameCursor(); // Dessine le curseur sur la fenêtre
		StdDraw.show(); // Montre a l'ecran les changement demandes
	}

	public void initialDisplay() {
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche
											// qu'au prochain StdDraw.show();
		display();
	}

	public void drawGameCursor() {
		Affichage.dessineCurseur(etat.getCurseurX(), etat.getCurseurY()); // affiche le curseau en (0,0), a modifier
	}

	public void changeTour() {
		if (indexJoueurActif == 1) {
			indexJoueurActif = 2;
		} else {
			indexJoueurActif = 1;
			round++;
		}
		// Changer le joueur actif en fct du nouvel index
		joueurActif = joueurs.get(indexJoueurActif);
		// Augmenter l'argent du joueur en fct de ses propriétés
		joueurActif.addMoney();
		TestJeu.afficheArgentDesJoueurs(joueurs);
	}

	public void update() {

		AssociationTouches toucheSuivante = AssociationTouches.trouveProchaineEntree(); // cette fonction boucle jusqu'a
																						// la prochaine entree de
																						// l'utilisateur
		if (toucheSuivante.isHaut())
			etat = etat.actionHaut();
		if (toucheSuivante.isBas())
			etat = etat.actionBas();
		if (toucheSuivante.isGauche())
			etat = etat.actionGauche();
		if (toucheSuivante.isDroite())
			etat = etat.actionDroite();

		// ATTENTION ! si vous voulez detecter d'autres touches que 't',
		// vous devez les ajouter au tableau Config.TOUCHES_PERTINENTES_CARACTERES
		if (toucheSuivante.isCaractere('t')) { // Finir le tour du joueur actif
			String[] options = { "Oui", "Non" };
			if (Affichage.popup("Finir le tour du joueur " + indexJoueurActif + " ?", options, true, 0) == 0) {
				// le choix 0, "Oui", a été selectionné
				changeTour();
			}
		}
		if (toucheSuivante.isEntree()) { // Action de la touche entrée
			// Sélectionner une unité
			TestJeu.afficheElementDansCase(etat.getCurseurX(), etat.getCurseurY(), carte);
			etat = etat.actionEntree(carte, indexJoueurActif);
		}
		// Actualisation de l'affichage
		display();
	}
}
