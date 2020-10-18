package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorListaEjercicios;
import com.example.ejercicioencasaapp.models.AdaptadorRutinaLista;
import com.example.ejercicioencasaapp.models.EjercicioDAO;
import com.example.ejercicioencasaapp.models.NombreDialog;
import com.example.ejercicioencasaapp.models.PlanDAO;

import java.util.ArrayList;

public class ListaCompletaEjerciciosActivity extends AppCompatActivity {
    public static final String EXTRA_ID_PLAN = "id_plan";
    public static Context context;
    private RecyclerView recyclerViewEjercicios;
    private TextView tvIdPlan;
    private int idPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_completa_ejercicios);
        final Intent intent = getIntent();

        //guardo el contex de esta activity para cerrarlo al agregar un ejercicio a un plan
        context = this;

        //se obtiene el id del plan que va a agregar ejercicios
        idPlan = intent.getIntExtra(EXTRA_ID_PLAN, 0);

        tvIdPlan = (TextView)findViewById(R.id.tvIdPlan);
        tvIdPlan.setText(String.valueOf(idPlan));

        //se muestra la lista con todos los ejercicios
        recyclerViewEjercicios = (RecyclerView)findViewById(R.id.rvAllEjercicios);

        final ArrayList<Ejercicio> dataset;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewEjercicios.setLayoutManager(layoutManager);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(this);
        dataset = ejercicioDAO.consultarAllEjercicios();
        AdaptadorListaEjercicios adaptadorListaEjercicios = new AdaptadorListaEjercicios(dataset);
        recyclerViewEjercicios.setAdapter(adaptadorListaEjercicios);

        //se envia el id del plan al adaptador
        adaptadorListaEjercicios.enviarIdPlan(idPlan);

    }

}