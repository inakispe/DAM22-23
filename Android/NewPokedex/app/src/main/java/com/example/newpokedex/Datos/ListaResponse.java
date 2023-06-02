package com.example.newpokedex.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaResponse {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("results")
    @Expose
    private List<DetallePokemon> results;

    public List<DetallePokemon> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}
