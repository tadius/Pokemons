package com.tadiuzzz.pokemons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tadiuzzz.pokemons.view.INavigator;
import com.tadiuzzz.pokemons.view.PokemonAboutFragment;
import com.tadiuzzz.pokemons.view.PokemonListFragment;
import com.tadiuzzz.pokemons.view.ViewPagerAdapter;
import com.tadiuzzz.pokemons.view.ViewPagerFragment;

import static com.tadiuzzz.pokemons.PokemonApplication.TAG;

public class MainActivity extends AppCompatActivity implements INavigator {

    private FragmentManager fragmentManager;
    public static final String POKEMON_LIST_FRAGMENT = "PokemonListFragment";
    public static final String POKEMON_ABOUT_FRAGMENT = "PokemonAboutFragment";
    public static final String VIEW_PAGER_FRAGMENT = "ViewPagerFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            Log.d(TAG, "fragment = null");

            navigateTo(VIEW_PAGER_FRAGMENT, null);
        }
    }

    @Override
    public void navigateTo(String nameOfFragment, Bundle bundle) {

        Fragment fragment;
        fragmentManager = getSupportFragmentManager();

        switch (nameOfFragment) {
            case VIEW_PAGER_FRAGMENT:
                fragment = new ViewPagerFragment();
                fragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();
                break;
            case POKEMON_LIST_FRAGMENT:
                fragment = new PokemonListFragment();
                fragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();
                break;
            case POKEMON_ABOUT_FRAGMENT:
                fragment = new PokemonAboutFragment();
                fragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(POKEMON_ABOUT_FRAGMENT)
                        .commit();
                break;
        }


    }
}
