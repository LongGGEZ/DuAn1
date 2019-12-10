/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class HoaDonDAO {

    public void insert(HoaDon model) {
        String sql = "INSERT INTO HoaDon (mahoadon, tongsp,tongtien, ngaytao,manv) VALUES (?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMahoadon(), model.getTongsp(), model.getTongtien(), model.getNgaytao(), model.getManv());
    }

    public List<HoaDon> select() {
        String sql = "SELECT * FROM HoaDon";
        return select(sql);
    }

    public HoaDon findById(String mahd) {
        String sql = "SELECT * FROM HoaDon WHERE mahoadon=?";
        List<HoaDon> list = select(sql, mahd);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDon model = readFromResultSet(rs);
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
      public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDon WHERE mahoadon LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    private HoaDon readFromResultSet(ResultSet rs) throws SQLException {
        HoaDon model = new HoaDon();
        model.setMahoadon(rs.getString("mahoadon"));
        model.setTongsp(rs.getInt("tongsp"));
        model.setTongtien(rs.getInt("tongtien"));
        model.setManv(rs.getString("manv"));
        return model;
    }
}
