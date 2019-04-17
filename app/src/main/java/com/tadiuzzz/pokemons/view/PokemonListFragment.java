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

import com.tadiuzzz.pokemons.MainActivity;
import com.tadiuzzz.pokemons.PokemonApplication;
import com.tadiuzzz.pokemons.R;
import com.tadiuzzz.pokemons.adapter.PokemonListAdapter;
import com.tadiuzzz.pokemons.model.Pokemon;
import com.tadiuzzz.pokemons.presenter.IOnEndSettingUpViewListener;
import com.tadiuzzz.pokemons.presenter.IPresenterPokemonList;
import com.tadiuzzz.pokemons.presenter.PresenterPokemonList;

import java.util.ArrayList;

public class PokemonListFragment extends Fragment implements IViewPokemonList, IOnPokemonClickListener {

    private INavigator navigator;
    private IPresenterPokemonList presenterPokemonList;
    private RecyclerView rvPokemonList;
    private PokemonListAdapter pokemonListAdapter;
    private GridLayoutManager layoutManager;

    private int position; // переменная для хранения позиции ViewPager, по которой определяем, грузить данные из ДБ или из интернета

    private Context context;

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        navigator = (MainActivity)getActivity();
        context = this.getActivity();

        this.position = getArguments().getInt("position");

        initView(view);
        return view;
    }

    public void initView(View view) {
        presenterPokemonList = new PresenterPokemonList();

        rvPokemonList = (RecyclerView) view.findViewById(R.id.rvPokemonList);
        pokemonListAdapter = new PokemonListAdapter(context, this);
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

        presenterPokemonList.viewIsReady(this, this.position);
    }

    @Override
    public void setViewData(Pokemon pokemon, IOnEndSettingUpViewListener listener) {
        pokemonListAdapter.removeLoading();
        pokemonListAdapter.addPokemonToList(pokemon);
        listener.OnEndSettingUpViewCallback();
    }

    @Override
    public void setViewData(ArrayList<Pokemon> pokemons, IOnEndSettingUpViewListener listener) {
        pokemonListAdapter.removeLoading();
        pokemonListAdapter.addPokemons(pokemons);
        listener.OnEndSettingUpViewCallback();
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        pokemonListAdapter.addLoading();
    }

    @Override
    public void showError(String error) {
        pokemonListAdapter.removeLoading();
    }


    @Override
    public void onPokemonClick(Pokemon pokemon) {
        Log.d(PokemonApplication.TAG, "=================CLICK=================");
        Bundle bundle = new Bundle();
        bundle.putString("pokemonName", pokemon.getName());
        if(pokemon.getId() != 0) {
            bundle.putInt("dbId", pokemon.getId());
        }

        navigator.navigateTo(MainActivity.POKEMON_ABOUT_FRAGMENT, bundle);
    }

}
