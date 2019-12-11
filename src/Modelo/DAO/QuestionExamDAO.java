/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.QuestionExam;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import Principal.conexion;

/**
 *
 * @author Angel
 */
public class QuestionExamDAO implements ICRUD<QuestionExam>{

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    QuestionExam qe;
    
    @Override
    public void Create(QuestionExam t) throws Exception{
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTION_EXAMS.INSERT_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1,t.getId_exam());
            ps.setString(2,t.getQuestion());
            ps.setInt(3,t.getN_question());
                       
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
    public void Update(QuestionExam t) throws Exception{
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTION_EXAMS.UPDATE_D(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question_exam());
            ps.setInt(2,t.getId_exam());
            ps.setString(3,t.getQuestion());
            ps.setInt(4,t.getN_question());
                       
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
    public void Delete(QuestionExam t) throws Exception{
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTION_EXAMS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question_exam());
                       
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
    public QuestionExam Search(int t) throws Exception {
        
        qe = new QuestionExam();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_QUESTION_EXAMS.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                qe.setId_question_exam(rs.getInt("ID_QUESTION_EXAM"));
                qe.setId_exam(rs.getInt("ID_EXAM"));
                qe.setQuestion(rs.getString("QUESTION"));
                qe.setN_question(rs.getInt("N_QUESTION"));
                
                
                
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
        return qe;
        
    }

    @Override
    public ArrayList<QuestionExam> ListAll() throws Exception {
    
        ArrayList<QuestionExam> lista = new ArrayList<QuestionExam>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_QUESTION_EXAMS.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                qe = new QuestionExam();
                qe.setId_question_exam(rs.getInt("ID_QUESTION_EXAM"));
                qe.setId_exam(rs.getInt("ID_EXAM"));
                qe.setQuestion(rs.getString("QUESTION"));
                qe.setN_question(rs.getInt("N_QUESTION"));
                lista.add(qe);
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
