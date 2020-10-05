package com.example.ejercicioencasaapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    public DataBaseOpenHelper(@Nullable Context context) {
        super(context, UtilitiesDataBase.DATABASE_NAME, null, UtilitiesDataBase.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Se crea la tabla de ejercicios
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjercicios.CREATE_TABLE_EJERCICIOS);
        //se agregan los ejercicios a la tabla
        insertEjercicio(sqLiteDatabase, 0,"Salto de tijeras", 60);
        insertEjercicio(sqLiteDatabase, 1,"Crunch abdominal", 60);
        insertEjercicio(sqLiteDatabase, 2,"Plancha", 60);
        insertEjercicio(sqLiteDatabase, 3,"Flexión de pecho con apoyo de rodilla", 60);
        insertEjercicio(sqLiteDatabase, 4,"Tijera de brazos", 60);
        insertEjercicio(sqLiteDatabase, 5,"Elevación lateral de brazos", 60);
        insertEjercicio(sqLiteDatabase, 6,"Inclinación lateral con toque de talon", 60);
        insertEjercicio(sqLiteDatabase, 7,"Sentadillas", 60);
        insertEjercicio(sqLiteDatabase, 8,"Sentadilla de tijera", 60);
        insertEjercicio(sqLiteDatabase, 9,"Elevacion de pantorrilla", 60);

        //Se crea la tabla de rutinas
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaRutinas.CREATE_TABLE_RUTINAS);
        //Se agregan las rutinas a la tabla
        insertRutina(sqLiteDatabase,0,"Quemar grasa", 3, 18);
        insertRutina(sqLiteDatabase,1,"Tonificar tren superior", 4, 24);
        insertRutina(sqLiteDatabase,2,"Tonificar tren inferior", 3, 18);


        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjerciciosRutina.CREATE_TABLE_EJERCICIOS_RUTINA);
        //ejercicios que pertenecen a una rutina
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

    }


    private void insertEjercicio(SQLiteDatabase sqLiteDatabase, int idEjercicio, String nombre, int duracion){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjercicios.ID_EJERCICIO,idEjercicio);
        registro.put(UtilitiesDataBase.TablaEjercicios.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,duracion);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjercicios.TABLE_NAME,null, registro);
    }

    private void insertRutina(SQLiteDatabase sqLiteDatabase, int idRutina, String nombre, int cantidad, int duracion){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaRutinas.ID_RUTINA,idRutina);
        registro.put(UtilitiesDataBase.TablaRutinas.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaRutinas.QUANTITY,cantidad);
        registro.put(UtilitiesDataBase.TablaRutinas.DURATION,duracion);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaRutinas.TABLE_NAME,null, registro);
    }

    private void insertEjerciciosRutina(SQLiteDatabase sqLiteDatabase, int idEjercicioRutina, int idRutina, int idEjercicio){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_EJERCICIO_RUTINA,idEjercicioRutina);
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_RUTINA,idRutina);
        registro.put(UtilitiesDataBase.TablaEjerciciosRutina.ID_EJERCICIO,idEjercicio);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjerciciosRutina.TABLE_NAME, null, registro);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaRutinas.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
