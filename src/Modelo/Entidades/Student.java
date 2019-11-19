
package Modelo.Entidades;

public class Student {
    private int id_person;
    private String code_student;
    private String id_school;

    public Student(int id_person, String code_student, String id_school) {
        this.id_person = id_person;
        this.code_student = code_student;
        this.id_school = id_school;
    }
    
    public Student(){
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getCode_student() {
        return code_student;
    }

    public void setCode_student(String code_student) {
        this.code_student = code_student;
    }

    public String getId_school() {
        return id_school;
    }

    public void setId_school(String id_school) {
        this.id_school = id_school;
    }

    @Override
    public String toString() {
        return "Student{" + "id_person=" + id_person + ", code_student=" + code_student + ", id_school=" + id_school + '}';
    }
    
    
}
