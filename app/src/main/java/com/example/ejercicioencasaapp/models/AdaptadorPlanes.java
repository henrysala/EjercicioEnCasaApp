package com.example.ejercicioencasaapp.models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.Ejercicio;
import com.example.ejercicioencasaapp.views.EjerciciosRutinaActivity;
import com.example.ejercicioencasaapp.views.MisPlanesFragment;
import com.example.ejercicioencasaapp.views.Plan;
import com.example.ejercicioencasaapp.views.Rutina;
import com.example.ejercicioencasaapp.views.RutinasFragment;

import java.util.ArrayList;

public class AdaptadorPlanes extends RecyclerView.Adapter<AdaptadorPlanes.ViewHolderPlan> {
    private ArrayList<Plan> dataset;
    private MisPlanesFragment misPlanesFragment;

    public AdaptadorPlanes(ArrayList<Plan> planes, MisPlanesFragment misPlanesFragment) {
        dataset = planes;
        this.misPlanesFragment = misPlanesFragment;
    }

    @NonNull
    @Override
    public AdaptadorPlanes.ViewHolderPlan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_planes,parent,false);
        return new ViewHolderPlan(view, misPlanesFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPlanes.ViewHolderPlan holder, int position) {

        final Plan plan = dataset.get(position);
        holder.nombre.setText(plan.getNombre());
        holder.cantidad.setText(String.valueOf(plan.getCantidad()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EjerciciosRutinaActivity.class);
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_OR_PLAN, 1);
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_EJERCICIO,plan.getNombre());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_ID,plan.getId());
                //intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_IMAGE,rutina.getImageRutina());
                intent.putExtra(EjerciciosRutinaActivity.EXTRA_RUTINA_CANTIDAD,plan.getCantidad());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolderPlan extends RecyclerView.ViewHolder {
        private TextView nombre, cantidad;

        public ViewHolderPlan(@NonNull View itemView, final MisPlanesFragment misPlanesFragment) {
            super(itemView);

            nombre = (TextView)itemView.findViewById(R.id.tvNombrePlan);
            cantidad = (TextView)itemView.findViewById(R.id.tvCantidadEjPlan);
        }
    }
}
