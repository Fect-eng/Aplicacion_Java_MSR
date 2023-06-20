package com.aplicationapps.project_puca.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.aplicationapps.project_puca.R;

public class resolucion_Activity extends AppCompatActivity {

    private CheckBox checkBoxCumple;
    private CheckBox checkBoxNoCumple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolucion);

        checkBoxCumple = findViewById(R.id.checkBoxCumple);
        checkBoxNoCumple = findViewById(R.id.checkBoxNoCumple);

        checkBoxCumple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Si se marca el CheckBox "Cumple"
                    checkBoxNoCumple.setChecked(false); // Desmarcar el CheckBox "No Cumple"
                    Toast.makeText(resolucion_Activity.this, "Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBoxNoCumple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Si se marca el CheckBox "No Cumple"
                    checkBoxCumple.setChecked(false); // Desmarcar el CheckBox "Cumple"
                    Toast.makeText(resolucion_Activity.this, "No Cumple", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}