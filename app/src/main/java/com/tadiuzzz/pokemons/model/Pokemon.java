package com.tadiuzzz.pokemons.model;

import android.support.annotation.NonNull;

public class Pokemon {

    //    private int number;
    private String name;
    private int id;
    //    private String url;
    private PokemonCharacteristics pokemonCharacteristics;

    public Pokemon(){

    }

//    public Pokemon(int id, String nameOfPokemon, int pokemonId, String spriteFront, String spriteBack) {
//        this.id = id;
//        this.name = nameOfPokemon;
//        this.pokemonCharacteristics.setName(nameOfPokemon);
//        this.pokemonCharacteristics.setId(pokemonId);
//
//        Sprites sprites = new Sprites();
//        sprites.setFront_default(spriteFront);
//        sprites.setBack_default(spriteBack);
//        this.pokemonCharacteristics.setSprites(sprites);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public int getPokemonId() {
//        return pokemonCharacteristics.getPokemonId();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    //    public int getNumber() {
//        String[] urlParts = url.split("/");
//        return Integer.parseInt(urlParts[urlParts.length - 1]);
//    }
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }

    public PokemonCharacteristics getPokemonCharacteristics() {
        return pokemonCharacteristics;
    }

    public void setPokemonCharacteristics(PokemonCharacteristics pokemonCharacteristics) {
        this.pokemonCharacteristics = pokemonCharacteristics;
    }

}
