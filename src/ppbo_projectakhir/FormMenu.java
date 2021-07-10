/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppbo_projectakhir;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yn
 */
public class FormMenu extends javax.swing.JFrame {

    /**
     * Creates new form Form1
     */
    public FormMenu() {
        initComponents();
        jp_kategori.setVisible(true);
        jp_buku.setVisible(false);
            jp21.setVisible(false);
            jp22.setVisible(false);
            jp23.setVisible(false);
            jp24.setVisible(false);
        jp_penjualan.setVisible(false);
            jp31.setVisible(false);
            
        //Menampilkan Tabel Pada Kategori
        KosongkanFormKategori();
        TampilDataKategori();
        Tampil_cb_kategori();
        TampilDataBukuDibeli();
    }
    
    public void KosongkanFormKategori(){
        txt_idKategori.setEditable(true);
        txt_idKategori.setText(null);
        txt_namaKategori.setText(null);

    }
    
    public void TampilDataKategori(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Kategori");

    
        //Menampilkan data pada database ke dalam tabel
        try {
            int no=1;
            String sql="SELECT * FROM kategori";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               res.getString(1), 
               res.getString(2)});
            }
            TabelKategori.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void KosongkanFormBuku(){
        txt_kodeBuku.setEditable(true);
        txt_kodeBuku.setText(null);
        txt_judulBuku.setText(null);
        txt_pengarangBuku.setText(null);
        txt_penerbitBuku.setText(null);
        txt_tahunterbitBuku.setText(null);
        cb_kategori.setSelectedItem(this);
        txt_hargapokokBuku.setText(null);
        txt_hargajualBuku.setText(null);
    }
      
    public void TampilDataBuku(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Buku");
        model.addColumn("Judul Buku");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Kategori");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
    
        //Menampilkan data pada database ke dalam tabel
        try {
            int no=1;
            String sql="SELECT * FROM buku";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               no++,
               res.getString(1), 
               res.getString(2), 
               res.getString(3), 
               res.getString(4),
               res.getString(5),
               res.getString(6),
               res.getString(7),
               res.getString(8)});
            }
            tabelBuku.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void TampilDataBukuPenjualan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Buku");
        model.addColumn("Judul Buku");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Kategori");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
    
        //Menampilkan data pada database ke dalam tabel
        try {
            int no=1;
            String sql="SELECT * FROM buku";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               no++,
               res.getString(1), 
               res.getString(2), 
               res.getString(3), 
               res.getString(4),
               res.getString(5),
               res.getString(6),
               res.getString(7),
               res.getString(8)});
            }
            tabelBukuPenjualan.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void Tampil_cb_kategori(){
        try{
            String sql="SELECT * FROM kategori";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            
            while(res.next()){
                cb_kategori.addItem(res.getString("nama_kategori"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void TampilDataBukuDibeli(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Tanggal");
        model.addColumn("Kode Customer");
        model.addColumn("Kode Buku");
        model.addColumn("Judul Buku");
        model.addColumn("Kategori Buku");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
    
        //Menampilkan data pada database ke dalam tabel
        try {
            int no=1;
            String sql="SELECT * FROM daftarbuku_dibeli";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               no++,
               res.getString(1), 
               res.getString(2), 
               res.getString(3), 
               res.getString(4),
               res.getString(5),
               res.getString(6),
               res.getString(7),
               res.getString(8)});
            }
            tabelBukuDibeli.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void KosongkanFormBukuDibeli(){
        txt_kodeCustomer.setEditable(true);
        txt_kodeCustomer.setText(null);
        txt_tglPenjualan.setDate(null);
        txt_kodeBukuPenjualan.setText(null);
        txt_judulBukuPenjualan.setText(null);
        txt_kategoriBukuPenjualan.setText(null);
        txt_hargaBukuPenjualan.setText(null);
        txt_jumlahBukuPenjualan.setText(null);
        txt_totalhargaBukuPenjualan.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jSlider1 = new javax.swing.JSlider();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_kategori = new javax.swing.JButton();
        btn_dataBuku = new javax.swing.JButton();
        btn_penjualan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_dataPenjualan = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jp_kategori = new javax.swing.JPanel();
        jp11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_idKategori = new javax.swing.JTextField();
        txt_namaKategori = new javax.swing.JTextField();
        btn_simpanKategori = new javax.swing.JButton();
        btn_hapusKategori = new javax.swing.JButton();
        btn_editKategori = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jp12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jp13 = new javax.swing.JScrollPane();
        TabelKategori = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jp_buku = new javax.swing.JPanel();
        jp21 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_kodeBuku = new javax.swing.JTextField();
        txt_judulBuku = new javax.swing.JTextField();
        txt_pengarangBuku = new javax.swing.JTextField();
        txt_penerbitBuku = new javax.swing.JTextField();
        txt_tahunterbitBuku = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_hargapokokBuku = new javax.swing.JTextField();
        txt_hargajualBuku = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jp22 = new javax.swing.JPanel();
        bttn_simpanBuku = new javax.swing.JButton();
        btn_editBuku = new javax.swing.JButton();
        btn_deleteBuku = new javax.swing.JButton();
        btn_cancelBuku = new javax.swing.JToggleButton();
        jp23 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jp24 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        jp_penjualan = new javax.swing.JPanel();
        jp31 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBukuPenjualan = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelBukuDibeli = new javax.swing.JTable();
        txt_bayar = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        txt_hargatotalPenjualan = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        btn_inputDataPenjualan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jPanelDataPenjualan = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_kodeBukuPenjualan = new javax.swing.JTextField();
        txt_judulBukuPenjualan = new javax.swing.JTextField();
        txt_kategoriBukuPenjualan = new javax.swing.JTextField();
        txt_hargaBukuPenjualan = new javax.swing.JTextField();
        txt_jumlahBukuPenjualan = new javax.swing.JTextField();
        txt_totalhargaBukuPenjualan = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_kodeCustomer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tglPenjualan = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Menu");

        btn_kategori.setText("Kategori");
        btn_kategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kategoriMouseClicked(evt);
            }
        });
        btn_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kategoriActionPerformed(evt);
            }
        });

        btn_dataBuku.setText("Data Buku");
        btn_dataBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataBukuMouseClicked(evt);
            }
        });
        btn_dataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dataBukuActionPerformed(evt);
            }
        });

        btn_penjualan.setText("Penjualan");
        btn_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_penjualanMouseClicked(evt);
            }
        });
        btn_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_penjualanActionPerformed(evt);
            }
        });

        btn_dataPenjualan.setText("Data Penjualan");
        btn_dataPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dataPenjualanActionPerformed(evt);
            }
        });

        jButton8.setText("Logout");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btn_dataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_dataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(36, 36, 36)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_dataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_dataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton8))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 255, 0));

        jp_kategori.setPreferredSize(new java.awt.Dimension(755, 596));

        jLabel2.setText("ID Kategori");

        jLabel3.setText("Nama Kategori");

        txt_idKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idKategoriActionPerformed(evt);
            }
        });

        txt_namaKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaKategoriActionPerformed(evt);
            }
        });

        btn_simpanKategori.setText("Simpan Kategori");
        btn_simpanKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanKategoriActionPerformed(evt);
            }
        });

        btn_hapusKategori.setText("Hapus Kategori");
        btn_hapusKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusKategoriActionPerformed(evt);
            }
        });

        btn_editKategori.setText("Edit Kategori");
        btn_editKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editKategoriActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp11Layout = new javax.swing.GroupLayout(jp11);
        jp11.setLayout(jp11Layout);
        jp11Layout.setHorizontalGroup(
            jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_namaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jp11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btn_simpanKategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btn_editKategori)
                .addGap(31, 31, 31)
                .addComponent(btn_hapusKategori)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jp11Layout.setVerticalGroup(
            jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp11Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_namaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jp11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpanKategori)
                    .addComponent(btn_hapusKategori)
                    .addComponent(btn_editKategori)
                    .addComponent(jButton2)))
        );

        jLabel4.setText("Cari");

        javax.swing.GroupLayout jp12Layout = new javax.swing.GroupLayout(jp12);
        jp12.setLayout(jp12Layout);
        jp12Layout.setHorizontalGroup(
            jp12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp12Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel4)
                .addGap(64, 64, 64)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jp12Layout.setVerticalGroup(
            jp12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp12Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jp12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        TabelKategori.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelKategoriMouseClicked(evt);
            }
        });
        jp13.setViewportView(TabelKategori);

        jLabel17.setText("KATEGORI");

        javax.swing.GroupLayout jp_kategoriLayout = new javax.swing.GroupLayout(jp_kategori);
        jp_kategori.setLayout(jp_kategoriLayout);
        jp_kategoriLayout.setHorizontalGroup(
            jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_kategoriLayout.createSequentialGroup()
                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_kategoriLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jp12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jp13)
                            .addComponent(jp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_kategoriLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jLabel17)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jp_kategoriLayout.setVerticalGroup(
            jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_kategoriLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jp_buku.setPreferredSize(new java.awt.Dimension(755, 596));

        jLabel6.setText(" Kode Buku");

        jLabel7.setText("Judul buku");

        jLabel8.setText("Pengarang");

        jLabel9.setText("Penerbit");

        jLabel10.setText("Tahun Terbit");

        jLabel11.setText("Kategori Buku");

        cb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        cb_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoriActionPerformed(evt);
            }
        });

        jLabel13.setText("Harga Pokok");

        jLabel14.setText("Harga Jual");

        txt_hargajualBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargajualBukuActionPerformed(evt);
            }
        });

        jLabel18.setText("DATA BUKU");

        javax.swing.GroupLayout jp21Layout = new javax.swing.GroupLayout(jp21);
        jp21.setLayout(jp21Layout);
        jp21Layout.setHorizontalGroup(
            jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp21Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7))
                    .addGroup(jp21Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14)))
                .addGap(18, 18, 18)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_kodeBuku)
                    .addComponent(txt_judulBuku)
                    .addComponent(txt_pengarangBuku)
                    .addComponent(txt_penerbitBuku)
                    .addComponent(txt_tahunterbitBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cb_kategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jp21Layout.createSequentialGroup()
                            .addComponent(txt_hargapokokBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addComponent(txt_hargajualBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(293, 293, 293))
        );
        jp21Layout.setVerticalGroup(
            jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(57, 57, 57)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_judulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txt_hargapokokBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_pengarangBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_hargajualBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_penerbitBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_tahunterbitBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        bttn_simpanBuku.setText("Save");
        bttn_simpanBuku.setPreferredSize(new java.awt.Dimension(67, 25));
        bttn_simpanBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_simpanBukuActionPerformed(evt);
            }
        });

        btn_editBuku.setText("Edit");
        btn_editBuku.setPreferredSize(new java.awt.Dimension(67, 25));
        btn_editBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editBukuActionPerformed(evt);
            }
        });

        btn_deleteBuku.setText("Delete");
        btn_deleteBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteBukuActionPerformed(evt);
            }
        });

        btn_cancelBuku.setText("Cancel");
        btn_cancelBuku.setPreferredSize(new java.awt.Dimension(67, 25));
        btn_cancelBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelBukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp22Layout = new javax.swing.GroupLayout(jp22);
        jp22.setLayout(jp22Layout);
        jp22Layout.setHorizontalGroup(
            jp22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp22Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(bttn_simpanBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btn_editBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btn_deleteBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btn_cancelBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jp22Layout.setVerticalGroup(
            jp22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttn_simpanBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_deleteBuku)
                    .addComponent(btn_cancelBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setText("Cari");

        javax.swing.GroupLayout jp23Layout = new javax.swing.GroupLayout(jp23);
        jp23.setLayout(jp23Layout);
        jp23Layout.setHorizontalGroup(
            jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp23Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel15)
                .addGap(85, 85, 85)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp23Layout.setVerticalGroup(
            jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuMouseClicked(evt);
            }
        });
        jp24.setViewportView(tabelBuku);

        javax.swing.GroupLayout jp_bukuLayout = new javax.swing.GroupLayout(jp_buku);
        jp_buku.setLayout(jp_bukuLayout);
        jp_bukuLayout.setHorizontalGroup(
            jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_bukuLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jp21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp24))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jp_bukuLayout.setVerticalGroup(
            jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_bukuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jp21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp24, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_penjualan.setPreferredSize(new java.awt.Dimension(755, 596));

        tabelBukuPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBukuPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuPenjualanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelBukuPenjualan);

        jLabel16.setText("Cari Buku");

        jLabel23.setText("Bayar");

        jLabel24.setText("Kembalian");

        tabelBukuDibeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabelBukuDibeli);

        jButton5.setText("Proses");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btn_inputDataPenjualan.setText("Input Data");
        btn_inputDataPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inputDataPenjualanActionPerformed(evt);
            }
        });

        jLabel25.setText("Harga Total Seluruhnya");

        jButton4.setText("Proses Pembelian");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel26.setText("Daftar buku yang Dibeli");

        jToggleButton2.setText("Reset");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton5.setText("Simpan Transaksi");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        jLabel28.setText("Kode Buku");

        jLabel29.setText("Judul Buku");

        jLabel30.setText("Kategori Buku");

        jLabel31.setText("Harga");

        jLabel32.setText("Jumlah");

        jLabel33.setText("Total Harga");

        jLabel34.setText("Kode Customer");

        txt_kodeCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeCustomerActionPerformed(evt);
            }
        });

        jLabel5.setText(" Tanggal");

        javax.swing.GroupLayout jPanelDataPenjualanLayout = new javax.swing.GroupLayout(jPanelDataPenjualan);
        jPanelDataPenjualan.setLayout(jPanelDataPenjualanLayout);
        jPanelDataPenjualanLayout.setHorizontalGroup(
            jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataPenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataPenjualanLayout.createSequentialGroup()
                        .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(22, 22, 22)
                        .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kodeBukuPenjualan)
                            .addComponent(txt_judulBukuPenjualan)
                            .addComponent(txt_kategoriBukuPenjualan)
                            .addComponent(txt_hargaBukuPenjualan)
                            .addComponent(txt_jumlahBukuPenjualan)))
                    .addGroup(jPanelDataPenjualanLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(33, 33, 33)
                        .addComponent(txt_totalhargaBukuPenjualan))
                    .addGroup(jPanelDataPenjualanLayout.createSequentialGroup()
                        .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kodeCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txt_tglPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanelDataPenjualanLayout.setVerticalGroup(
            jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataPenjualanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_tglPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_kodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kodeBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_judulBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kategoriBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hargaBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlahBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_totalhargaBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jLabel19.setText("PENJUALAN");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp31Layout = new javax.swing.GroupLayout(jp31);
        jp31.setLayout(jp31Layout);
        jp31Layout.setHorizontalGroup(
            jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addGap(20, 20, 20))
            .addGroup(jp31Layout.createSequentialGroup()
                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp31Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp31Layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel19))
                    .addGroup(jp31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp31Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btn_inputDataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelDataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp31Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp31Layout.createSequentialGroup()
                                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25)
                                            .addComponent(jButton4))
                                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jp31Layout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_hargatotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jp31Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jToggleButton5))))
                                    .addGroup(jp31Layout.createSequentialGroup()
                                        .addComponent(jToggleButton2)
                                        .addGap(35, 35, 35)
                                        .addComponent(jButton1))))
                            .addGroup(jp31Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp31Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(jLabel26)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp31Layout.setVerticalGroup(
            jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp31Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp31Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp31Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txt_hargatotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp31Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jToggleButton2)
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)))
                        .addGap(18, 18, 18)
                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelDataPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(btn_inputDataPenjualan)
                    .addComponent(jButton4)
                    .addComponent(jToggleButton5))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jp_penjualanLayout = new javax.swing.GroupLayout(jp_penjualan);
        jp_penjualan.setLayout(jp_penjualanLayout);
        jp_penjualanLayout.setHorizontalGroup(
            jp_penjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_penjualanLayout.setVerticalGroup(
            jp_penjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_penjualanLayout.createSequentialGroup()
                .addComponent(jp31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_kategori, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp_buku, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 823, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_kategori, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp_buku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp_penjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dataBukuActionPerformed
        // TODO add your handling code here:
        KosongkanFormBuku();
        TampilDataBuku();
    }//GEN-LAST:event_btn_dataBukuActionPerformed

    private void btn_kategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kategoriMouseClicked
        // TODO add your handling code here:
        jp_kategori.setVisible(true);
        jp_buku.setVisible(false);
            jp21.setVisible(false);
            jp22.setVisible(false);
            jp23.setVisible(false);
            jp24.setVisible(false);
        jp_penjualan.setVisible(false);
            jp31.setVisible(false);
        
    }//GEN-LAST:event_btn_kategoriMouseClicked

    private void btn_dataBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataBukuMouseClicked
        // TODO add your handling code here:
        jp_buku.setVisible(true);   
            jp21.setVisible(true);  
            jp22.setVisible(true);  
            jp23.setVisible(true);  
            jp24.setVisible(true);  
        jp_kategori.setVisible(false);
        jp_penjualan.setVisible(false);
    }//GEN-LAST:event_btn_dataBukuMouseClicked

    private void btn_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_penjualanActionPerformed
        // TODO add your handling code here:
        TampilDataBukuPenjualan();
    }//GEN-LAST:event_btn_penjualanActionPerformed

    private void btn_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penjualanMouseClicked
        // TODO add your handling code here:
        jp_penjualan.setVisible(true);
           jp31.setVisible(true);
        jp_kategori.setVisible(false);
        jp_buku.setVisible(false);
    }//GEN-LAST:event_btn_penjualanMouseClicked

    private void txt_namaKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaKategoriActionPerformed

    private void txt_hargajualBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargajualBukuActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargajualBukuActionPerformed

    private void btn_inputDataPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputDataPenjualanActionPerformed
        // TODO add your handling code here:
        try{
            String sql="INSERT INTO daftarbuku_dibeli VALUES('" 
                    + txt_kodeCustomer.getText()+"',"
                    + "'" + txt_tglPenjualan.getDate()+"',"
                    + "'" + txt_kodeBukuPenjualan.getText()+"',"
                    + "'" + txt_judulBukuPenjualan.getText()+"',"
                    + "'" + txt_kategoriBukuPenjualan.getText()+"',"
                    + "'" + txt_hargaBukuPenjualan.getText()+"',"  
                    + "'" + txt_jumlahBukuPenjualan.getText()+"',"
                    + "'" + txt_totalhargaBukuPenjualan.getText()+"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            TampilDataBukuDibeli();
            KosongkanFormBukuDibeli();
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_inputDataPenjualanActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void btn_dataPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dataPenjualanActionPerformed
        // TODO add your handling code here:
        FormDataPenjualan fdp = new FormDataPenjualan();
        fdp.setVisible(true);
    }//GEN-LAST:event_btn_dataPenjualanActionPerformed

    private void txt_idKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKategoriActionPerformed

    private void btn_simpanKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanKategoriActionPerformed
        // TODO add your handling code here:
        try{
            String sql="INSERT INTO kategori VALUES('" + txt_idKategori.getText()+"',"
                    + "'" + txt_namaKategori.getText()+"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            TampilDataKategori();
            KosongkanFormKategori();
//            Tampil_cb_kategori();
            
           
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_btn_simpanKategoriActionPerformed

    private void btn_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kategoriActionPerformed
        // TODO add your handling code here: 
        KosongkanFormKategori();
        TampilDataKategori();
    }//GEN-LAST:event_btn_kategoriActionPerformed

    private void btn_hapusKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusKategoriActionPerformed
        // TODO add your handling code here:
        try{
            String sql="DELETE FROM kategori WHERE id='" + txt_idKategori.getText()+"'";
            
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataKategori();
            KosongkanFormKategori();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusKategoriActionPerformed

    private void TabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKategoriMouseClicked
        // TODO add your handling code here:
        txt_idKategori.setEditable(false);
        
        int baris=TabelKategori.rowAtPoint(evt.getPoint());
        String id=TabelKategori.getValueAt(baris,0).toString();
        txt_idKategori.setText(id);
        String nama=TabelKategori.getValueAt(baris,1).toString();
        txt_namaKategori.setText(nama);
        
    }//GEN-LAST:event_TabelKategoriMouseClicked

    private void btn_editKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editKategoriActionPerformed
        // TODO add your handling code here:
        try{
            String sql="UPDATE kategori SET id='" + txt_idKategori.getText()+"',"
                    + "nama_kategori='" + txt_namaKategori.getText()+"' WHERE id='"+txt_idKategori.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
            TampilDataKategori();
            KosongkanFormKategori();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_btn_editKategoriActionPerformed

    private void tabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBukuMouseClicked
        // TODO add your handling code here:
        txt_kodeBuku.setEditable(false);
        
        int baris=tabelBuku.rowAtPoint(evt.getPoint());
        
        String kode=tabelBuku.getValueAt(baris,1).toString();        
        txt_kodeBuku.setText(kode);
        
        String judul=tabelBuku.getValueAt(baris,2).toString();
        txt_judulBuku.setText(judul);
        
        String pengarang=tabelBuku.getValueAt(baris,3).toString();
        txt_pengarangBuku.setText(pengarang);
        
        String penerbit=tabelBuku.getValueAt(baris,4).toString();
        txt_penerbitBuku.setText(penerbit);
        
        String tahunterbit=tabelBuku.getValueAt(baris,5).toString();
        txt_tahunterbitBuku.setText(tahunterbit);
        
        String kategori=tabelBuku.getValueAt(baris,6).toString();
        cb_kategori.setSelectedItem(kategori);
        
        String hargapokok=tabelBuku.getValueAt(baris,7).toString();
        txt_hargapokokBuku.setText(hargapokok);
        
        String hargajual=tabelBuku.getValueAt(baris,8).toString();
        txt_hargajualBuku.setText(hargajual);
    }//GEN-LAST:event_tabelBukuMouseClicked

    private void cb_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_kategoriActionPerformed

    private void bttn_simpanBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_simpanBukuActionPerformed
        // TODO add your handling code here:
        try{
            String sql="INSERT INTO buku VALUES('" + txt_kodeBuku.getText()+"',"
                    + "'" + txt_judulBuku.getText()+"',"
                    + "'" + txt_pengarangBuku.getText()+"',"
                    + "'" + txt_penerbitBuku.getText()+"',"
                    + "'" + txt_tahunterbitBuku.getText()+"',"
                    + "'" + cb_kategori.getSelectedItem()+"',"  
                    + "'" + txt_hargapokokBuku.getText()+"',"
                    + "'" + txt_hargajualBuku.getText()+"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            TampilDataBuku();
            KosongkanFormBuku();
            Tampil_cb_kategori();
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_bttn_simpanBukuActionPerformed

    private void btn_editBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editBukuActionPerformed
        // TODO add your handling code here:
        try{
            String sql="UPDATE buku SET kode_buku='" + txt_kodeBuku.getText()+"',"
                    + "judul_buku='" + txt_judulBuku.getText()+"',"
                    + "pengarang_buku='" + txt_pengarangBuku.getText()+"',"
                    + "penerbit_buku='" + txt_penerbitBuku.getText()+"',"
                    + "tahunterbit_buku='" + txt_tahunterbitBuku.getText()+"',"
                    + "kategori_buku='" + cb_kategori.getSelectedItem()+"',"
                    + "hargapokok_buku='" + txt_hargapokokBuku.getText()+"',"
                    + "hargajual_buku='" + txt_hargajualBuku.getText()+"' WHERE kode_buku='"+txt_kodeBuku.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
            TampilDataBuku();
            KosongkanFormBuku();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_editBukuActionPerformed

    private void btn_deleteBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteBukuActionPerformed
        // TODO add your handling code here:
        try{
            String sql="DELETE FROM buku WHERE kode_buku='" + txt_kodeBuku.getText()+"'";
            
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataBuku();
            KosongkanFormBuku();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_deleteBukuActionPerformed

    private void btn_cancelBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelBukuActionPerformed
        // TODO add your handling code here:
        KosongkanFormBuku();
    }//GEN-LAST:event_btn_cancelBukuActionPerformed

    private void txt_kodeCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeCustomerActionPerformed

    private void tabelBukuPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBukuPenjualanMouseClicked
        // TODO add your handling code here:
        txt_kodeBukuPenjualan.setEditable(false);
        txt_judulBukuPenjualan.setEditable(false);
        txt_kategoriBukuPenjualan.setEditable(false);
        txt_hargaBukuPenjualan.setEditable(false);
        
        int baris=tabelBukuPenjualan.rowAtPoint(evt.getPoint());
        
        String kode=tabelBukuPenjualan.getValueAt(baris,1).toString();        
        txt_kodeBukuPenjualan.setText(kode);
        
        String judul=tabelBukuPenjualan.getValueAt(baris,2).toString();
        txt_judulBukuPenjualan.setText(judul);
        
        String kategori=tabelBukuPenjualan.getValueAt(baris,6).toString();
        txt_kategoriBukuPenjualan.setText(kategori);
        
        String hargajual=tabelBukuPenjualan.getValueAt(baris,8).toString();
        txt_hargaBukuPenjualan.setText(hargajual);
    }//GEN-LAST:event_tabelBukuPenjualanMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       double totalHarga ;      
       totalHarga = Double.parseDouble(txt_hargaBukuPenjualan.getText())*Double.parseDouble(txt_jumlahBukuPenjualan.getText());
       txt_totalhargaBukuPenjualan.setText(""+totalHarga);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
         try{
            String sql="DELETE FROM daftarbuku_dibeli ";
            
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            TampilDataBukuDibeli();
            KosongkanFormBukuDibeli();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            String sql="DELETE FROM daftarbuku_dibeli WHERE kode_customer='" + txt_kodeCustomer.getText()+"'";
            
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataBukuDibeli();
            KosongkanFormBukuDibeli();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        double hargatotal=0;
        for (int a=0; a< tabelBukuDibeli.getRowCount();a++){
            String y = tabelBukuDibeli.getValueAt(a,8).toString();
            double x = Double.parseDouble(y);
            hargatotal += x;
//            System.out.println(x);
        }
        txt_hargatotalPenjualan.setText(""+hargatotal);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        KosongkanFormKategori();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelKategori;
    private javax.swing.JToggleButton btn_cancelBuku;
    private javax.swing.JButton btn_dataBuku;
    private javax.swing.JButton btn_dataPenjualan;
    private javax.swing.JButton btn_deleteBuku;
    private javax.swing.JButton btn_editBuku;
    private javax.swing.JButton btn_editKategori;
    private javax.swing.JButton btn_hapusKategori;
    private javax.swing.JButton btn_inputDataPenjualan;
    private javax.swing.JButton btn_kategori;
    private javax.swing.JButton btn_penjualan;
    private javax.swing.JButton btn_simpanKategori;
    private javax.swing.JButton bttn_simpanBuku;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelDataPenjualan;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JPanel jp11;
    private javax.swing.JPanel jp12;
    private javax.swing.JScrollPane jp13;
    private javax.swing.JPanel jp21;
    private javax.swing.JPanel jp22;
    private javax.swing.JPanel jp23;
    private javax.swing.JScrollPane jp24;
    private javax.swing.JPanel jp31;
    private javax.swing.JPanel jp_buku;
    private javax.swing.JPanel jp_kategori;
    private javax.swing.JPanel jp_penjualan;
    private javax.swing.JTable tabelBuku;
    private javax.swing.JTable tabelBukuDibeli;
    private javax.swing.JTable tabelBukuPenjualan;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hargaBukuPenjualan;
    private javax.swing.JTextField txt_hargajualBuku;
    private javax.swing.JTextField txt_hargapokokBuku;
    private javax.swing.JTextField txt_hargatotalPenjualan;
    private javax.swing.JTextField txt_idKategori;
    private javax.swing.JTextField txt_judulBuku;
    private javax.swing.JTextField txt_judulBukuPenjualan;
    private javax.swing.JTextField txt_jumlahBukuPenjualan;
    private javax.swing.JTextField txt_kategoriBukuPenjualan;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_kodeBuku;
    private javax.swing.JTextField txt_kodeBukuPenjualan;
    private javax.swing.JTextField txt_kodeCustomer;
    private javax.swing.JTextField txt_namaKategori;
    private javax.swing.JTextField txt_penerbitBuku;
    private javax.swing.JTextField txt_pengarangBuku;
    private javax.swing.JTextField txt_tahunterbitBuku;
    private com.toedter.calendar.JDateChooser txt_tglPenjualan;
    private javax.swing.JTextField txt_totalhargaBukuPenjualan;
    // End of variables declaration//GEN-END:variables
}
