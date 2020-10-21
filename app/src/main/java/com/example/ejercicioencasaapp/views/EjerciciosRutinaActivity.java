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
import com.example.ejercicioencasaapp.models.AdaptadorListaEjercicios;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioDAO;
import com.example.ejercicioencasaapp.models.PlanDAO;
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
    private FloatingActionButton btnAgregarEjercicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        final Intent intent = getIntent();

        //rutinaOplan recibe 0 si se abre una rutina o 1 se se abre un plan
        rutinaOrPlan = intent.getIntExtra(EXTRA_RUTINA_OR_PLAN, 0);
        nombreRutina = intent.getStringExtra(EXTRA_EJERCICIO);
        idRutina = intent.getIntExtra(EXTRA_RUTINA_ID, 0);
        tvNombre = (TextView)findViewById(R.id.tvRutinaNombre);
        tvNombre.setText(String.valueOf(nombreRutina));
        image_rutina = intent.getIntExtra(EXTRA_RUTINA_IMAGE, 0);
        iVRutina = (ImageView)findViewById(R.id.imageCard);
        iVRutina.setImageResource(image_rutina);

        //este if es para que al agregar un ejercicio a un plan la cantidad de ejercicios este al entrar al reproductor
        if(rutinaOrPlan==1){
            PlanDAO planDAO = new PlanDAO(getBaseContext());
            cantidadRutina = planDAO.consultarCantidad(idRutina);
        }
        else{
            cantidadRutina = intent.getIntExtra(EXTRA_RUTINA_CANTIDAD,0);
        }

        recyclerViewEjerciciosRutina = (RecyclerView)findViewById(R.id.rvEjerciciosRutina);
        btnAgregarEjercicio = (FloatingActionButton) findViewById(R.id.btnAgregarEjercicioPlan);

        final ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjerciciosRutina.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        //dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);

        //dependiendo de si se abre una rutina o un plan se llama un metodo diferente y se muestra el boton agregar ejercicio
        if(rutinaOrPlan == 0){
            dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);

        }else{
            dataset = ejercicioDAO.consultarEjerciciosPlan(idRutina);
            btnAgregarEjercicio.setVisibility(View.VISIBLE);
            //iVRutina.setImageResource(R.drawable.fondo_card_plan);
        }

        //al pulsar este boton se abre la lista de todos los ejercicios y se obtiene el id del plan
        btnAgregarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), ListaCompletaEjerciciosActivity.class);
                intent1.putExtra(ListaCompletaEjerciciosActivity.EXTRA_ID_PLAN,idRutina);
                view.getContext().startActivity(intent1);

            }
        });
        AdaptadorRutinaLista adaptadorRutinaLista  = new AdaptadorRutinaLista(dataset);
        recyclerViewEjerciciosRutina.setAdapter(adaptadorRutinaLista);
        //se pasa el id del plan al adaptador
        adaptadorRutinaLista.enviarIdPlan(idRutina);


        Button btnComenzarRutina = (Button)findViewById(R.id.btnComenzarRutina);

        //al pulsar el boton comenzarRutina se abre el activity reproductor y se pasan el nombre, id, y cantidad de ejercicios
        //de la rutina, y si es una rutina o un plan
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

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();

        //refrescar la lista de ejercicio para ver los ejercicios nuevos agregados
        finish();
        startActivity(intent);
    }
}