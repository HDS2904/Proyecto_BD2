
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Subject;
import Modelo.Entidades.Teacher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import Principal.conexion;



public class TeacherDAO implements ICRUD<Teacher>{
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Teacher t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.INSERT_D(?,?)}";
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.setString(2, t.getCode_teacher());
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
    public void Update(Teacher t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.UPDATE_D(?,?)";
        
        try {
            call = con.prepareCall(sql);
            call.setInt(1, t.getId_person());
            call.setString(2, t.getCode_teacher());
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
    public void Delete(Teacher t) throws Exception{
        con = conexion.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.DELETE_D(?)";
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
    public Teacher Search(int t) throws Exception{
        con = conexion.getConnection();
        call = null;
        Teacher te = new Teacher();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_TEACHERS.SEARCH_D(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            if(rs.next()){
                te.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                te.setCode_teacher(rs.getString("CODE_TEACHER"));
            }else{
                te.setId_person(0);
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
        return te;
    }

    @Override
    public ArrayList<Teacher> ListAll() throws Exception{
        ArrayList<Teacher> lista = new ArrayList<>();
        con = conexion.getConnection();
        call= null;
        Teacher te ;
        ResultSet rs;
        String sql = "{ ? = call PACK_MANAGE_TEACHERS.LIST_D}";
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
                te = new Teacher();
                te.setId_person(Integer.parseInt(rs.getString("ID_PERSON")));
                te.setCode_teacher(rs.getString("CODE_TEACHER"));
                lista.add(te);
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
    public ArrayList<Subject> Search_Sub(int t) throws Exception{
        
        ArrayList<Subject> lista = new ArrayList<>();
        Subject curso ;
        con = conexion.getConnection();
        call = null;
        Teacher te = new Teacher();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_TEACHERS.SEARCH_SUB(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setInt(2, t);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
                curso = new Subject();
                curso.setId_subject(Integer.parseInt(rs.getString("ID_SUBJECT")));
                curso.setName_subject(rs.getString("NAME_SUBJECT"));
                lista.add(curso);
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
