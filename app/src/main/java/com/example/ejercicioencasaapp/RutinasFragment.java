package com.example.ejercicioencasaapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RutinasFragment extends Fragment {
    private RecyclerView recyclerViewRutina;


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
        return inflater.inflate(R.layout.fragment_rutinas, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        recyclerViewRutina = (RecyclerView)view.findViewById(R.id.rvListaRutinas);

        ArrayList<Rutina> dataSet = new ArrayList<Rutina>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewRutina.setLayoutManager(layoutManager);
        RutinaDAO rutinaDAO = new RutinaDAO(getContext());
        dataSet = rutinaDAO.consultarRutinas();
        recyclerViewRutina.setAdapter(new AdaptadorRutina(dataSet));


    }

}