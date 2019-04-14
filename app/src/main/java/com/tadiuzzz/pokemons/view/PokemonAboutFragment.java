package com.tadiuzzz.pokemons.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.R;
import com.tadiuzzz.pokemons.adapter.PokemonListAdapter;
import com.tadiuzzz.pokemons.model.Abilities;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.model.Stats;
import com.tadiuzzz.pokemons.presenter.IOnEndSettingUpViewListener;
import com.tadiuzzz.pokemons.presenter.IPresenterPokemonList;
import com.tadiuzzz.pokemons.presenter.PresenterPokemonAbout;
import com.tadiuzzz.pokemons.presenter.PresenterPokemonList;

import java.util.ArrayList;

public class PokemonAboutFragment extends Fragment implements IViewPokemonList {

    private IPresenterPokemonList presenterPokemonAbout;
    private ImageView ivPokemonPictureAbout;
    private ImageView ivPokemonPictureBackAbout;
    private TextView tvPokemonNameAbout;
    private ImageButton ibSaveToDB;
    private TextView tvStats;
    private TextView tvAbilities;

    private String pokemonName;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_about, container, false);
//        Log.d(PokemonApplication.TAG, "onCreateView");
        context = this.getActivity();

        Bundle bundle = getArguments();
        if(bundle != null) {
            pokemonName = bundle.getString("pokemonName");
        } else {
            pokemonName = "";
        }

        initView(view);
        return view;
    }

    public void initView(View view) {
//        Log.d(PokemonApplication.TAG, "initView");
        presenterPokemonAbout = new PresenterPokemonAbout(pokemonName);

        ivPokemonPictureAbout = (ImageView) view.findViewById(R.id.ivPokemonPictureAbout);
        ivPokemonPictureBackAbout = (ImageView) view.findViewById(R.id.ivPokemonPictureBackAbout);
        tvPokemonNameAbout = (TextView) view.findViewById(R.id.tvPokemonNameAbout);
        ibSaveToDB  = (ImageButton) view.findViewById(R.id.ibSaveToDB);
        tvStats = (TextView) view.findViewById(R.id.tvStats);
        tvAbilities = (TextView) view.findViewById(R.id.tvAbilities);

        presenterPokemonAbout.viewIsReady(this);
    }

    @Override
    public void setViewData(Pokemon pokemon, IOnEndSettingUpViewListener listener) {
//        Log.d(PokemonApplication.TAG, "setViewData");

        tvPokemonNameAbout.setText(pokemon.getName());
        String allStats = "";
        ArrayList<Stats> stats = pokemon.getPokemonCharacteristics().getStats();
        for (Stats stat : stats) {
            allStats += stat.getStat().getName() + ": ";
            allStats += stat.getBase_stat() + "\n";
        }
        tvStats.setText(allStats);

        String allAbilities = "";
        ArrayList<Abilities> abilities = pokemon.getPokemonCharacteristics().getAbilities();
        for (Abilities ability : abilities) {
            allAbilities += ability.getAbility().getName() + "\n";
        }
        tvAbilities.setText(allAbilities);

        if (pokemon.getPokemonCharacteristics() != null) {
            Glide.with(context)
                    .load(pokemon.getPokemonCharacteristics().getSprites().getFront_default())
//                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemonPictureAbout);
            Glide.with(context)
                    .load(pokemon.getPokemonCharacteristics().getSprites().getBack_default())
//                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemonPictureBackAbout);
        }


        listener.OnEndSettingUpViewCallback();
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
//        Log.d(PokemonApplication.TAG, "setRefreshing");
    }

    @Override
    public void showError(String error) {
//        Log.d(PokemonApplication.TAG, "showError");
    }



}
