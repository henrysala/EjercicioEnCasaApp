package com.example.ejercicioencasaapp.views;

public class Rutina {
    private int id, cantidad, duracion;
    private String nombre;
    private int imageRutina;

    public Rutina(int id, String nombre, int cantidad, int duracion, int imageRutina) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.duracion = duracion;
        this.imageRutina = imageRutina;
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

    public int getImageRutina() { return imageRutina;   }
}
