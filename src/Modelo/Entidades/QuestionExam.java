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
public class QuestionExam {
    
    private int id_question_exam;
    private int id_exam;
    private String question;
    private int n_question;

    public QuestionExam() {
    }

    public QuestionExam(int id_question_exam, int id_exam, String question, int n_question) {
        this.id_question_exam = id_question_exam;
        this.id_exam = id_exam;
        this.question = question;
        this.n_question = n_question;
    }

    public int getId_question_exam() {
        return id_question_exam;
    }

    public void setId_question_exam(int id_question_exam) {
        this.id_question_exam = id_question_exam;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getN_question() {
        return n_question;
    }

    public void setN_question(int n_question) {
        this.n_question = n_question;
    }
    
    
    
    
}
