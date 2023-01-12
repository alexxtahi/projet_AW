package main;

import java.util.ArrayList;
import java.util.List;

import cases.Propriete;
import unites.Unite;

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

    public void addProp() {
        nbProp++;
    }

    public void addMoney() {
        argent += nbProp * 1000;
    }
}
