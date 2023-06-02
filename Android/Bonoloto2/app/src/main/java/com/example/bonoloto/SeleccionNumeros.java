package com.example.bonoloto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeleccionNumeros extends AppCompatActivity {

    private static final int POSICION_REAL = 1;
    private static final int VALOR_MINIMO = 0;
    private static final int VALOR_MAXIMO = 3;
    private static final int CUENTA_CHECKS_MIN = 1;
    private static final int CUENTA_CHECKS_MAX = 10;
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    ArrayList<Boolean> checkboxValues = new ArrayList<>();
    ArrayList<String> checkboxTexts = new ArrayList<>();
    Button idCancelar, idAcceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_numeros);

        //RECOGEMOS VALORES ENVIADOS ANTERIORMENTE
        String numero1 = getIntent().getExtras().getString("numero1");
        String numero2 = getIntent().getExtras().getString("numero2");
        String numero3 = getIntent().getExtras().getString("numero3");

        //INSTANCIAMOS LOS CHECKBOX EN UN ARRAY LIST
        for (int i = CUENTA_CHECKS_MIN; i <= CUENTA_CHECKS_MAX; i++) {
            int resID = getResources().getIdentifier("checkBox" + i, "id", getPackageName());
            checkBoxes.add((CheckBox) findViewById(resID));
        }

        idCancelar = findViewById(R.id.idCancelar);
        idAcceptar = findViewById(R.id.idAcceptar);

        marcarValoresRecibidos(numero1, numero2, numero3);

        idAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarCuantos();
            }
        });
        idCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desmarcarValoresRecibidos(numero1, numero2, numero3);
            }
        });
    }

    //SI EL VALOR RECIBIDO DESDE LA PRINCIPA EL NULO NO HACE NADA EN OTRO MODO CHEQUEA LOS VALORES
    public void marcarValoresRecibidos(@NonNull String numero1, String numero2, String numero3) {

        if (numero1.isEmpty() || numero2.isEmpty() || numero3.isEmpty()) {
            Toast.makeText(this, "vacio", Toast.LENGTH_SHORT).show();
        } else {
            int parseado1 = Integer.parseInt(numero1) - POSICION_REAL;
            int parseado2 = Integer.parseInt(numero2) - POSICION_REAL;
            int parseado3 = Integer.parseInt(numero3) - POSICION_REAL;

            checkBoxes.get(parseado1).setChecked(true);
            checkBoxes.get(parseado2).setChecked(true);
            checkBoxes.get(parseado3).setChecked(true);
        }

    }
    //COMPROBAMOS CUANTOS CHECKBOX HA MARCADO Y ENVIAMOS DATOS
    public void  comprobarCuantos() {
        int seleccionados = VALOR_MINIMO;
        for (CheckBox check : checkBoxes) {
            if (check.isChecked()) {
                seleccionados++;
            }
        }
        if (seleccionados == VALOR_MAXIMO) {
            envioDeDatos();
        } else if(seleccionados>VALOR_MAXIMO) {
            Toast.makeText(SeleccionNumeros.this, "Debe seleccionar maximo 3 elementos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SeleccionNumeros.this, "Debe seleccionar minimo 3 elementos", Toast.LENGTH_SHORT).show();
        }
    }
    public void desmarcarValoresRecibidos(String numero1, String numero2, String numero3){
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            }
        }
    }
    public void envioDeDatos(){

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                checkboxTexts.add(checkBox.getText().toString());
            }
        }
        Intent intent = new Intent(this, MainBonoloto.class);
        intent.putStringArrayListExtra("checkbox_texts", checkboxTexts);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}