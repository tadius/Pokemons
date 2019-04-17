package com.tadiuzzz.pokemons.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.tadiuzzz.pokemons.model.ComparePokemon;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.view.IOnPokemonClickListener;

import java.util.ArrayList;
import java.util.Collections;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private boolean isLoadingAdded;
    private Context context;
    private IOnPokemonClickListener onPokemonClickListener;

    public PokemonListAdapter(Context context, IOnPokemonClickListener onPokemonClickListener) {
        this.context = context;
        this.onPokemonClickListener = onPokemonClickListener;
        pokemons = new ArrayList<>();
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

        public PokemonsViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivPokemonPicture = (ImageView) itemView.findViewById(R.id.ivPokemonPicture);
            tvPokemonName = (TextView) itemView.findViewById(R.id.tvPokemonName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pokemon pokemon = pokemons.get(getLayoutPosition());
                    onPokemonClickListener.onPokemonClick(pokemon);
                }
            });
        }

        @Override
        void bind(int position) {
            Pokemon pokemon = pokemons.get(position);
            tvPokemonName.setText(pokemon.getName());

            if (pokemon.getPokemonCharacteristics() != null) {
                Glide.with(context)
                        .load(pokemon.getPokemonCharacteristics().getSprites().getFront_default())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivPokemonPicture);
            }

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
    }

    @Override
    public int getItemCount() {
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
        ComparePokemon comparePokemon = new ComparePokemon();
        Collections.sort(pokemons, comparePokemon);

        notifyDataSetChanged();
    }

    public void addPokemons(ArrayList<Pokemon> pokemons) {
        clearPokemonsList();
        this.pokemons.addAll(pokemons);

        notifyDataSetChanged();
    }

    public void clearPokemonsList() {
        this.pokemons.clear();
    }

}
