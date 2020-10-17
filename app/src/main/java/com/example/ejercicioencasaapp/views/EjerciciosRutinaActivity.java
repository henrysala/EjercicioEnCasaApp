package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class EjerciciosRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_RUTINA_OR_PLAN = "rutina_or_plan";
    public static final String EXTRA_EJERCICIO = "rutinaNombre";
    public static final String EXTRA_RUTINA_ID = "id_rutina";
    public static final String EXTRA_RUTINA_IMAGE = "rutina_image";
    public static final String EXTRA_RUTINA_CANTIDAD = "cantidad_rutina";
    private RecyclerView recyclerViewEjerciciosRutina;
    private String nombreRutina;
    private int idRutina, image_rutina, cantidadRutina, rutinaOrPlan;
    private TextView tvNombre;
    private ImageView iVRutina;
    //private Button btnComenzarRutina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        Intent intent = getIntent();

        rutinaOrPlan = intent.getIntExtra(EXTRA_RUTINA_OR_PLAN, 0);
        nombreRutina = intent.getStringExtra(EXTRA_EJERCICIO);
        idRutina = intent.getIntExtra(EXTRA_RUTINA_ID, 0);
        tvNombre = (TextView)findViewById(R.id.tvRutinaNombre);
        tvNombre.setText(String.valueOf(nombreRutina));
        image_rutina = intent.getIntExtra(EXTRA_RUTINA_IMAGE, 0);
        iVRutina = (ImageView)findViewById(R.id.imageCard);
        iVRutina.setImageResource(image_rutina);
        cantidadRutina = intent.getIntExtra(EXTRA_RUTINA_CANTIDAD,0);



        //hasta aqui funciona
        recyclerViewEjerciciosRutina = (RecyclerView)findViewById(R.id.rvEjerciciosRutina);

        final ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjerciciosRutina.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        //dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);
        if(rutinaOrPlan == 0){
            dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);
        }else{
            dataset = ejercicioDAO.consultarEjerciciosPlan(idRutina);
        }
        recyclerViewEjerciciosRutina.setAdapter(new AdaptadorRutinaLista(dataset));

        FloatingActionButton btnComenzarRutina = (FloatingActionButton)findViewById(R.id.btnComenzarRutina);

        btnComenzarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReproductorRutinaActivity.class);

                intent.putExtra(ReproductorRutinaActivity.EXTRA_RUTINA_OR_PLAN, rutinaOrPlan);
                intent.putExtra(ReproductorRutinaActivity.EXTRA_RUTINA_NAME, nombreRutina);
                intent.putExtra(ReproductorRutinaActivity.EXTRA_RUTINA_ID,idRutina);
                intent.putExtra(ReproductorRutinaActivity.EXTRA_RUTINA_CANTIDAD,cantidadRutina);

                view.getContext().startActivity(intent);
            }
        });


    }
}