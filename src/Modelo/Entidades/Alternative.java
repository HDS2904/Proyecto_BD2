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
public class Alternative {

    private int id_alternative;
    private int id_question;
    private String alternative_A;
    private String alternative_B;
    private String alternative_C;
    private String answer;

    public Alternative() {
    }

    public Alternative(int id_alternative, int id_question, String alternative_A, String alternative_B, String alternative_C, String answer) {
        this.id_alternative = id_alternative;
        this.id_question = id_question;
        this.alternative_A = alternative_A;
        this.alternative_B = alternative_B;
        this.alternative_C = alternative_C;
        this.answer = answer;
    }

    public int getId_alternative() {
        return id_alternative;
    }

    public void setId_alternative(int id_alternative) {
        this.id_alternative = id_alternative;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
    
    
    


    
}

