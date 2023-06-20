package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aplicationapps.project_puca.R;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
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
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;


public class Generate_PDF_Text_Activity extends AppCompatActivity {

    EditText editTextTextPersonName1;
    EditText editTextTextPersonName2;
    EditText editTextTextPersonName3;
    EditText editTextTextPersonName4;

    TextView TextView_Monitoreo;

    Button btn_GenerarPdf;
    CheckBox checkBox3;
    CheckBox checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf_text);

        editTextTextPersonName1 = findViewById(R.id.editTextTextPersonName1);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        btn_GenerarPdf = findViewById(R.id.btn_GenerarPdf);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        TextView_Monitoreo = findViewById(R.id.TextView_Monitoreo);

        btn_GenerarPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTextPersonName1.getText().toString();
                String age = editTextTextPersonName2.getText().toString();
                String number = editTextTextPersonName3.getText().toString();
                String location = editTextTextPersonName4.getText().toString();
               // String Monitoreo = TextView_Monitoreo.getText().toString();       // en Proceso

               // Boolean falseado = Boolean.valueOf(checkBox3.getText().toString());
               // Boolean NoRespuesta = Boolean.valueOf(checkBox4.getText().toString());

                String falseado = checkBox3.getText().toString();   //si
                String NoRespuesta = checkBox4.getText().toString();  //no

                try {
                    createPDF(name, age, number, location, falseado, NoRespuesta);  // Primero
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } // fin de Onclick


        });

    }

    private void createPDF(String name, String age,String number, String Location, String falseado, String NoRespuesta) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(); // Directorio Descargas
        File file = new File(pdfPath, "21_05_2023.pdf");    // Asignamos el Nombre de PDF por ahora es estatico
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);
        document.setMargins(0,0,0,0);

       // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
       // Paragraph goup = new Paragraph("PDF de Pucamarcar CheckList\n" +
         //       "Area de SSOMA").setTextAlignment(TextAlignment.CENTER).setFontSize(12);
       // Paragraph varansi = new Paragraph("Developer").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);

        float[] width = {180f, 20f, 20f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //Monitoreo
        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(6).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));

        table.addCell(new Cell().add(new Paragraph("3. Garantizar el monitoreo de ubicación y control de velocidades mediante GPS. Contar con sistemas de alertas de exceso de velocidades.").setFontSize(8).setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));
        table.addCell(new Cell().add(new Paragraph(NoRespuesta)));


        float[] widths = {20f, 20f};
        Table tables = new Table(widths);
        tables.setHorizontalAlignment(HorizontalAlignment.CENTER);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tables.addCell(new Cell().add(new Paragraph("Date:").setFontSize(6)));
        tables.addCell(new Cell().add(new Paragraph(LocalDate.now().format(dateFormatter).toString()).setFontSize(8)));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        tables.addCell(new Cell().add(new Paragraph("Time:").setFontSize(6)));
        tables.addCell(new Cell().add(new Paragraph(LocalTime.now().format(timeFormatter).toString()).setFontSize(8)));

        BarcodeQRCode qrCode = new BarcodeQRCode(name+"\n"+age+"\n"+number+"\n"+Location+"\n"+LocalDate.now().format(dateFormatter)+"\n"+LocalTime.now().format(timeFormatter));
        PdfFormXObject qrCodeObject = qrCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
        Image qrCodeImage = new Image(qrCodeObject).setWidth(80).setHorizontalAlignment(HorizontalAlignment.CENTER);


        document.add(image);
        document.add(visitorTicket);
       // document.add(goup);
       // document.add(varansi);
        document.add(table);
        document.add(tables);     // tabla agregada
        document.add(qrCodeImage);
        document.close();
        Toast.makeText(this, "PDF Creado", Toast.LENGTH_SHORT).show();

    }
    // Faltan algunas cosillas pero ahi veremos
}