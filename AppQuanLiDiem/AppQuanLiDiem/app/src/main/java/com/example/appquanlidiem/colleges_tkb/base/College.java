package com.example.appquanlidiem.colleges_tkb.base;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.graphics.Bitmap;

import com.example.appquanlidiem.bean_tkb.Course;

import java.util.List;

public interface College {




    boolean login(String account, String pw, String RandomCode);


    List<Course> getCourses(String term);


    Bitmap getRandomCodeImg(String dirPath);

    String[] getTermOptions();


    boolean isLogin();


    boolean getFollowRedirects();



}
