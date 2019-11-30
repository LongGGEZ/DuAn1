/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;


/**
 *
 * @author hoang
 */
public class HoaDonChiTiet {
    private String mahoadonchitiet;
    private String mahoadon;
    private String masp;
     private int soluong;
     private int thanhtien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String mahoadonchitiet, String mahoadon, String masp, int soluong, int thanhtien) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.mahoadon = mahoadon;
        this.masp = masp;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public String getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(String mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
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

   
}
