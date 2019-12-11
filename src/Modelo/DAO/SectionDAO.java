/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Section;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import Principal.conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author Angel
 */
public class SectionDAO implements ICRUD<Section>{
    Connection con;
    CallableStatement call;

    @Override
    public void Create(Section t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SECTIONS.INSERT_D(?,?,?,?)}";
        try {
            System.out.println("paso chidori1");
            call = con.prepareCall(sql);
            call.setInt(1,t.getId_subject());
            call.setInt(2,t.getId_person());
            call.setInt(3,t.getId_exam());
            call.setString(4,t.getSection_group());
            call.execute();
            System.out.println("paso chidori2");
            call.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }finally{
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void Update(Section t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SECTIONS.UPDATE_D(?,?,?,?,?)}";
        
        try {
            call = con.prepareCall(sql);
            call.setInt(1,t.getId_section());
            call.setInt(2,t.getId_subject());
            call.setInt(3,t.getId_person());
            call.setInt(4,t.getId_exam());
            call.setString(5,t.getSection_group());
            call.execute();
            call.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(Section t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_SECTIONS.DELETE_D(?)}";
        try {
            call = con.prepareCall(sql);
            call.setInt(1,t.getId_section());
            call.execute();
            call.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public Section Search(int t) throws Exception {
        con = conexion.getConnection();
        call = null;
        Section st = new Section();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_SECTIONS.SEARCH_D(?)}";
         try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                st.setId_section(rs.getInt("ID_SECTION"));
                st.setId_subject(rs.getInt("ID_SUBJECT"));
                st.setId_person(rs.getInt("ID_PERSON"));
                st.setId_exam(rs.getInt("ID_EXAM"));
                st.setSection_group(rs.getString("SECTION_GROUP"));
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
        return st;
        
    }

    @Override
    public ArrayList<Section> ListAll() throws Exception {
        ArrayList<Section> lista = new ArrayList<>();
        con = conexion.getConnection();
        call= null;
        Section st;
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_SECTIONS.LIST_D}";
         try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            while(rs.next()){
                st = new Section();
                st.setId_section(rs.getInt("ID_SECTION"));
                st.setId_subject(rs.getInt("ID_SUBJECT"));
                st.setId_person(rs.getInt("ID_PERSON"));
                st.setId_exam(rs.getInt("ID_EXAM"));
                st.setSection_group(rs.getString("SECTION_GROUP"));
                lista.add(st);
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
