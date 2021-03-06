package com.tadiuzzz.pokemons.repository;

import android.content.Context;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;

public interface IRepositoryPokemonList {

    void addDataToDb(Context context, Pokemon pokemon);
    void getData(int offset, IOnDataGotListener listener);
    void getData(String name, IOnDataGotListener listener);
    void getDataFromDatabase(Context context, IOnDataGotListener listener);
    void getDataFromDatabase(int dbId, Context context, IOnDataGotListener listener);
    void getDataFromDatabase(String name, Context context, IOnDataGotListener listener);

}

