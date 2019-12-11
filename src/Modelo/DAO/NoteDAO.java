/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Note;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Angel
 */
public class NoteDAO implements ICRUD<Note>{
    
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Note no;
    
    @Override
    public void Create(Note t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_NOTES.INSERT_D(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section_student());
            ps.setInt(2,t.getId_note());
            
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Update(Note t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_NOTES.UPDATE_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_note());
            ps.setInt(2,t.getId_section_student());
            ps.setInt(3,t.getId_note());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ACTUALIZAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(Note t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_NOTES.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_note());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ELIMINAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public Note Search(int t) throws Exception {
        
        no = new Note();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_NOTES.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                no.setNote(rs.getInt("ID_NOTE"));
                no.setId_section_student(rs.getInt("ID_SECTION_STUDENT"));
                no.setNote(rs.getInt("NOTE"));
                
                
                
                
            }
            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return no;
        
    }

    @Override
    public ArrayList<Note> ListAll() throws Exception {
    
        ArrayList<Note> lista = new ArrayList<Note>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_NOTES.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                no = new Note();
                no.setNote(rs.getInt("ID_NOTE"));
                no.setId_section_student(rs.getInt("ID_SECTION_STUDENT"));
                no.setNote(rs.getInt("NOTE"));
                lista.add(no);
            }            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return lista;
        
    }

    
}
