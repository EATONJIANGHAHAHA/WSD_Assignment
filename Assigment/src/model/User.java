package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * This class representing the user of this online service
 */

@XmlSeeAlso({Student.class, Tutor.class})
public abstract class User implements Serializable {

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name ="name")
    private String name;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "date_of_birth")
    private String dateOfBirth;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
