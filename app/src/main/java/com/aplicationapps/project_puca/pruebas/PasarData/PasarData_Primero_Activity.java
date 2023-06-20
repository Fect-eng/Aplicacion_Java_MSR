package com.aplicationapps.project_puca.pruebas.PasarData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aplicationapps.project_puca.R;

public class PasarData_Primero_Activity extends AppCompatActivity {

    private EditText editTextTextMultiLine2;
    private Spinner spinner2;
    private Spinner spinner3;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar_data_primero);

        editTextTextMultiLine2 = findViewById(R.id.editTextTextMultiLine2);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        /**
         *
         */
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PasarData_Primero_Activity.this, "Bienvenido a la Aplicaciòn !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PasarData_Primero_Activity.this, PasarData_Segundo_Activity.class); //Login_Activity Control_Gen_Activity
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = editTextTextMultiLine2.getText().toString();
                String seleccion1 = spinner2.getSelectedItem().toString();
                String seleccion2 = spinner3.getSelectedItem().toString();

                Intent intent = new Intent(PasarData_Primero_Activity.this, PasarData_Segundo_Activity.class);
                intent.putExtra("texto", texto);
                intent.putExtra("seleccion1", seleccion1);
                intent.putExtra("seleccion2", seleccion2);
                startActivity(intent);
            }

        });

        // Definir las opciones del Spinner
        final String[] opciones = {"op1", "op2", "op3"};

        // Definir las opciones del Spinner
        final String[] opciones3 = {"op1", "op2", "op3"};

        // Crear un ArrayAdapter utilizando el layout predeterminado de Android para los elementos del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Crear un ArrayAdapter utilizando el layout predeterminado de Android para los elementos del Spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el ArrayAdapter al Spinner
        spinner2.setAdapter(adapter);           // 2
        spinner3.setAdapter(adapter);           // 3

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener la opción seleccionada del Spinner
                String opcionSeleccionada = (String) spinner2.getSelectedItem();
                abrirActividad(opcionSeleccionada);
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la opción seleccionada del Spinner
                String opcionSeleccionada = (String) spinner2.getSelectedItem();
                // Aquí puedes realizar acciones adicionales según la opción seleccionada
                // Ejemplo: mostrar un Toast con la opción seleccionada
                Toast.makeText(PasarData_Primero_Activity.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
            }


            public void enviarInformacion(View view) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
}

    private void abrirActividad(String opcion) {

        Intent intent;
        switch (opcion) {
            case "op1":
                //intent = new Intent(PasarData_Primero_Activity.this, Aplastamiento_gruas_izajes_Activity.class);
               // startActivity(intent);
                break;
            case "op2":
               // intent = new Intent(PasarData_Primero_Activity.this, Perdida_Control_Vehiculos_livianos_Activity.class);
              //  startActivity(intent);
               // break;
            case "op3":
              //  intent = new Intent(PasarData_Primero_Activity.this, Transporte_Materiales_Peligrosos_Activity.class);
               // startActivity(intent);
                break;
        }
    }
}
