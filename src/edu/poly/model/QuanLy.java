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
public class QuanLy {
    private String maql;
    private String tenql;
    private String matkhau;

    public QuanLy() {
    }

    public QuanLy(String maql, String tenql, String matkhau) {
        this.maql = maql;
        this.tenql = tenql;
        this.matkhau = matkhau;
    }

    public String getMaql() {
        return maql;
    }

    public void setMaql(String maql) {
        this.maql = maql;
    }

    public String getTenql() {
        return tenql;
    }

    public void setTenql(String tenql) {
        this.tenql = tenql;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
    
}
