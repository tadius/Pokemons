package com.tadiuzzz.pokemons.presenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.repository.IRepositoryPokemonList;
import com.tadiuzzz.pokemons.repository.RepositoryPokemonList;
import com.tadiuzzz.pokemons.view.IViewPokemonAbout;
import com.tadiuzzz.pokemons.view.IViewPokemonList;

import java.util.ArrayList;

public class PresenterPokemonAbout implements IPresenterPokemonAbout, IOnEndSettingUpViewListener, IOnDataGotListener {

    private IViewPokemonAbout view;
    private IRepositoryPokemonList repository;
    private boolean isLoading = false;
    private String pokemonName;
    private int dbId;

    private final int ICON_SAVE = 1;
    private final int ICON_DELETE = 2;

    public PresenterPokemonAbout() {
        repository = new RepositoryPokemonList();
    }

    public PresenterPokemonAbout(String pokemonName) {
        this.pokemonName = pokemonName;
        repository = new RepositoryPokemonList();
    }

    public PresenterPokemonAbout(String pokemonName, int dbId) {
        this.pokemonName = pokemonName;
        this.dbId = dbId;
        repository = new RepositoryPokemonList();
    }

    private void loadData() {
        if (dbId != 0) {
            Log.d(PokemonApplication.TAG, "getDataFromDatabase(dbId");

            repository.getDataFromDatabase(dbId, view.getContext(), this);
        } else {
            Log.d(PokemonApplication.TAG, "getDataFromDatabase(pokemonName");
            repository.getDataFromDatabase(pokemonName, view.getContext(), this);
        }
    }

    @Override
    public void viewIsReady(IViewPokemonAbout view) {
        this.view = view;
        loadData();
    }

    @Override
    public void onDataGotCallback(Pokemon pokemon) {
        if (pokemon.getId() == 0 && pokemon.getName() == null) { //вернулся пустой Pokemon
            view.setImageButtonIcon(ICON_SAVE);
            Log.d(PokemonApplication.TAG, "getData(pokemonName");
            repository.getData(pokemonName, this);
        } else if (pokemon.getId() != 0){ //вернулся Pokemon из базы
            view.setImageButtonIcon(ICON_DELETE);
            view.setViewData(pokemon, this);
        } else { //вернулся Pokemon из интерета
            view.setImageButtonIcon(ICON_SAVE);
            view.setViewData(pokemon, this);
        }

    }

    @Override
    public void onDataGotCallback(ArrayList<Pokemon> pokemons) {

    }

    @Override
    public void onErrorGotCallback(String error) {
        view.showError(error);
    }

    @Override
    public void OnEndSettingUpViewCallback() {
        isLoading = false;
    }

    @Override
    public void onSaveButtonClick(Pokemon pokemon) {
        repository.addDataToDb(view.getContext(), pokemon);
        loadData();
    }
}
