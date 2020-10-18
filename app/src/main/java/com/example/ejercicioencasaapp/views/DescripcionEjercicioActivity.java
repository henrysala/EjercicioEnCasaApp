package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;

public class DescripcionEjercicioActivity extends AppCompatActivity {
    public static final String EXTRA_ID_EJERCICIO = "id_ejercicio";
    public static final String EXTRA_NOMBRE_EJERCICIO = "nombre_ejercicio";
    private int idEjercicio;
    private String nombreEjercicio;
    private TextView tvId, tvNombre;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_ejercicio);

        Intent intent = getIntent();

        idEjercicio = intent.getIntExtra(EXTRA_ID_EJERCICIO,0);
        nombreEjercicio = intent.getStringExtra(EXTRA_NOMBRE_EJERCICIO);

        tvId = (TextView)findViewById(R.id.tvIdEjercicioAgregar);
        tvNombre = (TextView)findViewById(R.id.tvNombreEjercicioAgregar);

        tvId.setText(String.valueOf(idEjercicio));
        tvNombre.setText(nombreEjercicio);

        btnAgregar = (Button)findViewById(R.id.btnAgregarEjercicio);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }
}