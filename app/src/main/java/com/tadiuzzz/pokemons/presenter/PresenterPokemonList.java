package com.tadiuzzz.pokemons.presenter;

import android.support.v7.widget.GridLayoutManager;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.repository.IRepositoryPokemonList;
import com.tadiuzzz.pokemons.repository.RepositoryPokemonList;
import com.tadiuzzz.pokemons.view.IViewPokemonList;

import java.util.ArrayList;

public class PresenterPokemonList implements IPresenterPokemonList, IOnEndSettingUpViewListener, IOnDataGotListener {

    private IViewPokemonList view;
    private IRepositoryPokemonList repository;
    private boolean isLoading = false;
    private int offset;
    private int position;

    public PresenterPokemonList() {
        offset = 0;
        repository = new RepositoryPokemonList();
    }

    private void loadDataFromInternet() {
        isLoading = true;
        view.setRefreshing(isLoading);
        repository.getData(offset, this);
    }

    private void loadDataFromDb() {
        repository.getDataFromDatabase(view.getContext(), this);
    }

    @Override
    public void viewIsReady(IViewPokemonList view, int position) {
        this.view = view;
        this.position = position;

        switch (this.position) {
            case 1:
                loadDataFromInternet();
                break;
            case 2:
                loadDataFromDb();

        }
    }

    @Override
    public void onSwipeRefresh() {
    }

    @Override
    public void onScrolled(GridLayoutManager layoutManager) {
        if (position == 1) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= offset) {
                    offset += 20;
                    loadDataFromInternet();
                }
            }
        }
    }

    @Override
    public void onDataGotCallback(ArrayList<Pokemon> pokemons) {
        view.setViewData(pokemons, this);
    }

    @Override
    public void onDataGotCallback(Pokemon pokemon) {
        view.setViewData(pokemon, this);
    }

    @Override
    public void onErrorGotCallback(String error) {
        view.showError(error);
    }

    @Override
    public void OnEndSettingUpViewCallback() {
        isLoading = false;
    }
}
