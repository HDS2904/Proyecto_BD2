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
public class AlternativeExam {
    
    private int id_alternative_exam;
    private int id_question_exam;
    private String alternative_A;
    private String alternative_B;
    private String alternative_C;

    public AlternativeExam() {
    }

    public AlternativeExam(int id_alternative_exam, int id_question_exam, String alternative_A, String alternative_B, String alternative_C) {
        this.id_alternative_exam = id_alternative_exam;
        this.id_question_exam = id_question_exam;
        this.alternative_A = alternative_A;
        this.alternative_B = alternative_B;
        this.alternative_C = alternative_C;
    }

    public int getId_alternative_exam() {
        return id_alternative_exam;
    }

    public void setId_alternative_exam(int id_alternative_exam) {
        this.id_alternative_exam = id_alternative_exam;
    }

    public int getId_question_exam() {
        return id_question_exam;
    }

    public void setId_question_exam(int id_question_exam) {
        this.id_question_exam = id_question_exam;
    }

    public String getAlternative_A() {
        return alternative_A;
    }

    public void setAlternative_A(String alternative_A) {
        this.alternative_A = alternative_A;
    }

    public String getAlternative_B() {
        return alternative_B;
    }

    public void setAlternative_B(String alternative_B) {
        this.alternative_B = alternative_B;
    }

    public String getAlternative_C() {
        return alternative_C;
    }

    public void setAlternative_C(String alternative_C) {
        this.alternative_C = alternative_C;
    }
    
    
    
    
}
