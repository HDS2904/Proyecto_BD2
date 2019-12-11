/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.SubjectDAO;
import Modelo.Entidades.Subject;
import Vistas.VSubject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author HDS
 */
public class Controler_VSubject implements MouseListener, ActionListener{
    private SubjectDAO sbdao;
    private Subject sb;
    private VSubject vsb;
    
    
    
    
    public Controler_VSubject(Subject sb){
        this.sb = sb;
        sbdao= new SubjectDAO();
        vsb = new VSubject();
        
        this.vsb.btnop1.addActionListener(this);
        this.vsb.btnop2.addActionListener(this);
        this.vsb.btnop3.addActionListener(this);
        
        this.vsb.btnsav1.addActionListener(this);
        this.vsb.btncan1.addActionListener(this);
        
        this.vsb.btnbus2.addActionListener(this);
        this.vsb.btnsav2.addActionListener(this);
        this.vsb.btnupd2.addActionListener(this);
        this.vsb.btndel2.addActionListener(this);
        this.vsb.btnlim2.addActionListener(this);
    }
    
    public Controler_VSubject(){
        
    }
    
    public void inicio(){
        vsb.setTitle("CURSOS");
        vsb.setLocationRelativeTo(null);
        vsb.titulo2.setText("PORTAL DE "+sb.getName_subject());
        vsb.setVisible(true);
        
        vsb.Panel0.setVisible(true);
        vsb.Panel1.setVisible(false);
        vsb.Panel2.setVisible(false);
        vsb.Panel3.setVisible(false);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        
        
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
