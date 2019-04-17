package com.tadiuzzz.pokemons.presenter;

import android.support.v7.widget.GridLayoutManager;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.view.IViewPokemonAbout;
import com.tadiuzzz.pokemons.view.IViewPokemonList;

public interface IPresenterPokemonAbout {

    void viewIsReady(IViewPokemonAbout view);

    void onSaveButtonClick(Pokemon pokemon);

}
