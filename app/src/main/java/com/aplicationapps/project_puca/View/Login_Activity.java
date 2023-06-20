package com.aplicationapps.project_puca.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplicationapps.project_puca.Model.Control_Gen_Activity;
import com.aplicationapps.project_puca.R;

import com.aplicationapps.project_puca.View.seleccion_funciones.Seleccion_App_Activity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button btn_loginAuth;          // Boton
    private RequestQueue rq;
    TextInputEditText EDT_IDUser;
    TextInputEditText EDT_Password;
    TextView txtVisualizador;
    TextView TXT_Register;              // TXT_Register

    private static final String PREF_TEXT_VIEW_DISPLAYED = "text_view_displayed";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Inicio de Sesión");

        txtVisualizador = findViewById(R.id.txtVisualizador);
        // Obtener el valor de SharedPreferences


        EDT_IDUser = findViewById(R.id.EDT_IDUser);
        EDT_Password = findViewById(R.id.EDT_Password);

        btn_loginAuth = findViewById(R.id.btn_loginAuth);   // Boton Funcion metodo java
        btn_loginAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url = "http://192.168.0.11/KasSandra_App/validar_usuario.php/"+EDT_Password.getText().toString();  //desde la API apuntamos al registro code_estudent

               // Metodo_Btn_Login("http://192.168.100.48/Web_App_Checklist/validar_usuario.php");
                Metodo_Btn_Login("https://controles-que-salvan-vidas.000webhostapp.com/API_/validar_usuario.php");

                //Cambiar la Ip es dinamico

                  //Toast.makeText(Login_Activity.this, "Prueba Desarrollo", Toast.LENGTH_SHORT).show();
                   // Intent intent = new Intent(Login_Activity.this, Seleccion_App_Activity.class);   //direccion hacia mapas Tecnico Seleccion_App_Activity
                   // startActivity(intent);

            }
        });

        TXT_Register = findViewById(R.id.TXT_Register);
        /**
         *  Codigo que hace el TXT_Register que es un TextView hace que aparezca 1 sola vez asi evitamos que se registren dos veces
         */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean textViewDisplayed = sharedPreferences.getBoolean(PREF_TEXT_VIEW_DISPLAYED, false);

        if (!textViewDisplayed) {
            // Mostrar el TextView por primera vez
            TXT_Register.setVisibility(View.VISIBLE);

            // Guardar el valor en SharedPreferences para indicar que ya ha sido mostrado
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREF_TEXT_VIEW_DISPLAYED, true);
            editor.apply();
        } else {
            // El TextView ya ha sido mostrado anteriormente, ocultarlo
            TXT_Register.setVisibility(View.GONE);
        }
        /**
         * Fin del Codigo del TextView
         */

        TXT_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodo_TextoRegistro();
            }

            private void Metodo_TextoRegistro() {
                Toast.makeText(Login_Activity.this, " #1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Activity.this, RegisterLogin_Activity.class);   //direccion hacia mapas Tecnico
                startActivity(intent);
            }

        });

    }

    private void Metodo_Btn_Login(String URL) {
        //String url = "http://192.168.0.11/KasSandra_App/validar_usuario.php/"+EDT_Password.getText().toString();  //desde la API apuntamos al registro code_estudent
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), Seleccion_App_Activity.class);
                   // Toast.makeText(Login_Activity.this, "Bienvenido a la Aplicacion:"+txtVisualizador.getText().toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Login_Activity.this, "Pucamarca le da Bienvenida !!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(Login_Activity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String,String> parametros=new HashMap<String,String>();
              parametros.put("codigo_us", EDT_IDUser.getText().toString());        // usu_nombre           codigo_us
             // parametros.put("us_password",EDT_Password.getText().toString());     // usu_apellido         us_password
              return  parametros;
          }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
