/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.QuestionExamDAO;
import Modelo.Entidades.QuestionExam;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Controler_Question_Exam {
    
    private QuestionExamDAO dao;
    String mensaje ="";

    public Controler_Question_Exam() {
        
        dao = new QuestionExamDAO();
        
    }
    
    public String CreateQuestionExam(QuestionExam p) throws Exception{
        dao.Create(p);
        mensaje="QUESTION CREADA";
        
        return mensaje;
    }
    
    public String actualizarQuestionExam(QuestionExam p) throws Exception{
        dao.Update(p);
        mensaje="QUESTION ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteQuestionExam(QuestionExam p) throws Exception{
        dao.Delete(p);
        mensaje="QUESTION ELIMINADA";
        
        return mensaje;
    }
    
    public QuestionExam SearchQuestionExam(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<QuestionExam> ListQuestionExam() throws Exception{
                
       return dao.ListAll();
                
    }
    
}
