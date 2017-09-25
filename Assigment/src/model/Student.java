package model;

/**
 * This class represents student in this online application.
 */

public class Student extends User {
    public Student(){}
    public Student(Integer id, String email, String name, String password,
                   String dataOfBirth){
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setDateOfBirth(dataOfBirth);
    }
}
