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
public class Subject {
    
    private int id_subject;
    private String name_subject;

    public Subject() {
    }

    public Subject(int id_subject, String name_subject) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    @Override
    public String toString() {
        return name_subject;
    }
    
    
    
    
    
}
