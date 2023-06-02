package com.example.examencontinua;

import android.app.Activity;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HuertaNova extends AppCompatActivity {
    private static final double TURBA=0.87;
    private static final double FIBRA=0.8;
    private static final double SUSTRATO=0.85;
    TextView ancho, alto, largo;
    CheckBox ck1,ck2,ck3;
    Button enviar;
    double cantidad;
    double cantancho,cantlargo,cantalto;
    double descuento;
    Switch drenaje;
    boolean borrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huertanova);
        ancho=findViewById(R.id.ancho);
        alto=findViewById(R.id.alto);
        largo=findViewById(R.id.largo);
        ck1=findViewById(R.id.checkBox1);
        ck2=findViewById(R.id.checkBox2);
        ck3=findViewById(R.id.checkBox3);
        drenaje=findViewById(R.id.switch1);
        enviar=findViewById(R.id.enviar);
        borrarDatos = getIntent().getBooleanExtra("borrarDatos", false);
        if (borrarDatos) {
            borrarDatos();
        }
        ActivityResultLauncher<Intent> lanzadora = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), (result)->{
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        //Aqui pilla el data en array de String
                        cantancho = Double.parseDouble(ancho.getText().toString());
                        cantalto = Double.parseDouble(alto.getText().toString());
                        cantlargo = Double.parseDouble(largo.getText().toString());
                        if (ck1.isChecked()){
                         cantidad=TURBA;
                        } else if (ck2.isChecked()) {
                            cantidad=SUSTRATO;

                        } else{
                            cantidad=FIBRA;
                        }
                        if (drenaje.isChecked()) {
                            descuento=0.05;
                        } else {
                            descuento=0;
                        }
                    }
                }
        );
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HuertaNova.this,ActividadCalculo.class);
                intent.putExtra("ancho",cantancho);
                intent.putExtra("alto",cantalto);
                intent.putExtra("largo",cantlargo);
                intent.putExtra("cantidad",cantidad);
                intent.putExtra("descuento",descuento);
                lanzadora.launch(intent);
            }
        });


    }
    public void borrarDatos() {
        ancho.setText("");
        alto.setText("");
        largo.setText("");
        ck1.setChecked(false);
        ck2.setChecked(false);
        ck3.setChecked(false);
        drenaje.setChecked(false);
    }
}
