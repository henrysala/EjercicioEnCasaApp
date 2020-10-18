package com.example.ejercicioencasaapp.views;

public class Plan {
    private int id, cantidad;
    private String nombre;

    public Plan(int id, String nombre, int cantidad){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Plan(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
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
}
