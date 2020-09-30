package com.example.ejercicioencasaapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorRutinaLista extends RecyclerView.Adapter<AdaptadorRutinaLista.ViewHolderCVRutinaLista> {
    @NonNull
    @Override
    public ViewHolderCVRutinaLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVRutinaLista holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderCVRutinaLista extends RecyclerView.ViewHolder {
        private ImageView ivEjercicio;
        private TextView titleEjercicio;
        private TextView tiempoEjercicio;

        public ViewHolderCVRutinaLista(View view){
            super(view);
            ivEjercicio = (ImageView)view.findViewById(R.id.ivEjercicio);
            titleEjercicio = (TextView)view.findViewById(R.id.tvCantidadE);
            tiempoEjercicio     = (TextView)view.findViewById(R.id.tvDuracion);
        }
    }
}
