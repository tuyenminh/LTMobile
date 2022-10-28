package com.example.appquanlidiem.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.appquanlidiem.database.DBHeplper;
import com.example.appquanlidiem.model.Lop;


import java.util.ArrayList;

public class LopDao {
    DBHeplper db;

    public LopDao(Context context) {
        db = new DBHeplper(context);

    }

    public ArrayList<Lop> getAll() {
        ArrayList<Lop> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String maLop = cs.getString(0);
            String tenLop = cs.getString(1);
            Lop s = new Lop(maLop, tenLop);
            lsList.add(s);
            cs.moveToNext();

        }
        cs.close();
        return lsList;
    }
    public boolean insert(Lop lop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLop", lop.getMaLop());
        contentValues.put("tenLop", lop.getTenLop());
        long r = sqLiteDatabase.insert("LOP", null, contentValues);

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean update(Lop lop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maLop", lop.getMaLop());
        contentValues.put("tenLop", lop.getTenLop());
 ;
        long r = sqLiteDatabase.update("LOP", contentValues, "maLop=?", new String[]{lop.getMaLop()});

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean delete(String malop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("SINHVIEN", "maLop=?", new String[]{malop});
        int r = sqLiteDatabase.delete("LOP", "maLop=?", new String[]{malop});
        if (r <= 0) {
            return false;
        }
        return true;
    }

}
