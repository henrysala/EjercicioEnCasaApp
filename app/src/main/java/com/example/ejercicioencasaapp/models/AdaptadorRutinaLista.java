package com.example.ejercicioencasaapp.models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.Ejercicio;
import com.example.ejercicioencasaapp.views.ReproductorRutinaActivity;

import java.util.ArrayList;

public class AdaptadorRutinaLista extends RecyclerView.Adapter<AdaptadorRutinaLista.ViewHolderCVRutinaLista> {
    private ArrayList<Ejercicio> dataset;

    public AdaptadorRutinaLista(ArrayList<Ejercicio> ejercicios){
        dataset = ejercicios;
    }

    @NonNull
    @Override
    public AdaptadorRutinaLista.ViewHolderCVRutinaLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ejercicios,parent,false);
        return new ViewHolderCVRutinaLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVRutinaLista holder, int position) {
        final Ejercicio ejercicio = dataset.get(position);
        holder.nombreEjercicio.setText(ejercicio.getName());
        holder.duracionEjercicio.setText(String.valueOf(ejercicio.getDuracion()));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolderCVRutinaLista extends RecyclerView.ViewHolder {
        //private ImageView ivEjercicio;
        private TextView nombreEjercicio, duracionEjercicio;
        //private Button btnComenzarRutina;

        public ViewHolderCVRutinaLista(View view){
          super(view);
            //ivEjercicio = (ImageView)view.findViewById(R.id.ivEjercicio);
            nombreEjercicio = (TextView)view.findViewById(R.id.tvNombreEjercicio);
            duracionEjercicio     = (TextView)view.findViewById(R.id.tvDuracionEjercicio);
            //btnComenzarRutina = (Button)view.findViewById(R.id.btnComenzarRutina);
        }

    }
}
