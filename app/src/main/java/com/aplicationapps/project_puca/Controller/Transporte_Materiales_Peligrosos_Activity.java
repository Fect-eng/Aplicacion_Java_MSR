package com.aplicationapps.project_puca.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.aplicationapps.project_puca.R;

public class Transporte_Materiales_Peligrosos_Activity extends AppCompatActivity {

    Toolbar toolbar;

    private EditText Recibe_editTextTextMultiLine;
    private EditText Recibe_EditText_Empresa_DondeLabora;
    private EditText Recibe_EditText_Area_donde_Labora;
    private EditText Recibe_EditText_Riesgo_Fatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporte_materiales_peligrosos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Materiales Peligrosos");

        Recibe_editTextTextMultiLine = findViewById(R.id.Recibe_editTextTextMultiLine);
        Recibe_EditText_Empresa_DondeLabora = findViewById(R.id.Recibe_EditText_Empresa_DondeLabora);
        Recibe_EditText_Area_donde_Labora = findViewById(R.id.Recibe_EditText_Area_donde_Labora);
        Recibe_EditText_Riesgo_Fatal = findViewById(R.id.Recibe_EditText_Riesgo_Fatal);
        /**
         *  Codigo para recibir data en los EditText
         */
        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("text");
            String spinnerValue1 = intent.getStringExtra("spinnerValue1");
            String spinnerValue2 = intent.getStringExtra("spinnerValue2");
            String spinnerValue3 = intent.getStringExtra("spinnerValue3");

            String receivedText1 = "Actividad: " + text;
            Recibe_editTextTextMultiLine.setText(receivedText1);

            String receivedText2 = "Empresa: " + spinnerValue1;
            Recibe_EditText_Empresa_DondeLabora.setText(receivedText2);

            String receivedText3 = "Area Laboral: " + spinnerValue2;
            Recibe_EditText_Area_donde_Labora.setText(receivedText3);

            String receivedText4 = "Riesgo Fatal: " + spinnerValue3;
            Recibe_EditText_Riesgo_Fatal.setText(receivedText4);

            /**
             *  Codigo para recibir data en los EditText
             */
        }
    }
}