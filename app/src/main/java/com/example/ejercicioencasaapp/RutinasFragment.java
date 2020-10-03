package com.example.ejercicioencasaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class RutinasFragment extends Fragment {
    private RecyclerView recyclerViewRutina;
    private  RutinaDAO rutinaDAO;


    public RutinasFragment() {
        // Required empty public constructor
    }

    public static RutinasFragment newInstance() {

        return new RutinasFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);

        recyclerViewRutina = (RecyclerView)view.findViewById(R.id.rvListaRutinas);

        ArrayList<Rutina> dataSet = new ArrayList<Rutina>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewRutina.setLayoutManager(layoutManager);
        rutinaDAO = new RutinaDAO(getContext());
        dataSet = rutinaDAO.consultarRutinas();
        recyclerViewRutina.setAdapter(new AdaptadorRutina(dataSet,this));

        return view;
    }

    /*
    public void verEjerciciosRutina() {
        //rutinaDAO = new RutinaDAO(getContext());
        //rutinaDAO.consultarRutinas();
        Context context = getContext();
        Intent intent = new Intent(getActivity(),EjerciciosRutinaActivity.class);
        //startActivity(intent);
        context.startActivity(intent);
    }
    */
    @Override
    public void onStart() {
        super.onStart();



    }

}