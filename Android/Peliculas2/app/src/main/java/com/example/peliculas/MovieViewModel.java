package com.example.peliculas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.peliculas.model.Movie;
import com.example.peliculas.network.APIclient;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private APIclient apIclient;
    private LiveData<List<Movie>> movieLiveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        apIclient= new APIclient();
        movieLiveData=apIclient.getMovieMutableLiveData();
    }
    public void getMovies (){
        apIclient.getMovies();
    }
    public LiveData<List<Movie>> getMovieLiveData() {
        return movieLiveData;
    }
}