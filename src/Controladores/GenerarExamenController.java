/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Entidades.GenerarExamen;
import Modelo.Entidades.Question;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class GenerarExamenController {
    
    
    
    
    public ArrayList<GenerarExamen> obtenerCantidad(int cant){
        
        ArrayList<GenerarExamen> lista = new ArrayList<>();
        int a , b , c ,d ,aux;

        for(d =0;d<11;d++){
            for(c=0;c<7;c++){
                for(b=0;b<6;b++)
                    for(a=0;a<5;a++){
                        aux=2*d+3*c+4*b+5*a;
                        if(aux==20 && (a+b+c+d)==cant){
                            GenerarExamen gene = new GenerarExamen();
                            gene.setPregunta2(d);
                            gene.setPregunta3(c);
                            gene.setPregunta4(b);
                            gene.setPregunta5(a);
                            
                            lista.add(gene);
                            
                        }
                    }
            }
        }
        
        return lista;
    }
    
    
    public GenerarExamen obtenerPreguntas(ArrayList<GenerarExamen> x){
        
        GenerarExamen gene = new GenerarExamen();
        Random alea = new Random();
        int num = alea.nextInt(x.size());
        
        gene = x.get(num);       
        
        return gene;
    }
    
    public ArrayList<Question> obtenerPreguntasExamen(ArrayList<Question> x,int cant){
        
        ArrayList<Question> lista = new ArrayList<>();
        Question pre = new Question();
        Random alea = new Random();
        int num;
        for (int i=0;i<cant;i++) {
        
            num = alea.nextInt(x.size());
            pre = x.get(num);
            lista.add(pre);
            x.remove(num);
            
        }
         
        return lista;
    }
    
    public ArrayList<Question> obtenerPreguntasScore(ArrayList<Question> x,int score){
        
        ArrayList<Question> lista = new ArrayList<>();
        
        for (Question question : x) {
            
            if (question.getScore()==score) {
                lista.add(question);
            }
            
        }
         
        return lista;
    }
    
    public ArrayList<Question> obtenerExamen (ArrayList<Question> a,ArrayList<Question> b,ArrayList<Question> c,ArrayList<Question> d){
        
        ArrayList<Question> lista = new ArrayList<>();
        
        for (Question question : d) {
            
            lista.add(question);
            
        }
        
        for (Question question : c) {
            
            lista.add(question);
            
        }
        
        for (Question question : b) {
            
            lista.add(question);
            
        }
        
        for (Question question : a) {
            
            lista.add(question);
            
        }
         
        return lista;
        
        
    }
    
}
