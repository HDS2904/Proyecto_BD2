/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.AlternativeDAO;
import Modelo.Entidades.Alternative;
import java.util.ArrayList;

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
    
}
