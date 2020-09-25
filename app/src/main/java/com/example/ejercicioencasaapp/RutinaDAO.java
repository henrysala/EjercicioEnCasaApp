package com.example.ejercicioencasaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RutinaDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RutinaDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
    }
}
