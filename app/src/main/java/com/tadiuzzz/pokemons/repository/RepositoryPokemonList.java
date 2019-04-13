package com.tadiuzzz.pokemons.repository;

import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.PokemonResult;
import com.tadiuzzz.pokemons.network.PokeapiService;
import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;
import com.tadiuzzz.pokemons.presenter.IPresenterPokemonList;

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

    @Override
    public void getData(IOnDataGotListener listener) {
        Log.d(PokemonApplication.TAG, "getData");
        this.onDataGotListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadDataFromNetwork();
    }

    private void loadDataFromNetwork() {
        Log.d(PokemonApplication.TAG, "loadDataFromNetwork");
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonResult> pokemonResultCall = service.getPokemonList();

        Log.d(PokemonApplication.TAG, "Calling...");
        pokemonResultCall.enqueue(new Callback<PokemonResult>(){

            @Override
            public void onResponse(Call<PokemonResult> call, Response<PokemonResult> response) {
                Log.d(PokemonApplication.TAG, "onResponse");
                if(response.isSuccessful()) {

                    PokemonResult pokemonResult = response.body();
                    ArrayList<Pokemon> pokemons = pokemonResult.getResults();

                    onDataGotListener.onDataGotCallback(pokemons);

//                    pokemonListAdapter.addListOfPokemons(pokemons);

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
}
