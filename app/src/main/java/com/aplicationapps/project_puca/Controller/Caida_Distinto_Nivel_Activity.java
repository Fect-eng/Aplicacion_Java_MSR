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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.aplicationapps.project_puca.R;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Caida_Distinto_Nivel_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private static final int PROGRESS_BAR_DELAY = 3000;

    Toolbar toolbar;
    private ProgressBar progressBar;
    Button btn_CaidaNivel_Data;

    private EditText Recibe_editTextTextMultiLine;
    private EditText Recibe_EditText_Empresa_DondeLabora;
    private EditText Recibe_EditText_Area_donde_Labora;
    private EditText Recibe_EditText_Riesgo_Fatal;

    TextView TextView_PersonalCapacitado_Autorizado_1;
    CheckBox CheckBox_PersonalCapacitado_Autorizado_1_SI;
    CheckBox CheckBox_PersonalCapacitado_Autorizado_1_NO;

    TextView TextView_Procedimiento_Estandar_2;
    CheckBox CheckBox_Procedimiento_Estandar_2_SI;
    CheckBox CheckBox_Procedimiento_Estandar_2_NO;

    TextView TextView_Anclase_Certificado_3;
    CheckBox CheckBox_TextView_Anclase_Certificado_3_Si;
    CheckBox CheckBox_TextView_Anclase_Certificado_3_No;

    TextView TextView_Certificados_Operativos_4;
    CheckBox CheckBox_Certificados_Operativos_4_Si;
    CheckBox CheckBox_Certificados_Operativos_4_No;

    TextView TextView_Plataforma_Trabajo_Seguro_5;
    CheckBox CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI;
    CheckBox CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No;

    TextView TextView_Actividades_a_realizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caida_distinto_nivel);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Caida Distinto Nivel");

        Recibe_editTextTextMultiLine = findViewById(R.id.Recibe_editTextTextMultiLine);
        Recibe_EditText_Empresa_DondeLabora = findViewById(R.id.Recibe_EditText_Empresa_DondeLabora);
        Recibe_EditText_Area_donde_Labora = findViewById(R.id.Recibe_EditText_Area_donde_Labora);
        Recibe_EditText_Riesgo_Fatal = findViewById(R.id.Recibe_EditText_Riesgo_Fatal);

        TextView_Actividades_a_realizar = findViewById(R.id.TextView_Actividades_a_realizar);


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

        }    // Termino de If condicional Java

        TextView_PersonalCapacitado_Autorizado_1 = findViewById(R.id.TextView_PersonalCapacitado_Autorizado_1);
        CheckBox_PersonalCapacitado_Autorizado_1_SI = findViewById(R.id.CheckBox_PersonalCapacitado_Autorizado_1_SI);
        CheckBox_PersonalCapacitado_Autorizado_1_NO = findViewById(R.id.CheckBox_PersonalCapacitado_Autorizado_1_NO);

        TextView_Procedimiento_Estandar_2 = findViewById(R.id.TextView_Procedimiento_Estandar_2);
        CheckBox_Procedimiento_Estandar_2_SI = findViewById(R.id.CheckBox_Procedimiento_Estandar_2_SI);
        CheckBox_Procedimiento_Estandar_2_NO = findViewById(R.id.CheckBox_Procedimiento_Estandar_2_NO);

        TextView_Anclase_Certificado_3 = findViewById(R.id.TextView_Anclase_Certificado_3);
        CheckBox_TextView_Anclase_Certificado_3_Si = findViewById(R.id.CheckBox_TextView_Anclase_Certificado_3_Si);
        CheckBox_TextView_Anclase_Certificado_3_No = findViewById(R.id.CheckBox_TextView_Anclase_Certificado_3_No);

        TextView_Certificados_Operativos_4 = findViewById(R.id.TextView_Certificados_Operativos_4);
        CheckBox_Certificados_Operativos_4_Si = findViewById(R.id.CheckBox_Certificados_Operativos_4_Si);
        CheckBox_Certificados_Operativos_4_No = findViewById(R.id.CheckBox_Certificados_Operativos_4_No);

        TextView_Plataforma_Trabajo_Seguro_5 = findViewById(R.id.TextView_Plataforma_Trabajo_Seguro_5);
        CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI = findViewById(R.id.CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI);
        CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No = findViewById(R.id.CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No);

       // progressBar = findViewById(R.id.progressBar);

        CheckBox_PersonalCapacitado_Autorizado_1_SI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_PersonalCapacitado_Autorizado_1_NO.setChecked(false);
                    CheckBox_PersonalCapacitado_Autorizado_1_SI.setText("Si");
                    CheckBox_PersonalCapacitado_Autorizado_1_NO.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_PersonalCapacitado_Autorizado_1_NO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_PersonalCapacitado_Autorizado_1_SI.setChecked(false);
                    CheckBox_PersonalCapacitado_Autorizado_1_NO.setText("No");
                    CheckBox_PersonalCapacitado_Autorizado_1_SI.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_Procedimiento_Estandar_2_SI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_Procedimiento_Estandar_2_NO.setChecked(false);
                    CheckBox_Procedimiento_Estandar_2_SI.setText("Si");
                    CheckBox_Procedimiento_Estandar_2_NO.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_Procedimiento_Estandar_2_NO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Procedimiento_Estandar_2_SI.setChecked(false);
                    CheckBox_Procedimiento_Estandar_2_NO.setText("No");
                    CheckBox_Procedimiento_Estandar_2_SI.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_TextView_Anclase_Certificado_3_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_TextView_Anclase_Certificado_3_No.setChecked(false);
                    CheckBox_TextView_Anclase_Certificado_3_Si.setText("Si");
                    CheckBox_TextView_Anclase_Certificado_3_No.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_TextView_Anclase_Certificado_3_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_TextView_Anclase_Certificado_3_Si.setChecked(false);
                    CheckBox_TextView_Anclase_Certificado_3_No.setText("No");
                    CheckBox_TextView_Anclase_Certificado_3_Si.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_Certificados_Operativos_4_Si.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_Certificados_Operativos_4_No.setChecked(false);
                    CheckBox_Certificados_Operativos_4_Si.setText("Si");
                    CheckBox_Certificados_Operativos_4_No.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_Certificados_Operativos_4_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_Certificados_Operativos_4_Si.setChecked(false);
                    CheckBox_Certificados_Operativos_4_No.setText("No");
                    CheckBox_Certificados_Operativos_4_Si.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No.setChecked(false);
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI.setText("Si");
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI.setChecked(false);
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No.setText("No");
                    CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI.setText("  ");
                    Toast.makeText(Caida_Distinto_Nivel_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_CaidaNivel_Data = findViewById(R.id.btn_CaidaNivel_Data);
        btn_CaidaNivel_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EjecutarCondiciones_DistintoNivel();
            }
        });
    }

    private void EjecutarCondiciones_DistintoNivel() {
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
        String TextView_PersonalCapacitado_Autorizado_1_1 = TextView_PersonalCapacitado_Autorizado_1.getText().toString();
        String CheckBox_PersonalCapacitado_Autorizado_1_SI_1 = (CheckBox_PersonalCapacitado_Autorizado_1_SI.getText().toString());
        String CheckBox_PersonalCapacitado_Autorizado_1_NO_1 = (CheckBox_PersonalCapacitado_Autorizado_1_NO.getText().toString());

        String TextView_Procedimiento_Estandar_22 = TextView_Procedimiento_Estandar_2.getText().toString();
        String CheckBox_Procedimiento_Estandar_2_SI2 = (CheckBox_Procedimiento_Estandar_2_SI.getText().toString());
        String CheckBox_Procedimiento_Estandar_2_NO2 = (CheckBox_Procedimiento_Estandar_2_NO.getText().toString());

        String TextView_Anclase_Certificado_33 = TextView_Anclase_Certificado_3.getText().toString();
        String CheckBox_TextView_Anclase_Certificado_3_Si3 = (CheckBox_TextView_Anclase_Certificado_3_Si.getText().toString());
        String CheckBox_TextView_Anclase_Certificado_3_No3 = (CheckBox_TextView_Anclase_Certificado_3_No.getText().toString());

        String TextView_Certificados_Operativos_44 = TextView_Certificados_Operativos_4.getText().toString();
        String CheckBox_Certificados_Operativos_4_Si4 = (CheckBox_Certificados_Operativos_4_Si.getText().toString());
        String CheckBox_Certificados_Operativos_4_No4 = (CheckBox_Certificados_Operativos_4_No.getText().toString());

        String TextView_Plataforma_Trabajo_Seguro_55 = TextView_Plataforma_Trabajo_Seguro_5.getText().toString();
        String CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI5 = (CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI.getText().toString());
        String CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No5 = (CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No.getText().toString());

       // String Recibe_editTextTextMultiLine1 = TextView_Actividades_a_realizar.getText().toString();
        String Recibe_editTextTextMultiLine2 = Recibe_editTextTextMultiLine.getText().toString();
        String Recibe_EditText_Empresa_DondeLabora3 = Recibe_EditText_Empresa_DondeLabora.getText().toString();
        String Recibe_EditText_Area_donde_Labora4 = Recibe_EditText_Area_donde_Labora.getText().toString();
        String Recibe_EditText_Riesgo_Fatal5 = Recibe_EditText_Riesgo_Fatal.getText().toString();

        createPdfWithPermission(Recibe_editTextTextMultiLine2,Recibe_EditText_Empresa_DondeLabora3,Recibe_EditText_Area_donde_Labora4,Recibe_EditText_Riesgo_Fatal5,TextView_PersonalCapacitado_Autorizado_1_1, CheckBox_PersonalCapacitado_Autorizado_1_SI_1, CheckBox_PersonalCapacitado_Autorizado_1_NO_1,
                TextView_Procedimiento_Estandar_22,CheckBox_Procedimiento_Estandar_2_SI2,CheckBox_Procedimiento_Estandar_2_NO2,
                TextView_Anclase_Certificado_33,CheckBox_TextView_Anclase_Certificado_3_Si3,CheckBox_TextView_Anclase_Certificado_3_No3,
                TextView_Certificados_Operativos_44,CheckBox_Certificados_Operativos_4_Si4,CheckBox_Certificados_Operativos_4_No4,
                TextView_Plataforma_Trabajo_Seguro_55,CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI5,CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No5
        );
    }

    private void createPdfWithPermission( String Recibe_editTextTextMultiLine, String Recibe_EditText_Empresa_DondeLabora, String Recibe_EditText_Area_donde_Labora, String Recibe_EditText_Riesgo_Fatal,
                                         String TextView_PersonalCapacitado_Autorizado_1, String CheckBox_PersonalCapacitado_Autorizado_1_SI, String CheckBox_PersonalCapacitado_Autorizado_1_NO,
                                         String TextView_Procedimiento_Estandar_2, String CheckBox_Procedimiento_Estandar_2_SI, String CheckBox_Procedimiento_Estandar_2_NO,
                                         String TextView_Anclase_Certificado_3, String CheckBox_TextView_Anclase_Certificado_3_Si, String CheckBox_TextView_Anclase_Certificado_3_No,
                                         String TextView_Certificados_Operativos_4, String CheckBox_Certificados_Operativos_4_Si, String CheckBox_Certificados_Operativos_4_No,
                                         String TextView_Plataforma_Trabajo_Seguro_5, String CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI, String CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No
                                        )
    {
        String fileName = generateFileName();      // Generar PDF

      //  progressBar.setVisibility(View.VISIBLE);

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
            table.addCell(new Cell().add(new Paragraph(TextView_PersonalCapacitado_Autorizado_1).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_PersonalCapacitado_Autorizado_1_SI).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_PersonalCapacitado_Autorizado_1_NO).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Procedimiento_Estandar_2).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Procedimiento_Estandar_2_SI).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Procedimiento_Estandar_2_NO).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Anclase_Certificado_3).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_TextView_Anclase_Certificado_3_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_TextView_Anclase_Certificado_3_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Certificados_Operativos_4).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Certificados_Operativos_4_Si).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_Certificados_Operativos_4_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Plataforma_Trabajo_Seguro_5).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_TextView_Plataforma_Trabajo_Seguro_5_SI).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_TextView_Plataforma_Trabajo_Seguro_5_No).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

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
        // FIn de TryCatch

    }

    private String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "Caida_Distinto_Nivel_PDF_" + timeStamp + ".pdf";
        String folderPath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator + "Caida_Distinto_Nivel_PDF";

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}