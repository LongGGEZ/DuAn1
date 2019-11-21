/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (manv, tennv, matkhau, vaitro) VALUES (?, ?, ?,?)";
        JdbcHelper.executeUpdate(sql, model.getManv(), model.getTennv(), model.getMatkhau(), model.isVaitro());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET tennv=?, matkhau=?, vaitro=? WHERE manv=?";
        JdbcHelper.executeUpdate(sql, model.getTennv(), model.getMatkhau(), model.isVaitro(), model.getManv());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE manv=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    public NhanVien findById(String maql) {
        String sql = "SELECT * FROM NhanVien WHERE manv=?";
        List<NhanVien> list = select(sql, maql);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
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

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setManv(rs.getString("manv"));
        model.setTennv(rs.getString("tennv"));
        model.setMatkhau(rs.getString("matkhau"));
        model.setVaitro(rs.getBoolean("vaitro"));
        return model;
    }
}
