/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.Controler_Alternative;
import Controladores.Controler_Alternative_Exam;
import Controladores.Controler_Exam;
import Controladores.Controler_Question_Exam;
import Controladores.Controler_Section;
import Modelo.Entidades.Alternative;
import Modelo.Entidades.AlternativeExam;
import Modelo.Entidades.Exam;
import Modelo.Entidades.Question;
import Modelo.Entidades.QuestionExam;
import Modelo.Entidades.Section;
import Modelo.Entidades.Subject;
import Modelo.Entidades.Teacher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Exam_pre_5 extends javax.swing.JFrame {

    /**
     * Creates new form Exam_pre_5
     */
    
    int cantidad,seccion;
    
    public Exam_pre_5(int cant,int sec) throws Exception {
        initComponents();
        cantidad = cant;
        seccion = sec;
        cargarDatos();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        rtnAlterA_1 = new javax.swing.JRadioButton();
        rtnAlterB_1 = new javax.swing.JRadioButton();
        rtnAlterC_1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        rtnAlterA_2 = new javax.swing.JRadioButton();
        rtnAlterB_2 = new javax.swing.JRadioButton();
        rtnAlterC_2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        rtnAlterA_3 = new javax.swing.JRadioButton();
        rtnAlterB_3 = new javax.swing.JRadioButton();
        rtnAlterC_3 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        rtnAlterA_4 = new javax.swing.JRadioButton();
        rtnAlterB_4 = new javax.swing.JRadioButton();
        rtnAlterC_4 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        rtnAlterA_5 = new javax.swing.JRadioButton();
        rtnAlterB_5 = new javax.swing.JRadioButton();
        rtnAlterC_5 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        btnAcep = new javax.swing.JButton();
        btnCanc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 800));

        jLabel1.setText("Examen");

        lbl1.setText("1");

        buttonGroup1.add(rtnAlterA_1);
        rtnAlterA_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnAlterA_1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rtnAlterB_1);

        buttonGroup1.add(rtnAlterC_1);

        jLabel2.setText("PREGUNTA 1:");

        lbl2.setText("2");

        buttonGroup2.add(rtnAlterA_2);
        rtnAlterA_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnAlterA_2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rtnAlterB_2);

        buttonGroup2.add(rtnAlterC_2);

        jLabel5.setText("PREGUNTA 2:");

        lbl3.setText("3");

        buttonGroup3.add(rtnAlterA_3);
        rtnAlterA_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnAlterA_3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(rtnAlterB_3);

        buttonGroup3.add(rtnAlterC_3);

        jLabel7.setText("PREGUNTA 3:");

        lbl4.setText("4");

        buttonGroup4.add(rtnAlterA_4);
        rtnAlterA_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnAlterA_4ActionPerformed(evt);
            }
        });

        buttonGroup4.add(rtnAlterB_4);

        buttonGroup4.add(rtnAlterC_4);

        jLabel9.setText("PREGUNTA 4:");

        lbl5.setText("5");

        buttonGroup5.add(rtnAlterA_5);
        rtnAlterA_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnAlterA_5ActionPerformed(evt);
            }
        });

        buttonGroup5.add(rtnAlterB_5);

        buttonGroup5.add(rtnAlterC_5);

        jLabel11.setText("PREGUNTA 5:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(rtnAlterC_5)
                            .addComponent(rtnAlterA_5)
                            .addComponent(rtnAlterB_5)
                            .addComponent(jLabel9)
                            .addComponent(rtnAlterC_4)
                            .addComponent(rtnAlterA_4)
                            .addComponent(rtnAlterB_4)
                            .addComponent(jLabel7)
                            .addComponent(rtnAlterC_3)
                            .addComponent(rtnAlterA_3)
                            .addComponent(rtnAlterB_3)
                            .addComponent(jLabel5)
                            .addComponent(rtnAlterC_2)
                            .addComponent(rtnAlterA_2)
                            .addComponent(rtnAlterB_2)
                            .addComponent(jLabel2)
                            .addComponent(rtnAlterC_1)
                            .addComponent(rtnAlterA_1)
                            .addComponent(rtnAlterB_1))
                        .addGap(0, 1041, Short.MAX_VALUE))
                    .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rtnAlterA_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterB_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterC_1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rtnAlterA_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterB_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterC_2)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rtnAlterA_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterB_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterC_3)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rtnAlterA_4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterB_4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterC_4)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rtnAlterA_5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterB_5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnAlterC_5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        btnAcep.setText("Aceptar");
        btnAcep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcepActionPerformed(evt);
            }
        });

        btnCanc.setText("Cancelar");
        btnCanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(btnAcep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCanc)
                .addGap(286, 286, 286))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcep)
                    .addComponent(btnCanc))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rtnAlterA_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnAlterA_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtnAlterA_1ActionPerformed

    private void rtnAlterA_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnAlterA_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtnAlterA_2ActionPerformed

    private void rtnAlterA_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnAlterA_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtnAlterA_3ActionPerformed

    private void rtnAlterA_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnAlterA_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtnAlterA_4ActionPerformed

    private void rtnAlterA_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnAlterA_5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtnAlterA_5ActionPerformed

    private void btnCancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancActionPerformed
        this.dispose();
        Exam_pre exa;
        try {
            exa = new Exam_pre(aux_se.getId_person());
            exa.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Exam_pre_5.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }//GEN-LAST:event_btnCancActionPerformed

    private void btnAcepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcepActionPerformed
        try {
            crearExamen();
        } catch (Exception ex) {
            Logger.getLogger(Exam_pre_5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAcepActionPerformed

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
            java.util.logging.Logger.getLogger(Exam_pre_5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exam_pre_5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exam_pre_5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exam_pre_5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Exam_pre_5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcep;
    private javax.swing.JButton btnCanc;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JRadioButton rtnAlterA_1;
    private javax.swing.JRadioButton rtnAlterA_2;
    private javax.swing.JRadioButton rtnAlterA_3;
    private javax.swing.JRadioButton rtnAlterA_4;
    private javax.swing.JRadioButton rtnAlterA_5;
    private javax.swing.JRadioButton rtnAlterB_1;
    private javax.swing.JRadioButton rtnAlterB_2;
    private javax.swing.JRadioButton rtnAlterB_3;
    private javax.swing.JRadioButton rtnAlterB_4;
    private javax.swing.JRadioButton rtnAlterB_5;
    private javax.swing.JRadioButton rtnAlterC_1;
    private javax.swing.JRadioButton rtnAlterC_2;
    private javax.swing.JRadioButton rtnAlterC_3;
    private javax.swing.JRadioButton rtnAlterC_4;
    private javax.swing.JRadioButton rtnAlterC_5;
    // End of variables declaration//GEN-END:variables

    Controler_Exam ex = new Controler_Exam();
    Controler_Section se = new Controler_Section();
    Controler_Alternative al = new Controler_Alternative();

    Controler_Question_Exam qex = new Controler_Question_Exam();
    Controler_Alternative_Exam aex = new Controler_Alternative_Exam();
    
    ArrayList<AlternativeExam> list_aex = new ArrayList();
    ArrayList<QuestionExam> list_qex = new ArrayList();
    ArrayList<QuestionExam> list_qex_a = new ArrayList();
    
    ArrayList<Alternative> list_al = new ArrayList();
    ArrayList<Question> list_qu = new ArrayList();
    Section aux_se;
    Subject aux_su;
    Teacher aux_te;
    Question aux_qu;
    Alternative aux_al;
    QuestionExam aux_qu_ex;
    AlternativeExam aux_al_ex;
    Exam aux_ex;
    
    private void cargarDatos() throws Exception {
    
        aux_se = se.SearchSection(seccion);
        list_qu = ex.PlantillaExam(cantidad, aux_se.getId_subject());
                
        llenarPregunta(list_qu);
        for (Question x : list_qu) {
              
            aux_al = al.SearchAlternative_by_question(x.getId_question());
            list_al.add(aux_al);
            
        }
        llenarAlternativas(list_al);
        
        
        
    }
    
    private void llenarPregunta(ArrayList<Question> x){
        
        lbl1.setText(x.get(0).getQuestion());
        lbl2.setText(x.get(1).getQuestion());
        lbl3.setText(x.get(2).getQuestion());
        lbl4.setText(x.get(3).getQuestion());
        lbl5.setText(x.get(4).getQuestion());
        
        
    }

    private void llenarAlternativas(ArrayList<Alternative> x) {
    
        rtnAlterA_1.setText(x.get(0).getAlternative_A());
        rtnAlterB_1.setText(x.get(0).getAlternative_B());
        rtnAlterC_1.setText(x.get(0).getAlternative_C());
        
        rtnAlterA_2.setText(x.get(1).getAlternative_A());
        rtnAlterB_2.setText(x.get(1).getAlternative_B());
        rtnAlterC_2.setText(x.get(1).getAlternative_C());
        
        rtnAlterA_3.setText(x.get(2).getAlternative_A());
        rtnAlterB_3.setText(x.get(2).getAlternative_B());
        rtnAlterC_3.setText(x.get(2).getAlternative_C());
        
        rtnAlterA_4.setText(x.get(3).getAlternative_A());
        rtnAlterB_4.setText(x.get(3).getAlternative_B());
        rtnAlterC_4.setText(x.get(3).getAlternative_C());
        
        rtnAlterA_5.setText(x.get(4).getAlternative_A());
        rtnAlterB_5.setText(x.get(4).getAlternative_B());
        rtnAlterC_5.setText(x.get(4).getAlternative_C());
        
    }

    private void crearExamen() throws Exception {
    
        aux_ex = new Exam();
        aux_ex.setId_section(seccion);
        ex.CreateExam(aux_ex);
        
        aux_ex = ex.SearchExam_by_sec(seccion);
        
        aux_al_ex = new AlternativeExam();
        int i=1;
        
        for (Question q : list_qu) {
            aux_qu_ex = new QuestionExam();
            aux_qu_ex.setId_exam(aux_ex.getId_exam());
            aux_qu_ex.setN_question(i);
            aux_qu_ex.setQuestion(q.getQuestion());
            i++;
            list_qex.add(aux_qu_ex);
            
        }
        
        for (QuestionExam q : list_qex) {
            
            String msg = qex.CreateQuestionExam(q);
            
        }
        
        for (int k = 0; k<list_qex.size() ;k++) {
            aux_qu_ex = new QuestionExam();
            aux_qu_ex=qex.SearchQuestionExam_Qu(list_qex.get(k).getId_exam(), list_qex.get(k).getN_question());
            list_qex_a.add(aux_qu_ex);
        }
        
        int j=0;
        for (Alternative q : list_al) {
            aux_al_ex = new AlternativeExam();
            aux_al_ex.setAlternative_A(q.getAlternative_A());
            aux_al_ex.setAlternative_B(q.getAlternative_B());
            aux_al_ex.setAlternative_C(q.getAlternative_C());
            aux_qu_ex=list_qex_a.get(j);
            aux_al_ex.setId_question_exam(aux_qu_ex.getId_question_exam());
            list_aex.add(aux_al_ex);
            j++;
            
        }
        
        for (AlternativeExam al : list_aex) {
            String msg =aex.CreateAlternativeExam(al);
        }
        
        
        JOptionPane.showConfirmDialog(null, "EXAMEN CREADO");
        
        
        
        this.dispose();
        
        
    }
    
    
    
}
