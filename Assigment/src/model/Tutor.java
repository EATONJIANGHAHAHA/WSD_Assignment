package model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents tutor in this online application.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Tutor extends User{

    public static final String WSD = "WSD";
    public static final String USP = "USP";
    public static final String SEP = "SEP";
    public static final String APP_PROG = "AppProg";
    public static final String MOBILE_APP = "MobileApp";
    public static final String[] SUBJECTS = { WSD, USP, SEP, APP_PROG, MOBILE_APP};

    @XmlElement(name = "speciality")
    String speciality;
    @XmlElement(name = "status")
    boolean isAvailable;

    public Tutor(Integer id, String email, String name, String password,
                 String dateOfBirth, String speciality, boolean isAvailable) {
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }

    public Tutor(){}


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
