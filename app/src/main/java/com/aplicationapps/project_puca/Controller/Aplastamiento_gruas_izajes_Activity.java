package com.aplicationapps.project_puca.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplicationapps.project_puca.R;
import com.aplicationapps.project_puca.View.RegisterLogin_Activity;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Aplastamiento_gruas_izajes_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private static final int PROGRESS_BAR_DELAY = 3000;

    Toolbar toolbar;
    Button BTN_Aplastamiento_Data;
    private ProgressBar progressBar;

    TextView TextView_Correcta_Condicion;
    CheckBox CheckBox_Correcta_Condicion_Si;
    CheckBox CheckBox_Correcta_Condicion_No;

    TextView TextView_Cumplimiento_Mantenimiento;
    CheckBox CheckBox_Cumplimiento_Mantenimiento_Si;
    CheckBox CheckBox_Cumplimiento_Mantenimiento_No;

    TextView TextView_Capacitacion_operador;
    CheckBox CheckBox_Capacitacion_operador_si;
    CheckBox CheckBox_Capacitacion_operador_No;

    TextView TextView_Plan_Izaje_PETAR;
    CheckBox CheckBox_Plan_Izaje_PETAR_Si;
    CheckBox CheckBox_Plan_Izaje_PETAR_No;

    TextView TextView_Velocidad_Viento;
    CheckBox CheckBox_Velocidad_Viento_Si;
    CheckBox CheckBox_Velocidad_Viento_No;

    TextView TextView_Area_Critica;
    CheckBox CheckBox_Area_Critica_Si;
    CheckBox CheckBox_Area_Critica_No;

    private EditText Recibe_editTextTextMultiLine;
    private EditText Recibe_EditText_Empresa_DondeLabora;
    private EditText Recibe_EditText_Area_donde_Labora;
    private EditText Recibe_EditText_Riesgo_Fatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplastamiento_gruas_izajes);
        // Este activity saca PDF solo falta hacer el envio de data eso tienes que resolverlo

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setTitle("Aplastamiento Gruas e Izajes");

        /**
         * Referencias de Edittect que enviaran data
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

            String receivedText4 = "Riesgo Fatal: " + spinnerValue3;
            Recibe_EditText_Riesgo_Fatal.setText(receivedText4);

            /**
             *  Codigo para recibir data en los EditText
             */
        }

        /**
         * Referenciamos los EditText que usaremos
         */
        TextView_Correcta_Condicion = findViewById(R.id.TextView_Correcta_Condicion);
        CheckBox_Correcta_Condicion_Si = (CheckBox) findViewById(R.id.CheckBox_Correcta_Condicion_Si);
        CheckBox_Correcta_Condicion_No = (CheckBox) findViewById(R.id.CheckBox_Correcta_Condicion_No);

        TextView_Cumplimiento_Mantenimiento = findViewById(R.id.TextView_Cumplimiento_Mantenimiento);
        CheckBox_Cumplimiento_Mantenimiento_Si = (CheckBox) findViewById(R.id.CheckBox_Cumplimiento_Mantenimiento_Si);
        CheckBox_Cumplimiento_Mantenimiento_No = (CheckBox) findViewById(R.id.CheckBox_Cumplimiento_Mantenimiento_No);

        TextView_Capacitacion_operador = findViewById(R.id.TextView_Capacitacion_operador);
        CheckBox_Capacitacion_operador_si = (CheckBox) findViewById(R.id.CheckBox_Capacitacion_operador_si);
        CheckBox_Capacitacion_operador_No = (CheckBox) findViewById(R.id.CheckBox_Capacitacion_operador_No);

        TextView_Plan_Izaje_PETAR = findViewById(R.id.TextView_Plan_Izaje_PETAR);
        CheckBox_Plan_Izaje_PETAR_Si = (CheckBox) findViewById(R.id.CheckBox_Plan_Izaje_PETAR_Si);
        CheckBox_Plan_Izaje_PETAR_No = (CheckBox) findViewById(R.id.CheckBox_Plan_Izaje_PETAR_No);

        TextView_Velocidad_Viento = findViewById(R.id.TextView_Velocidad_Viento);
        CheckBox_Velocidad_Viento_Si = (CheckBox) findViewById(R.id.CheckBox_Velocidad_Viento_Si);
        CheckBox_Velocidad_Viento_No = (CheckBox) findViewById(R.id.CheckBox_Velocidad_Viento_No);

        TextView_Area_Critica = findViewById(R.id.TextView_Area_Critica);
        CheckBox_Area_Critica_Si = (CheckBox) findViewById(R.id.CheckBox_Area_Critica_Si);
        CheckBox_Area_Critica_No = (CheckBox) findViewById(R.id.CheckBox_Area_Critica_No);

        /**
         * Final de referencia de EditText que se enviara a otro data
         */

        /**
         * Empieza hacia abajo la Logica condicional  SI y NO ===========================================================================================================
         */

        CheckBox_Correcta_Condicion_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_Correcta_Condicion_No.setChecked(false);
                    CheckBox_Correcta_Condicion_Si.setText("Si");
                    CheckBox_Correcta_Condicion_No.setText("  ");
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_Correcta_Condicion_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Correcta_Condicion_Si.setChecked(false);
                    CheckBox_Correcta_Condicion_No.setText("No");
                    CheckBox_Correcta_Condicion_Si.setText("  ");
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_Cumplimiento_Mantenimiento_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // CheckBox_Cumplimiento_Mantenimiento_No CheckBox_Cumplimiento_Mantenimiento_Si
                    CheckBox_Cumplimiento_Mantenimiento_No.setChecked(false);
                    CheckBox_Cumplimiento_Mantenimiento_Si.setText("Si");         // si
                    CheckBox_Cumplimiento_Mantenimiento_No.setText("  ");
                }
            }
        });
        CheckBox_Cumplimiento_Mantenimiento_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Cumplimiento_Mantenimiento_Si.setChecked(false);      // si
                    CheckBox_Cumplimiento_Mantenimiento_No.setText("No");     // no
                    CheckBox_Cumplimiento_Mantenimiento_Si.setText("  ");         //si
                    // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        }); // hasta este momento dos secciones se realizaron

        CheckBox_Capacitacion_operador_si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // CheckBox_Capacitacion_operador_No  CheckBox_Capacitacion_operador_si
                if (isChecked) {
                    CheckBox_Capacitacion_operador_No.setChecked(false);  // no
                    CheckBox_Capacitacion_operador_si.setText("Si");         // si
                    CheckBox_Capacitacion_operador_No.setText("  ");
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CheckBox_Capacitacion_operador_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Capacitacion_operador_si.setChecked(false);      // si
                    CheckBox_Capacitacion_operador_No.setText("No");     // no
                    CheckBox_Capacitacion_operador_si.setText("  ");         //si
                    // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        }); // tercer bloque de pregunta check List Si y no

        // CheckBox_Plan_Izaje_PETAR_Si   CheckBox_Plan_Izaje_PETAR_No
        CheckBox_Plan_Izaje_PETAR_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Plan_Izaje_PETAR_No.setChecked(false);  // no
                    CheckBox_Plan_Izaje_PETAR_Si.setText("Si");         // si
                    CheckBox_Plan_Izaje_PETAR_No.setText("  ");      // no
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        }); // CheckBox_Plan_Izaje_PETAR_Si   CheckBox_Plan_Izaje_PETAR_No
        CheckBox_Plan_Izaje_PETAR_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Plan_Izaje_PETAR_Si.setChecked(false);      // si
                    CheckBox_Plan_Izaje_PETAR_No.setText("No");     // no
                    CheckBox_Plan_Izaje_PETAR_Si.setText("  ");         //si
                    // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });  // 4ta pregunta terminada

        // CheckBox_Velocidad_Viento_Si  CheckBox_Velocidad_Viento_No
        CheckBox_Velocidad_Viento_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Velocidad_Viento_No.setChecked(false);  // no
                    CheckBox_Velocidad_Viento_Si.setText("Si");         // si
                    CheckBox_Velocidad_Viento_No.setText("  ");      // no
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CheckBox_Velocidad_Viento_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Velocidad_Viento_Si.setChecked(false);      // si
                    CheckBox_Velocidad_Viento_No.setText("No");     // no
                    CheckBox_Velocidad_Viento_Si.setText("  ");
                }
            }
        });  //  fin de quinta pregunta

        // CheckBox_Area_Critica_Si     CheckBox_Area_Critica_No
        CheckBox_Area_Critica_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Area_Critica_No.setChecked(false);  // no
                    CheckBox_Area_Critica_Si.setText("Si");         // si
                    CheckBox_Area_Critica_No.setText("  ");      // no
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // CheckBox_Area_Critica_Si     CheckBox_Area_Critica_No
        CheckBox_Area_Critica_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Area_Critica_Si.setChecked(false);      // si
                    CheckBox_Area_Critica_No.setText("No");     // no
                    CheckBox_Area_Critica_Si.setText("  ");     // no
                    // checkBox_cumpleAseguraInspeccion.setText("Nosss");// Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BTN_Aplastamiento_Data = findViewById(R.id.BTN_Aplastamiento_Data);
        BTN_Aplastamiento_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EjecutarCondiciones_Metodos_Gruas_Izajes();

                /**
                 * ///////////////////////////////////////////////////////////////////////////////////
                 */
                // TODO: 25/05/2023 cambiar IP -- es dinamico
               // Envio_data_AplastamientoData("http://192.168.0.9/Web_App_Checklist/API/Data_Lista_app/data_aplastamientoGruas.php");
            }
        });

    }

    private void EjecutarCondiciones_Metodos_Gruas_Izajes() {
        if (checkPermission()) {
            Ejecuciones_createPdfWithPermission();

        } else {
            requestPermission();
        }

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
    }


    private void Ejecuciones_createPdfWithPermission() {
        /**
         * Procedimiento para crear PDF
         */
        // TODO: 15/06/2023  Procedimiento para Crear PDF
        String TextView_Correcta_Condicion11 = TextView_Correcta_Condicion.getText().toString();
        String CheckBox_Correcta_Condicion_Si_11 = (CheckBox_Correcta_Condicion_Si.getText().toString());
        String CheckBox_Correcta_Condicion_No_11 = (CheckBox_Correcta_Condicion_No.getText().toString());

        String TextView_Cumplimiento_Mantenimiento22 = TextView_Cumplimiento_Mantenimiento.getText().toString();
        String CheckBox_Cumplimiento_Mantenimiento_Si22 = (CheckBox_Cumplimiento_Mantenimiento_Si.getText().toString());
        String CheckBox_Cumplimiento_Mantenimiento_No22 = (CheckBox_Cumplimiento_Mantenimiento_No.getText().toString());

        String TextView_Capacitacion_operador33 = TextView_Capacitacion_operador.getText().toString();
        String CheckBox_Capacitacion_operador_si33 = (CheckBox_Capacitacion_operador_si.getText().toString());
        String CheckBox_Capacitacion_operador_No33 = (CheckBox_Capacitacion_operador_No.getText().toString());

        String TextView_Plan_Izaje_PETAR44 = TextView_Plan_Izaje_PETAR.getText().toString();
        String CheckBox_Plan_Izaje_PETAR_Si44 = (CheckBox_Plan_Izaje_PETAR_Si.getText().toString());
        String CheckBox_Plan_Izaje_PETAR_No44 = (CheckBox_Plan_Izaje_PETAR_No.getText().toString());

        String TextView_Velocidad_Viento55 = TextView_Velocidad_Viento.getText().toString();
        String CheckBox_Velocidad_Viento_Si55 = (CheckBox_Velocidad_Viento_Si.getText().toString());
        String CheckBox_Velocidad_Viento_No55 = (CheckBox_Velocidad_Viento_No.getText().toString());

        String TextView_Area_Critica66 = TextView_Area_Critica.getText().toString();
        String CheckBox_Area_Critica_Si66 = (CheckBox_Area_Critica_Si.getText().toString());
        String CheckBox_Area_Critica_No66 = (CheckBox_Area_Critica_No.getText().toString());

        String Recibe_editTextTextMultiLine7 = Recibe_editTextTextMultiLine.getText().toString();
        String Recibe_EditText_Empresa_DondeLabora7 = Recibe_EditText_Empresa_DondeLabora.getText().toString();
        String Recibe_EditText_Area_donde_Labora7 = Recibe_EditText_Area_donde_Labora.getText().toString();
        String Recibe_EditText_Riesgo_Fatal7 = Recibe_EditText_Riesgo_Fatal.getText().toString();

        createPdfWithPermission(TextView_Correcta_Condicion11,CheckBox_Correcta_Condicion_Si_11,CheckBox_Correcta_Condicion_No_11,
                                TextView_Cumplimiento_Mantenimiento22,CheckBox_Cumplimiento_Mantenimiento_Si22,CheckBox_Cumplimiento_Mantenimiento_No22,
                TextView_Capacitacion_operador33,CheckBox_Capacitacion_operador_si33,CheckBox_Capacitacion_operador_No33,
                TextView_Plan_Izaje_PETAR44,CheckBox_Plan_Izaje_PETAR_Si44,CheckBox_Plan_Izaje_PETAR_No44,
                TextView_Velocidad_Viento55,CheckBox_Velocidad_Viento_Si55,CheckBox_Velocidad_Viento_No55,
                TextView_Area_Critica66,CheckBox_Area_Critica_Si66,CheckBox_Area_Critica_No66,
                Recibe_editTextTextMultiLine7,Recibe_EditText_Empresa_DondeLabora7,Recibe_EditText_Area_donde_Labora7,Recibe_EditText_Riesgo_Fatal7);
    }

    private void createPdfWithPermission(String TextView_Correcta_Condicion, String CheckBox_Correcta_Condicion_Si, String CheckBox_Correcta_Condicion_No,
                                         String TextView_Cumplimiento_Mantenimiento, String CheckBox_Cumplimiento_Mantenimiento_Si, String CheckBox_Cumplimiento_Mantenimiento_No,
                                         String TextView_Capacitacion_operador, String CheckBox_Capacitacion_operador_si, String CheckBox_Capacitacion_operador_No,
                                         String TextView_Plan_Izaje_PETAR, String CheckBox_Plan_Izaje_PETAR_Si, String CheckBox_Plan_Izaje_PETAR_No,
                                         String TextView_Velocidad_Viento, String CheckBox_Velocidad_Viento_Si, String CheckBox_Velocidad_Viento_No,
                                         String TextView_Area_Critica, String CheckBox_Area_Critica_Si, String CheckBox_Area_Critica_No,
                                         String Recibe_editTextTextMultiLine, String Recibe_EditText_Empresa_DondeLabora, String Recibe_EditText_Area_donde_Labora, String Recibe_EditText_Riesgo_Fatal
    )
    {
        String fileName = generateFileName();      // Generar PDF

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileName));
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            Drawable d = getDrawable(R.drawable.riesgos_criticos_10);
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            int desiredWidth = 50;
            int desiredHeight = 40;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapData = stream.toByteArray();
            ImageData imageData = ImageDataFactory.create(bitmapData);
            Image image = new Image(imageData);

            Paragraph visitorTicket = new Paragraph("Accidente con Explosivos").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER);

            float[] width = {200f, 50f, 50f};
            Table table = new Table(width);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Correcta_Condicion).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Correcta_Condicion_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Correcta_Condicion_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Cumplimiento_Mantenimiento).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Cumplimiento_Mantenimiento_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Cumplimiento_Mantenimiento_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Capacitacion_operador).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Capacitacion_operador_si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Capacitacion_operador_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Plan_Izaje_PETAR).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Plan_Izaje_PETAR_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Plan_Izaje_PETAR_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Velocidad_Viento).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Velocidad_Viento_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Velocidad_Viento_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Area_Critica).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Area_Critica_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Area_Critica_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            float[] widthd = {200f};
            Table tablePrimary = new Table(widthd);
            tablePrimary.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph TituloActividad1 = new Paragraph("\n").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
            Paragraph TituloActividad = new Paragraph("Actividades a Realizar").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);


             tablePrimary.addCell(new Cell().add(new Paragraph(Recibe_editTextTextMultiLine).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
             tablePrimary.addCell(new Cell().add(new Paragraph(Recibe_EditText_Empresa_DondeLabora).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
             tablePrimary.addCell(new Cell().add(new Paragraph(Recibe_EditText_Area_donde_Labora).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
             tablePrimary.addCell(new Cell().add(new Paragraph(Recibe_EditText_Riesgo_Fatal).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            Paragraph TituloActividad2 = new Paragraph("\n").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);

            float[] widths = {30f, 30f};
            Table tables = new Table(widths);
            tables.setHorizontalAlignment(HorizontalAlignment.CENTER);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tables.addCell(new Cell().add(new Paragraph("Dia Realizado:").setFontSize(12)));
            tables.addCell(new Cell().add(new Paragraph(LocalDate.now().format(dateFormatter).toString()).setFontSize(12)));

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
            tables.addCell(new Cell().add(new Paragraph("Tiempo:").setFontSize(12)));
            tables.addCell(new Cell().add(new Paragraph(LocalTime.now().format(timeFormatter).toString()).setFontSize(12)));

            document.add(image);
            document.add(visitorTicket);
            document.add(table);
            document.add(TituloActividad1);
            document.add(TituloActividad);
            document.add(tablePrimary);
            document.add(TituloActividad2);
            document.add(tables);

            document.close();
            // progressBar.setVisibility(View.GONE);

            Toast.makeText(this, "PDF guardado exitosamente", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //  progressBar.setVisibility(View.GONE);
                }
            }, PROGRESS_BAR_DELAY);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "Gruas_Izajes_PDF_" + timeStamp + ".pdf";
        String folderPath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator + "Aplastamiento_Gruas_Izajes_PDF";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        int count = 1;
        while (new File(folder, fileName).exists()) {
            fileName = "PDF_" + timeStamp + "(" + count + ").pdf";
            count++;
        }

        return folderPath + File.separator + fileName;
    }



    /**
     * Procedimiento para cambiar datos
     * @param URL
     */
    private void Envio_data_AplastamientoData(String URL) {
        // TODO: 25/05/2023  - Metodo de Envio 
        // Enviar informarcion de la activity a la DB
        // Metodo HTTP Volley
       // Toast.makeText(this, "Prueba de Metodo Notifdicacion ", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registro Exitoso CheckList Gruas Izajes - DB", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Falta Implementar el Error de Escucha haremos un poco de web....
                Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Aplastamiento_gruas_izajes_Activity.this, "Se Ocasiono un error Inesperado, revise su Conexión !!!", Toast.LENGTH_SHORT).show();
            }
        })  {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<>();

                // Falta 23/05/2023 --- 20:09
                /**
                 *
                 */
                parametros.put("correcta_condicion_tb", TextView_Correcta_Condicion.getText().toString());    // ok en BD
                parametros.put("correcta_condicion_tb_SI", CheckBox_Correcta_Condicion_Si.getText().toString());           // ok en BD
                parametros.put("correcta_condicion_tb_NO", CheckBox_Correcta_Condicion_No.getText().toString());           // ok en BD

                parametros.put("asegurar_cumplimiento_tb", TextView_Cumplimiento_Mantenimiento.getText().toString());
                parametros.put("asegurar_cumplimiento_tb_SI", CheckBox_Cumplimiento_Mantenimiento_Si.getText().toString());
                parametros.put("asegurar_cumplimiento_tb_NO", CheckBox_Cumplimiento_Mantenimiento_No.getText().toString());

                parametros.put("capacitar_operador_tb", TextView_Capacitacion_operador.getText().toString());
                parametros.put("capacitar_operador_tb_SI", CheckBox_Capacitacion_operador_si.getText().toString());
                parametros.put("capacitar_operador_tb_NO", CheckBox_Capacitacion_operador_No.getText().toString());

                parametros.put("plan_izaje_petar_tb", TextView_Plan_Izaje_PETAR.getText().toString());
                parametros.put("plan_izaje_petar_tb_SI", CheckBox_Plan_Izaje_PETAR_Si.getText().toString());
                parametros.put("plan_izaje_petar_tb_NO", CheckBox_Plan_Izaje_PETAR_No.getText().toString());

                parametros.put("velocidad_viento_tb", TextView_Velocidad_Viento.getText().toString());
                parametros.put("velocidad_viento_tb_SI", CheckBox_Velocidad_Viento_Si.getText().toString());
                parametros.put("velocidad_viento_tb_NO", CheckBox_Velocidad_Viento_No.getText().toString());

                parametros.put("linea_fuego_pers_tb", TextView_Area_Critica.getText().toString());
                parametros.put("linea_fuego_pers_tb_SI", CheckBox_Area_Critica_Si.getText().toString());
                parametros.put("linea_fuego_pers_tb_NO", CheckBox_Area_Critica_No.getText().toString());
                /**
                 *
                 */
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        /**
         * Metodo Volley Http
         */
    }

    /**
     * //////////////////////////////////////////////////////////////////////////////////////////////////////
     */
/**
 * //////////////////////////////////////////////////////////////////////////////////////////////////////
 */
    /**
     * //////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    /**
     * //////////////////////////////////////////////////////////////////////////////////////////////////////
     */

    /**
     * la Barrita de opciones al lado derecho de la interfaz
     * @param
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed(); // O realiza cualquier acción que desees al presionar la flecha
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int writeExternalStoragePermission = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lider_riesgo, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_liderR) {
            Toast.makeText(this, "Opciones Principales", Toast.LENGTH_SHORT).show();

        } //else if (id == R.id.Utilidad2) {
            //para utilizar
            //Toast.makeText(this, "Opcion2", Toast.LENGTH_SHORT).show();
       // }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}