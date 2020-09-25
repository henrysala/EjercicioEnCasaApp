package com.example.ejercicioencasaapp;

public final class UtilitiesDataBase {
    static final String DATABASE_NAME="ejercicios";
    static final int VERSION=1;

    public class TablaEjercicios{
        static final String TABLE_NAME="ejercicios";
        static final String ID="id";
        static final String NAME="nombre";
        static final String DURATION="duracion";

        static final String CREATE_TABLE_EJERCICIOS="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY, "
                +NAME+" TEXT, "+DURATION+" INTEGER)";

        static final String CONSULTAR_ALL_TABLE="SELECT * FROM "+TABLE_NAME;
    }
}
