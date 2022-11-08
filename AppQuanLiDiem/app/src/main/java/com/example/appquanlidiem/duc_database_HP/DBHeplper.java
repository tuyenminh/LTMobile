package com.example.appquanlidiem.duc_database_HP;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.appquanlidiem.R;
import java.util.ArrayList;

public class DBHeplper extends SQLiteOpenHelper {
    public DBHeplper(@Nullable Context context) {
        super(context, "QUANLYSINHVIENFPTDB.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = " CREATE TABLE LOP(maLop TEXT PRIMARY KEY, tenLop TEXT, tinChi TEXT, mucTieu TEXT)";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('LT1','Lap Trinh Android','3', '9')";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('LT2','Lap Trinh PHP','4', '4')";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('LT3','Lap Trinh C#','4', '5')";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('CT225','Co So du lieu phan tan','3', '4')";
        db.execSQL(sql);

        sql = " CREATE TABLE SINHVIEN(maSv TEXT PRIMARY KEY, tenSV TEXT ," + " email TEXT ,hinh TEXT, maLop TEXT REFERENCES LOP(maLop))";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('001','Nguyen Van Que','nguyenvanque@gmail.com','avatame','LT1')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('002','Tran Thi Truc','tranthitruc@gmail.com','tranthitruc','LT2')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('003','Tran Quoc Nguyen','quocnguyen@gmail.com','avatamacdinh','LT3')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('004','Duong Tue Linh','tuelinh@gmail.com','duongtuelinh','LT1')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('005','Tran Van Nam','vannam@gmail.com','tranvannam','LT2')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('006','Nguyen Van Hung','vanhung@gmail.com','nguyenvanhung','LT3')";
        db.execSQL(sql);
        sql = "CREATE TABLE taiKhoan(tenTaiKhoan text primary key, matKhau text)";
        db.execSQL(sql);
        sql = "INSERT INTO taiKhoan VALUES('admin','admin')";
        db.execSQL(sql);
        sql = "INSERT INTO taiKhoan VALUES('admin1','admin1')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
