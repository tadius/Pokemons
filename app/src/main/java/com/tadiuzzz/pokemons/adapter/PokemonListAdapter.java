package com.tadiuzzz.pokemons.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.R;
import com.tadiuzzz.pokemons.model.Pokemon;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemons;

    public PokemonListAdapter() {
        pokemons = new ArrayList<>();
        Log.d(PokemonApplication.TAG, "PokemonListAdapter");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        Log.d(PokemonApplication.TAG, "onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Pokemon pokemon = pokemons.get(position);
        viewHolder.tvPokemonName.setText(pokemon.getName());
        Log.d(PokemonApplication.TAG, "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        Log.d(PokemonApplication.TAG, "getItemCount " + pokemons.size());
        return pokemons.size();
    }

    public void addListOfPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
        notifyDataSetChanged();
        Log.d(PokemonApplication.TAG, "addListOfPokemons");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPokemonPicture;
        private TextView tvPokemonName;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPokemonPicture = (ImageView) itemView.findViewById(R.id.ivPokemonPicture);
            tvPokemonName = (TextView) itemView.findViewById(R.id.tvPokemonName);
            Log.d(PokemonApplication.TAG, "ViewHolder");

        }
    }
}
