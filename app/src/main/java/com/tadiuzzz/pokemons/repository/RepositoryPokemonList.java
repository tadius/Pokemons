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
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
    public void addDataToDb(Context context, Pokemon pokemon){
        PokemonsDBManager dbManager = new PokemonsDBManager(context);
//        TODO сделать запрос асинхронным (AsyncTask, new Thread или RxJava?)
        if(dbManager.getPokemonByPokemonId(pokemon.getPokemonCharacteristics().getId()) == null) { //проверка, есть ли уже в базе
            dbManager.addPokemon(pokemon);
            dbManager.addAbilities(pokemon);
            dbManager.addStats(pokemon);
        } else {
            dbManager.deletePokemonById(pokemon.getPokemonCharacteristics().getId());
            Log.d(TAG, "DELETED: ");
        }
    }

    @Override
    public void getData(int offset, IOnDataGotListener listener) {
//        Log.d(PokemonApplication.TAG, "getData");
        this.offset = offset;
        this.onDataGotListener = listener;

//        loadPokemonListFromNetwork();
        loadRXPokemonListFromNetwork();
    }

    @Override
    public void getData(String name, IOnDataGotListener listener) {
//        Log.d(PokemonApplication.TAG, "getData");
        this.onDataGotListener = listener;

        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);

        loadRXPokemonCharacteristicsFromNetwork(pokemon);
    }

    @Override
    public void getDataFromDatabase(Context context, IOnDataGotListener listener) {
        this.onDataGotListener = listener;
        PokemonsDBManager dbManager = new PokemonsDBManager(context);

        io.reactivex.Observable.just(dbManager.getAllPokemons())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Pokemon>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Pokemon> pokemons) {
                        onDataGotListener.onDataGotCallback(pokemons);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        ArrayList<Pokemon> pokemons = dbManager.getAllPokemons();
//        Log.d(PokemonApplication.TAG,"pokemons size - " + pokemons.size());
//        onDataGotListener.onDataGotCallback(pokemons);
    }

    @Override
    public void getDataFromDatabase(int dbId, Context context, IOnDataGotListener listener) {
        this.onDataGotListener = listener;
        PokemonsDBManager dbManager = new PokemonsDBManager(context);

        io.reactivex.Observable.just(dbManager.getPokemonById(dbId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pokemon>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        Log.d(PokemonApplication.TAG, "Pokemon is already in DataBase");
                        onDataGotListener.onDataGotCallback(pokemon);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getDataFromDatabase(String pokemonName, Context context, IOnDataGotListener listener) {
        this.onDataGotListener = listener;
        PokemonsDBManager dbManager = new PokemonsDBManager(context);

        io.reactivex.Observable.just(dbManager.getPokemonByName(pokemonName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pokemon>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        Log.d(PokemonApplication.TAG, "Pokemon is already in DataBase");
                        onDataGotListener.onDataGotCallback(pokemon);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }

                    @Override
                    public void onComplete() {

                    }
                });

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
