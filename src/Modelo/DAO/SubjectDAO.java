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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import oracle.jdbc.OracleTypes;
import principal.Conectar;

/**
 *
 * @author Angel
 */
public class SubjectDAO implements ICRUD<Subject> {
    
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Subject su;
    
    @Override
    public String Create(Subject t) {
        
        
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SUBJECT.INSERT_SU(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_subject());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    @Override
    public String Update(Subject t) {
    
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SUBJECT.UPDATE_SU(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_subject());
            ps.setString(2,t.getName_subject());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ACTUALIZAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    @Override
    public String Delete(Subject t) {
    
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SUBJECT.DELETE_SU(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_subject());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ELIMINAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    
    @Override
    public Subject SearchId(int t) {
    
        Subject su = new Subject();
        cn = Conectar.getConnection();
        String sql = "{call PACK_LIST_SUBJECT.GET_SUBJECT(?,?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.setInt(1, t);
            ca.registerOutParameter(2,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            int c = 0;
            if(rs.next()){
                System.out.println(rs.getString("NAME_SUBJECT"));
                su.setName_subject(rs.getString("NAME_SUBJECT"));
                su.setId_subject(Integer.parseInt(rs.getString("DNI")));
                mensaje = "SE UBICO EL CURSO: "+t+"\n";
            }else
                mensaje = "NO SE PUDO ENCONTRAR LA ENTDAD DE CODIGO: "+t+"\n";
            mensaje = mensaje+ "SE EJECUTO CORRECTAMENTE LA BUSQUEDA";
            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            mensaje = mensaje + "ERROR AL EJECUTAR LA BUSQUEDA \n" + e.getMessage();
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return su;
    }

    @Override
    public ArrayList<Subject> ListAll() {
    
        
        ArrayList<Subject> lista = new ArrayList<Subject>();
        
        
        
         try {
             cn = Conectar.getConnection();
            String sql = "{call PACK_LIST_SUBJECT.GET_SUBJECTS(?)}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                su = new Subject();
                su.setName_subject(rs.getString("NAME_SUBJECT"));
                su.setId_subject(Integer.parseInt(rs.getString("DNI")));
                lista.add(su);
            }            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            mensaje = mensaje + "ERROR AL EJECUTAR LA BUSQUEDA \n" + e.getMessage();
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
