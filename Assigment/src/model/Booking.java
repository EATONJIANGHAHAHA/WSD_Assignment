package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Booking implements Serializable {

    public static final String CANCELLED = "cancelled";
    public static final String ACTIVE = "active";
    public static final String COMPLETED = "completed";

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "student_name")
    private String studentName;

    @XmlElement(name = "student_email")
    private String studentEmail;

    @XmlElement(name = "tutor_name")
    private String tutorName;

    @XmlElement(name = "tutor_email")
    private String tutorEmail;

    @XmlElement(name = "subject")
    private String subject;

    @XmlElement(name = "status")
    private String status;

    public Booking(){}

    public Booking(Integer id, String studentName, String studentEmail, String tutorName, String tutorEmail,
                   String subject, String status) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.subject = subject;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
