package com.tadiuzzz.pokemons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tadiuzzz.pokemons.view.PokemonListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new PokemonListFragment();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();

    }
}
