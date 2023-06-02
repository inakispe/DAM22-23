package com.example.examencontinua;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SaltadoresActivity extends AppCompatActivity {
    RecyclerView lista;
    SaltadoresAdapter saltadoresAdapter;
    public SaltadoreViewModel saltadoreViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saltadores);
            saltadoresAdapter= new SaltadoresAdapter();
            lista=findViewById(R.id.saltadoresRV);
            lista.setLayoutManager(new LinearLayoutManager(this));
            lista.setAdapter(saltadoresAdapter);
            saltadoreViewModel= new ViewModelProvider(this).get(SaltadoreViewModel.class);
            saltadoreViewModel.getListaSaltadoresData().observe(this, dato ->{
                saltadoresAdapter.setResults(dato);
            });
           saltadoreViewModel.getSaltadores();
          }
    }
