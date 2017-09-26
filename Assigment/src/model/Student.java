package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * This class represents student in this online application.
 */

@XmlRootElement(name = "student")
public class Student extends User {
    public Student(){}
    public Student(Integer id, String email, String name, String password,
                   Date dataOfBirth){
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setDateOfBirth(dataOfBirth);
    }
}
