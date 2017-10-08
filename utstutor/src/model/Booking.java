package model;

import javax.xml.bind.annotation.*;

/**
 * The model of booking, representing the booking record.
 */
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

    /**
     * Get the student's name.
     * @return
     */
    @XmlElement(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    /**
     * Set the student's name.
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Get the student's email.
     * @return
     */
    @XmlElement(name = "student_email")
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Set the student's email.
     * @param studentEmail
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Get the name of the tutor.
     * @return
     */
    @XmlElement(name = "tutor_name")
    public String getTutorName() {
        return tutorName;
    }

    /**
     * Get the name of the tutor.
     * @param tutorName
     */
    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    /**
     * Get the tutor's email.
     * @return
     */
    public String getTutorEmail() {
        return tutorEmail;
    }

    /**
     * Email of the tutor
     * @param tutorEmail
     */
    @XmlElement(name = "tutor_email")
    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    /**
     * Get subject of this booking.
     * @return
     */
    @XmlElement(name = "subject")
    public String getSubject() {
        return subject;
    }

    /**
     * Set subject.
     * WSD, USP, SEP...
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get status.
     * @return
     */
    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    /**
     * Set status.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * To string method.
     * @return
     */
    @Override
    public String toString() {
        return "id: " + getId() + " student name: " + getStudentName()
                + " student email: " + getStudentEmail() + " tutor name: "
                + getTutorEmail() + " subject: " + getSubject() + " status: "
                + getStatus();
    }
}
