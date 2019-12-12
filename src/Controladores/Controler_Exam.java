/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.ExamDAO;
import Modelo.Entidades.Exam;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Controler_Exam {
    
    private ExamDAO dao;
    String mensaje ="";

    public Controler_Exam() {
        
        dao = new ExamDAO();
        
    }
    
    public String CreateExam(Exam p) throws Exception{
        dao.Create(p);
        mensaje="Alternative CREADA";
        
        return mensaje;
    }
    
    public String actualizarExam(Exam p) throws Exception{
        dao.Update(p);
        mensaje="Alternative ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteExam(Exam p) throws Exception{
        dao.Delete(p);
        mensaje="Alternative ELIMINADA";
        
        return mensaje;
    }
    
    public Exam SearchExam(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<Exam> ListExam() throws Exception{
                
       return dao.ListAll();
                
    }
    
}
