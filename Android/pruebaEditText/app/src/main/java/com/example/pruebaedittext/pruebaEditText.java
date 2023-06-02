package com.example.pruebaedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
public class pruebaEditText extends AppCompatActivity {

    TextView nombre, mail,usuario,todo;
    EditText editNombre, editMail, editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebaeditext);

        todo=findViewById(R.id.verTodo);
        editNombre=findViewById(R.id.editNombre);
        editMail=findViewById(R.id.editMail);
        editUser=findViewById(R.id.editUser);

        //Esta interfaz se utiliza para escuchar eventos de accion del teclado cuando se esta editando un TextView
        TextView.OnEditorActionListener manejador = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //Si la accion del teclado es GO (lr)
                if(i == EditorInfo.IME_ACTION_GO) {
                    //Setea todo
                    todo.setText(
                            //Dandole este formato
                            String.format(
                                    "Hola %s\nTus datos:\n%s\n%s",
                                    editNombre.getText(),
                                    editMail.getText(),
                                    editUser.getText()
                            )
                    );
                    //Se utiliza para interacturar con el teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //Oculta el teclado
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                }
                return false;
            }
        };
        //Aqui se le setea la accion a los edit para que escuchen el manejador
        editNombre.setOnEditorActionListener(manejador);
        editMail.setOnEditorActionListener(manejador);
        editUser.setOnEditorActionListener(manejador);
    }
}