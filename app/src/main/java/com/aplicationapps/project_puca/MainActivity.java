package com.aplicationapps.project_puca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aplicationapps.project_puca.Controller.Accidente_Explosivos_Activity;
import com.aplicationapps.project_puca.Controller.Aplastamiento_gruas_izajes_Activity;
import com.aplicationapps.project_puca.Controller.Caida_Distinto_Nivel_Activity;
import com.aplicationapps.project_puca.Controller.Vehiculo_Trasporte_Personal_Activity;
import com.aplicationapps.project_puca.View.Controles.Actividad_Criticas_Activity;
import com.aplicationapps.project_puca.View.Login_Activity;
import com.aplicationapps.project_puca.View.RegisterLogin_Activity;
import com.aplicationapps.project_puca.pruebas.Generate_PDF_Text_Activity;

public class MainActivity extends AppCompatActivity {

    Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = findViewById(R.id.btn_iniciar);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Metodo_WelcomeLogin();
            }

            private void Metodo_WelcomeLogin() {   // Login_Activity  resolucion_Activity Vehiculo_Trasporte_Personal_Activity
              //  Toast.makeText(MainActivity.this, "Bienvenido a la Aplicaciòn !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Login_Activity.class); //Actividad_Criticas_Activity Control_Gen_Activity
                startActivity(intent);     //Aplastamiento_gruas_izajes_Activity   Login_Activity Actividad_Criticas_Activity
            }
            /**
             * Es la interfaz que primero se visualiza, es decir la presentacion
             */
        });


    }
}