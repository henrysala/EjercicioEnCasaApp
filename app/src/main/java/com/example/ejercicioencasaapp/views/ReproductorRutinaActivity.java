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
    public static final String EXTRA_RUTINA_ID = "id_rutina";
    public static final String EXTRA_RUTINA_CANTIDAD = "cantidad_rutina";
    private String nombreRutina;
    private TextView tvNombreRutina, tvCurrentEjercicio, tvTimer, tvContador;
    private Button btnPlay;
    private int idRutina, cantidadRutina;
    private GifImageView gvEjercicio;
    private int seconds = 10;
    public int count = 0;
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

        gvEjercicio = (GifImageView)findViewById(R.id.gvEjercicio);

        cantidadRutina = intent.getIntExtra(EXTRA_RUTINA_CANTIDAD,0);
        tvContador = (TextView)findViewById(R.id.tvReproductor);
        tvContador.setText(String.valueOf(cantidadRutina));


        running = true;
        btnPlay = (Button)findViewById(R.id.btnPlay);

        //Estoy tratando de crear nuevamente la lista de ejercicios de la rutina
        ArrayList<Ejercicio> dataset;
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);

        tvCurrentEjercicio = (TextView)findViewById(R.id.tVCurrentEjercicio);
        //tvCurrentEjercicio.setText(dataset.get(0).getName());
        tvCurrentEjercicio.setText("PREPARATE");
        gvEjercicio.setBackgroundResource(R.drawable.be_prepared);


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer(dataset, cantidadRutina);
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

    private void runTimer(ArrayList<Ejercicio> list, int cantidadEjercicios){
        final ArrayList<Ejercicio> dataset = list;
        final int veces = cantidadEjercicios;
        final TextView timeView = (TextView)findViewById(R.id.tvTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int sec = seconds%60;
                String time = String.format("%02d ",sec);
                timeView.setText(time);

                if(count < veces*2){

                    if(running == true){
                        seconds--;
                    }
                    if(seconds == -1){
                        count++;

                        if(count%2 == 0){
                            tvCurrentEjercicio.setText("Descanso");
                            gvEjercicio.setBackgroundResource(R.drawable.stop);
                            seconds = 10;
                        }else{
                            tvCurrentEjercicio.setText(dataset.get(count/2).getName());
                            gvEjercicio.setBackgroundResource(dataset.get(count/2).getGifEjercicio());
                            seconds = dataset.get(count/2).getDuracion();
                            //seconds = 5;
                        }
                        tvContador.setText(String.valueOf(veces));
                        //seconds = 30;
                    }
                }else{
                    tvCurrentEjercicio.setText("Finalizado");
                }

                handler.postDelayed(this,1000);
            }
        });
    }
}