/** package principal */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cases.Propriete;
import cases.Terrain;
import librairies.AssociationTouches;
import librairies.ChoisitTrajet;
import librairies.Deplacement;
import librairies.Etat;
import librairies.NavigationLibre;
import librairies.StdDraw;
import ressources.Config;
import ressources.ParseurCartes;
import unites.Unite;
import ressources.Affichage;
import ressources.Chemins;

public class Jeu {
	private int indexJoueurActif; // l'indice du joueur actif: 1 = rouge, 2 = bleu
	// l'indice 0 est reserve au neutre, qui ne joue pas mais peut posseder des
	// proprietes
	private String[][] carteString;
	private List<Joueur> joueurs;
	private List<Terrain> terrains;
	private List<String> dicoTypesTerrain, dicoTypesPropriete;
	private int round;
	private Etat etat;

	public Jeu(String fileName) throws Exception {
		// Appel au parseur, qui renvoie un tableau de String
		carteString = ParseurCartes.parseCarte(fileName);
		// Configs de départ
		terrains = new ArrayList<Terrain>();
		dicoTypesTerrain = new ArrayList<String>(Arrays.asList("Plaine", "Foret", "Montagne", "Eau"));
		dicoTypesPropriete = new ArrayList<String>(Arrays.asList("Usine", "Ville", "QG"));

		joueurs = new ArrayList<Joueur>();
		joueurs.add(new Joueur(0)); // Joueur neutre 0
		joueurs.add(new Joueur(1)); // Joueur rouge 1
		joueurs.add(new Joueur(2)); // Joueur bleu 2

		genererCases();

		etat = new NavigationLibre(0, 0);

		Config.setDimension(carteString[0].length, carteString.length);
		// Initialise la configuration avec la longueur de la carte
		indexJoueurActif = 1; // rouge commence
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

		String msg = "Round : " + round;
		msg += " - Joueur actuel : " + ((indexJoueurActif == 1) ? "Rouge" : "Bleu");
		Joueur joueurActif = joueurs.get(indexJoueurActif);
		msg += " - Argent : " + joueurActif.getArgent();
		Affichage.videZoneTexte();
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
	 */
	public void genererCases() {
		for (int i = 0; i < carteString.length; i++) {
			for (int j = 0; j < carteString[0].length; j++) {
				String[][] caseDispatchee = dispacthCaseString(carteString[i][j]);
				// Générer les terrains et propriétés
				if (dicoTypesTerrain.contains(caseDispatchee[0][0])) {
					terrains.add(new Terrain(caseDispatchee[0][0], j, i));
				} else if (dicoTypesPropriete.contains(caseDispatchee[0][0])) {
					Joueur joueurProprietaire = (caseDispatchee[0][1].equals("0")) ? joueurs.get(0)
							: joueurs.get(Integer.parseInt(caseDispatchee[0][1]));
					Propriete nouvellePropriete = new Propriete(caseDispatchee[0][0], joueurProprietaire, j, i);
					joueurProprietaire.addPropriete(nouvellePropriete);
				}
				// Générer les unités
				if (caseDispatchee[1][0] != null) {
					Joueur joueurProprietaire = joueurs.get(Integer.parseInt(caseDispatchee[1][1]));
					Unite nouvelleUnite = Unite.genererUniteParType(caseDispatchee[1][0],
							joueurProprietaire, j, i);
					nouvelleUnite.changeDispo();
					joueurProprietaire.addUnite(nouvelleUnite);
				}
			}
		}
		// Définir l'argent des joueurs
		for (Joueur j : joueurs)
			j.addMoney();
	}

	/**
	 * Affiche dans la fenêtre l'image de chaque case
	 */
	public void afficheCases() {
		for (Terrain t : terrains) {
			t.affiche();
		}
		for (Joueur j : joueurs) {
			for (Propriete p : j.getProprietes()) {
				p.affiche();
			}
			for (Unite u : j.getUnites()) {
				u.affiche();
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
				Chemins.getCheminFleche(Chemins.DIRECTION_DROITE, Chemins.DIRECTION_DEBUT));
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
			if (Affichage.popup("Finir le tour du joueur " + indexJoueurActif + " ?", options, true, 1) == 0) {
				// le choix 0, "Oui", a été selectionné
				System.out.println("FIN DE TOUR");
			}

		}
		if (toucheSuivante.isEntree()) { // Action de la touche entrée
			// Sélectionner une unité
			TestJeu.afficheElementDansCase(etat.getCurseurX(), etat.getCurseurY(), carteString);
			etat = etat.actionEntree();
		}
		// Actualisation de l'affichage
		display();
	}
}
