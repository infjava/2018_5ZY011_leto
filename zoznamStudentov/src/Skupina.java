
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
class Skupina {

    private final String cisloSkupiny;
    private final ArrayList<Student> zoznamStudentov;

    Skupina(String cisloSkupiny) {
        this.cisloSkupiny = cisloSkupiny;
        this.zoznamStudentov = new ArrayList<Student>();
    }

    public String getCisloSkupiny() {
        return this.cisloSkupiny;
    }

    boolean pridajStudenta(Student student) {
        if (this.getStudent(student.getOsobneCislo()) != null) {
            return false;
        }
        this.zoznamStudentov.add(student);
        return true;
    }

    void vypisStudentov() {
        for (Student student : this.zoznamStudentov) {
            System.out.println(student.toString());
        }
    }

    Student getStudent(String osobneCislo) {
        for (Student student : this.zoznamStudentov) {
            if (student.getOsobneCislo().equals(osobneCislo)) {
                return student;
            }
        }
        
        return null;
    }
    
}
