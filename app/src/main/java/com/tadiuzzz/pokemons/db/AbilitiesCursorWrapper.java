package com.tadiuzzz.pokemons.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Ability;
import com.tadiuzzz.pokemons.model.Pokemon;

public class AbilitiesCursorWrapper extends CursorWrapper {
    public AbilitiesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Abilities getAbilities() {
        int id = getInt(getColumnIndex("_id"));
        int pokemonId = getInt(getColumnIndex(PokemonsDbSchema.AbilitiesTable.Cols.ID));
        String abilityName = getString(getColumnIndex(PokemonsDbSchema.AbilitiesTable.Cols.ABILITYNAME));

        Abilities abilities = new Abilities();
        abilities.setAbility(new Ability(id, abilityName));
        return abilities;
    }
}