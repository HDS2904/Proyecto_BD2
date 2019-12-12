package Principal;

import Controladores.Controler_Person;
import Controladores.Controler_Question;
import Controladores.GenerarExamenController;
import Modelo.Entidades.GenerarExamen;
import Modelo.Entidades.Person;
import Modelo.Entidades.Question;
import Vistas.Login;
import java.util.ArrayList;
public class ProyectoHDS {

    public static void main(String[] args) throws Exception {
        Controler_Question qu = new Controler_Question();
        GenerarExamenController ge = new GenerarExamenController();
        ArrayList<GenerarExamen> list = ge.obtenerCantidad(6);
        GenerarExamen pex = ge.obtenerPreguntas(list);
        ArrayList<Question> examen;
        try {
            ArrayList<Question> lis_p2;
            ArrayList<Question> lis_p3;
            ArrayList<Question> lis_p4;
            ArrayList<Question> lis_p5;
            for (GenerarExamen x : list) {
                
                System.out.println("cantidad de preguntas 2:  "+x.getPregunta2()+" cantidad de preguntas 3:  "+x.getPregunta3()+" cantidad de preguntas 4:  "+x.getPregunta4()+" cantidad de preguntas 5:  "+x.getPregunta5() );
            }
            
            GenerarExamen x=pex;
            System.out.println("\ncantidad de preguntas 2:  "+x.getPregunta2()+" cantidad de preguntas 3:  "+x.getPregunta3()+" cantidad de preguntas 4:  "+x.getPregunta4()+" cantidad de preguntas 5:  "+x.getPregunta5() );
            
            ArrayList<Question> list_q = qu.SearchQuestion_by_subject(1);
            System.out.println(list_q.size());
            lis_p2 = ge.obtenerPreguntasScore(list_q, 2);
            lis_p3 = ge.obtenerPreguntasScore(list_q, 3);
            lis_p4 = ge.obtenerPreguntasScore(list_q, 4);
            lis_p5 = ge.obtenerPreguntasScore(list_q, 5);
            
            System.out.println(lis_p2.size()+" "+lis_p3.size()+" "+lis_p4.size()+" "+lis_p5.size()+" " );
            
            lis_p2 = ge.obtenerPreguntasExamen(lis_p2, x.getPregunta2());
            lis_p3 = ge.obtenerPreguntasExamen(lis_p3, x.getPregunta3());
            lis_p4 = ge.obtenerPreguntasExamen(lis_p4, x.getPregunta4());
            lis_p5 = ge.obtenerPreguntasExamen(lis_p5, x.getPregunta5());
            
            
            System.out.println(lis_p2.size()+" "+lis_p3.size()+" "+lis_p4.size()+" "+lis_p5.size()+" " );
            
            for (Question q : lis_p2) {
                System.out.println("pregunta 2: "+q.getQuestion());
            }
            for (Question q : lis_p3) {
                System.out.println("pregunta 3: "+q.getQuestion());
            }
            for (Question q : lis_p4) {
                System.out.println("pregunta 4: "+q.getQuestion());
            }
            for (Question q : lis_p5) {
                System.out.println("pregunta 5: "+q.getQuestion());
            }
            
            examen= ge.obtenerExamen(lis_p2,lis_p3,lis_p4,lis_p5);
            
            for (Question q : examen) {
                System.out.println("examen: "+q.getQuestion());
            }
            
            
        } catch (Exception e) {
        }
    }
    
}
