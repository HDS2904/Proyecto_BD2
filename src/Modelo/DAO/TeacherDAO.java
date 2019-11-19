
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Teacher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import principal.Conectar;



public class TeacherDAO implements ICRUD<Teacher>{
    Connection con;
    CallableStatement call;
    
    @Override
    public void Create(Teacher t) throws Exception{
        con = Conectar.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.INSERT_T(?,?)}";
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
        con = Conectar.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.UPDATE_T(?,?)";
        
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
        con = Conectar.getConnection();
        call = null;
        String sql = "{call PACK_MANAGE_TEACHERS.DELETE_T(?)";
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
        con = Conectar.getConnection();
        call = null;
        Teacher te = new Teacher();
        ResultSet rs;
        String sql = "{? = call PACK_MANAGE_TEACHERS.SEARCH_T(?)}";
        
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
        ArrayList<Teacher> lista = new ArrayList<Teacher>();
        con = Conectar.getConnection();
        call= null;
        Teacher te = new Teacher();
        ResultSet rs = null;
        String sql = "{ ? = call PACK_MANAGE_TEACHERS.LIST_T}";
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
             while(rs.next()){
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
    
}
