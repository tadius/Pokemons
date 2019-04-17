package com.tadiuzzz.pokemons.presenter;

import com.tadiuzzz.pokemons.model.Pokemon;

import java.util.ArrayList;

public interface IOnDataGotListener {
    void onDataGotCallback(Pokemon pokemon);
    void onDataGotCallback(ArrayList<Pokemon> pokemons);
    void onErrorGotCallback(String error);
}
