package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cases.Propriete;
import unites.Unite;

public class Joueur {

    private int id;
    private List<Unite> unites;
    private List<Propriete> proprietes;

    public Joueur(int id) {
        this.id = id;
        unites = new ArrayList<Unite>();
        proprietes = new ArrayList<Propriete>();
    }

    public int getId() {
        return id;
    }

    public List<Unite> getUnites() {
        return unites;
    }

    public List<Propriete> getProprietes() {
        return proprietes;
    }

    public Unite getUniteParPosition(int[] position) {
        for (Unite u : unites) {
            if (u.getPositionString().equals(position[0] + "_" + position[1]))
                return u;
        }
        return null;
    }

    public Propriete getProprieteParPosition(int[] position) {
        for (Propriete p : proprietes) {
            if (p.getPositionString().equals(position[0] + "_" + position[1]))
                return p;
        }
        return null;
    }

    public void addUnite(Unite unite) {
        unites.add(unite);
    }

    public void addPropriete(Propriete propriete) {
        proprietes.add(propriete);
    }
}
