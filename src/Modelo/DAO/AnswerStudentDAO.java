/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.AnswerStundent;
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
public class AnswerStudentDAO implements ICRUD<AnswerStundent>{

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    AnswerStundent aes;
    
    @Override
    public void Create(AnswerStundent t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ANSWERS_STUDENTS.INSERT_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_sectoin_student());
            ps.setString(2, t.getAnswer());
            ps.setInt(3, t.getN_question());
            
            
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Update(AnswerStundent t) throws Exception {
    
         cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ANSWERS_STUDENTS.UPDATE_D(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_answer_student());
            ps.setInt(2,t.getId_sectoin_student());
            ps.setString(3, t.getAnswer());
            ps.setInt(4, t.getN_question());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(AnswerStundent t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ANSWERS_STUDENTS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_answer_student());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public AnswerStundent Search(int t) throws Exception {
        aes = new AnswerStundent();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_ANSWERS_STUDENTS.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                aes.setId_answer_student(rs.getInt("ID_ANSWER_STUDENT"));
                aes.setId_sectoin_student(rs.getInt("ID_SECTION_STUDENT"));
                aes.setAnswer(rs.getString("ANSWER"));
                aes.setN_question(rs.getInt("ANSWER"));
                
                
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
        return aes;
        
    }

    @Override
    public ArrayList<AnswerStundent> ListAll() throws Exception {
    
        ArrayList<AnswerStundent> lista = new ArrayList<AnswerStundent>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_ANSWERS_STUDENTS.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                aes = new AnswerStundent();
                aes.setId_answer_student(rs.getInt("ID_ANSWER_STUDENT"));
                aes.setId_sectoin_student(rs.getInt("ID_SECTION_STUDENT"));
                aes.setAnswer(rs.getString("ANSWER"));
                aes.setN_question(rs.getInt("ANSWER"));
                
                lista.add(aes);
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
