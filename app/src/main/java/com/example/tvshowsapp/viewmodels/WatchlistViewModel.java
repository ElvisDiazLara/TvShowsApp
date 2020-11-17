package com.example.tvshowsapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tvshowsapp.database.TVShowsDatabase;
import com.example.tvshowsapp.models.TVShow;

import java.util.List;

import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {


    private TVShowsDatabase tvShowsDatabase;

    public WatchlistViewModel(@NonNull Application application){
        super(application);
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);
    }

    public Flowable<List<TVShow>> loadWatchlist(){
        return tvShowsDatabase.tvShowDao().getWatchlist();
    }

}
