
package Interfaz;

import java.util.ArrayList;

public interface ICRUD<T> {
    
    public String Create(T t);

    public String Update(T t);

    public String Delete(T t);

    public T SearchId(int t);

    public ArrayList<T> ListAll();
}
