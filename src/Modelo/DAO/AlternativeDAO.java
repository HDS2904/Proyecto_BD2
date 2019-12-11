
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Alternative;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Angel
 */
public class AlternativeDAO implements ICRUD<Alternative> {

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Alternative al;
    
    @Override
    public void Create(Alternative t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVES.INSERT_D(?,?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question());
            ps.setString(2, t.getAlternative_A());
            ps.setString(3, t.getAlternative_B());
            ps.setString(4, t.getAlternative_C());
            ps.setString(5, t.getAnswer());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Update(Alternative t) throws Exception {
    
         cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVES.UPDATE_D(?,?,?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_question());
            ps.setInt(2,t.getId_question());
            ps.setString(3, t.getAlternative_A());
            ps.setString(4, t.getAlternative_B());
            ps.setString(5, t.getAlternative_C());
            ps.setString(6, t.getAnswer());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(Alternative t) throws Exception {
    
           cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_ALTERNATIVES.DELETE_D(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_alternative());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public Alternative Search(int t) throws Exception {
        
        al = new Alternative();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_ALTERNATIVES.SEARCH_D(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                al.setId_alternative(rs.getInt("ID_ALTERNATIVE"));
                al.setId_question(rs.getInt("ID_QUESTION"));
                al.setAlternative_A(rs.getString("ALTERNATIVE_A"));
                al.setAlternative_B(rs.getString("ALTERNATIVE_B"));
                al.setAlternative_C(rs.getString("ALTERNATIVE_C"));
                al.setAnswer(rs.getString("ANSWER"));
                
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
        return al;
        
    }

    @Override
    public ArrayList<Alternative> ListAll() throws Exception {
    
        ArrayList<Alternative> lista = new ArrayList<Alternative>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_ALTERNATIVES.LIST_D}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                al = new Alternative();
                al.setId_alternative(rs.getInt("ID_ALTERNATIVE"));
                al.setId_question(rs.getInt("ID_QUESTION"));
                al.setAlternative_A(rs.getString("ALTERNATIVE_A"));
                al.setAlternative_B(rs.getString("ALTERNATIVE_B"));
                al.setAlternative_C(rs.getString("ALTERNATIVE_C"));
                al.setAnswer(rs.getString("ANSWER"));
                lista.add(al);
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
