/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Person;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class PersonDAO implements ICRUD<Person>{
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Person p) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_PERSONS.INSERT_D(?,?,?,?,?,?)}";
        try {
            System.out.println("paso1");
            call = con.prepareCall(sql);
            System.out.println("paso2");
            call.setString(1, p.getFirs_name());
            call.setString(2, p.getLast_name());
            call.setInt(3, p.getDni());
            call.setInt(4, p.getPhone());
            call.setString(5, p.getAddress());
            call.setString(6, p.getEmail());
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
    public void Update(Person p) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_PERSONS.UPDATE_P(?,?,?,?,?,?,?)";
        try {
            call = con.prepareCall(sql);
            call.setInt(1, p.getId_person());
            call.setString(2, p.getFirs_name());
            call.setString(3, p.getLast_name());
            call.setInt(4, p.getDni());
            call.setInt(5, p.getPhone());
            call.setString(6, p.getAddress());
            call.setString(7, p.getEmail());
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
    public void Delete(Person p) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_PERSONS.DELETE_P(?)";
        
        try {
            call = con.prepareCall(sql);
            call.setInt(1, p.getId_person());
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
    public Person Search(int t) throws Exception{
        con = conexion.getConnection();
        call = null;
        Person p = new Person();
        ResultSet rs;
        
        String sql = "{? = call PACK_MANAGE_PERSONS.SEARCH_D(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                p.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                p.setFirs_name(rs.getString("FIRST_NAME"));
                p.setLast_name(rs.getString("LAST_NAME"));
                p.setDni(Integer.parseInt(rs.getString("DNI")));
                p.setPhone(Integer.parseInt(rs.getString("PHONE")));
                p.setAddress(rs.getString("ADDRESS"));
                p.setEmail(rs.getString("EMAIL"));
            }else{
                p.setId_person(0);
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
        System.out.println("te devolvio");
        return p;
    }

    @Override
    public ArrayList<Person> ListAll() throws Exception{
        ArrayList<Person> lista = new ArrayList<Person>();
        con = conexion.getConnection();
        call= null;
        Person ps = new Person();
        ResultSet rs = null;
        String sql = "{? = call PACK_MANAGE_TEACHERS.LIST_T}";
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
                ps = new Person();
                ps.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                ps.setFirs_name(rs.getString("FIRST_NAME"));
                ps.setLast_name(rs.getString("LAST_NAME"));
                ps.setDni(Integer.parseInt(rs.getString("DNI")));
                ps.setPhone(Integer.parseInt(rs.getString("PHONE")));
                ps.setAddress(rs.getString("ADDRESS"));
                ps.setEmail(rs.getString("EMAIL"));
                lista.add(ps);
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
