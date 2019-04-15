package com.tadiuzzz.pokemons.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private PokemonCursorWrapper queryPokemons(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = db.query(
                PokemonsDbSchema.PokemonsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                orderBy
        );

        return new PokemonCursorWrapper(cursor);
    }

    private AbilitiesCursorWrapper queryAbilities(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = db.query(
                PokemonsDbSchema.AbilitiesTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                orderBy
        );

        return new AbilitiesCursorWrapper(cursor);
    }

    private StatsCursorWrapper queryStats(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = db.query(
                PokemonsDbSchema.StatsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                orderBy
        );

        return new StatsCursorWrapper(cursor);
    }

//    **************************
//    **** Получить список всех покемонов
//    **************************

    public ArrayList<Pokemon> getAllPokemons() {
        ArrayList<Pokemon> allPokemons = new ArrayList<Pokemon>();
        //все записи
        PokemonCursorWrapper cursor = queryPokemons(null, null, PokemonsDbSchema.PokemonsTable.Cols.ID);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Pokemon pokemon = cursor.getPokemon();

                ArrayList<Abilities> abilities = new ArrayList<Abilities>();
                abilities.addAll(getAbilitieByPokemonId(pokemon.getPokemonCharacteristics().getId()));
                pokemon.getPokemonCharacteristics().setAbilities(abilities);

                ArrayList<Stats> stats = new ArrayList<Stats>();
                stats.addAll(getStatsByPokemonId(pokemon.getPokemonCharacteristics().getId()));
                pokemon.getPokemonCharacteristics().setStats(stats);

                allPokemons.add(pokemon);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return allPokemons;
    }
//    ******************************************************************

//    *******************
//    **** Получить Abilities по PokemonId
//    *******************
    public ArrayList<Abilities> getAbilitieByPokemonId(int pokemonId) {
        ArrayList<Abilities> allAbilities = new ArrayList<Abilities>();

        AbilitiesCursorWrapper cursor = queryAbilities(PokemonsDbSchema.AbilitiesTable.Cols.ID + " = ?", new String[]{String.valueOf(pokemonId)}, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Abilities abilities = cursor.getAbilities();
                allAbilities.add(abilities);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return allAbilities;
    }

//    *******************
//    **** Получить Stats по PokemonId
//    *******************
    public ArrayList<Stats> getStatsByPokemonId(int pokemonId) {
        ArrayList<Stats> allStats = new ArrayList<Stats>();

        StatsCursorWrapper cursor = queryStats(PokemonsDbSchema.StatsTable.Cols.ID + " = ?", new String[]{String.valueOf(pokemonId)}, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Stats stats = cursor.getStats();
                allStats.add(stats);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return allStats;
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
