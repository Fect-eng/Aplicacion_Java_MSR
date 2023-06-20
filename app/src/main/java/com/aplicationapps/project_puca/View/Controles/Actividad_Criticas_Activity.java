package com.aplicationapps.project_puca.View.Controles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aplicationapps.project_puca.Controller.Accidente_Explosivos_Activity;
import com.aplicationapps.project_puca.Controller.Aplastamiento_gruas_izajes_Activity;
import com.aplicationapps.project_puca.Controller.Caida_Distinto_Nivel_Activity;
import com.aplicationapps.project_puca.Controller.Contact_EnergiaElectrica_Activity;
import com.aplicationapps.project_puca.Controller.Descarga_Electrica_Rayo_Activity;
import com.aplicationapps.project_puca.Controller.Liberacion_desc_Energia_Activity;
import com.aplicationapps.project_puca.Controller.Perdida_Control_Vehiculos_livianos_Activity;
import com.aplicationapps.project_puca.Controller.Transporte_Materiales_Peligrosos_Activity;
import com.aplicationapps.project_puca.Controller.PerdidaControl_Minero_Auxiliar_Activity;
import com.aplicationapps.project_puca.Controller.Vehiculo_Trasporte_Personal_Activity;
import com.aplicationapps.project_puca.R;
import com.aplicationapps.project_puca.View.seleccion_funciones.Seleccion_App_Activity;
import com.google.android.material.navigation.NavigationView;

