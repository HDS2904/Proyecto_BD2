package Principal;

import Controladores.Controlador_Profesor;

import Modelo.DAO.TeacherDAO;
import Modelo.Entidades.Person;
import Modelo.Entidades.Teacher;
import Vista.V_Profesores;

public class ProyectoHDS {

    public static void main(String[] args) {
        Teacher tea = new Teacher();
        Person per = new Person();
        TeacherDAO pcrud = new TeacherDAO();
        V_Profesores vc = new V_Profesores();
        
        Controlador_Profesor cc = new Controlador_Profesor(per, tea,pcrud,vc);
        cc.iniciar();
        vc.setVisible(true);
        
    }
    
}
