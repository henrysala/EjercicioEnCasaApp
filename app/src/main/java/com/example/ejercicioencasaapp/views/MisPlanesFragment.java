package com.example.ejercicioencasaapp.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejercicioencasaapp.R;


public class MisPlanesFragment extends Fragment {

    public MisPlanesFragment() {

    }

    public static MisPlanesFragment newInstance() {
        return new MisPlanesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_planes, container, false);
    }
}