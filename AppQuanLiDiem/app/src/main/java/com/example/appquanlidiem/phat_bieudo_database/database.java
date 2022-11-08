package com.example.appquanlidiem.phat_bieudo_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, constant.DATABASE_NAME, null, constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String BARCHART_TABLE = "CREATE TABLE " +
             constant.TABLE_NAME + " (" +
             constant.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             constant.DATE + " STRING, " +
             constant.yAXIS + " STRING);";

     db.execSQL(BARCHART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + constant.TABLE_NAME);
        onCreate(db);
    }

    public void  saveData (String valX, String valY){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(constant.DATE,valX);
        values.put(constant.yAXIS, valY);

        db.insert(constant.TABLE_NAME, null, values);

    }

    public ArrayList<String> queryXData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> xData = new ArrayList<String>();
        String query = "SELECT " + constant.DATE + " FROM " + constant.TABLE_NAME + " GROUP BY " + constant.DATE;

        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            xData.add(cursor.getString(0));
        }

        cursor.close();

        return xData;
    }


    public ArrayList<String> queryYData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData = new ArrayList<String>();
        String query = "SELECT SUM(" + constant.yAXIS + ") FROM " + constant.TABLE_NAME + " WHERE " + constant.yAXIS + " IS NOT NULL " + " GROUP BY " + constant.DATE;

        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            yData.add(cursor.getString(0));
        }

        cursor.close();

        return yData;
    }
}
