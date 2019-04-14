package com.tadiuzzz.pokemons.repository;

import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Forms;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.PokemonCharacteristics;
import com.tadiuzzz.pokemons.model.PokemonResult;
import com.tadiuzzz.pokemons.model.Sprites;
import com.tadiuzzz.pokemons.model.Stats;
import com.tadiuzzz.pokemons.network.PokeapiService;
import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tadiuzzz.pokemons.PokemonApplication.TAG;

public class RepositoryPokemonList implements IRepositoryPokemonList {

    private IOnDataGotListener onDataGotListener;
    private Retrofit retrofit;

    private int offset;

    @Override
    public void getData(int offset, IOnDataGotListener listener) {
        Log.d(PokemonApplication.TAG, "getData");
        this.offset = offset;
        this.onDataGotListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadPokemonListFromNetwork();
    }

    private void loadPokemonListFromNetwork() {
        Log.d(PokemonApplication.TAG, "loadPokemonListFromNetwork");
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonResult> pokemonResultCall = service.getPokemonList(20, offset);

        Log.d(PokemonApplication.TAG, "Calling...");
        pokemonResultCall.enqueue(new Callback<PokemonResult>() {

            @Override
            public void onResponse(Call<PokemonResult> call, Response<PokemonResult> response) {
                Log.d(PokemonApplication.TAG, "onResponse");
                if (response.isSuccessful()) {

                    PokemonResult pokemonResult = response.body();
                    ArrayList<Pokemon> pokemons = pokemonResult.getResults();

                    for (Pokemon pokemon : pokemons) {
                        loadPokemonCharacteristicsFromNetwork(pokemon);
                    }

//                    onDataGotListener.onDataGotCallback(pokemons);

//                    pokemonListAdapter.addPokemonToList(pokemons);

                } else {
                    onDataGotListener.onErrorGotCallback(response.errorBody().toString());
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResult> call, Throwable t) {
                Log.d(PokemonApplication.TAG, "onFailure");
                onDataGotListener.onErrorGotCallback(t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadPokemonCharacteristicsFromNetwork(final Pokemon pokemon) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonCharacteristics> pokemonAbilitiesResult = service.getAbilitiesList(pokemon.getNumber());

        pokemonAbilitiesResult.enqueue(new Callback<PokemonCharacteristics>() {

            @Override
            public void onResponse(Call<PokemonCharacteristics> call, Response<PokemonCharacteristics> response) {
                Log.d(PokemonApplication.TAG, "onResponse");
                if (response.isSuccessful()) {

                    PokemonCharacteristics pokemonCharacteristics = response.body();
                    pokemon.setPokemonCharacteristics(pokemonCharacteristics);
                    onDataGotListener.onDataGotCallback(pokemon);

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

//                    pokemonListAdapter.addPokemonToList(pokemons);

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonCharacteristics> call, Throwable t) {
                Log.d(PokemonApplication.TAG, "onFailure");
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
