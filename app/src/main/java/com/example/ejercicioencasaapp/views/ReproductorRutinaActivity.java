package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;

public class ReproductorRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_REPRODUCTOR = "rutinaNombre";
    private String nombreRutina;
    private TextView tvNombreRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_rutina);

        Intent intent = getIntent();

        nombreRutina = intent.getStringExtra(EXTRA_REPRODUCTOR);
        tvNombreRutina = (TextView)findViewById(R.id.tvReproductor);
        tvNombreRutina.setText(String.valueOf(nombreRutina));

    }
}