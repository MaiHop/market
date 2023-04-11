/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BLL.CustomerBLL;
import DTO.Customers;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CustomerPanel extends javax.swing.JPanel {
    private CustomerBLL customersBLL = new CustomerBLL();
    private Customers customersdelete = new Customers();
    private  Customers customersUpdate = new Customers();
    private DefaultTableModel jtbCustomersModel;
    /**
     * Creates new form CustomerPanel
     */
    public CustomerPanel() {
        initComponents();
        initTable();
        LoadCustomersTable();
    }
    private void initTable() {
        jtbCustomersModel = new DefaultTableModel();
        jtbCustomersModel.setColumnIdentifiers(new String[] {"CustomerID","Fullname","Address","City"});
        jtbCustomers.setModel(jtbCustomersModel);
    }
    private void LoadCustomersTable() {
        try {
            CustomerBLL bll = new CustomerBLL();
            List<Customers> list = bll.LoadCustomers(WIDTH);
            jtbCustomersModel.setRowCount(0);
            for(Customers it : list) {
                jtbCustomersModel.addRow(new Object[] {
                it.getCustomerID(),it.getFullname(),it.getAddress(),it.getCity()});
            }
            jtbCustomersModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfFullName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfCity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jtfPassword = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCustomers = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel1.setText("FullName");

        jLabel2.setText("Password");

        jLabel3.setText("Address");

        jLabel4.setText("City");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jtfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchActionPerformed(evt);
            }
        });
        jtfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfSearchKeyReleased(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfCity, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jtfAddress)
                    .addComponent(jtfFullName)
                    .addComponent(jtfPassword))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addContainerGap(204, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfSearch, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSearch)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnUpdate)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete)))))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jtbCustomers.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbCustomersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbCustomers);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            Customers oc = new Customers();
            oc.setFullname(jtfFullName.getText());
            oc.setPassword(jtfPassword.getText());
            oc.setAddress(jtfAddress.getText());
            oc.setCity(jtfCity.getText());
            CustomerBLL bll = new CustomerBLL();

            if(bll.addCustomer(oc)){
                JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                LoadCustomersTable();
            }else{
                JOptionPane.showMessageDialog(null,"Thêm khách hàng thất bại" , "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jtfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchActionPerformed

    private void jtfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSearchKeyReleased
        try{
            CustomerBLL bll = new CustomerBLL();
            List<Customers> list = bll.findbyName(jtfSearch.getText());

            initTable();
            for(Customers it : list){
                jtbCustomersModel.addRow(new Object[] {
                    it.getCustomerID(),it.getFullname(),it.getAddress(),it.getCity()});
        }

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Tên người dùng không tồn tại" , "Cảnh báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jtfSearchKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try{
            CustomerBLL bll = new CustomerBLL();
            List<Customers> list = bll.findbyName((jtfSearch.getText()));

            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this,"Tên người dùng không tồn tại" , "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }else{
                for(Customers customers : list){
                    jtfFullName.setText(customers.getFullname());
                    jtfAddress.setText(customers.getAddress());
                    jtfCity.setText(customers.getCity());
                    LoadCustomersTable();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Tên người dùng không tồn tại" , "Cảnh báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            Customers oc = new Customers();
            oc.setCustomerID(this.customersUpdate.getCustomerID());
            oc.setFullname(jtfFullName.getText());
            oc.setPassword(jtfPassword.getText());
            oc.setAddress(jtfAddress.getText());
            oc.setCity(jtfCity.getText());

            if(JOptionPane.showConfirmDialog(this, "Bạn có muốn Update không?", "Hỏi"
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
            return;
        }
        CustomerBLL customersBLL = new CustomerBLL();
        if(customersBLL.updateCustomer(oc)){
            JOptionPane.showMessageDialog(null, "Update thành công");
            LoadCustomersTable();
        }else{
            JOptionPane.showMessageDialog(null, "Update thất bại");
        }
        } catch (Exception e) {
            Logger.getLogger(CustomersGUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            if(JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Hỏi"
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
            return;
        }
        if(customersBLL.deleteCustomer(customersdelete)){
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            LoadCustomersTable();
        }
        else{
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
        } catch (Exception e) {
            Logger.getLogger(CustomersGUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jtbCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbCustomersMouseClicked
        int ID = (Integer) this.jtbCustomers.getValueAt(this.jtbCustomers.getSelectedRow(), 0);
        Customers customers;
        try {
            customers = this.customersBLL.getCustomer(ID);
            customersdelete = this.customersBLL.getCustomer(ID);
            customersUpdate = this.customersBLL.getCustomer(ID);
            this.jtfFullName.setText(customers.getFullname());
            this.jtfPassword.setText(customers.getPassword());
            this.jtfAddress.setText(customers.getAddress());
            this.jtfCity.setText(customers.getCity());
        } catch (SQLException ex) {
            Logger.getLogger(CustomersGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jtbCustomersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbCustomers;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfCity;
    private javax.swing.JTextField jtfFullName;
    private javax.swing.JTextField jtfPassword;
    private javax.swing.JTextField jtfSearch;
    // End of variables declaration//GEN-END:variables
}
