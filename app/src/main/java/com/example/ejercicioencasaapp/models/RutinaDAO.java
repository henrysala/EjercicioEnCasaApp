package com.example.ejercicioencasaapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicioencasaapp.views.Rutina;

import java.util.ArrayList;

public class RutinaDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RutinaDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
    }

    public ArrayList<Rutina> consultarRutinas() {
        ArrayList<Rutina> rutinas = new ArrayList<Rutina>();
        Cursor cursor = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaRutinas.CONSULTAR_ALL_RUTINES,null);
        while (cursor.moveToNext()){
            rutinas.add(new Rutina(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
        }
        sqLiteDatabase.close();
        return rutinas;
    }
}
