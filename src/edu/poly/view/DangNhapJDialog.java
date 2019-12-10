package edu.poly.view;

import edu.poly.dao.NhanVienDAO;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.NhanVien;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class DangNhapJDialog extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    public DangNhapJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    NhanVienDAO dao = new NhanVienDAO();

    void login() {
        String manv = txtTaiKhoan.getText();
        String matKhau = new String(txtMatKhau.getPassword());
        try {
            NhanVien nhanvien = dao.findById(manv);
            if (nhanvien != null) {
                String matKhau2 = nhanvien.getMatkhau();
                if (matKhau.equals(matKhau2)) {
                    ShareHelper.USER = nhanvien;
                    DialogHelper.alert(this, "Đăng nhập thành công!");
                    this.dispose();
                } else {
                    DialogHelper.alert(this, "Sai mật khẩu!");
                }
            } else {
                DialogHelper.alert(this, "Sai tên đăng nhập!");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
            System.exit(0);
        }
    }

    public boolean isvalid() {
        if (txtTaiKhoan.getText().length() == 0) {
            DialogHelper.alert(this, "Vui lòng nhập tên đăng nhập !");
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
        jPanel3 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        lblTittle = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        btnDanhNhap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CÔNG TY NƯỚC GIẢI KHÁT TIENLONG");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/images/newlogin_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblTittle.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(204, 204, 204));
        lblTittle.setText("LOGIN ACCOUNT");

        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(204, 204, 204));
        lblTaiKhoan.setText("Tài Khoản:");

        lblMatKhau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(204, 204, 204));
        lblMatKhau.setText("Mật Khẩu:");

        txtTaiKhoan.setText("NV1");
        txtTaiKhoan.setCaretColor(new java.awt.Color(255, 51, 51));
        txtTaiKhoan.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txtTaiKhoan.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtMatKhau.setText("1234");
        txtMatKhau.setCaretColor(new java.awt.Color(255, 51, 51));
        txtMatKhau.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txtMatKhau.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnDanhNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDanhNhap.setForeground(new java.awt.Color(204, 0, 0));
        btnDanhNhap.setText("Enter Your Account");
        btnDanhNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDanhNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTaiKhoan)
                            .addComponent(lblMatKhau)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblTittle)))
                .addGap(0, 425, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 476, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblTittle)
                .addGap(59, 59, 59)
                .addComponent(lblTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnDanhNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setSize(new java.awt.Dimension(703, 515));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDanhNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhNhapActionPerformed
        if (this.isvalid()) {
            this.login();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDanhNhapActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhapJDialog dialog = new DangNhapJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhNhap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
