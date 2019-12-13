/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Director;
import Modelo.Entidades.Teacher;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author HDS
 */
public class DirectorDAO implements ICRUD<Director>{
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Director t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_DIRECTORS.INSERT_D(?.?,?)}";
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.setString(2, t.getCode_director());
            call.setInt(3, t.getId_faculty());
            call.execute();
            call.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void Update(Director t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_DIRECTORS.UPDATE_D(?,?,?)";
        
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.setString(2, t.getCode_director());
            call.setInt(3, t.getId_faculty());
            call.execute();
            call.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void Delete(Director t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_DIRECTORS.DELETE_D(?)";
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.execute();
            call.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public Director Search(int t) throws Exception {
        con = conexion.getConnection();
        call = null;
        Director di = new Director();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_DIRECTORS.SEARCH_D(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                di.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                di.setCode_director(rs.getString("CODE_DIRECTOR"));
                di.setId_faculty(Integer.parseInt(rs.getString("ID_FACULTY")));
            }else{
                di.setId_person(0);
            }
            rs.close();
            call.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return di;
    }

    @Override
    public ArrayList<Director> ListAll() throws Exception {
        ArrayList<Director> lista = new ArrayList<>();
        con = conexion.getConnection();
        call= null;
        Director di;
        ResultSet rs;
        String sql = "{ ? = call PACK_MANAGE_DIRECTORS.LIST_D}";
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
                di = new Director();
                di.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                di.setCode_director(rs.getString("CODE_DIRECTOR"));
                di.setId_faculty(Integer.parseInt(rs.getString("ID_FACULTY")));
                lista.add(di);
             }
             rs.close();
             call.close();
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
