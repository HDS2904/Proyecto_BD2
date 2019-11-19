
package Controladores;

import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Teacher;
import java.util.ArrayList;

public class Controler_Teacher {
    private TeacherDAO pdao;
    String mensaje ="";

    public Controler_Teacher() {
        pdao = new TeacherDAO();
    }
    
    public String CreateTeacher(Teacher t) throws Exception{
        pdao.Create(t);
        mensaje="Teacher CREADA";
        
        return mensaje;
    }
    
    public String actualizarTeacher(Teacher p) throws Exception{
        pdao.Update(p);
        mensaje="Teacher ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteTeacher(Teacher p) throws Exception{
        pdao.Delete(p);
        mensaje="Teacher ELIMINADA";
        
        return mensaje;
    }
    
    public Teacher SearchTeacher(int t) throws Exception{
        
        return pdao.Search(t);
    }
    
    public ArrayList<Teacher> ListTeacher() throws Exception{
                
       return pdao.ListAll();
                
    }
}
