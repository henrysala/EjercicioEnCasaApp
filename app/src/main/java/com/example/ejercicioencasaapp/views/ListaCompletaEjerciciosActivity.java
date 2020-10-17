package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
        crearPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ponerNombre = new AlertDialog.Builder(ListaCompletaEjerciciosActivity.this);
                ponerNombre.setMessage("insertar nombre")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = ponerNombre.create();
                titulo.setTitle("GUARDAR PLAN");
                titulo.show();
            }
        });

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