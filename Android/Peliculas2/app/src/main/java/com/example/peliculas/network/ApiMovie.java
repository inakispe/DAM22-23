package com.example.peliculas.network;

import com.example.peliculas.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//Esta tiene como papel principal describir todas las operaciones que solicitaremos a la API
//Agregar, listar, editar
public interface ApiMovie {
    //En este caso solo haremos la opcion de lista las peliculas
    //A LA RUTA MOVIE LIST.PHP
    @GET("movies/list.php")
    Call<List<Movie>> getMovies();
    @GET
    Call<Movie> getDetalle(@Url String url);
}
