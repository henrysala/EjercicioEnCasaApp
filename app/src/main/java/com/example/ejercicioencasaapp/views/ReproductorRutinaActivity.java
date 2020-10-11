package com.example.ejercicioencasaapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.EjercicioDAO;

import java.util.ArrayList;

public class ReproductorRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_NOMBRE_RUTINA = "rutinaNombre";
    public static final String EXTRA_ID_RUTINA = "rutinaID";
    private String nombreRutina,nombreEjercicio;
    private int idRutina;
    private TextView tvNombreRutina, tvCurrentEjercicio, tvIDRutina;
    private Button btnPlay;
    private int seconds = 15;
    public int count = 0;
    private boolean running, wasRunning;
    private ArrayList<Ejercicio> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_rutina);

        Intent intent = getIntent();

        //ArrayList<Ejercicio> dataset = (ArrayList<Ejercicio>) getIntent().getSerializableExtra("dataset");
        //ArrayList<? extends Ejercicio> dataset = getIntent().getParcelableArrayListExtra("listaEjercicios");


        nombreRutina = intent.getStringExtra(EXTRA_NOMBRE_RUTINA);
        idRutina = intent.getIntExtra(EXTRA_ID_RUTINA,0);

        tvNombreRutina = (TextView)findViewById(R.id.tvReproductorNombreRutina);
        tvNombreRutina.setText(String.valueOf(nombreRutina));

        tvIDRutina = (TextView)findViewById(R.id.tvReproductorIdRutina);
        tvIDRutina.setText(String.valueOf(idRutina));


        running = true;
        btnPlay = (Button)findViewById(R.id.btnPlay);

        //Estoy tratando de crear nuevamente la lista de ejercicios de la rutina
        ArrayList<Ejercicio> dataset;
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarEjerciciosRutina(idRutina);

        tvCurrentEjercicio = (TextView)findViewById(R.id.tvReproductorCurrentEjercicio);
        //tvCurrentEjercicio.setText(dataset.get(0).getName());
        tvCurrentEjercicio.setText("PREPARATE");


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
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
        final TextView timeView = (TextView)findViewById(R.id.tvReproductorTimer);
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


                }
                handler.postDelayed(this,1000);
            }
        });
    }
}