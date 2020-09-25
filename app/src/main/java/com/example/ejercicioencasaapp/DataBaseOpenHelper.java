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
        sqLiteDatabase.execSQL(UtilitiesDataBase.TablaEjercicios.CREATE_TABLE_EJERCICIOS);

        insert(sqLiteDatabase, 0,"Salto de tijeras", 60000);
        insert(sqLiteDatabase, 1,"Crunch abdominal", 60000);
        insert(sqLiteDatabase, 2,"plancha", 60000);
        insert(sqLiteDatabase, 3,"Flexión de pecho con apoyo de rodilla", 60000);
        insert(sqLiteDatabase, 4,"Tijera de brazos", 60000);
        insert(sqLiteDatabase, 5,"Elevación lateral de brazos", 60000);
        insert(sqLiteDatabase, 6,"Inclinación lateral con toque de talon", 60000);
        insert(sqLiteDatabase, 7,"Sentadillas", 60000);
        insert(sqLiteDatabase, 8,"Sentadilla de tijera", 60000);
        insert(sqLiteDatabase, 9,"Elevacion de pantorrilla", 60000);

    }

    private void insert(SQLiteDatabase sqLiteDatabase, int id, String nombre, int duracion){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjercicios.ID,id);
        registro.put(UtilitiesDataBase.TablaEjercicios.NAME,nombre);
        registro.put(UtilitiesDataBase.TablaEjercicios.DURATION,duracion);

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjercicios.TABLE_NAME,null, registro);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+UtilitiesDataBase.TablaEjercicios.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
