package model;


import adapter.IDAdapter;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * This class represents user in this online application.
 */
@XmlRootElement(name = "user")
public class User {

    public static final String WSD = "WSD";
    public static final String USP = "USP";
    public static final String SEP = "SEP";
    public static final String APP_PROG = "AppProg";
    public static final String MOBILE_APP = "MobileApp";
    public static final String[] SUBJECTS = { WSD, USP, SEP, APP_PROG, MOBILE_APP};
    public static final String SPECIALITY = "speciality";
    public static final String STUDENT = "student";
    public static final String TUTOR = "tutor";
    public static final String TYPE = "type";
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String DATE_OF_BIRTH = "dateOfBirth";

    private Integer id = 0;
    private String email;
    private String name;
    private String password;
    private Date dateOfBirth;
    @XmlTransient
    private String speciality;
    private Boolean isAvailable;

    /**
     * Used to create a student within a id.
     * @param id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     */
    public User(Integer id, String email, String name, String password,
                Date dateOfBirth) {
        this(email, name, password, dateOfBirth);
        this.setId(id);
    }

    /**
     * Used to create a student without an id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     */
    public User( String email, String name, String password,
                Date dateOfBirth) {
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
    }

    /**
     * Used to generate a tutor without an id.
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     * @param speciality
     * @param isAvailable
     */
    public User(String email, String name, String password,
                Date dateOfBirth, String speciality, boolean isAvailable) {
        this(email, name, password, dateOfBirth);
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }


    /**
     * Used to create a tutor within id
     * @param id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     * @param speciality
     * @param isAvailable
     */
    public User(Integer id, String email, String name, String password,
                Date dateOfBirth, String speciality, boolean isAvailable) {
        this(id, email, name, password, dateOfBirth);
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }

    public User(){}

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
        this.password = password;
    }

    public void setEncrypPassword(String password){
        setPassword(DigestUtil.encryptPWD(password));
    }

    @XmlElement(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @XmlElement(name = "id")
    @XmlJavaTypeAdapter(IDAdapter.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public boolean isStudent(){ return speciality == null && isAvailable == null; }

    @XmlElement(name = "speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @XmlElement(name = "status")
    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
