package com.example.ejercicioencasaapp.views;

public class EjercicioPlan {
    private int id, idPlan, idEjercicio;

    public EjercicioPlan(int id, int idPlan, int idEjercicio) {
        this.id = id;
        this.idPlan = idPlan;
        this.idEjercicio = idEjercicio;
    }

    public EjercicioPlan(int idPlan, int idEjercicio) {
        this.idPlan = idPlan;
        this.idEjercicio = idEjercicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
}
