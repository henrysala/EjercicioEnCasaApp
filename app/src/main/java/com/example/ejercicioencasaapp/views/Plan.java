package com.example.ejercicioencasaapp.views;

import android.widget.ImageView;

public class Plan {
    private int id, cantidad;
    private String nombre;
    private int imagePlan;

    public Plan(int id, String nombre, int cantidad, int imagePlan){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagePlan = imagePlan;
    }

    public Plan(String nombre, int cantidad, int imagePlan){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagePlan = imagePlan;
    }



    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagePlan() {
        return imagePlan;
    }

    public void setImagePlan(int imagePlan) {
        this.imagePlan = imagePlan;
    }
}
