package com.example.ejercicioencasaapp.views;

public class Ejercicio {
    private int id, duracion, gif_ejercicio;
    private String nombre;

    public Ejercicio(int id, String nombre, int duracion, int gif_ejercicio) {
        this.id = id;
        this.duracion = duracion;
        this.nombre = nombre;
        this.gif_ejercicio = gif_ejercicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public int getGifEjercicio() { return gif_ejercicio; }
}
