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
public class School {
    
    private int id_school;
    private int id_faculty;
    private String name_school;

    public School() {
    }

    public School(int id_school, int id_faculty, String name_school) {
        this.id_school = id_school;
        this.id_faculty = id_faculty;
        this.name_school = name_school;
    }

    public int getId_school() {
        return id_school;
    }

    public void setId_school(int id_school) {
        this.id_school = id_school;
    }

    public int getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(int id_faculty) {
        this.id_faculty = id_faculty;
    }

    public String getName_school() {
        return name_school;
    }

    public void setName_school(String name_school) {
        this.name_school = name_school;
    }
    
    
    
    
}
