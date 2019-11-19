
package Modelo.Entidades;

public class Person {
    
    private int id_person;
    private String firs_name;
    private String last_name;
    private int dni;
    private int phone;
    private String address;
    private String email;

    public Person(){
        
    }

    public Person(int id_person, String firs_name, String last_name, int dni, int phone, String address, String email) {
        this.id_person = id_person;        
        this.firs_name = firs_name;
        this.last_name = last_name;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
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
    
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }     
     
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Person{" + "id_person=" + id_person + ", firs_name=" + firs_name + ", last_name=" + last_name + ", dni=" + dni + ", phone=" + phone + ", address=" + address + ", email=" + email + '}';
    }

}