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
    public static final String EXTRA_DESCRIP_EJERCICIO = "descrip_ejercicio";
    private int idEjercicio, idPlan;
    private String nombreEjercicio, descrip_ejercicio;
    private boolean agregando;
    private TextView tvIdPlan, tvIdEjercicio, tvNombre, tvDescription;
    private Button btnAgregar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_ejercicio);

        final Intent intent = getIntent();

        agregando = intent.getBooleanExtra(EXTRA_AGREGAR, false);
        idEjercicio = intent.getIntExtra(EXTRA_ID_EJERCICIO,0);
        idPlan = intent.getIntExtra(EXTRA_ID_PLAN,0);

        nombreEjercicio = intent.getStringExtra(EXTRA_NOMBRE_EJERCICIO);
        descrip_ejercicio = intent.getStringExtra(EXTRA_DESCRIP_EJERCICIO);

        tvIdPlan = (TextView)findViewById(R.id.tvIdPlanAgregar);
        tvIdEjercicio = (TextView)findViewById(R.id.tvIdEjercicioAgregar);
        tvNombre = (TextView)findViewById(R.id.tvNombreEjercicioAgregar);
        tvDescription = (TextView)findViewById(R.id.tvDescription);

        tvIdPlan.setText(String.valueOf(idPlan));
        tvIdEjercicio.setText(String.valueOf(idEjercicio));
        tvNombre.setText(nombreEjercicio);
        tvDescription.setText(descrip_ejercicio);

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