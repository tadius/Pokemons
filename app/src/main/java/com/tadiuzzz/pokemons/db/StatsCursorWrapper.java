package com.tadiuzzz.pokemons.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Ability;
import com.tadiuzzz.pokemons.model.Stat;
import com.tadiuzzz.pokemons.model.Stats;

public class StatsCursorWrapper extends CursorWrapper {
    public StatsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Stats getStats() {
        int id = getInt(getColumnIndex("_id"));
        int pokemonId = getInt(getColumnIndex(PokemonsDbSchema.StatsTable.Cols.ID));
        int statBase = getInt(getColumnIndex(PokemonsDbSchema.StatsTable.Cols.STATBASE));
        String statName = getString(getColumnIndex(PokemonsDbSchema.StatsTable.Cols.STATNAME));

        Stats stats = new Stats();
        stats.setBase_stat(statBase);
        stats.setStat(new Stat(id, statName));
        return stats;
    }
}