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

    @XmlElement(name = "student")
    private Student student;

    @XmlElement(name = "tutor")
    private Tutor tutor;

    @XmlElement(name = "status")
    private String status;

    public Booking(){}

    public Booking(Integer id, Student student, Tutor tutor, String status) {
        this.id = id;
        this.student = student;
        this.tutor = tutor;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
