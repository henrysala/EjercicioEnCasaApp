package com.example.ejercicioencasaapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseIntArray;

import androidx.annotation.Nullable;

import com.example.ejercicioencasaapp.R;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    public DataBaseOpenHelper(@Nullable Context context) {
        super(context, UtilitiesDataBase.DATABASE_NAME, null, UtilitiesDataBase.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Se crea la tabla de ejercicios
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjercicios.CREATE_TABLE_EJERCICIOS);
        //se agregan los ejercicios a la tabla
        insertEjercicio(sqLiteDatabase, 0,"Abdomen con toque al talón", 60, R.drawable.abdomen_toque_talon);
        insertEjercicio(sqLiteDatabase, 1,"Crunch abdominal", 30, R.drawable.crunch_abs);
        insertEjercicio(sqLiteDatabase, 2,"Plancha", 30, R.drawable.plank);
        insertEjercicio(sqLiteDatabase, 3,"Flexión de pecho con apoyo", 40, R.drawable.lagartija_con_apoyo);
        insertEjercicio(sqLiteDatabase, 4,"Tijera de brazos", 30, R.drawable.tijeras_brazo);
        insertEjercicio(sqLiteDatabase, 5,"Elevación lateral de brazos", 30, R.drawable.lateral_brazos);
        insertEjercicio(sqLiteDatabase, 6,"Inclinación lateral", 30, R.drawable.lateral_brazos);
        insertEjercicio(sqLiteDatabase, 7,"Sentadillas", 30, R.drawable.squat_color);
        insertEjercicio(sqLiteDatabase, 8,"Titere", 30, R.drawable.titere);
        insertEjercicio(sqLiteDatabase, 9,"Elevacion de pantorrilla", 40, R.drawable.calf);

        //Se crea la tabla de rutinas
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaRutinas.CREATE_TABLE_RUTINAS);

        //Se agregan las rutinas a la tabla
        insertRutina(sqLiteDatabase,0,"Quemar grasa", 3, 140, R.drawable.quemar_grasa);
        insertRutina(sqLiteDatabase,1,"Tonificar tren superior", 4, 160, R.drawable.tren_superior);
        insertRutina(sqLiteDatabase,2,"Tonificar tren inferior", 3, 120, R.drawable.tren_inferior);


        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjerciciosRutina.CREATE_TABLE_EJERCICIOS_RUTINA);
        //Ejercicios que pertenecen a una rutina
        insertEjerciciosRutina(sqLiteDatabase,00, 0,0);
        insertEjerciciosRutina(sqLiteDatabase,01, 0,1);
        insertEjerciciosRutina(sqLiteDatabase,02, 0,2);

        insertEjerciciosRutina(sqLiteDatabase,13, 1,3);
        insertEjerciciosRutina(sqLiteDatabase,14, 1,4);
        insertEjerciciosRutina(sqLiteDatabase,15, 1,5);
        insertEjerciciosRutina(sqLiteDatabase,16, 1,6);

        insertEjerciciosRutina(sqLiteDatabase,27, 2,7);
        insertEjerciciosRutina(sqLiteDatabase,28, 2,8);
        insertEjerciciosRutina(sqLiteDatabase,29, 2,9);

        //insercion manual de un plan para poder verlo en el modulo, despues debo borrar
        //esta insercion y hacerla automaticamente.

        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjerciciosPlan.CREATE_TABLE_EJERCICIOS_PLANES);
        insertEjerciciosPlan(sqLiteDatabase, 3,0);
        insertEjerciciosPlan(sqLiteDatabase, 3,3);
        insertEjerciciosPlan(sqLiteDatabase, 1,7);
        insertEjerciciosPlan(sqLiteDatabase, 2,1);
        insertEjerciciosPlan(sqLiteDatabase, 2,3);
        insertEjerciciosPlan(sqLiteDatabase, 2,8);

        //Cursor cantidad = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaEjerciciosPlan.CONSULTAR_CANTIDAD, null);
        //int can = cantidad.getInt(-1);

        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaPlanes.CREATE_TABLE_PLANES);
        insertPlan(sqLiteDatabase, "plan tranqui", 1);
        insertPlan(sqLiteDatabase, "pesado", 3);
        insertPlan(sqLiteDatabase, "fin de semana", 2);
        //insercion de ejercicios en los planes para probar que se reproducen

        //sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjerciciosPlan.CONSULTAR_CANTIDAD);

    }

    private void insertEjercicio(SQLiteDatabase sqLiteDatabase, int idEjercicio, String nombre, int duracion, int gif_ejercicio){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjercicios.ID_EJERCICIO,idEjercicio);
        registro.put(UtilitiesDataBase.TablaEjercicios.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,duracion);
        registro.put(UtilitiesDataBase.TablaEjercicios.GIF,gif_ejercicio);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjercicios.TABLE_NAME,null, registro);
    }

    private void insertRutina(SQLiteDatabase sqLiteDatabase, int idRutina, String nombre, int cantidad, int duracion, int imageRutina){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaRutinas.ID_RUTINA,idRutina);
        registro.put(UtilitiesDataBase.TablaRutinas.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaRutinas.QUANTITY,cantidad);
        registro.put(UtilitiesDataBase.TablaRutinas.DURATION,duracion);
        registro.put(UtilitiesDataBase.TablaRutinas.IMAGE_RUTINA,imageRutina);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaRutinas.TABLE_NAME,null, registro);
    }

    private void insertEjerciciosRutina(SQLiteDatabase sqLiteDatabase, int idEjercicioRutina, int idRutina, int idEjercicio){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_EJERCICIO_RUTINA,idEjercicioRutina);
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_RUTINA,idRutina);
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_EJERCICIO,idEjercicio);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjerciciosRutina.TABLE_NAME, null, registro);

    }

    private void insertPlan(SQLiteDatabase sqLiteDatabase, String nombre, int cantidad){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaPlanes.NAME, nombre);
        //registro.put(UtilitiesDataBase.TablaPlanes.CANTIDAD, UtilitiesDataBase.TablaEjerciciosPlan.CONSULTAR_CANTIDAD);
        registro.put(UtilitiesDataBase.TablaPlanes.CANTIDAD, cantidad);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaPlanes.TABLE_NAME, null, registro);

    }

    private void insertEjerciciosPlan(SQLiteDatabase sqLiteDatabase, int idPlan, int idEjercicio){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjerciciosPlan.ID_PLAN, idPlan);
        registro.put(UtilitiesDataBase.TablaEjerciciosPlan.ID_EJERCICIO, idEjercicio);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjerciciosPlan.TABLE_NAME, null, registro);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaRutinas.TABLE_NAME);
        onCreate(sqLiteDatabase);
        /*
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaPlanes.TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaEjercicios.TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaEjerciciosRutina.TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaEjerciciosPlan.TABLE_NAME);
        onCreate(sqLiteDatabase);
         */
    }
}
