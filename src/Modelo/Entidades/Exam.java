/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Entidades;

/**
 *
 * @author Angel
 */
public class Exam {
   
    private int id_exam;
    private int id_section;

    public Exam() {
    }

    public Exam(int id_exam, int id_section) {
        this.id_exam = id_exam;
        this.id_section = id_section;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
    }
    
    
    
}
