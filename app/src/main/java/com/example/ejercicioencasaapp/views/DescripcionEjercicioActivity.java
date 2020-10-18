package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;

public class DescripcionEjercicioActivity extends AppCompatActivity {
    public static final String EXTRA_AGREGAR = "agregar_ejercico";
    public static final String EXTRA_ID_PLAN = "id_plan";
    public static final String EXTRA_ID_EJERCICIO = "id_ejercicio";
    public static final String EXTRA_NOMBRE_EJERCICIO = "nombre_ejercicio";
    private int idEjercicio, idPlan;
    private String nombreEjercicio;
    private boolean agregando;
    private TextView tvIdPlan, tvIdEjercicio, tvNombre;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_ejercicio);

        Intent intent = getIntent();

        agregando = intent.getBooleanExtra(EXTRA_AGREGAR, false);
        idEjercicio = intent.getIntExtra(EXTRA_ID_EJERCICIO,0);
        idPlan = intent.getIntExtra(EXTRA_ID_PLAN,0);
        nombreEjercicio = intent.getStringExtra(EXTRA_NOMBRE_EJERCICIO);

        tvIdPlan = (TextView)findViewById(R.id.tvIdPlanAgregar);
        tvIdEjercicio = (TextView)findViewById(R.id.tvIdEjercicioAgregar);
        tvNombre = (TextView)findViewById(R.id.tvNombreEjercicioAgregar);

        tvIdPlan.setText(String.valueOf(idPlan));
        tvIdEjercicio.setText(String.valueOf(idEjercicio));
        tvNombre.setText(nombreEjercicio);

        btnAgregar = (Button)findViewById(R.id.btnAgregarEjercicio);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        if(agregando){
            btnAgregar.setVisibility(View.VISIBLE);
        }
    }
}