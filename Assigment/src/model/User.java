package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Date;

/**
 * This class representing the user of this online service
 */

@XmlSeeAlso({Student.class, Tutor.class})
public abstract class User implements Serializable {

    public static final String STUDENT = "student";
    public static final String TUTOR = "tutor";
    public static final String TYPE = "type";

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name ="name")
    private String name;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "date_of_birth")
    private Date dateOfBirth;


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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
