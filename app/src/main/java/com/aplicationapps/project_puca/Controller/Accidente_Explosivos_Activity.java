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


import com.aplicationapps.project_puca.R;
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
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Accidente_Explosivos_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private static final int PROGRESS_BAR_DELAY = 3000;

    Toolbar toolbar;

    private EditText Recibe_editTextTextMultiLine;
    private EditText Recibe_EditText_Empresa_DondeLabora;
    private EditText Recibe_EditText_Area_donde_Labora;
    private EditText Recibe_EditText_Riesgo_Fatal;
    TextView TextView_Actividades_Realizar;

    TextView TextView_Plan_Voladura_1;
    CheckBox checkBox_1_Si_Plan_Voladura;
    CheckBox checkBox_1_No_Plan_Voladura;

    TextView TextView_Flujo_Comunicaciones_2;
    CheckBox CheckBox_2_SI_Flujo_Comunicaciones;
    CheckBox CheckBox_2_NO_Flujo_Comunicaciones;

    TextView TextView_Explosivos_Accesorios_3;
    CheckBox CheckBox_3_SI_Explosivos_Accesorios;
    CheckBox CheckBox_3_NO_Explosivos_Accesorios;

    TextView TextView_Calificado_SUCAMEC_4;
    CheckBox CheckBox_4_SI_Calificado_SUCAMEC;
    CheckBox CheckBox_4_NO_Calificado_SUCAMEC;

    TextView TextView_Restriccion_zona_voladura_5;
    CheckBox CheckBox_5_SI_Restriccion_zona_voladura;
    CheckBox CheckBox_5_NO_Restriccion_zona_voladura;

    private ProgressBar progressBar;
    Button BTN_ExplosivosData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accidente_explosivos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#0A708A")); //Color de Toolbar diferente
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //Color de Texto Blanco
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Accidente Explosivos");

        Recibe_editTextTextMultiLine = findViewById(R.id.Recibe_editTextTextMultiLine);
        Recibe_EditText_Empresa_DondeLabora = findViewById(R.id.Recibe_EditText_Empresa_DondeLabora);
        Recibe_EditText_Area_donde_Labora = findViewById(R.id.Recibe_EditText_Area_donde_Labora);
        Recibe_EditText_Riesgo_Fatal = findViewById(R.id.Recibe_EditText_Riesgo_Fatal);
        TextView_Actividades_Realizar = findViewById(R.id.TextView_Actividades_Realizar);

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

            String receivedText4 = "Riesgo Fatal: \n" + spinnerValue3;
            Recibe_EditText_Riesgo_Fatal.setText(receivedText4);

            /**
             *  Codigo para recibir data en los EditText
             */
        } // fin de If condicional para recibir data de editText

        /**
         * Referenciamos los TextView y CheckButton que se configurara, configurados
         */
        TextView_Plan_Voladura_1 = findViewById(R.id.TextView_Plan_Voladura_1);
        checkBox_1_Si_Plan_Voladura = findViewById(R.id.checkBox_1_Si_Plan_Voladura);
        checkBox_1_No_Plan_Voladura = findViewById(R.id.checkBox_1_No_Plan_Voladura);

        TextView_Flujo_Comunicaciones_2 = findViewById(R.id.TextView_Flujo_Comunicaciones_2);
        CheckBox_2_SI_Flujo_Comunicaciones = findViewById(R.id.CheckBox_2_SI_Flujo_Comunicaciones);
        CheckBox_2_NO_Flujo_Comunicaciones = findViewById(R.id.CheckBox_2_NO_Flujo_Comunicaciones);

        TextView_Explosivos_Accesorios_3 = findViewById(R.id.TextView_Explosivos_Accesorios_3);
        CheckBox_3_SI_Explosivos_Accesorios = findViewById(R.id.CheckBox_3_SI_Explosivos_Accesorios);
        CheckBox_3_NO_Explosivos_Accesorios = findViewById(R.id.CheckBox_3_NO_Explosivos_Accesorios);

        TextView_Calificado_SUCAMEC_4 = findViewById(R.id.TextView_Calificado_SUCAMEC_4);
        CheckBox_4_SI_Calificado_SUCAMEC = findViewById(R.id.CheckBox_4_SI_Calificado_SUCAMEC);
        CheckBox_4_NO_Calificado_SUCAMEC = findViewById(R.id.CheckBox_4_NO_Calificado_SUCAMEC);

        TextView_Restriccion_zona_voladura_5 = findViewById(R.id.TextView_Restriccion_zona_voladura_5);
        CheckBox_5_SI_Restriccion_zona_voladura = findViewById(R.id.CheckBox_5_SI_Restriccion_zona_voladura);
        CheckBox_5_NO_Restriccion_zona_voladura = findViewById(R.id.CheckBox_5_NO_Restriccion_zona_voladura);

        progressBar = findViewById(R.id.progressBar);

        /**
         * Juego de seleccion del si y no
         */

        checkBox_1_Si_Plan_Voladura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    checkBox_1_No_Plan_Voladura.setChecked(false);
                    checkBox_1_Si_Plan_Voladura.setText("Si");
                    checkBox_1_No_Plan_Voladura.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        checkBox_1_No_Plan_Voladura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkBox_1_Si_Plan_Voladura.setChecked(false);
                    checkBox_1_No_Plan_Voladura.setText("No");
                    checkBox_1_Si_Plan_Voladura.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_2_SI_Flujo_Comunicaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_2_NO_Flujo_Comunicaciones.setChecked(false);
                    CheckBox_2_SI_Flujo_Comunicaciones.setText("Si");
                    CheckBox_2_NO_Flujo_Comunicaciones.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_2_NO_Flujo_Comunicaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_2_SI_Flujo_Comunicaciones.setChecked(false);
                    CheckBox_2_NO_Flujo_Comunicaciones.setText("No");
                    CheckBox_2_SI_Flujo_Comunicaciones.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_3_SI_Explosivos_Accesorios.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_3_NO_Explosivos_Accesorios.setChecked(false);
                    CheckBox_3_SI_Explosivos_Accesorios.setText("Si");
                    CheckBox_3_NO_Explosivos_Accesorios.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_3_NO_Explosivos_Accesorios.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_3_SI_Explosivos_Accesorios.setChecked(false);
                    CheckBox_3_NO_Explosivos_Accesorios.setText("No");
                    CheckBox_3_SI_Explosivos_Accesorios.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_4_SI_Calificado_SUCAMEC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_4_NO_Calificado_SUCAMEC.setChecked(false);
                    CheckBox_4_SI_Calificado_SUCAMEC.setText("Si");
                    CheckBox_4_NO_Calificado_SUCAMEC.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_4_NO_Calificado_SUCAMEC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_4_SI_Calificado_SUCAMEC.setChecked(false);
                    CheckBox_4_NO_Calificado_SUCAMEC.setText("No");
                    CheckBox_4_SI_Calificado_SUCAMEC.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox_5_SI_Restriccion_zona_voladura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //CheckBox_Correcta_Condicion_Si  CheckBox_Correcta_Condicion_No
                if (isChecked) {
                    CheckBox_5_NO_Restriccion_zona_voladura.setChecked(false);
                    CheckBox_5_SI_Restriccion_zona_voladura.setText("Si");
                    CheckBox_5_NO_Restriccion_zona_voladura.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }   // Fin de CheckBox_Correcta_Condicion_Si
        });

        CheckBox_5_NO_Restriccion_zona_voladura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CheckBox_5_SI_Restriccion_zona_voladura.setChecked(false);
                    CheckBox_5_NO_Restriccion_zona_voladura.setText("No");
                    CheckBox_5_SI_Restriccion_zona_voladura.setText("  ");
                    Toast.makeText(Accidente_Explosivos_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**
         *
         */

        BTN_ExplosivosData = findViewById(R.id.BTN_ExplosivosData);
        BTN_ExplosivosData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EjecutarCondiciones_ActividadExplosivos();
            }
        });
    }

    private void EjecutarCondiciones_ActividadExplosivos() {
        if (checkPermission()) {
            Ejecuciones_createPdfWithPermission();

        } else {
            requestPermission();
        }
    }

    private void Ejecuciones_createPdfWithPermission() {

        String Recibe_editTextTextMultiLine1 = TextView_Actividades_Realizar.getText().toString();
        String Recibe_editTextTextMultiLine2 = Recibe_editTextTextMultiLine.getText().toString();
        String Recibe_EditText_Empresa_DondeLabora3 = Recibe_EditText_Empresa_DondeLabora.getText().toString();
        String Recibe_EditText_Area_donde_Labora4 = Recibe_EditText_Area_donde_Labora.getText().toString();
        String Recibe_EditText_Riesgo_Fatal5 = Recibe_EditText_Riesgo_Fatal.getText().toString();

        String TextView_Plan_Voladura_11 = TextView_Plan_Voladura_1.getText().toString();
        String checkBox_1_Si_Plan_Voladura11 = (checkBox_1_Si_Plan_Voladura.getText().toString());
        String checkBox_1_No_Plan_Voladura11 = (checkBox_1_No_Plan_Voladura.getText().toString());

        String TextView_Flujo_Comunicaciones_22 = TextView_Flujo_Comunicaciones_2.getText().toString();
        String CheckBox_2_SI_Flujo_Comunicaciones_22 = (CheckBox_2_SI_Flujo_Comunicaciones.getText().toString());
        String CheckBox_2_NO_Flujo_Comunicaciones_22 = (CheckBox_2_NO_Flujo_Comunicaciones.getText().toString());

        String TextView_Explosivos_Accesorios_33 = TextView_Explosivos_Accesorios_3.getText().toString();
        String CheckBox_3_SI_Explosivos_Accesorios_33 = (CheckBox_3_SI_Explosivos_Accesorios.getText().toString());
        String CheckBox_3_NO_Explosivos_Accesorios_33 = (CheckBox_3_NO_Explosivos_Accesorios.getText().toString());

        String TextView_Calificado_SUCAMEC_44 = TextView_Calificado_SUCAMEC_4.getText().toString();
        String CheckBox_4_SI_Calificado_SUCAMEC_44 = (CheckBox_4_SI_Calificado_SUCAMEC.getText().toString());
        String CheckBox_4_NO_Calificado_SUCAMEC_44 = (CheckBox_4_NO_Calificado_SUCAMEC.getText().toString());

        String TextView_Restriccion_zona_voladura_55 = TextView_Restriccion_zona_voladura_5.getText().toString();
        String CheckBox_5_SI_Restriccion_zona_voladura_55 = (CheckBox_5_SI_Restriccion_zona_voladura.getText().toString());
        String CheckBox_5_NO_Restriccion_zona_voladura_55 = (CheckBox_5_NO_Restriccion_zona_voladura.getText().toString());


        createPdfWithPermission(Recibe_editTextTextMultiLine1, Recibe_editTextTextMultiLine2, Recibe_EditText_Empresa_DondeLabora3, Recibe_EditText_Area_donde_Labora4, Recibe_EditText_Riesgo_Fatal5,
                TextView_Plan_Voladura_11,checkBox_1_Si_Plan_Voladura11,checkBox_1_No_Plan_Voladura11,
                TextView_Flujo_Comunicaciones_22, CheckBox_2_SI_Flujo_Comunicaciones_22, CheckBox_2_NO_Flujo_Comunicaciones_22,
                TextView_Explosivos_Accesorios_33, CheckBox_3_SI_Explosivos_Accesorios_33,CheckBox_3_NO_Explosivos_Accesorios_33,
                TextView_Calificado_SUCAMEC_44, CheckBox_4_SI_Calificado_SUCAMEC_44, CheckBox_4_NO_Calificado_SUCAMEC_44,
                TextView_Restriccion_zona_voladura_55, CheckBox_5_SI_Restriccion_zona_voladura_55, CheckBox_5_NO_Restriccion_zona_voladura_55
        );
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

    private void createPdfWithPermission(String TextView_Actividades_Realizar, String Recibe_editTextTextMultiLine, String Recibe_EditText_Empresa_DondeLabora, String Recibe_EditText_Area_donde_Labora, String Recibe_EditText_Riesgo_Fatal,String TextView_Plan_Voladura_1, String checkBox_1_Si_Plan_Voladura, String checkBox_1_No_Plan_Voladura,
                                         String TextView_Flujo_Comunicaciones_2, String CheckBox_2_SI_Flujo_Comunicaciones, String CheckBox_2_NO_Flujo_Comunicaciones,
                                         String TextView_Explosivos_Accesorios_3, String CheckBox_3_SI_Explosivos_Accesorios, String CheckBox_3_NO_Explosivos_Accesorios,
                                         String TextView_Calificado_SUCAMEC_4, String CheckBox_4_SI_Calificado_SUCAMEC, String CheckBox_4_NO_Calificado_SUCAMEC,
                                         String TextView_Restriccion_zona_voladura_5, String CheckBox_5_SI_Restriccion_zona_voladura, String CheckBox_5_NO_Restriccion_zona_voladura)
    {
        String fileName = generateFileName();      // Generar PDF

        progressBar.setVisibility(View.VISIBLE);

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
            table.addCell(new Cell().add(new Paragraph(TextView_Plan_Voladura_1).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(checkBox_1_Si_Plan_Voladura).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(checkBox_1_No_Plan_Voladura).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Flujo_Comunicaciones_2).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_2_SI_Flujo_Comunicaciones).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_2_NO_Flujo_Comunicaciones).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Explosivos_Accesorios_3).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_3_SI_Explosivos_Accesorios).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_3_NO_Explosivos_Accesorios).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Calificado_SUCAMEC_4).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_4_SI_Calificado_SUCAMEC).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_4_NO_Calificado_SUCAMEC).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));

            // Celdas izquierdas con el texto correspondiente
            table.addCell(new Cell().add(new Paragraph(TextView_Restriccion_zona_voladura_5).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_5_SI_Restriccion_zona_voladura).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(CheckBox_5_NO_Restriccion_zona_voladura).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));


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
            progressBar.setVisibility(View.GONE);

            Toast.makeText(this, "PDF guardado exitosamente", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
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
        String fileName = "Accidente_ExplosivoPDF_" + timeStamp + ".pdf";
        String folderPath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator + "Accidente_Explosivo_PDF";

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // TODO: 14/06/2023 createPdfWithPermission falta Arreglar el Metodo 
                /**
                 * createPdfWithPermission tenemos problemas en este metodo luego arreglar
                 */

            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
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