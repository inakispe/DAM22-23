package com.example.examencontinua;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadCalculo extends AppCompatActivity {
    Button Limpiar;
    Button Modificar;
    TextView Resultado;
    double ancho,alto,largo,descuento,cantidad, resultado, resultadoFinal, tamanio, tamanioCubo;
    public static final double valorMinimo=0.0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        Resultado= findViewById(R.id.resultado);
        Limpiar= findViewById(R.id.borrar);
        ancho = getIntent().getDoubleExtra("ancho",0.0);

        alto = getIntent().getDoubleExtra("alto",0.0);
        largo= getIntent().getDoubleExtra("largo",0.0);
        descuento= getIntent().getDoubleExtra("descuento",0.0);
        cantidad=getIntent().getDoubleExtra("cantidad",0.0);
        resultadoFinal=calcularMedida();
        String textoResultado=String.valueOf(resultadoFinal);
        Resultado.setText(textoResultado);
        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverActividadAnterior();
            }
        });
    }
    public double calcularMedida() {
        tamanio= ancho * alto * largo;
        tamanioCubo=tamanio/1000;
        if (descuento>valorMinimo){
            tamanioCubo*= 0.95; // Aplica un descuento del 5%
            resultado=tamanioCubo*cantidad;
        } else{
            resultado=tamanioCubo*cantidad;
        }
        return resultado;
    }
    public void volverActividadAnterior() {
        Intent intent = new Intent(this, HuertaNova.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("borrarDatos", true);
        startActivity(intent);
    }


}
