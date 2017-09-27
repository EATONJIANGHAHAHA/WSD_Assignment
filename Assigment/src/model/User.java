package model;

import util.DigestUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

/**
 * This class representing the user of this online service
 */
//@XmlTransient
@XmlSeeAlso({Student.class, Tutor.class})
public abstract class User implements Serializable {

    public static final String STUDENT = "student";
    public static final String TUTOR = "tutor";
    public static final String TYPE = "type";


    private Integer id;
    private String email;
    private String name;
    private String password;
    private Date dateOfBirth;

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name ="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtil.encryptPWD(password);
    }

    @XmlElement(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
