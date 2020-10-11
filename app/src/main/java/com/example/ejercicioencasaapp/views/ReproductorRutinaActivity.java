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

public class ReproductorRutinaActivity extends AppCompatActivity {
    public static final String EXTRA_REPRODUCTOR = "rutinaNombre";
    private String nombreRutina;
    private TextView tvNombreRutina, tvCurrentEjercicio;
    private Button btnPlay;
    private int seconds = 30;
    public int count = 0;
    private boolean running, wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_rutina);

        Intent intent = getIntent();

        nombreRutina = intent.getStringExtra(EXTRA_REPRODUCTOR);
        tvNombreRutina = (TextView)findViewById(R.id.tvReproductor);
        tvNombreRutina.setText(String.valueOf(nombreRutina));

        tvCurrentEjercicio = (TextView)findViewById(R.id.tvCurrentEjercicio);
        tvCurrentEjercicio.setText(String.valueOf(count));
        running = true;
        btnPlay = (Button)findViewById(R.id.btnPlay);

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
        if(wasRunning){
            running = true;
            btnPlay.setText("pause");
        }
    }

    @Override
    //Al cambiar de ventana en el telefono el reproductor se pausa automaticamente
    protected void onStop() {
        super.onStop();
        wasRunning = false;
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
                    tvCurrentEjercicio.setText(String.valueOf(count));
                    seconds = 30;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}