package com.example.ejercicioencasaapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EjerciciosFragment extends Fragment {

    public EjerciciosFragment() {
        // Required empty public constructor
    }

    public static EjerciciosFragment newInstance() {

        return new EjerciciosFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);
        return view;
    }
}