
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DataTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form DataTransaksi
     */
    public DataTransaksi() {
        initComponents();
        getNamaPC();
        getNamaPlg();
        tampil();
        tanggal();
    }
    
    
    DefaultTableModel tabel;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void tampil() {
        tabel = new DefaultTableModel();

        tabel.addColumn("Kode");
        tabel.addColumn("Nama PC");
        tabel.addColumn("Nama Pelanggan");
        tabel.addColumn("Tanggal Sewa");
        tabel.addColumn("Total Harga");

        final String tampil_pc = "SELECT * FROM tb_transaksi";

        try (Connection conn = koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(tampil_pc)) {

          while (rs.next()) {
            int id = rs.getInt("id");
            String pc = rs.getString("nama_pc");
            String plg = rs.getString("nama_pelanggan");
            String tgl = rs.getString("tgl_sewa");
            String total = rs.getString("total_harga");
            tabel.addRow(new Object[]{id, pc, plg, tgl, total});
          }
        } catch (SQLException se) {
          se.printStackTrace();
        }

        tb_transaksi.setModel(tabel);
    }

    private void getHargaSewa() {
        String pc = cmbPc.getSelectedItem().toString();

        Connection conn = koneksi.getConnection();

        String query = "SELECT harga_sewa FROM tb_pc WHERE nama_pc = ?";

    try {
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, pc);
        ResultSet hasil = statement.executeQuery();

        while (hasil.next()) {
          String harga = hasil.getString("harga_sewa");
          
          fHargaSewa.setText(harga);
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void getNamaPC() {
        Connection conn = koneksi.getConnection();

        String sql = "SELECT nama_pc FROM tb_pc";

        try {
          Statement stmt = conn.createStatement();

          ResultSet rs = stmt.executeQuery(sql);

          while (rs.next()) {
            cmbPc.addItem(rs.getString("nama_pc"));
          }

          rs.close();
          stmt.close();
          conn.close();
        } catch (SQLException se) {
          se.printStackTrace();
        }
    }
    
    public void getNamaPlg() {
        Connection conn = koneksi.getConnection();

        String sql = "SELECT nama FROM tb_pelanggan";

        try {
          Statement stmt = conn.createStatement();

          ResultSet rs = stmt.executeQuery(sql);

          while (rs.next()) {
            cmbPlg.addItem(rs.getString("nama"));
          }

          rs.close();
          stmt.close();
          conn.close();
        } catch (SQLException se) {
          se.printStackTrace();
        }
    }
    
    public void tanggal() {
    Date tgl = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        fTanggal.setText(date.format(tgl));
    }
    
    public void bersih() {
        cmbPc.setSelectedItem("Pilih");
        cmbPlg.setSelectedItem("Pilih");
        fHargaSewa.setText("");
        fJumlahSewa.setText("");
        fTotal.setText("");
        tID.setText("#");
    }
    
    public void cetak(){
    Document doc = new Document();
    try {
        PdfWriter.getInstance(doc, new FileOutputStream("laporan-transaksi.pdf"));
        doc.open();

        Font fontTebal = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        
        Paragraph jdl = new Paragraph("Laporan data transaksi", fontTebal);
        jdl.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(jdl);

        PdfPTable table = new PdfPTable(5);

        doc.add(new Paragraph("\n"));
        
        PdfPCell cellId = new PdfPCell(new Paragraph("Kode"));
        table.addCell(cellId);
        
        PdfPCell cellPc = new PdfPCell(new Paragraph("Nama PC"));
        table.addCell(cellPc);
        
        PdfPCell cellPlg = new PdfPCell(new Paragraph("Nama Pelanggan"));
        table.addCell(cellPlg);
        
        PdfPCell cellSewa = new PdfPCell(new Paragraph("Tanggal Sewa"));
        table.addCell(cellSewa);
        
        PdfPCell cellHarga = new PdfPCell(new Paragraph("Total harga"));
        table.addCell(cellHarga);
        

        Connection conn = koneksi.getConnection();

        String query = "SELECT * FROM tb_transaksi";

        Statement statement = conn.createStatement();
        ResultSet hasil = statement.executeQuery(query);

        while (hasil.next()) {
          String id             = hasil.getString("id");
          String pc             = hasil.getString("nama_pc");
          String plg            = hasil.getString("nama_pelanggan");
          String tgl            = hasil.getString("tgl_sewa");
          String ttl            = hasil.getString("total_harga");
          
          table.addCell(id);
          table.addCell(pc);
          table.addCell(plg);
          table.addCell(tgl);
          table.addCell(ttl);
        }

        doc.add(table);
        JOptionPane.showMessageDialog(null, "Mencetak data transaksi Berhasil, file sudah dibuat", "Infromasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        fHargaSewa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_transaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbPc = new javax.swing.JComboBox<>();
        cmbPlg = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        fJumlahSewa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fTotal = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        fTanggal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("ubah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Pelanggan");

        jButton2.setText("simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Harga sewa");

        jButton3.setText("hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("bersihkan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        fHargaSewa.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("ID:");

        tID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tID.setText("#");

        tb_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_transaksi);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Transaksi penyewaan PC");

        jLabel2.setText("Nama PC");

        cmbPc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmbPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPcActionPerformed(evt);
            }
        });

        cmbPlg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));

        jLabel6.setText("Jumlah sewa/pc");

        jLabel7.setText("Total  harga");

        fTotal.setEnabled(false);

        jButton6.setText("Hitung total harga");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setText("Tanggal sewa");

        fTanggal.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(124, 124, 124))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tID))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fHargaSewa)
                            .addComponent(cmbPc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPlg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fTotal)
                            .addComponent(fTanggal)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fJumlahSewa))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbPc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbPlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fHargaSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fJumlahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(tID)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String id               = tID.getText();
        String pc               = cmbPc.getSelectedItem().toString();
        String pelanggan        = cmbPlg.getSelectedItem().toString();
        String tgl              = fTanggal.getText();
        String total            = fTotal.getText();

        try (Connection conn = koneksi.getConnection()) {
            final String ubah_pelanggan = "UPDATE tb_transaksi SET nama_pc = ?, nama_pelanggan = ?, tgl_sewa = ?, total_harga = ? WHERE id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(ubah_pelanggan)) {
                pstmt.setString(1, pc);
                pstmt.setString(2, pelanggan);
                pstmt.setString(3, tgl);
                pstmt.setString(4, total);
                pstmt.setString(5, id);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Mengubah data Transaksi Berhasil", "Infromasi", JOptionPane.INFORMATION_MESSAGE);
                tampil();
                bersih();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String pc               = cmbPc.getSelectedItem().toString();
        String plg              = cmbPlg.getSelectedItem().toString();
        String tgl              = fTanggal.getText();
        String ttl              = fTotal.getText();

        if(fHargaSewa.getText().equals("") || fTotal.getText().equals("") || cmbPc.getSelectedItem().equals("Pilih") || cmbPlg.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Inputan tidak boleh kosong / Pilih nama pc & pelanggan dengan benar", "Informasi", JOptionPane.YES_OPTION);
        } else {
            try (Connection conn = koneksi.getConnection()) {
            final String tambah_transksi = "INSERT INTO tb_transaksi (nama_pc, nama_pelanggan, tgl_sewa, total_harga)"
            + " VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(tambah_transksi)) {
                pstmt.setString(1, pc);
                pstmt.setString(2, plg);
                pstmt.setString(3, tgl);
                pstmt.setString(4, ttl);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Menyimpan data Transaksi Berhasil", "Infromasi", JOptionPane.INFORMATION_MESSAGE);
                tampil();
                bersih();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String id = tID.getText();

        int yes = JOptionPane.showConfirmDialog(null, "Hapus data Transaksi ini?","Perhatian!!", JOptionPane.YES_NO_OPTION);

        if(yes == 0) {
            try (Connection conn = koneksi.getConnection()) {
                final String hapus_pelanggan = "DELETE FROM tb_transaksi WHERE id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(hapus_pelanggan)) {
                    pstmt.setString(1, id);

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Menghapus data Transaksi Berhasil", "Infromasi", JOptionPane.INFORMATION_MESSAGE);
                    tampil();
                    bersih();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        bersih();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cetak();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tb_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_transaksiMouseClicked
        // TODO add your handling code here:
        int baris           = tb_transaksi.getSelectedRow();
        String id           = tabel.getValueAt(baris, 0).toString();
        String pc           = tabel.getValueAt(baris, 1).toString();
        String plg          = tabel.getValueAt(baris, 2).toString();
        String tgl          = tabel.getValueAt(baris, 3).toString();
        String ttl          = tabel.getValueAt(baris, 4).toString();

        tID.setText(String.valueOf(id));
        cmbPc.setSelectedItem(pc);
        cmbPlg.setSelectedItem(plg);
        fTanggal.setText(tgl);
        fTotal.setText(ttl);
    }//GEN-LAST:event_tb_transaksiMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(fJumlahSewa.getText().equals("") || fHargaSewa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan jumlah sewa / harga sewa masih kosong!", "Informasi", JOptionPane.YES_OPTION);
        } else {
            int sewa = Integer.parseInt(fHargaSewa.getText());
            int jumlah = Integer.parseInt(fJumlahSewa.getText());
            int total = sewa * jumlah;
            fTotal.setText(String.valueOf(total));
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cmbPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPcActionPerformed
        // TODO add your handling code here:
        getHargaSewa();
    }//GEN-LAST:event_cmbPcActionPerformed

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
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbPc;
    private javax.swing.JComboBox<String> cmbPlg;
    private javax.swing.JTextField fHargaSewa;
    private javax.swing.JTextField fJumlahSewa;
    private javax.swing.JTextField fTanggal;
    private javax.swing.JTextField fTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tID;
    private javax.swing.JTable tb_transaksi;
    // End of variables declaration//GEN-END:variables
}
