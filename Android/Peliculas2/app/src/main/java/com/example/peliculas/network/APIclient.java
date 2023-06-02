package com.example.peliculas.network;
//LLama a la API


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static okhttp3.internal.Internal.instance;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.peliculas.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {
    private ApiMovie apiMovie;
    private APIclient instance;
    public static final String url="https://192.168.56.1:443/";

    private MutableLiveData<List<Movie>>movieMutableLiveData;

    public void MovieRepository(){
        movieMutableLiveData= new MutableLiveData();
        apiMovie= new Retrofit.Builder()
                //Se mete solo el principio
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiMovie.class);

    }

    public APIclient getInstance() {
        if(instance==null){
            instance= new APIclient();
        }
        return instance;
    }

    public void getMovies(){
        apiMovie.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.body()!=null){
                    Log.d(TAG,"Datos");
                    movieMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(TAG,"No se ha encontrado dsato");
                movieMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<List<Movie>> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }
}

