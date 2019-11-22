/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamDAO {
    public void insert(LoaiSanPham model) {
        String sql
                = "INSERT INTO LoaiSP (maloai, tenloai) "
                + "VALUES( ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaLoaiSP(),
                model.getTenLoaiSP());
                
    }
    
     private List<LoaiSanPham> select(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiSanPham model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public void update(LoaiSanPham model) {
        String sql = "UPDATE LoaiSP SET tenloai=? WHERE maloai=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenLoaiSP(),
                model.getMaLoaiSP());
    }
    
     
    public LoaiSanPham findById(String macd) {
        String sql = "SELECT * FROM LoaiSP WHERE maloai=?";
        List<LoaiSanPham> list = select(sql, macd);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public void delete(String MaCD) {
        String sql = "DELETE FROM LoaiSP WHERE maloai=?";
        JdbcHelper.executeUpdate(sql, MaCD);
    }
     
    public List<LoaiSanPham> select() {
        String sql = "SELECT * FROM LoaiSP";
        return select(sql);
    }
    
    private LoaiSanPham readFromResultSet(ResultSet rs) throws SQLException {
        LoaiSanPham model = new LoaiSanPham();
        model.setMaLoaiSP(rs.getString("maloai"));
        model.setTenLoaiSP(rs.getString("tenloai"));
        
        return model;
    }
    
//    public List<LoaiSanPham> selectByCourse(Integer loaisp) {
//        String sql = "SELECT * FROM LoaiSP WHERE maloai NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
//        return select(sql, loaisp);
//    }
}
