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
public class SectionStudent {
    
    private int id_section_student;
    private int id_section;
    private int id_person;
    private int id_answer_student;
    private int id_note;

    public SectionStudent() {
    }

    public SectionStudent(int id_section_student, int id_section, int id_person, int id_answer_student, int id_note) {
        this.id_section_student = id_section_student;
        this.id_section = id_section;
        this.id_person = id_person;
        this.id_answer_student = id_answer_student;
        this.id_note = id_note;
    }

    public int getId_section_student() {
        return id_section_student;
    }

    public void setId_section_student(int id_section_student) {
        this.id_section_student = id_section_student;
    }

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getId_answer_student() {
        return id_answer_student;
    }

    public void setId_answer_student(int id_answer_student) {
        this.id_answer_student = id_answer_student;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }
    
    
    
    
    
    
}
