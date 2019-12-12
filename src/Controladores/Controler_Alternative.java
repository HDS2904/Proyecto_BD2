/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.AlternativeDAO;
import Modelo.Entidades.Alternative;
import Modelo.Entidades.AlternativeExam;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Usuario
 */
public class Controler_Alternative {
    
    private AlternativeDAO dao;
    String mensaje ="";

    public Controler_Alternative() {
        
        dao = new AlternativeDAO();
        
    }
    
    public String CreateAlternative(Alternative p) throws Exception{
        dao.Create(p);
        mensaje="Alternative CREADA";
        
        return mensaje;
    }
    
    public String actualizarAlternative(Alternative p) throws Exception{
        dao.Update(p);
        mensaje="Alternative ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteAlternative(Alternative p) throws Exception{
        dao.Delete(p);
        mensaje="Alternative ELIMINADA";
        
        return mensaje;
    }
    
    public Alternative SearchAlternative(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<Alternative> ListAlternative() throws Exception{
                
       return dao.ListAll();
                
    }
    
    public Alternative SearchAlternative_by_question(int t) throws Exception{
        
        return dao.Search_by_question(t);
    }
    
    public AlternativeExam mesclar_question(Alternative t) throws Exception{
        
        AlternativeExam aux = new AlternativeExam();
        
        ArrayList<String> li = new ArrayList<>();
        li.add(t.getAlternative_A());
        li.add(t.getAlternative_B());
        li.add(t.getAlternative_C());
        
        Collections.shuffle(li);
        
        aux.setId_alternative_exam(t.getId_alternative());
        aux.setId_question_exam(t.getId_question());
        aux.setAlternative_A(li.get(0));
        aux.setAlternative_B(li.get(1));
        aux.setAlternative_C(li.get(2));
        
        return aux;
    }
    
}
