package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EjerciciosRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_EJERCICIO = "rutinaNombre";
    public static final String EXTRA_RUTINA_ID = "id_rutina";
    private RecyclerView recyclerViewEjerciciosRutina;
    private String nombreRutina;
    private int idRutina;
    private TextView tvNombre;
    //private Button btnComenzarRutina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        Intent intent = getIntent();

        nombreRutina = intent.getStringExtra(EXTRA_EJERCICIO);
        idRutina = intent.getIntExtra(EXTRA_RUTINA_ID, 0);

        tvNombre = (TextView)findViewById(R.id.tvRutinaNombre);

        tvNombre.setText(String.valueOf(nombreRutina));


        //hasta aqui funciona
        recyclerViewEjerciciosRutina = (RecyclerView)findViewById(R.id.rvEjerciciosRutina);

        ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjerciciosRutina.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);
        recyclerViewEjerciciosRutina.setAdapter(new AdaptadorRutinaLista(dataset));

        FloatingActionButton btnComenzarRutina = (FloatingActionButton)findViewById(R.id.btnComenzarRutina);

        btnComenzarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReproductorRutinaActivity.class);
                intent.putExtra(ReproductorRutinaActivity.EXTRA_REPRODUCTOR, nombreRutina);
                view.getContext().startActivity(intent);
            }
        });


    }
}