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
public class HoaDonChiTiet {
    private String tensp;
    private String giasp;
     private int soluong;
     private int thanhtien;
    private Date ngaytao;
    private String manv;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String tensp, String giasp, int soluong, int thanhtien, Date ngaytao, String manv) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
        this.ngaytao = ngaytao;
        this.manv = manv;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
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
