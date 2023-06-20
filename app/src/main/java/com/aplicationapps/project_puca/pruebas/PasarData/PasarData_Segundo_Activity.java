package com.aplicationapps.project_puca.pruebas.PasarData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.aplicationapps.project_puca.R;

public class PasarData_Segundo_Activity extends AppCompatActivity {

    private EditText Edt1;
    private EditText Edt2;
    private EditText Edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar_data_segundo);

        Edt1 = findViewById(R.id.Edt1);      // prueba
        Edt2 = findViewById(R.id.Edt2);
        Edt3 = findViewById(R.id.Edt3);

        Intent intent = getIntent();
        String texto = intent.getStringExtra("texto");
        String seleccion1 = intent.getStringExtra("seleccion1");
        String seleccion2 = intent.getStringExtra("seleccion2");

        String resultado = "Texto: " + texto + "\n" +
                "Selección 1: " + seleccion1 + "\n" +
                "Selección 2: " + seleccion2;

       // editTextResultado.setText(resultado);
        Edt1.setText(resultado);
    }
}