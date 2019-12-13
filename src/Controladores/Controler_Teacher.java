
package Controladores;

import Modelo.DAO.PersonDAO;
import Modelo.DAO.SectionDAO;
import Modelo.DAO.SubjectDAO;
import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Section;
import Modelo.Entidades.Subject;
import Modelo.Entidades.Teacher;
import Vistas.VSTeacher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Controler_Teacher implements MouseListener, ActionListener{
    private PersonDAO pdao;
    private TeacherDAO tdao;
    private VSTeacher vst;
    private Person p;
    private Teacher t;
    private Subject sb_aper;
    private SubjectDAO sbdao;
    private SectionDAO stdao;

    public Controler_Teacher() {
        this.tdao = new TeacherDAO();
    }
    
    
    
    public Controler_Teacher(Teacher t, Person p){
        this.t = t;
        this.p = p;
        pdao = new PersonDAO();
        tdao = new TeacherDAO();
        sbdao= new SubjectDAO();
        stdao = new SectionDAO();
        vst = new VSTeacher();
        
        this.vst.btn1.addActionListener(this);
        this.vst.btn3.addActionListener(this);
        this.vst.btn2.addActionListener(this);
        
        this.vst.btngcam.addActionListener(this);
        this.vst.btncand.addActionListener(this);
        
        this.vst.tabla2sec.addMouseListener(this);
        this.vst.btn2bus.addActionListener(this);
        this.vst.btn2lim.addActionListener(this);
        this.vst.btn2adm.addActionListener(this);
    }
    
    
    
    public void inicio(){
        vst.setTitle("PROFESORES");
        vst.setLocationRelativeTo(null);
        vst.titulo2.setText("BIENVENIDO PROFESOR "+p.getLast_name());
        vst.setVisible(true);
        vst.Panel0.setVisible(true);
        vst.Panel1.setVisible(false);
        vst.Panel2.setVisible(false);
        vst.Panel3.setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //ACCIONES DE BOTONES PRINCIPALES
        if(ae.getSource() == vst.btn1){
            datos();
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(true);
            vst.Panel2.setVisible(false);
            vst.Panel3.setVisible(false);
        }
        
        if(ae.getSource() == vst.btn3){
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(false);
            vst.Panel2.setVisible(true);
            vst.Panel3.setVisible(false);
        }
        
        if(ae.getSource() == vst.btn2){
            vst.Panel3.setVisible(true);
            try {
                load_subject();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
            
            vst.Panel0.setVisible(false);
            vst.Panel1.setVisible(false);
            vst.Panel2.setVisible(false);
            vst.Panel3.setVisible(true);
        }
        
        //ACCIONES PANEL DE EDITAR PERFIL
        if(ae.getSource() == vst.btngcam){
            try {
                edit_teacher();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar datos: "+ex);
            }
        }
        
        if(ae.getSource() == vst.btncand){
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
        
        if(ae.getSource() == vst.btn2lim){
            limpiar();
        }
        
        if(ae.getSource() == vst.btn2adm){
            Controler_VSubject su = new Controler_VSubject(sb_aper);
            vst.setVisible(false);
            su.inicio();
        }
        
    }
    
    public void datos(){
        vst.txcodp.setText(t.getCode_teacher());
        vst.txnomp.setText(p.getFirs_name());
        vst.txapep.setText(p.getLast_name());
        vst.txdni.setText(p.getDni()+"");
        vst.txtel.setText(p.getPhone()+"");
        vst.txdir.setText(p.getAddress());
        vst.txema.setText(p.getEmail());
    }
    
    public void edit_teacher()throws Exception{
        t.setCode_teacher(vst.txcodp.getText());
        p.setFirs_name(vst.txnomp.getText());
        p.setLast_name(vst.txapep.getText());
        p.setDni(Integer.parseInt(vst.txdni.getText()));
        p.setPhone(Integer.parseInt(vst.txtel.getText()));
        p.setAddress(vst.txdir.getText());
        p.setEmail(vst.txema.getText());
        pdao.Update(p);
        tdao.Update(t);
    }
    
    public void load_subject()throws Exception{
        DefaultTableModel ad = new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"ID_Section","Name_Section","Subject"});
        ArrayList<Section> st = stdao.ListAll();
        Subject sb;
        for (Section st1 : st) {
            if(st1.getId_person() == p.getId_person()){
                System.out.println(st1.getSection_group());
                System.out.println(st1.getId_subject());
                sb = sbdao.Search(st1.getId_subject());
                ad.addRow(new Object[]{st1.getId_section(),st1.getSection_group(),sb.getName_subject()});
            }
        }
       
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(ad);
        vst.tabla2sec.setRowSorter(order);
        vst.tabla2sec.setModel(ad);
    }
   
    
    public void search_sub()throws Exception{
        Section st = stdao.Search(Integer.parseInt(vst.tx2id.getText()));
        sb_aper = sbdao.Search(st.getId_subject());
        vst.tx2nom.setText(st.getSection_group());
    }
    
    public void limpiar(){
        vst.tx2nom.setText("");
        vst.tx2id.setText("");
    }
    
    public ArrayList<Subject> search_sub(int t) throws Exception{
        
        ArrayList<Subject> lista = new ArrayList<>();
        
        lista = tdao.Search_Sub(t);
        
        return lista;
    }
    
    
    
    

    @Override
    public void mouseClicked(MouseEvent me) {
        int id = vst.tabla2sec.rowAtPoint(me.getPoint());
        vst.tx2id.setText(vst.tabla2sec.getValueAt(id, 0)+"");
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
