package com.tadiuzzz.pokemons.network;

import com.tadiuzzz.pokemons.model.PokemonResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonResult> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);
}
