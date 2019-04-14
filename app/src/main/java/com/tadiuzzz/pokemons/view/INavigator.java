package com.tadiuzzz.pokemons.view;

import android.os.Bundle;

import com.tadiuzzz.pokemons.model.Pokemon;

public interface INavigator {
    void navigateTo(String nameOfFragment, Bundle bundle);
}
