
package Modelo.Entidades;

public class Teacher {
    private int dni_teacher;
    private String cod_teacher;
    private Person per;

    public Teacher() {
    }

    public Teacher(int dni_teacher, String cod_teacher, Person per) {
        this.dni_teacher = dni_teacher;
        this.cod_teacher = cod_teacher;
        this.per = per;
    }
    
    public int getDni_teacher() {
        return dni_teacher;
    }

    public void setDni_teacher(int dni_teacher) {
        this.dni_teacher = dni_teacher;
    }

    public String getCod_teacher() {
        return cod_teacher;
    }

    public void setCod_teacher(String cod_teacher) {
        this.cod_teacher = cod_teacher;
    }

    public Person getPer() {
        return per;
    }

    public void setPer(Person per) {
        this.per = per;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id_teacher=" + dni_teacher + ", cod_teacher=" + cod_teacher + ", per=" + per + '}';
    }

    
    
    
}
