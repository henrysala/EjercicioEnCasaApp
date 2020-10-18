package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

public class ListaCompletaEjerciciosActivity extends AppCompatActivity implements NombreDialog.NombreDialogListener {
    private RecyclerView recyclerViewEjercicios;
    private Button crearPlan;
    private TextView tvNombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_completa_ejercicios);


        tvNombre  = (TextView)findViewById(R.id.tvEditable);

        crearPlan = (Button)findViewById(R.id.btnCrearPlan);
        crearPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                openDialog();
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

    private void openDialog() {
        NombreDialog nombreDialog = new NombreDialog();
        nombreDialog.show(getSupportFragmentManager(),"nombre dialog");
    }

    @Override
    public void applyText(String nombre) {
        tvNombre.setText(nombre);
    }


    //private void guardarPlan() {
        //String nombre = editText.getText().toString();

        //Plan plan = new Plan(nombre, 2);
        //PlanDAO planDAO = new PlanDAO(getBaseContext());
        //long id = planDAO.insertarPlan(plan);
        //if(id >-1){
        //    Toast.makeText(getBaseContext(), "Id = "+id,Toast.LENGTH_SHORT).show();
        //}else {
        //    Toast.makeText(getBaseContext(), "Fallo en la conexión",Toast.LENGTH_SHORT).show();
        //}
    //}

}