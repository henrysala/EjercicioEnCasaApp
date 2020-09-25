package com.example.ejercicioencasaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRutina extends RecyclerView.Adapter<AdaptadorRutina.ViewHolderRutina> {

    ArrayList<Rutina> dataSet;

    public AdaptadorRutina(ArrayList<Rutina> rutinas) {
        dataSet = rutinas;
    }

    @NonNull
    @Override
    public AdaptadorRutina.ViewHolderRutina onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_rutinas,parent,false);
        return new ViewHolderRutina(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRutina holder, int position) {
        final Rutina rutina = dataSet.get(position);
        holder.nombre.setText(rutina.getNombre());
        holder.cantidad.setText(String.valueOf(rutina.getCantidad()));
        holder.duracion.setText(String.valueOf(rutina.getDuracion()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderRutina extends RecyclerView.ViewHolder{
        private TextView nombre, cantidad, duracion;
        public ViewHolderRutina(View view){
            super(view);
            nombre = (TextView)view.findViewById(R.id.tvNombreRutina);
            cantidad = (TextView)view.findViewById(R.id.tvCantidadEjercicios);
            duracion = (TextView)view.findViewById(R.id.tvDuracionRutina);
        }
    }
}
