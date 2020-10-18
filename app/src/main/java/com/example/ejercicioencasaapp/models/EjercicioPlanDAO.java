package com.example.ejercicioencasaapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicioencasaapp.views.EjercicioPlan;
import com.example.ejercicioencasaapp.views.Plan;

public class EjercicioPlanDAO {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EjercicioPlanDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
    }

    public void insertarEjercicioPlan(EjercicioPlan ejercicioPlan){
        ContentValues registro = new ContentValues();
        registro.put(UtilitiesDataBase.TablaEjerciciosPlan.ID_PLAN,ejercicioPlan.getIdPlan());
        registro.put(UtilitiesDataBase.TablaEjerciciosPlan.ID_EJERCICIO,ejercicioPlan.getIdEjercicio());

        sqLiteDatabase.insert(UtilitiesDataBase.TablaEjerciciosPlan.TABLE_NAME,null, registro);

    }

}
