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
//        Log.d(PokemonApplication.TAG, "PresenterPokemonList");
        offset = 0;
        repository = new RepositoryPokemonList();
    }

    private void loadDataFromInternet() {
//        Log.d(PokemonApplication.TAG, "loadData");
        isLoading = true;
        view.setRefreshing(isLoading);
        repository.getData(offset, this);
    }

    private void loadDataFromDb() {
        repository.getDataFromDatabase(view.getContext(), this);
    }

    @Override
    public void viewIsReady(IViewPokemonList view, int position) {
//        Log.d(PokemonApplication.TAG, "viewIsReady");
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
//        Log.d(PokemonApplication.TAG, "onSwipeRefresh");
    }

    @Override
    public void onScrolled(GridLayoutManager layoutManager) {
//        Log.d(PokemonApplication.TAG, "onScrolled");
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

    //    private Pok convertPokemonToPok(Pokemon pokemon) {
//        String name = pokemon.getPokemonCharacteristics().getName();
//        int pokemonId = pokemon.getPokemonCharacteristics().getPokemonId();
//        ArrayList<Ability> allAbilities = new ArrayList<Ability>();
//
//        ArrayList<Abilities> abilities = pokemon.getPokemonCharacteristics().getAbilities();
//        for (Abilities ability : abilities) {
//            allAbilities.add(ability.getAbility());
//        }
//
//        ArrayList<Stat> allStats = new ArrayList<Stat>();
//        ArrayList<Stats> stats = pokemon.getPokemonCharacteristics().getStats();
//        for (Stats stat : stats) {
//            allStats.add(stat.getStat());
//            allStats += stat.getBase_stat() + "\n";
//        }
//
//        ArrayList<Forms> forms;
//        Sprites sprites;
//
//        Pok pok = new Pok(pokemon.getPokemonCharacteristics().getPokemonId(),
//
//
//        );
//
//
//        return pok;
//    }
    @Override
    public void onDataGotCallback(ArrayList<Pokemon> pokemons) {
//        Log.d(PokemonApplication.TAG, "onDataGotCallback");
        view.setViewData(pokemons, this);
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
