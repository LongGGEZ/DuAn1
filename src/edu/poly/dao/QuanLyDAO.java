/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.QuanLy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuanLyDAO {
    public void insert(QuanLy model) {
        String sql = "INSERT INTO QuanLy (maql, tenql, matkhau) VALUES (?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaQuanLy(),
                model.getTenQuanLy(),
                model.getMatKhau());  
    }

    public void update(QuanLy model) {
        String sql = "UPDATE QuanLy SET tenql=?, matkhau=? WHERE maql=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenQuanLy(),
                model.getMatKhau(),
                model.getMaQuanLy());

    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM QuanLy WHERE maql=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public List<QuanLy> select() {
        String sql = "SELECT * FROM QuanLy";
        return select(sql);
    }

    public QuanLy findById(String manv) {
        String sql = "SELECT * FROM QuanLy WHERE maql=?";
        List<QuanLy> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<QuanLy> select(String sql, Object... args) {
        List<QuanLy> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    QuanLy model = readFromResultSet(rs);
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

    private QuanLy readFromResultSet(ResultSet rs) throws SQLException {
        QuanLy model = new QuanLy();
        model.setMaQuanLy(rs.getString("maql"));
        model.setTenQuanLy(rs.getString("tenql"));
        model.setMatKhau(rs.getString("matkhau"));
        return model;
    }
}
