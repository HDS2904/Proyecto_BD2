/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import Vista.V_Profesores;
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.btnagregar){
            tea.setCod_teacher(vista.txid.getText());
            per.setFirs_name(vista.txnombre.getText());
            per.setLast_name(vista.txapellido.getText());
            per.setPhone(Integer.parseInt(vista.txtel.getText()));
            per.setDireccion(vista.txdir.getText());
            per.setEmail(vista.txem.getText());
            tea.setPer(per);
            JOptionPane.showMessageDialog(null, Pcrud.Create(tea));
        }
        
        
        if(e.getSource() == vista.btnmodificar){
            per.setFirs_name(vista.txnombre.getText());
            per.setLast_name(vista.txapellido.getText());
            per.setPhone(Integer.parseInt(vista.txtel.getText()));
            per.setDireccion(vista.txdir.getText());
            per.setEmail(vista.txem.getText());
            tea.setPer(per);
            JOptionPane.showMessageDialog(null, Pcrud.Update(tea));
        }
        
        
        if(e.getSource() == vista.btneliminar){
            JOptionPane.showMessageDialog(null, Pcrud.Delete(tea));
        }
        
        if(e.getSource() == vista.btnbus){
            JOptionPane.showMessageDialog(null, Pcrud.SearchId(vista.txid.getText(), tea));
        }
        
        Pcrud.ListAll(vista.tabla);
        Limpiar();
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int id = vista.tabla.rowAtPoint(e.getPoint());
        tea.setCod_teacher((vista.tabla.getValueAt(id, 1)+""));
        vista.txid.setText(vista.tabla.getValueAt(id, 1)+"");
        vista.txnombre.setText(vista.tabla.getValueAt(id,2)+"");
        vista.txapellido.setText(vista.tabla.getValueAt(id,3)+"");
        vista.txtel.setText(vista.tabla.getValueAt(id,4)+"");
        vista.txdir.setText(vista.tabla.getValueAt(id,5)+"");
        vista.txem.setText(vista.tabla.getValueAt(id,6)+"");
    }
    
            
    public void Limpiar(){
        vista.txid.setText("");
        vista.txnombre.setText("");
        vista.txapellido.setText("");
        vista.txtel.setText("");
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
