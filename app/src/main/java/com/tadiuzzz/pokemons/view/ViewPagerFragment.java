package com.tadiuzzz.pokemons.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tadiuzzz.pokemons.R;

public class ViewPagerFragment extends Fragment {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
//    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);


        viewPager = view.findViewById(R.id.viewPager);

//        toolbar = view.findViewById(R.id.toolBar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
