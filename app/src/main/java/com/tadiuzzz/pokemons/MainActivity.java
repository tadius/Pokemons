package com.tadiuzzz.pokemons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tadiuzzz.pokemons.view.INavigator;
import com.tadiuzzz.pokemons.view.PokemonAboutFragment;
import com.tadiuzzz.pokemons.view.PokemonListFragment;

import static com.tadiuzzz.pokemons.PokemonApplication.TAG;

public class MainActivity extends AppCompatActivity implements INavigator {

//    private Retrofit retrofit;

    private FragmentManager fragmentManager;
    public static final String POKEMON_LIST_FRAGMENT = "PokemonListFragment";
    public static final String POKEMON_ABOUT_FRAGMENT = "PokemonAboutFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        PokeapiService service = retrofit.create(PokeapiService.class);
//        Call<PokemonCharacteristics> pokemonAbilitiesResult = service.getAbilitiesList(456);
//
//        pokemonAbilitiesResult.enqueue(new Callback<PokemonCharacteristics>() {
//
//            @Override
//            public void onResponse(Call<PokemonCharacteristics> call, Response<PokemonCharacteristics> response) {
//                Log.d(PokemonApplication.TAG, "onResponse");
//                if (response.isSuccessful()) {
//
//                    PokemonCharacteristics pokemonCharacteristics = response.body();
//                    ArrayList<Abilities> abilities = pokemonCharacteristics.getAbilities();
//                    ArrayList<Forms> forms = pokemonCharacteristics.getForms();
//                    ArrayList<Stats> stats = pokemonCharacteristics.getStats();
//                    Sprites sprites = pokemonCharacteristics.getSprites();
//
//
//                    for (Abilities ability : abilities) {
//                        Log.d(TAG, "ability.getName(): " + ability.getAbility().getName());
//                        Log.d(TAG, "ability.getUrl(): " + ability.getAbility().getUrl());
//                    }
//
//                    for (Forms form : forms) {
//                        Log.d(TAG, "form.getName(): " + form.getName());
//                        Log.d(TAG, "form.getUrl(): " + form.getUrl());
//
//                    }
//
//                    Log.d(TAG, "sprite.getFront_default(): " + sprites.getFront_default());
//                    Log.d(TAG, "sprite.getBack_default(): " + sprites.getBack_default());
//
//
//                    for (Stats stat : stats) {
//                        Log.d(TAG, "stat.getBaseStat(): " + stat.getBase_stat());
//                        Log.d(TAG, "stat.getName(): " + stat.getStat().getName());
//                        Log.d(TAG, "stat.getUrl(): " + stat.getStat().getUrl());
//                    }
//
////                    pokemonListAdapter.addPokemonToList(pokemons);
//
//                } else {
//                    Log.e(TAG, "onResponse: " + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PokemonCharacteristics> call, Throwable t) {
//                Log.d(PokemonApplication.TAG, "onFailure");
//                Log.e(TAG, "onFailure: " + t.getMessage());
//            }
//        });

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            Log.d(TAG, "fragment = null");

            navigateTo(POKEMON_LIST_FRAGMENT, null);
        }
    }

    @Override
    public void navigateTo(String nameOfFragment, Bundle bundle) {

        Fragment fragment;
        fragmentManager = getSupportFragmentManager();

        switch (nameOfFragment) {
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
