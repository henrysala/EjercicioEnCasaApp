package com.example.ejercicioencasaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EjercicioDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EjercicioDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
    }
}
