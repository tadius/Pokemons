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

import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.R;
import com.tadiuzzz.pokemons.adapter.PokemonListAdapter;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.presenter.IOnEndSettingUpViewListener;
import com.tadiuzzz.pokemons.presenter.IPresenterPokemonList;
import com.tadiuzzz.pokemons.presenter.PresenterPokemonList;

public class PokemonAboutFragment extends Fragment implements IViewPokemonList {

    private IPresenterPokemonList presenterPokemonList;
    private RecyclerView rvPokemonList;
    private PokemonListAdapter pokemonListAdapter;
    private GridLayoutManager layoutManager;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        Log.d(PokemonApplication.TAG, "onCreateView");
        context = this.getActivity();
        initView(view);
        return view;
    }

    public void initView(View view) {
        Log.d(PokemonApplication.TAG, "initView");
        presenterPokemonList = new PresenterPokemonList();

        rvPokemonList = (RecyclerView) view.findViewById(R.id.rvPokemonList);
        pokemonListAdapter = new PokemonListAdapter(context);
        rvPokemonList.setAdapter(pokemonListAdapter);
        rvPokemonList.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 3);

//        Растягиваем progress bar на всю ширину экрана:
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(pokemonListAdapter.getItemViewType(position)){
                    case R.layout.item_loading:
                        return 3;

                    case R.layout.item_pokemon:
                        return 1;

                    default:
                        return 1;
                }
            }
        });

        rvPokemonList.setLayoutManager(layoutManager);
        rvPokemonList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenterPokemonList.onScrolled(layoutManager);
            }
        });

        presenterPokemonList.viewIsReady(this);
    }

    @Override
    public void setViewData(Pokemon pokemon, IOnEndSettingUpViewListener listener) {
        pokemonListAdapter.removeLoading();
        Log.d(PokemonApplication.TAG, "setViewData");
        pokemonListAdapter.addPokemonToList(pokemon);
        listener.OnEndSettingUpViewCallback();
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        Log.d(PokemonApplication.TAG, "setRefreshing");
        pokemonListAdapter.addLoading();
    }

    @Override
    public void showError(String error) {
        Log.d(PokemonApplication.TAG, "showError");
        pokemonListAdapter.removeLoading();
    }


}
