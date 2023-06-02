package com.example.lanzadora;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numero1, numero2;
    Button lanzar;
    RadioButton suma, resta;
    int n1, n2, total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero1= findViewById(R.id.n1);
        numero2= findViewById(R.id.n2);
        suma= findViewById(R.id.suma);
        resta= findViewById(R.id.resta);
        lanzar= findViewById(R.id.lanzar);
        ActivityResultLauncher<Intent> lanzadora= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),(result) ->{
            if (result.getResultCode()== Activity.RESULT_OK){
                Intent data= result.getData();
                total.setText(data.get)
            }
        } );
        n1=Integer.parseInt(numero1.getText().toString());
        n2=Integer.parseInt(numero2.getText().toString());
    }
    public void seleccionar(){
        if (suma.isChecked()){
            total=n1+n2;
        } else if (resta.isChecked()) {
            total=n1-n2;
        } else{
            total=0;
        }
    }
}