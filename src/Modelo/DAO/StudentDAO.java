/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Student;
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
public class StudentDAO implements ICRUD<Student>{
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Student t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_STUDENTS.INSERT_D(?,?)}";
        try {
            call = con.prepareCall(sql);
            call.setString(1, t.getCode_student());
            call.setInt(2, t.getId_school());
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
    public void Update(Student t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_STUDENTS.UPDATE_D(?,?,?)";
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.setString(2, t.getCode_student());
            call.setInt(3, t.getId_school());
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
    public void Delete(Student t) throws Exception {
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_STUDENTS.DELETE_D(?)";
        
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
    public Student Search(int t) throws Exception {
        con = conexion.getConnection();
        call = null;
        Student s = new Student();
        ResultSet rs;
        
        String sql = "{? = call PACK_MANAGE_STUDENTS.SEARCH_D(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                s.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                s.setCode_student(rs.getString("CODE_STUDENT"));
                s.setId_school(Integer.parseInt(rs.getString("ID_SCHOOL")));
            }else{
                s.setId_person(0);
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
        return s;
    }

    @Override
    public ArrayList<Student> ListAll() throws Exception {
        ArrayList<Student> lista = new ArrayList<>();
        con = conexion.getConnection();
        call= null;
        Student st;
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_STUDENTS.LIST_D}";
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
                st = new Student();
                st.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                st.setCode_student(rs.getString("CODE_STUDENT"));
                st.setId_school(Integer.parseInt(rs.getString("ID_SCHOOL")));
                lista.add(st);
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
