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
    private float giasp;
    private String loaisp;
    private String hinhsp;

    public SanPham() {
    }

    public SanPham(String masp, String tensp, float giasp, String loaisp, String hinhsp) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.loaisp = loaisp;
        this.hinhsp = hinhsp;
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

    public float getGiasp() {
        return giasp;
    }

    public void setGiasp(float giasp) {
        this.giasp = giasp;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }
    
}
