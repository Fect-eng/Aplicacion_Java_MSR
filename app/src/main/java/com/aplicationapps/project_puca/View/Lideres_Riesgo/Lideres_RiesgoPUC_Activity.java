package com.aplicationapps.project_puca.View.Lideres_Riesgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.aplicationapps.project_puca.R;

public class Lideres_RiesgoPUC_Activity extends AppCompatActivity {

    // TODO: 01/06/2023  verificacion Lideres de riesgo

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lideres_riesgo_puc);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Lideres de RIesgo");
    }
}