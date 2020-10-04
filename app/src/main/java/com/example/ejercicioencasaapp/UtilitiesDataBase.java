package com.example.ejercicioencasaapp;

public final class UtilitiesDataBase {
    static final String DATABASE_NAME="rutinasDeEjercicio";
    static final int VERSION=1;

    public class TablaEjercicios{
        static final String TABLE_NAME="ejercicios";
        static final String ID_EJERCICIO="id_ejercicio";
        static final String NAME="nombre";
        static final String DURATION="duracion";

        static final String CREATE_TABLE_EJERCICIOS="CREATE TABLE "+TABLE_NAME+" ("+ID_EJERCICIO+" INTEGER PRIMARY KEY, "
                +NAME+" TEXT, "+DURATION+" INTEGER)";

        static final String CONSULTAR_ALL_EJERCICIOS="SELECT * FROM "+TABLE_NAME;
    }

    //la tabla TablaRutinas es la que agrupara las rutinas de ejercicios del modulo Ejercicios
    public class TablaRutinas{
        static final String TABLE_NAME="rutinas";
        static final String ID_RUTINA="id_rutina";
        static final String NAME="nombre";
        static final String QUANTITY="cantidad";
        static final String DURATION="duracion";

        static final String CREATE_TABLE_RUTINAS="CREATE TABLE "+TABLE_NAME+" ("+ID_RUTINA+" INTEGER PRIMARY KEY, "
                +NAME+" TEXT, "+QUANTITY+" INTEGER, "+DURATION+" INTEGER)";

        static final String CONSULTAR_ALL_RUTINES="SELECT * FROM "+TABLE_NAME;

        static final String CONSULTAR_EJERCICIOS=
                "SELECT * FROM "+TablaEjercicios.TABLE_NAME+
                " WHERE "+TablaEjercicios.ID_EJERCICIO+" IN ( SELECT "+TablaEjerciciosRutina.ID_EJERCICIO+
                        " FROM "+TablaEjerciciosRutina.TABLE_NAME+" WHERE "+TablaEjerciciosRutina.ID_RUTINA+
                        " = ? )";
    }

    public class TablaEjerciciosRutina{
        static final String TABLE_NAME="ejerciciosRutina";
        static final String ID_EJERCICIO_RUTINA="idEjercicioRutina";
        static final String ID_RUTINA="id_rutina";
        static final String ID_EJERCICIO="id_ejercicio";


        static final String CREATE_TABLE_EJERCICIOS_RUTINA="CREATE TABLE "+TABLE_NAME+" ("+ID_EJERCICIO_RUTINA+
                " INTEGER PRIMARY KEY, "+ID_RUTINA+" INTEGER, "+ID_EJERCICIO+" INTEGER, "+
                "FOREIGN KEY ("+ID_RUTINA+") REFERENCES "+ TablaRutinas.TABLE_NAME+" ("+TablaRutinas.ID_RUTINA+"), "+
                "FOREIGN KEY ("+ID_EJERCICIO+") REFERENCES "+TablaEjercicios.TABLE_NAME+" ("+TablaEjercicios.ID_EJERCICIO+"))";

    }
}
