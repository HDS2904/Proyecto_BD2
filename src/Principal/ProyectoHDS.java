package Principal;

import Controladores.Controler_Alternative;
import Controladores.Controler_Exam;
import Controladores.Controler_Person;
import Controladores.Controler_Question;
import Controladores.Controler_Question_Exam;
import Controladores.Controler_Section;
import Controladores.GenerarExamenController;
import Modelo.Entidades.Alternative;
import Modelo.Entidades.AlternativeExam;
import Modelo.Entidades.Exam;
import Modelo.Entidades.GenerarExamen;
import Modelo.Entidades.Person;
import Modelo.Entidades.Question;
import Modelo.Entidades.QuestionExam;
import Modelo.Entidades.Section;
import Vistas.Login;
import java.util.ArrayList;
import java.util.Collections;
public class ProyectoHDS {

    public static void main(String[] args) throws Exception {
//        Controler_Question qu = new Controler_Question();
//        GenerarExamenController ge = new GenerarExamenController();
//        ArrayList<GenerarExamen> list = ge.obtenerCantidad(6);
//        GenerarExamen pex = ge.obtenerPreguntas(list);
//        ArrayList<Question> examen;
//        
//        Controler_Alternative al = new Controler_Alternative();
//        
//        try {
//            ArrayList<Question> lis_p2;
//            ArrayList<Question> lis_p3;
//            ArrayList<Question> lis_p4;
//            ArrayList<Question> lis_p5;
//            for (GenerarExamen x : list) {
//                
//                System.out.println("cantidad de preguntas 2:  "+x.getPregunta2()+" cantidad de preguntas 3:  "+x.getPregunta3()+" cantidad de preguntas 4:  "+x.getPregunta4()+" cantidad de preguntas 5:  "+x.getPregunta5() );
//            }
//            
//            GenerarExamen x=pex;
//            System.out.println("\ncantidad de preguntas 2:  "+x.getPregunta2()+" cantidad de preguntas 3:  "+x.getPregunta3()+" cantidad de preguntas 4:  "+x.getPregunta4()+" cantidad de preguntas 5:  "+x.getPregunta5() );
//            
//            ArrayList<Question> list_q = qu.SearchQuestion_by_subject(1);
//            System.out.println(list_q.size());
//            lis_p2 = ge.obtenerPreguntasScore(list_q, 2);
//            lis_p3 = ge.obtenerPreguntasScore(list_q, 3);
//            lis_p4 = ge.obtenerPreguntasScore(list_q, 4);
//            lis_p5 = ge.obtenerPreguntasScore(list_q, 5);
//            
//            System.out.println(lis_p2.size()+" "+lis_p3.size()+" "+lis_p4.size()+" "+lis_p5.size()+" " );
//            
//            lis_p2 = ge.obtenerPreguntasExamen(lis_p2, x.getPregunta2());
//            lis_p3 = ge.obtenerPreguntasExamen(lis_p3, x.getPregunta3());
//            lis_p4 = ge.obtenerPreguntasExamen(lis_p4, x.getPregunta4());
//            lis_p5 = ge.obtenerPreguntasExamen(lis_p5, x.getPregunta5());
//            
//            
//            System.out.println(lis_p2.size()+" "+lis_p3.size()+" "+lis_p4.size()+" "+lis_p5.size()+" " );
//            
//            for (Question q : lis_p2) {
//                System.out.println("pregunta 2: "+q.getQuestion());
//            }
//            for (Question q : lis_p3) {
//                System.out.println("pregunta 3: "+q.getQuestion());
//            }
//            for (Question q : lis_p4) {
//                System.out.println("pregunta 4: "+q.getQuestion());
//            }
//            for (Question q : lis_p5) {
//                System.out.println("pregunta 5: "+q.getQuestion());
//            }
//            
//            examen= ge.obtenerExamen(lis_p5,lis_p4,lis_p3,lis_p2);
//            System.out.println("\n");
//            for (Question q : examen) {
//                System.out.println("examen: "+q.getQuestion());
//            }
//            System.out.println("\n");
//            Collections.shuffle(examen);
//            
//            for (Question q : examen) {
//                System.out.println("examen: "+q.getId_question()+" "+q.getQuestion());
//                Alternative li = al.SearchAlternative_by_question(q.getId_question());
//                AlternativeExam lo = al.mesclar_question(li);
//                System.out.println("alternativa A: "+lo.getAlternative_A()+" alternativa B: "+lo.getAlternative_B()+" alternativa C: "+lo.getAlternative_C());
//                
//            }
//        } catch (Exception e) {
//        }
        //Login log = new Login();
        //log.setVisible(true);
        
        Controler_Question_Exam qex = new Controler_Question_Exam();
        QuestionExam a=qex.SearchQuestionExam_Qu(5, 3);
        
        System.out.println(a.getQuestion());
        
        
        
    }
    
}
