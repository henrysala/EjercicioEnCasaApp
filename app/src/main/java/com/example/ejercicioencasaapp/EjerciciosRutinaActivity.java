package com.example.ejercicioencasaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class EjerciciosRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_EJERCICIO = "rutinaNombre";
    private RecyclerView recyclerViewEjerciciosRutina;
    private String nombreRutina;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        Intent intent = getIntent();
        //nombreRutina = intent.getIntExtra(EXTRA_EJERCICIO,0);
        nombreRutina = intent.getStringExtra(EXTRA_EJERCICIO);
        textView = (TextView)findViewById(R.id.tvRutinaNombre);

        textView.setText(String.valueOf(nombreRutina));

        //hasta aqui funciona

        recyclerViewEjerciciosRutina = (RecyclerView)findViewById(R.id.rvEjerciciosRutina);

        ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjerciciosRutina.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarEjerciciosRutina();
        recyclerViewEjerciciosRutina.setAdapter(new AdaptadorRutinaLista(dataset));

        /*
        ArrayList<Rutina> dataSet = new ArrayList<Rutina>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewRutina.setLayoutManager(layoutManager);
        rutinaDAO = new RutinaDAO(getContext());
        dataSet = rutinaDAO.consultarRutinas();
        recyclerViewRutina.setAdapter(new AdaptadorRutina(dataSet,this));

        return view;

         */

    }
}