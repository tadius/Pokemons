package com.tadiuzzz.pokemons.model;

import android.support.annotation.NonNull;

public class Pokemon {

    private String name;
    private int id;
    private PokemonCharacteristics pokemonCharacteristics;

    public Pokemon(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonCharacteristics getPokemonCharacteristics() {
        return pokemonCharacteristics;
    }

    public void setPokemonCharacteristics(PokemonCharacteristics pokemonCharacteristics) {
        this.pokemonCharacteristics = pokemonCharacteristics;
    }

}
