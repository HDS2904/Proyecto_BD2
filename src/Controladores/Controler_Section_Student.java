/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.SectionStudentDAO;
import Modelo.Entidades.SectionStudent;
import java.util.ArrayList;

/**
 *
 * @author HDS
 */
public class Controler_Section_Student {
    private SectionStudentDAO dao;
    String mensaje ="";

    public Controler_Section_Student() {
        
        dao = new SectionStudentDAO();
        
    }
    
    public String CreateSection(SectionStudent p) throws Exception{
        dao.Create(p);
        mensaje="Seccion CREADA";
        
        return mensaje;
    }
    
    public String actualizarSectionStudent(SectionStudent p) throws Exception{
        dao.Update(p);
        mensaje="SectionStudent ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteSectionStudent(SectionStudent p) throws Exception{
        dao.Delete(p);
        mensaje="SectionStudent ELIMINADA";
        
        return mensaje;
    }
    
    public SectionStudent SearchSectionStudent(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<SectionStudent> ListSectionStudent() throws Exception{
                
       return dao.ListAll();
                
    }
}
