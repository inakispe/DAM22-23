package com.example.examencontinua.API.SaltadoresRepository;

import com.example.examencontinua.Datos.SaltadoresResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SaltadoresService {
    @GET("pmdm/api/resultado/")
    Call<List<SaltadoresResponse>> getSaltadores();
}
