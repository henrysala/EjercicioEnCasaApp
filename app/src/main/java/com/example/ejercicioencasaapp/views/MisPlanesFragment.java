package com.example.ejercicioencasaapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.AdaptadorPlanes;
import com.example.ejercicioencasaapp.models.PlanDAO;

import java.util.ArrayList;


public class MisPlanesFragment extends Fragment {
    private RecyclerView recyclerViewPlanes;
    private Button btnAgregarPlan;

    public MisPlanesFragment() {
        // Required empty public constructor
    }

    public static MisPlanesFragment newInstance() {
        return new MisPlanesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_planes, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        assert view != null;

        //return inflater.inflate(R.layout.fragment_mis_planes, container, false);
        recyclerViewPlanes = (RecyclerView)view.findViewById(R.id.rvListaPlanes);

        //mostrar la lista de planes
        ArrayList<Plan> dataset = new ArrayList<Plan>();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        //layoutManager.setOrientation(GridLayout);
        recyclerViewPlanes.setLayoutManager(layoutManager);
        PlanDAO planDAO = new PlanDAO(getContext());
        dataset = planDAO.consultarPlanes();
        recyclerViewPlanes.setAdapter(new AdaptadorPlanes(dataset, this));

        btnAgregarPlan = (Button)view.findViewById(R.id.btnAgregarPlan);
        btnAgregarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ListaCompletaEjerciciosActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }
}