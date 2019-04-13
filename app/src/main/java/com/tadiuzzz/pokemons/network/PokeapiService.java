package com.tadiuzzz.pokemons.network;

import com.tadiuzzz.pokemons.model.PokemonResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonResult> getPokemonList();
}
