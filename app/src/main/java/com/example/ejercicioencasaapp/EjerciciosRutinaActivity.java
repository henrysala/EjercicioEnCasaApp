package com.example.ejercicioencasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EjerciciosRutinaActivity extends AppCompatActivity {
    public static final String RUTINA = "idRutina";
    private TextView rutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        Intent intent = getIntent();
        String texto = intent.getStringExtra(RUTINA);

        rutina = (TextView)findViewById(R.id.tvEjerciciosRutina);
        rutina.setText(texto);
    }
}