package com.example.ejercicioencasaapp.views;

public class Ejercicio {
    private int id, duracion, gif_ejercicio;
    private String nombre, descrip;

    public Ejercicio(int id, String nombre, int duracion, int gif_ejercicio, String descrip) {
        this.id = id;
        this.duracion = duracion;
        this.nombre = nombre;
        this.gif_ejercicio = gif_ejercicio;
        this.descrip = descrip;
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

    public String getDescrip() { return descrip;  }

}
