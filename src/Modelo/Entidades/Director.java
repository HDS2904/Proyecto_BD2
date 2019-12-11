
package Modelo.Entidades;

public class Director {
    private int id_person;
    private String code_director;
    private int id_faculty;

    public Director(int id_person, String code_director, int id_faculty) {
        this.id_person = id_person;
        this.code_director = code_director;
        this.id_faculty = id_faculty;
    }
    
    public Director(){
        
    }
    
    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getCode_director() {
        return code_director;
    }

    public void setCode_director(String code_director) {
        this.code_director = code_director;
    }

    public int getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(int id_faculty) {
        this.id_faculty = id_faculty;
    }

    
    @Override
    public String toString() {
        return "Director{" + "id_person=" + id_person + ", code_director=" + code_director + ", id_faculty=" + id_faculty + '}';
    }
}
