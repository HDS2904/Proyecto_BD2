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
public class Faculty {
    
    private int id_faculty;
    private int id_person;
    private String name_faculty;

    public Faculty() {
    }

    public Faculty(int id_faculty, int id_person, String name_faculty) {
        this.id_faculty = id_faculty;
        this.id_person = id_person;
        this.name_faculty = name_faculty;
    }

    public int getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(int id_faculty) {
        this.id_faculty = id_faculty;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName_faculty() {
        return name_faculty;
    }

    public void setName_faculty(String name_faculty) {
        this.name_faculty = name_faculty;
    }
    
    
    
    
    
    
}
