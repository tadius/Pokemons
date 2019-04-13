package com.tadiuzzz.pokemons.view;

import android.view.View;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.presenter.IOnEndSettingUpViewListener;

import java.util.ArrayList;

public interface IViewPokemonList {

    void setViewData(ArrayList<Pokemon> pokemons, IOnEndSettingUpViewListener listener);

    void setRefreshing(boolean isRefreshing);

    void showError(String error);
}
