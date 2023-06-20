package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.aplicationapps.project_puca.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDF_Pruebas_Activity extends AppCompatActivity {

    Button btn_PDF_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_pruebas);

        btn_PDF_generate = findViewById(R.id.btn_PDF_generate);


       ActivityCompat.requestPermissions(this,new String[]{
               Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF();
               }


    private void createPDF() {
        btn_PDF_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();

                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250,400,1).create();
                PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);

                Canvas canvas = myPage1.getCanvas();

                canvas.drawText("Prueba Documento", 40, 50, myPaint);

                myPdfDocument.finishPage(myPage1);

                File file = new File(Environment.getExternalStorageDirectory(), "/Pucamarca.pdf");

                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                myPdfDocument.close();
            }
        });


    }
}