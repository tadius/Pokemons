package com.tadiuzzz.pokemons.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.Stats;

import java.util.ArrayList;

public class PokemonsDBManager {

    private SQLiteDatabase db;

    public PokemonsDBManager(Context context) {
        db = new PokemonsDbHelper(context).getWritableDatabase();
    }

    public void addPokemon(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put(PokemonsDbSchema.PokemonsTable.Cols.NAMEOFPOKEMON, pokemon.getName());
        values.put(PokemonsDbSchema.PokemonsTable.Cols.ID, pokemon.getPokemonCharacteristics().getId());
        values.put(PokemonsDbSchema.PokemonsTable.Cols.SPRITEFRONT, pokemon.getPokemonCharacteristics().getSprites().getFront_default());
        values.put(PokemonsDbSchema.PokemonsTable.Cols.SPRITEBACK, pokemon.getPokemonCharacteristics().getSprites().getBack_default());

        db.insert(PokemonsDbSchema.PokemonsTable.NAME, null, values);
        Log.d(PokemonApplication.TAG, "POKEMON inserted");
    }

    public void addAbilities(Pokemon pokemon) {
        ArrayList<Abilities> abilities = pokemon.getPokemonCharacteristics().getAbilities();
        for (Abilities ability : abilities) {
            ContentValues values = new ContentValues();
            values.put(PokemonsDbSchema.AbilitiesTable.Cols.ID, pokemon.getPokemonCharacteristics().getId());
            values.put(PokemonsDbSchema.AbilitiesTable.Cols.ABILITYNAME, ability.getAbility().getName());
            db.insert(PokemonsDbSchema.AbilitiesTable.NAME, null, values);
            Log.d(PokemonApplication.TAG, "ABILITY inserted");
        }
    }

    public void addStats(Pokemon pokemon) {
        ArrayList<Stats> stats = pokemon.getPokemonCharacteristics().getStats();
        for (Stats stat : stats) {
            ContentValues values = new ContentValues();
            values.put(PokemonsDbSchema.StatsTable.Cols.ID, pokemon.getPokemonCharacteristics().getId());
            values.put(PokemonsDbSchema.StatsTable.Cols.STATNAME, stat.getStat().getName());
            values.put(PokemonsDbSchema.StatsTable.Cols.STATBASE, stat.getBase_stat());
            db.insert(PokemonsDbSchema.StatsTable.NAME, null, values);
            Log.d(PokemonApplication.TAG, "STAT inserted");
        }
    }
}
