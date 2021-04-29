package com.example.sqlite_act.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    //buat database
    public DBController(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    //buat table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teman (id integer primary key, nama text, telpon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists teman");
        onCreate(db);
    }

    //hashmap adalah array yang berpasangan, artinya array ada 2 parameter
    //parameter 1 = kuncinya, parameter 2 = nilainya
    public void insertData(HashMap<String, String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama", queryValues.get("nama"));
        nilai.put("telpon", queryValues.get("telpon"));
        basisdata.insert("teman", null,nilai);
        basisdata.close();

    }
}
