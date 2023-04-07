/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.CustomerBLL;
import BLL.OrderBLL;
import BLL.OrdersDetailBLL;
import BLL.VegetableBLL;
import DAL.HibernateUtils;
import DAL.VegetableDAL;
import DTO.Customers;
import DTO.OrderDetail;
import DTO.Orders;
import DTO.Vegetable;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class OrderManagementGUI extends javax.swing.JFrame{
    /**
     * Creates new form OrderManagementGUI
     */
    private DefaultTableModel tblordermodel;
    private DefaultTableModel tblvegetablemodel;
    private DefaultTableModel tblvegetableselectmodel;
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getInstance(localeVN);
    private List<OrderDetail> listveg = new ArrayList<>();
    private static Orders odCRUD = new Orders();
 
    public OrderManagementGUI() {
        initComponents();
        
        initTableOrders();//Tên cột sản phẩm đã chọn
        initTableOrderDetail();//Tên cột hóa đơn đã lưu
        loadDataToTableOrders();// Hiển thị dữ liệu sản phẩm đã thêm
        cbbcustomer.setSelectedItem("");
        cbbvegetable.setSelectedItem("");
        loadDataToCbCustomers();// Hiển thị dữ liệu khách hàng vào combobox
        cbbCustomereditable();// Hiển thị dữ liệu khách hàng tìm kiếm theo tên
        loadDataToCbVegetables();//Hiển thị dữ liệu sản phẩm vào combobox
        getTime();//Hiển thị ngày tháng năm hiện tại
        initTableVegetables();
        loadDataToTableVegetables();
        loadDataToCbb();
        this.setLocationRelativeTo(null);
    }

    private void initTableOrders() {
        tblordermodel = new DefaultTableModel();
        tblordermodel.setColumnIdentifiers(new String[] {"OrderID","CustomerID","Date","Total","Note"});
        tblorders.setModel(tblordermodel);
    }
    private void initTableOrderDetail() {
        tblvegetablemodel = new DefaultTableModel();
        tblvegetablemodel.setColumnIdentifiers(new String[] {"VegetableID","Name","Unit price","Quantity","Unit","Price"});
        tbloderdetail.setModel(tblvegetablemodel);
    }
    private void loadDataToTableOrders() {
        try {
            OrderBLL bll = new  OrderBLL();
            List<Orders> list = bll.LoadOrders(WIDTH);
            tblordermodel.setRowCount(0);
            for (Orders it : list) {
                tblordermodel.addRow(new Object[]{
                    it.getOrderID(),  it.getCustomerID(),it.getDate(), it.getTotal(), it.getNote()
              });
            }
            tblordermodel.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
    private void loadDataToTableOrdersDetails(List<OrderDetail> list) {
        float total = 0;
        try {
                tblvegetablemodel.setRowCount(0);
                for (OrderDetail it : list) {
                tblvegetablemodel.addRow(new Object[]{
                    it.getVegetable().getVegetableID(),  it.getVegetable().getVegetable_Name()
                        ,it.getVegetable().getPrice(), it.getQuantity(), it.getVegetable().getUnit(), it.getPrice()
              });
                total += it.getVegetable().getPrice()*it.getQuantity();
                
            }
                lbtotal.setText(String.valueOf(currencyVN.format(total)));
            tblvegetablemodel.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
    private void loadDataToCbCustomers() {
        try {
            CustomerBLL cusBLL = new CustomerBLL();
            List<Customers> listct = cusBLL.LoadCustomers(WIDTH);
            for (Customers ct : listct) {
                String name= ct.getCustomerID()+ " - "+ct.getFullname();
                cbbcustomer.addItem(name);
            }
        } catch (Exception e) {
        }
        
    }
    private void cbbCustomereditable() {
        JTextField editcus = (JTextField) cbbcustomer.getEditor().getEditorComponent();
        editcus.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            String findname = editcus.getText();
            try {
                if(findname.length()==0){
                    loadDataToCbCustomers();
                }
                CustomerBLL cusBLL = new CustomerBLL();
                List<Customers> listct = cusBLL.findbyName(findname);
                cbbcustomer.removeAllItems();    
                for (Customers ct : listct) {
                String name= ct.getCustomerID()+ " - "+ct.getFullname();
                cbbcustomer.addItem(name);
            }
            } catch (SQLException e) {
            }
            editcus.setText(findname);
            };
                });
    }
    private void loadDataToCbVegetables() {
        try {
            VegetableBLL vegBLL = new VegetableBLL();
            List<Vegetable> listvg = vegBLL.LoadVegetables(WIDTH);
            for (Vegetable vg : listvg) {
                String name= vg.getVegetableID()+ " - "+vg.getVegetable_Name();
                cbbvegetable.addItem(name);
            }
        } catch (SQLException e) {
        }
        
    }
    private void initTableVegetables() {
        tblvegetableselectmodel = new DefaultTableModel();
        tblvegetableselectmodel.setColumnIdentifiers(new String[] {"VegetableID","Vegetable Name","Category","Unit",
            "Amount","Price"});
        tblvegetableselect.setModel(tblvegetableselectmodel);
    }
    private void loadDataToTableVegetables() {
        try {
            VegetableBLL bll = new  VegetableBLL();
            List<Vegetable> list = bll.LoadVegetables(WIDTH);
            tblvegetableselectmodel.setRowCount(0);
            for (Vegetable it : list) {
                tblvegetableselectmodel.addRow(new Object[]{
                    it.getVegetableID(),  it.getVegetable_Name(),it.getCategory().getName(),
                    it.getUnit(), it.getAmount(), it.getPrice()
              });
            }
            tblvegetableselectmodel.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
     private void loadDataToCbb(){
         try {
            VegetableBLL bll = new  VegetableBLL();
            List<Vegetable> list = bll.LoadVegetables(WIDTH);
            List<String> temp1 = new ArrayList<String>();
            List<String> temp2 = new ArrayList<String>();
            List<String> listcate_name = new ArrayList<String>();
            List<String> listunit = new ArrayList<String>();
            cbbcategory.addItem("null");
            cbbunit.addItem("null");
            cbbamount.addItem("null");
            cbbprice.addItem("null");
            for (Vegetable vg : list) {
                temp1.add(vg.getCategory().getName());
                temp2.add(vg.getUnit());
            }
            for(String cate_name : temp1 ){
                if(!listcate_name.contains(cate_name)){
                    listcate_name.add(cate_name);
                    cbbcategory.addItem(cate_name);
                }
            }
            for(String unit : temp2 ){
                if(!listunit.contains(unit)){
                    listunit.add(unit);
                    cbbunit.addItem(unit);
                }
            }
            cbbamount.addItem("Amount <30");
            cbbamount.addItem("Amount >30");
            cbbamount.addItem("Amount >30");
            cbbamount.addItem("30<=Amount<=100");
            cbbamount.addItem("Amount >100");
            cbbprice.addItem("Descending");
            cbbprice.addItem("Ascending");
        } catch (Exception e) {
        }
     }
    private void getTime(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        lbTime.setText(sdf.format(date));
    }
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diaSelectVeg = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblvegetableselect = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cbbcategory = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbunit = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbbamount = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cbbprice = new javax.swing.JComboBox<>();
        btnfilter = new javax.swing.JButton();
        btnsearchveg = new javax.swing.JButton();
        txtkeyword = new javax.swing.JTextField();
        jpOrderControl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblorders = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnofind = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tanote = new javax.swing.JTextArea();
        jdfinddate = new com.toedter.calendar.JDateChooser();
        jpMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        cbbcustomer = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtcity = new javax.swing.JTextField();
        lbTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbloderdetail = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cbbvegetable = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtcategory = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtunit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtimage = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        spiquanity = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        lbprice = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbtotal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnoadd = new javax.swing.JButton();
        btnodelete = new javax.swing.JButton();
        btnoreset = new javax.swing.JButton();
        btnopenvegtbl = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel5.setText("SEARCH: Vegetable");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel15.setText("Veg Name:");

        tblvegetableselect.setModel(new javax.swing.table.DefaultTableModel(
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
        tblvegetableselect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblvegetableselectMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblvegetableselect);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter by", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel18.setText("Category name:");

        cbbcategory.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbbcategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbcategoryItemStateChanged(evt);
            }
        });
        cbbcategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbcategoryActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel21.setText("Unit:");

        cbbunit.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbbunit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbunitItemStateChanged(evt);
            }
        });
        cbbunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbunitActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel22.setText("Amount:");

        cbbamount.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbbamount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbamountItemStateChanged(evt);
            }
        });
        cbbamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbamountActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel23.setText("Price:");

        cbbprice.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbbprice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbpriceItemStateChanged(evt);
            }
        });
        cbbprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbpriceActionPerformed(evt);
            }
        });

        btnfilter.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnfilter.setText("Filter");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(cbbcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(71, 71, 71)
                        .addComponent(cbbprice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnfilter, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbamount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbunit, 0, 177, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbbcategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cbbunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(cbbamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(cbbprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnfilter)
                .addContainerGap())
        );

        btnsearchveg.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnsearchveg.setText("Search");

        txtkeyword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtkeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtkeywordKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtkeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsearchveg)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15)
                    .addComponent(btnsearchveg)
                    .addComponent(txtkeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout diaSelectVegLayout = new javax.swing.GroupLayout(diaSelectVeg.getContentPane());
        diaSelectVeg.getContentPane().setLayout(diaSelectVegLayout);
        diaSelectVegLayout.setHorizontalGroup(
            diaSelectVegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        diaSelectVegLayout.setVerticalGroup(
            diaSelectVegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpOrderControl.setBackground(new java.awt.Color(255, 255, 204));

        tblorders.setModel(new javax.swing.table.DefaultTableModel(
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
        tblorders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblordersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblorders);

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setText("Note:");

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel20.setText("Date:");

        btnofind.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnofind.setText("FIND");
        btnofind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnofindActionPerformed(evt);
            }
        });

        btnreset.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnreset.setText("RESET");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btndelete.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnupdate.setText("UPDATE");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnsave.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnsave.setText("SAVE");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        tanote.setColumns(20);
        tanote.setRows(5);
        jScrollPane4.setViewportView(tanote);

        jdfinddate.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jpOrderControlLayout = new javax.swing.GroupLayout(jpOrderControl);
        jpOrderControl.setLayout(jpOrderControlLayout);
        jpOrderControlLayout.setHorizontalGroup(
            jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrderControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOrderControlLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdfinddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnofind, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpOrderControlLayout.createSequentialGroup()
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpOrderControlLayout.createSequentialGroup()
                        .addGroup(jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpOrderControlLayout.setVerticalGroup(
            jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrderControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(btnofind))
                    .addComponent(jdfinddate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpOrderControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnupdate)
                    .addComponent(btnsave)
                    .addComponent(btndelete)
                    .addComponent(btnreset))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        getContentPane().add(jpOrderControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 0, -1, 830));

        jpMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        jLabel1.setText("ORDER MANAGEMENT");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Date:");

        txtdate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Customer:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Address:");

        txtaddress.setEditable(false);
        txtaddress.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        cbbcustomer.setEditable(true);
        cbbcustomer.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbbcustomer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbcustomerItemStateChanged(evt);
            }
        });
        cbbcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbcustomerActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("City:");

        txtcity.setEditable(false);
        txtcity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcity)
                    .addComponent(txtaddress))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtcity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lbTime.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbTime.setText("00/00/0000");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N

        tbloderdetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tbloderdetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbloderdetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbloderdetail);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("Vegetable:");

        cbbvegetable.setEditable(true);
        cbbvegetable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbbvegetable.setMaximumRowCount(2);
        cbbvegetable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbvegetableItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Category:");

        txtcategory.setEditable(false);
        txtcategory.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtprice.setEditable(false);
        txtprice.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtprice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtprice.setText("0");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Unit price:");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("/");

        txtunit.setEditable(false);
        txtunit.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setText("Image:");

        txtimage.setEditable(false);
        txtimage.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText("Quantity:");

        spiquanity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        spiquanity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spiquanityStateChanged(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel14.setText("Price:");

        lbprice.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbprice.setForeground(new java.awt.Color(0, 255, 102));
        lbprice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbprice.setText("0");

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel16.setText("VNĐ");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel17.setText("Total:");

        lbtotal.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lbtotal.setForeground(new java.awt.Color(255, 0, 0));
        lbtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbtotal.setText("0");
        lbtotal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel19.setText("VNĐ");

        btnoadd.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnoadd.setText("ADD");
        btnoadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoaddActionPerformed(evt);
            }
        });

        btnodelete.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnodelete.setText("DELETE");
        btnodelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnodeleteActionPerformed(evt);
            }
        });

        btnoreset.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnoreset.setText("RESET");
        btnoreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoresetActionPerformed(evt);
            }
        });

        btnopenvegtbl.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnopenvegtbl.setText("More");
        btnopenvegtbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopenvegtblActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(420, 420, 420)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel14)))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spiquanity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lbprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnoadd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(160, 160, 160))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel9)
                                                    .addGap(22, 22, 22))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addGap(20, 20, 20)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(44, 44, 44)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtimage, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(cbbvegetable, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtunit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnopenvegtbl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(70, 70, 70)
                                .addComponent(btnodelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(btnoreset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbvegetable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtcategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnopenvegtbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(spiquanity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtimage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lbprice)
                            .addComponent(jLabel16))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lbtotal)
                            .addComponent(jLabel19))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnoadd)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnodelete)
                        .addComponent(btnoreset)))
                .addGap(0, 96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpMainLayout = new javax.swing.GroupLayout(jpMain);
        jpMain.setLayout(jpMainLayout);
        jpMainLayout.setHorizontalGroup(
            jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMainLayout.createSequentialGroup()
                        .addGroup(jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpMainLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTime)
                                .addGap(26, 26, 26)))
                        .addContainerGap())
                    .addGroup(jpMainLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))))
        );
        jpMainLayout.setVerticalGroup(
            jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jpMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        txtdate.setText("");
        txtaddress.setText("");
        txtcity.setText("");
        cbbcustomer.setSelectedItem("");
        cbbvegetable.setSelectedItem("");
        tanote.setText("");
        txtcategory.setText("");
        txtunit.setText("");
        txtprice.setText("0");
        spiquanity.setValue(0);
        lbprice.setText("0");
        listveg.clear();
        initTableOrders();
        loadDataToTableOrders();
        loadDataToTableOrdersDetails(listveg);
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnoresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoresetActionPerformed
       txtcategory.setText("");
       txtunit.setText("");
       txtprice.setText("0");
       spiquanity.setValue(0);
       lbprice.setText("0");
       cbbvegetable.setSelectedIndex(-1);
    }//GEN-LAST:event_btnoresetActionPerformed

    private void btnodeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnodeleteActionPerformed
        OrderDetail odd = new OrderDetail();
        Vegetable veg = new Vegetable();
        VegetableBLL vegbll = new VegetableBLL();
        String vegselected = cbbvegetable.getSelectedItem().toString();
        int vegid = Character.getNumericValue(vegselected.charAt(0));
        try {
            veg = vegbll.getVegetable(vegid);
            odd.setVegetable(veg);
            odd.setQuantity((int) spiquanity.getValue());
            odd.setPrice(Float.parseFloat(lbprice.getText()));
            for(int i =0; i<listveg.size(); i++){
                OrderDetail oddcheck = listveg.get(i);
                if(odd.getVegetable().getVegetableID() == oddcheck.getVegetable().getVegetableID()){
                    listveg.remove(i);
                }
            }
            loadDataToTableOrdersDetails(listveg);
        } catch (SQLException ex) {
            Logger.getLogger(OrderManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnodeleteActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        if(cbbcustomer.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        JTextField editcus = (JTextField) cbbcustomer.getEditor().getEditorComponent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        editcus.setText(cbbcustomer.getSelectedItem().toString());
        String info = editcus.getText();
        int id = Character.getNumericValue(info.charAt(0));
        try{
            Orders od = new Orders();
            OrderBLL odbll = new OrderBLL();
            OrdersDetailBLL oddbll = new OrdersDetailBLL();
            VegetableBLL vegbll = new VegetableBLL();
            od.setDate(sdf.parse(txtdate.getText()));
            od.setCustomerID(id);
            od.setTotal(Float.parseFloat(lbtotal.getText())*1000);
            od.setNote(tanote.getText());
            if(odbll.addOrder(od)){
                for(OrderDetail odd : listveg){
                    odd.setOrderID(od.getOrderID());
                    odd.setVegetableID(odd.getVegetable().getVegetableID());
                    oddbll.addorupdateOrdersDetail(odd);
                    Vegetable vegupdate = vegbll.getVegetable(odd.getVegetableID());
                    vegupdate.setAmount(vegupdate.getAmount()-odd.getQuantity());
                    vegbll.updateVegetable(vegupdate);
                }
                JOptionPane.showMessageDialog(rootPane, "Lưu hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDataToTableOrders();
                resetInputField();
                loadDataToTableOrdersDetails(listveg);
            }else{
                 JOptionPane.showMessageDialog(rootPane, "Lưu hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                 resetInputField();
            }
        }catch(HeadlessException | NumberFormatException | SQLException | ParseException e){
            JOptionPane.showMessageDialog(rootPane, e, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void resetInputField() {
        txtdate.setText("");
        txtaddress.setText("");
        txtcity.setText("");
        cbbcustomer.setSelectedItem("");
        cbbvegetable.setSelectedItem("");
        tanote.setText("");
        txtcategory.setText("");
        txtunit.setText("");
        txtprice.setText("");
        spiquanity.setValue(1);
        lbprice.setText("0");
        listveg.clear();
    }

    private void cbbcustomerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbcustomerItemStateChanged
       if(cbbcustomer.getSelectedItem().equals("")){
           return;
       }else{
           String info = cbbcustomer.getSelectedItem().toString();
        int id = Character.getNumericValue(info.charAt(0)); 
        try {
             CustomerBLL cusBLL = new CustomerBLL();        
             Customers cus = cusBLL.getCustomer(id);
             txtdate.setText(lbTime.getText());
             txtaddress.setText(cus.getAddress());
             txtcity.setText(cus.getCity());
         } catch (SQLException ex) {
             Logger.getLogger(OrderManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
    }//GEN-LAST:event_cbbcustomerItemStateChanged

    private void btnoaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoaddActionPerformed
        if(spiquanity.getValue().equals(0)){
            JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderDetail odd = new OrderDetail();
        Vegetable veg = new Vegetable();
        VegetableBLL vegbll = new VegetableBLL();
        String vegselected = cbbvegetable.getSelectedItem().toString();
        int vegid = Character.getNumericValue(vegselected.charAt(0));
        boolean tontai = false;
        try {
            veg = vegbll.getVegetable(vegid);
            odd.setVegetable(veg);
            odd.setQuantity((int) spiquanity.getValue());
            odd.setPrice(Float.parseFloat(txtprice.getText().toString()) * (int) spiquanity.getValue());
            for(int i =0; i<listveg.size(); i++){
                OrderDetail oddcheck = listveg.get(i);
                if(odd.getVegetable().getVegetableID() == oddcheck.getVegetable().getVegetableID()){
                    odd.setQuantity((int) spiquanity.getValue()+oddcheck.getQuantity());
                    odd.setPrice(odd.getVegetable().getPrice()*odd.getQuantity());
                    listveg.set(i, odd);
                    tontai = true;
                }
            }
            if(!tontai){
                listveg.add(odd);
            }  
            loadDataToTableOrdersDetails(listveg);
        } catch (SQLException ex) {
            Logger.getLogger(OrderManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnoaddActionPerformed

    private void cbbcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbcustomerActionPerformed

    }//GEN-LAST:event_cbbcustomerActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        JTextField editcus = (JTextField) cbbcustomer.getEditor().getEditorComponent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        editcus.setText(cbbcustomer.getSelectedItem().toString());
        String info = editcus.getText();
        int id = Character.getNumericValue(info.charAt(0));
        try{
            OrderBLL odBLL = new OrderBLL();
            OrdersDetailBLL oddbll = new OrdersDetailBLL();
            Orders od = new Orders();
            VegetableBLL vegbll = new VegetableBLL();
            od.setOrderID(odCRUD.getOrderID());
            od.setDate(sdf.parse(txtdate.getText()));
            od.setCustomerID(id);
            od.setTotal(Float.parseFloat(lbtotal.getText())*1000);
            od.setNote(tanote.getText());
            if(odBLL.updateOrder(od)){
                 for(OrderDetail odd : listveg){
                    odd.setOrderID(od.getOrderID());
                    odd.setVegetableID(odd.getVegetable().getVegetableID());
                    oddbll.addorupdateOrdersDetail(odd);
                    Vegetable vegupdate = vegbll.getVegetable(odd.getVegetableID());
                    vegupdate.setAmount(vegupdate.getAmount()-odd.getQuantity());
                    vegbll.updateVegetable(vegupdate);
                }
                JOptionPane.showMessageDialog(rootPane, "Cập nhật hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetInputField();
                loadDataToTableOrders();               
                loadDataToTableOrdersDetails(listveg);
            }else{
                 JOptionPane.showMessageDialog(rootPane, "Cập nhật hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                 resetInputField();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void tblordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblordersMouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            int row = tblorders.getSelectedRow();
            if(row >= 0){         
                int id = (Integer) tblorders.getValueAt(row, 0);
                OrderBLL bll = new OrderBLL();
                OrdersDetailBLL odbll= new OrdersDetailBLL();
                odCRUD= bll.getOrder(id);
                if(odCRUD != null) {
                    txtdate     .setText(sdf.format(odCRUD.getDate()));
                    txtaddress  .setText(odCRUD.getCustomer().getAddress());
                    txtcity     .setText(odCRUD.getCustomer().getCity());
                    cbbcustomer     .setSelectedItem(odCRUD.getCustomerID()+" - "+ odCRUD.getCustomer().getFullname());
                    tanote      .setText(odCRUD.getNote());
                }
                listveg = odbll.findbyID(id);
                loadDataToTableOrdersDetails(listveg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblordersMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if(odCRUD == null){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa không?", "Hỏi"
            , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
        {
        return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            OrderBLL odBLL = new OrderBLL();
            OrdersDetailBLL oddbll = new OrdersDetailBLL();
            VegetableBLL vegbll = new VegetableBLL();
            Orders od = new Orders();
            od.setOrderID(odCRUD.getOrderID());
            od.setDate(sdf.parse(txtdate.getText()));
            od.setCustomerID(odCRUD.getCustomerID());
            od.setTotal(Float.parseFloat(lbtotal.getText()));
            od.setNote(tanote.getText());
            if(odBLL.deleteOrder(od)){
                for(OrderDetail odd : listveg){
                    odd.setOrderID(od.getOrderID());
                    odd.setVegetableID(odd.getVegetable().getVegetableID());
                    oddbll.deleteOrdersDetail(odd);
                    Vegetable vegupdate = vegbll.getVegetable(odd.getVegetableID());
                    vegupdate.setAmount(vegupdate.getAmount()+odd.getQuantity());
                    vegbll.updateVegetable(vegupdate);
                }
                JOptionPane.showMessageDialog(rootPane, "Xóa hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDataToTableOrders();
                resetInputField();
                loadDataToTableOrdersDetails(listveg);
            }else{
                 JOptionPane.showMessageDialog(rootPane, "Xóa hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                 resetInputField();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void cbbvegetableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbvegetableItemStateChanged
       if(cbbvegetable.getSelectedItem().toString().equals("")){
           return;
       }else{
           String info = cbbvegetable.getSelectedItem().toString();
           int id = Character.getNumericValue(info.charAt(0));
           spiquanity.setValue(0);
           try {
                VegetableBLL vegBLL = new VegetableBLL();        
                Vegetable veg = vegBLL.getVegetable(id);
                txtcategory.setText(veg.getCategory().getName());
                txtprice.setText(String.valueOf(veg.getPrice()));
                txtunit.setText(veg.getUnit());
                }catch (SQLException ex) {
                 Logger.getLogger(OrderManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
    }//GEN-LAST:event_cbbvegetableItemStateChanged

    private void spiquanityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spiquanityStateChanged
        float quality =  Float.parseFloat( spiquanity.getValue().toString()) ;
        if(txtprice.getText().equals("")){
            return;
        }
        float price = Float.parseFloat(txtprice.getText().toString()) * quality;
        lbprice.setText(String.valueOf(currencyVN.format(price)));
    }//GEN-LAST:event_spiquanityStateChanged

    private void tbloderdetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbloderdetailMouseClicked
        OrderDetail odd = new OrderDetail();
        try {
            int row = tbloderdetail.getSelectedRow();
            if(row >= 0){         
                odd= listveg.get(row);
                if(odd != null){
                    txtcategory     .setText(odd.getVegetable().getCategory().getName());
                    txtunit  .setText(odd.getVegetable().getUnit());
                    txtprice     .setText(String.valueOf(odd.getVegetable().getPrice()));
                    cbbvegetable     .setSelectedItem(odd.getVegetable().getVegetableID()+" - "+ odd.getVegetable().getVegetable_Name());
                    lbprice      .setText(String.valueOf(currencyVN.format(odd.getPrice())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tbloderdetailMouseClicked

    private void btnopenvegtblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopenvegtblActionPerformed
        diaSelectVeg.setSize(620, 470);
        diaSelectVeg.setVisible(true);
    }//GEN-LAST:event_btnopenvegtblActionPerformed

    private void tblvegetableselectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblvegetableselectMouseClicked
        try {
            int row = tblvegetableselect.getSelectedRow();
            if(row >= 0){         
                int id = (Integer) tblvegetableselect.getValueAt(row, 0);
                VegetableBLL bll = new VegetableBLL();
                Vegetable v = bll.getVegetable(id);
                if(v!= null){
                    txtcategory     .setText(v.getCategory().getName());
                    txtunit  .setText(v.getUnit());
                    txtprice     .setText(String.valueOf(v.getPrice()));
                    cbbvegetable     .setSelectedItem(v.getVegetableID()+" - "+ v.getVegetable_Name());
                    lbprice      .setText(String.valueOf(currencyVN.format(v.getPrice())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblvegetableselectMouseClicked

    private void cbbcategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbcategoryItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbcategoryItemStateChanged

    private void cbbcategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbcategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbcategoryActionPerformed

    private void cbbunitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbunitItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbunitItemStateChanged

    private void cbbunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbunitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbunitActionPerformed

    private void cbbamountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbamountItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbamountItemStateChanged

    private void cbbamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbamountActionPerformed

    private void cbbpriceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbpriceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbpriceItemStateChanged

    private void cbbpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbpriceActionPerformed

    private void txtkeywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkeywordKeyReleased

    }//GEN-LAST:event_txtkeywordKeyReleased

    private void btnofindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnofindActionPerformed
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date finddate = jdfinddate.getDate();
            OrderBLL odbll = new OrderBLL();
            List<Orders> odfind = odbll.findbyDate(finddate);
            initTableOrders();
            tblordermodel.setRowCount(0);
            for (Orders it : odfind) {
                tblordermodel.addRow(new Object[]{
                    it.getOrderID(),  it.getCustomerID(),it.getDate(), it.getTotal(), it.getNote()
              });
            }
            tblordermodel.fireTableDataChanged();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnofindActionPerformed
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(OrderManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderManagementGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnfilter;
    private javax.swing.JButton btnoadd;
    private javax.swing.JButton btnodelete;
    private javax.swing.JButton btnofind;
    private javax.swing.JButton btnopenvegtbl;
    private javax.swing.JButton btnoreset;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsearchveg;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbbamount;
    private javax.swing.JComboBox<String> cbbcategory;
    private javax.swing.JComboBox<String> cbbcustomer;
    private javax.swing.JComboBox<String> cbbprice;
    private javax.swing.JComboBox<String> cbbunit;
    private javax.swing.JComboBox<String> cbbvegetable;
    private javax.swing.JDialog diaSelectVeg;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdfinddate;
    private javax.swing.JPanel jpMain;
    private javax.swing.JPanel jpOrderControl;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbprice;
    private javax.swing.JLabel lbtotal;
    private javax.swing.JSpinner spiquanity;
    private javax.swing.JTextArea tanote;
    private javax.swing.JTable tbloderdetail;
    private javax.swing.JTable tblorders;
    private javax.swing.JTable tblvegetableselect;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtcategory;
    private javax.swing.JTextField txtcity;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtimage;
    private javax.swing.JTextField txtkeyword;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtunit;
    // End of variables declaration//GEN-END:variables

}
