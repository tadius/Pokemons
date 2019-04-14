package com.tadiuzzz.pokemons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Forms;
import com.tadiuzzz.pokemons.model.PokemonCharacteristics;
import com.tadiuzzz.pokemons.model.Sprites;
import com.tadiuzzz.pokemons.model.Stats;
import com.tadiuzzz.pokemons.network.PokeapiService;
import com.tadiuzzz.pokemons.view.PokemonListFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tadiuzzz.pokemons.PokemonApplication.TAG;

public class MainActivity extends AppCompatActivity {

//    private Retrofit retrofit;

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


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new PokemonListFragment();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();

    }
}
