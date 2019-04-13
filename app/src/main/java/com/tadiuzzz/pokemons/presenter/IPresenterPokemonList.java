package com.tadiuzzz.pokemons.presenter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.tadiuzzz.pokemons.view.IViewPokemonList;

public interface IPresenterPokemonList {

    void viewIsReady(IViewPokemonList view);

    void onSwipeRefresh();

    void onScrolled(GridLayoutManager layoutManager);

}
