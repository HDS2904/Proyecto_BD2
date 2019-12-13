/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.SectionDAO;
import Modelo.Entidades.Section;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Controler_Section {
    
    private SectionDAO dao;
    String mensaje ="";

    public Controler_Section() {
        
        dao = new SectionDAO();
        
    }
    
    public String CreateSection(Section p) throws Exception{
        dao.Create(p);
        mensaje="Alternative CREADA";
        
        return mensaje;
    }
    
    public String actualizarSection(Section p) throws Exception{
        dao.Update(p);
        mensaje="Alternative ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteSection(Section p) throws Exception{
        dao.Delete(p);
        mensaje="Alternative ELIMINADA";
        
        return mensaje;
    }
    
    public Section SearchSection(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<Section> ListSection() throws Exception{
                
       return dao.ListAll();
                
    }
    
    public Section SearchSection_Te_Su(int x,int y) throws Exception{
        
        return dao.Search_TE_SU(x,y);
    }
    
}
