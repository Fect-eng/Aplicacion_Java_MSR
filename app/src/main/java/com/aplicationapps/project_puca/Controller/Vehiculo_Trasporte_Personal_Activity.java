package com.aplicationapps.project_puca.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.aplicationapps.project_puca.MainActivity;
import com.aplicationapps.project_puca.R;
import com.aplicationapps.project_puca.pruebas.resolucion_Activity;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Vehiculo_Trasporte_Personal_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    private RequestQueue rq;
    Button btn_envioData;

    TextView TextView_Asegurar_Inspecciones;
    private CheckBox checkBox_cumpleAseguraInspeccion;
    private CheckBox checkBox_NO_cumpleAseguraInspeccion;

    TextView TextView_Garantizar_Cumplimiento;
    CheckBox CheckBox_2_SI_Garantizar_Cumplimiento;
    CheckBox CheckBox_2_NO_Garantizar_Cumplimiento;

    TextView TextView_Monitoreo_Ubicacion;
    CheckBox CheckBox_3_SI_Monitoreo_Ubicacion;       // realizando
    CheckBox CheckBox_3_NO_Monitoreo_Ubicacion;

    TextView TextView_Control_Alcotest;
    CheckBox CheckBox_4_SI_Control_Alcotest;
    CheckBox CheckBox_4_NO_Control_Alcotest;

    TextView TextView_Control_Somnolencia;
    CheckBox CheckBox_5_SI_Control_Somnolencia;
    CheckBox CheckBox_5_NO_Control_Somnolencia;

    TextView TextView_Validacion_Experiencia;
    CheckBox CheckBox_6_SI_Validacion_Experiencia;
    CheckBox CheckBox_6_NO_Validacion_Experiencia;

    TextView TextView_Comportamiento_Riesgoso;
    CheckBox CheckBox_7_SI_Comportamiento_Riesgoso;
    CheckBox CheckBox_7_NO_Comportamiento_Riesgoso;

    TextView TextView_Vehiculo_VIP;
    CheckBox CheckBox_8_SI_Vehiculo_VIP;
    CheckBox CheckBox_8_NO_Vehiculo_VIP;

    TextView TextView_Plan_Rutas;
    CheckBox CheckBox_9_SI_Plan_Rutas;
    CheckBox CheckBox_9_NO_Plan_Rutas;

    private EditText Recibe_editTextTextMultiLine;
    private EditText Recibe_EditText_Empresa_DondeLabora;
    private EditText Recibe_EditText_Area_donde_Labora;
    private EditText Recibe_EditText_Riesgo_Fatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_trasporte_personal);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Crear instancia de AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Control Transporte Personal");

        /**
         * Codigo que recibe datos
         */
        Recibe_editTextTextMultiLine = findViewById(R.id.Recibe_editTextTextMultiLine);
        Recibe_EditText_Empresa_DondeLabora = findViewById(R.id.Recibe_EditText_Empresa_DondeLabora);
        Recibe_EditText_Area_donde_Labora = findViewById(R.id.Recibe_EditText_Area_donde_Labora);
        Recibe_EditText_Riesgo_Fatal = findViewById(R.id.Recibe_EditText_Riesgo_Fatal);
        /**
         *  Codigo para recibir data en los EditText
         */
        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("text");
            String spinnerValue1 = intent.getStringExtra("spinnerValue1");
            String spinnerValue2 = intent.getStringExtra("spinnerValue2");
            String spinnerValue3 = intent.getStringExtra("spinnerValue3");

            String receivedText1 = "Actividad: " + text;
            Recibe_editTextTextMultiLine.setText(receivedText1);

            String receivedText2 = "Empresa: " + spinnerValue1;
            Recibe_EditText_Empresa_DondeLabora.setText(receivedText2);

            String receivedText3 = "Area Laboral: " + spinnerValue2;
            Recibe_EditText_Area_donde_Labora.setText(receivedText3);

            String receivedText4 = "Riesgo Fatal:\n \n  " + spinnerValue3;
            Recibe_EditText_Riesgo_Fatal.setText(receivedText4);

            /**
             *  Codigo para recibir data en los EditText
             */
        }

        // Inflar vista personalizada
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialog_lider, null);
        // Establecer vista personalizada en AlertDialog.Builder
        builder.setView(view);

        btn_envioData = findViewById(R.id.btn_envioData);
        btn_envioData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Metodo_EnvioData_AlertiDialog();
               // Metodo_GenerarPDF_VehiculoTransporte();  //

                String cumpleAseguraInspeccion_si = checkBox_cumpleAseguraInspeccion.getText().toString();
                String cumpleAseguraInspeccion_no = checkBox_NO_cumpleAseguraInspeccion.getText().toString();

                String cumpleGarantizar_Cumplimiento_si = CheckBox_2_SI_Garantizar_Cumplimiento.getText().toString();
                String cumpleGarantizar_Cumplimiento_no = CheckBox_2_NO_Garantizar_Cumplimiento.getText().toString();

                String CheckBox_3_SI_Monitoreo_Ubicacion_3 = CheckBox_3_SI_Monitoreo_Ubicacion.getText().toString();
                String CheckBox_3_NO_Monitoreo_Ubicacion_3 = CheckBox_3_NO_Monitoreo_Ubicacion.getText().toString();

                String CheckBox_4_SI_Control_Alcotest_4 = CheckBox_4_SI_Control_Alcotest.getText().toString();
                String CheckBox_4_NO_Control_Alcotest_4 = CheckBox_4_NO_Control_Alcotest.getText().toString();

                String CheckBox_5_SI_Control_Somnolencia_5 = CheckBox_5_SI_Control_Somnolencia.getText().toString();
                String CheckBox_5_NO_Control_Somnolencia_5 = CheckBox_5_NO_Control_Somnolencia.getText().toString();

                String CheckBox_6_SI_Validacion_Experiencia_6 = CheckBox_6_SI_Validacion_Experiencia.getText().toString();
                String CheckBox_6_NO_Validacion_Experiencia_6 = CheckBox_6_NO_Validacion_Experiencia.getText().toString();

                String CheckBox_7_SI_Comportamiento_Riesgoso_7 = CheckBox_7_SI_Comportamiento_Riesgoso.getText().toString();
                String CheckBox_7_NO_Comportamiento_Riesgoso_7 = CheckBox_7_NO_Comportamiento_Riesgoso.getText().toString();

                String CheckBox_8_SI_Vehiculo_VIP_8 = CheckBox_8_SI_Vehiculo_VIP.getText().toString();
                String CheckBox_8_NO_Vehiculo_VIP_8 = CheckBox_8_NO_Vehiculo_VIP.getText().toString();

                String CheckBox_9_SI_Plan_Rutas_9 = CheckBox_9_SI_Plan_Rutas.getText().toString();
                String CheckBox_9_NO_Plan_Rutas_9 = CheckBox_9_NO_Plan_Rutas.getText().toString();

                try {   // todo: Metodo Creado ---- CreatePDF_Transporte
                    CreatePDF_Transporte(cumpleAseguraInspeccion_si, cumpleAseguraInspeccion_no,
                            cumpleGarantizar_Cumplimiento_si,
                            cumpleGarantizar_Cumplimiento_no,
                            CheckBox_3_SI_Monitoreo_Ubicacion_3,
                            CheckBox_3_NO_Monitoreo_Ubicacion_3,
                            CheckBox_4_SI_Control_Alcotest_4,
                            CheckBox_4_NO_Control_Alcotest_4,
                            CheckBox_5_SI_Control_Somnolencia_5,
                            CheckBox_5_NO_Control_Somnolencia_5,
                            CheckBox_6_SI_Validacion_Experiencia_6,
                            CheckBox_6_NO_Validacion_Experiencia_6,
                            CheckBox_7_SI_Comportamiento_Riesgoso_7,
                            CheckBox_7_NO_Comportamiento_Riesgoso_7,
                            CheckBox_8_SI_Vehiculo_VIP_8,
                            CheckBox_8_NO_Vehiculo_VIP_8,
                            CheckBox_9_SI_Plan_Rutas_9,
                            CheckBox_9_NO_Plan_Rutas_9);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



        /**
         *  Primer Parametro en celda de seleccion y eliminacion de Si o No depende la condicion
         */
        checkBox_cumpleAseguraInspeccion = (CheckBox) findViewById(R.id.checkBox_cumpleAseguraInspeccion);
        checkBox_NO_cumpleAseguraInspeccion = (CheckBox) findViewById(R.id.checkBox_NO_cumpleAseguraInspeccion);

        checkBox_cumpleAseguraInspeccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    // Si cumple
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Si se marca el CheckBox "Cumple"
                    checkBox_NO_cumpleAseguraInspeccion.setChecked(false);  // no
                    checkBox_cumpleAseguraInspeccion.setText("Si");         // si
                    checkBox_NO_cumpleAseguraInspeccion.setText("  ");      // no
                   // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox_NO_cumpleAseguraInspeccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {   // No cumple
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Si se marca el CheckBox "No Cumple"
                    checkBox_cumpleAseguraInspeccion.setChecked(false);      // si
                    checkBox_NO_cumpleAseguraInspeccion.setText("No");     // no
                    checkBox_cumpleAseguraInspeccion.setText("  ");         //si
                    // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         *  FIn del Primer Parametro en celda --- lo de arriba es condicional.....
         */

        /**
         * Segundo Parametro en condicional
         */
        CheckBox_2_SI_Garantizar_Cumplimiento = (CheckBox) findViewById(R.id.CheckBox_2_SI_Garantizar_Cumplimiento); // Instancia
        CheckBox_2_SI_Garantizar_Cumplimiento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked2) {
                if (isChecked2) {
                    CheckBox_2_NO_Garantizar_Cumplimiento.setChecked(false);  // no
                    CheckBox_2_SI_Garantizar_Cumplimiento.setText("Si");         // si
                    CheckBox_2_NO_Garantizar_Cumplimiento.setText("  ");
                    // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_2_NO_Garantizar_Cumplimiento = (CheckBox) findViewById(R.id.CheckBox_2_NO_Garantizar_Cumplimiento);
        CheckBox_2_NO_Garantizar_Cumplimiento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked2) {
                if (isChecked2) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_2_SI_Garantizar_Cumplimiento.setChecked(false);      // si
                    CheckBox_2_NO_Garantizar_Cumplimiento.setText("No");     // no
                    CheckBox_2_SI_Garantizar_Cumplimiento.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**
         *
         */
        CheckBox_3_SI_Monitoreo_Ubicacion = (CheckBox) findViewById(R.id.CheckBox_3_SI_Monitoreo_Ubicacion);
        CheckBox_3_NO_Monitoreo_Ubicacion = (CheckBox) findViewById(R.id.CheckBox_3_NO_Monitoreo_Ubicacion);

        CheckBox_3_SI_Monitoreo_Ubicacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked3) {
                if (isChecked3) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_3_NO_Monitoreo_Ubicacion.setChecked(false);      // si
                    CheckBox_3_SI_Monitoreo_Ubicacion.setText("Si");     // no
                    CheckBox_3_NO_Monitoreo_Ubicacion.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_3_NO_Monitoreo_Ubicacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked3) {
                if (isChecked3) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_3_SI_Monitoreo_Ubicacion.setChecked(false);      // si
                    CheckBox_3_NO_Monitoreo_Ubicacion.setText("No");     // no
                    CheckBox_3_SI_Monitoreo_Ubicacion.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_4_SI_Control_Alcotest = (CheckBox) findViewById(R.id.CheckBox_4_SI_Control_Alcotest);
        CheckBox_4_NO_Control_Alcotest = (CheckBox) findViewById(R.id.CheckBox_4_NO_Control_Alcotest);

        CheckBox_4_SI_Control_Alcotest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked4) {
                if (isChecked4) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_4_NO_Control_Alcotest.setChecked(false);      // si
                    CheckBox_4_SI_Control_Alcotest.setText("Si");     // no
                    CheckBox_4_NO_Control_Alcotest.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_4_NO_Control_Alcotest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked4) {
                if (isChecked4) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_4_SI_Control_Alcotest.setChecked(false);      // si
                    CheckBox_4_NO_Control_Alcotest.setText("No");     // no
                    CheckBox_4_SI_Control_Alcotest.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_5_SI_Control_Somnolencia = (CheckBox) findViewById(R.id.CheckBox_5_SI_Control_Somnolencia);
        CheckBox_5_NO_Control_Somnolencia = (CheckBox) findViewById(R.id.CheckBox_5_NO_Control_Somnolencia);

        CheckBox_5_SI_Control_Somnolencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked5) {
                if (isChecked5) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_5_NO_Control_Somnolencia.setChecked(false);      // si
                    CheckBox_5_SI_Control_Somnolencia.setText("Si");     // no
                    CheckBox_5_NO_Control_Somnolencia.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_5_NO_Control_Somnolencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked5) {
                if (isChecked5) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_5_SI_Control_Somnolencia.setChecked(false);      // si
                    CheckBox_5_NO_Control_Somnolencia.setText("No");     // no
                    CheckBox_5_SI_Control_Somnolencia.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_6_SI_Validacion_Experiencia = (CheckBox) findViewById(R.id.CheckBox_6_SI_Validacion_Experiencia);
        CheckBox_6_NO_Validacion_Experiencia = (CheckBox) findViewById(R.id.CheckBox_6_NO_Validacion_Experiencia);

        CheckBox_6_SI_Validacion_Experiencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked6) {
                if (isChecked6) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_6_NO_Validacion_Experiencia.setChecked(false);      // si
                    CheckBox_6_SI_Validacion_Experiencia.setText("Si");     // no
                    CheckBox_6_NO_Validacion_Experiencia.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_6_NO_Validacion_Experiencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked6) {
                if (isChecked6) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_6_SI_Validacion_Experiencia.setChecked(false);      // si
                    CheckBox_6_NO_Validacion_Experiencia.setText("No");     // no
                    CheckBox_6_SI_Validacion_Experiencia.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_7_SI_Comportamiento_Riesgoso = (CheckBox) findViewById(R.id.CheckBox_7_SI_Comportamiento_Riesgoso);
        CheckBox_7_NO_Comportamiento_Riesgoso = (CheckBox) findViewById(R.id.CheckBox_7_NO_Comportamiento_Riesgoso);

        CheckBox_7_SI_Comportamiento_Riesgoso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked7) {
                if (isChecked7) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_7_NO_Comportamiento_Riesgoso.setChecked(false);      // si
                    CheckBox_7_SI_Comportamiento_Riesgoso.setText("Si");     // no
                    CheckBox_7_NO_Comportamiento_Riesgoso.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_7_NO_Comportamiento_Riesgoso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked7) {
                if (isChecked7) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_7_SI_Comportamiento_Riesgoso.setChecked(false);      // si
                    CheckBox_7_NO_Comportamiento_Riesgoso.setText("No");     // no
                    CheckBox_7_SI_Comportamiento_Riesgoso.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_8_SI_Vehiculo_VIP = (CheckBox) findViewById(R.id.CheckBox_8_SI_Vehiculo_VIP);
        CheckBox_8_NO_Vehiculo_VIP = (CheckBox) findViewById(R.id.CheckBox_8_NO_Vehiculo_VIP);

        CheckBox_8_SI_Vehiculo_VIP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked8) {
                if (isChecked8) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_8_NO_Vehiculo_VIP.setChecked(false);      // si
                    CheckBox_8_SI_Vehiculo_VIP.setText("Si");     // no
                    CheckBox_8_NO_Vehiculo_VIP.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_8_NO_Vehiculo_VIP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked8) {
                if (isChecked8) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_8_SI_Vehiculo_VIP.setChecked(false);      // si
                    CheckBox_8_NO_Vehiculo_VIP.setText("No");     // no
                    CheckBox_8_SI_Vehiculo_VIP.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_9_SI_Plan_Rutas = (CheckBox) findViewById(R.id.CheckBox_9_SI_Plan_Rutas);
        CheckBox_9_NO_Plan_Rutas = (CheckBox) findViewById(R.id.CheckBox_9_NO_Plan_Rutas);

        CheckBox_9_SI_Plan_Rutas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked9) {
                if (isChecked9) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_9_NO_Plan_Rutas.setChecked(false);      // si
                    CheckBox_9_SI_Plan_Rutas.setText("Si");     // no
                    CheckBox_9_NO_Plan_Rutas.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox_9_NO_Plan_Rutas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked9) {
                if (isChecked9) {
                    // Si se marca el CheckBox "Cumple"
                    CheckBox_9_SI_Plan_Rutas.setChecked(false);      // si
                    CheckBox_9_NO_Plan_Rutas.setText("No");     // no
                    CheckBox_9_SI_Plan_Rutas.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Fin de condicional - 2
         *
         */

        // ================================================================================================================
     }  // Fin de OnCreate

    private void CreatePDF_Transporte(String cumpleAseguraInspeccion_si, String cumpleAseguraInspeccion_no, String cumpleGarantizar_cumplimiento_si, String cumpleGarantizar_cumplimiento_no, String checkBox_3_si_monitoreo_ubicacion_3, String checkBox_3_no_monitoreo_ubicacion_3, String checkBox_4_si_control_alcotest_4, String checkBox_4_no_control_alcotest_4, String checkBox_5_si_control_somnolencia_5, String checkBox_5_no_control_somnolencia_5, String checkBox_6_si_validacion_experiencia_6, String checkBox_6_no_validacion_experiencia_6, String checkBox_7_si_comportamiento_riesgoso_7, String checkBox_7_no_comportamiento_riesgoso_7, String checkBox_8_si_vehiculo_vip_8, String checkBox_8_no_vehiculo_vip_8, String checkBox_9_si_plan_rutas_9, String checkBox_9_no_plan_rutas_9) throws FileNotFoundException{
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Edward_Prueba.pdf");    // Asignamos el Nombre de PDF por ahora es estatico
        OutputStream outputStream = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);
        document.setMargins(0,0,0,0);

        Drawable d = getDrawable(R.drawable.accidentes_con_explosivos); // Reemplaza "accidentes_con_explosivos" con el nombre de tu imagen
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        int desiredWidth = 50;
        int desiredHeight = 40;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();
        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);

        Paragraph visitorTicket = new Paragraph("CheckList ALto Riesgo").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        float[] width = {180f, 20f, 20f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

    }


    private void Metodo_GenerarPDF_VehiculoTransporte() {

} // Fin de Vehiculo Transporte


    private void Metodo_EnvioData_AlertiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Vehiculo_Trasporte_Personal_Activity.this);

        builder.setMessage("AlertDialog")
                .setTitle("Alert Dialog");

       builder.setPositiveButton("Sii", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Prueba1sss111", Toast.LENGTH_SHORT).show();
              // Intent intent = new Intent(Vehiculo_Trasporte_Personal_Activity.this, alertdialog_lider.); //Login_Activity
               //startActivity(intent);
           }
       });

       builder.setNegativeButton("Noo", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Toast.makeText(Vehiculo_Trasporte_Personal_Activity.this, "Pruebaaaaa", Toast.LENGTH_SHORT).show();
           }
       });

    AlertDialog dialog = builder.create();
    dialog.show();

    } // FIn del alertDialog

    // Codigo de Menu a la Derecha --- cangreburger
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lider_riesgo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_liderR) {
            Toast.makeText(this, "Opciones Principales", Toast.LENGTH_SHORT).show();

        }//else if (id == R.id.Utilidad2) {
            //para utilizar
           // Toast.makeText(this, "Opcion2", Toast.LENGTH_SHORT).show();
       // }
        return true;
       // return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}