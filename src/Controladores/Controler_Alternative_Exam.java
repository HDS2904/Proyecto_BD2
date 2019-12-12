/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.AlternativeExamDAO;
import Modelo.Entidades.AlternativeExam;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Controler_Alternative_Exam {
    
    private AlternativeExamDAO dao;
    String mensaje ="";

    public Controler_Alternative_Exam() {
        
        dao = new AlternativeExamDAO();
        
    }
    
    public String CreateAlternativeExam(AlternativeExam p) throws Exception{
        dao.Create(p);
        mensaje="Alternative CREADA";
        
        return mensaje;
    }
    
    public String actualizarAlternativeExam(AlternativeExam p) throws Exception{
        dao.Update(p);
        mensaje="Alternative ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteAlternativeExam(AlternativeExam p) throws Exception{
        dao.Delete(p);
        mensaje="Alternative ELIMINADA";
        
        return mensaje;
    }
    
    public AlternativeExam SearchAlternativeExam(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<AlternativeExam> ListAlternativeExam() throws Exception{
                
       return dao.ListAll();
                
    }
    
}
