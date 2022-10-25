package com.example.appquanlidiem.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlidiem.model.HocPhan;
import com.example.appquanlidiem.database.DBHeplper;

import java.util.ArrayList;

public class HocPhanDao {
    DBHeplper db;

    public HocPhanDao(Context context) {
        db = new DBHeplper(context);

    }

    public ArrayList<HocPhan> getAll() {
        ArrayList<HocPhan> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String maHocPhan = cs.getString(0);
            String tenHocPhan = cs.getString(1);
            String tinChi = cs.getString(2);
            String mucTieu = cs.getString(3);
            HocPhan s = new HocPhan(maHocPhan, tenHocPhan, tinChi, mucTieu);
            lsList.add(s);
            cs.moveToNext();

        }
        cs.close();
        return lsList;
    }
    public boolean insert(HocPhan hocPhan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHocPhan", hocPhan.getMaHocPhan());
        contentValues.put("tenHocPhan", hocPhan.getTenHocPhan());
        contentValues.put("tinChi",hocPhan.getTinChi());
        contentValues.put("mucTieu",hocPhan.getMucTieu());
        long r = sqLiteDatabase.insert("LOP", null, contentValues);

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean update(HocPhan hocPhan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maHocPhan", hocPhan.getMaHocPhan());
        contentValues.put("tenHocPhan", hocPhan.getTenHocPhan());
        contentValues.put("tinChi",hocPhan.getTinChi());
        contentValues.put("mucTieu",hocPhan.getMucTieu());
 ;
        long r = sqLiteDatabase.update("HOCPHAN", contentValues, "maHocPhan=?", new String[]{hocPhan.getMaHocPhan()});

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean delete(String mahocphan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("SINHVIEN", "maHocPhan=?", new String[]{mahocphan});
        int r = sqLiteDatabase.delete("HOCPHAN", "maHocPhan=?", new String[]{mahocphan});
        if (r <= 0) {
            return false;
        }
        return true;
    }


    public void clear() {
    }

    public void addAll(ArrayList<HocPhan> all) {
    }

    public void getALL() {
    }
}
