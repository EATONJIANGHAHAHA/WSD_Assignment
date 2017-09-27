package model;



import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * This class represents tutor in this online application.
 */
@XmlRootElement(name = "tutor")
public class Tutor extends User{

    public static final String WSD = "WSD";
    public static final String USP = "USP";
    public static final String SEP = "SEP";
    public static final String APP_PROG = "AppProg";
    public static final String MOBILE_APP = "MobileApp";
    public static final String[] SUBJECTS = { WSD, USP, SEP, APP_PROG, MOBILE_APP};
    public static final String SPECIALITY = "speciality";


    @XmlTransient
    private String speciality;

    private boolean isAvailable;

    public Tutor(Integer id, String email, String name, String password,
                 Date dateOfBirth, String speciality, boolean isAvailable) {
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }

    public Tutor(){}


    @XmlElement(name = "speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @XmlElement(name = "status")
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
