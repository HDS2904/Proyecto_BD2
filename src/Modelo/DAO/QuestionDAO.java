/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Question;
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
public class QuestionDAO implements ICRUD<Question>{

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    
    Question qu;
    
    @Override
    public void Create(Question t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTIONS.INSERT_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_theme());
            ps.setString(2, t.getQuestion());
            ps.setInt(3, t.getScore());
                       
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
    public void Update(Question t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTIONS.UPDATE_D(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question());
            ps.setInt(2,t.getId_theme());
            ps.setString(3, t.getQuestion());
            ps.setInt(4, t.getScore());
                       
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
    public void Delete(Question t) throws Exception {
    
         cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_QUESTIONS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question());
                       
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
    public Question Search(int t) throws Exception {
        qu = new Question();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_QUESTIONS.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                qu.setId_question(rs.getInt("ID_QUESTION"));
                qu.setId_theme(rs.getInt("ID_THEME"));
                qu.setQuestion(rs.getString("ID_SUBJECT"));
                qu.setScore(rs.getInt("SCORE"));
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
        return qu;
        
    }

    @Override
    public ArrayList<Question> ListAll() throws Exception {
    
        ArrayList<Question> lista = new ArrayList<Question>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_QUESTIONS.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                qu = new Question();
                qu.setId_question(rs.getInt("ID_QUESTION"));
                qu.setId_theme(rs.getInt("ID_THEME"));
                qu.setQuestion(rs.getString("QUESTION"));
                qu.setScore(rs.getInt("SCORE"));
                lista.add(qu);
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

    public ArrayList<Question> Search_by_subject(int t) throws Exception {
        ArrayList<Question> lista = new ArrayList<Question>();
        qu = new Question();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_QUESTIONS.SEARCH_QU_SUB(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                qu = new Question();
                qu.setId_question(rs.getInt("ID_QUESTION"));
                qu.setId_theme(rs.getInt("ID_THEME"));
                qu.setQuestion(rs.getString("QUESTION"));
                qu.setScore(rs.getInt("SCORE"));
                lista.add(qu);
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
