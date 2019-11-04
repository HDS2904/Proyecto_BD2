
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import oracle.jdbc.OracleTypes;

public class TeacherDAO extends Conectar implements ICRUD<Teacher>{
    private String mensaje = " ";

    @Override
    public String Create(Teacher t) {
        Person per = t.getPer();
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "{call PACK_MANT_TEACHER.INSERT_T(?,?,?,?,?,?)}";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getCod_teacher());
            pst.setString(2, per.getFirs_name());
            pst.setString(3, per.getLast_name());
            pst.setInt(4, per.getPhone());
            pst.setString(5, per.getDireccion());
            pst.setString(6, per.getEmail());
            mensaje = "DATOS GUARDADOS EXITOSAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + e.getMessage();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            return mensaje;
        }
    }

    @Override
    public String Update(Teacher t) {
        Person per = t.getPer();
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "{call PACK_MANT_TEACHER.UPDATE_T(?,?,?,?,?,?)";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getCod_teacher());
            pst.setString(2, per.getFirs_name());
            pst.setString(3, per.getLast_name());
            pst.setInt(4, per.getPhone());
            pst.setString(5, per.getDireccion());
            pst.setString(6, per.getEmail());
            mensaje = "SE ACTUALIZO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO REALIZAR LA MODIFICACIÓN \n" + e.getMessage();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return mensaje;
    }

    @Override
    public String Delete(Teacher t) {
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "{call PACK_MANT_TEACHER.DELETE_T(?)";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getCod_teacher());
            mensaje = "REGISTRO ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO ELIMINAR EL REGISTRO \n" + e.getMessage();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return mensaje;
    }

    @Override
    public String SearchId(String r, Teacher t) {
        Person pe = new Person();
        ResultSet rs;
        CallableStatement call;
        Connection con = getConnection();
        String sql = "{call PACK_LIST_TEACHER.GET_TEACHER(?,?)}";
        
        try {
            call = con.prepareCall(sql);
            call.setString(1, r);
            call.registerOutParameter(2,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(2);
            int c = 0;
            if(rs.next()){
                System.out.println(rs.getString("EMAIL"));
                t.setCod_teacher(rs.getString("CODETEACHER"));
                pe.setFirs_name(rs.getString("FIRSNAME"));
                pe.setLast_name(rs.getString("LASTNAME"));
                pe.setPhone(Integer.parseInt(rs.getString("PHONE")));
                pe.setDireccion(rs.getString("ADDRESS"));
                pe.setEmail(rs.getString("EMAIL"));
                t.setPer(pe);
                mensaje = "SE UBICO LA ENTIDAD: "+r+"\n";
            }else
                mensaje = "NO SE PUDO ENCONTRAR LA ENTDAD DE CODIGO: "+r+"\n";
            mensaje = mensaje+ "SE EJECUTO CORRECTAMENTE LA BUSQUEDA";
            call.execute();
            call.close();
        } catch (SQLException e) {
            mensaje = mensaje + "ERROR AL EJECUTAR LA BUSQUEDA \n" + e.getMessage();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return mensaje;
    }
    
    @Override
    public String ListAll(JTable tabla) {
        Connection con = getConnection();
        CallableStatement call= null;
        DefaultTableModel ad=new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"Pos","Código","Nombre","Apellido","Telefono","Dirección","Email"});
        
        String sql = "{call PACK_LIST_TEACHER.GET_TEACHERS(?)}";
        
        String[] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            int c = 1;
             while(rs.next()){
                 filas[0] = String.valueOf(c + "°");
                 for(int i =1; i<7; i++){
                     filas[i] = rs.getString(i);
                 }
                 ad.addRow(filas);
                 c++;
             }
             TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(ad);
             tabla.setRowSorter(order);
             tabla.setModel(ad);
        } catch (SQLException e) {
            mensaje = "NO SE PUDO MOSTRAR LA TABLA \n" + e.getMessage();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return mensaje;
    }

    
    
    
}