package com.tadiuzzz.pokemons.presenter;

import com.tadiuzzz.pokemons.model.Pokemon;

import java.util.ArrayList;

public interface IOnDataGotListener {
//    void onDataGotCallback(ArrayList<Pokemon> pokemons);
    void onDataGotCallback(Pokemon pokemon);
    void onErrorGotCallback(String error);
}
