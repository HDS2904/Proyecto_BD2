
package Vistas;
import AppPackage.AnimationClass;
import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import java.awt.Color;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {
    int cont = 0;
    private Teacher tea;
    private Person per;
    private TeacherDAO Pcrud;
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pingreso = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txusers = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txpass = new javax.swing.JPasswordField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jlmusica = new javax.swing.JLabel();
        jlinternet = new javax.swing.JLabel();
        jlcalculadora = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jlnot = new javax.swing.JLabel();
        jlsoc = new javax.swing.JLabel();
        jlprog = new javax.swing.JLabel();
        jlcron = new javax.swing.JLabel();
        jldri = new javax.swing.JLabel();
        jlman = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pingreso.setBackground(new java.awt.Color(255, 255, 255));
        pingreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pingreso.setForeground(new java.awt.Color(255, 255, 255));
        pingreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("USERS");
        pingreso.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("PASSWORD:");
        pingreso.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_User_96px_2.png"))); // NOI18N
        pingreso.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));
        pingreso.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 220, 10));
        pingreso.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 220, 10));

        txusers.setText("INGRESE USUARIO");
        txusers.setBorder(null);
        pingreso.add(txusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 180, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Menu_32px.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        pingreso.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_customer_32px_1.png"))); // NOI18N
        pingreso.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Key_32px.png"))); // NOI18N
        pingreso.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        txpass.setText("jPasswordFiel*********d1");
        txpass.setBorder(null);
        pingreso.add(txpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 180, 40));

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_OFF.png"))); // NOI18N
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToggleButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jToggleButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jToggleButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });
        pingreso.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jlmusica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Musical_Notes_32px.png"))); // NOI18N
        pingreso.add(jlmusica, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 150, 40, 40));

        jlinternet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Globe_32px.png"))); // NOI18N
        jlinternet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlinternetMouseClicked(evt);
            }
        });
        pingreso.add(jlinternet, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 70, 40, 40));

        jlcalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Calculator_32px.png"))); // NOI18N
        jlcalculadora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlcalculadoraMouseClicked(evt);
            }
        });
        pingreso.add(jlcalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 110, 40, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 102, 51));
        jLabel16.setText("LOGIN");
        pingreso.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Profesor", "Estudiante", "Administrador" }));
        jComboBox1.setBorder(null);
        pingreso.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 150, 20));

        getContentPane().add(pingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlnot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlnot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Idea_96px.png"))); // NOI18N
        jlnot.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlnotMouseMoved(evt);
            }
        });
        jlnot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlnotMouseExited(evt);
            }
        });
        jPanel1.add(jlnot, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 190, 140));

        jlsoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlsoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Handshake_96px.png"))); // NOI18N
        jlsoc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlsocMouseMoved(evt);
            }
        });
        jlsoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlsocMouseExited(evt);
            }
        });
        jPanel1.add(jlsoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 190, 140));

        jlprog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlprog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Today_96px.png"))); // NOI18N
        jlprog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlprogMouseMoved(evt);
            }
        });
        jlprog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlprogMouseExited(evt);
            }
        });
        jPanel1.add(jlprog, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 190, 140));

        jlcron.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlcron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Clock_96px.png"))); // NOI18N
        jlcron.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlcronMouseMoved(evt);
            }
        });
        jlcron.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlcronMouseExited(evt);
            }
        });
        jPanel1.add(jlcron, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 190, 140));

        jldri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jldri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Google_Drive_96px.png"))); // NOI18N
        jldri.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jldriMouseMoved(evt);
            }
        });
        jldri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jldriMouseExited(evt);
            }
        });
        jPanel1.add(jldri, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 190, 140));

        jlman.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Maintenance_96px.png"))); // NOI18N
        jlman.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlmanMouseMoved(evt);
            }
        });
        jlman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlmanMouseExited(evt);
            }
        });
        jPanel1.add(jlman, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 190, 140));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 102, 51));
        jLabel13.setText("Mantenimiento");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 51));
        jLabel14.setText("Programado");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 51));
        jLabel15.setText("Socios");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 51));
        jLabel17.setText("Notas");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 102, 51));
        jLabel18.setText("Drive");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("BUSCAR");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 99, 71)));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 230, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Search_32px_2.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 40, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 102, 51));
        jLabel22.setText("Cronograma");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 760, 530));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Multiply_32px.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 760, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        cont++;
        AnimationClass ani = new AnimationClass();
        if (cont%2 != 0){
            ani.jLabelXRight(-40, 10, 10, 5, jlinternet);
            ani.jLabelXRight(-40, 10, 10, 5, jlcalculadora);
            ani.jLabelXRight(-40, 10, 10, 5, jlmusica);
        }else{
            ani.jLabelXLeft(10, -40, 10, 5, jlinternet);
            ani.jLabelXLeft(10, -40, 10, 5, jlcalculadora);
            ani.jLabelXLeft(10, -40, 10, 5, jlmusica);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        int res = JOptionPane.YES_NO_OPTION;
        int dialog = JOptionPane.showConfirmDialog(null,"¿Seguro que quiere salir?","Exit",res);
        if(dialog == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        this.setState(Login.ICONIFIED);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jlinternetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlinternetMouseClicked
    OpenMenu1(1);
    }//GEN-LAST:event_jlinternetMouseClicked

    private void jlcalculadoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcalculadoraMouseClicked
     OpenMenu1(2);
    }//GEN-LAST:event_jlcalculadoraMouseClicked

    private void jlprogMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlprogMouseMoved
        jlprog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jlprogMouseMoved

    private void jlprogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlprogMouseExited
        jlprog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jlprogMouseExited

    private void jlcronMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcronMouseMoved
        jlcron.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jlcronMouseMoved

    private void jlcronMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcronMouseExited
        jlcron.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jlcronMouseExited

    private void jlmanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlmanMouseMoved
        jlman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jlmanMouseMoved

    private void jlmanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlmanMouseExited
        jlman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jlmanMouseExited

    private void jlnotMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlnotMouseMoved
        jlnot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jlnotMouseMoved

    private void jlnotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlnotMouseExited
        jlnot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jlnotMouseExited

    private void jldriMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldriMouseMoved
        jldri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jldriMouseMoved

    private void jldriMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldriMouseExited
        jldri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jldriMouseExited

    private void jlsocMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsocMouseMoved
        jlsoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jlsocMouseMoved

    private void jlsocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsocMouseExited
        jlsoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jlsocMouseExited

    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
        String users = txusers.getText();
        String password = txpass.getText();
        
    }//GEN-LAST:event_jToggleButton1MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel jlcalculadora;
    private javax.swing.JLabel jlcron;
    private javax.swing.JLabel jldri;
    private javax.swing.JLabel jlinternet;
    private javax.swing.JLabel jlman;
    private javax.swing.JLabel jlmusica;
    private javax.swing.JLabel jlnot;
    private javax.swing.JLabel jlprog;
    private javax.swing.JLabel jlsoc;
    private javax.swing.JPanel pingreso;
    private javax.swing.JPasswordField txpass;
    private javax.swing.JTextField txusers;
    // End of variables declaration//GEN-END:variables

    public void OpenMenu1(int i){
        Runtime rt = Runtime.getRuntime();
        switch(i){
            case 1: try {
                        Desktop.getDesktop().browse(URI.create("www.google.com.pe"));
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(this, e);
                    }
                    break;
            case 2: try {
                        
                        Process p = rt.exec("calc");
                        p.waitFor();
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(this, e);
                    }
                    break;
            case 3: try {
                        Process p = rt.exec("wmplayer");
                        p.waitFor();
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(this, e);
                    }
                    break;
        }
        
    }


}