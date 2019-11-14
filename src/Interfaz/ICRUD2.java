/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public interface ICRUD2<T> {
    
    public String Create(T t);

    public String Update(T t);

    public String Delete(T t);

    public String SearchId(T t);

    public String ListAll(JTable tabla);
}