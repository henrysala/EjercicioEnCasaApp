package com.example.ejercicioencasaapp.models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.DescripcionEjercicioActivity;
import com.example.ejercicioencasaapp.views.Ejercicio;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class AdaptadorListaEjercicios extends RecyclerView.Adapter<AdaptadorListaEjercicios.ViewHolderCVListaEjercicios> {
    private ArrayList<Ejercicio> dataset;
    private int idPlan;


    public AdaptadorListaEjercicios(ArrayList<Ejercicio> ejercicios){
        dataset = ejercicios;
    }

    @NonNull
    @Override
    public ViewHolderCVListaEjercicios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ejercicios,parent,false);
        return new ViewHolderCVListaEjercicios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVListaEjercicios holder, int position) {
        final Ejercicio ejercicio = dataset.get(position);

        holder.nombreEjercicio.setText(ejercicio.getName());
        holder.duracionEjercicio.setText(String.valueOf(ejercicio.getDuracion()));
        holder.gif_ejercicio.setBackgroundResource(ejercicio.getGifEjercicio());

        //al pulsar sobre un ejercicio de la lista se abre el activit descripcion y se pasan el id el nombre
        //y el valor true indica que esta agregando un ejercicio para habilitar el boton agregar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DescripcionEjercicioActivity.class);
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_AGREGAR,true);
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_ID_PLAN,idPlan);
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_ID_EJERCICIO,ejercicio.getId());
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_NOMBRE_EJERCICIO,ejercicio.getName());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolderCVListaEjercicios extends RecyclerView.ViewHolder {
        private TextView nombreEjercicio, duracionEjercicio;
        private GifImageView gif_ejercicio;

        public ViewHolderCVListaEjercicios(@NonNull View itemView) {
            super(itemView);

            nombreEjercicio = (TextView)itemView.findViewById(R.id.tvNombreEjercicio);
            duracionEjercicio = (TextView)itemView.findViewById(R.id.tvDuracionEjercicio);
            gif_ejercicio = (GifImageView)itemView.findViewById(R.id.gifImageView);
            duracionEjercicio = (TextView)itemView.findViewById(R.id.tvDuracionEjercicio);
        }
    }

    public void enviarIdPlan(int idRecivido){
        idPlan = idRecivido;
    }
}
