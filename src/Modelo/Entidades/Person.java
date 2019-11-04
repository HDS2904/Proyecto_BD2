
package Modelo.Entidades;

public class Person {
    private String id_person;
    private String firs_name;
    private String last_name;
    private int phone;
    private String direccion;
    private String email;

    public Person(String id_person, String firs_name, String last_name, int phone, String direccion, String email) {
        this.id_person = id_person;
        this.firs_name = firs_name;
        this.last_name = last_name;
        this.phone = phone;
        this.direccion = direccion;
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Person(){
        
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public String getFirs_name() {
        return firs_name;
    }

    public void setFirs_name(String firs_name) {
        this.firs_name = firs_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" + "id_person=" + id_person + ", firs_name=" + firs_name + ", last_name=" + last_name + ", phone=" + phone + ", direccion=" + direccion + ", email=" + email + '}';
    }

    
    
}
