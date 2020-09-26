package com.example.ejercicioencasaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EjercicioDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EjercicioDAO(Context context){

        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
/*
        public ArrayList<Ejercicio> consultarEjercicios() {
            ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
            Cursor cursor = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaRutinas.CONSULTAR_ALL_EJERCICIOS,null);
            while (cursor.moveToNext()){
                ejercicios.add(new Ejercicio(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }
            sqLiteDatabase.close();
            return ejercicios;
        }

*/
    }
}
