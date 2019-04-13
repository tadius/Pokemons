package com.tadiuzzz.pokemons;

import android.app.Application;

public class PokemonApplication extends Application {
    public static final String TAG = "logTag";
    public static PokemonApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
