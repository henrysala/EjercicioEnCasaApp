package com.example.ejercicioencasaapp.models;

import android.content.ContentValues;
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

    public long insertarPlan(Plan plan){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaPlanes.NAME,plan.getNombre());
        registro.put(UtilitiesDataBase.TablaPlanes.CANTIDAD,plan.getCantidad());
        long id = sqLiteDatabase.insert(UtilitiesDataBase.TablaPlanes.TABLE_NAME,UtilitiesDataBase.TablaPlanes.ID_PLAN,registro);
        sqLiteDatabase.close();
        return id;
    }

    public ArrayList<Plan> consultarPlanes(){

        ArrayList<Plan> planes = new ArrayList<Plan>();
        Cursor cursor = sqLiteDatabase.rawQuery(UtilitiesDataBase.TablaPlanes.CONSULTAR_ALL_PLANES,null);
        while (cursor.moveToNext()){
            planes.add(new Plan(cursor.getInt(0),cursor.getString(1), cursor.getInt(2)));
        }
        sqLiteDatabase.close();
        return planes;
    }
}
