package com.aplicationapps.project_puca.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aplicationapps.project_puca.Controller.Aplastamiento_gruas_izajes_Activity;
import com.aplicationapps.project_puca.Controller.Transporte_Materiales_Peligrosos_Activity;
import com.aplicationapps.project_puca.Controller.Vehiculo_Trasporte_Personal_Activity;
import com.aplicationapps.project_puca.Controller.Perdida_Control_Vehiculos_livianos_Activity;
import com.aplicationapps.project_puca.R;

public class peligros_diez_lista_Activity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_transporte_personal;
    Button btn_Transporte_Material_Peligroso;
    Button btn_Vehiculos_livianos;
    Button btn_gruas_izajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peligros_diez_lista);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("TOP Ten - Riesgos Fatales");

        btn_transporte_personal = findViewById(R.id.btn_transporte_personal);
        btn_transporte_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_transporte_personal_Metodo();
            }
        });

        btn_Transporte_Material_Peligroso = findViewById(R.id.btn_Transporte_Material_Peligroso);
        btn_Transporte_Material_Peligroso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Transporte_Material_Peligroso_Methodo();
            }
        });

        btn_Vehiculos_livianos = findViewById(R.id.btn_Vehiculos_livianos);
        btn_Vehiculos_livianos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Vehiculos_livianos_Methodo();
            }
        });

        btn_gruas_izajes = findViewById(R.id.btn_gruas_izajes);
        btn_gruas_izajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_gruas_izajes_Methodo();
            }
        });
    }

    private void btn_transporte_personal_Metodo() {
        Toast.makeText(peligros_diez_lista_Activity.this, "Transporte Personal", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(peligros_diez_lista_Activity.this, Vehiculo_Trasporte_Personal_Activity.class);
        startActivity(intent);
    }

    private void btn_Transporte_Material_Peligroso_Methodo() {
        Toast.makeText(peligros_diez_lista_Activity.this, "Materiales Peligrosos", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(peligros_diez_lista_Activity.this, Transporte_Materiales_Peligrosos_Activity.class);
        startActivity(intent);
    }

    private void btn_Vehiculos_livianos_Methodo() {
        Toast.makeText(peligros_diez_lista_Activity.this, "Vehiculos Livianos", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(peligros_diez_lista_Activity.this, Perdida_Control_Vehiculos_livianos_Activity.class);
        startActivity(intent);
    }

    private void btn_gruas_izajes_Methodo() {
        Toast.makeText(peligros_diez_lista_Activity.this, "Actividades con Gruas e Izajes", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(peligros_diez_lista_Activity.this, Aplastamiento_gruas_izajes_Activity.class);
        startActivity(intent);
    }
}