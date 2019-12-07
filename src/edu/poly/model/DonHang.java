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
public class DonHang {
    public String mahoadon;
    public String tensanpham;
    public int giasp;
    public int soluong;
    public int thanhtien;
    public Date ngaytao;
    public String mnv;

    public DonHang() {
    }

    public DonHang(String mahoadon, String tensanpham, int giasp, int soluong, int thanhtien, Date ngaytao, String mnv) {
        this.mahoadon = mahoadon;
        this.tensanpham = tensanpham;
        this.giasp = giasp;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
        this.ngaytao = ngaytao;
        this.mnv = mnv;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
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

    public String getMnv() {
        return mnv;
    }

    public void setMnv(String mnv) {
        this.mnv = mnv;
    }

    @Override
    public String toString() {
        return "DonHang{" + "mahoadon=" + mahoadon + ", tensanpham=" + tensanpham + ", giasp=" + giasp + ", soluong=" + soluong + ", thanhtien=" + thanhtien + ", ngaytao=" + ngaytao + ", mnv=" + mnv + '}';
    }

   
}
