package main;

public class Joueur {

	private int id;
	private int argent;
	private int nbProp;

	public Joueur(int id) {
		this.id = id;
		this.argent = 0;
		this.nbProp = 0;
	}

	public int getId() {
		return id;
	}

	public int getArgent() {
		return argent;
	}

	/**
	 * Ajouter les propriétées de l'unité
	 */
	public void addProp() {
		nbProp++;
	}

	/**
	 * Augmenter l'argent d'un joueur
	 * 
	 */
	public void addMoney() {
		argent += nbProp * 1000;
	}
}
