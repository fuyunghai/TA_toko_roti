/*
 * Nama     : Leviana Nanda Ramadhanti
 * NPM      : 2019.435.017.07
 * Kelas    : RU
 * Judul TA : Analisis dan Implementasi Metode Scrum Pada Sistem Penjualan
              Toko MargasSari Roti
 * Universitas Indraprasta PGRI Jakarta 2023.
 */
package toko;

import java.sql.*;
import java.sql.Connection;
import javax.swing.JSpinner;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import konek.koneksi;
import popup.popup_menu;
import popup.popup_pelanggan;
import popup.popup_karyawan;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO LevianaNandaRamadhanti
 */
public class transaksi extends javax.swing.JFrame {
    public String id_pelanggan, nama_pelanggan, no_telpon, alamat;
    public String id_menu, nama_menu, kategori, harga_jual, stok_menu;
    public String id_karyawan, nama_karyawan, no_telp, almt, password;
    
    private DefaultTableModel tabmode;
    private Connection conn;
    
    public void cetak(JFrame parentFrame){      
        try {            
            File file = new File("./src/laporan/nota.jrxml");
            Map parameter = new HashMap();
            parameter.put("id_transaksi",txt_idtransaksi.getText());
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,conn);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            if (parentFrame != null) {
                SwingUtilities.invokeLater(() -> {
                    viewer.toFront(); 
                    viewer.repaint(); 
                });
            }
            viewer.setVisible(true);
        } catch (Exception ex) {           
            JOptionPane.showMessageDialog(null,"Dokumen Tidak Ada "+ex);        
        } 
    } 
    public transaksi() {
        try {
            conn = koneksi.connect();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        initComponents();
        kosong();
        aktif();
        autonumber();
        hitung();
    }
    protected void autonumber() {
        try {
            String sql = "SELECT id_transaksi FROM trans ORDER BY id_transaksi ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txt_idtransaksi.setText("TRN0001");
            while (rs.next()) {
                String id_transaksi = rs.getString("id_transaksi");
                String numericPart = id_transaksi.substring(3); 
                int AN = Integer.parseInt(numericPart) + 1;
                String Nol = "";
                if (AN < 10) {
                    Nol = "000";
                } else if (AN < 100) {
                    Nol = "00";
                } else if (AN < 1000) {
                    Nol = "0";
                } else if (AN < 10000) {
                    Nol = "";
                }
                txt_idtransaksi.setText("TRN" + Nol + AN);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Auto Number is Failed" + e);
        }
    }
    protected void aktif(){
        txt_qty.requestFocus();
        SpinnerDateModel dateModel = new SpinnerDateModel();
        jtanggal.setModel(dateModel);
        jtanggal.setEditor(new JSpinner.DateEditor(jtanggal, "yyyy/MM/dd"));
        Object[] baris = {"ID Barang", "Nama Barang", "Harga", "Jumlah", "Total Bayar"};
        tabmode = new DefaultTableModel(null, baris);
        tbl_transaksi.setModel(tabmode);
    }
    protected void kosong(){
        txt_idtransaksi.setText("");
        txt_idkaryawan.setText("");
        txt_nmkaryawan.setText("");
        txt_idpelanggan.setText("");
        txt_nmpelanggan.setText("");
        txt_idmenu.setText("");
        txt_nmmenu.setText("");
        txt_qty.setText("");
        txt_hargajual.setText("");
        txt_totalbyr.setText("");
        txt_tunai.setText("");
        txt_kembalian.setText("");
    }
    public void itemTerpilihMenu(){
        popup_menu Pp = new popup_menu();
        Pp.menu = this;
        txt_idmenu.setText(id_menu);
        txt_nmmenu.setText(nama_menu);
        txt_hargajual.setText(harga_jual);
    } 
    public void itemTerpilihKaryawan(){
        popup_karyawan Pp = new popup_karyawan();
        Pp.karyawan = this;
        txt_idkaryawan.setText(id_karyawan);
        txt_nmkaryawan.setText(nama_karyawan);
    }
    public void itemTerpilihPelanggan(){
        popup_pelanggan Pp = new popup_pelanggan();
        Pp.pelanggan = this;
        txt_idpelanggan.setText(id_pelanggan);
        txt_nmpelanggan.setText(nama_pelanggan);
    }
    public void hitung(){
        int total = 0;
        for (int i=0; i<tbl_transaksi.getRowCount(); i++){
            int amount = Integer.valueOf(tbl_transaksi.getValueAt(i, 4).toString());
            total+=amount;
        } txt_totalbyr.setText(Integer.toString(total));
    } 
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtcaribarang = new javax.swing.JTextField();
        btncarbarangi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbarang = new javax.swing.JTable();
        jDialog2 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtcaripelanggan = new javax.swing.JTextField();
        btncaripelanggan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblpelanggan = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_nmkaryawan = new javax.swing.JTextField();
        txt_idtransaksi = new javax.swing.JTextField();
        txt_nmmenu = new javax.swing.JTextField();
        txt_hargajual = new javax.swing.JTextField();
        jtanggal = new javax.swing.JSpinner();
        txt_qty = new javax.swing.JTextField();
        txt_subtot = new javax.swing.JTextField();
        txt_idmenu = new javax.swing.JTextField();
        btn_carimenu = new javax.swing.JButton();
        btn_carikaryawan = new javax.swing.JButton();
        txt_idkaryawan = new javax.swing.JTextField();
        btn_caripelanggan = new javax.swing.JButton();
        txt_idpelanggan = new javax.swing.JTextField();
        txt_nmpelanggan = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        btn_kembali = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        txt_kembalian = new javax.swing.JTextField();
        txt_tunai = new javax.swing.JTextField();
        txt_totalbyr = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(90, 112, 52));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari ID/Nama Barang");
        jPanel3.add(jLabel7);

        txtcaribarang.setPreferredSize(new java.awt.Dimension(150, 20));
        txtcaribarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcaribarangKeyPressed(evt);
            }
        });
        jPanel3.add(txtcaribarang);

        btncarbarangi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-10.png"))); // NOI18N
        btncarbarangi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarbarangiActionPerformed(evt);
            }
        });
        jPanel3.add(btncarbarangi);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 200));

        tblbarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblbarang.setPreferredSize(new java.awt.Dimension(400, 200));
        tblbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblbarang);

        jPanel3.add(jScrollPane2);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 433, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(90, 112, 52));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cari ID/Nama Barang");
        jPanel4.add(jLabel8);

        txtcaripelanggan.setPreferredSize(new java.awt.Dimension(150, 20));
        txtcaripelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcaripelangganKeyPressed(evt);
            }
        });
        jPanel4.add(txtcaripelanggan);

        btncaripelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-10.png"))); // NOI18N
        btncaripelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripelangganActionPerformed(evt);
            }
        });
        jPanel4.add(btncaripelanggan);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 200));

        tblpelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpelanggan.setPreferredSize(new java.awt.Dimension(400, 200));
        tblpelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpelangganMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblpelanggan);

        jPanel4.add(jScrollPane3);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog2Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog2Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setMinimumSize(new java.awt.Dimension(950, 550));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(90, 112, 52));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 550));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATA TRANSAKSI PENJUALAN");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Transaksi");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID Karyawan");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama Karyawan");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ID Pelanggan");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Pelanggan");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tanggal");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ID Barang");

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nama Barang");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Harga");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Jumlah");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Sub Total");

        txt_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qtyActionPerformed(evt);
            }
        });

        btn_carimenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-10.png"))); // NOI18N
        btn_carimenu.setText("Cari");
        btn_carimenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carimenuActionPerformed(evt);
            }
        });

        btn_carikaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-10.png"))); // NOI18N
        btn_carikaryawan.setText("Cari");
        btn_carikaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carikaryawanActionPerformed(evt);
            }
        });

        txt_idkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idkaryawanActionPerformed(evt);
            }
        });

        btn_caripelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-10.png"))); // NOI18N
        btn_caripelanggan.setText("Cari");
        btn_caripelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caripelangganActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(133, 166, 77));

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_transaksi);

        btn_kembali.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btn_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-back-15.png"))); // NOI18N
        btn_kembali.setText("Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        btn_tambah.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-plus-10.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-save-15.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-bin-15.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        txt_kembalian.setBackground(new java.awt.Color(204, 204, 204));

        txt_tunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tunaiKeyPressed(evt);
            }
        });

        txt_totalbyr.setBackground(new java.awt.Color(255, 118, 118));
        txt_totalbyr.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Kembalian");

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Tunai");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Total Bayar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_totalbyr, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_tunai)
                            .addComponent(txt_kembalian)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_hapus))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txt_totalbyr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txt_tunai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_kembali)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nmpelanggan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_idtransaksi)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txt_idkaryawan)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_carikaryawan))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_nmkaryawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_caripelanggan)))))))
                        .addGap(153, 153, 153)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(31, 31, 31)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_hargajual)
                            .addComponent(jtanggal)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_subtot, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_idmenu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_carimenu))
                            .addComponent(txt_nmmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_idtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(btn_carikaryawan)
                            .addComponent(txt_idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_nmkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(btn_caripelanggan)
                            .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_nmpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_idmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_carimenu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txt_nmmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_hargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(txt_subtot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 550));

        setBounds(400, 140, 950, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcaribarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaribarangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcaribarangKeyPressed

    private void btncarbarangiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarbarangiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncarbarangiActionPerformed

    private void tblbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbarangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblbarangMouseClicked

    private void txtcaripelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaripelangganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcaripelangganKeyPressed

    private void btncaripelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncaripelangganActionPerformed

    private void tblpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelangganMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpelangganMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_carikaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carikaryawanActionPerformed
        popup_karyawan Pp = new popup_karyawan();
        Pp.karyawan = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
        Pp.setBounds(650, 130, 481, 350);
    }//GEN-LAST:event_btn_carikaryawanActionPerformed

    private void btn_caripelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caripelangganActionPerformed
        popup_pelanggan Pp = new popup_pelanggan();
        Pp.pelanggan = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
        Pp.setBounds(650, 130, 481, 350);
    }//GEN-LAST:event_btn_caripelangganActionPerformed

    private void btn_carimenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carimenuActionPerformed
        popup_menu Pp = new popup_menu();
        Pp.menu = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
        Pp.setBounds(250, 130, 481, 350);
    }//GEN-LAST:event_btn_carimenuActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int index = tbl_transaksi.getSelectedRow();
        tabmode.removeRow(index);
        tbl_transaksi.setModel(tabmode);
        hitung();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        try {
            String id_menu=txt_idmenu.getText();
            String nama_menu=txt_nmmenu.getText();
            int hrg_jual=Integer.parseInt(txt_hargajual.getText());
            int jumlah=Integer.parseInt(txt_qty.getText());
            int sub_ttl=Integer.parseInt(txt_subtot.getText());
            tabmode.addRow(new Object[]{id_menu,nama_menu,hrg_jual,jumlah,sub_ttl});
            tbl_transaksi.setModel(tabmode);    
        } catch (Exception e) {
            System.out.println("error : "+e);
        }
        txt_idmenu.setText("");
        txt_nmmenu.setText("");
        txt_hargajual.setText("");
        txt_qty.setText("");
        txt_subtot.setText("");
        hitung();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void txt_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qtyActionPerformed
        int harga = Integer.parseInt(txt_hargajual.getText());
        int qty = Integer.parseInt(txt_qty.getText());
        int jml = harga*qty;
        txt_subtot.setText(String.valueOf(jml));
    }//GEN-LAST:event_txt_qtyActionPerformed

    private void txt_tunaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tunaiKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            int total = Integer.parseInt(txt_totalbyr.getText());
            int tunai = Integer.parseInt(txt_tunai.getText());
            if (tunai<total){
                JOptionPane.showMessageDialog(null, "jumlah bayar tidak mencukupi");
                txt_tunai.requestFocus();
            } else{
                int kembali = tunai-total;
                txt_kembalian.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_txt_tunaiKeyPressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = sdf.format(jtanggal.getValue());
        String sql1 = "insert into trans values (?,?,?,?,?,?)";
        String sql2 = "insert into trans_rinci values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql1);
            stat.setString(1, txt_idtransaksi.getText());
            stat.setString(2, tgl);
            stat.setString(3, txt_idkaryawan.getText());
            stat.setString(4, txt_nmkaryawan.getText());
            stat.setString(5, txt_idpelanggan.getText());
            stat.setString(6, txt_nmpelanggan.getText());
            stat.executeUpdate();
            int t = tbl_transaksi.getRowCount();
            for(int i=0; i < t ; i++) {
                String idmenu = tbl_transaksi.getValueAt(i,0).toString();
                String nmmenu = tbl_transaksi.getValueAt(i,1).toString();
                String harga = tbl_transaksi.getValueAt(i,2).toString();
                String qty = tbl_transaksi.getValueAt(i,3).toString();
                String sbtotal = tbl_transaksi.getValueAt(i,4).toString();
                
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.setString(1, txt_idtransaksi.getText());
                stat2.setString(2, idmenu);
                stat2.setString(3, nmmenu);
                stat2.setString(4, harga);
                stat2.setString(5, qty);
                stat2.setString(6, sbtotal);
                stat2.setString(7, txt_totalbyr.getText());
                stat2.setString(8, txt_tunai.getText());
                stat2.setString(9, txt_kembalian.getText());
                stat2.executeUpdate();          
            } JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            cetak(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Data gagal disimpan"+e); 
        } 
        kosong();
        aktif();
        autonumber();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        dispose();
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void txt_idkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idkaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idkaryawanActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_carikaryawan;
    private javax.swing.JButton btn_carimenu;
    private javax.swing.JButton btn_caripelanggan;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btncarbarangi;
    private javax.swing.JButton btncaripelanggan;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jtanggal;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTable tblbarang;
    private javax.swing.JTable tblpelanggan;
    private javax.swing.JTextField txt_hargajual;
    private javax.swing.JTextField txt_idkaryawan;
    private javax.swing.JTextField txt_idmenu;
    private javax.swing.JTextField txt_idpelanggan;
    private javax.swing.JTextField txt_idtransaksi;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_nmkaryawan;
    private javax.swing.JTextField txt_nmmenu;
    private javax.swing.JTextField txt_nmpelanggan;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_subtot;
    private javax.swing.JTextField txt_totalbyr;
    private javax.swing.JTextField txt_tunai;
    private javax.swing.JTextField txtcaribarang;
    private javax.swing.JTextField txtcaripelanggan;
    // End of variables declaration//GEN-END:variables

}
