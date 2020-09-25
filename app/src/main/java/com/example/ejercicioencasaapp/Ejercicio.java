package com.example.ejercicioencasaapp;

public class Ejercicio {
    private int id, duracion;
    private String name;

    public Ejercicio(int id, int duracion, String name) {
        this.id = id;
        this.duracion = duracion;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