public class Actividad_Criticas_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    Button btn_siguiente_Form;     // TODO: 28/05/2023  falta asignar funcion
    private EditText EditText_Actividad_a_realizar;
    private Spinner spinner_empresa;
    private Spinner spinner_AreaLabora;
    private Spinner spinner_RiesgoFatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_criticas);

        btn_siguiente_Form = findViewById(R.id.btn_siguiente_Form);
        EditText_Actividad_a_realizar = findViewById(R.id.EditText_Actividad_a_realizar);
        spinner_empresa = findViewById(R.id.spinner_empresa);
        spinner_AreaLabora = findViewById(R.id.spinner_AreaLabora);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Flecha Retroceso
        getSupportActionBar().setTitle("Lista de Verificacion");

        Spinner spinner = findViewById(R.id.spinner_empresa);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.empresa_laboral, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        /**
         *
         */
        Spinner spinner1 = findViewById(R.id.spinner_AreaLabora);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.areas_laborables, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        /**
         *
         */

        spinner_RiesgoFatal = findViewById(R.id.spinner_RiesgoFatal);
        // Definir las opciones del Spinner
        final String[] opcionSeleccionada = {"1. Perdida de control del vehículo de Transporte de personal",
                                    "2. Perdida de control de Transporte de Materiales Peligrosos",
                                    "3. Perdida de control de Vehículos livianos",
                                    "4. Aplastamiento en actividades con Gruas e izajes",
                                    "5. Perdida de control Equipos Mineros y auxiliares",
                                    "6. Accidente con explosivos",
                                    "7. Liberacion descontrolada de energia",
                                    "8. Contacto con Energía eléctrica",
                                    "9. Descarga eléctrica por rayo",
                                    "10. Caída de persona a diferente nivel"};

        // Crear un ArrayAdapter utilizando el layout predeterminado de Android para los elementos del Spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionSeleccionada);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el ArrayAdapter al Spinner
        spinner_RiesgoFatal.setAdapter(adapter3);

        btn_siguiente_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/06/2023  Boton de Varios Metodos - 10 Check Activity -  Boton Siguiente Formulario

                // String opcionSeleccionada = (String) spinner_RiesgoFatal.getSelectedItem();
                //  abrirActividad(opcionSeleccionada);

                // String opcionSeleccionada = spinner_RiesgoFatal.getSelectedItem().toString();

                /**
                 *  enviarData_Vehiculo_Trasporte_Personal_Activity();
                 *                    enviarData_Transporte_Materiales_Peligrosos_Activity();
                 *                    enviarData_Perdida_Control_Vehiculos_livianos_Activity();
                 *                    enviarData_Aplastamiento_gruas_izajes_Activity();
                 *                    enviarData_PerdidaControl_Minero_Auxiliar_Activity();
                 *                    enviarData_Accidente_Explosivos_Activity();
                 *                    enviarData_Liberacion_desc_Energia_Activity();
                 *                    enviarData_Contact_EnergiaElectrica_Activity();
                 *                    enviarData_Descarga_Electrica_Rayo_Activity();
                 *                    enviarData_Caida_Distinto_Nivel_Activity();
                  */

                String opcionSeleccionada = (String) spinner_RiesgoFatal.getSelectedItem().toString();
                abrirActividad(opcionSeleccionada);




            }
        });

                spinner_RiesgoFatal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la opción seleccionada del Spinner
                String opcionSeleccionada = (String) spinner.getSelectedItem();
                // Aquí puedes realizar acciones adicionales según la opción seleccionada
                // Ejemplo: mostrar un Toast con la opción seleccionada
                Toast.makeText(Actividad_Criticas_Activity.this, " " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó ninguna opción
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, Seleccion_App_Activity.class); // Reemplaza OtraActividad con la clase de la actividad que deseas abrir
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fin de codigo
     * @param opcionSeleccionada
     */
    // TODO: 11/06/2023  Actividades Direccionadas por actividad, incluye codigo para envio de datos correspondiente  
    private void abrirActividad(String opcionSeleccionada) {
        Intent intent;
        switch (opcionSeleccionada) {
            case "1. Perdida de control del vehículo de Transporte de personal":
                Toast.makeText(Actividad_Criticas_Activity.this, "1. Perdida de control del vehículo de Transporte de personal" , Toast.LENGTH_SHORT).show();

               // intent = new Intent(Actividad_Criticas_Activity.this, Vehiculo_Trasporte_Personal_Activity.class);
                String text = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue1 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue2 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue3 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Vehiculo_Trasporte_Personal_Activity.class);
                intent.putExtra("text", text);
                intent.putExtra("spinnerValue1", spinnerValue1);
                intent.putExtra("spinnerValue2", spinnerValue2);
                intent.putExtra("spinnerValue3", spinnerValue3);
                //startActivity(intent);

                startActivity(intent);
                break;
            case "2. Perdida de control de Transporte de Materiales Peligrosos":
                Toast.makeText(Actividad_Criticas_Activity.this, "2. Perdida de control de Transporte de Materiales Peligrosos" , Toast.LENGTH_SHORT).show();
                String text1 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue11 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue22 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue33 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Transporte_Materiales_Peligrosos_Activity.class);
                intent.putExtra("text", text1);
                intent.putExtra("spinnerValue1", spinnerValue11);
                intent.putExtra("spinnerValue2", spinnerValue22);
                intent.putExtra("spinnerValue3", spinnerValue33);
                //startActivity(intent);

                startActivity(intent);
                break;
            case "3. Perdida de control de Vehículos livianos":
                Toast.makeText(Actividad_Criticas_Activity.this, "3. Perdida de control de Vehículos livianos" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Perdida_Control_Vehiculos_livianos_Activity.class);
                String text3 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue321 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue3211 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue32 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Perdida_Control_Vehiculos_livianos_Activity.class);
                intent.putExtra("text", text3);
                intent.putExtra("spinnerValue1", spinnerValue321);
                intent.putExtra("spinnerValue2", spinnerValue3211);
                intent.putExtra("spinnerValue3", spinnerValue32);

                startActivity(intent);
                break;
            case "4. Aplastamiento en actividades con Gruas e izajes":
                Toast.makeText(Actividad_Criticas_Activity.this, "4. Aplastamiento en actividades con Gruas e izajes" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Aplastamiento_gruas_izajes_Activity.class);
                String text4321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue42 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue41 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Aplastamiento_gruas_izajes_Activity.class);
                intent.putExtra("text", text4321);
                intent.putExtra("spinnerValue1", spinnerValue432);
                intent.putExtra("spinnerValue2", spinnerValue42);
                intent.putExtra("spinnerValue3", spinnerValue41);
                startActivity(intent);
                break;
            case "5. Perdida de control Equipos Mineros y auxiliares":
                Toast.makeText(Actividad_Criticas_Activity.this, "5. Perdida de control Equipos Mineros y auxiliares" , Toast.LENGTH_SHORT).show();

               // intent = new Intent(Actividad_Criticas_Activity.this, PerdidaControl_Minero_Auxiliar_Activity.class);
                String text54321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue5432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, PerdidaControl_Minero_Auxiliar_Activity.class);
                intent.putExtra("text", text54321);
                intent.putExtra("spinnerValue1", spinnerValue5432);
                intent.putExtra("spinnerValue2", spinnerValue542);
                intent.putExtra("spinnerValue3", spinnerValue541);
                startActivity(intent);
                break;
            case "6. Accidente con explosivos":
                Toast.makeText(Actividad_Criticas_Activity.this, "6. Accidente con explosivos" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Accidente_Explosivos_Activity.class);
                String text654321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue65432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue6542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue6541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Accidente_Explosivos_Activity.class);
                intent.putExtra("text", text654321);
                intent.putExtra("spinnerValue1", spinnerValue65432);
                intent.putExtra("spinnerValue2", spinnerValue6542);
                intent.putExtra("spinnerValue3", spinnerValue6541);
                startActivity(intent);
                break;
            case "7. Liberacion descontrolada de energia":
                Toast.makeText(Actividad_Criticas_Activity.this, "7. Liberacion descontrolada de energia" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Liberacion_desc_Energia_Activity.class);
                String text7654321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue765432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue76542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue76541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Liberacion_desc_Energia_Activity.class);
                intent.putExtra("text", text7654321);
                intent.putExtra("spinnerValue1", spinnerValue765432);
                intent.putExtra("spinnerValue2", spinnerValue76542);
                intent.putExtra("spinnerValue3", spinnerValue76541);
                startActivity(intent);
                break;
            case "8. Contacto con Energía eléctrica":
                Toast.makeText(Actividad_Criticas_Activity.this, "8. Contacto con Energía eléctrica" , Toast.LENGTH_SHORT).show();

               // intent = new Intent(Actividad_Criticas_Activity.this, Contact_EnergiaElectrica_Activity.class);
                String text87654321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue8765432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue876542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue876541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Contact_EnergiaElectrica_Activity.class);
                intent.putExtra("text", text87654321);
                intent.putExtra("spinnerValue1", spinnerValue8765432);
                intent.putExtra("spinnerValue2", spinnerValue876542);
                intent.putExtra("spinnerValue3", spinnerValue876541);
                startActivity(intent);
                break;
            case "9. Descarga eléctrica por rayo":
                Toast.makeText(Actividad_Criticas_Activity.this, "9. Descarga eléctrica por rayo" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Descarga_Electrica_Rayo_Activity.class);
                String text987654321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue98765432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue9876542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue9876541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Descarga_Electrica_Rayo_Activity.class);
                intent.putExtra("text", text987654321);
                intent.putExtra("spinnerValue1", spinnerValue98765432);
                intent.putExtra("spinnerValue2", spinnerValue9876542);
                intent.putExtra("spinnerValue3", spinnerValue9876541);
                startActivity(intent);
                break;
            case "10. Caída de persona a diferente nivel":
                Toast.makeText(Actividad_Criticas_Activity.this, "10. Caída de persona a diferente nivel" , Toast.LENGTH_SHORT).show();

                //intent = new Intent(Actividad_Criticas_Activity.this, Caida_Distinto_Nivel_Activity.class);
                String text0987654321 = EditText_Actividad_a_realizar.getText().toString();
                String spinnerValue098765432 = spinner_empresa.getSelectedItem().toString();
                String spinnerValue09876542 = spinner_AreaLabora.getSelectedItem().toString();
                String spinnerValue09876541 = spinner_RiesgoFatal.getSelectedItem().toString();

                intent = new Intent(Actividad_Criticas_Activity.this, Caida_Distinto_Nivel_Activity.class);
                intent.putExtra("text", text0987654321);
                intent.putExtra("spinnerValue1", spinnerValue098765432);
                intent.putExtra("spinnerValue2", spinnerValue09876542);
                intent.putExtra("spinnerValue3", spinnerValue09876541);
                startActivity(intent);
                break;

    }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        //Perteneciente al Implements Spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // TODO: 28/05/2023 -- Modificar
        return false;
    }
}