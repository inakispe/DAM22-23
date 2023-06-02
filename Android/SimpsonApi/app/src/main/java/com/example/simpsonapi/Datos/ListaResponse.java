package com.example.simpsonapi.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaResponse {
    @SerializedName("quote")
    @Expose
    String quote;
    @SerializedName("character")
    @Expose
    String character;
    @SerializedName("image")
    @Expose
    String image;
    @SerializedName("characterDirection")
    @Expose
    String characterDirection;
}
