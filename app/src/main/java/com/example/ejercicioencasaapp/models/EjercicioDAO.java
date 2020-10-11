package com.example.ejercicioencasaapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicioencasaapp.views.Ejercicio;

import java.util.ArrayList;

public class EjercicioDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EjercicioDAO(Context context) {

        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
    }

    public ArrayList<Ejercicio> consultarEjerciciosRutina(int id) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
        String[] parametro = new String[1];
        parametro[0] = String.valueOf(id);
        Cursor cursor = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaRutinas.CONSULTAR_EJERCICIOS,parametro);
        while (cursor.moveToNext()){
            ejercicios.add(new Ejercicio(cursor.getInt(0),cursor.getString(1),cursor.getInt(2), cursor.getInt(3)));
        }
        sqLiteDatabase.close();
        return ejercicios;
    }

     
}
