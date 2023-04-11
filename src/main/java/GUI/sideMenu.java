package GUI;


import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author macbookpro
 */
public class sideMenu extends javax.swing.JFrame {
    private CustomerPanel cus ;
    /**
     * Creates new form FageOne
     */
    public sideMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    String active = "home";
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        html = new javax.swing.JPanel();
        left_side_bar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nhaphang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        laphoadon = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        quanlykhachhang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        thongke = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        main = new javax.swing.JPanel();
        import_nhaphang = new javax.swing.JPanel();
        import_laphoadon = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        import_quanlykhachhang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        import_thongke = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        separator = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tpMain = new javax.swing.JTabbedPane();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        html.setBackground(new java.awt.Color(255, 255, 255));
        html.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        left_side_bar.setBackground(new java.awt.Color(0, 51, 51));
        left_side_bar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        left_side_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SALES MANAGER");
        left_side_bar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 180, 70));

        nhaphang.setBackground(new java.awt.Color(0, 102, 102));
        nhaphang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nhaphang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhaphangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nhaphangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nhaphangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhaphangMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setText("Nhập hàng");

        javax.swing.GroupLayout nhaphangLayout = new javax.swing.GroupLayout(nhaphang);
        nhaphang.setLayout(nhaphangLayout);
        nhaphangLayout.setHorizontalGroup(
            nhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhaphangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        nhaphangLayout.setVerticalGroup(
            nhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        left_side_bar.add(nhaphang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 240, 50));

        laphoadon.setBackground(new java.awt.Color(0, 102, 102));
        laphoadon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laphoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laphoadonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laphoadonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                laphoadonMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("Lập hoá đơn");

        javax.swing.GroupLayout laphoadonLayout = new javax.swing.GroupLayout(laphoadon);
        laphoadon.setLayout(laphoadonLayout);
        laphoadonLayout.setHorizontalGroup(
            laphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laphoadonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        laphoadonLayout.setVerticalGroup(
            laphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        left_side_bar.add(laphoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 240, 50));

        quanlykhachhang.setBackground(new java.awt.Color(0, 102, 102));
        quanlykhachhang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quanlykhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quanlykhachhangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                quanlykhachhangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quanlykhachhangMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setText("Quản lý khách hàng");

        javax.swing.GroupLayout quanlykhachhangLayout = new javax.swing.GroupLayout(quanlykhachhang);
        quanlykhachhang.setLayout(quanlykhachhangLayout);
        quanlykhachhangLayout.setHorizontalGroup(
            quanlykhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quanlykhachhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        quanlykhachhangLayout.setVerticalGroup(
            quanlykhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        left_side_bar.add(quanlykhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 240, 50));

        thongke.setBackground(new java.awt.Color(0, 102, 102));
        thongke.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        thongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongkeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongkeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                thongkeMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel8.setText("Thống kê");

        javax.swing.GroupLayout thongkeLayout = new javax.swing.GroupLayout(thongke);
        thongke.setLayout(thongkeLayout);
        thongkeLayout.setHorizontalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        thongkeLayout.setVerticalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        left_side_bar.add(thongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 240, -1));

        html.add(left_side_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 240, 930));

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        import_nhaphang.setBackground(new java.awt.Color(204, 204, 204));
        import_nhaphang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        main.add(import_nhaphang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 800));

        import_laphoadon.setBackground(new java.awt.Color(204, 204, 204));
        import_laphoadon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("laphoadon");
        import_laphoadon.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, 100, 20));

        main.add(import_laphoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 810));

        import_quanlykhachhang.setBackground(new java.awt.Color(204, 204, 204));
        import_quanlykhachhang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("quanlykhachhang");
        import_quanlykhachhang.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, 60, 20));

        main.add(import_quanlykhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 810));

        import_thongke.setBackground(new java.awt.Color(204, 204, 204));
        import_thongke.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Thongke");
        import_thongke.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, 100, 20));

        main.add(import_thongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 810));

        html.add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 740, 710));

        getContentPane().add(html, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 920));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        separator.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout separatorLayout = new javax.swing.GroupLayout(separator);
        separator.setLayout(separatorLayout);
        separatorLayout.setHorizontalGroup(
            separatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        separatorLayout.setVerticalGroup(
            separatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 880, 960, 20));

        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Project2-Sales Manager");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 900, -1, -1));
        jPanel2.add(tpMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 960, 870));

        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Code by java");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 900, -1, 17));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 970, 930));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int x, y;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try{
            //clearing
            main.removeAll();
            main.repaint();
            main.revalidate();
            //adding a pannel 
            main.add(import_nhaphang);
            main.repaint();
            main.revalidate();
        }catch(Exception e) {
            
        }
    }//GEN-LAST:event_formWindowOpened

    private void laphoadonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laphoadonMouseEntered
        laphoadon.setBackground(new Color(30,144,255));

    }//GEN-LAST:event_laphoadonMouseEntered

    private void laphoadonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laphoadonMouseExited
        if(  !(active.equalsIgnoreCase("laphoadon")  )){
            laphoadon.setBackground(new Color(0,102,102));
        }
    }//GEN-LAST:event_laphoadonMouseExited

    private void nhaphangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphangMouseEntered
        nhaphang.setBackground(new Color(30,144,255));
    }//GEN-LAST:event_nhaphangMouseEntered

    private void nhaphangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphangMouseExited
        if(  !(active.equalsIgnoreCase("nhaphang")  )){
            nhaphang.setBackground(new Color(0,102,102));
        }
    }//GEN-LAST:event_nhaphangMouseExited

    private void quanlykhachhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanlykhachhangMouseEntered
        quanlykhachhang.setBackground(new Color(30,144,255));
    }//GEN-LAST:event_quanlykhachhangMouseEntered

    private void quanlykhachhangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanlykhachhangMouseExited
        if(  !(active.equalsIgnoreCase("quanlykhachhang")  )){
            quanlykhachhang.setBackground(new Color(0,102,102));
        }
    }//GEN-LAST:event_quanlykhachhangMouseExited

    private void thongkeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkeMouseEntered
        thongke.setBackground(new Color(30,144,255));
    }//GEN-LAST:event_thongkeMouseEntered

    private void thongkeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkeMouseExited
        if(  !(active.equalsIgnoreCase("thongke")  )){
            thongke.setBackground(new Color(0,102,102));
        }
    }//GEN-LAST:event_thongkeMouseExited

    private void nhaphangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphangMousePressed
        active = "nhaphang";
        nhaphang.setBackground(new Color(30,144,255));
        laphoadon.setBackground(new Color(0,102,102));
        quanlykhachhang.setBackground(new Color(0,102,102));
        thongke.setBackground(new Color(0,102,102));
        try{
            //clearing
            main.removeAll();
            main.repaint();
            main.revalidate();
            //adding a pannel 
            main.add(import_nhaphang);
            main.repaint();
            main.revalidate();
        }catch(Exception e) {
            
        }
    }//GEN-LAST:event_nhaphangMousePressed

    private void laphoadonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laphoadonMousePressed
        active = "laphoadon";
        nhaphang.setBackground(new Color(0,102,102));
        laphoadon.setBackground(new Color(30,144,255));
        quanlykhachhang.setBackground(new Color(0,102,102));
        thongke.setBackground(new Color(0,102,102));
        try{
            //clearing
            main.removeAll();
            main.repaint();
            main.revalidate();
            //adding a pannel 
            main.add(import_laphoadon);
            main.repaint();
            main.revalidate();
        }catch(Exception e) {
            
        }
    }//GEN-LAST:event_laphoadonMousePressed

    private void quanlykhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanlykhachhangMousePressed
        active = "quanlykhachang";
        nhaphang.setBackground(new Color(0,102,102));
        laphoadon.setBackground(new Color(0,102,102));
        quanlykhachhang.setBackground(new Color(30,144,255));
        thongke.setBackground(new Color(0,102,102));
        try{
            //clearing
            main.removeAll();
            main.repaint();
            main.revalidate();
            //adding a pannel 
            main.add(import_quanlykhachhang);
            main.repaint();
            main.revalidate();
        }catch(Exception e) {
            
        }
    }//GEN-LAST:event_quanlykhachhangMousePressed

    private void thongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkeMousePressed
        active = "thongke";
        nhaphang.setBackground(new Color(0,102,102));
        laphoadon.setBackground(new Color(0,102,102));
        quanlykhachhang.setBackground(new Color(0,102,102));
        thongke.setBackground(new Color(30,144,255));
        try{
            //clearing
            main.removeAll();
            main.repaint();
            main.revalidate();
            //adding a pannel 
            main.add(import_thongke);
            main.repaint();
            main.revalidate();
        }catch(Exception e) {
            
        }
    }//GEN-LAST:event_thongkeMousePressed

    private void nhaphangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphangMouseClicked
        if(cus == null) {
        cus = new CustomerPanel();
        
        tpMain.addTab("ONSITE COURSE", cus);
        }
        tpMain.setSelectedComponent(cus);
    }//GEN-LAST:event_nhaphangMouseClicked

    /**c
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
            java.util.logging.Logger.getLogger(sideMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sideMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sideMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sideMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new sideMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel html;
    private javax.swing.JPanel import_laphoadon;
    private javax.swing.JPanel import_nhaphang;
    private javax.swing.JPanel import_quanlykhachhang;
    private javax.swing.JPanel import_thongke;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel laphoadon;
    private javax.swing.JPanel left_side_bar;
    private javax.swing.JPanel main;
    private javax.swing.JPanel nhaphang;
    private javax.swing.JPanel quanlykhachhang;
    private javax.swing.JPanel separator;
    private javax.swing.JPanel thongke;
    private javax.swing.JTabbedPane tpMain;
    // End of variables declaration//GEN-END:variables
}
