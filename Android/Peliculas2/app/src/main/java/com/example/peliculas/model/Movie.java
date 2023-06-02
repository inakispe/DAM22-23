package com.example.peliculas.model;

import java.util.List;

//Aqui cogemos tal cual nos da el API
public class Movie {

    private String titulo;
    private String fecha;
    private String duracion;
    private String genero;
    private List<Actor> actores;

    public List<Actor> getActores() {
        return actores;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    private String portada;

}
