package Principal;

import Controladores.Controler_Person;
import Modelo.Entidades.Person;
import Vistas.Login;
import java.util.ArrayList;
public class ProyectoHDS {

    public static void main(String[] args) throws Exception {
        Controler_Person co = new Controler_Person();
        
        try {
            System.out.println("hola");
            ArrayList<Person> lista = co.ListPerson();
            for (Person x : lista) {
                
                System.out.println("persona:  "+x.getLast_name());
                
            }
            
            
            
        } catch (Exception e) {
        }
    }
    
}
