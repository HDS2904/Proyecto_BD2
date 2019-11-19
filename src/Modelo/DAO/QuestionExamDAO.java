/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.QuestionExam;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import principal.Conectar;

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
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_QUESTION_EXAM.INSERT_QEX(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getQuestion());
            ps.setInt(2,t.getN_question());
            ps.setInt(3,t.getId_exam());
                       
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
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_QUESTION_EXAM.UPDATE_QEX(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question_exam());
            ps.setString(2,t.getQuestion());
            ps.setInt(3,t.getN_question());
            ps.setInt(4,t.getId_exam());
                       
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
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_QUESTION_EXAM.DELETE_QEX(?)}";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<QuestionExam> ListAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
