package com.tadiuzzz.pokemons.view;

import android.content.Context;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.presenter.IOnEndSettingUpViewListener;

import java.util.ArrayList;

public interface IViewPokemonAbout {

//    void setViewData(ArrayList<Pokemon> pokemons, IOnEndSettingUpViewListener listener);
    Context getContext();

    void setViewData(Pokemon pokemon, IOnEndSettingUpViewListener listener);

    void setImageButtonIcon(int type);

    void showError(String error);
}
