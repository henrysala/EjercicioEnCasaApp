package com.example.ejercicioencasaapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.EjercicioDAO;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class ReproductorRutinaActivity extends AppCompatActivity {

    public static final String EXTRA_RUTINA_OR_PLAN = "rutina_or_plan";
    public static final String EXTRA_RUTINA_NAME = "rutina_nombre";
    public static final String EXTRA_RUTINA_ID = "id_rutina";
    public static final String EXTRA_RUTINA_CANTIDAD = "cantidad_rutina";
    private String nombreRutina;
    private TextView tvNombreRutina, tvCurrentEjercicio, tvTimer, tvContador;
    private Button btnPlay;
    private ImageButton btnNext,btnPrev;
    private int idRutina, cantidadRutina, rutinaOrPlan;
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

        rutinaOrPlan = intent.getIntExtra(EXTRA_RUTINA_OR_PLAN,0);
        nombreRutina = intent.getStringExtra(EXTRA_RUTINA_NAME);
        idRutina = intent.getIntExtra(EXTRA_RUTINA_ID,1);
        tvNombreRutina = (TextView)findViewById(R.id.tvCurrentRutina);
        tvNombreRutina.setText(String.valueOf(nombreRutina));

        gvEjercicio = (GifImageView)findViewById(R.id.gvEjercicio);

        cantidadRutina = intent.getIntExtra(EXTRA_RUTINA_CANTIDAD,0);
        tvContador = (TextView)findViewById(R.id.tvReproductor);
        tvContador.setText(String.valueOf(cantidadRutina));


        running = true;
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnNext = (ImageButton)findViewById(R.id.btnNext);
        btnPrev = (ImageButton)findViewById(R.id.btnPrev);

        //Estoy tratando de crear nuevamente la lista de ejercicios de la rutina
        ArrayList<Ejercicio> dataset;
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        if (rutinaOrPlan == 0) {
            dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);
        }else{
            dataset = ejercicioDAO.consultarEjerciciosPlan(idRutina);
        }

        tvCurrentEjercicio = (TextView)findViewById(R.id.tVCurrentEjercicio);
        //tvCurrentEjercicio.setText(dataset.get(0).getName());
        tvCurrentEjercicio.setText("");
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

    public void onClickNext(View view){
        seconds = 0;
    }
    public void onClickPrev(View view){
        //seconds = 0;
        count -= 2;
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
        final MediaPlayer mediaPlayer;

        mediaPlayer = MediaPlayer.create(this, R.raw.pitido);
        // no need to call prepare(); create() does that for you

        handler.post(new Runnable() {
            @Override
            public void run() {

                //int sec = seconds%60;
                String time = String.format("%02d ",seconds);
                timeView.setText(time);
                btnPrev.setVisibility(View.INVISIBLE);

                if(count>0){
                    //btnPrev.setVisibility(View.VISIBLE);
                }

                if(count < veces*2){

                    if(running == true){
                        seconds--;
                    }
                    if(seconds == 3){
                        mediaPlayer.start(); //reproduce el sonido cuando quedan 3 segundos
                    }
                    if(seconds == -1){
                        count++;

                        if(count == veces*2){
                            seconds = 0;
                            tvCurrentEjercicio.setText("Finalizado");
                            gvEjercicio.setVisibility(View.INVISIBLE);
                            btnNext.setVisibility(View.INVISIBLE);
                        }


                        else if(count%2 == 0){
                            tvCurrentEjercicio.setText("");
                            gvEjercicio.setBackgroundResource(R.drawable.stop);
                            seconds = 10;
                        }
                        else {
                            tvCurrentEjercicio.setText(dataset.get(count/2).getName());
                            gvEjercicio.setBackgroundResource(dataset.get(count/2).getGifEjercicio());
                            seconds = dataset.get(count/2).getDuracion();
                            //seconds = 5;
                        }


                        tvContador.setText(String.valueOf(veces));
                        //seconds = 30;
                    }

                }
                /*
                else{
                    seconds = 0;
                    tvCurrentEjercicio.setText("Finalizado");
                    btnNext.setVisibility(View.INVISIBLE);
                }

                 */

                handler.postDelayed(this,1000);
            }
        });
    }
}