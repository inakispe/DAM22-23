package com.example.newpokedex.APIS;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.newpokedex.Datos.DetallePokemon;
import com.example.newpokedex.Datos.HabilidadesPokemon;
import com.example.newpokedex.Datos.ListaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeRepository {
    //En el Repositorio rellenamos
    private static final String POKEAPI_URL="https://pokeapi.co/api/v2/";
    PokeService pokeService;
    MutableLiveData<HabilidadesPokemon> habilidadesLiveData;
    //MutableLiveData<DetallePokemon> detalleLiveData;
    MutableLiveData<ListaResponse> resultadosLiveData;
    public PokeRepository (){
        habilidadesLiveData = new MutableLiveData<>();
        //detalleLiveData= new MutableLiveData<>();
        resultadosLiveData= new MutableLiveData<>();

        pokeService = new Retrofit.Builder()
                .baseUrl(POKEAPI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeService.class);
    }
    public void llamadaDetalle(String url){
        pokeService.llamadaDetalle(url).enqueue(new Callback<HabilidadesPokemon>() {
            @Override
            public void onResponse(Call<HabilidadesPokemon> call, Response<HabilidadesPokemon> response) {
                if (response.body()!=null){
                    habilidadesLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<HabilidadesPokemon> call, Throwable t) {
                habilidadesLiveData.postValue(null);
            }
        });
    }
    public void llamadaLista (int limit) {
        pokeService.llamadaLista(limit).enqueue(new Callback<DetallePokemon>() {
            @Override
            public void onResponse(Call<DetallePokemon> call, Response<DetallePokemon> response) {
                if (response.body() != null) {
                    Log.d(TAG, "BUSCANDO DATA");
                    resultadosLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DetallePokemon> call, Throwable t) {
                resultadosLiveData.postValue(null);
            }
        });
    }
    public MutableLiveData<HabilidadesPokemon> getHabilidadesLiveData (){
        return habilidadesLiveData;
    }
    /**public MutableLiveData<DetallePokemon> getDetallesiveData (){
        return  detalleLiveData;
    }*/
    public MutableLiveData<ListaResponse> getResultadosLiveData(){
        return resultadosLiveData;
    }
}
