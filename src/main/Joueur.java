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

	/**
	 * Renvoi le numéro du joueur
	 * 
	 * @return Le numéro du joueur
	 */
	public int getId() {
		return id;
	}

	/**
	 * Renvoi l'argent du joueur
	 * 
	 * @return L'argent total du joueur
	 */
	public int getArgent() {
		return argent;
	}

	/**
	 * Augmenter le nombre de propriétés possédées par le joueur
	 */
	public void addProp() {
		nbProp++;
	}

	/**
	 * Augmenter l'argent du joueur
	 */
	public void addMoney() {
		argent += nbProp * 1000;
	}
}
