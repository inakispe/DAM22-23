package com.example.examencontinua.API.SaltadoresRepository;

import androidx.lifecycle.MutableLiveData;

import com.example.examencontinua.Datos.SaltadoresResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaltadoresRepository {
    private static final String API_URL = "http://54.38.191.158:8888/";
    private static SaltadoresRepository instance;
    private SaltadoresService saltadoresService;
    private MutableLiveData<List<SaltadoresResponse>> saltadoresLiveData;

    public SaltadoresRepository () {
        saltadoresLiveData= new MutableLiveData<>();

        saltadoresService = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SaltadoresService.class);
    }
    public void getSaltadores(){
        saltadoresService.getSaltadores().enqueue(new Callback<List<SaltadoresResponse>>() {
            @Override
            public void onResponse(Call<List<SaltadoresResponse>> call, Response<List<SaltadoresResponse>> response) {
                if (response.body()!=null){
                    saltadoresLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SaltadoresResponse>> call, Throwable t) {
                    saltadoresLiveData.postValue(null);
            }
        });
    }
    public static SaltadoresRepository getInstance(){
        if(instance==null){
            instance= new SaltadoresRepository();
        }
        return instance;
    }

    public MutableLiveData<List<SaltadoresResponse>> getSaltadoresLiveData() {
        return saltadoresLiveData;
    }
}
