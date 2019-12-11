
package Controladores;

import Modelo.DAO.DirectorDAO;
import Modelo.DAO.PersonDAO;
import Modelo.DAO.SectionDAO;
import Modelo.DAO.StudentDAO;
import Modelo.DAO.SubjectDAO;
import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Director;
import Modelo.Entidades.Person;
import Modelo.Entidades.Section;
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
    private Subject sb_aper = new Subject();
    
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
        
        this.vd.tabla4sec.addMouseListener(this);
        this.vd.btn4bus.addActionListener(this);
        this.vd.btn4ins.addActionListener(this);
        this.vd.btn4mod.addActionListener(this);
        this.vd.btn4del.addActionListener(this);
        this.vd.btn4lim.addActionListener(this);
    }
    
    public void inicio(){
        vd.setTitle("PROFESORES");
        vd.setLocationRelativeTo(null);
        vd.titulo2.setText("BIENVENIDO PROFESOR "+p.getLast_name());
        vd.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
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
        //ACCIONES DE SECCION 
        if(ae.getSource() == vd.btn4ins){
            try {
                insert_section();
                load_subject();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn4bus){
            try {
                search_sub();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar sección: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn4mod){
            System.out.println("pasoooo ups");
            try {
                update_section();
                load_subject();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al editar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn4del){
            try {
                delete_section();
                load_subject();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al borrar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vd.btn4lim){
            limpiar();
        }
        
      
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
        sb_aper = sb;
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
    
    public void limpiar(){
        vd.cb4cur.setSelectedIndex(0);
        vd.tx4nom.setText("");
        vd.tx4cod.setText("");
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
