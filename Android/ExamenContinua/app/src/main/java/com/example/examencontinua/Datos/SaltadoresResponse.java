package com.example.examencontinua.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaltadoresResponse {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("localidad")
    @Expose
    private String localidad;

    @SerializedName("fecha")
    @Expose
    private int fecha;

    @SerializedName("centimetros")
    private int centimetros;

    public int getCentimetros() {return centimetros;}
    public String getUrl() {
        return url;
    }
    public String getNombre() {
        return nombre;
    }
    public String getLocalidad() {
        return localidad;
    }
    public int getFecha() {
        return fecha;
    }
}
