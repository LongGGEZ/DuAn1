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
public class SanPham {

    private String masp;
    private String tensp;
    private double giasp;
    private int soluongsp;
    private String hinhsp;
    private String maloai;

    public SanPham() {
    }

    public SanPham(String masp, String tensp, double giasp, int soluongsp, String hinhsp, String maloai) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.hinhsp = hinhsp;
        this.maloai = maloai;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public double getGiasp() {
        return giasp;
    }

    public void setGiasp(double giasp) {
        this.giasp = giasp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

   
    
}
