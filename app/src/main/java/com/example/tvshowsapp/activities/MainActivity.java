package com.example.tvshowsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tvshowsapp.R;
import com.example.tvshowsapp.adapters.TVShowAdapter;
import com.example.tvshowsapp.databinding.ActivityMainBinding;
import com.example.tvshowsapp.databinding.ActivityMainBindingImpl;
import com.example.tvshowsapp.models.TVShow;
import com.example.tvshowsapp.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter tvShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }


    private void doInitialization(){
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowAdapter = new TVShowAdapter(tvShows);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowAdapter);
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(0).observe(this, getMostPopularTVShowsResponse ->{
             activityMainBinding.setIsLoading(false);
             if (getMostPopularTVShowsResponse != null){
                 if (getMostPopularTVShowsResponse.getTvShows() != null){
                     tvShows.addAll(getMostPopularTVShowsResponse.getTvShows());
                     tvShowAdapter.notifyDataSetChanged();
                 }
             }


        });
    }

}