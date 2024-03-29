/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Subject;
import java.sql.CallableStatement;
import java.sql.Connection;
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
public class SubjectDAO implements ICRUD<Subject> {
    
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Subject t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SUBJECTS.INSERT_D(?)}";
        try {
            call = con.prepareCall(sql);
            call.setString(1,t.getName_subject());
            call.execute();
            call.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Update(Subject t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SUBJECTS.UPDATE_D(?,?)}";
        try {
            call = con.prepareCall(sql);
            call.setInt(1,t.getId_subject());
            call.setString(2,t.getName_subject());
            call.execute();
            call.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(Subject t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SUBJECTS.DELETE_D(?)}";
        try {
            call = con.prepareCall(sql);
            call.setInt(1,t.getId_subject());
                       
            call.execute();
            call.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    @Override
    public Subject Search(int t) throws Exception{
        con = conexion.getConnection();
        call = null;
        Subject s = new Subject();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_SUBJECTS.SEARCH_D(?)}";
        
        
         try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.execute();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                s.setId_subject(rs.getInt("ID_SUBJECT"));
                s.setName_subject(rs.getString("NAME_SUBJECT"));
            }
            call.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return s;
    }

    @Override
    public ArrayList<Subject> ListAll() throws Exception{
        ArrayList<Subject> lista = new ArrayList<>();
        con = conexion.getConnection();
        call= null;
        Subject s;
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_SUBJECTS.LIST_D}";
         try {
            call = con.prepareCall(sql);
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.execute();
            rs = (ResultSet)call.getObject(1);
            while(rs.next()){
                
                s = new Subject();
                s.setName_subject(rs.getString("NAME_SUBJECT"));
                s.setId_subject(rs.getInt("ID_SUBJECT"));
                lista.add(s);
            }            
            call.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return lista;
    }
    
}
