
package Modelo.Entidades;

public class Teacher {
    private int id_person;
    private String code_teacher;

    public Teacher() {
    }

    public Teacher(int id_person, String code_teacher) {
        this.id_person = id_person;
        this.code_teacher = code_teacher;
    }
    
    public int getId_person() {
        return id_person;
    }

    public void setId_person(int dni_person) {
        this.id_person = dni_person;
    }

    public String getCode_teacher() {
        return code_teacher;
    }

    public void setCode_teacher(String code_teacher) {
        this.code_teacher = code_teacher;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id_person=" + id_person + ", code_teacher=" + code_teacher;
    }

    
    
    
}
