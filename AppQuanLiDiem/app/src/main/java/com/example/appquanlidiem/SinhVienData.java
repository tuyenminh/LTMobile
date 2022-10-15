package com.example.appquanlidiem;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SinhVienData extends SQLiteOpenHelper {
    static String dbname = "SinhVien.db";
    static String SinVien_CREATE = "CREATE TABLE IF NOT EXISTS SinhVien ( " +
            "tensv TEXT NOT NULL, " +
            "hocki TEXT NOT NULL )";

    static String SinVien_DROP = "DROP TABLE IF EXISTS SinhVien";
    static String TABLE_NAME = "SinVien";
    static int version=1;
    public SinhVienData(Context context ) {

        super(context,dbname,  null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(this.SinVien_CREATE);
        }catch(Exception e){

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(this.SinVien_DROP);
        onCreate(sqLiteDatabase);
    }
    public boolean InsertSinVin(SinhVien sv_obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tensv",sv_obj.getTenSv());
        cv.put("hocki", sv_obj.getHocKi());
        long result = db.insert(TABLE_NAME,null,cv);
        db.close();
        if (result==-1)
            return false;
        else return true;
    }
    public boolean UpdateSinhVien(SinhVien sv_obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tensv",sv_obj.getTenSv());
        cv.put("hocki", sv_obj.getHocKi());

        String sv =sv_obj.getTenSv();
        String []para = new String[1];
        para[0]=sv_obj.getTenSv();

        long result = db.update(TABLE_NAME,cv,"tensv=?",para);

        if (result==-1)
            return false;
        else return true;
    }
}
