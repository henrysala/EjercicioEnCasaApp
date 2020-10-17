package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorListaEjercicios;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioDAO;

import java.util.ArrayList;

public class ListaCompletaEjerciciosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEjercicios;
    private Button crearPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_completa_ejercicios);

        crearPlan = (Button)findViewById(R.id.btnCrearPlan);
        recyclerViewEjercicios = (RecyclerView)findViewById(R.id.rvAllEjercicios);

        final ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjercicios.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarAllEjercicios();
        recyclerViewEjercicios.setAdapter(new AdaptadorListaEjercicios(dataset));

    }
}