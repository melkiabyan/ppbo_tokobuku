/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppbo_projectakhir;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import groovy.swing.SwingBuilder;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.layout.Background;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Yn
 */
public class FormMenuKasir extends javax.swing.JFrame {
private DefaultTableModel model;
private Font font = new Font("Comic Sans MS", Font.BOLD, 18);
private Color ganjil;
private Color genap;
    /**
     * Creates new form FormMenuKasrir
     */
    public FormMenuKasir() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
         jp_kategori.setVisible(true);
        jp_buku.setVisible(false);
//            jp21.setVisible(false);
//            jp22.setVisible(false);
//            jp23.setVisible(false);
//            jp24.setVisible(false);
        jp_penjualan.setVisible(false);
//            jp31.setVisible(false);
            
        //Menampilkan Tabel Pada Kategori
        KosongkanFormKategori();
        TampilDataKategori();
        Tampil_cb_kategori();

        
        //field yang tidak bisa diedit
        txt_kodeBukuPenjualan.setEditable(false);
        txt_judulBukuPenjualan.setEditable(false);
        txt_kategoriBukuPenjualan.setEditable(false);
        txt_hargaBukuPenjualan.setEditable(false);
        txt_hargatotalPenjualan.setEditable(false);
        
        // auto increment
        id_transaksi();
            id_transaksi.setVisible(false);
        kode_customer();
            txt_kodeCustomer.setEnabled(false);
        kode_transaksi();
            txt_kodeTransaksi.setEnabled(false);
            
        

    }
    
    
     public void KosongkanFormKategori(){
        txt_idKategori.setEditable(true);
        txt_idKategori.setText(null);
        txt_namaKategori.setText(null);

    }
    
    public void TampilDataKategori(){

        model = new DefaultTableModel();
               
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
        
        
        //mendekorasi header table
        TabelKategori.getTableHeader().setDefaultRenderer(new HeaderColor() );
        JTableHeader header = TabelKategori.getTableHeader();
        header.setOpaque(false);
        header.setReorderingAllowed(false);
        header.setFont(font);
        header.setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        
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
        model = new DefaultTableModel();
        
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
        
        //mendekorasi header table
        tabelBuku.getTableHeader().setDefaultRenderer(new HeaderColor() );
        JTableHeader header = tabelBuku.getTableHeader();
        header.setOpaque(false);
        header.setReorderingAllowed(false);
        header.setFont(font);
        header.setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void TampilDataBukuPenjualan(){
        model = new DefaultTableModel();
       
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
         //mendekorasi header table
        tabelBukuPenjualan.getTableHeader().setDefaultRenderer(new HeaderColor() );
        JTableHeader header = tabelBukuPenjualan.getTableHeader();
        header.setOpaque(false);
        header.setReorderingAllowed(false);
        header.setFont(font);
        header.setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
    
     public void KosongkanFormPenjualan(){
        txt_kodeBukuPenjualan.setText(null);
        txt_judulBukuPenjualan.setText(null);
        txt_kategoriBukuPenjualan.setText(null);
        txt_hargaBukuPenjualan.setText(null);
        txt_jumlahBukuPenjualan.setText(null);
        txt_totalhargaBukuPenjualan.setText(null);
    }
    
    public void Tampil_cb_kategori(){
        try{
            String sql="SELECT * FROM kategori";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            
            cb_kategori.addItem("--SELECT--");
            while(res.next()){
                cb_kategori.addItem(res.getString("nama_kategori"));
            }
            
//            res.last();
//            int jumlahdata = res.getRow();
//            res.first();
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    

    
    public void bayarKembalian(){
        double byr,totalHarga,kembalian;
        totalHarga = Double.parseDouble(txt_hargatotalPenjualan.getText());
        byr = Double.parseDouble(txt_bayar.getText());
        kembalian = byr-totalHarga;
         txt_kembalian.setText(String.valueOf(kembalian));
    }
    

     
     public void id_transaksi(){
         try{
            String sql="SELECT * FROM penjualan order by id_transaksi desc";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            if(res.next()){
                String kode = res.getString("id_transaksi").substring(1);
                String AN = "" +(Integer.parseInt(kode) + 1);
                String Nol = " ";
                
                if(AN.length()==1)
                {Nol = "00";}
                    else if(AN.length()==2)
                     {Nol = "0";}
                     else if(AN.length()==3)
                       {Nol = "0";}
                       
                       id_transaksi.setText("0"+Nol+AN);
                       
                       
            }else{
                id_transaksi.setText("0001");
            }   
         }
         
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
     
     
     public void kode_transaksi(){
         try{
            String sql="SELECT * FROM penjualan order by kode_transaksi desc";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            if(res.next()){
                String kode = res.getString("kode_transaksi").substring(1);
                String AN = "" +(Integer.parseInt(kode) + 1);
                String Nol = " ";
                
                if(AN.length()==1)
                {Nol = "00";}
                    else if(AN.length()==2)
                     {Nol = "0";}
                     else if(AN.length()==3)
                       {Nol = "0";}
                       
                       txt_kodeTransaksi.setText("T"+Nol+AN);
                       
                       
            }else{
                txt_kodeTransaksi.setText("T001");
            }   
         }
         
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
      
     public void kode_customer(){
         try{
            String sql="SELECT * FROM penjualan order by kode_customer desc";
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            if(res.next()){
                String kode = res.getString("kode_customer").substring(1);
                String AN = "" +(Integer.parseInt(kode) + 1);
                String Nol = " ";
                
                if(AN.length()==1)
                {Nol = "00";}
                    else if(AN.length()==2)
                     {Nol = "0";}
                     else if(AN.length()==3)
                       {Nol = "0";}
                       
                       txt_kodeCustomer.setText("C"+Nol+AN);
                       
                       
            }else{
                txt_kodeCustomer.setText("C001");
            }   
         }
         
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        btnKategori = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnDataBuku = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnPenjualan = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnDataPenjualan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jp_kategori = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JPanel();
        lblCancel = new javax.swing.JLabel();
        btnDelete = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnSave = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        btnEdit = new javax.swing.JPanel();
        lblEdit = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cariKategori = new javax.swing.JTextField();
        jp13 = new javax.swing.JScrollPane();
        TabelKategori = new javax.swing.JTable();
        txt_idKategori = new javax.swing.JTextField();
        txt_namaKategori = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
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
        jLabel11 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_hargapokokBuku = new javax.swing.JTextField();
        txt_hargajualBuku = new javax.swing.JTextField();
        txt_tahunterbitBuku = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnCancelBuku = new javax.swing.JPanel();
        lblCancelBuku = new javax.swing.JLabel();
        btnDeleteBuku = new javax.swing.JPanel();
        lblDeleteBuku = new javax.swing.JLabel();
        btnEditBuku = new javax.swing.JPanel();
        lblEditBuku = new javax.swing.JLabel();
        btnSaveBuku = new javax.swing.JPanel();
        lblSaveBuku = new javax.swing.JLabel();
        jp23 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cariBuku = new javax.swing.JTextField();
        jp24 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jp_penjualan = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBukuPenjualan = new javax.swing.JTable();
        cariBukuPenjualan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanelDataPenjualan6 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txt_kodeBukuPenjualan = new javax.swing.JTextField();
        txt_judulBukuPenjualan = new javax.swing.JTextField();
        txt_kategoriBukuPenjualan = new javax.swing.JTextField();
        txt_hargaBukuPenjualan = new javax.swing.JTextField();
        txt_jumlahBukuPenjualan = new javax.swing.JTextField();
        txt_totalhargaBukuPenjualan = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txt_kodeCustomer = new javax.swing.JTextField();
        btnProcess = new javax.swing.JPanel();
        lblProcess = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        id_transaksi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_kodeTransaksi = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_hargatotalPenjualan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tglPenjualan = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnST = new javax.swing.JPanel();
        lblST = new javax.swing.JLabel();
        btnCP = new javax.swing.JPanel();
        lblCP = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        panelJudul = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel3.setBackground(new java.awt.Color(66, 23, 193));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Kategori");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bodyPanel.setBackground(new java.awt.Color(66, 23, 193));

        menuPanel.setBackground(new java.awt.Color(66, 23, 193));
        menuPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        menuPanel.setAlignmentX(1.0F);
        menuPanel.setAlignmentY(1.0F);

        btnKategori.setBackground(new java.awt.Color(66, 23, 193));
        btnKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKategoriMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKategoriMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKategoriMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKategoriMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnKategoriMouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kategori");

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppbo_projectakhir/icon/icons8_category_24px_1.png"))); // NOI18N

        javax.swing.GroupLayout btnKategoriLayout = new javax.swing.GroupLayout(btnKategori);
        btnKategori.setLayout(btnKategoriLayout);
        btnKategoriLayout.setHorizontalGroup(
            btnKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKategoriLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnKategoriLayout.setVerticalGroup(
            btnKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKategoriLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(btnKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btnDataBuku.setBackground(new java.awt.Color(66, 23, 193));
        btnDataBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataBuku.setPreferredSize(new java.awt.Dimension(296, 79));
        btnDataBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDataBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDataBukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDataBukuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDataBukuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDataBukuMouseReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Data buku");

        jLabel34.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppbo_projectakhir/icon/icons8_books_24px.png"))); // NOI18N

        javax.swing.GroupLayout btnDataBukuLayout = new javax.swing.GroupLayout(btnDataBuku);
        btnDataBuku.setLayout(btnDataBukuLayout);
        btnDataBukuLayout.setHorizontalGroup(
            btnDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDataBukuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnDataBukuLayout.setVerticalGroup(
            btnDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDataBukuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(btnDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnPenjualan.setBackground(new java.awt.Color(66, 23, 193));
        btnPenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPenjualan.setPreferredSize(new java.awt.Dimension(296, 79));
        btnPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPenjualanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPenjualanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPenjualanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPenjualanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPenjualanMouseReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Penjualan");

        jLabel33.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppbo_projectakhir/icon/icons8_transaction_32px.png"))); // NOI18N

        javax.swing.GroupLayout btnPenjualanLayout = new javax.swing.GroupLayout(btnPenjualan);
        btnPenjualan.setLayout(btnPenjualanLayout);
        btnPenjualanLayout.setHorizontalGroup(
            btnPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPenjualanLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnPenjualanLayout.setVerticalGroup(
            btnPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPenjualanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(btnPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnDataPenjualan.setBackground(new java.awt.Color(66, 23, 193));
        btnDataPenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPenjualan.setPreferredSize(new java.awt.Dimension(296, 79));
        btnDataPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDataPenjualanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDataPenjualanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDataPenjualanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDataPenjualanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDataPenjualanMouseReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Data Penjualan");

        jLabel35.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppbo_projectakhir/icon/icons8_data_transfer_26px.png"))); // NOI18N

        javax.swing.GroupLayout btnDataPenjualanLayout = new javax.swing.GroupLayout(btnDataPenjualan);
        btnDataPenjualan.setLayout(btnDataPenjualanLayout);
        btnDataPenjualanLayout.setHorizontalGroup(
            btnDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDataPenjualanLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel35)
                .addGap(50, 50, 50)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnDataPenjualanLayout.setVerticalGroup(
            btnDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDataPenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnDataPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel22))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setAlignmentY(1.0F);

        jPanel5.setBackground(new java.awt.Color(66, 23, 193));

        jLabel32.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Hello,");

        label_username.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        label_username.setForeground(new java.awt.Color(255, 255, 255));
        label_username.setText("melki");
        label_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                label_usernameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_username, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppbo_projectakhir/icon/icons8_logout_rounded_up_26px.png"))); // NOI18N
        jLabel18.setText("Logout");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(btnDataPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(btnPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(btnKategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 228, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        mainPanel.setLayout(new java.awt.CardLayout());

        jp_kategori.setBackground(new java.awt.Color(255, 255, 255));
        jp_kategori.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("ID Kategori");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setText("Nama Kategori");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnCancel.setPreferredSize(new java.awt.Dimension(174, 48));
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelMousePressed(evt);
            }
        });

        lblCancel.setBackground(new java.awt.Color(255, 255, 255));
        lblCancel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblCancel.setText("Cancel");

        javax.swing.GroupLayout btnCancelLayout = new javax.swing.GroupLayout(btnCancel);
        btnCancel.setLayout(btnCancelLayout);
        btnCancelLayout.setHorizontalGroup(
            btnCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(btnCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCancelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblCancel)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnCancelLayout.setVerticalGroup(
            btnCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
            .addGroup(btnCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCancelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblCancel)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnDelete.setPreferredSize(new java.awt.Dimension(174, 48));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });

        lblDelete.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblDelete.setText("Delete");

        javax.swing.GroupLayout btnDeleteLayout = new javax.swing.GroupLayout(btnDelete);
        btnDelete.setLayout(btnDeleteLayout);
        btnDeleteLayout.setHorizontalGroup(
            btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnDeleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDelete)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnDeleteLayout.setVerticalGroup(
            btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnDeleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDelete)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnSave.setPreferredSize(new java.awt.Dimension(174, 48));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveMousePressed(evt);
            }
        });

        lblSave.setBackground(new java.awt.Color(0, 0, 0));
        lblSave.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblSave.setText("Save");
        lblSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSaveMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnSaveLayout = new javax.swing.GroupLayout(btnSave);
        btnSave.setLayout(btnSaveLayout);
        btnSaveLayout.setHorizontalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSaveLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblSave)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnSaveLayout.setVerticalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSaveLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblSave)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditMousePressed(evt);
            }
        });

        lblEdit.setBackground(new java.awt.Color(0, 0, 0));
        lblEdit.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblEdit.setText("Edit");

        javax.swing.GroupLayout btnEditLayout = new javax.swing.GroupLayout(btnEdit);
        btnEdit.setLayout(btnEditLayout);
        btnEditLayout.setHorizontalGroup(
            btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
            .addGroup(btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnEditLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblEdit)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnEditLayout.setVerticalGroup(
            btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnEditLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblEdit)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel3.setText("Search");

        cariKategori.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cariKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariKategoriActionPerformed(evt);
            }
        });
        cariKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKategoriKeyReleased(evt);
            }
        });

        jp13.setBackground(new java.awt.Color(255, 255, 255));

        TabelKategori.setAutoCreateRowSorter(true);
        TabelKategori.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        TabelKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabelKategori.setGridColor(new java.awt.Color(255, 255, 255));
        TabelKategori.setInheritsPopupMenu(true);
        TabelKategori.setRowHeight(35);
        TabelKategori.setSelectionBackground(new java.awt.Color(117, 74, 244));
        TabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelKategoriMouseClicked(evt);
            }
        });
        jp13.setViewportView(TabelKategori);

        txt_idKategori.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txt_idKategori.setText(" ");
        txt_idKategori.setPreferredSize(new java.awt.Dimension(6, 40));

        txt_namaKategori.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txt_namaKategori.setPreferredSize(new java.awt.Dimension(6, 40));

        jPanel2.setBackground(new java.awt.Color(66, 23, 193));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("KATEGORI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jp_kategoriLayout = new javax.swing.GroupLayout(jp_kategori);
        jp_kategori.setLayout(jp_kategoriLayout);
        jp_kategoriLayout.setHorizontalGroup(
            jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_kategoriLayout.createSequentialGroup()
                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_kategoriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jp13))
                    .addGroup(jp_kategoriLayout.createSequentialGroup()
                        .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_kategoriLayout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabel3)
                                .addGap(141, 141, 141)
                                .addComponent(cariKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                                .addGap(97, 97, 97))
                            .addGroup(jp_kategoriLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                                .addGap(93, 93, 93)
                                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idKategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_namaKategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_kategoriLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_kategoriLayout.setVerticalGroup(
            jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_kategoriLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_kategoriLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_kategoriLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_namaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cariKategori))
                .addGap(18, 18, 18)
                .addComponent(jp13, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(jp_kategori, "card2");

        jp_buku.setBackground(new java.awt.Color(255, 255, 255));
        jp_buku.setPreferredSize(new java.awt.Dimension(1101, 904));

        jp21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel6.setText("Kode Buku");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel7.setText("Judul buku");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel8.setText("Pengarang");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel9.setText("Penerbit");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel10.setText("Tahun Terbit");

        txt_kodeBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_judulBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_pengarangBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_penerbitBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel11.setText("Kategori Buku");

        cb_kategori.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        cb_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoriActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel13.setText("Harga Pokok");

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel14.setText("Harga Jual");

        txt_hargapokokBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_hargajualBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_hargajualBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargajualBukuActionPerformed(evt);
            }
        });

        txt_tahunterbitBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txt_tahunterbitBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tahunterbitBukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp21Layout = new javax.swing.GroupLayout(jp21);
        jp21.setLayout(jp21Layout);
        jp21Layout.setHorizontalGroup(
            jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_penerbitBuku, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_pengarangBuku, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_judulBuku, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_kodeBuku, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tahunterbitBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_kategori, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_hargapokokBuku, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_hargajualBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp21Layout.setVerticalGroup(
            jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp21Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp21Layout.createSequentialGroup()
                        .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp21Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel7)
                                .addGap(26, 26, 26)
                                .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_pengarangBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jp21Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel14))
                            .addGroup(jp21Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel13)))
                        .addGap(25, 25, 25)
                        .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_penerbitBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_tahunterbitBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addGroup(jp21Layout.createSequentialGroup()
                        .addComponent(txt_kodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txt_judulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp21Layout.createSequentialGroup()
                        .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txt_hargapokokBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(txt_hargajualBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnCancelBuku.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelBuku.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnCancelBuku.setPreferredSize(new java.awt.Dimension(40, 26));
        btnCancelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelBukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelBukuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelBukuMousePressed(evt);
            }
        });

        lblCancelBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblCancelBuku.setText(" Cancel");

        javax.swing.GroupLayout btnCancelBukuLayout = new javax.swing.GroupLayout(btnCancelBuku);
        btnCancelBuku.setLayout(btnCancelBukuLayout);
        btnCancelBukuLayout.setHorizontalGroup(
            btnCancelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(btnCancelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCancelBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblCancelBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnCancelBukuLayout.setVerticalGroup(
            btnCancelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
            .addGroup(btnCancelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCancelBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblCancelBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnDeleteBuku.setBackground(new java.awt.Color(255, 255, 255));
        btnDeleteBuku.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnDeleteBuku.setPreferredSize(new java.awt.Dimension(40, 26));
        btnDeleteBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteBukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteBukuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteBukuMousePressed(evt);
            }
        });

        lblDeleteBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblDeleteBuku.setText("Delete");

        javax.swing.GroupLayout btnDeleteBukuLayout = new javax.swing.GroupLayout(btnDeleteBuku);
        btnDeleteBuku.setLayout(btnDeleteBukuLayout);
        btnDeleteBukuLayout.setHorizontalGroup(
            btnDeleteBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
            .addGroup(btnDeleteBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnDeleteBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDeleteBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnDeleteBukuLayout.setVerticalGroup(
            btnDeleteBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnDeleteBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnDeleteBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDeleteBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnEditBuku.setBackground(new java.awt.Color(255, 255, 255));
        btnEditBuku.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnEditBuku.setPreferredSize(new java.awt.Dimension(40, 26));
        btnEditBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditBukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditBukuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditBukuMousePressed(evt);
            }
        });

        lblEditBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblEditBuku.setText("Edit");

        javax.swing.GroupLayout btnEditBukuLayout = new javax.swing.GroupLayout(btnEditBuku);
        btnEditBuku.setLayout(btnEditBukuLayout);
        btnEditBukuLayout.setHorizontalGroup(
            btnEditBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
            .addGroup(btnEditBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnEditBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblEditBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnEditBukuLayout.setVerticalGroup(
            btnEditBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnEditBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnEditBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblEditBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnSaveBuku.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveBuku.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnSaveBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveBukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveBukuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveBukuMousePressed(evt);
            }
        });

        lblSaveBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblSaveBuku.setText("Save");

        javax.swing.GroupLayout btnSaveBukuLayout = new javax.swing.GroupLayout(btnSaveBuku);
        btnSaveBuku.setLayout(btnSaveBukuLayout);
        btnSaveBukuLayout.setHorizontalGroup(
            btnSaveBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
            .addGroup(btnSaveBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSaveBukuLayout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(lblSaveBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(67, 67, 67)))
        );
        btnSaveBukuLayout.setVerticalGroup(
            btnSaveBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(btnSaveBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSaveBukuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblSaveBuku)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(btnSaveBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEditBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnCancelBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnDeleteBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnSaveBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jp23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel15.setText("Cari");

        cariBuku.setPreferredSize(new java.awt.Dimension(6, 40));
        cariBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBukuActionPerformed(evt);
            }
        });
        cariBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariBukuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jp23Layout = new javax.swing.GroupLayout(jp23);
        jp23.setLayout(jp23Layout);
        jp23Layout.setHorizontalGroup(
            jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp23Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85)
                .addComponent(cariBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(181, 181, 181))
        );
        jp23Layout.setVerticalGroup(
            jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp24.setBackground(new java.awt.Color(255, 255, 255));

        tabelBuku.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        tabelBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabelBuku.setGridColor(new java.awt.Color(255, 255, 255));
        tabelBuku.setRowHeight(35);
        tabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuMouseClicked(evt);
            }
        });
        jp24.setViewportView(tabelBuku);

        jPanel1.setBackground(new java.awt.Color(66, 23, 193));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("DATA BUKU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jp_bukuLayout = new javax.swing.GroupLayout(jp_buku);
        jp_buku.setLayout(jp_bukuLayout);
        jp_bukuLayout.setHorizontalGroup(
            jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_bukuLayout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .addComponent(jp23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
            .addGroup(jp_bukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp24))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_bukuLayout.setVerticalGroup(
            jp_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_bukuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(jp_buku, "card3");

        jp_penjualan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tabelBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        tabelBukuPenjualan.setGridColor(new java.awt.Color(255, 255, 255));
        tabelBukuPenjualan.setRowHeight(35);
        tabelBukuPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuPenjualanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelBukuPenjualan);

        cariBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        cariBukuPenjualan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariBukuPenjualanKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel16.setText("Search");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(28, 28, 28)
                .addComponent(cariBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(345, 345, 345))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        jPanelDataPenjualan6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel70.setText("Kode Buku");

        jLabel71.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel71.setText("Judul Buku");

        jLabel72.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel72.setText("Kategori Buku");

        jLabel73.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel73.setText("Harga");

        jLabel74.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel74.setText("Jumlah");

        jLabel75.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel75.setText("Total Harga");

        txt_kodeBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_judulBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_kategoriBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        txt_hargaBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_hargaBukuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaBukuPenjualanActionPerformed(evt);
            }
        });

        txt_jumlahBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_jumlahBukuPenjualan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahBukuPenjualanKeyReleased(evt);
            }
        });

        txt_totalhargaBukuPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_totalhargaBukuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalhargaBukuPenjualanActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel76.setText("Kode Customer");

        txt_kodeCustomer.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_kodeCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeCustomerActionPerformed(evt);
            }
        });

        btnProcess.setBackground(new java.awt.Color(255, 255, 255));
        btnProcess.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnProcess.setPreferredSize(new java.awt.Dimension(178, 30));
        btnProcess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProcessMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProcessMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProcessMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnProcessMousePressed(evt);
            }
        });

        lblProcess.setBackground(new java.awt.Color(255, 255, 255));
        lblProcess.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblProcess.setText("Process");

        javax.swing.GroupLayout btnProcessLayout = new javax.swing.GroupLayout(btnProcess);
        btnProcess.setLayout(btnProcessLayout);
        btnProcessLayout.setHorizontalGroup(
            btnProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
            .addGroup(btnProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnProcessLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblProcess)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnProcessLayout.setVerticalGroup(
            btnProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
            .addGroup(btnProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnProcessLayout.createSequentialGroup()
                    .addGap(0, 6, Short.MAX_VALUE)
                    .addComponent(lblProcess)
                    .addGap(0, 7, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelDataPenjualan6Layout = new javax.swing.GroupLayout(jPanelDataPenjualan6);
        jPanelDataPenjualan6.setLayout(jPanelDataPenjualan6Layout);
        jPanelDataPenjualan6Layout.setHorizontalGroup(
            jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kategoriBukuPenjualan)
                            .addComponent(txt_hargaBukuPenjualan)))
                    .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kodeBukuPenjualan)
                            .addComponent(txt_judulBukuPenjualan)
                            .addComponent(txt_kodeCustomer)))
                    .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75))
                        .addGap(52, 52, 52)
                        .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_jumlahBukuPenjualan)
                            .addComponent(txt_totalhargaBukuPenjualan)))
                    .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                        .addGap(0, 27, Short.MAX_VALUE)
                        .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelDataPenjualan6Layout.setVerticalGroup(
            jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataPenjualan6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txt_kodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(txt_kodeBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txt_judulBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txt_kategoriBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hargaBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(18, 18, 18)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txt_jumlahBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelDataPenjualan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totalhargaBukuPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        id_transaksi.setText(" ");
        id_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_transaksiActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel21.setText("Kode transaksi");

        txt_kodeTransaksi.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel25.setText("Harga Total Seluruhnya");

        txt_hargatotalPenjualan.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_hargatotalPenjualan.setText("0");
        txt_hargatotalPenjualan.setPreferredSize(new java.awt.Dimension(6, 40));

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel23.setText("Bayar");

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel24.setText("Kembalian");

        txt_bayar.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel12.setText("Rp. ");

        txt_kembalian.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txt_kembalian.setText("0");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel5.setText("Tanggal");

        txt_tglPenjualan.setPreferredSize(new java.awt.Dimension(6, 40));

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel26.setText("Rp. ");

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel27.setText("Rp. ");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        btnST.setBackground(new java.awt.Color(255, 255, 255));
        btnST.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnST.setPreferredSize(new java.awt.Dimension(178, 4));
        btnST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSTMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSTMousePressed(evt);
            }
        });

        lblST.setBackground(new java.awt.Color(255, 255, 255));
        lblST.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblST.setText("Save Transaction");

        javax.swing.GroupLayout btnSTLayout = new javax.swing.GroupLayout(btnST);
        btnST.setLayout(btnSTLayout);
        btnSTLayout.setHorizontalGroup(
            btnSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
            .addGroup(btnSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSTLayout.createSequentialGroup()
                    .addGap(203, 203, 203)
                    .addComponent(lblST, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(203, 203, 203)))
        );
        btnSTLayout.setVerticalGroup(
            btnSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
            .addGroup(btnSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnSTLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblST)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnCP.setBackground(new java.awt.Color(255, 255, 255));
        btnCP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btnCP.setPreferredSize(new java.awt.Dimension(178, 4));
        btnCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCPMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCPMousePressed(evt);
            }
        });

        lblCP.setBackground(new java.awt.Color(255, 255, 255));
        lblCP.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblCP.setText("Completed Payment");

        javax.swing.GroupLayout btnCPLayout = new javax.swing.GroupLayout(btnCP);
        btnCP.setLayout(btnCPLayout);
        btnCPLayout.setHorizontalGroup(
            btnCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
            .addGroup(btnCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCPLayout.createSequentialGroup()
                    .addGap(0, 193, Short.MAX_VALUE)
                    .addComponent(lblCP)
                    .addGap(0, 193, Short.MAX_VALUE)))
        );
        btnCPLayout.setVerticalGroup(
            btnCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
            .addGroup(btnCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCPLayout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(lblCP)
                    .addGap(0, 8, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_kembalian)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_hargatotalPenjualan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(txt_bayar, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel5))
                                .addGap(127, 127, 127)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tglPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(txt_kodeTransaksi)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(btnCP, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(btnST, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_tglPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_hargatotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel26)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel12)
                    .addComponent(txt_kembalian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnST, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnCP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(66, 23, 193));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("PENJUALAN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jp_penjualanLayout = new javax.swing.GroupLayout(jp_penjualan);
        jp_penjualan.setLayout(jp_penjualanLayout);
        jp_penjualanLayout.setHorizontalGroup(
            jp_penjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_penjualanLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanelDataPenjualan6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_penjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jp_penjualanLayout.setVerticalGroup(
            jp_penjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_penjualanLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_penjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDataPenjualan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        mainPanel.add(jp_penjualan, "card4");

        panelJudul.setBackground(new java.awt.Color(255, 255, 255));
        panelJudul.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        panelJudul.setForeground(new java.awt.Color(255, 255, 255));

        jLabel36.setIcon(new javax.swing.ImageIcon("C:\\Users\\Yn\\Downloads\\Arang Persegi Industri Logo (1).png")); // NOI18N

        javax.swing.GroupLayout panelJudulLayout = new javax.swing.GroupLayout(panelJudul);
        panelJudul.setLayout(panelJudulLayout);
        panelJudulLayout.setHorizontalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJudulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(320, 320, 320))
        );
        panelJudulLayout.setVerticalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cariKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariKategoriActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cariKategoriActionPerformed

    private void cariKategoriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKategoriKeyReleased
  String cari = cariKategori.getText();
        TableRowSorter tr = new TableRowSorter(model);
        TabelKategori.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter( cari ));
        
        TampilDataKategori();    
    }//GEN-LAST:event_cariKategoriKeyReleased

    private void TabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKategoriMouseClicked
        // TODO add your handling code here:
        txt_idKategori.setEditable(false);
        
        int baris=TabelKategori.rowAtPoint(evt.getPoint());
        String id=TabelKategori.getValueAt(baris,0).toString();
        txt_idKategori.setText(id);
        String nama=TabelKategori.getValueAt(baris,1).toString();
        txt_namaKategori.setText(nama);
    }//GEN-LAST:event_TabelKategoriMouseClicked

    private void cb_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_kategoriActionPerformed

    private void txt_hargajualBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargajualBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargajualBukuActionPerformed

    private void cariBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBukuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cariBukuActionPerformed

    private void cariBukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariBukuKeyReleased
        // TODO add your handling code here:
        String cari = cariBuku.getText();
        TableRowSorter tr = new TableRowSorter(model);
        tabelBuku.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter( cari ));

        TampilDataBuku();
    }//GEN-LAST:event_cariBukuKeyReleased

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

    private void cariBukuPenjualanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariBukuPenjualanKeyReleased
        // TODO add your handling code here:
        String cari = cariBukuPenjualan.getText();
        TableRowSorter tr = new TableRowSorter(model);
        tabelBukuPenjualan.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter( cari ));

        TampilDataBukuPenjualan();
    }//GEN-LAST:event_cariBukuPenjualanKeyReleased

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

    private void txt_hargaBukuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaBukuPenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaBukuPenjualanActionPerformed

    private void txt_jumlahBukuPenjualanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahBukuPenjualanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahBukuPenjualanKeyReleased

    private void txt_totalhargaBukuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalhargaBukuPenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalhargaBukuPenjualanActionPerformed

    private void txt_kodeCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeCustomerActionPerformed

    private void id_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_transaksiActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        bayarKembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void btnKategoriMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKategoriMouseEntered
        // TODO add your handling code here:
        btnKategori.setBackground(new Color(117,74,244));
        
    }//GEN-LAST:event_btnKategoriMouseEntered

    private void btnKategoriMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKategoriMouseExited
        // TODO add your handling code here:
        btnKategori.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnKategoriMouseExited

    private void btnKategoriMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKategoriMousePressed
        // TODO add your handling code here:
        btnKategori.setBackground(new Color(92,49,219 ));
    }//GEN-LAST:event_btnKategoriMousePressed

    private void btnKategoriMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKategoriMouseReleased
        // TODO add your handling code here:
        btnKategori.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnKategoriMouseReleased

    private void btnDataBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataBukuMouseEntered
        // TODO add your handling code here:
        btnDataBuku.setBackground(new Color(117,74,244));
    }//GEN-LAST:event_btnDataBukuMouseEntered

    private void btnPenjualanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenjualanMouseEntered
        // TODO add your handling code here:
        btnPenjualan.setBackground(new Color(117,74,244));
    }//GEN-LAST:event_btnPenjualanMouseEntered

    private void btnDataPenjualanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPenjualanMouseEntered
        // TODO add your handling code here:
        btnDataPenjualan.setBackground(new Color(117,74,244));
    }//GEN-LAST:event_btnDataPenjualanMouseEntered

    private void btnDataBukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataBukuMouseExited
        // TODO add your handling code here:
        btnDataBuku.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnDataBukuMouseExited

    private void btnPenjualanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenjualanMouseExited
        // TODO add your handling code here:
        btnPenjualan.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnPenjualanMouseExited

    private void btnDataPenjualanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPenjualanMouseExited
        // TODO add your handling code here:
        btnDataPenjualan.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnDataPenjualanMouseExited

    private void btnDataBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataBukuMousePressed
        // TODO add your handling code here:
        btnDataBuku.setBackground(new Color(92,49,219 ));
    }//GEN-LAST:event_btnDataBukuMousePressed

    private void btnPenjualanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenjualanMousePressed
        // TODO add your handling code here:
        btnPenjualan.setBackground(new Color(92,49,219 ));
    }//GEN-LAST:event_btnPenjualanMousePressed

    private void btnDataPenjualanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPenjualanMousePressed
        // TODO add your handling code here:
        btnDataPenjualan.setBackground(new Color(92,49,219 ));
    }//GEN-LAST:event_btnDataPenjualanMousePressed

    private void btnDataBukuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataBukuMouseReleased
        // TODO add your handling code here:
        btnDataBuku.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnDataBukuMouseReleased

    private void btnPenjualanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenjualanMouseReleased
        // TODO add your handling code here:
        btnPenjualan.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnPenjualanMouseReleased

    private void btnDataPenjualanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPenjualanMouseReleased
        // TODO add your handling code here:
        btnDataPenjualan.setBackground(new Color(66,23,193));
    }//GEN-LAST:event_btnDataPenjualanMouseReleased

    private void btnKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKategoriMouseClicked
        // TODO add your handling code here:
        KosongkanFormKategori();
        TampilDataKategori();
        
        jp_kategori.setVisible(true);
        jp_buku.setVisible(false);
