/** package principal */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entities.Propriete;
import entities.Terrain;
import librairies.AssociationTouches;
import librairies.StdDraw;
import ressources.Config;
import ressources.ParseurCartes;
import ressources.Affichage;
import ressources.Chemins;

public class Jeu {
	private int indexJoueurActif; // l'indice du joueur actif: 1 = rouge, 2 = bleu
	// l'indice 0 est reserve au neutre, qui ne joue pas mais peut posseder des
	// proprietes
	String[][] carteString;
	private int[] positionCurseur = { 0, 0 }; // Coordonnées du curseur sur la carte
	ArrayList<String> dicoTerrains, dicoProprietes;

	public Jeu(String fileName) throws Exception {
		// appel au parseur, qui renvoie un tableau de String
		carteString = ParseurCartes.parseCarte(fileName);
		// configuration des types de terrains et de propriétés
		dicoTerrains = new ArrayList<String>(Arrays.asList("Plaine", "Foret", "Montagne", "Eau"));
		dicoProprietes = new ArrayList<String>(Arrays.asList("Usine", "Ville", "QG"));

		Config.setDimension(carteString[0].length, carteString.length);
		// initialise la configuration avec la longueur de la carte

		indexJoueurActif = 1; // rouge commence
	}

	public boolean isOver() {
		return false;
	}

	public void afficheStatutJeu() {
		Affichage.videZoneTexte();
		Affichage.afficheTexteDescriptif("Status du jeu");
	}

	public void display() {
		StdDraw.clear();
		afficheStatutJeu();

		// Dessine les images des terrains correspondants à chaque case
		for (int i = 0; i < carteString.length; i++) {
			for (int j = 0; j < carteString[0].length; j++) {
				String[] typeEtUnite = carteString[i][j].split(";"); // Ici on sépare le type de terrain des unités
				// On vérifie si nous sommes sur un terrain ou une propriété
				String[] typeEtJoueur = typeEtUnite[0].split(":"); // Type de terrain/propriété des joueurs
				if (dicoTerrains.contains(typeEtJoueur[0])) {
					Terrain terrain = new Terrain(typeEtJoueur[0]);
					terrain.affiche(j, i);
				} else if (dicoProprietes.contains(typeEtJoueur[0])) {
					Propriete prop = new Propriete(typeEtJoueur[0], Integer.parseInt(typeEtJoueur[1]));
					prop.affiche(j, i);
				}
			}
		}

		Affichage.dessineImageDansCase(1, 1,
				Chemins.getCheminFleche(Chemins.DIRECTION_DROITE, Chemins.DIRECTION_DEBUT));
		Affichage.dessineImageDansCase(2, 1, Chemins.getCheminFleche(Chemins.DIRECTION_GAUCHE, Chemins.DIRECTION_HAUT));
		Affichage.dessineImageDansCase(2, 2, Chemins.getCheminFleche(Chemins.DIRECTION_BAS, Chemins.DIRECTION_HAUT));
		Affichage.dessineImageDansCase(2, 3, Chemins.getCheminFleche(Chemins.DIRECTION_BAS, Chemins.DIRECTION_FIN));

		Affichage.dessineImageDansCase(4, 4, Chemins.getCheminFleche(Chemins.DIRECTION_DEBUT, Chemins.DIRECTION_FIN));

		Affichage.dessineGrille(); // affiche une grille, mais n'affiche rien dans les cases
		drawGameCursor();
		StdDraw.show(); // montre a l'ecran les changement demandes
	}

	public void initialDisplay() {
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche
											// qu'au prochain StdDraw.show();
		display();
	}

	public void drawGameCursor() {
		Affichage.dessineCurseur(positionCurseur[0], positionCurseur[1]); // affiche le curseau en (0,0), a modifier
	}

	public void update() {

		AssociationTouches toucheSuivante = AssociationTouches.trouveProchaineEntree(); // cette fonction boucle jusqu'a
																						// la prochaine entree de
																						// l'utilisateur
		if (toucheSuivante.isHaut()) {
			// TODO: deplacer le curseur vers le haut
			System.out.println("Touche HAUT");
			if (positionCurseur[1] < Config.longueurCarteYCases - 1)
				positionCurseur[1]++;
		}
		if (toucheSuivante.isBas()) {
			// TODO: deplacer le curseur vers le bas
			System.out.println("Touche BAS");
			if (positionCurseur[1] > 0)
				positionCurseur[1]--;
		}
		if (toucheSuivante.isGauche()) {
			// TODO: deplacer le curseur vers la gauche
			System.out.println("Touche GAUCHE");
			if (positionCurseur[0] > 0)
				positionCurseur[0]--;
		}
		if (toucheSuivante.isDroite()) {
			// TODO: deplacer le curseur vers la droite
			System.out.println("Touche DROITE");
			if (positionCurseur[0] < Config.longueurCarteXCases - 1)
				positionCurseur[0]++;
		}

		// ATTENTION ! si vous voulez detecter d'autres touches que 't',
		// vous devez les ajouter au tableau Config.TOUCHES_PERTINENTES_CARACTERES
		if (toucheSuivante.isCaractere('t')) {
			String[] options = { "Oui", "Non" };
			if (Affichage.popup("Finir le tour de XXX?", options, true, 1) == 0) {
				// le choix 0, "Oui", a été selectionné
				// TODO: passer au joueur suivant
				System.out.println("FIN DE TOUR");
			}

		}
		// Actualisation de la carte
		display();
	}
}
