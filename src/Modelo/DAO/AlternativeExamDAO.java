/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;

import Modelo.Entidades.AlternativeExam;
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


/**
 *
 * @author Angel
 */
public class AlternativeExamDAO implements ICRUD<AlternativeExam> {

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    AlternativeExam al;
    
    @Override
    public void Create(AlternativeExam t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVE_EXAMS.INSERT_D(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question_exam());
            ps.setString(2, t.getAlternative_A());
            ps.setString(3, t.getAlternative_B());
            ps.setString(4, t.getAlternative_C());
            
                       
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
    public void Update(AlternativeExam t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVE_EXAMS.UPDATE_D(?,?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_alternative_exam());
            ps.setInt(2,t.getId_question_exam());
            ps.setString(3, t.getAlternative_A());
            ps.setString(4, t.getAlternative_B());
            ps.setString(5, t.getAlternative_C());
                       
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
    public void Delete(AlternativeExam t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVE_EXAMS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_alternative_exam());
                       
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
    public AlternativeExam Search(int t) throws Exception {
        al = new AlternativeExam();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_ALTERNATIVE_EXAMS.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                al.setId_alternative_exam(rs.getInt("ID_ALTERNATIVE"));
                al.setId_question_exam(rs.getInt("ID_QUESTION"));
                al.setAlternative_A(rs.getString("ALTERNATIVE_A"));
                al.setAlternative_B(rs.getString("ALTERNATIVE_B"));
                al.setAlternative_C(rs.getString("ALTERNATIVE_C"));
                
                
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
        return al;
        
    }

    @Override
    public ArrayList<AlternativeExam> ListAll() throws Exception {
    
        ArrayList<AlternativeExam> lista = new ArrayList<AlternativeExam>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_ALTERNATIVE_EXAMS.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                al = new AlternativeExam();
                al.setId_alternative_exam(rs.getInt("ID_ALTERNATIVE"));
                al.setId_question_exam(rs.getInt("ID_QUESTION"));
                al.setAlternative_A(rs.getString("ALTERNATIVE_A"));
                al.setAlternative_B(rs.getString("ALTERNATIVE_B"));
                al.setAlternative_C(rs.getString("ALTERNATIVE_C"));
                
                lista.add(al);
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
