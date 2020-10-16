package com.example.ejercicioencasaapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicioencasaapp.views.Plan;

import java.util.ArrayList;

public class PlanDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public PlanDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
    }

    public ArrayList<Plan> consultarPlanes(){

        ArrayList<Plan> planes = new ArrayList<Plan>();
        Cursor cursor = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaPlanes.CONSULTAR_ALL_PLANES,null);
        while (cursor.moveToNext()){
            planes.add(new Plan(cursor.getInt(0),cursor.getString(1)));
        }
        sqLiteDatabase.close();
        return planes;
    }
}
