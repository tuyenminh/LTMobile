package com.example.appquanlidiem.model;

import androidx.annotation.NonNull;

public class Lop {
    private String maLop;
    private String tenLop;

    public Lop(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @NonNull
    @Override
    public String toString() {
        return getMaLop();
    }
}
