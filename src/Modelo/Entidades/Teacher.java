
package Modelo.Entidades;

public class Teacher {
    private String id_teacher;
    private String cod_teacher;
    private Person per;

    public Teacher() {
    }

    public Teacher(String id_teacher, String cod_teacher, Person per) {
        this.id_teacher = id_teacher;
        this.cod_teacher = cod_teacher;
        this.per = per;
    }
    
    public String getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(String id_teacher) {
        this.id_teacher = id_teacher;
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
        return "Teacher{" + "id_teacher=" + id_teacher + ", cod_teacher=" + cod_teacher + ", per=" + per + '}';
    }

    
    
    
}
