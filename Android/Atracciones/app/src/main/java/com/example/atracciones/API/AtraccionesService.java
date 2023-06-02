package com.example.atracciones.API;

import com.example.atracciones.Model.AtraccionesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface AtraccionesService {
    @GET("pmdm/api/atracciones/")
    Call<List<AtraccionesResponse>> getAtracciones();

    @GET("pmdm/api/atracciones/{id}/")
    Call<AtraccionesResponse> getDetalle(@Path("id") String id);

    @GET
    Call<AtraccionesResponse> getDetalleV2(@Url String url);
}