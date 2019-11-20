package Principal;

import Controladores.Controlador_Profesor;
import Modelo.DAO.PersonDAO;

import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import Vistas.Login;
import Vistas.V_Profesores;

public class ProyectoHDS {

    public static void main(String[] args) {
        Teacher tea = new Teacher();
        Person per = new Person();
        TeacherDAO pcrud = new TeacherDAO();
        PersonDAO tcrud = new PersonDAO();
        V_Profesores vc = new V_Profesores();
        
        Controlador_Profesor cc = new Controlador_Profesor(per, tea,tcrud,pcrud,vc);
        cc.iniciar();
        vc.setVisible(true);
        //Login a = new Login();
        //a.setVisible(true);
        // dsad
        
    }
    
}
