
package edu.poly.model;

/**
 *
 * @author Admin
 */
public class LoaiSanPham {
    private String maLoaiSP;
    private String tenLoaiSP;

    @Override
    public String toString() {
        return tenLoaiSP;
    }

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }
}
