package com.example.appquanlidiem.model;

import androidx.annotation.NonNull;

public class HocPhan {
    private String maHocPhan;
    private String tenHocPhan;
    private String tinChi;
    private String mucTieu;

    public HocPhan(String maHocPhan, String tenHocPhan, String tinChi, String mucTieu) {
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.tinChi = tinChi;
        this.mucTieu = mucTieu;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
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
    public  String toString() {return getMaHocPhan();}
}
