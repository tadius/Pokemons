package com.tadiuzzz.pokemons.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PokemonsDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pokemonsDb.db";

    public PokemonsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PokemonsDbSchema.PokemonsTable.NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PokemonsDbSchema.PokemonsTable.Cols.ID + " INTEGER, " +
                PokemonsDbSchema.PokemonsTable.Cols.SPRITEFRONT + " TEXT, " +
                PokemonsDbSchema.PokemonsTable.Cols.SPRITEBACK + " TEXT, " +
                PokemonsDbSchema.PokemonsTable.Cols.NAMEOFPOKEMON + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + PokemonsDbSchema.AbilitiesTable.NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PokemonsDbSchema.AbilitiesTable.Cols.ID + " INTEGER, " +
                PokemonsDbSchema.AbilitiesTable.Cols.ABILITYNAME + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + PokemonsDbSchema.StatsTable.NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PokemonsDbSchema.StatsTable.Cols.ID + " INTEGER, " +
                PokemonsDbSchema.StatsTable.Cols.STATBASE + " INTEGER, " +
                PokemonsDbSchema.StatsTable.Cols.STATNAME + " TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
