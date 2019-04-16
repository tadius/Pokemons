package com.tadiuzzz.pokemons.repository;

import android.content.Context;
import android.util.Log;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.db.PokemonsDBManager;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.PokemonCharacteristics;
import com.tadiuzzz.pokemons.model.PokemonResult;
import com.tadiuzzz.pokemons.network.ApiClient;
import com.tadiuzzz.pokemons.network.PokeapiService;
import com.tadiuzzz.pokemons.presenter.IOnDataGotListener;
import com.tadiuzzz.pokemons.presenter.PresenterPokemonList;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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
//        Log.d(PokemonApplication.TAG, "getData");
        this.offset = offset;
        this.onDataGotListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        loadPokemonListFromNetwork();
        loadRXPokemonListFromNetwork();
    }

    @Override
    public void getData(String name, IOnDataGotListener listener) {
//        Log.d(PokemonApplication.TAG, "getData");
        this.onDataGotListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);

        loadRXPokemonCharacteristicsFromNetwork(pokemon);
    }

    @Override
    public void getDataFromDatabase(Context context, IOnDataGotListener listener) {
        this.onDataGotListener = listener;
        PokemonsDBManager dbManager = new PokemonsDBManager(context);
        ArrayList<Pokemon> pokemons = dbManager.getAllPokemons();
        Log.d(PokemonApplication.TAG,"pokemons size - " + pokemons.size());
        onDataGotListener.onDataGotCallback(pokemons);

    }

    @Override
    public void getDataFromDatabase(int dbId, Context context, IOnDataGotListener listener) {
        this.onDataGotListener = listener;
        PokemonsDBManager dbManager = new PokemonsDBManager(context);
        Pokemon pokemon = dbManager.getPokemonById(dbId);
        onDataGotListener.onDataGotCallback(pokemon);
    }

    private void loadRXPokemonListFromNetwork(){
        PokeapiService apiService = ApiClient.getClient()
                .create(PokeapiService.class);

        apiService.getRXPokemonList(20, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<PokemonResult>() {
                    @Override
                    public void onSuccess(PokemonResult pokemonResult) {

                        ArrayList<Pokemon> pokemons = pokemonResult.getResults();

                        for (Pokemon pokemon : pokemons) {
                            Log.d(PokemonApplication.TAG, "RXName " + pokemon.getName());
                            loadRXPokemonCharacteristicsFromNetwork(pokemon);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }
                });

    }

    private void loadRXPokemonCharacteristicsFromNetwork(final Pokemon pokemon) {
        PokeapiService apiService = ApiClient.getClient()
                .create(PokeapiService.class);

        apiService.getRXAbilitiesList(pokemon.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<PokemonCharacteristics>() {
                    @Override
                    public void onSuccess(PokemonCharacteristics pokemonCharacteristics) {

                        pokemon.setPokemonCharacteristics(pokemonCharacteristics);
                            Log.d(PokemonApplication.TAG, "PokemonCharacteristics Loaded for " +
                                    pokemon.getPokemonCharacteristics().getName());

                            onDataGotListener.onDataGotCallback(pokemon);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }
                });
    }

}
