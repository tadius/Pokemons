package com.tadiuzzz.pokemons.model;

import java.util.ArrayList;

public class PokemonCharacteristics {

    private ArrayList<Abilities> abilities;
    private ArrayList<Forms> forms;
    private ArrayList<Stats> stats;
    private Sprites sprites;

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Forms> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Forms> forms) {
        this.forms = forms;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stats> stats) {
        this.stats = stats;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
