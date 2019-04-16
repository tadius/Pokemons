package com.tadiuzzz.pokemons.network;

import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.PokemonCharacteristics;
import com.tadiuzzz.pokemons.model.PokemonResult;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {

//        @GET("pokemon")
//    Call<PokemonResult> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);




//    @GET("pokemon/{number}")
//    Call<PokemonCharacteristics> getAbilitiesList(@Path("number") int number);

//        @GET("pokemon/{name}")
//    Call<PokemonCharacteristics> getAbilitiesList(@Path("name") String name);


    @GET("pokemon")
    Single<PokemonResult> getRXPokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{name}")
    Single<PokemonCharacteristics> getRXAbilitiesList(@Path("name") String name);
}
