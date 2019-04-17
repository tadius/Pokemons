package com.tadiuzzz.pokemons.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PokemonListFragment pokemonListFragment = new PokemonListFragment();
        position = position + 1;

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        pokemonListFragment.setArguments(bundle);
        return pokemonListFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position = position + 1;

        switch (position) {
            case 1:
                return "Все покемоны";
            case 2:
                return "Покедекс";
            default:
                return "";
        }
    }
}
