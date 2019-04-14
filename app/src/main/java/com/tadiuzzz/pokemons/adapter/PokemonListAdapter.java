package com.tadiuzzz.pokemons.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.R;
import com.tadiuzzz.pokemons.model.Pokemon;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private boolean isLoadingAdded;
    private Context context;

    public PokemonListAdapter(Context context) {
        this.context = context;
        pokemons = new ArrayList<>();
        Log.d(PokemonApplication.TAG, "PokemonListAdapter");
    }

    abstract class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(int position);
    }

    public class PokemonsViewHolder extends MyViewHolder {
        private ImageView ivPokemonPicture;
        private TextView tvPokemonName;

        public PokemonsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPokemonPicture = (ImageView) itemView.findViewById(R.id.ivPokemonPicture);
            tvPokemonName = (TextView) itemView.findViewById(R.id.tvPokemonName);
        }

        @Override
        void bind(int position) {
            Pokemon pokemon = pokemons.get(position);
            tvPokemonName.setText(pokemon.getName());

            if (pokemon.getPokemonCharacteristics() != null) {
                Glide.with(context)
                        .load(pokemon.getPokemonCharacteristics().getSprites().getFront_default())
//                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivPokemonPicture);
            }

            Log.d(PokemonApplication.TAG, "bind PokemonsViewHolder");
        }
    }

    public class LoadingViewHolder extends MyViewHolder {
        public ProgressBar pbLoadingPokemons;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            pbLoadingPokemons = (ProgressBar) itemView.findViewById(R.id.pbLoadingPokemons);

        }

        @Override
        void bind(int position) {

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(viewType, viewGroup, false);
        switch (viewType) {
            case R.layout.item_pokemon:
                return new PokemonsViewHolder(view);
            case R.layout.item_loading:
                return new LoadingViewHolder(view);
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        viewHolder.bind(position);
        Log.d(PokemonApplication.TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        Log.d(PokemonApplication.TAG, "getItemCount");
        if (isLoadingAdded)
            return pokemons.size() + 1;
        else return pokemons.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == pokemons.size() && isLoadingAdded) {
            return R.layout.item_loading;
        } else {
            return R.layout.item_pokemon;
        }
    }

    public void addLoading() {
        isLoadingAdded = true;
        notifyDataSetChanged();
    }

    public void removeLoading() {
        if (isLoadingAdded) {
            isLoadingAdded = false;
            notifyDataSetChanged();
        }
    }

    public void addPokemonToList(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        notifyDataSetChanged();
        Log.d(PokemonApplication.TAG, "addPokemonToList");

    }

}
