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
public class AnswerStundent {
    
    private int id_answer_student;
    private int id_sectoin_student;
    private String answer;
    private int n_question;

    public AnswerStundent() {
    }

    public AnswerStundent(int id_answer_student, int id_sectoin_student, String answer, int n_question) {
        this.id_answer_student = id_answer_student;
        this.id_sectoin_student = id_sectoin_student;
        this.answer = answer;
        this.n_question = n_question;
    }

    public int getId_answer_student() {
        return id_answer_student;
    }

    public void setId_answer_student(int id_answer_student) {
        this.id_answer_student = id_answer_student;
    }

    public int getId_sectoin_student() {
        return id_sectoin_student;
    }

    public void setId_sectoin_student(int id_sectoin_student) {
        this.id_sectoin_student = id_sectoin_student;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getN_question() {
        return n_question;
    }

    public void setN_question(int n_question) {
        this.n_question = n_question;
    }
    
    
    
    
    
    
}
