
package Controladores;

import Modelo.DAO.DirectorDAO;
import Modelo.DAO.FacultyDAO;
import Modelo.DAO.PersonDAO;
import Modelo.DAO.SchoolDAO;
import Modelo.DAO.SectionDAO;
import Modelo.DAO.StudentDAO;
import Modelo.DAO.SubjectDAO;
import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Director;
import Modelo.Entidades.Faculty;
import Modelo.Entidades.Person;
import Modelo.Entidades.School;
import Modelo.Entidades.Section;
import Modelo.Entidades.Student;
import Modelo.Entidades.Subject;
import Vistas.VDirector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Controler_VDirector implements MouseListener, ActionListener{
    private PersonDAO pdao;
    private DirectorDAO ddao;
    private StudentDAO sdao;
    private TeacherDAO tdao;
    private SubjectDAO sbdao;
    private SectionDAO stdao;
    private Person p;
    private Director d;
    private VDirector vd;
    
    public Controler_VDirector(Director d, Person p){
        this.d = d;
        this.p = p;
        
        vd = new VDirector();
        pdao = new PersonDAO();
        ddao = new DirectorDAO();
        sdao = new StudentDAO();
        tdao = new TeacherDAO();
        sbdao= new SubjectDAO();
        stdao = new SectionDAO();
        
        this.vd.btn1.addActionListener(this);
        this.vd.btn2.addActionListener(this);
        this.vd.btn3.addActionListener(this);
        this.vd.btn4.addActionListener(this);
        this.vd.btn5.addActionListener(this);
        
        this.vd.btn1sav.addActionListener(this);
        this.vd.btn1can.addActionListener(this);
        
        this.vd.tabla2est.addMouseListener(this);
        this.vd.btn2bus.addActionListener(this);
        this.vd.btn2ins.addActionListener(this);
        this.vd.btn2mod.addActionListener(this);
        this.vd.btn2del.addActionListener(this);
        this.vd.btn2lim.addActionListener(this);
        this.vd.cb2fac.addActionListener(this);
        this.vd.cb2esc.addActionListener(this);
        
    }
    
    public Controler_VDirector(){
        
    }
    
    public void inicio(){
        vd.setTitle("PROFESORES");
        vd.setLocationRelativeTo(null);
        vd.titulo2.setText("BIENVENIDO PROFESOR "+p.getLast_name());
        vd.setVisible(true);
        
        vd.Panel0.setVisible(true);
        vd.Panel1.setVisible(false);
        vd.Panel2.setVisible(false);
        vd.Panel3.setVisible(false);
        vd.Panel4.setVisible(false);
        vd.Panel5.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Interaccion de ventana
        if(ae.getSource() == vd.btn1){
            datos();
            vd.Panel0.setVisible(false);
            vd.Panel1.setVisible(true);
            vd.Panel2.setVisible(false);
            vd.Panel3.setVisible(false);
            vd.Panel4.setVisible(false);
            vd.Panel5.setVisible(false);
        }
        
        if(ae.getSource() == vd.btn2){
            try {
                list_student();
                list_school(1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al Cargar lista de alumnos: "+ex);
            }
            vd.Panel0.setVisible(false);
            vd.Panel1.setVisible(false);
            vd.Panel2.setVisible(true);
            vd.Panel3.setVisible(false);
            vd.Panel4.setVisible(false);
            vd.Panel5.setVisible(false);
        }
        
        if(ae.getSource() == vd.btn3){
            vd.Panel0.setVisible(false);
            vd.Panel1.setVisible(true);
            vd.Panel2.setVisible(false);
            vd.Panel3.setVisible(true);
            vd.Panel4.setVisible(false);
            vd.Panel5.setVisible(false);
        }
        
        if(ae.getSource() == vd.btn4){
            vd.Panel0.setVisible(false);
            vd.Panel1.setVisible(false);
            vd.Panel2.setVisible(false);
            vd.Panel3.setVisible(false);
            vd.Panel4.setVisible(true);
            vd.Panel5.setVisible(false);
        }
        
        if(ae.getSource() == vd.btn5){
            vd.Panel0.setVisible(false);
            vd.Panel1.setVisible(false);
            vd.Panel2.setVisible(false);
            vd.Panel3.setVisible(false);
            vd.Panel4.setVisible(false);
            vd.Panel5.setVisible(true);
        }
        
        //ACCIONES PANEL DE EDITAR PERFIL
        if(ae.getSource() == vd.btn1sav){
            try {
                edit_director();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn1can){
            datos();
        }
        
        //ACCIONES DE STUDENT
        if(ae.getSource() == vd.btn2bus){
            try {
                mant_student(4);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar sección: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn2ins){
            try {
                mant_student(1);
                list_student();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn2mod){
            try {
                mant_student(2);
                list_student();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al editar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn2del){
            try {
                mant_student(3);
                list_student();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al borrar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.cb2fac){
            try {
                list_school(2);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al desplegar school: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn2lim){
            limpiar(1);
        }
        
        //ACCIONES DE TEACHER
        
      
    }
    
    public void datos(){
        vd.tx1cod.setText(d.getCode_director());
        vd.tx1nom.setText(p.getFirs_name());
        vd.tx1ape.setText(p.getLast_name());
        vd.tx1dni.setText(p.getDni()+"");
        vd.tx1tel.setText(p.getPhone()+"");
        vd.tx1dir.setText(p.getAddress());
        vd.tx1ema.setText(p.getEmail());
    }
    
    public void edit_director()throws Exception{
        d.setCode_director(vd.tx1cod.getText());
        p.setFirs_name(vd.tx1nom.getText());
        p.setLast_name(vd.tx1ape.getText());
        p.setDni(Integer.parseInt(vd.tx1dni.getText()));
        p.setPhone(Integer.parseInt(vd.tx1tel.getText()));
        p.setAddress(vd.tx1dir.getText());
        p.setEmail(vd.tx1ema.getText());
        pdao.Update(p);
        ddao.Update(d);
    }
    
    public void list_student()throws Exception{
        DefaultTableModel ad = new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"ID","Código","Nombres","Apellidos","DNI","Telefono","Dirección","Email"});
        ArrayList<Student> s = sdao.ListAll();
        Person p1;
        for (Student s1 : s) {
            p1 = pdao.Search(s1.getId_person());
            ad.addRow(new Object[]{s1.getId_person(),s1.getCode_student(),p1.getFirs_name(),p1.getLast_name(),
                p1.getDni(),p1.getPhone(),p1.getAddress(),p1.getEmail()});
        }
       
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(ad);
        vd.tabla2est.setRowSorter(order);
        vd.tabla2est.setModel(ad);
    }
    
    public void list_school(int a)throws Exception{
        
        if(a==1){
            FacultyDAO fdao = new FacultyDAO();
            vd.cb2fac.removeAllItems();
            vd.cb2fac.addItem("Seleccione");
            ArrayList<Faculty> fs = fdao.ListAll();
            for (Faculty f1 : fs) {
                vd.cb2fac.addItem(f1.getName_faculty());
            }
        }
        if(vd.cb2fac.getSelectedIndex() != 0 ){
            SchoolDAO scdao = new SchoolDAO();
            vd.cb2esc.removeAllItems();
            vd.cb2esc.addItem("Seleccione");
            ArrayList<School> sc = scdao.ListAll();
            for (School sc1 : sc) {
                if(sc1.getId_faculty() == vd.cb2fac.getSelectedIndex())
                    vd.cb2esc.addItem(sc1.getId_school()+" ."+ sc1.getName_school());
            }
            
        }
        
        
    }
    
    public void mant_student(int op)throws Exception{
        Person p1 = new Person();
        Student s1 = new Student();
        int ci;
        switch(op){
            case 1: //Insertar
                    s1.setCode_student(vd.tx2cod.getText());
                    System.out.println(s1.getCode_student());
                    ci = Integer.parseInt((vd.cb2esc.getSelectedItem()+"").substring(0,2).trim());
                    
                    s1.setId_school(ci);
                    System.out.println(s1.getId_school());
                    p1.setFirs_name(vd.tx2nom.getText());
                    p1.setLast_name(vd.tx2ape.getText());
                    p1.setDni(Integer.parseInt(vd.tx2dni.getText()));
                    p1.setPhone(Integer.parseInt(vd.tx2tel.getText()));
                    p1.setAddress(vd.tx2dir.getText());
                    p1.setEmail(vd.tx2ema.getText());
                    pdao.Create(p1);
                    p1 = pdao.Search(Integer.parseInt(vd.tx2dni.getText()));
                    s1.setId_person(p1.getId_person());
                    System.out.println(s1.getId_person());
                    sdao.Create(s1);
                    break;
            case 2: //Modificar
                    s1.setId_person(Integer.parseInt(vd.tx2id.getText()));
                    s1.setCode_student(vd.tx2cod.getText());
                    ci = Integer.parseInt((vd.cb2esc.getSelectedItem()+"").substring(0,2).trim());
                    s1.setId_school(ci);
                    p1.setId_person(Integer.parseInt(vd.tx2id.getText()));
                    p1.setFirs_name(vd.tx2nom.getText());
                    p1.setLast_name(vd.tx2ape.getText());
                    p1.setDni(Integer.parseInt(vd.tx2dni.getText()));
                    p1.setPhone(Integer.parseInt(vd.tx2tel.getText()));
                    p1.setAddress(vd.tx2dir.getText());
                    p1.setEmail(vd.tx2ema.getText());
                    pdao.Update(p1);
                    sdao.Update(s1);
                    break;
            case 3: //Eliminar
                    p1.setId_person(Integer.parseInt(vd.tx2id.getText()));
                    pdao.Delete(p1);
                    break;
            case 4: //Buscar
                    vd.cb2fac.setSelectedIndex(1); //OJO SOLO ESTO ES POR DEFECTO MOMENTANEMAENTE
                    p1 = pdao.Search(Integer.parseInt(vd.tx2id.getText()));
                    s1 = sdao.Search(Integer.parseInt(vd.tx2id.getText()));
                    vd.tx2id.setText(s1.getId_person()+"");
                    vd.tx2cod.setText(s1.getCode_student());
                    vd.tx2nom.setText(p1.getFirs_name());
                    vd.tx2ape.setText(p1.getLast_name());
                    vd.tx2dni.setText(p1.getDni()+"");
                    vd.cb2esc.setSelectedIndex(s1.getId_school());
                    vd.tx2tel.setText(p1.getPhone()+"");
                    vd.tx2dir.setText(p1.getAddress());
                    vd.tx2ema.setText(p1.getEmail());
                    break;
        }
        
    }
    
    
    
    
    
    
    
    
    public void load_subject()throws Exception{
        DefaultTableModel ad = new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"ID_Section","Name_Section","Subject"});
        ArrayList<Section> st = stdao.ListAll();
        Subject sb;
        for (Section st1 : st) {
            if(st1.getId_person() == p.getId_person()){
                sb = sbdao.Search(st1.getId_subject());
                ad.addRow(new Object[]{st1.getId_section(),st1.getSection_group(),sb.getName_subject()});
            }
        }
       
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(ad);
        vd.tabla4sec.setRowSorter(order);
        vd.tabla4sec.setModel(ad);
    }
    
    public void list_subject()throws Exception{
        vd.cb4cur.removeAllItems();
        vd.cb4cur.addItem("Seleccione");
        ArrayList<Subject> sba = sbdao.ListAll();
        for (Subject sb1 : sba) {
            vd.cb4cur.addItem(sb1.getName_subject());
        }
    }
   
    public void insert_section()throws Exception{
        Section st = new Section();
        st.setId_person(p.getId_person());
        st.setId_subject(vd.cb4cur.getSelectedIndex());
        st.setId_exam(0);
        st.setSection_group(vd.tx4nom.getText());
        stdao.Create(st);
    }
    
    public void search_sub()throws Exception{
        Section st = stdao.Search(Integer.parseInt(vd.tx4cod.getText()));
        Subject sb = sbdao.Search(st.getId_subject());
        vd.tx4nom.setText(st.getSection_group());
        vd.cb4cur.setSelectedIndex(sb.getId_subject());
    }
    
    public void update_section()throws Exception{
        Section st = new Section();
        st.setId_section(Integer.parseInt(vd.tx4cod.getText()));
        st.setId_person(p.getId_person());
        st.setId_subject(vd.cb4cur.getSelectedIndex());
        st.setId_exam(0);
        st.setSection_group(vd.tx4nom.getText());
        stdao.Update(st);
    }
    
    public void delete_section()throws Exception{
        Section st = new Section();
        st.setId_section(Integer.parseInt(vd.tx4cod.getText()));
        stdao.Delete(st);
    }
    
    public void limpiar(int op){
        switch(op){
            case 1: //limpiar panel estudiante
                    vd.tx2id.setText("");
                    vd.tx2cod.setText("");
                    vd.tx2nom.setText("");
                    vd.tx2ape.setText("");
                    vd.tx2dni.setText("");
                    vd.tx2tel.setText("");
                    vd.tx2dir.setText("");
                    vd.tx2ema.setText("");
                    break;
            case 2: //limpiar panel Profesores
                    break;
            case 3: //Limpiar panel curso
                    break;
            case 4: //Limpiar panel seccion
                    vd.cb4cur.setSelectedIndex(0);
                    vd.tx4nom.setText("");
                    vd.tx4cod.setText("");
                    break;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
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

    
    
    
}
