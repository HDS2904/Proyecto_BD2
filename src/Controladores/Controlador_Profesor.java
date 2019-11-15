/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import Vistas.V_Profesores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class Controlador_Profesor implements ActionListener, MouseListener {
    private Teacher tea;
    private Person per;
    private TeacherDAO Pcrud;
    private V_Profesores vista;

    public Controlador_Profesor(Person per, Teacher tea, TeacherDAO Pcrud, V_Profesores vista) {
        this.tea = tea;
        this.per = per;
        this.Pcrud = Pcrud;
        this.vista = vista;
        this.vista.btnagregar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btnmodificar.addActionListener(this);
        this.vista.btnlimpiar.addActionListener(this);
        this.vista.btnbus.addActionListener(this);
        this.vista.tabla.addMouseListener(this);
    }
    
    public void iniciar() {
        vista.setTitle("PROFESORES");
        vista.setLocationRelativeTo(null);
        Pcrud.ListAll(vista.tabla);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == vista.btnagregar){
            tea.setCod_teacher(vista.txid.getText());
            per.setFirs_name(vista.txnombre.getText());
            per.setLast_name(vista.txapellido.getText());
            per.setDni(Integer.parseInt(vista.txdni.getText()));
            per.setPhone(Integer.parseInt(vista.txtel.getText()));
            per.setAddress(vista.txdir.getText());
            per.setEmail(vista.txem.getText());
            tea.setPer(per);
            JOptionPane.showMessageDialog(null, Pcrud.Create(tea));
            Limpiar();
        }
        
        
        if(e.getSource() == vista.btnmodificar){
            per.setFirs_name(vista.txnombre.getText());
            per.setLast_name(vista.txapellido.getText());
            per.setDni(Integer.parseInt(vista.txdni.getText()));
            per.setPhone(Integer.parseInt(vista.txtel.getText()));
            per.setAddress(vista.txdir.getText());
            per.setEmail(vista.txem.getText());
            tea.setPer(per);
            JOptionPane.showMessageDialog(null, Pcrud.Update(tea));
            Limpiar();
        }
        
        
        if(e.getSource() == vista.btneliminar){
            JOptionPane.showMessageDialog(null, Pcrud.Delete(tea));
            Limpiar();
        }
        
        if(e.getSource() == vista.btnbus){
            tea.setCod_teacher(vista.txid.getText());
            JOptionPane.showMessageDialog(null, Pcrud.SearchId(tea));
            if(!(tea.getCod_teacher().equals("fail"))){
                vista.txnombre.setText(tea.getPer().getFirs_name());
                vista.txapellido.setText(tea.getPer().getLast_name());
                vista.txdni.setText(tea.getPer().getDni()+"");
                vista.txtel.setText(tea.getPer().getPhone()+"");
                vista.txdir.setText(tea.getPer().getAddress());
                vista.txem.setText(tea.getPer().getEmail());
                vista.txid.setEditable(false);
                vista.txdni.setEditable(false);
            }
            
        }
        
        if(e.getSource() == vista.btnlimpiar){
            Limpiar();
        }
        
        Pcrud.ListAll(vista.tabla);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        Person per = new Person();
        int id = vista.tabla.rowAtPoint(e.getPoint());
        tea.setCod_teacher(vista.tabla.getValueAt(id,1)+"");
        per.setFirs_name(vista.tabla.getValueAt(id,2)+"");
        per.setLast_name(vista.tabla.getValueAt(id,3)+"");
        per.setDni(Integer.parseInt(vista.tabla.getValueAt(id,4)+""));
        per.setPhone(Integer.parseInt(vista.tabla.getValueAt(id,5)+""));
        per.setAddress(vista.tabla.getValueAt(id,6)+"");
        per.setEmail(vista.tabla.getValueAt(id,7)+"");
        tea.setPer(per);
        vista.txid.setText(tea.getCod_teacher());

    }
    
            
    public void Limpiar(){
        vista.txid.setText("");
        vista.txnombre.setText("");
        vista.txapellido.setText("");
        vista.txdni.setText("");
        vista.txtel.setText("");
        vista.txdir.setText("");
        vista.txem.setText("");
        vista.txid.setEditable(true);
        vista.txdni.setEditable(true);
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
