/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.QuestionDAO;
import Modelo.Entidades.Question;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Controler_Question {
    
    private QuestionDAO dao;
    String mensaje ="";

    public Controler_Question() {
        
        dao = new QuestionDAO();
        
    }
    
    public String CreateQuestion(Question p) throws Exception{
        dao.Create(p);
        mensaje="QUESTION CREADA";
        
        return mensaje;
    }
    
    public String actualizarQuestion(Question p) throws Exception{
        dao.Update(p);
        mensaje="QUESTION ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteQuestion(Question p) throws Exception{
        dao.Delete(p);
        mensaje="QUESTION ELIMINADA";
        
        return mensaje;
    }
    
    public Question SearchQuestion(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<Question> ListQuestion() throws Exception{
                
       return dao.ListAll();
                
    }
    
    public ArrayList<Question> SearchQuestion_by_subject(int t) throws Exception{
        
        return dao.Search_by_subject(t);
    }
    
}
