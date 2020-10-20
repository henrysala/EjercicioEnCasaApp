package com.example.ejercicioencasaapp.models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioencasaapp.views.EjerciciosRutinaActivity;
import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.Rutina;
import com.example.ejercicioencasaapp.views.RutinasFragment;

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
    public void onBindViewHolder(@NonNull final ViewHolderRutina holder, final int position) {
        final Rutina rutina = dataSet.get(position);

        holder.nombre.setText(rutina.getNombre());
        holder.cantidad.setText(String.valueOf(rutina.getCantidad()));
        holder.duracion.setText(String.valueOf(rutina.getDuracion()));
        holder.back_img.setImageResource(rutina.getImageRutina());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EjerciciosRutinaActivity.class);
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_OR_PLAN, 0);
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_EJERCICIO,rutina.getNombre());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_ID,rutina.getId());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_IMAGE,rutina.getImageRutina());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_CANTIDAD,rutina.getCantidad());
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

        public ViewHolderRutina(View view, final RutinasFragment rutinasFragment){
            super(view);

            nombre = (TextView)view.findViewById(R.id.tvNombreRutina);
            cantidad = (TextView)view.findViewById(R.id.tvCantidadEjercicios);
            duracion = (TextView)view.findViewById(R.id.tvDuracionRutina);
            back_img = (ImageView)view.findViewById(R.id.imageCard);

        }
    }
}
