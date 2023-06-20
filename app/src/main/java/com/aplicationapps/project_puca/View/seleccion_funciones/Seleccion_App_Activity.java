package com.aplicationapps.project_puca.View.seleccion_funciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.aplicationapps.project_puca.MainActivity;
import com.aplicationapps.project_puca.Model.Control_Gen_Activity;
import com.aplicationapps.project_puca.R;
import com.aplicationapps.project_puca.View.Controles.Actividad_Criticas_Activity;
import com.aplicationapps.project_puca.View.Lideres_Riesgo.Lideres_RiesgoPUC_Activity;
import com.aplicationapps.project_puca.View.Login_Activity;
import com.aplicationapps.project_puca.View.peligros_diez_lista_Activity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class Seleccion_App_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    CircleImageView imgButton_Control_vida;
    TextView txt_ControVida;
    TextInputEditText EditText_CodigoUser_Buscar;
    TextView TextView_Area_Laboral;
    RequestQueue requestQueue;        // Instancia de volley
    Button btn_ConsultaData_Selecc;
    CircleImageView IMGCircle_LideresRiesgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_app);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Opciones SSOMA");

       // TextView_Area_Laboral = findViewById(R.id.TextView_Area_Laboral);

        IMGCircle_LideresRiesgo = findViewById(R.id.IMGCircle_LideresRiesgo);
        IMGCircle_LideresRiesgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Seleccion_App_Activity.this, "Bienvenido a la Aplicaciòn !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Seleccion_App_Activity.this, Lideres_RiesgoPUC_Activity.class); //Login_Activity Control_Gen_Activity
                startActivity(intent);     //Aplastamiento_gruas_izajes_Activity        Lideres_RiesgoPUC_Activity
            }
        });

       // EditText_CodigoUser_Buscar = findViewById(R.id.EditText_CodigoUser_Buscar);
        /**
         * btn_ConsultaData_Selecc = (Button) findViewById(R.id.btn_ConsultaData_Selecc);
         *         btn_ConsultaData_Selecc.setOnClickListener(new View.OnClickListener() {
         *             @Override
         *             public void onClick(View view) {
         *                // btn_ConsultaData_Selecc_Metodo();
         *                 Toast.makeText(Seleccion_App_Activity.this, "Prueba", Toast.LENGTH_SHORT).show();
         *                 Buscar_Usuario("http://192.168.0.9/Web_App_Checklist/buscar.php?codigo_us="+TextView_Area_Laboral.getText()+"");
         *
         *             }
         *         });
         */

        txt_ControVida = (TextView) findViewById(R.id.txt_ControVida);
        txt_ControVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Metod_TextViewControlVida();
            }
        });
        imgButton_Control_vida = (CircleImageView) findViewById(R.id.imgButton_Control_vida);
        imgButton_Control_vida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metod_ControlVida();

            }
        });

    } // Fin de Oncreate

    private void btn_ConsultaData_Selecc_Metodo() {
    }

    private void Buscar_Usuario(String URL) {   // Buscar Producto
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; 1 < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        EditText_CodigoUser_Buscar.setText(jsonObject.getString("codigo"));
                        EditText_CodigoUser_Buscar.setText(jsonObject.getString("codigo_us"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXCION",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void Metod_TextViewControlVida() {
        Toast.makeText(Seleccion_App_Activity.this, "TOP Ten - Riesgos Fatales", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Seleccion_App_Activity.this, peligros_diez_lista_Activity.class);   //direccion hacia mapas Tecnico
        startActivity(intent);
    }

    private void Metod_ControlVida() {
        Toast.makeText(Seleccion_App_Activity.this, "TOP Ten - Actividad Critica", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Seleccion_App_Activity.this, Actividad_Criticas_Activity.class);   //direccion hacia mapas Tecnico
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // TODO: 28/05/2023 - Modificar la hamburguesa 
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}