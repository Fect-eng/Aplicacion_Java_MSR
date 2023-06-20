package com.aplicationapps.project_puca.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.aplicationapps.project_puca.Controller.Vehiculo_Trasporte_Personal_Activity;
import com.aplicationapps.project_puca.R;

public class Control_Gen_Activity extends AppCompatActivity {

    Toolbar toolbar;
    private RequestQueue rq;

    Button btn_transporte_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_gen);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#3F51B6")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("TOP Ten - Riesgos Fatales");

        btn_transporte_personal = findViewById(R.id.btn_transporte_personal);
        btn_transporte_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Control_Gen_Activity.this, "Bienvenido !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Control_Gen_Activity.this, Vehiculo_Trasporte_Personal_Activity.class);
                startActivity(intent);
            }
        });
    }
}