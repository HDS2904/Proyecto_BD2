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
public class Note {
    
    
    private int id_note;
    private int id_section_student;
    private float note;

    public Note() {
    }

    public Note(int id_note, int id_section_student, float note) {
        this.id_note = id_note;
        this.id_section_student = id_section_student;
        this.note = note;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getId_section_student() {
        return id_section_student;
    }

    public void setId_section_student(int id_section_student) {
        this.id_section_student = id_section_student;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
    
    
    
}
