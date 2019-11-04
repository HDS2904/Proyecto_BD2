
package Interfaz;

import javax.swing.JTable;

public interface ICRUD<T> {
    
    public String Create(T t);

    public String Update(T t);

    public String Delete(T t);

    public String SearchId(String r, T t);

    public String ListAll(JTable tabla);
}
