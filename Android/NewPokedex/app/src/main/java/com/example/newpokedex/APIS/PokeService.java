package com.example.newpokedex.APIS;

import com.example.newpokedex.Datos.DetallePokemon;
import com.example.newpokedex.Datos.HabilidadesPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeService {
    @GET("pokemon")
    //Query es cuando sacas un maximo/limite
    Call<DetallePokemon> llamadaLista (@Query("limit") int limit);
    @GET("pokemon/{nombre}/")
    //Path cuando buscas algo en concreo
    Call<HabilidadesPokemon> llamadaDetalle(@Path("String")String nombre);
}
