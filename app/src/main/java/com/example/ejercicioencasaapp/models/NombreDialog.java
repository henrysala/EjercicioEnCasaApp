package com.example.ejercicioencasaapp.models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.views.ListaCompletaEjerciciosActivity;
import com.example.ejercicioencasaapp.views.Plan;

public class NombreDialog extends AppCompatDialogFragment {
    private EditText editTextNombre;
    private NombreDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.insertar_nombre_plan,null);

        builder.setView(view)
                .setTitle("Nombre")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String nombre = editTextNombre.getText().toString();
                        listener.applyText(nombre);

                        Plan plan = new Plan(nombre, 2);
                        PlanDAO planDAO = new PlanDAO(getContext());
                        long id = planDAO.insertarPlan(plan);
                        if(id >-1){
                            Toast.makeText(getContext(), "Id = "+id,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Fallo en la conexión",Toast.LENGTH_SHORT).show();
                        }
                        getActivity().finish();
                    }
                });

        editTextNombre = view.findViewById(R.id.etPonerNombrePlan);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NombreDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + "must implement NombreDialogListener");
        }
    }

    public interface NombreDialogListener{
        void applyText(String nombre);
    }
}
