/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.SectionStudent;
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
public class SectionStudentDAO implements ICRUD<SectionStudent>{
    
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    SectionStudent std;

    @Override
    public void Create(SectionStudent t) throws Exception {
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION_STUDENTS.INSERT_D(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section());
            ps.setInt(2,t.getId_person());
            ps.setInt(3,t.getId_answer_student());
            ps.setInt(4,t.getId_note());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Update(SectionStudent t) throws Exception {
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION_STUDENTS.UPDATE_D(?,?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section_student());
            ps.setInt(2,t.getId_section());
            ps.setInt(3,t.getId_person());
            ps.setInt(4,t.getId_answer_student());
            ps.setInt(4,t.getId_note());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ACTUALIZAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void Delete(SectionStudent t) throws Exception {
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION_STUDENTS.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section_student());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ELIMINAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public SectionStudent Search(int t) throws Exception {
        std = new SectionStudent();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_ALTERNATIVES.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                std.setId_section_student(rs.getInt("ID_SECTION_STUDENT"));
                std.setId_section(rs.getInt("ID_SECTION"));
                std.setId_person(rs.getInt("ID_PERSON"));
                std.setId_answer_student(rs.getInt("ID_ANSWER_STUDENT"));
                std.setId_note(rs.getInt("ID_NOTE"));
                
                
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
        return std;
        
    }

    @Override
    public ArrayList<SectionStudent> ListAll() throws Exception {
    
        ArrayList<SectionStudent> lista = new ArrayList<SectionStudent>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_ALTERNATIVES.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                std = new SectionStudent();
                std.setId_section_student(rs.getInt("ID_SECTION_STUDENT"));
                std.setId_section(rs.getInt("ID_SECTION"));
                std.setId_person(rs.getInt("ID_PERSON"));
                std.setId_answer_student(rs.getInt("ID_ANSWER_STUDENT"));
                std.setId_note(rs.getInt("ID_NOTE"));
                lista.add(std);
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
