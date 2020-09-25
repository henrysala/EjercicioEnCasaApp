package com.example.ejercicioencasaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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
        insertEjercicio(sqLiteDatabase, 0,"Salto de tijeras", 60000);
        insertEjercicio(sqLiteDatabase, 1,"Crunch abdominal", 60000);
        insertEjercicio(sqLiteDatabase, 2,"plancha", 60000);
        insertEjercicio(sqLiteDatabase, 3,"Flexión de pecho con apoyo de rodilla", 60000);
        insertEjercicio(sqLiteDatabase, 4,"Tijera de brazos", 60000);
        insertEjercicio(sqLiteDatabase, 5,"Elevación lateral de brazos", 60000);
        insertEjercicio(sqLiteDatabase, 6,"Inclinación lateral con toque de talon", 60000);
        insertEjercicio(sqLiteDatabase, 7,"Sentadillas", 60000);
        insertEjercicio(sqLiteDatabase, 8,"Sentadilla de tijera", 60000);
        insertEjercicio(sqLiteDatabase, 9,"Elevacion de pantorrilla", 60000);

        //Se crea la tabla de rutinas
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaRutinas.CREATE_TABLE_RUTINAS);
        //Se agregan las rutinas a la tabla
        insertRutina(sqLiteDatabase,0,"Quemar grasa", 3, 180000);
        insertRutina(sqLiteDatabase,1,"Tonificar tren superior", 4, 240000);
        insertRutina(sqLiteDatabase,2,"Tonificar tren inferior", 3, 180000);

    }

    private void insertEjercicio(SQLiteDatabase sqLiteDatabase, int id, String nombre, int duracion){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjercicios.ID,id);
        registro.put(UtilitiesDataBase.TablaEjercicios.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,duracion);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjercicios.TABLE_NAME,null, registro);
    }

    private void insertRutina(SQLiteDatabase sqLiteDatabase, int id, String nombre,int cantidad, int duracion){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjercicios.ID,id);
        registro.put(UtilitiesDataBase.TablaEjercicios.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,cantidad);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,duracion);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaRutinas.TABLE_NAME,null, registro);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+UtilitiesDataBase.TablaEjercicios.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
