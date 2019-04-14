package com.tadiuzzz.pokemons.model;

import java.util.Comparator;

public class ComparePokemon implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon pokemon, Pokemon pokemon1) {
        return pokemon.getPokemonCharacteristics().getId() - pokemon1.getPokemonCharacteristics().getId();
    }
}
