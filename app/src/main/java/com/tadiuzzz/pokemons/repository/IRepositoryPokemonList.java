package com.tadiuzzz.pokemons.repository;

import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;

public interface IRepositoryPokemonList {

    void getData(IOnDataGotListener listener);

}

