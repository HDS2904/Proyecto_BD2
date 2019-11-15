
package Modelo.DAO;

import Interfaz.ICRUD;
import Interfaz.ICRUD2;
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
import principal.Conectar;

public class TeacherDAO implements ICRUD2<Teacher>{
    private String mensaje = " ";

    @Override
    public String Create(Teacher t) {
        Person per = t.getPer();
        PreparedStatement pst = null;
        Connection con = Conectar.getConnection();
        String sql = "{call PACK_MANAGE_TEACHER.INSERT_T(?,?,?,?,?,?,?)}";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getCod_teacher());
            pst.setString(2, per.getFirs_name());
            pst.setString(3, per.getLast_name());
            pst.setInt(4, per.getDni());
            pst.setInt(5, per.getPhone());
            pst.setString(6, per.getAddress());
            pst.setString(7, per.getEmail());
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
        Connection con = Conectar.getConnection();
        String sql = "{call PACK_MANAGE_TEACHER.UPDATE_T(?,?,?,?,?,?,?)";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getCod_teacher());
            pst.setString(2, per.getFirs_name());
            pst.setString(3, per.getLast_name());
            pst.setInt(4, per.getDni());
            pst.setInt(5, per.getPhone());
            pst.setString(6, per.getAddress());
            pst.setString(7, per.getEmail());
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
        Connection con = Conectar.getConnection();
        String sql = "{call PACK_MANAGE_TEACHER.DELETE_T(?)";
        
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
    public String SearchId(Teacher t) {
        Person pe = new Person();
        ResultSet rs;
        CallableStatement call;
        Connection con = Conectar.getConnection();
        String sql = "{? = call PACK_MANAGE_TEACHER.GET_TEACHER(?)}";
        
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.setString(2, t.getCod_teacher());
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            int c = 0;
            if(rs.next()){
                System.out.println(rs.getString("EMAIL"));
                t.setCod_teacher(rs.getString("CODETEACHER"));
                pe.setFirs_name(rs.getString("FIRSNAME"));
                pe.setLast_name(rs.getString("LASTNAME"));
                pe.setDni(Integer.parseInt(rs.getString("DNI")));
                pe.setPhone(Integer.parseInt(rs.getString("PHONE")));
                pe.setAddress(rs.getString("ADDRESS"));
                pe.setEmail(rs.getString("EMAIL"));
                t.setPer(pe);
                mensaje = "SE UBICO LA ENTIDAD: "+t.getCod_teacher()+"\n";
            }else{
                mensaje = "NO SE PUDO ENCONTRAR LA ENTDAD DE CODIGO: "+t.getCod_teacher()+"\n";
                t.setCod_teacher("fail");
            }
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
        Connection con = Conectar.getConnection();
        CallableStatement call= null;
        DefaultTableModel ad=new DefaultTableModel();
        ad.setColumnIdentifiers(new Object[]{"Pos","Código","Nombre","Apellido","DNI","Telefono","Dirección","Email"});
        
        String sql = "{ ? = call PACK_MANAGE_TEACHER.GET_TEACHERS}";
        
        String[] filas = new String[8];
        ResultSet rs = null;
       
        try {
            call = con.prepareCall(sql);
            call.registerOutParameter(1,OracleTypes.CURSOR);
            call.executeQuery();
            rs = (ResultSet)call.getObject(1);
            int c = 1;
             while(rs.next()){
                 filas[0] = String.valueOf(c + "°");
                 for(int i =1; i<8; i++){
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
