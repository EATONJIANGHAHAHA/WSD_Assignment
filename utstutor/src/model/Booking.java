package model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "booking")
@XmlType(propOrder = {"studentName", "studentEmail", "tutorName","tutorEmail", "subject", "status"})
public class Booking extends BaseModel{

    public static final String CANCELLED = "cancelled";
    public static final String ACTIVE = "active";
    public static final String COMPLETED = "completed";

    private String studentName;
    private String studentEmail;
    private String tutorName;
    private String tutorEmail;
    private String subject;
    private String status;

    public Booking(){}

    public Booking(Integer id, String studentName, String studentEmail, String tutorName, String tutorEmail,
                   String subject, String status) {
        this.setId(id);
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.subject = subject;
        this.status = status;
    }

    public Booking(String studentName, String studentEmail, String tutorName, String tutorEmail,
                   String subject) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.subject = subject;
        this.status = ACTIVE;
    }
    public Booking(String studentName, String studentEmail, String tutorName, String tutorEmail,
                   String subject, String status) {
        this(studentName, studentEmail, tutorName, tutorEmail, subject);
        this.setStatus(status);
    }

    @XmlElement(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @XmlElement(name = "student_email")
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTutorName() {
        return tutorName;
    }

    @XmlElement(name = "tutor_name")
    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    @XmlElement(name = "tutor_email")
    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    @XmlElement(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " student name: " + getStudentName()
                + " student email: " + getStudentEmail() + " tutor name: "
                + getTutorEmail() + " subject: " + getSubject() + " status: "
                + getStatus();
    }
}
