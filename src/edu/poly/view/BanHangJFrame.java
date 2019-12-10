/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.view;

import edu.poly.dao.DonHangDAO;
import edu.poly.dao.HoaDonChiTietDAO;
import edu.poly.dao.HoaDonDAO;
import edu.poly.dao.SanPhamDAO;
import edu.poly.helper.DateHelper;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.DonHang;
import edu.poly.model.HoaDon;
import edu.poly.model.HoaDonChiTiet;
import edu.poly.model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hoang
 */
public class BanHangJFrame extends javax.swing.JFrame {

    ArrayList<DonHang> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    DonHangDAO eDAO = new DonHangDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    HoaDonDAO hdDAO = new HoaDonDAO();
    HoaDonChiTietDAO hdctDAO = new HoaDonChiTietDAO();

    /**
     * Creates new form BanHangJFrame
     */
    public BanHangJFrame() {
        initComponents();
        setLocationRelativeTo(null);

    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<SanPham> list = spDAO.selectByKeyword(keyword);
            for (SanPham sp : list) {

                Object[] row = {
                    sp.getTensp(),
                    sp.getGiasp(),
                    sp.getMaloai(),
                    sp.getHinhsp()

                };
                model.addRow(row);
            }
        } catch (Exception e) {

            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insertHD() {
        HoaDon model = getModelHD();
        try {
            hdDAO.insert(model);
            this.removeAllDonHang();
            this.fillToTable();
            DialogHelper.alert(this, "Thanh toán thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thanh toán thất bại!");

        }

    }

    void insertHDCT() {
        HoaDonChiTiet model = getModelHDCT();
        try {
            hdctDAO.insert(model);
        } catch (Exception e) {
            DialogHelper.alert(this, "Chưa lưu được hóa đơn chi tiết!");

        }

    }
    HoaDonChiTiet getModelHDCT(){
        HoaDonChiTiet model =new HoaDonChiTiet();
        int row = tblDonHang.getRowCount();
        for (int i = 0; i < row; i++) {
          
      
        }
        return model;
    }

    HoaDon getModelHD() {
        HoaDon model = new HoaDon();
        model.setMahoadon(txtMahoadon.getText());
        model.setTongsp(Integer.parseInt(lblTongSP1.getText()));
        model.setTongtien(Integer.valueOf(lblTongGia1.getText()));
        model.setNgaytao(DateHelper.toDate(txtNgayTao.getText()));
        model.setManv(txtManv.getText());

        return model;
    }

    void clear() {
        HoaDon model = new HoaDon();
        model.setManv(ShareHelper.USER.getManv());
        model.setNgaytao(DateHelper.now());
        this.setModel(model);
    }

    void setModel(HoaDon model) {
        txtNgayTao.setText(DateHelper.toString(model.getNgaytao()));
        txtManv.setText(model.getManv());
    }

    public void fillToTable() {
        model = (DefaultTableModel) tblDonHang.getModel();
        model.setRowCount(0);
        for (DonHang e : list) {
            Object[] row = new Object[]{
                e.mahoadon, e.tensanpham, e.giasp, e.soluong, e.thanhtien, DateHelper.toString(e.ngaytao), e.mnv
            };
            model.addRow(row);
            this.getTongGia();
            this.getTongSanPham();
        }
    }

    void thanhtien() {
        int SoLuong = 0;
        int Tien = 0;
        try {
            SoLuong = Integer.valueOf(txtSoLuong.getText());
        } catch (Exception e) {
        }
        int Gia = Integer.valueOf(txtGiaSP.getText());
        Tien = (Integer) Gia * SoLuong;
        txtThanhTien.setText(String.valueOf(Tien));
    }

    public void getTongGia() {
        int sum = 0;
        if (tblDonHang.getRowCount() != 0) {
            for (int i = 0; i < tblDonHang.getRowCount(); i++) {
                sum = sum + Integer.parseInt(tblDonHang.getValueAt(i, 4).toString());
            }

            lblTongGia1.setText(Integer.toString(sum));
        }

    }

    public void getTongSanPham() {
        int row = tblDonHang.getRowCount();
        for (int i = 0; i < row; i++) {
            lblTongSP1.setText("" + row);
        }
    }

    public void addDonHang() {
        DonHang dh = new DonHang();
        HoaDon model1 = hdDAO.findById(txtMahoadon.getText());
        if (model1 != null) {
            DialogHelper.alert(this, "Mã hóa đơn bị trùng");
            return;
        }
        try {
            dh.setMahoadon(txtMahoadon.getText());
            dh.setTensanpham(txtTenSP.getText());
            dh.setGiasp(Integer.parseInt(txtGiaSP.getText()));
            dh.setSoluong(Integer.parseInt(txtSoLuong.getText()));
            dh.setThanhtien(Integer.parseInt(txtThanhTien.getText()));
            dh.setNgaytao(DateHelper.toDate(txtNgayTao.getText()));
            dh.setMnv(txtManv.getText());
            list.add(dh);
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm sản phẩm thất bại!");
        }
    }

    public void removeDonHang() {
       
        int a = tblDonHang.getSelectedRow();
        list.remove(a);
    }

    public void removeAllDonHang() {
        list.removeAll(list);
        lblTongGia1.setText("");
        lblTongSP1.setText("");
    }

    public void addAllDonHang() {
        list.addAll(list);
        System.out.print("listA:" + list);
    }

    void close() {
        if (DialogHelper.confirm(this, "Bạn có thực sự muốn thoát không?")) {
            this.dispose();
        }
    }

    private boolean check() {
        StringBuilder sb = new StringBuilder();
        if (txtMahoadon.getText().equals("")) {
            sb.append("Mã hóa đơn không được để trống!\n");
        }
        if (txtTenSP.getText().equals("") | txtGiaSP.getText().equals("")) {
            sb.append("Sản phẩm chưa được thêm vào!\n");
        }

        if (txtSoLuong.getText().equals("")) {
            sb.append("Số lượng sản phẩm không được để trống!\n");
        } else if (!(Pattern.matches("[0-9]{1,100}", txtSoLuong.getText()))) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return false;
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return false;
        }

        return true;
    }

