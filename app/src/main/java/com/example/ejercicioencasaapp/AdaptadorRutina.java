package com.example.ejercicioencasaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRutina extends RecyclerView.Adapter<AdaptadorRutina.ViewHolderRutina> {
    //private Context context;
    private ArrayList<Rutina> dataSet;
    private RutinasFragment rutinasFragment;

    public AdaptadorRutina(ArrayList<Rutina> rutinas, RutinasFragment rutinasFragment) {
        //this.context = context;
        dataSet = rutinas;
        this.rutinasFragment = rutinasFragment;
    }

    @NonNull
    @Override
    public AdaptadorRutina.ViewHolderRutina onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_rutinas,parent,false);
        return new ViewHolderRutina(view, rutinasFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRutina holder, final int position) {
        final Rutina rutina = dataSet.get(position);
        //holder.id.setText(String.valueOf(rutina.getId()));
        holder.nombre.setText(rutina.getNombre());
        holder.cantidad.setText(String.valueOf(rutina.getCantidad()));
        holder.duracion.setText(String.valueOf(rutina.getDuracion()));
        holder.back_img.setImageResource(R.drawable.cardio_rutina);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EjerciciosRutinaActivity.class);
                //intent.putExtra(EjerciciosRutinaActivity.RUTINA,rutina.getNombre());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_EJERCICIO,rutina.getNombre());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderRutina extends RecyclerView.ViewHolder{
        private ImageView back_img;
        private TextView id, nombre, cantidad, duracion;
        //private Button abrir;
        public ViewHolderRutina(View view, final RutinasFragment rutinasFragment){
            super(view);

            nombre = (TextView)view.findViewById(R.id.tvNombreRutina);
            cantidad = (TextView)view.findViewById(R.id.tvCantidadEjercicios);
            duracion = (TextView)view.findViewById(R.id.tvDuracionRutina);
            back_img = (ImageView)view.findViewById(R.id.imageCard);


            /*
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Integer.parseInt(id.getText().toString())
                    //rutinasFragment.verEjerciciosRutina();


                }
            });

             */
        }
    }
}
