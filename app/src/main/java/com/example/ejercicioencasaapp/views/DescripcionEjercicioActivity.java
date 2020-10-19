package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorListaEjercicios;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioPlanDAO;
import com.example.ejercicioencasaapp.models.PlanDAO;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class DescripcionEjercicioActivity extends AppCompatActivity {
    public static final String EXTRA_AGREGAR = "agregar_ejercico";
    public static final String EXTRA_ID_PLAN = "id_plan";
    public static final String EXTRA_ID_EJERCICIO = "id_ejercicio";
    public static final String EXTRA_NOMBRE_EJERCICIO = "nombre_ejercicio";
    private int idEjercicio, idPlan, idGif;
    private String nombreEjercicio;
    private boolean agregando;
    private TextView tvIdPlan, tvIdEjercicio, tvNombre;
    private Button btnAgregar;
    private GifImageView gvEjercicio;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_ejercicio);

        final Intent intent = getIntent();

        agregando = intent.getBooleanExtra(EXTRA_AGREGAR, false);
        idEjercicio = intent.getIntExtra(EXTRA_ID_EJERCICIO,0);
        idPlan = intent.getIntExtra(EXTRA_ID_PLAN,0);

        nombreEjercicio = intent.getStringExtra(EXTRA_NOMBRE_EJERCICIO);

        tvIdPlan = (TextView)findViewById(R.id.tvIdPlanAgregar);
        tvIdEjercicio = (TextView)findViewById(R.id.tvIdEjercicioAgregar);
        tvNombre = (TextView)findViewById(R.id.tvNombreEjercicioAgregar);

        gvEjercicio = (GifImageView)findViewById(R.id.gvEjercicio);

        tvIdPlan.setText(String.valueOf(idPlan));
        tvIdEjercicio.setText(String.valueOf(idEjercicio));
        tvNombre.setText(nombreEjercicio);

        btnAgregar = (Button)findViewById(R.id.btnAgregarEjercicio);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EjercicioPlan ejercicioPlan = new EjercicioPlan(idPlan,idEjercicio);
                EjercicioPlanDAO ejercicioPlanDAO = new EjercicioPlanDAO(getBaseContext());
                ejercicioPlanDAO.insertarEjercicioPlan(ejercicioPlan);

                PlanDAO planDAO = new PlanDAO(getBaseContext());
                planDAO.actualizarCantidad(idPlan);

                //Cierra el activity de la lista de ejercicios
                ((Activity)ListaCompletaEjerciciosActivity.context).finish();
                finish();

            }
        });

        if(agregando){
            btnAgregar.setVisibility(View.VISIBLE);
        }
    }
}