package com.example.appquanlidiem.phat_tkb_database;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import androidx.annotation.NonNull;
public class Course implements Cloneable, Comparable<Course> {
    private String name;
    private String teacher;
    private int classLength = 0;
    private int classStart = -1;
    private String classRoom;
    private int weekOfTerm = -1;
    private int dayOfWeek = 0;
    public int getClassStart() {
        return classStart;
    }
    public void setClassStart(int classStart) {
        this.classStart = classStart;
    }
    public int getWeekOfTerm() {
        return weekOfTerm;
    }
    public void setWeekOfTerm(int weekOfTerm) {
        this.weekOfTerm = weekOfTerm;
    }
    public int getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public String getClassRoom() {
        return classRoom;
    }
    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
    public int getClassLength() {
        return classLength;
    }
    public void setClassLength(int classLength) {
        if (classLength <= 0)
            classLength = 1;
        else if (classLength > 12)
            classLength = 12;
        this.classLength = classLength;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public int compareTo(Course course) {
        int i = this.getDayOfWeek() - course.getDayOfWeek();
        if (i == 0)
        {
            return this.getClassStart() - course.getClassStart();
        } else {
            return i;
        }
    }
}
