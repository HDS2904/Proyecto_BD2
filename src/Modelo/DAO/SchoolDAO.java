/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.School;
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
public class SchoolDAO implements ICRUD<School>{

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    School sc;
    
    @Override
    public void Create(School t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SCHOOLS.INSERT_D(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_school());
            ps.setInt(2,t.getId_faculty());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Update(School t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SCHOOLS.UPDATE_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_faculty());
            ps.setString(2,t.getName_school());
            ps.setInt(3,t.getId_faculty());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ACTUALIZAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(School t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SCHOOLS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_school());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ELIMINAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public School Search(int t) throws Exception {
        sc = new School();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_SCHOOLS.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                sc.setId_school(rs.getInt("ID_SCHOOL"));
                sc.setName_school(rs.getString("NAME_SCHOOL"));
                sc.setId_faculty(rs.getInt("ID_FACULTY"));
                
                
                
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
        return sc;
        
    }

    @Override
    public ArrayList<School> ListAll() throws Exception {
    
         ArrayList<School> lista = new ArrayList<School>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_FACULTIES.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                sc = new School();
                sc.setId_school(rs.getInt("ID_SCHOOL"));
                sc.setName_school(rs.getString("NAME_SCHOOL"));
                sc.setId_faculty(rs.getInt("ID_FACULTY"));
                lista.add(sc);
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
