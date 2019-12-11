/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;

import Modelo.Entidades.Faculty;
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
public class FacultyDAO implements ICRUD<Faculty>{

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Faculty fa;
    
    @Override
    public void Create(Faculty t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_FACULTIES.INSERT_D(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_faculty());
            ps.setInt(2,t.getId_person());
                       
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
    public void Update(Faculty t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_FACULTIES.UPDATE_D(?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_faculty());
            ps.setString(2,t.getName_faculty());
            ps.setInt(3,t.getId_person());
                       
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
    public void Delete(Faculty t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_FACULTIES.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_faculty());
                       
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
    public Faculty Search(int t) throws Exception {
        fa = new Faculty();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_FACULTIES.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                fa.setId_faculty(rs.getInt("ID_FACULTY"));
                fa.setName_faculty(rs.getString("NAME_FACULTY"));
                fa.setId_person(rs.getInt("ID_PERSON"));
                
                
                
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
        return fa;
        
    }

    @Override
    public ArrayList<Faculty> ListAll() throws Exception {
    
        ArrayList<Faculty> lista = new ArrayList<Faculty>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_FACULTIES.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                fa = new Faculty();
                fa.setId_faculty(rs.getInt("ID_FACULTY"));
                fa.setName_faculty(rs.getString("NAME_FACULTY"));
                fa.setId_person(rs.getInt("ID_PERSON"));
                lista.add(fa);
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
