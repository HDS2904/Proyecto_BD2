/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.DAO.ExamDAO;
import Modelo.Entidades.Exam;
import Modelo.Entidades.GenerarExamen;
import Modelo.Entidades.Question;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Usuario
 */
public class Controler_Exam {
    
    private ExamDAO dao;
    String mensaje ="";

    public Controler_Exam() {
        
        dao = new ExamDAO();
        
    }
    
    public String CreateExam(Exam p) throws Exception{
        dao.Create(p);
        mensaje="Alternative CREADA";
        
        return mensaje;
    }
    
    public String actualizarExam(Exam p) throws Exception{
        dao.Update(p);
        mensaje="Alternative ACTUALIZADA";
        
        return mensaje;
    }
    
    public String DeleteExam(Exam p) throws Exception{
        dao.Delete(p);
        mensaje="Alternative ELIMINADA";
        
        return mensaje;
    }
    
    public Exam SearchExam(int t) throws Exception{
        
        return dao.Search(t);
    }
    
    public ArrayList<Exam> ListExam() throws Exception{
                
       return dao.ListAll();
                
    }
    
    public ArrayList<Question> PlantillaExam(ArrayList<Question> preguntas,int cant, int curso) throws Exception{
        
        Controler_Question qu = new Controler_Question();
        GenerarExamenController ge = new GenerarExamenController();
        ArrayList<GenerarExamen> list = ge.obtenerCantidad(cant);
        GenerarExamen pex = ge.obtenerPreguntas(list);
        ArrayList<Question> examen = new ArrayList<>();
        ArrayList<Question> list_q = qu.SearchQuestion_by_subject(curso);
        Controler_Alternative al = new Controler_Alternative();
        
        try {
            ArrayList<Question> lis_p2;
            ArrayList<Question> lis_p3;
            ArrayList<Question> lis_p4;
            ArrayList<Question> lis_p5;
            
                        
            lis_p2 = ge.obtenerPreguntasScore(list_q, 2);
            lis_p3 = ge.obtenerPreguntasScore(list_q, 3);
            lis_p4 = ge.obtenerPreguntasScore(list_q, 4);
            lis_p5 = ge.obtenerPreguntasScore(list_q, 5);
                        
            lis_p2 = ge.obtenerPreguntasExamen(lis_p2, pex.getPregunta2());
            lis_p3 = ge.obtenerPreguntasExamen(lis_p3, pex.getPregunta3());
            lis_p4 = ge.obtenerPreguntasExamen(lis_p4, pex.getPregunta4());
            lis_p5 = ge.obtenerPreguntasExamen(lis_p5, pex.getPregunta5());
                                    
            
            
            examen= ge.obtenerExamen(lis_p5,lis_p4,lis_p3,lis_p2);
            
            Collections.shuffle(examen);
            
        } catch (Exception e) {
        }
        
        
        return examen;
    }
    
    
}
