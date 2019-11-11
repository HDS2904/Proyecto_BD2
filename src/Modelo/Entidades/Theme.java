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
public class Theme {
    
    private int id_theme;
    private int id_subject;
    private String name_theme;

    public Theme() {
    }

    public Theme(int id_theme, int id_subject, String name_theme) {
        this.id_theme = id_theme;
        this.id_subject = id_subject;
        this.name_theme = name_theme;
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_theme() {
        return name_theme;
    }

    public void setName_theme(String name_theme) {
        this.name_theme = name_theme;
    }
    
    
    
    
    
}
