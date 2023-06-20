package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.aplicationapps.project_puca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Busqueda_PHP_Activity extends AppCompatActivity {
    private EditText etBusqueda;
    private Button btnBuscar;
    private TextView tvResultado;

    private static final String URL_PHP = "http://192.168.0.9/Web_App_Checklist/Buscar_usuario.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_php);

        etBusqueda = findViewById(R.id.etBusqueda);
        btnBuscar = findViewById(R.id.btnBuscar);
        tvResultado = findViewById(R.id.tvResultado);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda = etBusqueda.getText().toString();
                buscarDatos(busqueda);
            }
        });
    }

    private void buscarDatos(String busqueda) {
        // Crear una solicitud de tipo JsonArrayRequest utilizando Volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL_PHP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta JSON recibida del servidor
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                // Obtener los valores de las columnas necesarias
                                String columna1 = jsonObject.getString("usu_apellido");
                                String columna2 = jsonObject.getString("codigo_us");

                                // Realizar las acciones necesarias con los datos obtenidos
                                String resultado = "usu_apellido: " + columna1 + "\ncodigo_us: " + columna2;
                                tvResultado.setText(resultado);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        Log.e("Error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Parámetros que se enviarán al archivo PHP en el servidor
                Map<String, String> params = new HashMap<>();
                params.put("busqueda", busqueda);
                return params;
            }
        };

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}