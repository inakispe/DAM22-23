package com.example.newpokedex.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetallePokemon {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("URL")
    @Expose
    String URL;

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }
}
