package com.example.ejercicioencasaapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ejercicioencasaapp.R;


public class MisPlanesFragment extends Fragment {
    private Button btnAgregarPlan;

    public MisPlanesFragment() {

    }

    public static MisPlanesFragment newInstance() {
        return new MisPlanesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_planes, container, false);
        //return inflater.inflate(R.layout.fragment_mis_planes, container, false);

        btnAgregarPlan = (Button)view.findViewById(R.id.btnAgregarPlan);
        btnAgregarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ListaCompletaEjerciciosActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }
}