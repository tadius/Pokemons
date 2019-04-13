package com.tadiuzzz.pokemons.presenter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.repository.IRepositoryPokemonList;
import com.tadiuzzz.pokemons.repository.RepositoryPokemonList;
import com.tadiuzzz.pokemons.view.IViewPokemonList;

import java.util.ArrayList;

public class PresenterPokemonList implements IPresenterPokemonList, IOnEndSettingUpViewListener, IOnDataGotListener {

    private IViewPokemonList view;
    private IRepositoryPokemonList repository;

    public PresenterPokemonList(){
        Log.d(PokemonApplication.TAG, "PresenterPokemonList");
        repository = new RepositoryPokemonList();
    }

    private void loadData() {
        Log.d(PokemonApplication.TAG, "loadData");
        repository.getData(this);
    }

    @Override
    public void viewIsReady(IViewPokemonList view) {
        Log.d(PokemonApplication.TAG, "viewIsReady");
        this.view = view;
        loadData();
    }

    @Override
    public void onSwipeRefresh() {
        Log.d(PokemonApplication.TAG, "onSwipeRefresh");
    }

    @Override
    public void onScrolled(GridLayoutManager layoutManager) {
        Log.d(PokemonApplication.TAG, "onScrolled");
    }

    @Override
    public void onDataGotCallback(ArrayList<Pokemon> pokemons) {
        Log.d(PokemonApplication.TAG, "onDataGotCallback");
        view.setViewData(pokemons, this);
    }

    @Override
    public void onErrorGotCallback(String error) {
        Log.d(PokemonApplication.TAG, "onErrorGotCallback");
        view.showError(error);
    }

    @Override
    public void OnEndSettingUpViewCallback() {
        Log.d(PokemonApplication.TAG, "OnEndSettingUpViewCallback");
    }
}
