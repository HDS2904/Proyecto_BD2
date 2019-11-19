
package Interfaz;

import java.util.ArrayList;

public interface ICRUD<T> {
    
    public void Create(T t) throws Exception;

    public void Update(T t) throws Exception;

    public void Delete(T t) throws Exception;

    public T Search(int t) throws Exception;

    public ArrayList<T> ListAll() throws Exception;
}
