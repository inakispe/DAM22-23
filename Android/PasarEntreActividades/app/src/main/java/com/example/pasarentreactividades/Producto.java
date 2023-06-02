package com.example.pasarentreactividades;

import java.util.ArrayList;

public class Producto {
    private int precio;
    private String nombre;
    private int cantidadSeleccionada;

    public Producto(String nombre, int precio) {
        this.precio = precio;
        this.nombre = nombre;
        this.cantidadSeleccionada=0;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static ArrayList<Producto> generador(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(new Producto("Lechuga",20));
        productos.add(new Producto("Tomate",25));
        productos.add(new Producto("Cebolla",23));
        productos.add(new Producto("Pepinillo",18));
        productos.add(new Producto("Filete Pollo",150));
        productos.add(new Producto("Filete Ternera",210));
        productos.add(new Producto("Filete Lomo",190));
        return productos;
    }
}