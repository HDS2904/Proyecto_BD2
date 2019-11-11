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
public class Section {
    
    private int id_section;
    private int id_subject;
    private int id_person;
    private int id_exam;
    private String section_group;

    public Section() {
    }

    public Section(int id_section, int id_subject, int id_person, int id_exam, String section_group) {
        this.id_section = id_section;
        this.id_subject = id_subject;
        this.id_person = id_person;
        this.id_exam = id_exam;
        this.section_group = section_group;
    }

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }

    public String getSection_group() {
        return section_group;
    }

    public void setSection_group(String section_group) {
        this.section_group = section_group;
    }
    
    
    
    
}
