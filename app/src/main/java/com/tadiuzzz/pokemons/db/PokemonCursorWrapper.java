package com.tadiuzzz.pokemons.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.PokemonCharacteristics;
import com.tadiuzzz.pokemons.model.Sprites;

public class PokemonCursorWrapper extends CursorWrapper {
    public PokemonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Pokemon getPokemon() {
        int id = getInt(getColumnIndex("_id"));
        String nameOfPokemon = getString(getColumnIndex(PokemonsDbSchema.PokemonsTable.Cols.NAMEOFPOKEMON));
        int pokemonId = getInt(getColumnIndex(PokemonsDbSchema.PokemonsTable.Cols.ID));
        String spriteFront = getString(getColumnIndex(PokemonsDbSchema.PokemonsTable.Cols.SPRITEFRONT));
        String spriteBack = getString(getColumnIndex(PokemonsDbSchema.PokemonsTable.Cols.SPRITEBACK));


        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);
        pokemon.setName(nameOfPokemon);
        pokemon.setPokemonCharacteristics(new PokemonCharacteristics());

        pokemon.getPokemonCharacteristics().setName(nameOfPokemon);
        pokemon.getPokemonCharacteristics().setId(pokemonId);

        Sprites sprites = new Sprites();
        pokemon.getPokemonCharacteristics().setSprites(sprites);

        pokemon.getPokemonCharacteristics().getSprites().setFront_default(spriteFront);
        pokemon.getPokemonCharacteristics().getSprites().setBack_default(spriteBack);

        return pokemon;
    }
}