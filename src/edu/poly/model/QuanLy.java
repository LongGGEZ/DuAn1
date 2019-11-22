/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

/**
 *
 * @author Admin
 */
public class QuanLy {
    private String maQuanLy;
    private String tenQuanLy;
    private String matKhau;

    public QuanLy() {
    }

    public QuanLy(String maQuanLy, String tenQuanLy, String matKhau) {
        this.maQuanLy = maQuanLy;
        this.tenQuanLy = tenQuanLy;
        this.matKhau = matKhau;
    }

    public String getMaQuanLy() {
        return maQuanLy;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    public String getTenQuanLy() {
        return tenQuanLy;
    }

    public void setTenQuanLy(String tenQuanLy) {
        this.tenQuanLy = tenQuanLy;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
