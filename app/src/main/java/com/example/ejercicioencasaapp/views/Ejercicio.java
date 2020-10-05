package com.example.ejercicioencasaapp.views;

public class Ejercicio {
    private int id, duracion;
    private String nombre;

    public Ejercicio(int id, String nombre, int duracion) {
        this.id = id;
        this.duracion = duracion;
        this.nombre = nombre;
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
}
