package com.example.ejercicioencasaapp.models;

public final class UtilitiesDataBase {
    static final String DATABASE_NAME="rutinasDeEjercicio";
    static final int VERSION=1;

    public class TablaEjercicios{
        static final String TABLE_NAME="ejercicios";
        static final String ID_EJERCICIO="id_ejercicio";
        static final String NAME="nombre";
        static final String DURATION="duracion";
        static final String GIF="gif";
        static final String DESCRIP="descrip";

        static final String CREATE_TABLE_EJERCICIOS="CREATE TABLE "+TABLE_NAME+" ("+ID_EJERCICIO+" INTEGER PRIMARY KEY, "
                +NAME+" TEXT, "+DURATION+" INTEGER, " + GIF + " INTEGER, " + DESCRIP + " TEXT)";

        static final String CONSULTAR_ALL_EJERCICIOS="SELECT * FROM "+TABLE_NAME;
    }

    //la tabla TablaRutinas es la que agrupara las rutinas de ejercicios del modulo Ejercicios
    public class TablaRutinas{
        static final String TABLE_NAME="rutinas";
        static final String ID_RUTINA="id_rutina";
        static final String NAME="nombre";
        static final String QUANTITY="cantidad";
        static final String DURATION="duracion";
        static final String IMAGE_RUTINA = "image_rutina";

        static final String CREATE_TABLE_RUTINAS="CREATE TABLE "+TABLE_NAME+" ("+ID_RUTINA+" INTEGER PRIMARY KEY, "
                +NAME+" TEXT, "+QUANTITY+" INTEGER, "+DURATION+" INTEGER, " + IMAGE_RUTINA+ " INTEGER)";

        static final String CONSULTAR_ALL_RUTINES="SELECT * FROM "+TABLE_NAME;

        static final String CONSULTAR_EJERCICIOS=
                "SELECT * FROM "+TablaEjercicios.TABLE_NAME+
                " WHERE "+TablaEjercicios.ID_EJERCICIO+" IN ( SELECT "+TablaEjerciciosRutina.ID_EJERCICIO+
                        " FROM "+TablaEjerciciosRutina.TABLE_NAME+" WHERE "+TablaEjerciciosRutina.ID_RUTINA+
                        " = ? )";
    }

    public class TablaPlanes{
        static final String TABLE_NAME="planes";
        static final String ID_PLAN="id_plan";
        static final String NAME="nombre";
        static final String CANTIDAD="cantidad";
        static final String IMAGE_PLAN="image_plan";

        static final String CREATE_TABLE_PLANES="CREATE TABLE "+TABLE_NAME+" ("+ID_PLAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME+" TEXT, "+CANTIDAD+" INTEGER, "+IMAGE_PLAN+" INTEGER)";

        static final String CONSULTAR_ALL_PLANES="SELECT * FROM "+TABLE_NAME;

        static final String CONSULTAR_EJERCICIOS=
                "SELECT * FROM "+TablaEjercicios.TABLE_NAME+
                        " WHERE "+TablaEjercicios.ID_EJERCICIO+" IN ( SELECT "+TablaEjerciciosPlan.ID_EJERCICIO+
                        " FROM "+TablaEjerciciosPlan.TABLE_NAME+" WHERE "+TablaEjerciciosPlan.ID_PLAN+
                        " = ? )";


        static final String OBTENER_CANTIDAD =
                "SELECT "+CANTIDAD+" FROM "+TABLE_NAME+" WHERE "+ID_PLAN+" = ?";

    }

    public class TablaEjerciciosRutina{
        static final String TABLE_NAME="ejerciciosRutina";
        static final String ID_EJERCICIO_RUTINA="idEjercicioRutina";
        static final String ID_RUTINA="id_rutina";
        static final String ID_EJERCICIO="id_ejercicio";


        static final String CREATE_TABLE_EJERCICIOS_RUTINA="CREATE TABLE "+TABLE_NAME+" ("+ID_EJERCICIO_RUTINA+
                " INTEGER PRIMARY KEY, "+ID_RUTINA+" INTEGER, "+ID_EJERCICIO+" INTEGER, "+
                "FOREIGN KEY ("+ID_RUTINA+") REFERENCES "+TablaRutinas.TABLE_NAME+" ("+TablaRutinas.ID_RUTINA+"), "+
                "FOREIGN KEY ("+ID_EJERCICIO+") REFERENCES "+TablaEjercicios.TABLE_NAME+" ("+TablaEjercicios.ID_EJERCICIO+"))";

    }

    public class TablaEjerciciosPlan{
        static final String TABLE_NAME="ejerciciosPlan";
        //static final String ID_EJERCICIO_PLAN="idEjercicioPlan";
        static final String ID_PLAN="id_plan";
        static final String ID_EJERCICIO="id_ejercicio";

        static final String CREATE_TABLE_EJERCICIOS_PLANES="CREATE TABLE "+TABLE_NAME+" ("+ID_PLAN+" INTEGER NOT NULL, "
                +ID_EJERCICIO+" INTEGER NOT NULL, PRIMARY KEY ("+ID_PLAN+", "+ID_EJERCICIO+"), " +
                "FOREIGN KEY ("+ID_PLAN+") REFERENCES "+TablaPlanes.TABLE_NAME+" ("+TablaPlanes.ID_PLAN+") " +
                "ON UPDATE CASCADE,"+
                "FOREIGN KEY ("+ID_EJERCICIO+") REFERENCES "+TablaEjercicios.TABLE_NAME+" ("+TablaEjercicios.ID_EJERCICIO+") " +
                "ON UPDATE CASCADE)";

    }
}
