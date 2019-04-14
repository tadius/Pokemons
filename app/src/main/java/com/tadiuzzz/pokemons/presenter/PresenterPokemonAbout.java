package com.tadiuzzz.pokemons.presenter;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.repository.IRepositoryPokemonList;
import com.tadiuzzz.pokemons.repository.RepositoryPokemonList;
import com.tadiuzzz.pokemons.view.IViewPokemonList;

public class PresenterPokemonAbout implements IPresenterPokemonList, IOnEndSettingUpViewListener, IOnDataGotListener {

    private IViewPokemonList view;
    private IRepositoryPokemonList repository;
    private boolean isLoading = false;
    private String pokemonName;

    public PresenterPokemonAbout(){
//        Log.d(PokemonApplication.TAG, "PresenterPokemonList");
        repository = new RepositoryPokemonList();
    }

    public PresenterPokemonAbout(String pokemonName){
//        Log.d(PokemonApplication.TAG, "PresenterPokemonList");
        this.pokemonName = pokemonName;
        repository = new RepositoryPokemonList();
    }

    private void loadData() {
//        Log.d(PokemonApplication.TAG, "loadData");
        isLoading = true;
        view.setRefreshing(isLoading);
        repository.getData(pokemonName, this);
    }

    @Override
    public void viewIsReady(IViewPokemonList view) {
//        Log.d(PokemonApplication.TAG, "viewIsReady");
        this.view = view;

        loadData();
    }

    @Override
    public void onSwipeRefresh() {
//        Log.d(PokemonApplication.TAG, "onSwipeRefresh");
    }

    @Override
    public void onScrolled(GridLayoutManager layoutManager) {
//        Log.d(PokemonApplication.TAG, "onScrolled");
    }

    @Override
    public void onDataGotCallback(Pokemon pokemon) {
//        Log.d(PokemonApplication.TAG, "onDataGotCallback");
        view.setViewData(pokemon, this);
    }

    @Override
    public void onErrorGotCallback(String error) {
//        Log.d(PokemonApplication.TAG, "onErrorGotCallback");
        view.showError(error);
    }

    @Override
    public void OnEndSettingUpViewCallback() {
//        Log.d(PokemonApplication.TAG, "OnEndSettingUpViewCallback");
        isLoading = false;
    }
}