    private boolean checkThanhToan() {
        StringBuilder sb = new StringBuilder();

        int row = tblDonHang.getRowCount();
        if (row == 0) {

            sb.append("Không có sản phầm để thanh toán!");

        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return false;
        }

        return true;
    }

    private boolean checkXoa() {
        StringBuilder sb = new StringBuilder();

        int row = tblDonHang.getRowCount();

        if (row == 0) {

            sb.append("Không có sản phầm để xóa!");

        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return false;
        }

        return true;
    }

    private boolean checkLamMoi() {
        StringBuilder sb = new StringBuilder();

        int row = tblDonHang.getRowCount();

        if (row == 0) {

            sb.append("Không có sản phầm để làm mới!");

        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        lblTongGia = new javax.swing.JLabel();
        btnThanhtoan = new javax.swing.JButton();
        lblTongSP = new javax.swing.JLabel();
        lblDonHang = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        lblNew = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblTongSP1 = new javax.swing.JLabel();
        lblTongGia1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTenSP = new javax.swing.JLabel();
        lblGiaSP = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtGiaSP = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        lblThanhTien = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        lblNgayTao = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        lblMaNV = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMahoadon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CÔNG TY NƯỚC GIẢI KHÁT TIENLONG");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên sản phẩm", "Giá sản phẩm", "Số lượng", "Thành tiền", "Ngày tạo", "Mã NV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDonHang);

        lblTongGia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongGia.setText("Tổng Giá:");

        btnThanhtoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/bill.png"))); // NOI18N
        btnThanhtoan.setText("Th.Toán");
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        lblTongSP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongSP.setText("Tổng sản phẩm:");

        lblDonHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDonHang.setText("Đơn hàng:");

        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/exit.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        lblNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/refresh.png"))); // NOI18N
        lblNew.setText("Mới");
        lblNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblNewActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/delete1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lblTongSP1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongSP1.setForeground(new java.awt.Color(0, 51, 255));

        lblTongGia1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongGia1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblDonHang)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTongSP)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTongGia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTongGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblDonHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(lblNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTongGia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(36, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel3.setText("Tìm kiếm sản phẩm:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Giá sản phẩm", "Loại sản phẩm", "Hình sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Sản Phẩm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTenSP.setText("Tên sản phẩm:");

        lblGiaSP.setText("Giá sản phẩm:");

        lblSoLuong.setText("Số lượng:");

        txtTenSP.setEditable(false);
        txtTenSP.setEnabled(false);

        txtGiaSP.setEditable(false);
        txtGiaSP.setEnabled(false);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/add1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/refresh.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        lblThanhTien.setText("Thành tiền:");

        lblNgayTao.setText("Ngày tạo:");

        txtNgayTao.setEditable(false);
        txtNgayTao.setEnabled(false);

        lblMaNV.setText("Mã nhân viên:");

        txtManv.setEditable(false);
        txtManv.setEnabled(false);

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel1.setText("Mã hóa đơn:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(lblTenSP)
                            .addComponent(lblGiaSP)
                            .addComponent(lblSoLuong)
                            .addComponent(lblThanhTien)
                            .addComponent(lblNgayTao))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblMaNV)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtManv)
                    .addComponent(txtSoLuong)
                    .addComponent(txtThanhTien)
                    .addComponent(txtNgayTao)
                    .addComponent(txtGiaSP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMahoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTenSP))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSP)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaSP)
                    .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoLuong)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThanhTien)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayTao)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNV)
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int r = tblSanPham.getSelectedRow();
        if (r < 0) {
            return;
        }
        txtTenSP.setText(tblSanPham.getValueAt(r, 0).toString());
        txtGiaSP.setText(tblSanPham.getValueAt(r, 1).toString());
        txtSoLuong.setText("");
        txtThanhTien.setText("");
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        // TODO add your handling code here:
        if (checkThanhToan()) {
            this.insertHD();

        }
    }//GEN-LAST:event_btnThanhtoanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.clear();
    }//GEN-LAST:event_formWindowOpened

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (check()) {
            addDonHang();
        }
        fillToTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        // TODO add your handling code here:
        thanhtien();
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        txtMahoadon.setText("");
        txtTenSP.setText("");
        txtGiaSP.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (checkXoa()) {
            this.removeDonHang();
            this.fillToTable();
            index = -1;
            JOptionPane.showMessageDialog(this, "Đã xóa thành công");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.close();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void lblNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblNewActionPerformed

        if (checkLamMoi()) {
            if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa toàn bộ sản phẩm?")) {
                try {
                    removeAllDonHang();
                    DialogHelper.alert(this, "Xóa thành công");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Xóa thất bại!");
                }
            }
            fillToTable();
        }
    }//GEN-LAST:event_lblNewActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        this.load();
        this.clear();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThanhtoan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblGiaSP;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JButton lblNew;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTongGia;
    private javax.swing.JLabel lblTongGia1;
    private javax.swing.JLabel lblTongSP;
    private javax.swing.JLabel lblTongSP1;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtMahoadon;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
