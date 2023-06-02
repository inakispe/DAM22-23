package com.example.pasarentreactividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Ejercicio2_part2 extends AppCompatActivity {
    TextView totalProductosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ejercicio2_part2);

        totalProductosTextView = findViewById(R.id.precioTotal);

        Intent intent = getIntent();
        if (intent != null) {
            int totalProductos = intent.getIntExtra("totalProductos", 0);
            ArrayList<Integer> preciosSeleccionados = intent.getIntegerArrayListExtra("preciosSeleccionados");
            ArrayList<String> nombresSeleccionados = intent.getStringArrayListExtra("nombreSeleccionados");

            StringBuilder productosInfo = new StringBuilder();
            int totalPrecios = calcularTotalPrecios(preciosSeleccionados);

            HashSet<String> nombresUnicos = new HashSet<>(nombresSeleccionados); // Utilizar HashSet para evitar repetición

            for (String nombre : nombresUnicos) {
                int cantidad = Collections.frequency(nombresSeleccionados, nombre);
                int precio = preciosSeleccionados.get(nombresSeleccionados.indexOf(nombre));
                int precioTotalProducto = cantidad * precio;
                productosInfo.append(nombre).append(": ").append(cantidad).append(" unidades, precio total: ").append(precioTotalProducto).append("\n");
            }

            totalProductosTextView.setText(productosInfo.toString() + "\nTotal de productos seleccionados: " + totalProductos +
                    "\nTotal de todo: " + totalPrecios);
        }
    }

    private int calcularTotalPrecios(ArrayList<Integer> preciosSeleccionados) {
        int total = 0;
        for (int precio : preciosSeleccionados) {
            total += precio;
        }
        return total;
    }
}

//En este código, he creado un HashSet llamado nombresUnicos para almacenar los nombres de los productos seleccionados sin repetición.
// Luego, itero sobre nombresUnicos en lugar de nombresSeleccionados para evitar la repetición de información para productos con el mismo precio.
// Utilizo nombresSeleccionados.indexOf(nombre) para obtener el índice correspondiente al nombre
// en la lista nombresSeleccionados y obtener el precio correcto de preciosSeleccionados.