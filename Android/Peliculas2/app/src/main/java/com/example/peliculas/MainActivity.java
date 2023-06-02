package com.example.peliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.peliculas.model.Movie;
import com.example.peliculas.movieAdapter.MovieAdapter;
import com.example.peliculas.network.APIclient;
import com.example.peliculas.network.ApiMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //Listado peliculas del JSON
    LiveData<List<Movie>> movies;
    //Otra para el RecyclerView
    RecyclerView recyclerView;
    //Otra para usar el adaptador
    MovieAdapter movieAdapter= new MovieAdapter();
    MovieViewModel movieViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_movies);
        //Aqui le damos la forma que queremos al RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        movieViewModel= new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieLiveData().observe(this,responses->{
            movieAdapter.setResults(responses);
        });
        movieViewModel.getMovies();
            }

}