package model;


import util.DigestUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * This class represents user in this online dao.
 */
@XmlRootElement(name = "user")
@XmlType(propOrder = {"name", "email", "password","dateOfBirth", "speciality", "availability"})
public class User extends BaseModel {

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
    public static final String AVAILABLE = "available";
    public static final String UNAVAILABLE = "unavailable";

    private String email;
    private String name;
    private String password;
    private String dateOfBirth;
    @XmlTransient
    private String speciality;
    private String availability;

    /**
     * Used to create a student within a id.
     * @param id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     */
    public User(Integer id, String email, String name, String password,
                String dateOfBirth) {
        this(email, name, password, dateOfBirth);
        this.setId(id);
    }

    public User(Integer id, String email, String name, String password,
                String dateOfBirth, boolean isStudent, String speciality) {
        this(email, name, password, dateOfBirth);
        this.setId(id);
        if(!isStudent) {
            this.setSpeciality(speciality);
            this.setAvailability(AVAILABLE);
        }
    }

    /**
     * Used to create a student without an id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     */
    public User(String email, String name, String password,
                String dateOfBirth) {
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
     * @param availability
     */
    public User(String email, String name, String password,
                String dateOfBirth, String speciality, String availability) {
        this(email, name, password, dateOfBirth);
        this.speciality = speciality;
        this.availability = availability;
    }


    /**
     * Used to create a tutor within id
     * @param id
     * @param email
     * @param name
     * @param password
     * @param dateOfBirth
     * @param speciality
     * @param availability
     */
    public User(Integer id, String email, String name, String password,
                String dateOfBirth, String speciality, String availability) {
        this(id, email, name, password, dateOfBirth);
        this.speciality = speciality;
        this.availability = availability;
    }

    public User(){}


    @XmlElement(name ="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public boolean isStudent(){ return speciality == null && availability == null; }

    public boolean isAvailable(){ return availability.equals(AVAILABLE); }

    @XmlElement(name = "speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @XmlElement(name = "status")
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @XmlTransient
    public String getType(){
        if(isStudent()) return STUDENT;
        else return TUTOR;
    }
}
