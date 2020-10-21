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

import org.w3c.dom.Text;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class AdaptadorRutinaLista extends RecyclerView.Adapter<AdaptadorRutinaLista.ViewHolderCVRutinaLista> {
    private ArrayList<Ejercicio> dataset;
    private int idPlan;

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
        holder.gif_ejercicio.setBackgroundResource(ejercicio.getGifEjercicio());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DescripcionEjercicioActivity.class);
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_ID_PLAN,idPlan);
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_ID_EJERCICIO,ejercicio.getId());
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_NOMBRE_EJERCICIO,ejercicio.getName());
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_DESCRIP_EJERCICIO,ejercicio.getDescrip());
                intent.putExtra(DescripcionEjercicioActivity.EXTRA_GIF,ejercicio.getGifEjercicio());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolderCVRutinaLista extends RecyclerView.ViewHolder {
        //private ImageView ivEjercicio;
        private TextView nombreEjercicio, duracionEjercicio, description;
        private GifImageView gif_ejercicio;
        //private Button btnComenzarRutina;

        public ViewHolderCVRutinaLista(View view){
          super(view);
            //ivEjercicio = (ImageView)view.findViewById(R.id.ivEjercicio);
            nombreEjercicio = (TextView)view.findViewById(R.id.tvNombreEjercicio);
            duracionEjercicio = (TextView)view.findViewById(R.id.tvDuracionEjercicio);
            gif_ejercicio = (GifImageView)view.findViewById(R.id.gifImageView);
            //btnComenzarRutina = (Button)view.findViewById(R.id.btnComenzarRutina);
        }

    }
    //se recibe el id de plan desde el activity
    public void enviarIdPlan(int idRecibido){
        idPlan = idRecibido;
    }
}
