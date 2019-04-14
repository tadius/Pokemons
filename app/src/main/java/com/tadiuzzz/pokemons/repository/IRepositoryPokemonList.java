package com.tadiuzzz.pokemons.repository;

import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;

public interface IRepositoryPokemonList {

    void getData(int offset, IOnDataGotListener listener);
    void getData(String name, IOnDataGotListener listener);

}

