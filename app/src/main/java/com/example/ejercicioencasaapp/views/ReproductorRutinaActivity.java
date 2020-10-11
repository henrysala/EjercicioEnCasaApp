package com.example.ejercicioencasaapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.EjercicioDAO;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class ReproductorRutinaActivity extends AppCompatActivity {

    public static final String EXTRA_RUTINA_NAME = "rutina_nombre";
    private static final String EXTRA_IMAGE = "image_rutina";
    public static final String EXTRA_RUTINA_ID = "id_rutina";
    private String nombreRutina;
    private TextView tvNombreRutina, tvCurrentEjercicio, tvTimer, tvContador;
    private Button btnPlay;
    private int gif_ejercicio,idRutina;
    private GifImageView gvEjercicio;
    private int seconds = 30;
    public int count = 1;
    private boolean running, wasRunning;
    private ArrayList<Ejercicio> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_rutina);

        Intent intent = getIntent();


        nombreRutina = intent.getStringExtra(EXTRA_RUTINA_NAME);
        tvNombreRutina = (TextView)findViewById(R.id.tvCurrentRutina);
        tvNombreRutina.setText(String.valueOf(nombreRutina));

        gif_ejercicio = intent.getIntExtra(EXTRA_IMAGE,0);
        gvEjercicio = (GifImageView)findViewById(R.id.gvEjercicio);
        gvEjercicio.setBackgroundResource(gif_ejercicio);

        tvContador = (TextView)findViewById(R.id.tvReproductor);
        tvContador.setText(String.valueOf(count));


        running = true;
        btnPlay = (Button)findViewById(R.id.btnPlay);

        //Estoy tratando de crear nuevamente la lista de ejercicios de la rutina
        ArrayList<Ejercicio> dataset;
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);

        tvCurrentEjercicio = (TextView)findViewById(R.id.tvPalabraEjercicio);
        //tvCurrentEjercicio.setText(dataset.get(0).getName());
        tvCurrentEjercicio.setText("PREPARATE");


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            //running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }
    //al clikear el boton conmuta el estado running y cambia el texto del boton
    public void onClickPause(View view){
        running = !running;
        if(running == true){
            btnPlay.setText("pause");
        }else{
            btnPlay.setText("play");
        }
    }

    @Override
    //Al volver a la ventana de la aplicacion el reproductor sigue pausado hasta que se de click al boton
    protected void onStart() {
        super.onStart();
        if(running){
            //running = true;
            btnPlay.setText("pause");
        }else{
            btnPlay.setText("play");
        }
    }

    @Override
    //Al cambiar de ventana en el dispositivo el reproductor se pausa automaticamente
    protected void onStop() {
        super.onStop();
        //wasRunning = false;
        running = false;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.tvTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int sec = seconds%60;
                String time = String.format("%02d ",sec);
                timeView.setText(time);
                if(running == true){
                    seconds--;
                }
                if(seconds == 0){
                    count++;

                    //tvCurrentEjercicio.setText("descanso");
                    //seconds = 30;

                    if(count == 1){
                        tvCurrentEjercicio.setText("Descanso");
                        seconds = 15;
                    }else{
                        tvCurrentEjercicio.setText("ejercicio");
                        //seconds = dataset.get(0).getDuracion();
                        seconds = 30;
                    }



                    tvContador.setText(String.valueOf(count));
                    seconds = 30;

                }
                handler.postDelayed(this,1000);
            }
        });
    }
}