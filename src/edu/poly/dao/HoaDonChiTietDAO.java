/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.HoaDon;
import edu.poly.model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class HoaDonChiTietDAO {
   public void insert(HoaDonChiTiet model) {
        String sql = "INSERT INTO HoaDonChiTiet (mahoadon, tensp,giasp,soluongsp, thanhtien) VALUES (?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMahoadon(), model.getMasp(), model.getGiasp(), model.getSoluong(), model.getThanhtien());
    }

    public List<HoaDonChiTiet> select() {
        String sql = "SELECT * FROM HoaDon";
        return select(sql);
    }

    public HoaDonChiTiet findById(String mahd) {
        String sql = "SELECT * FROM HoaDon WHERE Mahoadon=?";
        List<HoaDonChiTiet> list = select(sql, mahd);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<HoaDonChiTiet> select(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDonChiTiet model = readFromResultSet(rs);
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

    private HoaDonChiTiet readFromResultSet(ResultSet rs) throws SQLException {
        HoaDonChiTiet model = new HoaDonChiTiet();
        model.setMahoadon(rs.getString("mahoadon"));

        return model;
    }
}