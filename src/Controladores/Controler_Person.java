/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.PersonDAO;
import Modelo.Entidades.Person;
import java.util.ArrayList;

/**
 *
 * @author HDS
 */
public class Controler_Person {
    private PersonDAO pdao;
    String mensaje ="";

    public Controler_Person() {
        pdao = new PersonDAO();
    }
    
    public String CreatePerson(Person p) throws Exception{
        pdao.Create(p);
        mensaje="PERSONA CREADA";
        
        return mensaje;
    }
    
    public String actualizarPerson(Person p) throws Exception{
        pdao.Update(p);
        mensaje="PERSONA ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeletePerson(Person p) throws Exception{
        pdao.Delete(p);
        mensaje="PERSONA ELIMINADA";
        
        return mensaje;
    }
    
    public Person SearchPerson(int t) throws Exception{
        
        return pdao.Search(t);
    }
    
    public ArrayList<Person> ListPerson() throws Exception{
                
       return pdao.ListAll();
                
    }
    
}
