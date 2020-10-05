package com.example.ejercicioencasaapp.views;

public class Rutina {
    private int id, cantidad, duracion;
    private String nombre;

    public Rutina(int id, String nombre, int cantidad, int duracion) {
        this.id = id;
        this.cantidad = cantidad;
        this.duracion = duracion;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
