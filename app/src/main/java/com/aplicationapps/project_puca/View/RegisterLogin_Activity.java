package com.aplicationapps.project_puca.View;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplicationapps.project_puca.MainActivity;
import com.aplicationapps.project_puca.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterLogin_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Toolbar toolbar;
    Spinner spinner_areas;
    TextInputEditText Register_editText_Nombres;
    TextInputEditText Register_editText_Apellidos;
    TextInputEditText Register_editText_CodigoUser;
    TextInputEditText Register_editText_Password;
    TextInputEditText Register_editText_Correo;
    EditText Recibe_EditText;
    Button Btn_RegistrarUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registro Usuario !!");



        /**
         * Spinner Areas
         */
        Spinner spinner_areas = findViewById(R.id.spinner_areas);
        /**
         * ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
         *                 R.array.areas_laborables, android.R.layout.simple_spinner_item);
         *         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         *         spinner.setAdapter(adapter);
         *         spinner.setOnItemSelectedListener(this);
         */

        List<String> opciones = new ArrayList<>();
        opciones.add("Seleccione su area Laboral");
        opciones.add("SSOMA");
        opciones.add("Logistica");
        opciones.add("Proyectos");
        opciones.add("RRHH");
        opciones.add("Mina");
        opciones.add("Planta");
        opciones.add("Geoplan");
        opciones.add("Laboratorio");
        opciones.add("Goetecnia");
        opciones.add("Mantenimiento");
        opciones.add("Sistemas");
        opciones.add("Seguridad Patrimonial");

        // Crear un ArrayAdapter utilizando las opciones y un diseño de spinner por defecto
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        // Especificar el diseño del dropdown que aparece al hacer clic en el spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el spinner
        spinner_areas.setAdapter(adapter);

        spinner_areas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado del spinner
                String opcionSeleccionada = opciones.get(position);

                // Establecer la opción seleccionada en el EditText
                Recibe_EditText.setText(opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /**
         * Spinner Areas
         */

        // Referenciar los TextInputEditText y Button
        Register_editText_Nombres = findViewById(R.id.Register_editText_Nombres);
        Register_editText_Apellidos = findViewById(R.id.Register_editText_Apellidos);
        Register_editText_CodigoUser = findViewById(R.id.Register_editText_CodigoUser);
        Register_editText_Password = findViewById(R.id.Register_editText_Password);
        Register_editText_Correo = findViewById(R.id.Register_editText_Correo);
        Recibe_EditText = findViewById(R.id.Recibe_EditText);
        // spinner_areas = (Spinner) findViewById(R.id.spinner_areas);
        
        Btn_RegistrarUsuario = (Button) findViewById(R.id.Btn_RegistrarUsuario);
        Btn_RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodo_RegistrarUsuario("https://controles-que-salvan-vidas.000webhostapp.com/API_/insertar_usuario.php");
                // URL funcional   http://192.168.0.9/Web_App_Checklist/insertar_usuario.php
                MetodoMain_Actividad();
            }
        });
    }

    private void MetodoMain_Actividad() {
        Toast.makeText(RegisterLogin_Activity.this, "Bienvenido a la Aplicaciòn !!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterLogin_Activity.this, MainActivity.class); //Login_Activity Control_Gen_Activity
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent(this, MainActivity.class); // Reemplaza OtraActividad con la clase de la actividad que deseas abrir
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Metodo_RegistrarUsuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Muchas Gracias Por Registrarse !!!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterLogin_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RegisterLogin_Activity.this, "Se Ocasiono un error Inesperado, revise su Conexión !!!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();

                parametros.put("usu_nombre",Register_editText_Nombres.getText().toString());
                parametros.put("usu_apellido",Register_editText_Apellidos.getText().toString());
                parametros.put("codigo_us",Register_editText_CodigoUser.getText().toString());
                parametros.put("us_password",Register_editText_Password.getText().toString());
                parametros.put("correo_corp",Register_editText_Correo.getText().toString());
                parametros.put("area_laboral",Recibe_EditText.getText().toString());
               // parametros.put("area_laboral",spinner_areas.getText().toString());
               // parametros.put("area_laboral",spinner_areas.toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        //Perteneciente al Implements Spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Perteneciente al Implements Spinner
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        //Perteneciente al Implements Spinner
    }
}