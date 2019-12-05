/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

import java.util.Date;

/**
 *
 * @author hoang
 */
public class HoaDon {
    private String mahoadon;
    private int tongtien;
    private Date ngaytao;  
    private String manv;

    public HoaDon() {
    }

    public HoaDon(String mahoadon, int tongtien, Date ngaytao, String manv) {
        this.mahoadon = mahoadon;
        this.tongtien = tongtien;
        this.ngaytao = ngaytao;
        this.manv = manv;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    
}
