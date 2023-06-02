package com.example.bonoloto;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainBonoloto extends AppCompatActivity {

    private static final int NUMERO_UNO = 0;
    private static final int NUMERO_DOS = 1;
    private static final int NUMERO_TRES = 2;
    Button idBotonSeleccion;
    TextView idNum1, idNum2, idNum3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bono_loto);

        idBotonSeleccion = findViewById(R.id.idBotonSeleccion);
        idNum1 = findViewById(R.id.idNum1);
        idNum2 = findViewById(R.id.idNum2);
        idNum3 = findViewById(R.id.idNum3);


        ActivityResultLauncher<Intent> lanzadora = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), (result)->{
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        //Aqui pilla el data en array de String
                        ArrayList<String> checkboxTexts = data.getStringArrayListExtra("checkbox_texts");

                        String num1 = checkboxTexts.get(NUMERO_UNO);
                        idNum1.setText(num1);
                        String num2 = checkboxTexts.get(NUMERO_DOS);
                        idNum2.setText(num2);
                        String num3 = checkboxTexts.get(NUMERO_TRES);
                        idNum3.setText(num3);
                    }
                }
        );

        idBotonSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainBonoloto.this, SeleccionNumeros.class);
                intent.putExtra("numero1", idNum1.getText().toString());
                intent.putExtra("numero2", idNum2.getText().toString());
                intent.putExtra("numero3", idNum3.getText().toString());
                lanzadora.launch(intent);

            }
        });

    }
}