//            jp21.setVisible(false);
//            jp22.setVisible(false);
//            jp23.setVisible(false);
//            jp24.setVisible(false);
        jp_penjualan.setVisible(false);
//            jp31.setVisible(false);
    }//GEN-LAST:event_btnKategoriMouseClicked

    private void btnDataBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataBukuMouseClicked
        // TODO add your handling code here:
         KosongkanFormBuku();
        TampilDataBuku();
        
         jp_buku.setVisible(true);   
//            jp21.setVisible(true);  
//            jp22.setVisible(true);  
//            jp23.setVisible(true);  
//            jp24.setVisible(true);  
        jp_kategori.setVisible(false);
        jp_penjualan.setVisible(false);
    }//GEN-LAST:event_btnDataBukuMouseClicked

    private void btnPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenjualanMouseClicked
        // TODO add your handling code here:
         TampilDataBukuPenjualan();
        
         jp_penjualan.setVisible(true);
//           jp31.setVisible(true);
        jp_kategori.setVisible(false);
        jp_buku.setVisible(false);
    }//GEN-LAST:event_btnPenjualanMouseClicked

    private void btnDataPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPenjualanMouseClicked
        // TODO add your handling code here:
        FormDataPenjualan fdp = new FormDataPenjualan();
        fdp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDataPenjualanMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        btnSave.setBackground(new Color(117,74,244));
        lblSave.setForeground(Color.WHITE);
        
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
        btnSave.setBackground(new Color(255,255,255));
        lblSave.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        btnSave.setBackground(new Color(92,49,219));
        lblSave.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveMousePressed

    private void lblSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaveMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSaveMouseEntered

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        // TODO add your handling code here:
        btnEdit.setBackground(new Color(117,74,244));
        lblEdit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
        btnDelete.setBackground(new Color(117,74,244));
        lblDelete.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        // TODO add your handling code here:
        btnCancel.setBackground(new Color(117,74,244));
         lblCancel.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        // TODO add your handling code here:
        btnEdit.setBackground(new Color(255,255,255));
        lblEdit.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnEditMouseExited

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
        btnDelete.setBackground(new Color(255,255,255));
        lblDelete.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        // TODO add your handling code here:
        btnCancel.setBackground(new Color(255,255,255));
        lblCancel.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnEditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMousePressed
        // TODO add your handling code here:
        btnEdit.setBackground(new Color(92,49,219));
        lblEdit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEditMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        // TODO add your handling code here:
        btnDelete.setBackground(new Color(92,49,219));
        lblDelete.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
        // TODO add your handling code here:
        btnCancel.setBackground(new Color(92,49,219));
        lblCancel.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCancelMousePressed

    private void btnSaveBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBukuMouseEntered
        // TODO add your handling code here:
        btnSaveBuku.setBackground(new Color(117,74,244));
        lblSaveBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveBukuMouseEntered

    private void btnEditBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditBukuMouseEntered
        // TODO add your handling code here:
        btnEditBuku.setBackground(new Color(117,74,244));
        lblEditBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEditBukuMouseEntered

    private void btnDeleteBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteBukuMouseEntered
        // TODO add your handling code here:
        btnDeleteBuku.setBackground(new Color(117,74,244));
        lblDeleteBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDeleteBukuMouseEntered

    private void btnCancelBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelBukuMouseEntered
        // TODO add your handling code here:
        btnCancelBuku.setBackground(new Color(117,74,244));
        lblCancelBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCancelBukuMouseEntered

    private void btnSaveBukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBukuMouseExited
        // TODO add your handling code here:
        btnSaveBuku.setBackground(new Color(255,255,255));
        lblSaveBuku.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSaveBukuMouseExited

    private void btnEditBukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditBukuMouseExited
        // TODO add your handling code here:
        btnEditBuku.setBackground(new Color(255,255,255));
        lblEditBuku.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnEditBukuMouseExited

    private void btnDeleteBukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteBukuMouseExited
        // TODO add your handling code here:
        btnDeleteBuku.setBackground(new Color(255,255,255));
        lblDeleteBuku.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnDeleteBukuMouseExited

    private void btnCancelBukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelBukuMouseExited
        // TODO add your handling code here:
        btnCancelBuku.setBackground(new Color(255,255,255));
        lblCancelBuku.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnCancelBukuMouseExited

    private void btnSaveBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBukuMousePressed
        // TODO add your handling code here:
        btnSaveBuku.setBackground(new Color(92,49,219));
        lblSaveBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveBukuMousePressed

    private void btnEditBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditBukuMousePressed
        // TODO add your handling code here:
        btnEditBuku.setBackground(new Color(92,49,219));
        lblEditBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEditBukuMousePressed

    private void btnDeleteBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteBukuMousePressed
        // TODO add your handling code here:
        btnDeleteBuku.setBackground(new Color(92,49,219));
        lblDeleteBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDeleteBukuMousePressed

    private void btnCancelBukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelBukuMousePressed
        // TODO add your handling code here:
        btnCancelBuku.setBackground(new Color(92,49,219));
        lblCancelBuku.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCancelBukuMousePressed

    private void btnProcessMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcessMouseEntered
        // TODO add your handling code here:
        btnProcess.setBackground(new Color(117,74,244));
        lblProcess.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnProcessMouseEntered

    private void btnSTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSTMouseEntered
        // TODO add your handling code here:
        btnST.setBackground(new Color(117,74,244));
        lblST.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSTMouseEntered

    private void btnProcessMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcessMouseExited
        // TODO add your handling code here:
        btnProcess.setBackground(new Color(255,255,255));
        lblProcess.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnProcessMouseExited

    private void btnSTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSTMouseExited
        // TODO add your handling code here:
        btnST.setBackground(new Color(255,255,255));
        lblST.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSTMouseExited

    private void btnProcessMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcessMousePressed
        // TODO add your handling code here:
         btnProcess.setBackground(new Color(92,49,219));
        lblProcess.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnProcessMousePressed

    private void btnSTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSTMousePressed
        // TODO add your handling code here:
         btnST.setBackground(new Color(92,49,219));
        lblST.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSTMousePressed

    private void btnCPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPMouseEntered
        // TODO add your handling code here:
         btnCP.setBackground(new Color(117,74,244));
        lblCP.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCPMouseEntered

    private void btnCPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPMouseExited
        // TODO add your handling code here:
        btnCP.setBackground(new Color(255,255,255));
        lblCP.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnCPMouseExited

    private void btnCPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPMousePressed
        // TODO add your handling code here:
        btnCP.setBackground(new Color(92,49,219));
        lblCP.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCPMousePressed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
       try{
            String sql="INSERT INTO kategori VALUES('" + txt_idKategori.getText()+"',"
                    + "'" + txt_namaKategori.getText()+"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            cb_kategori.removeAllItems();
            TampilDataKategori();
            KosongkanFormKategori();
            Tampil_cb_kategori();
            
           
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        try{
            String sql="UPDATE kategori SET id='" + txt_idKategori.getText()+"',"
                    + "nama_kategori='" + txt_namaKategori.getText()+"' WHERE id='"+txt_idKategori.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
            cb_kategori.removeAllItems();
            TampilDataKategori();
            KosongkanFormKategori();
            Tampil_cb_kategori();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
          try{
            String sql="DELETE FROM kategori WHERE id='" + txt_idKategori.getText()+"'";
            
            java.sql.Connection conn=(Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            cb_kategori.removeAllItems();
            TampilDataKategori();
            KosongkanFormKategori();
            Tampil_cb_kategori();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        // TODO add your handling code here:
         KosongkanFormKategori();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnSaveBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBukuMouseClicked
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
    }//GEN-LAST:event_btnSaveBukuMouseClicked

    private void txt_tahunterbitBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tahunterbitBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tahunterbitBukuActionPerformed

    private void btnEditBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditBukuMouseClicked
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
    }//GEN-LAST:event_btnEditBukuMouseClicked

    private void btnDeleteBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteBukuMouseClicked
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
    }//GEN-LAST:event_btnDeleteBukuMouseClicked

    private void btnCancelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelBukuMouseClicked
        // TODO add your handling code here:
         KosongkanFormBuku();
    }//GEN-LAST:event_btnCancelBukuMouseClicked

    private void btnProcessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcessMouseClicked
        // TODO add your handling code here:
        double totalHarga ;      
       totalHarga = Double.parseDouble(txt_hargaBukuPenjualan.getText())*Double.parseDouble(txt_jumlahBukuPenjualan.getText());
       txt_totalhargaBukuPenjualan.setText(""+totalHarga);
    }//GEN-LAST:event_btnProcessMouseClicked

    private void btnSTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSTMouseClicked
        // TODO add your handling code here:
        String tampilan="yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(sdf.format(txt_tglPenjualan.getDate()));
         try{
            String sql="INSERT INTO penjualan VALUES('" 
                    + id_transaksi.getText()+"',"
                    + "'" + tanggal +"',"
                    + "'" + txt_kodeTransaksi.getText()+"',"
                    + "'" + label_username.getText()+"',"
                    + "'" + txt_kodeCustomer.getText()+"',"
                    + "'" + txt_kodeBukuPenjualan.getText()+"',"
                    + "'" + txt_judulBukuPenjualan.getText()+"',"
                    + "'" + txt_hargaBukuPenjualan.getText()+"'," 
                    + "'" + txt_jumlahBukuPenjualan.getText()+"',"
                    + "'" + txt_totalhargaBukuPenjualan.getText()+"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            
            double total = Double.valueOf(txt_hargatotalPenjualan.getText());
            double x = Double.valueOf(txt_totalhargaBukuPenjualan.getText());
                    total = total + x;
            txt_hargatotalPenjualan.setText(String.valueOf(total));
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
         
         
         id_transaksi();
         KosongkanFormPenjualan();
    }//GEN-LAST:event_btnSTMouseClicked

    private void btnCPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPMouseClicked
        // TODO add your handling code here:
         try{
            double kembalian;
            int bayar = Integer.parseInt(txt_bayar.getText());
             kembalian = Double.parseDouble(txt_kembalian.getText());
            String sql="INSERT INTO pembayaran VALUES('" 
                    + txt_kodeTransaksi.getText()+"',"
                    + "'" + bayar +"',"
                    + "'" + kembalian +"')";
        
            java.sql.Connection conn=(Connection)Konfig.configDB();
            
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Simpan data baru berhasil");
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnCPMouseClicked

    private void label_usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label_usernameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_label_usernameKeyReleased

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        login l1 = new login();
        l1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

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
            java.util.logging.Logger.getLogger(FormMenuKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenuKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenuKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenuKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenuKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelKategori;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel btnCP;
    private javax.swing.JPanel btnCancel;
    private javax.swing.JPanel btnCancelBuku;
    private javax.swing.JPanel btnDataBuku;
    private javax.swing.JPanel btnDataPenjualan;
    private javax.swing.JPanel btnDelete;
    private javax.swing.JPanel btnDeleteBuku;
    private javax.swing.JPanel btnEdit;
    private javax.swing.JPanel btnEditBuku;
    private javax.swing.JPanel btnKategori;
    private javax.swing.JPanel btnPenjualan;
    private javax.swing.JPanel btnProcess;
    private javax.swing.JPanel btnST;
    private javax.swing.JPanel btnSave;
    private javax.swing.JPanel btnSaveBuku;
    private javax.swing.JTextField cariBuku;
    private javax.swing.JTextField cariBukuPenjualan;
    private javax.swing.JTextField cariKategori;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JTextField id_transaksi;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDataPenjualan6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane jp13;
    private javax.swing.JPanel jp21;
    private javax.swing.JPanel jp23;
    private javax.swing.JScrollPane jp24;
    private javax.swing.JPanel jp_buku;
    private javax.swing.JPanel jp_kategori;
    private javax.swing.JPanel jp_penjualan;
    public static final javax.swing.JLabel label_username = new javax.swing.JLabel();
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblCancelBuku;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblDeleteBuku;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JLabel lblEditBuku;
    private javax.swing.JLabel lblProcess;
    private javax.swing.JLabel lblST;
    private javax.swing.JLabel lblSave;
    private javax.swing.JLabel lblSaveBuku;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panelJudul;
    private javax.swing.JTable tabelBuku;
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
    private javax.swing.JLabel txt_kembalian;
    private javax.swing.JTextField txt_kodeBuku;
    private javax.swing.JTextField txt_kodeBukuPenjualan;
    private javax.swing.JTextField txt_kodeCustomer;
    private javax.swing.JTextField txt_kodeTransaksi;
    private javax.swing.JTextField txt_namaKategori;
    private javax.swing.JTextField txt_penerbitBuku;
    private javax.swing.JTextField txt_pengarangBuku;
    private javax.swing.JTextField txt_tahunterbitBuku;
    private com.toedter.calendar.JDateChooser txt_tglPenjualan;
    private javax.swing.JTextField txt_totalhargaBukuPenjualan;
    // End of variables declaration//GEN-END:variables

    static public class HeaderColor extends DefaultTableCellRenderer {
    
        public HeaderColor(){
            setOpaque(true);
        }
    @Override
    public Component getTableCellRendererComponent(JTable mytable, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(mytable, value, isSelected, hasFocus, row, column);
        setBackground(new java.awt.Color(66,23,193));
        setForeground(Color.WHITE);
        
        return this;
        
        }
    }
    

    
    
}


    
