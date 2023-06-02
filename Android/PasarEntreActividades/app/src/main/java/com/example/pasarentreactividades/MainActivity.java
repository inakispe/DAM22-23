package com.example.pasarentreactividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    AdaptadorProductos adapter;
    Button total;

    private Handler handler;
    private Runnable runnable;

    public ArrayList<Producto> lista;
    public static ArrayList<String> listaTotal = new ArrayList<>();
    private int totalProductosSeleccionados = 0;
    private ArrayList<Integer> preciosSeleccionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.RV);
        total = findViewById(R.id.total);
        //Aqui se le da tamaño al RV
        recyclerView.setHasFixedSize(true);
        //Aqui se le da el layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<>(Producto.generador());
        adapter = new AdaptadorProductos(lista);

        adapter.setOnItemClickListener(new AdaptadorProductos.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int precio) {
                Producto producto = lista.get(position);
                int cantidadSeleccionada = producto.getCantidadSeleccionada() + 1;
                producto.setCantidadSeleccionada(cantidadSeleccionada);

                totalProductosSeleccionados++;
                preciosSeleccionados.add(producto.getPrecio());
                listaTotal.add(producto.getNombre());

                //total.setText("Total: " + totalProductosSeleccionados + " (€" + precioTotalProductos + ")");
            }
        });
        recyclerView.setAdapter(adapter);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 5000); // Espera de 5 segundos antes de pasar a la siguiente
            }
        });
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Ejercicio2_part2.class);
                intent.putExtra("totalProductos", totalProductosSeleccionados);
                intent.putExtra("preciosSeleccionados", preciosSeleccionados);
                intent.putExtra("nombreSeleccionados", listaTotal);

                startActivity(intent);
            }
        };
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable); // Detener el Runnable si se vuelve atrás
    }
}