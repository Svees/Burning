package com.example.burning.DatabaseV2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Databasee.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table historia(id INTEGER primary key, data STRING, startTime STRING, endTime STRING, durationTime STRING, distance FLOAT, calorie INTEGER, aktywnosc STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertinhistory(Integer id,String data, String startTime, String endTime, String durationTime, Float distance, Integer calorie, String aktywnosc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("data",data);
        contentValues.put("startTime",startTime);
        contentValues.put("endTime",endTime);
        contentValues.put("durationTime",durationTime);
        contentValues.put("distance",distance);
        contentValues.put("calorie",calorie);
        contentValues.put("aktywnosc",aktywnosc);

        long result = db.insert("historia",null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from historia ORDER BY id DESC LIMIT 10",null);
        return  cursor;
    }

}
