package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.aplicationapps.project_puca.Controller.Aplastamiento_gruas_izajes_Activity;
import com.aplicationapps.project_puca.Controller.Perdida_Control_Vehiculos_livianos_Activity;
import com.aplicationapps.project_puca.Controller.Transporte_Materiales_Peligrosos_Activity;
import com.aplicationapps.project_puca.R;

public class spinner_Activity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);

        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);

        // Definir las opciones del Spinner
        final String[] opciones = {"op1", "op2", "op3"};

        // Crear un ArrayAdapter utilizando el layout predeterminado de Android para los elementos del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el ArrayAdapter al Spinner
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // metodo();
                // Obtener la opción seleccionada del Spinner
                String opcionSeleccionada = (String) spinner.getSelectedItem();
                abrirActividad(opcionSeleccionada);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la opción seleccionada del Spinner
                String opcionSeleccionada = (String) spinner.getSelectedItem();
                // Aquí puedes realizar acciones adicionales según la opción seleccionada
                // Ejemplo: mostrar un Toast con la opción seleccionada
                Toast.makeText(spinner_Activity.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó ninguna opción
            }
        });
    }


    private void abrirActividad(String opcion) {
        Intent intent;
        switch (opcion) {
            case "op1":
                intent = new Intent(spinner_Activity.this, Aplastamiento_gruas_izajes_Activity.class);
                startActivity(intent);
                break;
            case "op2":
                intent = new Intent(spinner_Activity.this, Perdida_Control_Vehiculos_livianos_Activity.class);
                startActivity(intent);
                break;
            case "op3":
                intent = new Intent(spinner_Activity.this, Transporte_Materiales_Peligrosos_Activity.class);
                startActivity(intent);
                break;
        }
    }
}

