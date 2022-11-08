package com.example.appquanlidiem.duc_model_HP;

import androidx.annotation.NonNull;

public class Lop {
    private String maLop;
    private String tenLop;
    private String tinChi;
    private String mucTieu;
    public Lop(String maLop, String tenLop, String tinChi, String mucTieu) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.tinChi = tinChi;
        this.mucTieu = mucTieu;
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

    public String getTinChi() {
        return tinChi;
    }

    public void setTinChi(String tinChi) {
        this.tinChi = tinChi;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    @NonNull
    @Override
    public String toString() {
        return getMaLop();
    }
}
