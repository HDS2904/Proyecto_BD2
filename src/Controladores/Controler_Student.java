/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.PersonDAO;
import Modelo.DAO.SectionDAO;
import Modelo.DAO.StudentDAO;
import Modelo.DAO.SubjectDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Section;
import Modelo.Entidades.Student;
import Modelo.Entidades.Subject;

import Vistas.VStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SERGIO
 */
public class Controler_Student implements MouseListener, ActionListener{
    private PersonDAO pdao;
    private StudentDAO tdao;
    private VStudent vst;
    private Person p;
    private Student t;
    private Subject subj;
    private SubjectDAO sbjdao;
    private SectionDAO stcdao;
    
    public Controler_Student(Student t, Person p){
        this.t = t;
        this.p = p;
        pdao = new PersonDAO();
        tdao = new StudentDAO();
        sbjdao= new SubjectDAO();
        stcdao = new SectionDAO();
        vst = new VStudent();
        
        this.vst.btn1.addActionListener(this);
        this.vst.btn2.addActionListener(this);
        this.vst.btn3.addActionListener(this);
        
        this.vst.btn1sav.addActionListener(this);
        this.vst.btn1can.addActionListener(this);
        
        //this.vst.tabla2sec.addMouseListener(this);
        this.vst.btn2bus.addActionListener(this);
        this.vst.btn2mat.addActionListener(this);

        //this.vst.btn2lim.addActionListener(this);
        //this.vst.btn2adm.addActionListener(this);
    }
    
    public Controler_Student(){
        
    }
    
    public void inicio(){
        vst.setTitle("ESTUDIANTES");
        vst.setLocationRelativeTo(null);
        vst.titulo2.setText("BIENVENIDO ESTUDIANTE "+p.getLast_name());
        vst.setVisible(true);
        vst.Panel0.setVisible(true);
        vst.Panel1.setVisible(false);
        vst.Panel2.setVisible(false);
        vst.Panel4.setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //ACCIONES DE BOTONES PRINCIPALES
        if(ae.getSource() == vst.btn1){
            datos();
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(true);
            vst.Panel2.setVisible(false);
            vst.Panel4.setVisible(false);
        }
        
        if(ae.getSource() == vst.btn2){
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(false);
            vst.Panel2.setVisible(true);
            vst.Panel4.setVisible(false);
        }
        
        if(ae.getSource() == vst.btn3){
            vst.Panel4.setVisible(true);
            try {
                load_subject();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
            
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(false);
            vst.Panel2.setVisible(false);
            vst.Panel4.setVisible(true);
        }
        
        //ACCIONES PANEL DE EDITAR PERFIL
        if(ae.getSource() == vst.btn1sav){
            try {
                edit_student();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vst.btn1can){
            datos();
        }
        
        //ACCIONES DE SECCION 
        if(ae.getSource() == vst.btn2bus){
            try {
                search_sub();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar sección: "+ex);
            }
        }
        
        if(ae.getSource() == vst.btn2mat){
            limpiar();
        }
        
        
        
    }
    
    public void datos(){
        vst.tx1cod.setText(t.getCode_student());
        vst.tx1nom.setText(p.getFirs_name());
        vst.tx1ape.setText(p.getLast_name());
        vst.tx1dni.setText(p.getDni()+"");
        vst.tx1tel.setText(p.getPhone()+"");
        vst.tx1dir.setText(p.getAddress());
        vst.tx1ema.setText(p.getEmail());
    }
    
    public void edit_student()throws Exception{
        t.setCode_student(vst.tx1cod.getText());
        p.setFirs_name(vst.tx1nom.getText());
        p.setLast_name(vst.tx1ape.getText());
        p.setDni(Integer.parseInt(vst.tx1dni.getText()));
        p.setPhone(Integer.parseInt(vst.tx1tel.getText()));
        p.setAddress(vst.tx1dir.getText());
        p.setEmail(vst.tx1ema.getText());
        pdao.Update(p);
        tdao.Update(t);
    }
    
    public void load_subject()throws Exception{
        DefaultTableModel ad = new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"ID_Section","Name_Section","Subject"});
        ArrayList<Section> st = stcdao.ListAll();
        Subject sb;
        for (Section st1 : st) {
            if(st1.getId_person() == p.getId_person()){
                System.out.println(st1.getSection_group());
                System.out.println(st1.getId_subject());
                sb = sbjdao.Search(st1.getId_subject());
                ad.addRow(new Object[]{st1.getId_section(),st1.getSection_group(),sb.getName_subject()});
            }
        }
       
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(ad);
        vst.tabla2estudi.setRowSorter(order);
        vst.tabla2estudi.setModel(ad);
    }
   
    
    public void search_sub()throws Exception{
        Section st = stcdao.Search(Integer.parseInt(vst.tx2cod.getText()));
        subj = sbjdao.Search(st.getId_subject());
        vst.tx2sec.setText(st.getSection_group());
    }
    
    public void limpiar(){
        vst.tx2sec.setText("");
        vst.tx2cod.setText("");
    }
    
    
    
    
    
    

    @Override
    public void mouseClicked(MouseEvent me) {
        int id = vst.tabla2estudi.rowAtPoint(me.getPoint());
        vst.tx2cod.setText(vst.tabla2estudi.getValueAt(id, 0)+"");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    /*@Override
    public void mouseClicked(MouseEvent me) {
        
    }*/

    
}
