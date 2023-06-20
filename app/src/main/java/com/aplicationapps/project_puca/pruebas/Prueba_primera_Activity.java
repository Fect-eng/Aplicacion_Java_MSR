package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.aplicationapps.project_puca.R;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class Prueba_primera_Activity extends AppCompatActivity {

    private LinearLayout pdfLayout;

    Button btn_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_primera);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        pdfLayout = findViewById(R.id.pdfLayout);

        btn_prueba = findViewById(R.id.btn_prueba);
        btn_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                generatePDF();
            }
        });

    }


    private void generatePDF() {

        // Crear un nuevo documento PDF
        PdfDocument document = new PdfDocument();

        // Configurar el tamaño de página y márgenes
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        // Comenzar una nueva página
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();

        // Definir el estilo de texto
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);

        // Obtener la lista de datos (aquí debes reemplazar con tus propios datos)

        // Datos que salen en PDF
        String[] dataList = {"button", "butston", "Voldemor"};

        // Iterar sobre la lista y dibujar cada elemento en el PDF
        int y = 50; // Posición vertical inicial
        for (String data : dataList) {
            canvas.drawText(data, 50, y, paint);
            y += 20; // Incrementar la posición vertical para el siguiente elemento
        }

        // Finalizar la página
        document.finishPage(page);

        // Guardar el documento PDF en el almacenamiento externo
        String fileName = "listaz_datos.pdf";
        File filePath = new File(Environment.getExternalStorageDirectory(), fileName);

        try {
            document.writeTo(new FileOutputStream(filePath));
            Log.d("PDF", "PDF generado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("PDF", "Error al generar el PDF: " + e.toString());
        }

        // Cerrar el documento PDF
        document.close();
    }
}