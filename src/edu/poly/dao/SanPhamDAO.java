/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (masp, tensp, giasp, soluongsp, hinhanhsp, maloai) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMasp(), model.getTensp(), model.getGiasp(), model.getSoluongsp(), model.getHinhsp(), model.getMaloai());
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET tensp=?, giasp=?, soluongsp=?, hinhanhsp=?, maloai=? WHERE masp=?";
        JdbcHelper.executeUpdate(sql, model.getTensp(), model.getGiasp(), model.getSoluongsp(), model.getHinhsp(), model.getMaloai(), model.getMasp());
    }

    public void delete(String masp) {
        String sql = "DELETE FROM SanPham WHERE masp=?";
        JdbcHelper.executeUpdate(sql, masp);
    }

    public List<SanPham> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }

    public SanPham findById(String masp) {
        String sql = "SELECT * FROM SanPham WHERE masp=?";
        List<SanPham> list = select(sql, masp);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSet(rs);
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

    private SanPham readFromResultSet(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMasp(rs.getString("masp"));
        model.setTensp(rs.getString("tensp"));
        model.setGiasp(rs.getDouble("giasp"));
        model.setSoluongsp(rs.getInt("soluongsp"));
        model.setHinhsp(rs.getString("hinhanhsp"));
        model.setMaloai(rs.getString("maloai"));
        return model;
    }
}
